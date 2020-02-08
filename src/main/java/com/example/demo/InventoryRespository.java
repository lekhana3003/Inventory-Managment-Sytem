package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryRespository {

	@Autowired
	ItemsDao itemsRepository;
	@Autowired
	InventoryDao inventoryDao;
	public InventoryRespository() {
		// TODO Auto-generated constructor stub
	}
	    public List<Inventory> getAllInventory() {
	        return inventoryDao.findAll();
	    }
	 
	 public List<Inventory> addInventory(Inventory inv)
	 {	
		
		 inventoryDao.save(inv);
		return inventoryDao.findAll();
	 }
	 
	 @PostMapping("/editInventory/{invId}")
	 public Optional<Inventory> editInventory(@PathVariable("invId") Integer invId)
	 {
		return inventoryDao.findById(invId);
	 }
	 
	 @PostMapping("/updateInventory")
	 public List<Inventory> updateInventory(@ModelAttribute(name="inventory")Inventory inventory,Model m)
	 {
		Inventory old=inventoryDao.getOne(inventory.getInvId());
		old.setInvName(inventory.getInvName());
		old.setInvDesc(inventory.getInvDesc());
		inventoryDao.save(old);
		return inventoryDao.findAll();
	 }
	 @PostMapping("/deleteInventory/{invId}")
	 public List<Inventory> deleteInventory(@PathVariable("invId") Integer invId)
	 {
		Inventory inventory=inventoryDao.getOne(invId);
		//itemsRepository.deleteInBatch(itemsRepository.findByInv(inventory)));
		inventoryDao.delete(inventory);
		return inventoryDao.findAll();
	 }
}
