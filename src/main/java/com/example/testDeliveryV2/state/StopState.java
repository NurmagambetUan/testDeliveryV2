package com.example.testDeliveryV2.state;

public class StopState implements State {

    public boolean doAction(Context context) {
        System.out.println("User is in stop state");
        context.setState(this);
        return true;
    }

    public String toString(){
        return "Stop State";
    }
}
