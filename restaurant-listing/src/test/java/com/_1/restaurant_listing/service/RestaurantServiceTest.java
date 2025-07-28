package com._1.restaurant_listing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com._1.restaurant_listing.dto.RestaurantDTO;
import com._1.restaurant_listing.entity.Restaurant;
import com._1.restaurant_listing.mapper.RestaurantMapper;
import com._1.restaurant_listing.repo.RestaurantRepo;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RestaurantServiceTest {
    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepo restaurantRepo;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRestaurants(){
        // Mock the service behavior
        List<Restaurant> mockRestaurants = Arrays.asList(
            new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
            new Restaurant(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantRepo.findAll()).thenReturn(mockRestaurants);
        // Call the controller method
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurants();
        // Verify the response
        assertEquals(mockRestaurants.size(), restaurantDTOList.size());
        for (int i = 0; i < mockRestaurants.size(); i++){
            RestaurantDTO expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(mockRestaurants.get(i));
            assertEquals(expectedDTO, restaurantDTOList.get(i));
        }
        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).findAll();
    }
    @Test
    public void testAddRestaurantInDB(){
        RestaurantDTO mockRestaurantDTO = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        Restaurant mockRestaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(mockRestaurantDTO);
        when(restaurantRepo.save(mockRestaurant)).thenReturn(mockRestaurant);
        RestaurantDTO savedRestaurantDTO = restaurantService.addRestaurantInDB(mockRestaurantDTO);
        assertEquals(savedRestaurantDTO, mockRestaurantDTO);
        verify(restaurantRepo, times(1)).save(mockRestaurant);
    }


}
