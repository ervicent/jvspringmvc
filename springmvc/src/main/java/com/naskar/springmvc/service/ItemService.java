package com.naskar.springmvc.service;

import java.util.List;

import com.naskar.springmvc.domain.Item;

public interface ItemService {

	List<Item> getItems();
	
	void save(Item item);
	
	void remove(Item item);
	
	Item load(Integer itemId);
}
