package com._1.restaurant_listing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class RestaurantDTO {

    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
