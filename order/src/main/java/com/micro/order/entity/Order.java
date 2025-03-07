package com.micro.order.entity;

import com.micro.order.dto.FoodItemsDTO;
import com.micro.order.dto.Restaurant;
import com.micro.order.dto.UserDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("order")
public class Order {
    private Integer orderId;
    private List<FoodItemsDTO> foodItemsList;
    private Restaurant restaurant;
    private UserDTO userDTO;
}
