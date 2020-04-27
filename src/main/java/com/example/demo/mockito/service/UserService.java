package com.example.demo.mockito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private BaseService baseService;

    public String test() {
        String connect = baseService.findFromDb();
        connect += "-test";//test自己的业务
        return connect;
    }
}
