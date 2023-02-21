package com.example.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Items;
import com.example.repository.ItemsRepository;

/**
 * 商品編集の商品の読み込み、更新の操作を行うServiceクラス.
 * 
 * @author hongo
 *
 */
@Service
@Transactional
public class EditService {
	@Autowired
	private ItemsRepository ItemsRepository;

	/**
	 * 商品編集時にその商品の情報を読み込むメソッド.
	 * 
	 * @param id
	 * @return
	 */
	public Items Editload(Integer id) {
		return ItemsRepository.load(id);

	}

	/**
	 * 商品情報の更新を行うメソッド.
	 * @param id
	 */
	public void update(Items item) {
		ItemsRepository.update(item);
	}
}
