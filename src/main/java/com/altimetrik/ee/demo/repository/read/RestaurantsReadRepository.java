package com.altimetrik.ee.demo.repository.read;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.ee.demo.entity.RestaurantListEntity;

@Repository
public interface RestaurantsReadRepository extends JpaRepository<RestaurantListEntity, Long> {

	Page<RestaurantListEntity> findByAddress(String destination, Pageable pageable);

	Optional<RestaurantListEntity> findByIdAndIsActive(Long restaurantId, boolean b);

}
