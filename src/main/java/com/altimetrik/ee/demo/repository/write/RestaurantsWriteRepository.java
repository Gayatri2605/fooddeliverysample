package com.altimetrik.ee.demo.repository.write;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.ee.demo.entity.RestaurantListEntity;

@Repository
public interface RestaurantsWriteRepository extends JpaRepository<RestaurantListEntity, Long> {

}
