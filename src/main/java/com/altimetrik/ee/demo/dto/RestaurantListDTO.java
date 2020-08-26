package com.altimetrik.ee.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long restaurantId;

	private String restaurantName;

	private List<FoodDataDTO> foodItemsList;

	private boolean isActive;

	private long totalFoodItems;

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<FoodDataDTO> getFoodItemsList() {
		return foodItemsList;
	}

	public void setFoodItemsList(List<FoodDataDTO> foodItemsList) {
		this.foodItemsList = foodItemsList;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getTotalFoodItems() {
		return totalFoodItems;
	}

	public void setTotalFoodItems(long totalFoodItems) {
		this.totalFoodItems = totalFoodItems;
	}

	@Override
	public String toString() {
		return "RestaurantListDTO [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", foodItemsList=" + foodItemsList + ", isActive=" + isActive + ", totalFoodItems=" + totalFoodItems
				+ "]";
	}

}
