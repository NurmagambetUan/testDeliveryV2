package com.example.testDeliveryV2.controllers.rest;

import com.example.testDeliveryV2.controllers.BaseController;
import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.entities.Category;
import com.example.testDeliveryV2.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "Categories")
@RequestMapping("/api/categories")
public class CategoryController extends BaseController {
    private final com.example.testDeliveryV2.services.CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/findAll")
    @ApiOperation("Получение всех категории в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    @ApiOperation("Получение категории по ID")
    public ResponseEntity<?> getId(@ApiParam("ID элемента") @RequestParam Long id) throws ServiceException {
        return buildResponse(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiOperation("Добавление категории")
    public ResponseEntity<?> add(@RequestBody Category category) throws ServiceException {
        return buildResponse(categoryService.add(category),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление категории")
    public ResponseEntity<?> delete(@RequestBody Category category) throws ServiceException {
        categoryService.delete(category);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    @ApiOperation("Удаление категории по ID")
    public ResponseEntity<?> deleteById(@RequestParam Long id) throws ServiceException {
        categoryService.deleteById(id);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation("Обновление категории")
    public ResponseEntity<?> update(@RequestBody Category category) throws ServiceException {

        return buildResponse(categoryService.update(category), HttpStatus.OK);
    }
}



