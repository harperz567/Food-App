package com.micro.food_catalog.service;

import com.micro.food_catalog.dto.FoodCatalogPage;
import com.micro.food_catalog.dto.FoodItemDTO;
import com.micro.food_catalog.dto.Restaurant;
import com.micro.food_catalog.entity.FoodItem;
import com.micro.food_catalog.mapper.FoodItemMapper;
import com.micro.food_catalog.repo.FoodItemRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FoodCatalogService {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodCatalogPage fetchFoodCatalogPage(Integer restaurantId) {
        // Food item list
        // Restaurant details
        List<FoodItem> foodItemsList = fetchFoodItemsList(restaurantId);
        Restaurant restaurant = fetchRestaurantdetailsFromRestaurantMS(restaurantId);
        return createFoodCatalogPage(foodItemsList, restaurant);
    }

    private FoodCatalogPage createFoodCatalogPage(List<FoodItem> foodItemsList, Restaurant restaurant) {
        FoodCatalogPage foodCatalogPage = new FoodCatalogPage();
        foodCatalogPage.setFoodItemsList(foodItemsList);
        foodCatalogPage.setRestaurant(restaurant);
        return foodCatalogPage;
    }

    private Restaurant fetchRestaurantdetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANTLISTING/restaurant/fetchById/" + restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemsList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItemSaved = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItemSaved);
    }
}
