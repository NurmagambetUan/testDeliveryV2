package com.example.testDeliveryV2.controllers.rest;


import com.example.testDeliveryV2.controllers.BaseController;
import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.DTO.OrderDTO;
import com.example.testDeliveryV2.models.entities.Meal;
import com.example.testDeliveryV2.models.entities.Order;
import com.example.testDeliveryV2.services.MealService;
import com.example.testDeliveryV2.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Orders")
@RequestMapping("/api/orders")
public class OrderController extends BaseController {
    private final com.example.testDeliveryV2.services.OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/findAll")
    @ApiOperation("Получение всех категории в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    @ApiOperation("Получение категории по ID")
    public ResponseEntity<?> getId(@ApiParam("ID элемента") @RequestParam Long id) throws ServiceException {
        return buildResponse(orderService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiOperation("Добавление категории")
    public ResponseEntity<?> add(@RequestBody OrderDTO order) throws ServiceException {
        return buildResponse(orderService.add(order),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление категории")
    public ResponseEntity<?> delete(@RequestBody Order order) throws ServiceException {
        orderService.delete(order);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    @ApiOperation("Удаление категории по ID")
    public ResponseEntity<?> deleteById(@RequestParam Long id) throws ServiceException {
        orderService.deleteById(id);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation("Обновление категории")
    public ResponseEntity<?> update(@RequestBody OrderDTO order) throws ServiceException {

        return buildResponse(orderService.update(order), HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<?> getPriceById(@RequestParam Long id) throws ServiceException {
        return buildResponse(orderService.countOverallPrice(id), HttpStatus.OK);
    }

    @GetMapping("/payment")
    public ResponseEntity<?> payment(@RequestParam Long id) throws ServiceException {
        return buildResponse(orderService.payment(id), HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmation(@RequestParam Long id) throws ServiceException {
        return buildResponse(orderService.confirm(id), HttpStatus.OK);
    }

    @GetMapping("/complete")
    public ResponseEntity<?> complete(@RequestParam Long id) throws ServiceException {
        return buildResponse(orderService.complete(id), HttpStatus.OK);
    }
}
