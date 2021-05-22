package com.pizzaserver.domain.dto;

public class CheckoutDto {
    private String orderList;

    public CheckoutDto(){
    }

    public CheckoutDto(String orderList){
        this.orderList=orderList;
    }

    public String getOrderList() {
        return orderList;
    }
}
