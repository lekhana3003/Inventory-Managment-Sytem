package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.InventoryDao;
import com.example.demo.Model.Inventory;

@RestController
public class InventoryRespository {

	
	@Autowired
	InventoryDao inventoryDao;
	public InventoryRespository() {
		// TODO Auto-generated constructor stub
	}
	    public List<Inventory> getAllInventory() {
	        return inventoryDao.findAll();
	    }
	 
	 public Inventory addInventory(String name,String desc)
	 {	
		 Inventory inventory=new Inventory(name,desc);
		 Inventory newInventory=inventoryDao.save(inventory);
		return newInventory;
	 }
	 
	 
	 public Optional<Inventory> editInventory(Long invId)
	 {
		return inventoryDao.findById(invId);
	 }
	 
	
	 public Inventory updateInventoryValues(Inventory inventory)
	 {	
		
		Optional<Inventory> old=inventoryDao.findById(inventory.getInvId());
		old.get().setInvName(inventory.getInvName());
		old.get().setInvDesc(inventory.getInvDesc());
		Inventory updateInventory=inventoryDao.save(old.get());
		return updateInventory;
	 }
	 
	 public String deleteInventoryValues(Long invId)
	 {
		 inventoryDao.deleteById(invId);
		 return "Success";
	 }
}
