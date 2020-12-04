package com.example.testDeliveryV2.services;

import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.entities.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id) throws ServiceException;
    List<Role> findAll();
    List<Role> findAllWithDeleted();
    Role update(Role role) throws ServiceException ;
    Role add(Role role) throws ServiceException;
    void delete(Role role) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}

