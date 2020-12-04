package com.example.testDeliveryV2.services;

import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.DTO.OrderDTO;
import com.example.testDeliveryV2.models.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    List<Order> findAllByUserId(Long id);
    Order findById(Long id) throws ServiceException;
    Order update(OrderDTO order) throws ServiceException;
    Order add(OrderDTO order) throws ServiceException;
    void delete(Order order) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
    Double countOverallPrice(Long id) throws ServiceException;
    boolean payment(Long id) throws ServiceException;
    boolean confirm(Long id) throws ServiceException;
    String complete(Long id) throws ServiceException;

}
