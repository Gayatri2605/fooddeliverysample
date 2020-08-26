package com.altimetrik.ee.demo.repository.read;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.ee.demo.entity.FoodDataEntity;

@Repository
public interface FoodReadRepository extends JpaRepository<FoodDataEntity, Long> {

	List<FoodDataEntity> findByRestaurantIdIn(List<Long> restaurantIdList);

	List<FoodDataEntity> findByIdInAndAndRestaurantIdAndIsAvailable(List<Long> foodIdList,
      Long restaurantId, boolean isAvailable);

}
