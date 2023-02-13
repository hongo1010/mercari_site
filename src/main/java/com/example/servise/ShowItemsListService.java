package com.example.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Items;
import com.example.repository.ItemsRepository;

/**
 * 商品一覧の表示、検索を行うserviceクラス.
 * 
 * @author hongo
 *
 */
@Service
public class ShowItemsListService {
	@Autowired
	private ItemsRepository repisitory;

	/**
	 * 商品一覧を画面を表示した際に出てくる商品一覧の情報を検索するメソッド.
	 * 
	 * @return 商品一覧
	 */
	public List<Items> showItemList() {

		List<Items> ItemsList = repisitory.findAll();

		return ItemsList;
	}

	/**
	 * ページ移動した際や、ページから情報を検索する際に使用されるメソッド.
	 * 
	 * @param offset
	 * @return ページングで使用される商品一覧のデータ
	 */
	public List<Items> paging(int offset) {
		return repisitory.paging(offset);
	}
}
