package com.example.testDeliveryV2.chainOfResp;

public class Confirmation implements OrderHandler {
    protected OrderHandler orderHandler;
    @Override
    public void nextHandler(OrderHandler handler) {
        this.orderHandler = handler;
    }
    @Override
    public boolean processRequest(OrderAction request) {
        if ("confirm".equalsIgnoreCase(request.getAction())) {
            System.out.println("Your order has been placed successfully");
            return true;
        } else {
            System.out.println("Request cannot be processed by any one of handlers");
            return false;
        }
    }
}
