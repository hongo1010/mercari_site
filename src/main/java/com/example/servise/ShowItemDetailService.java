package com.example.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Items;
import com.example.repository.ItemsRepository;

/**
 * 商品詳細画面の表示を行うServiceクラス.
 * 
 * @author 
 *
 */
@Service
public class ShowItemDetailService {

	@Autowired
	private ItemsRepository repository;

	/**
	 * 商品詳細情報を取得するメソッド.
	 * 
	 * @param id
	 * @return 取ってきた商品情報
	 */
	public Items showItemsDetail(Integer id) {

		return repository.load(id);
	}

}
