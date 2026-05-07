package com.net_peru.credit.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TestRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:test")
            .routeId("test-route")
            .log("Mensaje recibido en Camel: ${body}");
    }
}