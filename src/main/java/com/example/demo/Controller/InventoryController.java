package com.example.demo.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Inventory;
import com.example.demo.Repository.InventoryRespository;
import com.google.gson.*;


@RestController
public class InventoryController {
	@Autowired
	InventoryRespository ic;
	Gson gson = new Gson();

	public InventoryController() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping(value="/inventory")
	@CrossOrigin(origins = "http://localhost:3000")
	public Map<String, List<Inventory>> inventory() throws ResourceNotFoundException {
		System.out.println("called api");
		List<Inventory> inventoryList = ic.getAllInventory();
		if (inventoryList.isEmpty()==true)
			throw new ResourceNotFoundException("Inventory not found");
		Map<String,List<Inventory>> jsonMap= new LinkedHashMap<String,List<Inventory>>();
		jsonMap.put("items", inventoryList);
		return jsonMap;
	}
	@PostMapping(value="/addInventory")
		@CrossOrigin(origins = "http://localhost:3000")
	public Inventory addInventory(@RequestBody String responseJson) throws ResourceNotFoundException
	{	
		
		JSONObject jsonObject =  new JSONObject(responseJson);
        String name = jsonObject.get("invName").toString();
        String desc = jsonObject.get("invDesc").toString();
        
		Inventory newInventory = ic.addInventory(name,desc);
		if(newInventory==null)
			throw new ResourceNotFoundException("Inventory not Created");
		return newInventory;
	}
	
	@GetMapping(value="/editInventory/{invId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<Inventory> addInventory(@PathVariable(name="invId")Long invId) throws ResourceNotFoundException
	{
		Optional<Inventory> inventory = ic.editInventory(invId);
		if(inventory==null)
			throw new ResourceNotFoundException("InventoryID Id"+invId+" not found");
		return inventory;
	}

	@PostMapping(value="/updateInventory")
	@CrossOrigin(origins = "http://localhost:3000")
	public Inventory updateInventory(@RequestBody String responseJson) throws ResourceNotFoundException
	{	
		Inventory inventory=gson.fromJson(responseJson,Inventory.class);
		Inventory updateInventory = ic.updateInventoryValues(inventory);
		if(updateInventory==null)
			throw new ResourceNotFoundException("Inventory not updated");
		return updateInventory;
	}
	
	@GetMapping(value="/deleteInventory/{invId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteInventory(@PathVariable(name="invId")Long invId) throws ResourceNotFoundException
	{
		String code=ic.deleteInventoryValues(invId);
		if(code.isEmpty())
				throw new ResourceNotFoundException("Delete Failed!");
		return code;
	}
}