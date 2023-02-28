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

import jakarta.servlet.http.HttpSession;

/**
 * 商品一覧画面の操作を行うControllerクラス.
 * 
 * @author 
 *
 */
@Controller
@RequestMapping("/ShowItemsList")
public class ShowItemsListController {

	@Autowired
	private ShowItemsListService service;

	@Autowired
	private ItemsRepository repisitory;

	@Autowired
	private HttpSession session;
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
		List<Items> itemsList = service.showItemList(0);

		model.addAttribute("maxPage", maxPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("itemsList", itemsList);
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("maxPage", maxPage);

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

		int offset = OUTPUT_NUM * currentPage;

		List<Items> itemsList = service.showItemList(offset);

		model.addAttribute("maxPage", maxPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("itemsList", itemsList);

		session.setAttribute("currentPage", currentPage);

		return "list";
	}

	/**
	 * ページ番号を指定して表示するフォームを操作するメソッド.
	 * 
	 * @param page
	 * @param model
	 * @return 賞品一覧画面
	 */
	@PostMapping("/selectpage")
	public String selectPage(Integer page, Model model) {

		// もし入力したページ数が0以下、もしくは最大ページ数より大きかった場合、ページを１に設定し表示する。
		if (page < 0 || page > maxPage || page == null) {
			page = 0;
		}

		currentPage = 0;
		currentPage = page;
		int offset = OUTPUT_NUM * currentPage;
		List<Items> itemsList = service.showItemList(offset);

		model.addAttribute("maxPage", maxPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("itemsList", itemsList);
		session.setAttribute("currentPage", currentPage);
		return "list";
	}

	/**
	 * 商品詳細画面からBackした際のメソッド.
	 * 
	 * @param num1
	 * @param model
	 * @return 商品一覧画面
	 */
	@GetMapping("/Back")
	public String backPage(Model model) {
		session.getAttribute("currentPage");

		int offset = OUTPUT_NUM * currentPage;

		List<Items> itemsList = service.showItemList(offset);

		model.addAttribute("maxPage", maxPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("itemsList", itemsList);
		session.setAttribute("currentPage", currentPage);
		
		return "list";
	}
}
