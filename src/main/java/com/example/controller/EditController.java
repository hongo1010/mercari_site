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
import com.example.servise.CategoryService;
import com.example.servise.EditService;

import jakarta.servlet.http.HttpSession;

/**
 * 商品編集画面を操作するコントローラー.
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/Edit")
public class EditController {
	@Autowired
	private EditService editService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private HttpSession session;

	/**
	 * 商品編集画面を表示するためのメソッド.
	 * 
	 * @param id
	 * @param model
	 * @param itemsForm
	 * @return
	 */
	@GetMapping("")
	public String showEdit(Integer id, Model model, ItemsForm itemsForm) {
		Items item = editService.Editload(id);
		model.addAttribute("item", item);
		List<Category> bigCategoryList = categoryService.bigCategoryList();
		model.addAttribute("bigCategoryList", bigCategoryList);
		return "edit";
	}

	/**
	 * 商品編集画面で編集した商品を更新するためのメソッド.
	 * 
	 * @param itemsForm
	 * @param result
	 * @return
	 */
	@PostMapping("/update")
	public String updateItem(@Validated ItemsForm itemsForm, BindingResult result, Model model,Integer id) {
		if(result.hasErrors()) {
			return showEdit(id,model,itemsForm);
		}
		
		Items item = new Items();
		BeanUtils.copyProperties(itemsForm, item);
		editService.update(item);

		return "detail";
	}

}
