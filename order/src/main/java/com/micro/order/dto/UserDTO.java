package com.micro.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int userId;
    private String Username;
    private String UserPassword;
    private String address;
    private String city;
}