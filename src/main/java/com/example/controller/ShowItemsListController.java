package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Items;
import com.example.repository.ItemsRepository;
import com.example.servise.ShowItemsListService;

/**
 * 商品一覧画面の操作を行うControllerクラス.
 * 
 * @author hongo
 *
 */
@Controller
@RequestMapping("/ShowItemsList")
public class ShowItemsListController {

	@Autowired
	private ShowItemsListService service;

	@Autowired
	private ItemsRepository repisitory;

	// １ページの最大表示数
	public static final int OUTPUT_NUM = 100;

	// itemsテーブルのレコード総数
	private int recordNum;

	// 現在のページ
	private int currentPage = 0;

	// 全レコードの件数から算出されるページ数（recordNum/outputNum）
	private int maxPage;

	/**
	 * 商品一覧画面へ遷移するメソッド.
	 * 
	 * @param model
	 * @return 商品一覧
	 */
	@RequestMapping("/")
	public String findAllShowItemsList(Model model) {
		recordNum = repisitory.recordNum();
		maxPage = recordNum / OUTPUT_NUM;
		model.addAttribute("maxPage", maxPage);
		List<Items> itemsList = service.showItemList();

		model.addAttribute("itemsList", itemsList);

		return "list";
	}

	/**
	 * ページ移動した際のメソッド.
	 * 
	 * @param num1
	 * @param model
	 * @return 商品一覧画面
	 */
	@GetMapping("/next")
	public String turnPage(Integer page, Model model) {
		currentPage += page;

		// 1ページよりも前に戻ろうとすると１ページ目に遷移するようにしているif文
		if (currentPage <= 0) {
			currentPage = 0;
			return "redirect:/ShowItemsList/";
		}
		int offset = OUTPUT_NUM * currentPage;
		

		List<Items> itemsList = service.paging(offset);
		

		// 最後のページ以降に行こうとすると直前のページに戻るようにするif文
		if (itemsList == null) {
			currentPage -= 1;
			offset = OUTPUT_NUM * currentPage;
			itemsList = service.paging(offset);

		}
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("itemsList", itemsList);
		return "list";
	}

	@PostMapping("/selectpage")
	public String selectPage(Integer page, Model model) {
		currentPage = 0;
		currentPage = page - 1;
		int offset = OUTPUT_NUM * currentPage;
		List<Items> itemsList = service.paging(offset);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("itemsList", itemsList);
		return "list";
	}

}
