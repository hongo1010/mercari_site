package com.example.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Items;
import com.example.repository.ItemsRepository;

/**
 * 商品詳細画面の表示を行うServiceクラス.
 * 
 * @author hongo
 *
 */
@Service
public class ShowItemDetailService {

	@Autowired
	private ItemsRepository repository;
	
	public Items showItemsDetail(Integer id) {
		
		return repository.load(id);
	} 
	
	
}
