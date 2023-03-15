package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Items;
import com.example.servise.ShowItemDetailService;

/**
 * 商品詳細画面を操作するためのControllerクラス.
 * 
 * @author 
 *
 */
@Controller
@RequestMapping("/ShowItemDetail")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService Service;

	/**
	 * 商品詳細画面を表示するためのメソッド.
	 * 
	 * @param id
	 * @param model
	 * @return 商品詳細画面
	 */
	@GetMapping("")
	public String showItemDetail(Integer id, Model model) {
		Items item = Service.showItemsDetail(id);
		model.addAttribute("item", item);
		return "detail";
		
	}
	
	
	

}
