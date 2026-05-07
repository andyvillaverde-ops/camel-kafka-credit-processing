package com.net_peru.credit.processor;

import com.net_peru.credit.model.CreditRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DecisionProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {

        CreditRequest req = exchange.getIn().getBody(CreditRequest.class);

        boolean approved = req.getAmount() < 10000;

        // Guardamos resultado en header
        exchange.getIn().setHeader("approved", approved);
    }
}