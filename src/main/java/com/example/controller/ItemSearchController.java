package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Category;
import com.example.domain.ItemSearch;
import com.example.domain.Items;
import com.example.form.ItemSearchForm;
import com.example.servise.CategoryService;
import com.example.servise.ItemSearchService;

@Controller
@RequestMapping("/search")
public class ItemSearchController {
	
	@Autowired
	private ItemSearchService ItemsearchService;
	
	// １ページの最大表示数
	public static final int OUTPUT_NUM = 100;
	
	// itemsテーブルのレコード総数
	private int recordNum;
	
	// 現在のページ
	private int currentPage = 0;
	
	// 全レコードの件数から算出されるページ数（recordNum/outputNum）
	private int maxPage;
	
	private ItemSearchForm searchLog = null;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("")
	public String searchItem(ItemSearchForm searchItemForm, Model model) {
		//検索フォームの親要素を入れる処理
		List<Category> bigCategoryList = categoryService.bigCategoryList();
		model.addAttribute("bigCategoryList", bigCategoryList);	
		//searchLogの初期化と更新を行っている 初期化は2回目以降検索のログが更新できるように
		searchLog = null;
		searchLog = searchItemForm;
		List<Items> itemList = ItemsearchService.search(OUTPUT_NUM, searchItemForm);
		model.addAttribute("itemList", itemList);
		return "searchItemList";
	}
	
	@GetMapping("/next")
	public String turnPage(Integer num1, Model model, ItemSearchForm searchItemForm) {	
		//検索フォームの親要素を入れる処理
		List<Category> bigCategoryList = categoryService.bigCategoryList();
		model.addAttribute("bigCategoryList", bigCategoryList);	
		
		//こっからがページング処理部分
		currentPage += num1;
		//1ページよりも前に戻ろうとすると１ページ目に遷移するようにしているif文
		if(currentPage <= 0) {
			currentPage = 0;
			return "redirect:/search";
		}
		int num2 = OUTPUT_NUM * currentPage;
		List<Items> itemList = ItemsearchService.turnPage(OUTPUT_NUM, num2, searchLog);
		//最後のページ以降に行こうとすると直前のページに戻るようにするif文
		if(itemList == null) {
			currentPage -= 1;
			num2 = OUTPUT_NUM * currentPage;
			itemList = ItemsearchService.turnPage(OUTPUT_NUM, num2, searchLog);
		}
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("itemList", itemList);
		return "searchItemList";
	}

}
