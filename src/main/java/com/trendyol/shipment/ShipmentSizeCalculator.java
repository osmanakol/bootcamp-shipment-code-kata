package com.trendyol.shipment;

import com.trendyol.shipment.exception.EmptyBasketException;
import com.trendyol.shipment.exception.MaximumBasketSizeException;

import java.util.List;

public interface ShipmentSizeCalculator {
    ShipmentSize calculate(List<Product> products) throws EmptyBasketException, MaximumBasketSizeException;
}
