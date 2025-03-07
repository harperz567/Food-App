package com.micro.order.service;

import com.micro.order.dto.OrderDTO;
import com.micro.order.dto.OrderDTOFromFE;
import com.micro.order.dto.UserDTO;
import com.micro.order.entity.Order;
import com.micro.order.mapper.OrderMapper;
import com.micro.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;

    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails){
        Integer newOrderId = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderId, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO);
        orderRepo.save(orderToBeSaved);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId){
        return restTemplate.getForObject("http://USERINFORMATION/user/fetchUserById/" + userId, UserDTO.class);
    }

}
