package com.pizzaserver.domain.dto;

public class CheckoutDto {
    private String orderList;
    //TODO add token

    public CheckoutDto(){
    }

    public CheckoutDto(String orderList){
        this.orderList=orderList;
    }

    public String getOrderList() {
        return orderList;
    }
}
