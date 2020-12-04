package com.example.testDeliveryV2.services.impl;

import com.example.testDeliveryV2.exceptions.ErrorCode;
import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.entities.User;
import com.example.testDeliveryV2.proxy.Authority;
import com.example.testDeliveryV2.proxy.ProxyAuthority;
import com.example.testDeliveryV2.repositories.UserRepository;
import com.example.testDeliveryV2.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("user not found")
                .build());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByDeletedAtIsNull();
    }



    @Override
    public List<User> findAllWithDeleted() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) throws ServiceException {
        if(user.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        return  userRepository.save(user);
    }

    @Override
    public User save(User user) throws ServiceException {
        if(user.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("user already exists")
                    .build();
        }
        return  userRepository.save(user);
    }

    @Override
    public void delete(User user) throws ServiceException {
        if(user.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        user = findById(user.getId());
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        User user = findById(id);
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    public String checkAuthority(String login){
        Authority authority = new ProxyAuthority();
        try {
            return authority.connectTo(login);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }


}
