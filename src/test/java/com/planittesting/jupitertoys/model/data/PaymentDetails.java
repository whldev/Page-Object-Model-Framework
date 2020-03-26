package com.planittesting.jupitertoys.model.data;

public class PaymentDetails {

    private String cardType;
    private String cardNumber;

    public PaymentDetails setCardType(String cardType) {
        this.cardType = cardType;
        return this;
    }

    public PaymentDetails setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCardType() { return cardType; }

    public String getCardNumber() { return cardNumber; }
}
