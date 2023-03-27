package com.example.domain;

/**
 * 商品検索をする際のドメイン.
 * @author hongo
 *
 */
public class ItemSearch {

	/** 名前 */
	private String name;
	/** 大カテゴリ */
	private Integer largeCategory;
	/** 中カテゴリ */
	private Integer mediumCategory;
	/** 小カテゴリ */
	private Integer smallCategory;
	/** ブランド */
	private String brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLargeCategory() {
		return largeCategory;
	}

	public void setLargeCategory(Integer largeCategory) {
		this.largeCategory = largeCategory;
	}

	public Integer getMediumCategory() {
		return mediumCategory;
	}

	public void setMediumCategory(Integer mediumCategory) {
		this.mediumCategory = mediumCategory;
	}

	public Integer getSmallCategory() {
		return smallCategory;
	}

	public void setSmallCategory(Integer smallCategory) {
		this.smallCategory = smallCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
