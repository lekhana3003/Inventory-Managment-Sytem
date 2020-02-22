package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Items;
import com.example.demo.Repository.ItemsRepository;
import com.google.gson.Gson;
@RestController
public class ItemsController {
	@Autowired
	ItemsRepository ir;
	Gson gson = new Gson();
	
	public ItemsController() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping(value="/getItems/{invId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Items> getItems(@PathVariable("invId")Long invId) throws ResourceNotFoundException {
		List<Items> itemsList = ir.getAllItemsByInvId(invId);
		if (itemsList.isEmpty()==true)
			throw new ResourceNotFoundException("Items not found");
		return itemsList;
	}
	@PostMapping(value="/addItem")
	@CrossOrigin(origins = "http://localhost:3000")
	public Items addItem(@RequestBody String responseJson) throws ResourceNotFoundException
	{	
		
		JSONObject jsonObject =  new JSONObject(responseJson);
        String name = jsonObject.get("itemName").toString();
        Integer qty = Integer.valueOf(jsonObject.get("qty").toString()); 
        Long invId = Long.valueOf(jsonObject.get("invId").toString());   
		Items newItem = ir.addItemValues(name,qty,invId);
		if(newItem==null)
			throw new ResourceNotFoundException("Item not Created");
		return newItem;
	}
	
	@GetMapping(value="/editItem/{itemId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Items editItems(@PathVariable(name="itemId")Long itemId) throws ResourceNotFoundException
	{
		Items inventory = ir.editItemsValues(itemId);
		if(inventory==null)
			throw new ResourceNotFoundException("ItemID Id"+itemId+" not found");
		return inventory;
	}
	
	@PostMapping(value="/updateItem")
	@CrossOrigin(origins = "http://localhost:3000")
	public Items updateItem(@RequestBody String responseJson) throws ResourceNotFoundException
	{	
		JSONObject jsonObject =  new JSONObject(responseJson);
        String name = jsonObject.get("itemName").toString();
        Integer qty = Integer.valueOf(jsonObject.get("qty").toString()); 
        Long itemId = Long.valueOf(jsonObject.get("itemId").toString());  
		Items updateItem = ir.updateItemValues(name,qty,itemId);
		if(updateItem==null)
			throw new ResourceNotFoundException("Item not updated");
		return updateItem;
	}
	
	@GetMapping(value="/deleteItem/{itemId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteItems(@PathVariable(name="itemId")Long itemId) throws ResourceNotFoundException
	{
		String code=ir.deleteItemsValues(itemId);
		if(code.isEmpty())
				throw new ResourceNotFoundException("Delete Failed!");
		return code;
	}

}
