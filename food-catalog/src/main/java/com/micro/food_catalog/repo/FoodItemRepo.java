package com.micro.food_catalog.repo;

import com.micro.food_catalog.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {

    List<FoodItem> findByRestaurantId(Integer restaurantId);
}
