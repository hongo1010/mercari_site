package com.example.form;

/**
 * 商品検索を行う際に使用するform.
 * @author hongo
 *
 */
public class ItemSearchForm {
	private String name;
	private Integer bigCategory;
	private Integer mediumCategory;
	private Integer smallCategory;
	private String brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBigCategory() {
		return bigCategory;
	}

	public void setBigCategory(Integer bigCategory) {
		this.bigCategory = bigCategory;
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

	@Override
	public String toString() {
		return "SearchItemForm [name=" + name + ", bigCategory=" + bigCategory + ", mediumCategory=" + mediumCategory
				+ ", smallCategory=" + smallCategory + ", brand=" + brand + "]";
	}

}
