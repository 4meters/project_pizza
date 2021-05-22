package com.pizzaserver.service;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.model.OrderListProduct;

import java.util.ArrayList;

public interface CheckoutService {
    ArrayList<OrderListProduct> checkoutOrderListDecode(String orderList);
    CheckoutCalculatedDto getCartTotalCost(String orderList);
}
