package com.example.domain;

/**
 * categoryテーブルのドメイン.
 * @author 
 *
 */
public class Category {
	/** id */
	private Integer id;
	/** parentId */
	private Integer parent;
	/** カテゴリー名 */
	private String name;
	/** 全ての名前 */
	private String name_all;

	/** Getter&Setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_all() {
		return name_all;
	}

	public void setName_all(String name_all) {
		this.name_all = name_all;
	}

	/** toString */
	@Override
	public String toString() {
		return "Category [id=" + id + ", parent=" + parent + ", name=" + name + ", name_all="
				+ name_all + "]";
	}
}
