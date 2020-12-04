package com.example.testDeliveryV2.exceptions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceException extends Exception {

    protected String message;
    protected ErrorCode errorCode;

}
