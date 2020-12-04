package com.example.testDeliveryV2.controllers.rest;

import com.example.testDeliveryV2.controllers.BaseController;
import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.entities.Role;
import com.example.testDeliveryV2.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Roles")
@RequestMapping("/api/roles")
public class RoleController extends BaseController {
    private final com.example.testDeliveryV2.services.RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/findAll")
    @ApiOperation("Получение всех категории в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    @ApiOperation("Получение категории по ID")
    public ResponseEntity<?> getId(@ApiParam("ID элемента") @RequestParam Long id) throws ServiceException {
        return buildResponse(roleService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiOperation("Добавление категории")
    public ResponseEntity<?> add(@RequestBody Role role) throws ServiceException {
        return buildResponse(roleService.add(role),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление категории")
    public ResponseEntity<?> delete(@RequestBody Role role) throws ServiceException {
        roleService.delete(role);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    @ApiOperation("Удаление категории по ID")
    public ResponseEntity<?> deleteById(@RequestParam Long id) throws ServiceException {
        roleService.deleteById(id);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation("Обновление категории")
    public ResponseEntity<?> update(@RequestBody Role role) throws ServiceException {

        return buildResponse(roleService.update(role), HttpStatus.OK);
    }
}
