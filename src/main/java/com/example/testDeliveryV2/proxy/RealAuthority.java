package com.example.testDeliveryV2.proxy;

public class RealAuthority implements Authority {
    @Override
    public String connectTo(String login)
    {
        return "User "+ login + " accessed";
    }
}
