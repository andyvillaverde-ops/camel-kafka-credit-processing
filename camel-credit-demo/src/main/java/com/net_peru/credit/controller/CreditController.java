package com.net_peru.credit.controller;

import com.net_peru.credit.model.CreditRequest;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/apply")
    public String apply(@RequestBody CreditRequest request) {
        producerTemplate.sendBody("direct:credit", request);
        return "Solicitud enviada";
    }
}
