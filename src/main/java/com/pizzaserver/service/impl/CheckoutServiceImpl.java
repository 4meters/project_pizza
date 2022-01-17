package com.pizzaserver.service.impl;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.dto.CheckoutDto;
import com.pizzaserver.domain.mapper.CheckoutMapper;
import com.pizzaserver.domain.mapper.Converter;
import com.pizzaserver.domain.model.CheckoutCalculate;
import com.pizzaserver.domain.object.OrderListProduct;
import com.pizzaserver.service.CheckoutService;
import com.pizzaserver.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service for CheckoutApiController
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final Converter<ArrayList<OrderListProduct>, String> checkoutMapper = new CheckoutMapper();
    private final ProductService productService;
    @Override
    public ArrayList<OrderListProduct> checkoutOrderListDecode(String orderList) {

        return checkoutMapper.convert(orderList);
    }

    public CheckoutServiceImpl(ProductService productService) {
        this.productService=productService;
    }

    @Override
    public CheckoutCalculatedDto getCartTotalCost(CheckoutDto checkoutDto) {
        return new CheckoutCalculate(checkoutOrderListDecode(checkoutDto.getOrderList()), productService).getCheckoutCalculatedDto();
    }
}
