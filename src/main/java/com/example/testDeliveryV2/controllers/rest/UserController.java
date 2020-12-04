package com.example.testDeliveryV2.controllers.rest;


import com.example.testDeliveryV2.controllers.BaseController;
import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.models.entities.Role;
import com.example.testDeliveryV2.models.entities.User;
import com.example.testDeliveryV2.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Api("Точка входа для распознования")
public class UserController extends BaseController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation("Получение всех пользователей в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Регистрация пользователей")
    public ResponseEntity<?> add(@RequestBody User user) throws ServiceException {
        Role role = new Role();
        role.setId(Role.ROLE_USER);
        user.setRole(role);
        user = userService.save(user);
        return buildResponse(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
        userService.deleteById(id);
        return buildResponse("deleted", HttpStatus.OK);
    }


    @GetMapping("/checkAuthority")
    public ResponseEntity<?> getAuthority(@RequestParam String login) {
        return buildResponse(userService.checkAuthority(login), HttpStatus.OK);
    }

}
