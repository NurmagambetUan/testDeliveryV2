package com.example.testDeliveryV2.models.mappers;

import com.example.testDeliveryV2.models.DTO.MealDTO;
import com.example.testDeliveryV2.models.entities.Meal;
import com.example.testDeliveryV2.models.mappers.abs.AbstractModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapper extends AbstractModelMapper<Meal, MealDTO> {

    private ModelMapper modelMapper;


    @Autowired
    public MealMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public MealDTO toDto(Meal meal) {
        return null;
    }

    @Override
    public Meal toEntity(MealDTO mealDTO) {
        return null;
    }

    @Override
    public List<MealDTO> toDtoList(List<Meal> meals) {
        return meals.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> toEntityList(List<MealDTO> mealDtos) {
        return mealDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
