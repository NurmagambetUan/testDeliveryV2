package com.example.testDeliveryV2.chainOfResp;

public class ProductSelection implements OrderHandler {
    protected OrderHandler orderHandler;
    @Override
    public void nextHandler(OrderHandler orderHandler) {
        this.orderHandler = orderHandler;
    }
    @Override
    public boolean processRequest(OrderAction request) {
        if ("selectprod".equalsIgnoreCase(request.getAction())) {
            System.out.println("Select a product you would like to buy");
            return true;
        } else {
            orderHandler.processRequest(request);
            return false;
        }
    }
}
