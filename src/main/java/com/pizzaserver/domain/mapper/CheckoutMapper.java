package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.object.OrderListProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Converts order list String to ArrayList of OrderListProduct
 * <p>"(id,size,count)" → OrderListProduct(id,size,count)</p>
 * <p>Example:
 *     "(1,M,1)" → OrderListProduct(1,M,1)
 * </p>
 */
@Component
public class CheckoutMapper implements Converter<ArrayList<OrderListProduct>, String>{

    @Override
    public ArrayList<OrderListProduct> convert(String from) {
        ArrayList<OrderListProduct> orderListSplitted=new ArrayList<>();
        String orderId="",orderSize="",orderCount="";
        String orderList=from;

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
                orderId="";
                orderSize="";
                orderCount="";
            }
        }
        return orderListSplitted;
    }
}
