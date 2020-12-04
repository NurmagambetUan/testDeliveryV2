package com.example.testDeliveryV2.services;

import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.entities.Category;


import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id) throws ServiceException ;
    Category update(Category category) throws ServiceException;
    Category add(Category category) throws ServiceException;
    void delete(Category category) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
}
