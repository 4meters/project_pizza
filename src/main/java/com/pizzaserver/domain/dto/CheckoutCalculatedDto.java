package com.pizzaserver.domain.dto;

public class CheckoutCalculatedDto {
    private String cost;
    private String costDiscount;
    private String discountType;
    private String discountProductList;

    public CheckoutCalculatedDto(){
    }

    public CheckoutCalculatedDto(String cost, String costDiscount, String discountType,
                                 String discountProductList){
        this.cost=cost;
        this.costDiscount=costDiscount;
        this.discountType=discountType;
        this.discountProductList=discountProductList;
    }

    public String getCost() {
        return cost;
    }
    public String getDiscountType() {
        return discountType;
    }
    public String getCostDiscount() {
        return costDiscount;
    }
    public String getDiscountProductList() {
        return discountProductList;
    }
}
