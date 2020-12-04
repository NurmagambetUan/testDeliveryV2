package com.example.testDeliveryV2.services.impl;

import com.example.testDeliveryV2.exceptions.ErrorCode;
import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.entities.Meal;
import com.example.testDeliveryV2.repositories.MealRepository;
import com.example.testDeliveryV2.services.MealService;
import org.springframework.stereotype.Service;
import com.example.testDeliveryV2.models.DTO.MealDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public List<Meal> findAll() {
        return mealRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Meal findById(Long id) throws ServiceException {
        Optional<Meal> mealOptional = Optional.ofNullable(mealRepository.findByIdAndDeletedAtIsNull(id));
        return mealOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("meal not found")
                .build());
    }

    @Override
    public List<Meal> findByCategoryId(Long id) throws ServiceException {
        List<Meal> meals = mealRepository.findAllByCategoryIdAndDeletedAtIsNull(id);
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("category is null")
                    .build();
        }
        return meals;
    }

    @Override
    public Meal update(MealDTO meal) throws ServiceException {
        if(meal.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("meal is null")
                    .build();
        }
        Meal mealEntity = new Meal();
        mealEntity.setId(meal.getId());
        mealEntity.setTitle(meal.getTitle());
        mealEntity.setPrice(meal.getPrice());
        mealEntity.setIngredients(meal.getIngredients());
        mealEntity.setDescription(meal.getDescription());
        mealEntity.setTag(meal.getTag());
        mealEntity.setUrl(meal.getUrl());
        mealEntity.setQuantity(meal.getQuantity());
        mealEntity.setCategory(meal.getCategory());
        return mealRepository.save(mealEntity);
    }

    @Override
    public Meal add(Meal meal) throws ServiceException {
        if(meal.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("meal already exists")
                    .build();
        }
        return  mealRepository.save(meal);
    }

    @Override
    public void delete(Meal meal) throws ServiceException {
        if(meal.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("meal is null")
                    .build();
        }
        meal = findById(meal.getId());
        meal.setDeletedAt(new Date());
        mealRepository.save(meal);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Meal meal = findById(id);
        meal.setDeletedAt(new Date());
        mealRepository.save(meal);
    }
}

