package com._1.restaurant_listing.mapper;

import com._1.restaurant_listing.dto.RestaurantDTO;
import com._1.restaurant_listing.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    RestaurantDTO mapRestaurantToRestaurantDTO (Restaurant restaurant);
    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
}
