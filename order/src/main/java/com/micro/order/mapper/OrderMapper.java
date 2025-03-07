package com.micro.order.mapper;
import com.micro.order.dto.OrderDTO;
import com.micro.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order mapOrderDTOToOrder(OrderDTO orderDTO);
    OrderDTO mapOrderToOrderDTO(Order order);
}
