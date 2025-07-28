package com.micro.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.micro.order.dto.OrderDTO;
import com.micro.order.dto.OrderDTOFromFE;
import com.micro.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class orderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrder_shouldReturnCreatedStatus() {
        // Arrange
        OrderDTOFromFE orderDetails = new OrderDTOFromFE();
        OrderDTO orderSavedInDB = new OrderDTO();
        when(orderService.saveOrderInDb(orderDetails)).thenReturn(orderSavedInDB);

        // Act
        ResponseEntity<OrderDTO> response = orderController.saveOrder(orderDetails);

        // Assert
        verify(orderService, times(1)).saveOrderInDb(orderDetails);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderSavedInDB, response.getBody());
    }
}
