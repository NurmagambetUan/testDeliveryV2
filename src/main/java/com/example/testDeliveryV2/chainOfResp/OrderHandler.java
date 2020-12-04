package com.example.testDeliveryV2.chainOfResp;


public interface OrderHandler {

    void nextHandler(OrderHandler handler);

    public boolean processRequest(OrderAction request);
}
