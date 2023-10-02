package com.trendyol.shipment.exception;

public class EmptyBasketException extends RuntimeException{
    private static final String message = "BASKET IS EMPTY";
    public EmptyBasketException() {
        super(message);
    }
}
