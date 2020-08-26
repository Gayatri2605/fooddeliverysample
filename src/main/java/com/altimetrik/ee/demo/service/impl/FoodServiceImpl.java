package com.altimetrik.ee.demo.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.altimetrik.ee.demo.constants.FoodConstants;
import com.altimetrik.ee.demo.dto.FoodBookingRequest;
import com.altimetrik.ee.demo.dto.FoodDataDTO;
import com.altimetrik.ee.demo.dto.FoodItemRequest;
import com.altimetrik.ee.demo.dto.RestaurantListDTO;
import com.altimetrik.ee.demo.dto.RestaurantsResponse;
import com.altimetrik.ee.demo.entity.FoodDataEntity;
import com.altimetrik.ee.demo.entity.RestaurantListEntity;
import com.altimetrik.ee.demo.repository.read.FoodReadRepository;
import com.altimetrik.ee.demo.repository.read.RestaurantsReadRepository;
import com.altimetrik.ee.demo.repository.write.FoodWriteRepository;
import com.altimetrik.ee.demo.repository.write.RestaurantsWriteRepository;
import com.altimetrik.ee.demo.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private RestaurantsReadRepository restaurantsReadRepository;

	@Autowired
	private RestaurantsWriteRepository restaurantsWriteRepository;

	@Autowired
	private FoodReadRepository foodReadRepository;

	@Autowired
	private FoodWriteRepository foodWriteRepository;

	@PostConstruct
	private void populateDataInDB() {
		List<String> inputList = readInputFileLines();
		List<RestaurantListEntity> restaurantsEntities = new ArrayList<>();
		List<FoodDataEntity> foodEntities = new ArrayList<>();
		if (!inputList.isEmpty()) {
			inputList.stream().forEach(line -> {
				String[] lineSplit = line.split(";");
				String[] restaurantDetails = lineSplit[0].split(",");
				RestaurantListEntity restaurantsEntity = new RestaurantListEntity();
				restaurantsEntity.setActive(Boolean.parseBoolean(restaurantDetails[4]));
				restaurantsEntity.setAddress(restaurantDetails[3]);
				restaurantsEntity.setContactNumber(Long.parseLong(restaurantDetails[2]));
				restaurantsEntity.setCreatedAt(System.currentTimeMillis());
				restaurantsEntity.setCreatedBy("Admin");
				restaurantsEntity.setRating(Double.parseDouble(restaurantDetails[5]));
				restaurantsEntity.setRestaurantName(restaurantDetails[1]);
				restaurantsEntity.setUpdatedAt(System.currentTimeMillis());
				restaurantsEntity.setUpdatedBy("Admin");
				restaurantsEntity.setId(Long.parseLong(restaurantDetails[0]));
				restaurantsEntities.add(restaurantsEntity);
				String[] foodDetails = lineSplit[1].split("-");
				for (String foodItem : foodDetails) {
					String[] foods = foodItem.split(",");
					FoodDataEntity entity = new FoodDataEntity();
					entity.setAvailable(Boolean.parseBoolean(foods[4]));
					entity.setCreatedAt(System.currentTimeMillis());
					entity.setCreatedBy("Admin");
					entity.setFoodCategory(Integer.parseInt(foods[2]));
					entity.setFoodName(foods[0]);
					entity.setFoodType(Integer.parseInt(foods[1]));
					entity.setPrice(Double.parseDouble(foods[3]));
					entity.setRestaurantId(restaurantsEntity.getId());
					entity.setUpdatedAt(System.currentTimeMillis());
					entity.setUpdatedBy("Admin");
					foodEntities.add(entity);
				}
			});
			restaurantsWriteRepository.saveAll(restaurantsEntities);
			foodWriteRepository.saveAll(foodEntities);
		}
	}

	private static List<String> readInputFileLines() {
		String fileName = "restaurants.txt";
		List<String> inputList = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(new File(fileName).getAbsolutePath()))) {
			inputList = stream.filter(line -> (line != null && !line.isEmpty())).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputList;
	}

	@Override
	public RestaurantsResponse getAllRestaurants(String destination, Pageable pageable) {
		RestaurantsResponse response = new RestaurantsResponse();
		Page<RestaurantListEntity> restaurantsEntities = restaurantsReadRepository.findByAddress(destination, pageable);
		List<RestaurantListDTO> restaurantsDTOList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(restaurantsEntities.getContent())) {
			response.setTotalRestaurants(restaurantsEntities.getTotalElements());
			List<Long> restaurantIdList = restaurantsEntities.getContent().stream().map(RestaurantListEntity::getId)
					.collect(Collectors.toList());
			List<FoodDataEntity> foodList = foodReadRepository.findByRestaurantIdIn(restaurantIdList);
			AtomicReference<Map<Long, List<FoodDataEntity>>> restaurantFoodListMap = new AtomicReference<>(
					new HashMap<>());
			if (!foodList.isEmpty()) {
				restaurantFoodListMap
						.set(foodList.stream().collect(Collectors.groupingBy(FoodDataEntity::getRestaurantId)));
			}
			restaurantsEntities.getContent().stream().forEach(action -> {
				RestaurantListDTO restaurantsDTO = new RestaurantListDTO();
				List<FoodDataEntity> foodItemsList = restaurantFoodListMap.get().getOrDefault(action.getId(),
						new ArrayList<>());
				List<FoodDataDTO> foodDTOList = new ArrayList<>();
				if (!foodItemsList.isEmpty()) {
					foodItemsList.stream().forEach(foodItem -> {
						FoodDataDTO foodDTO = new FoodDataDTO();
						BeanUtils.copyProperties(foodItem, foodDTO);
						foodDTOList.add(foodDTO);
					});
				}
				restaurantsDTO.setActive(action.isActive());
				restaurantsDTO.setFoodItemsList(foodDTOList);
				restaurantsDTO.setRestaurantId(action.getId());
				restaurantsDTO.setRestaurantName(action.getRestaurantName());
				restaurantsDTO.setTotalFoodItems(foodDTOList.size());
				restaurantsDTOList.add(restaurantsDTO);
			});
			response.setRestaurantsList(restaurantsDTOList);
		}
		return response;
	}

//Just validated the data , unable to do the order process
	@Override
	public Map<String, Boolean> createBooking(FoodBookingRequest foodBookingRequest) {
		Optional<RestaurantListEntity> restaurantEntity = restaurantsReadRepository
				.findByIdAndIsActive(foodBookingRequest.getRestaurantId(), true);
		List<Long> foodIdList = foodBookingRequest.getFoodId().stream().map(FoodItemRequest::getFoodId)
				.collect(Collectors.toList());
		List<FoodDataEntity> foodEnityList = foodReadRepository.findByIdInAndAndRestaurantIdAndIsAvailable(foodIdList,
				foodBookingRequest.getRestaurantId(), true);
		Map<String, Boolean> responseMap = new HashMap<>();
		responseMap.put(FoodConstants.BOOKING_SUCCESS, true);
		return responseMap;
	}

	@Override
	public Map<String, Boolean> cancelBooking(String bookingId) {
		Map<String, Boolean> responseMap = new HashMap<>();
		responseMap.put(FoodConstants.BOOKING_CANCELLED, true);
		return responseMap;
	}

}
