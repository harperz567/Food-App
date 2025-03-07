package com.micro.order.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTO {

    private Integer orderId;
    private Restaurant restaurant;
    private List<FoodItemsDTO> foodItemsList;
    private UserDTO userDTO;
}
