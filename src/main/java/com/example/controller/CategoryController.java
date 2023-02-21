package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Category;
import com.example.servise.CategoryService;


@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * 大カテゴリーのIDから中カテゴリーを検索するメソッド.
	 * 
	 * @param id
	 * @param model
	 * @return 中カテゴリー検索結果
	 */
	@ResponseBody
	@PostMapping("/middleCategory")
	public List<Category> middleCategoryList(Integer id, Model model) {
		List<Category> middleCategoryList = categoryService.middleCategoryList(id);
		System.out.println(middleCategoryList);
		return middleCategoryList;
	}

	/**
	 * 中カテゴリーのIDから小カテゴリーを検索するメソッド.
	 * 
	 * @param id
	 * @return　検索された小カテゴリーリスト
	 */
	
	@ResponseBody
	@PostMapping("/smallcategory")
	public List<Category> smallCategoryList(Integer id) {
		List<Category> smallCategoryList = categoryService.smallCategoryList(id);
		return smallCategoryList;
	}
}
