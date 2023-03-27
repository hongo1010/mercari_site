package com.example.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.repository.CategoryRepository;


/**
 * 各カテゴリーの検索を行うServiceクラス.
 * @author hongo
 *
 */
@Service
@Transactional

public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 大カテゴリーを検索するメソッド.
	 * 
	 * @return 大カテゴリーを検索した検索結果
	 */
	public List<Category> bigCategoryList() {
		return categoryRepository.findBigCategory();
	}

	/**
	 * 中カテゴリーを検索するメソッド.
	 * 
	 * @param id
	 * @return 中カテゴリーを検索した検索結果
	 */
	public List<Category> middleCategoryList(Integer id) {
		return categoryRepository.findMiddleCategoryById(id);
	}

	/**
	 * 小カテゴリーを検索するメソッド.
	 * 
	 * @param id
	 * @return 小カテゴリーを検索した検索結果
	 */
	public List<Category> smallCategoryList(Integer id) {
		return categoryRepository.findSmallCategoryById(id);
	}

}
