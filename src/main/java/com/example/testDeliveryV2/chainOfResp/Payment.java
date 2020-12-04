package com.example.testDeliveryV2.chainOfResp;

public class Payment implements OrderHandler {
    protected OrderHandler orderHandler;
    @Override
    public void nextHandler(OrderHandler handler) {
        this.orderHandler = handler;
    }
    @Override
    public boolean processRequest(OrderAction request) {
        if ("payment".equalsIgnoreCase(request.getAction())) {
            System.out.println("Make payment for your order");
            return true;
        } else {
            return false;
        }
    }
}
