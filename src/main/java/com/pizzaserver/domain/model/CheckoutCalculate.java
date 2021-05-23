package com.pizzaserver.domain.model;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.entity.Product;
import com.pizzaserver.domain.repository.ProductListRepository;

import java.util.ArrayList;

/**
 * Calculates total cost, and cost after discount also gives discount type
 * CheckoutDto --> CheckoutCalculatedDto
 */

public class CheckoutCalculate {
    ArrayList<OrderListProduct> orderListSplitted;
    CheckoutCalculatedDto checkoutCalculatedDto;
    ArrayList<Product> productList;

    public CheckoutCalculate(ArrayList<OrderListProduct> orderListSplitted) {
        this.orderListSplitted = orderListSplitted;
        this.productList = new ProductListRepository().getProductList();
    }

    private void checkoutCount() {
        double totalCost = 0.0;
        for (OrderListProduct orderListProduct : orderListSplitted) {
            for (Product product : productList) {
                if (product.getId().equals(orderListProduct.getOrderId())) {

                    double count = Double.parseDouble(orderListProduct.getOrderCount());

                    switch (orderListProduct.getOrderSize()) {
                        case "S": {
                            totalCost += count * Double.parseDouble(product.getCostS().replace(',', '.'));
                            break;
                        }
                        case "M": {
                            totalCost += count * Double.parseDouble(product.getCostM().replace(',', '.'));
                            break;
                        }
                        case "L": {
                            totalCost += count * Double.parseDouble(product.getCostL().replace(',', '.'));
                            break;
                        }
                        case "U": {
                            totalCost += count * Double.parseDouble(product.getCostU().replace(',', '.'));
                            break;
                        }
                    }
                }
            }
            checkoutCountDiscount(totalCost);
        }
    }

    private void checkoutCountDiscount(double totalCost) {
        checkoutCalculatedDto = new CheckoutDiscountCalculate(orderListSplitted, totalCost).getFinalCost();
    }

    public CheckoutCalculatedDto getCheckoutCalculatedDto() {
        checkoutCount();
        return checkoutCalculatedDto;
    }
}
