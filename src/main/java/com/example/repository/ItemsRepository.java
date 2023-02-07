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
import com.example.domain.Items;
import com.example.domain.RecordNum;

/**
 * @author hongo
 * itemsテーブルの操作を行うリポジトリ.
 *
 */
@Repository
public class ItemsRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	//ItemsクラスのRowMapper
	private static final RowMapper<Items>ITEMS_ROW_MAPPER = (rs,i) ->{
		
		Items items = new Items();
		
		items.setName(rs.getString("i_name"));
		items.setCondition(rs.getInt("i_condition"));
		items.setCategory(rs.getString("c_name_all"));
		items.setBrand(rs.getString("i_brand"));
		items.setPrice(rs.getDouble("i_price"));
		items.setCount(rs.getInt("record_count"));
		return items;
		
	};
	//RecordNumクラスのRowMapper
	private static final RowMapper<RecordNum> RECORDNUM_ROW_MAPPER = new BeanPropertyRowMapper<>(RecordNum.class);
	
	//CategoryクラスのRowMapper
	private static final RowMapper<Category>CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);	
	
	/**
	 * 一覧画面用に必要な情報をテーブル結合して全件取ってくるメソッド.
	 * 
	 * @return itemsList 商品リスト
	 */
	public List<Items> findAll(){
		String sql ="SELECT i.name as i_name,i.condition as i_condition,i.price as i_price, c.name_all as c_name_all, i.brand as i_brand ,(SELECT COUNT(*) as record_count FROM Items)FROM Items as i INNER JOIN category AS c ON i.category = c.id ORDER BY category LIMIT 30";
		List<Items> itemsList = template.query(sql,ITEMS_ROW_MAPPER);
		
		return itemsList;
		
	}
	public List<Items> paging(Integer offset){
		String sql ="SELECT i.name as i_name,i.condition as i_condition,i.price as i_price, c.name_all as c_name_all, i.brand as i_brand ,(SELECT COUNT(*) as record_count FROM Items)FROM Items as i INNER JOIN category AS c ON i.category = c.id c.id ORDER BY category LIMIT30 OFFSET :offset ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("offset", offset);
		List<Items> itemsList = template.query(sql, param,ITEMS_ROW_MAPPER);
		
		return itemsList;
		
	}
	
	public Integer recordNum() {
		String sql = "SELECT COUNT(id) AS record_num FROM items;";
		List<RecordNum> recordNumlist = template.query(sql, RECORDNUM_ROW_MAPPER);
		return recordNumlist.get(0).getRecordNum();
	}	
	
	
}
