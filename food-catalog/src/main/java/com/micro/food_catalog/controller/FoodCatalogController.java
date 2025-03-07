package com.micro.food_catalog.controller;

import com.micro.food_catalog.dto.FoodCatalogPage;
import com.micro.food_catalog.dto.FoodItemDTO;
import com.micro.food_catalog.entity.FoodItem;
import com.micro.food_catalog.service.FoodCatalogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodCatalog")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200",
    allowedHeaders = "*",
    allowCredentials = "true",
    methods = {RequestMethod.GET, RequestMethod.POST})

public class FoodCatalogController {
    @Autowired
    FoodCatalogService foodCatalogService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO foodItemSaved = foodCatalogService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCatalogPage> fetchRestaurantDetailsWithFoodMenu(@PathVariable Integer restaurantId){
        FoodCatalogPage foodCatalogPage = foodCatalogService.fetchFoodCatalogPage(restaurantId);
        return new ResponseEntity<>(foodCatalogPage, HttpStatus.OK);
    }

    public static Logger getLog() {
        return log;
    }
}
