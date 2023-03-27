package com.example.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 商品編集を行う際に使用するform.
 * 
 * @author 
 *
 */
public class ItemsForm {
		/** ID */
		private Integer id;

		/** 商品名 */
		@NotBlank(message="error:may not be empty")
		private String name;

		/** コンディションID */
		@NotNull(message="error:may not be empty")
		private Integer condition;
		
		/** カテゴリー */
		@NotBlank(message="error:may not be empty")
		private String category;
		
		/** ブランド */
		@NotBlank(message="error:may not be empty")
		private String brand;
		
		/** 価格 */
		@NotNull(message="error:may not be empty")
		
		private double price;
		
		/** 運送 */
//		@NotBlank(message="error:may not be empty")
		private Integer shipping;
		
		/** 説明 */
		@NotBlank(message="error:may not be empty")
		private String description;

		/** Getter&Setter */
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getCondition() {
			return condition;
		}

		public void setCondition(Integer condition) {
			this.condition = condition;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Integer getShipping() {
			return shipping;
		}

		public void setShipping(Integer shipping) {
			this.shipping = shipping;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		/** toString */
		@Override
		public String toString() {
			return "Items [id=" + id + ", name=" + name + ", condition=" + condition + ", category=" + category + ", brand="
					+ brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description + "]";
		}

	}

