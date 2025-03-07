package com.micro.food_catalog.mapper;

import com.micro.food_catalog.dto.FoodItemDTO;
import com.micro.food_catalog.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {

    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);
    FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);
    FoodItemDTO mapFoodItemToFoodItemDto(FoodItem foodItem);
}
