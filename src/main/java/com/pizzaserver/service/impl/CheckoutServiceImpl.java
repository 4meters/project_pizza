package com.pizzaserver.service.impl;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.model.CheckoutCalculate;
import com.pizzaserver.domain.model.OrderListProduct;
import com.pizzaserver.service.CheckoutService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Override
    public ArrayList<OrderListProduct> checkoutOrderListDecode(String orderList) { //? Wydzielić do innego pliku?
        //String orderList;
        ArrayList<OrderListProduct> orderListSplitted=new ArrayList<>();
        String orderId="",orderSize="",orderCount="";

        for(int i=0;i<orderList.length();i++){
            if(orderList.charAt(i)=='('){
                i++;
                while(orderList.charAt(i)!=','){
                    orderId+=orderList.charAt(i);
                    i++;
                }
                i++;
                while(orderList.charAt(i)!=','){
                    orderSize+=orderList.charAt(i);
                    i++;
                }
                i++;
                while(orderList.charAt(i)!=')'){
                    orderCount+=orderList.charAt(i);
                    i++;
                }
                orderListSplitted.add(new OrderListProduct(orderId,orderSize,orderCount));
                //LOGGER.info("### Product: {} size: {} count: {} ", orderId,orderSize,orderCount);
                orderId="";
                orderSize="";
                orderCount="";
            }
        }
        return orderListSplitted;
    }

    @Override //Do mappera? //Do mappera CheckoutDto ---> orderList
    public CheckoutCalculatedDto getCartTotalCost(String orderList) {

        return new CheckoutCalculate(checkoutOrderListDecode(orderList)).getCheckoutCalculatedDto();
    }
}
