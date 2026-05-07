package com.net_peru.credit.processor;

import com.net_peru.credit.model.CreditRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidationProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {

        CreditRequest req = exchange.getIn().getBody(CreditRequest.class);

        if (req.getAmount() <= 0) {
            throw new IllegalArgumentException("Monto inválido");
        }

        if (req.getClientId() == null || req.getClientId().isEmpty()) {
            throw new IllegalArgumentException("ClientId requerido");
        }
    }
}