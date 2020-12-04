package com.example.testDeliveryV2.services;

import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.DTO.MealDTO;
import com.example.testDeliveryV2.models.entities.Meal;

import java.util.List;

public interface MealService {
    List<Meal> findAll();
    Meal findById(Long id) throws ServiceException;
    Meal update(MealDTO meal) throws ServiceException;
    Meal add(Meal meal) throws ServiceException;
    void delete(Meal meal) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
    List<Meal> findByCategoryId(Long id) throws ServiceException;
}
