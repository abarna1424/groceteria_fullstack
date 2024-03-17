package com.groceteria.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.groceteria.entity.Category;
import com.groceteria.entity.Item;
import com.groceteria.entity.ItemPaging;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.ItemRepository;
import com.groceteria.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item addItem(Item item) {
		System.out.println("Item added Successfully "+item);
		item.setItemName(item.getItemName());
		item.setQuantity(item.getQuantity());
		item.setMrpPrice(item.getMrpPrice());
		item.setDescription(item.getDescription());
		return itemRepository.save(item);
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	@Override
	public Item getItemByItemId(long itemId) {
		return itemRepository.findById(itemId).orElseThrow(()->new ResourceNotFoundException("item", "Id", itemId));
	}

	@Override
	public Item updateItem(Item item, long itemId) {
		Item existingItem=itemRepository.findById(itemId).orElseThrow(()->new ResourceNotFoundException("item", "itemId", itemId));
		System.out.println("^^^^ " + item.getItemId());
		System.out.println("^^^^Quinity^^" + item.getQuantity());
		existingItem.setItemName(item.getItemName());
		existingItem.setMrpPrice(item.getMrpPrice());
		existingItem.setImage(item.getImage());
		existingItem.setDescription(item.getDescription());
		existingItem.setQuantity(item.getQuantity());
		
		itemRepository.save(existingItem);
		return existingItem;
	}
	
	@Override
	public Item updateItemWithQuantity(Item item, long itemId, long avaiableQantity) {
		Item existingItem=itemRepository.findById(itemId).orElseThrow(()->new ResourceNotFoundException("item", "itemId", itemId));
		System.out.println("^^^^ " + item.getItemId());
		System.out.println("^^^^Quinity^^" + item.getQuantity());
		existingItem.setItemName(item.getItemName());
		existingItem.setMrpPrice(item.getMrpPrice());
		existingItem.setImage(item.getImage());
		existingItem.setDescription(item.getDescription());
		existingItem.setQuantity(item.getQuantity() + avaiableQantity);
		
		itemRepository.save(existingItem);
		return existingItem;
	}
	
	

	@Override
	public void deleteItem(long itemId) {
		itemRepository.findById(itemId).orElseThrow(()->new ResourceNotFoundException("item", "Id", itemId));
		itemRepository.deleteById(itemId);
	}

	@Override
	public List<Item> findByCategory(Category category) {
		return itemRepository.findByCategory(category);
	}

	@Override
	public ItemPaging findByCategory(Category category, Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Item> itemResult=itemRepository.findByCategory(category,paging);
		ItemPaging ir=new ItemPaging();
		ir.setTotalItem(itemResult.getTotalElements());
		if(itemResult.hasContent()) {
			ir.setItem(itemResult.getContent());
		}
		else {
			ir.setItem(new ArrayList<Item>());
		}
		return ir;
	}

	@Override
	public ItemPaging getAllItems(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Item> itemResult = itemRepository.findAll(paging);
		ItemPaging ir = new ItemPaging();
		ir.setTotalItem(itemResult.getTotalElements());
		System.out.println(">>>>>"+itemResult.getTotalPages());
		if(itemResult.hasContent()) {
			ir.setItem(itemResult.getContent());
		}
		else {
			ir.setItem(new ArrayList<Item>());
		}
		return ir;
	}

	@Override
	public List<Item> findByMrpPrice(double mrpPrice) {
		return itemRepository.findByMrpPrice(mrpPrice);
	}

	@Override
	public ItemPaging findByItemName(String keyword, Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Item> itemResult=itemRepository.findByItemNameContains(keyword, paging);
		ItemPaging ir=new ItemPaging();
		ir.setTotalItem(itemResult.getTotalElements());
		if(itemResult.hasContent()) {
			ir.setItem(itemResult.getContent());
		}
		else {
			ir.setItem(new ArrayList<Item>());
		}
		return ir;
	}
	
	
}
