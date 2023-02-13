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
	 * @param offset
	 * @return 100件の商品一覧のデータ
	 */
	public List<Items> showItemList(int offset) {
		return repisitory.paging(offset);
	}
}
