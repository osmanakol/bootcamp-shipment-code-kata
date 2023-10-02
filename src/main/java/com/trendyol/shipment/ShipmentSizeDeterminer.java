package com.trendyol.shipment;

import com.trendyol.shipment.exception.EmptyBasketException;
import com.trendyol.shipment.exception.MaximumBasketSizeException;

import java.util.*;
import java.util.stream.Collectors;

public class ShipmentSizeDeterminer implements ShipmentSizeCalculator {

    private static final int OCCURRENCE_THRESHOLD = 3;
    private static final int MAXIMUM_BASKET_SIZE_LIMIT = 5;
    @Override
    public ShipmentSize calculate(List<Product> products) {
        if (products.size() > MAXIMUM_BASKET_SIZE_LIMIT) throw new MaximumBasketSizeException();
        if (products.isEmpty()) throw new EmptyBasketException();

        List<ShipmentSize> productSizes = products.stream().map(Product::getSize).toList();
        return thresholdRule(productSizes);
    }

    private ShipmentSize thresholdRule (List<ShipmentSize> productSizes) {
        Map<ShipmentSize, Long> groupedShipmentSize = groupByShipmentSize(productSizes);

        ShipmentSize result = groupedShipmentSize.entrySet().stream()
                .filter(entry -> entry.getValue() >= OCCURRENCE_THRESHOLD)
                .map(entry -> entry.getKey().getUpper())
                .findFirst()
                .orElse(ShipmentSize.getLargestShipmentSize(productSizes));

        return result;
    }


    private Map<ShipmentSize, Long> groupByShipmentSize(List<ShipmentSize> productSizes) {
        Map<ShipmentSize, Long> groupedBasketSize = productSizes.stream()
                .collect(Collectors.groupingBy(ShipmentSize::get, Collectors.counting()));

        return groupedBasketSize;
    }
}
