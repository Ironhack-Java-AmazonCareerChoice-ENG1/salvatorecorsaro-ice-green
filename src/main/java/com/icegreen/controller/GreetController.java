package com.icegreen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @GetMapping("/hello")
    public String helloWorld(@RequestParam String name, @RequestParam(required = false) String color) {
        var chosenColor = color == null ? "green" : color;
        return "Hello " + name + " your favorite color is " + chosenColor;
    }



}
