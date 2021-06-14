package com.pizzaserver.domain.object;

/**
 * Product from order list by format "($orderId, $orderSize, $orderCount)"
 */

public class OrderListProduct {
    private String orderId;
    private String orderSize;
    private String orderCount;

    public OrderListProduct() {
    }

    public OrderListProduct(String orderId, String orderSize, String orderCount) {
        this.orderId = orderId;
        this.orderSize = orderSize;
        this.orderCount = orderCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderSize() {
        return orderSize;
    }

    public String getOrderCount() {
        return orderCount;
    }

}
