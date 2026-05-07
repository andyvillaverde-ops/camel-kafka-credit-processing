package com.net_peru.credit.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.net_peru.credit.processor.DecisionProcessor;
import com.net_peru.credit.processor.ValidationProcessor;

@Component
public class CreditRoute extends RouteBuilder {

    @Override
    public void configure() {

        // 🔴 Manejo global de errores
        onException(Exception.class)
            .maximumRedeliveries(2)          // retry 2 veces
            .redeliveryDelay(1000)           // espera 1 segundo
            .log("Error procesando: ${exception.message}")
            .handled(true)                   // evita que explote
            //.to("direct.dlq");               // ruta interna
            //.to("kafka:credit.dlq");               // ahora va a Kafka
            .to("kafka:credit.dlq?brokers=localhost:9092");               // ahora va a Kafka

        from("direct:credit")
            .routeId("credit-route")

            .log("Solicitud recibida: ${body}")

            .wireTap("direct:audit")

            // 🔴 Validación (puede fallar)
            .process(new ValidationProcessor())

            .process(new DecisionProcessor())

            .choice()
                .when(header("approved").isEqualTo(true))
                    //.log("Crédito APROBADO")
                    //.to("kafka:credit.approved")
                    .to("kafka:credit.approved?brokers=localhost:9092")
                .otherwise()
                    //.log("Crédito RECHAZADO")
                    //.to("kafka:credit.rejected")
                    .to("kafka:credit.rejected?brokers=localhost:9092")
            .end();

        // Auditoría
        from("direct:audit")
        	.routeId("audit-route")
            .log("AUDITORIA: ${body}");

        //Consumer aprobado
        //from("kafka:credit.approved?groupId=credit-group")
        from("kafka:credit.approved?brokers=localhost:9092&groupId=credit-group")
            .routeId("approved-consumer")
            .log("Evento APROBADO recibido: ${body}");
        
        //Consumer rechazado
        //from("kafka:credit.rejected?groupId=credit-group")
	    from("kafka:credit.rejected?brokers=localhost:9092&groupId=credit-group")
	        .routeId("rejected-consumer")
	        .log("Evento RECHAZADO recibido: ${body}");
        
        // DLQ
        //from("direct:dlq")
        //    .log("MENSAJE ENVIADO A DLQ: ${body}");
        
        // DLQ kafka
	    //from("kafka:credit.dlq?groupId=dlq-group")
        from("kafka:credit.dlq?brokers=localhost:9092&groupId=dlq-group")
        	.routeId("dlq-consumer")
        	.log("Evento DLQ recibido: ${body}");
        
    }
}
