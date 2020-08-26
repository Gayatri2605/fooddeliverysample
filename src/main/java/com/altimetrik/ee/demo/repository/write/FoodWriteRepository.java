package com.altimetrik.ee.demo.repository.write;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.ee.demo.entity.FoodDataEntity;

@Repository
public interface FoodWriteRepository extends JpaRepository<FoodDataEntity, Long> {

}
