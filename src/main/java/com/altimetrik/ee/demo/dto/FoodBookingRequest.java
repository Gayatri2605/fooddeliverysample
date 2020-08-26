package com.altimetrik.ee.demo.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FoodBookingRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long restaurantId;

	@Valid
	@NotEmpty
	private List<FoodItemRequest> foodId;

	@NotNull
	private String userId;

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public List<FoodItemRequest> getFoodId() {
		return foodId;
	}

	public void setFoodId(List<FoodItemRequest> foodId) {
		this.foodId = foodId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "FoodBookingRequest [restaurantId=" + restaurantId + ", foodId=" + foodId + ", userId=" + userId + "]";
	}

}
