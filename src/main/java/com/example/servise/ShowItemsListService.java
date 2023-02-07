package com.example.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Items;
import com.example.repository.ItemsRepository;

/**
 * 商品一覧の表示、検索を行うserviceクラス.
 * @author hongo
 *
 */
@Service
public class ShowItemsListService {
	@Autowired
	private ItemsRepository repisitory;

	public List<Items> showItemList(){
		
		List<Items> ItemsList = repisitory.findAll();
		
		return ItemsList;
	}
	public List<Items> paging( int offset) {
		return repisitory.paging(offset);
	}
}
