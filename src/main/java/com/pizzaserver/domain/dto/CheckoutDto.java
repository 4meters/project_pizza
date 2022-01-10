package com.pizzaserver.domain.dto;

/**
 * Stores list of products to order
 * <p>one product is coded in pattern: "(id,size,count)"</p>
 * <p>each product is defined in brackets and splitted by comma</p>
 * <p>example orderList: "(1,S,1),(5,M,1)"</p>
 */
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

    public static final class CheckoutDtoBuilder {
        private String orderList;

        private CheckoutDtoBuilder() {
        }

        public static CheckoutDtoBuilder aCheckoutDto() {
            return new CheckoutDtoBuilder();
        }

        public CheckoutDtoBuilder orderList(String orderList) {
            this.orderList = orderList;
            return this;
        }

        public CheckoutDto build() {
            return new CheckoutDto(orderList);
        }
    }
}
