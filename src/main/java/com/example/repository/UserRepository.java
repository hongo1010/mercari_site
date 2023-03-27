package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * @author hongo
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザーオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

	
	
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(name, password, authority) VALUES(:name, :password, :authority);";
		template.update(sql, param);
	}
	
	/**
	 * ユーザーを1件検索します.
	 * 
	 * @param mailAddress メールアドレス
	 * @return ユーザー
	 */
	public User findByMail(String name) {
		String sql = "SELECT id, name, password, authority FROM users WHERE name=:name;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}

}
