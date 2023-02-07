package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Items;
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

	@RequestMapping("/")
	public String findAllShowItemsList(Model model) {

		List<Items> itemsList = service.findAll();
		
		
		model.addAttribute("itemsList", itemsList);
					
		return "list";
	}

}
