package com.trendyol.shipment;

import java.util.ArrayList;
import java.util.List;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public ShipmentSize getUpper() {
        int nextOrdinal= this.ordinal() + 1;

        if (nextOrdinal < ShipmentSize.values().length) {
            return ShipmentSize.values()[nextOrdinal];
        }

        return this;
    }

    public static ShipmentSize getLargestShipmentSize(List<ShipmentSize> sizes) {
        int maximumOrdinal = sizes.stream()
                .mapToInt(Enum::ordinal)
                .max()
                .orElse(-1);

        return ShipmentSize.values()[maximumOrdinal];
    }
}
