package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.Inventory;
//import com.google.gson.*;


@Controller
public class InventoryController {
	@Autowired
	InventoryRespository ic;
	Model inventoryView;
	//Gson gson = new Gson();

	public InventoryController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value="/inventory",method=RequestMethod.GET)
	public String inventory(Model m) {
		
		List<Inventory> inventoryList = ic.getAllInventory();
		m.addAttribute("inventoryList", inventoryList);
		return "inventory";
	}
	@RequestMapping(value="/addInventory",method=RequestMethod.POST)
	public String addInventory(@ModelAttribute(name="inventory")Inventory inventory,Model m)
	{
		List<Inventory> inventoryList = ic.addInventory(inventory);
		m.addAttribute("inventoryList", inventoryList);
		return "inventory";
	}
	//@RequestMapping(value="/editInventory/{invID}",method=RequestMethod.GET)
	/*public String addInventory(@PathVariable("invID") Integer invId,Model m)
	{
		List<Inventory> inventoryList = ic.addInventory(inventory.getInvName(), inventory.getInvDesc());
		m.addAttribute("inventoryList", inventoryList);
		return "inventory";
	}*/

}
