package com.example.testDeliveryV2.models.mappers;

import com.example.testDeliveryV2.models.DTO.OrderDTO;
import com.example.testDeliveryV2.models.entities.Order;
import com.example.testDeliveryV2.models.mappers.abs.AbstractModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.mappers.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderMapper extends AbstractModelMapper<Order, OrderDTO> {

    private final ModelMapper modelMapper;
    private final MealMapper mealMapper;

    @Override
    public OrderDTO toDto(Order order) {
        return null;
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public List<OrderDTO> toDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> toEntityList(List<OrderDTO> orderDTOs) {
        return orderDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
