package com.example.testDeliveryV2.controllers.rest;

import com.example.testDeliveryV2.controllers.BaseController;
import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.DTO.MealDTO;
import com.example.testDeliveryV2.models.entities.Meal;
import com.example.testDeliveryV2.services.MealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Meals")
@RequestMapping("/api/meals")
public class MealController extends BaseController {
    private final com.example.testDeliveryV2.services.MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/findAll")
    @ApiOperation("Получение всех категории в грязном виде")
    public ResponseEntity<?> findAll() {
        return buildResponse(mealService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    @ApiOperation("Получение категории по ID")
    public ResponseEntity<?> getId(@ApiParam("ID элемента") @RequestParam Long id) throws ServiceException {
        return buildResponse(mealService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiOperation("Добавление категории")
    public ResponseEntity<?> add(@RequestBody Meal meal) throws ServiceException {
        return buildResponse(mealService.add(meal),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление категории")
    public ResponseEntity<?> delete(@RequestBody Meal meal) throws ServiceException {
        mealService.delete(meal);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    @ApiOperation("Удаление категории по ID")
    public ResponseEntity<?> deleteById(@RequestParam Long id) throws ServiceException {
        mealService.deleteById(id);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation("Обновление категории")
    public ResponseEntity<?> update(@RequestBody MealDTO meal) throws ServiceException {

        return buildResponse(mealService.update(meal), HttpStatus.OK);
    }
}
