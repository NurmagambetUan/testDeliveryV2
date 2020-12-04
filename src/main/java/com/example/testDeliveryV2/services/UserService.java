package com.example.testDeliveryV2.services;


import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.entities.User;

import java.util.List;

public interface UserService {
    User findById(Long id) throws ServiceException;
    List<User> findAll();
    List<User> findAllWithDeleted();
    User update(User user) throws ServiceException ;
    void delete(User user) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
    User save(User user) throws ServiceException;
    String checkAuthority(String login);
}
