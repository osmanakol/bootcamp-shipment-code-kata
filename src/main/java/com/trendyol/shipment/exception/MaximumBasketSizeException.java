package com.trendyol.shipment.exception;

public class MaximumBasketSizeException extends RuntimeException {
    private static final String message = "MAXIMUM BASKET SIZE EXCEED";
    public MaximumBasketSizeException() {
        super(message);
    }
}
