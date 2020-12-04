package com.example.testDeliveryV2.state;

public class StartState implements State {

    public boolean doAction(Context context) {
        System.out.println("User is in start state");
        context.setState(this);
        return false;
    }

    public String toString(){
        return "Start State";
    }
}


