package com.altimetrik.ee.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "food_data")
public class FoodDataEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "food_name")
  private String foodName;
  
  @Column(name = "food_type")
  private Integer foodType;
  
  @Column(name = "food_category")
  private Integer foodCategory;
  
  @Column(name = "price")
  private Double price;
  
  @Column(name = "is_available")
  private boolean isAvailable;
  
  @Column(name = "restaurant_id")
  private Long restaurantId;
  
  @Column(name = "created_at")
  private Long createdAt;
  
  @Column(name = "created_by")
  private String createdBy;
  
  @Column(name = "updated_at")
  private Long updatedAt;
  
  @Column(name = "updated_by")
  private String updatedBy;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(Long restaurantId) {
    this.restaurantId = restaurantId;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Long updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  @Override
  public String toString() {
		return "FoodDataEntity [id=" + id + ", foodName=" + foodName + ", foodType=" + foodType
        + ", foodCategory=" + foodCategory + ", price=" + price + ", isAvailable=" + isAvailable
        + ", restaurantId=" + restaurantId + ", createdAt=" + createdAt + ", createdBy=" + createdBy
        + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
  }

}
