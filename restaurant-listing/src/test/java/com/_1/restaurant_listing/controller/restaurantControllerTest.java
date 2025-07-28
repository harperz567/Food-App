package com._1.restaurant_listing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com._1.restaurant_listing.dto.RestaurantDTO;
import com._1.restaurant_listing.service.RestaurantService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class restaurantControllerTest {

    @InjectMocks
    RestaurantController restaurantController;

    @Mock
    RestaurantService restaurantService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAllRestaurant(){
        // Mock the service behavior
        List<RestaurantDTO> mockRestaurant = Arrays.asList(
            new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
            new RestaurantDTO(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurant);

        // Call the controller method
        ResponseEntity<List<RestaurantDTO>> response = restaurantController.fetchAllRestaurants();
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).findAllRestaurants();
    }

    @Test
    public void testSaveRestaurant(){
        // Mock a restaurant to be saved
        RestaurantDTO mockRestaurant = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        // Mock the service behavior
        when(restaurantService.addRestaurantInDB(mockRestaurant)).thenReturn(mockRestaurant);
        // Call the controller method
        ResponseEntity <RestaurantDTO> response = restaurantController.saveRestaurant(mockRestaurant);
        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());
        // Verify that the service method was called
        verify(restaurantService, times(1)).addRestaurantInDB(mockRestaurant);

    }

    @Test
    public void testFindRestaurantById(){
        // Create a mock restaurant ID
        Integer mockRestaurantId = 1;
        // Mock a restaurant to be saved
        RestaurantDTO mockRestaurant = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        // Mock the service behavior
        when(restaurantService.fetchRestaurantById(mockRestaurantId)).thenReturn(new ResponseEntity<> (mockRestaurant, HttpStatus.OK));
        // Call the controller method
        ResponseEntity <RestaurantDTO> response = restaurantController.findRestaurantById(mockRestaurantId);
        // Verify the response
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), mockRestaurant);
        // Verify that the service method was called
        verify(restaurantService, times(1)).fetchRestaurantById(mockRestaurantId);
    }

}
