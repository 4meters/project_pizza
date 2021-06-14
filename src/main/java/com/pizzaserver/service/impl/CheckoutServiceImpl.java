package com.pizzaserver.service.impl;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.dto.CheckoutDto;
import com.pizzaserver.domain.mapper.CheckoutMapper;
import com.pizzaserver.domain.mapper.Converter;
import com.pizzaserver.domain.model.CheckoutCalculate;
import com.pizzaserver.domain.object.OrderListProduct;
import com.pizzaserver.service.CheckoutService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final Converter<ArrayList<OrderListProduct>, String> checkoutMapper = new CheckoutMapper();
    @Override
    public ArrayList<OrderListProduct> checkoutOrderListDecode(String orderList) {

        return checkoutMapper.convert(orderList);
    }

    @Override
    public CheckoutCalculatedDto getCartTotalCost(CheckoutDto checkoutDto) {
        return new CheckoutCalculate(checkoutOrderListDecode(checkoutDto.getOrderList())).getCheckoutCalculatedDto();
    }
}
