package com.example.servise;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Items;
import com.example.form.ItemsForm;
import com.example.repository.ItemsRepository;

/**
 * 商品の追加を行うServiceクラス.
 * @author hongo
 *
 */
@Service
@Transactional
public class AddItemService {
	
	@Autowired
	private ItemsRepository ItemsRepository;
	
	/**
	 * 商品追加を行うメソッド.
	 * @param form
	 */
	public void addItem(ItemsForm form) {
		Items newItem = new Items();
		BeanUtils.copyProperties(form, newItem);
		newItem.setShipping(1);
		ItemsRepository.insert(newItem);
	}
	
	

}
