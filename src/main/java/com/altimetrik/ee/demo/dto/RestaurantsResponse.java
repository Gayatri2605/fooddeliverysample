package com.altimetrik.ee.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantsResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  
	private List<RestaurantListDTO> restaurantsList;
  
  private long totalRestaurants;

	public List<RestaurantListDTO> getRestaurantsList() {
    return restaurantsList;
  }

	public void setRestaurantsList(List<RestaurantListDTO> restaurantsList) {
    this.restaurantsList = restaurantsList;
  }

  public long getTotalRestaurants() {
    return totalRestaurants;
  }

  public void setTotalRestaurants(long totalRestaurants) {
    this.totalRestaurants = totalRestaurants;
  }

  @Override
  public String toString() {
    return "RestaurantsResponse [restaurantsList=" + restaurantsList + ", totalRestaurants="
        + totalRestaurants + "]";
  }
}
