package com.trendyol.shipment;

import com.trendyol.shipment.exception.EmptyBasketException;
import com.trendyol.shipment.exception.MaximumBasketSizeException;

import java.util.*;
import java.util.stream.Collectors;

public class ShipmentSizeDeterminer implements ShipmentSizeCalculator {

    private static final int OCCURRENCE_THRESHOLD = 3;
    @Override
    public ShipmentSize calculate(List<Product> products) {
        if (products.size() > 5) throw new MaximumBasketSizeException();
        if (products.isEmpty()) throw new EmptyBasketException();

        Map<ShipmentSize, Long> groupedShipmentSize = groupByShipmentSize(products);
        return thresholdRule(groupedShipmentSize);
    }

    private ShipmentSize thresholdRule (Map<ShipmentSize, Long> groupedShipmentSize) {
        ShipmentSize size = null;

        Optional<ShipmentSize> first = groupedShipmentSize.entrySet().stream()
                .filter(entry -> entry.getValue() >= OCCURRENCE_THRESHOLD)
                .map(Map.Entry::getKey)
                .findFirst();

        if (first.isPresent()) {
            size = first.get().getUpper();
        } else {
            size = ShipmentSize.getLargestShipmentSize(
                    groupedShipmentSize.keySet().
                            stream().
                            toList());
        }

        return size;
    }

    private Map<ShipmentSize, Long> groupByShipmentSize(List<Product> products) {
        Map<ShipmentSize, Long> groupedBasketSize = products.stream()
                .collect(Collectors.groupingBy(Product::getSize, Collectors.counting()));

        return groupedBasketSize;
    }
}
