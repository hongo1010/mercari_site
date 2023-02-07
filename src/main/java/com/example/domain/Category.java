package com.example.domain;

/**
 * categoryテーブルのドメイン.
 * @author hongo
 *
 */
public class Category {
	/** id */
	private Integer id;
	/** parentId */
	private Integer parentId;
	/** カテゴリー名 */
	private String categoryName;
	/** 全ての名前 */
	private String nameAll;

	/** Getter&Setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	/** toString */
	@Override
	public String toString() {
		return "Category [id=" + id + ", parentId=" + parentId + ", categoryName=" + categoryName + ", nameAll="
				+ nameAll + "]";
	}
}
