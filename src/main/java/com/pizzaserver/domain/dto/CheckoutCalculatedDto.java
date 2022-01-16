package com.pizzaserver.domain.dto;

/**
 * Stores information about order cost and discounts
 */
public class CheckoutCalculatedDto {


    private String cost;
    private String costDiscount;
    private String discountType;
    private String discountProductList;

    public CheckoutCalculatedDto(){
    }

    public CheckoutCalculatedDto(Builder builder){
        this.cost=builder.cost;
        this.costDiscount=builder.costDiscount;
        this.discountType=builder.discountType;
        this.discountProductList=builder.discountProductList;
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

    public static final class Builder {
        private String cost;
        private String costDiscount;
        private String discountType;
        private String discountProductList;

        public Builder() {
        }

        public static Builder aCheckoutCalculatedDto() {
            return new Builder();
        }

        public Builder cost(String cost) {
            this.cost = cost;
            return this;
        }

        public Builder costDiscount(String costDiscount) {
            this.costDiscount = costDiscount;
            return this;
        }

        public Builder discountType(String discountType) {
            this.discountType = discountType;
            return this;
        }

        public Builder discountProductList(String discountProductList) {
            this.discountProductList = discountProductList;
            return this;
        }

        public CheckoutCalculatedDto build() {
            return new CheckoutCalculatedDto(this);
        }
    }
}
