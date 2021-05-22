package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.dto.CheckoutDto;
import org.springframework.stereotype.Component;

@Component
public class CheckoutMapper implements Converter<CheckoutCalculatedDto, CheckoutDto>{

    @Override
    public CheckoutCalculatedDto convert(CheckoutDto from) {
        //final OrderListSplitted orderListSplitted=new OrderListSplitted(from.getOrderList());
        //final ProductListRepository productListRepository = new ProductListRepository().getProductList();
        //checkoutCalculatedDto=new DiscountCalculate(productListDto,orderListSplitted,totalCost).getFinalCost();
        return null;
    }
}
