package com.micro.food_catalog.dto;

import com.micro.food_catalog.entity.FoodItem;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCatalogPage {

    private List<FoodItem> foodItemsList;
    private Restaurant restaurant;
}
