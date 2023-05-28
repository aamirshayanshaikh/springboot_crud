package com.aamir.demo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public String getNamer(){
        return "Aamir";
    }
}
