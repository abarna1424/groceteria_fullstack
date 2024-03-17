package com.groceteria.service;

import java.util.List;

import com.groceteria.entity.Category;
import com.groceteria.entity.Item;
import com.groceteria.entity.ItemPaging;

public interface ItemService {
	
	Item addItem(Item item);
	
	List<Item>getAllItems();
	
	Item getItemByItemId(long itemId);
	
	Item updateItemWithQuantity(Item item,long itemId, long avaiableQuatity);
	Item updateItem(Item item,long itemId);
	
	void deleteItem(long itemId);
	
	List<Item>findByCategory(Category category);
	
	ItemPaging findByCategory(Category category, Integer pageNo, Integer pageSize);
	
	ItemPaging getAllItems(Integer pageNo, Integer pageSize);
	
	List<Item>findByMrpPrice(double mrpPrice);
	
	ItemPaging findByItemName(String keyword, Integer pageNo, Integer pageSize);
	
	
	

}
