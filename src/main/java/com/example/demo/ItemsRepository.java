package com.example.demo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@RestController
public class ItemsRepository {

	@Autowired
	ItemsDao itemsRepository;
	@Autowired
	InventoryDao inventoryRepository;
	public ItemsRepository() {
		// TODO Auto-generated constructor stub
		
	}
	
	 @GetMapping("/getAllItems")
	    public List<Items> getAllItems() {
	        return itemsRepository.findAll();
	    }
	 @PostMapping("/addItem")
	 public List<Items> addInventory(@ModelAttribute(name="item")Items item,Model m)
	 {
		itemsRepository.save(item);
		return itemsRepository.findAll();
	 }
	 @GetMapping("/itemsInv/{invId}")
	    public List<Items> getItems(@PathVariable("invId") Integer invId) {
	        return itemsRepository.findByInv(inventoryRepository.findById(invId));
	    }
	
}
