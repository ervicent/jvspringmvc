package com.naskar.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.naskar.springmvc.domain.Item;
import com.naskar.springmvc.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	private List<Item> items;
	
	public ItemServiceImpl() {
		items = new ArrayList<Item>();
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void save(Item item) {
		if(items.contains(item)) {
			items.remove(item);
		}
		items.add(item);
	}
	
	public void remove(Item item) {
		items.remove(item);
	}
	
	public Item load(Integer itemId) {
		int i = items.indexOf(new Item(itemId, ""));
		if(i > -1)
			return items.get(i);
		else 
			return null;
	}

}
