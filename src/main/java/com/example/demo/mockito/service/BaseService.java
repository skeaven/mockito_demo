package com.example.demo.mockito.service;

import org.springframework.stereotype.Component;

@Component
public class BaseService {

    public String connect() {
        return "error";
    }

    public String findFromDb() {
        return "db_data";
    }
}
