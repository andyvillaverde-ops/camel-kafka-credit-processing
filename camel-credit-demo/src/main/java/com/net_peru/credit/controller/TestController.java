package com.net_peru.credit.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping
    public String test() {
        producerTemplate.sendBody("direct:test", "Hola Camel");
        return "Enviado a Camel";
    }
}