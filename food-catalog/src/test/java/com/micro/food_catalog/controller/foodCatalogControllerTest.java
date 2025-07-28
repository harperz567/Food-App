package com.micro.food_catalog.controller;

import com.micro.food_catalog.dto.FoodCatalogPage;
import com.micro.food_catalog.dto.FoodItemDTO;
import com.micro.food_catalog.service.FoodCatalogService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class foodCatalogControllerTest {
    @Mock
    private FoodCatalogService foodCatalogService;

    @InjectMocks
    private FoodCatalogController foodCatalogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addFoodItem_ShouldReturnCreatedStatus() {
        // Arrange
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        when(foodCatalogService.addFoodItem(any(FoodItemDTO.class))).thenReturn(foodItemDTO);

        // Act
        ResponseEntity<FoodItemDTO> response = foodCatalogController.addFoodItem(foodItemDTO);

        // Assert
        verify(foodCatalogService, times(1)).addFoodItem(foodItemDTO);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() == foodItemDTO;
    }

    @Test
    void fetchRestauDetailsWithFoodMenu_ShouldReturnOkStatus() {
        // Arrange
        int restaurantId = 123;
        FoodCatalogPage foodCatalogPage = new FoodCatalogPage();
        when(foodCatalogService.fetchFoodCatalogPage(restaurantId)).thenReturn(foodCatalogPage);

        // Act
        ResponseEntity<FoodCatalogPage> response = foodCatalogController.fetchRestaurantDetailsWithFoodMenu(restaurantId);

        // Assert
        verify(foodCatalogService, times(1)).fetchFoodCatalogPage(restaurantId);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == foodCatalogPage;
    }
}
