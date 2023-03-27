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
import com.example.domain.Items;
import com.example.form.ItemsForm;
import com.example.servise.AddItemService;
import com.example.servise.CategoryService;

@Controller
@RequestMapping("/add")
public class AddController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AddItemService addItemService;
	
	@GetMapping("")
	public String showAddItem(ItemsForm itemsForm, Model model) {
		List<Category> bigCategoryList = categoryService.bigCategoryList();
		model.addAttribute("bigCategoryList", bigCategoryList);	
		return "add";
	}
	
	@PostMapping("/addItem")
	public String addItem(@Validated ItemsForm itemsForm, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return showAddItem(itemsForm,model);
		}
		Items newItem = new Items();
		BeanUtils.copyProperties(itemsForm, newItem);
		addItemService.addItem(itemsForm);
		return "redirect:/list";
	}
	
}
