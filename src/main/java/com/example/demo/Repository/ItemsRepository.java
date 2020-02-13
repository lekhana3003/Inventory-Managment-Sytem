package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Dao.InventoryDao;
import com.example.demo.Dao.ItemsDao;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Inventory;
import com.example.demo.Model.Items;



@RestController
public class ItemsRepository {

	@Autowired
	ItemsDao itemsDao;
	@Autowired
	InventoryDao inventoryDao;
	public ItemsRepository() {
		// TODO Auto-generated constructor stub
		
	}
	public List<Items> getAllItemsByInvId(Long invId) {
		//Inventory inventory=inventoryDao.getOne(invId);
        return itemsDao.findAllByInv(invId);
    }
	
	 public Items addItemValues(String name, Integer qty,Long invId)
	 {	
		Inventory inventory=inventoryDao.getOne(invId);
		Items item=new Items(name,qty,inventory);
		Items newItem=itemsDao.save(item);
		return newItem;
	 }
	 
	public Items editItemsValues(Long itemId) {
		Items item=itemsDao.getOne(itemId);
		return item;
	}
	
	 public Items updateItemValues(String name,Integer qty,Long itemId)
	 {	
		Optional<Items> old=itemsDao.findById(itemId);
		old.get().setItemName(name);
		old.get().setQty(qty);
		Items updateItem=itemsDao.save(old.get());
		return updateItem;
	 }
	public String deleteItemsValues(Long itemId) {
		
		itemsDao.deleteById(itemId);
		return "Success";
	}
	
}
