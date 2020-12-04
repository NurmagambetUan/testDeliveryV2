//package com.example.testDeliveryV2.controllers.error;
//
//import com.example.testDeliveryV2.controllers.BaseController;
//import com.example.testDeliveryV2.exceptions.ServiceException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//
//@COntrollerAdvice
//@RestController
//public class ErrorController extends BaseController {
//
//    @ExceptionHandler(ServiceException.class)
//    public ResponseEntity<?> resourceNotFoundException(ServiceException e) {
//        return buildResponse(buildErrorResponse(e), HttpStatus.NOT_FOUND);
//    }
//
//}
