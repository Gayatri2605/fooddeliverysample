package com.altimetrik.ee.demo.dto;

import java.io.Serializable;

public class FoodItemRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long foodId;

	private Integer quantity;

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "FoodItemRequest [foodId=" + foodId + ", quantity=" + quantity + "]";
	}

}
