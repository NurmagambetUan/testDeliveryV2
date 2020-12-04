package com.example.testDeliveryV2.proxy;

import java.util.ArrayList;
import java.util.List;


public class ProxyAuthority implements Authority {
    private Authority authority = new RealAuthority();
    private static List<String> users;

    static
    {
        users = new ArrayList<String>();
        users.add("admin");
        users.add("admin1");
    }

    @Override
    public String connectTo(String login) throws Exception {
        if(!users.contains(login.toLowerCase())){
            throw new Exception("Access Denied");
        }
        return authority.connectTo(login);
    }

}
