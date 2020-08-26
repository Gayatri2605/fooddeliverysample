package com.altimetrik.ee.demo.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.altimetrik.ee.demo.dto.FoodBookingRequest;
import com.altimetrik.ee.demo.dto.RestaurantsResponse;

public interface FoodService {

  RestaurantsResponse getAllRestaurants(String destination, Pageable pageable);

  Map<String, Boolean> createBooking(FoodBookingRequest foodBookingRequest);

  Map<String, Boolean> cancelBooking(String bookingId);

}
