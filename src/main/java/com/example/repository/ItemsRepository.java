package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Items;
import com.example.domain.RecordNum;

/**
 * @author hongo itemsテーブルの操作を行うリポジトリ.
 *
 */
@Repository
public class ItemsRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	// ItemsクラスのRowMapper
	private static final RowMapper<Items> ITEMS_ROW_MAPPER = (rs, i) -> {

		Items items = new Items();
		
		items.setId(rs.getInt("i_id"));
		items.setName(rs.getString("i_name"));
		items.setCondition(rs.getInt("i_condition"));
		items.setCategory(rs.getString("c_name_all"));
		items.setBrand(rs.getString("i_brand"));
		items.setPrice(rs.getDouble("i_price"));
		return items;
	};

	
	// RecordNumクラスのRowMapper
	private static final RowMapper<RecordNum> RECORDNUM_ROW_MAPPER = new BeanPropertyRowMapper<>(RecordNum.class);

	
	//商品詳細情報を取ってくる際に必要なItemsクラスのRowMapper
	private static final RowMapper<Items> DETAILITEMS_ROW_MAPPER = new BeanPropertyRowMapper<>(Items.class);

	
	/**
	 *一覧画面用に必要な情報をテーブル結合して100件取ってくるメソッド.
	 * 
	 * @param offset
	 * @return
	 */
	public List<Items> paging(Integer offset) {
		String sql = "SELECT i.id as i_id ,i.name as i_name,i.condition as i_condition,i.price as i_price, c.name_all as c_name_all, i.brand as i_brand FROM Items as i INNER JOIN category AS c ON i.category = c.id ORDER BY category LIMIT 100 OFFSET :offset ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("offset", offset);
		List<Items> itemsList = template.query(sql, param, ITEMS_ROW_MAPPER);

		return itemsList;

	}

	/**
	 * 商品一覧の商品の総数を取ってくるメソッド.
	 * 
	 * @return 商品の総数
	 */
	public Integer recordNum() {
		String sql = "SELECT COUNT(category) AS record_num FROM items;";
		List<RecordNum> recordNumlist = template.query(sql, RECORDNUM_ROW_MAPPER);
		return recordNumlist.get(0).getRecordNum();
	}

	
	
	/**
	 * 商品の詳細情報を取得するためのメソッド.
	 * @param id
	 * @return 商品情報
	 */
	public Items load(Integer id) {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items i INNER JOIN category c ON i.category = c.id WHERE i.id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Items item = template.queryForObject(sql, param, DETAILITEMS_ROW_MAPPER);
		return item;
	}
	
}
