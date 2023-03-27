package com.example.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Items;
import com.example.form.ItemSearchForm;
import com.example.repository.ItemSearchRepository;

@Service
@Transactional
public class ItemSearchService {
	
	@Autowired
	private  ItemSearchRepository  ItemsearchRepository;
	
	public List<Items> search(int OUTPUT_NUM, ItemSearchForm searchItemForm) {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items i INNER JOIN category c ON i.category = c.id ";
		sql += "WHERE i.name ILIKE " + "'" + "%" + searchItemForm.getName() + "%" + "'" + " AND i.brand ILIKE " + "'" + "%" + searchItemForm.getBrand() + "%" + "' ";
		
		if(searchItemForm.getSmallCategory() != null) {
			sql += "AND i.category=" + searchItemForm.getSmallCategory() + " ";
		}
		sql += "ORDER BY id LIMIT " + OUTPUT_NUM + ";";
		return ItemsearchRepository.findByName(sql);
	}
	
	public List<Items> turnPage(int OUTPUT_NUM, int num2, ItemSearchForm searchLog) {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items i INNER JOIN category c ON i.category = c.id ";
		sql += "WHERE i.name ILIKE " + "'" + "%" + searchLog.getName() + "%" + "'" + " AND i.brand ILIKE " + "'" + "%" + searchLog.getBrand() + "%" + "' ";
		
		if(searchLog.getSmallCategory() != null) {
			sql += "AND i.category=" + searchLog.getSmallCategory() + " ";
		}
		sql += "ORDER BY id LIMIT " + OUTPUT_NUM + "OFFSET " + num2 + ";";
		return ItemsearchRepository.findByName(sql);
	}
}
