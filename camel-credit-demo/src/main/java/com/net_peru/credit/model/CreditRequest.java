package com.net_peru.credit.model;

public class CreditRequest {
    private String clientId;
    private double amount;

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
