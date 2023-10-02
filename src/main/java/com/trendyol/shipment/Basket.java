package com.trendyol.shipment;

import java.util.List;

public class Basket {

    public Basket(ShipmentSizeCalculator shipmentSizeCalculator) {
        this.shipmentSizeCalculator = shipmentSizeCalculator;
    }

    private List<Product> products;
    private final ShipmentSizeCalculator shipmentSizeCalculator;

    public ShipmentSize getShipmentSize() {
        return shipmentSizeCalculator.calculate(getProducts());
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
