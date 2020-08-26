package com.altimetrik.ee.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long foodId;

	private String foodName;

	private Integer foodType;

	private Integer foodCategory;

	private Double price;

	private boolean isAvailable;

	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Integer getFoodType() {
		return foodType;
	}

	public void setFoodType(Integer foodType) {
		this.foodType = foodType;
	}

	public Integer getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(Integer foodCategory) {
		this.foodCategory = foodCategory;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "FoodDataDTO [foodId=" + foodId + ", foodName=" + foodName + ", foodType=" + foodType + ", foodCategory="
				+ foodCategory + ", price=" + price + ", isAvailable=" + isAvailable + "]";
	}

}
