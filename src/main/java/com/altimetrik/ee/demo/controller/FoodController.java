package com.altimetrik.ee.demo.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.ee.demo.constants.FoodConstants;
import com.altimetrik.ee.demo.dto.FoodBookingRequest;
import com.altimetrik.ee.demo.dto.RestaurantsResponse;
import com.altimetrik.ee.demo.response.ResponseObject;
import com.altimetrik.ee.demo.service.FoodService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping
public class FoodController {

  @Autowired
	private FoodService foodService;

	@ApiOperation(value = "This API will returen restaurent list")
	@GetMapping(value = FoodConstants.VERSION_1 + FoodConstants.RESTAURANTS,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseObject<RestaurantsResponse>> getAllRestaurants(
      @Valid @RequestParam(name = "destination") @NotBlank String destination, Pageable pageable) {
    return ResponseEntity
				.ok(ResponseObject.success(foodService.getAllRestaurants(destination, pageable)));
  }

	@ApiOperation(value = "This API will order will be placed")
	@PostMapping(value = FoodConstants.VERSION_1 + FoodConstants.BOOKING,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseObject<Map<String, Boolean>>> createBooking(
      @Valid @RequestBody FoodBookingRequest foodBookingRequest) {
    return ResponseEntity
				.ok(ResponseObject.success(foodService.createBooking(foodBookingRequest)));
  }

	@ApiOperation(value = "This API will cancel order will be placed")
	@DeleteMapping(value = FoodConstants.VERSION_1 + FoodConstants.BOOKING,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseObject<Map<String, Boolean>>> cancelBooking(
      @Valid @RequestParam(name = "booking_id") @NotBlank String bookingId) {
		return ResponseEntity.ok(ResponseObject.success(foodService.cancelBooking(bookingId)));
  }

}
