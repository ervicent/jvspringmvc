package com.naskar.springmvc.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.naskar.springmvc.domain.Item;
import com.naskar.springmvc.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("item/list");
        
        mav.addObject("items", itemService.getItems());
        
        return mav;
    }
	
	@RequestMapping("/item/add")
	public ModelAndView add() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("item/form");
        return mav;
    }
	
	@RequestMapping("/item/edit/{itemId}")
	public ModelAndView editar(@PathVariable Integer itemId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("item/form");
       
        mav.addObject("item", itemService.load(itemId));
       
        return mav;
    }
	
	@RequestMapping("/item/remove/{itemId}")
	public ModelAndView remove(@PathVariable Integer itemId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/app/item");
       
        itemService.remove(new Item(itemId, ""));
        
        List<String> messages = new ArrayList<String>();
        messages.add("Item removido com sucesso.");
        
        mav.addObject("item", itemService.getItems());
        mav.addObject("messages", messages);
       
        return mav;
    }
	
	@RequestMapping(value = "/item/save")
	public ModelAndView save(Item item) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:");
       
        itemService.save(item);
        
        List<String> messages = new ArrayList<String>();
        messages.add("Item salvo com sucesso.");
        
        mav.addObject("item", itemService.getItems());
        mav.addObject("messages", messages);
       
        return mav;
    }
	
	//
	
	@RequestMapping(value = "/item/list.json")
    public @ResponseBody List<Item> listjson() {
        return itemService.getItems();
	}

	
	//
	
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	

}
