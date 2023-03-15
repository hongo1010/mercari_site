package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;

/**
 * Categoryの情報を操作するRepository
 * 
 * @author 
 *
 */
@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

	/**
	 * 大カテゴリーを検索するリポジトリ.
	 * 
	 * @return
	 */
	public List<Category> findBigCategory() {
		String sql = "SELECT id,parent,name,name_all FROM category WHERE parent IS NULL AND name_all IS NULL ORDER BY id;";
		List<Category> bigCategoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return bigCategoryList;

	}

	/**
	 * 中カテゴリーを検索するリポジトリ.
	 * 
	 * @param id
	 * @return 中カテゴリーリスト
	 */
	public List<Category> findMiddleCategoryById(Integer id) {
		String sql = "SELECT id, parent, name, name_all FROM category WHERE parent = :id AND name_all IS NULL ORDER BY id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Category> middleCategoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);

		return middleCategoryList;
	}

	/**
	 * 小カテゴリーを検索するリポジトリ.
	 * 
	 * @param id
	 * @return　小カテゴリーリスト
	 */
	public List<Category> findSmallCategoryById(Integer id) {
		String sql = "SELECT id, parent, name, name_all FROM category WHERE parent = :id ORDER BY id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Category> smailCategoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return smailCategoryList;
	}
	
}
