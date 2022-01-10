package com.pizzaserver.domain.model;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.entity.Product;
import com.pizzaserver.domain.object.OrderListProduct;
import com.pizzaserver.service.ProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * Class called by CheckoutCalculate, it's used to calculate discounts of order and final cost after discounts
 *
 * <p>discountHalfPrice - true if order has 2 pizzas (discount type 1)</p>
 * <p>discountFreeDrink - true if order has 2 pizzas and drink - results in free drink (combines with discountHalfPrice)</p>
 * <p>discountRealDeal - true if order is over 100zł - results in order cheaper by 20% of its price</p>
 * <p>pizzaCount - pizzas count</p>
 * <p>discountHPlist - list of products with HalfPrice discount</p>
 * <p>OrderListProduct - discountFDid</p>
 * <p>totalCost - order total cost before discount</p>
 * <p>totalCostDiscount - order total cost after discount</p>
 *
 *
*/

public class CheckoutDiscountCalculate {

    private boolean discountHalfPrice=false; //discount type 1 (when 2 pizzas in cart, second cheaper by 50%)
    private boolean discountFreeDrink=false; //2 (discountHP & discountFD) (drink is free, when 2 pizzas in cart)
    private boolean discountRealDeal=false; //3        //order over 100zł is cheaper by 20% of its price
    private int pizzaCount=0; //pizzas count
    private ArrayList<OrderListProduct> discountHPlist;
    private OrderListProduct discountFDid;
    private double totalCost;
    private double totalCostDiscount=0;

    private List<Product> productList;
    private ArrayList<OrderListProduct> orderListSplitted;

    public CheckoutDiscountCalculate(ArrayList<OrderListProduct> orderListSplitted, double totalCost, ProductService productService) {
        this.totalCost=totalCost;
        this.orderListSplitted=orderListSplitted;

        discountHPlist=new ArrayList<>();
        productList = productService.getProductList().getProductList();
        discountCheck();
        discountCount();
    }


    private void discountCheck() {
        if (totalCost > 100.0) {
            discountRealDeal = true;
        } else {
            totalCostDiscount = totalCost;
            for (OrderListProduct orderListProduct : orderListSplitted) {
                for (Product product : productList) {
                    if (product.getId().equals(orderListProduct.getOrderId())) {
                        switch (product.getType()) {
                            case "0": {
                                if (!discountHalfPrice) {
                                    if(orderListProduct.getOrderCount().equals("1")) {

                                        if (pizzaCount == 0 || pizzaCount ==1) {
                                            discountHPlist.add(orderListProduct);
                                            pizzaCount++;
                                        }
                                    }
                                    else{
                                        if (pizzaCount == 0) {
                                            discountHPlist.add(orderListProduct);
                                            discountHPlist.add(orderListProduct);
                                            pizzaCount=2;
                                        } else if (pizzaCount == 1) {
                                            discountHPlist.add(orderListProduct);
                                            pizzaCount++;
                                        }

                                    }
                                    if (pizzaCount == 2) {
                                        discountHalfPrice = true;
                                    }
                                }
                                break;
                            }
                            case "1": {
                                if (pizzaCount == 2 && !discountFreeDrink) {
                                    discountFreeDrink = true;
                                    discountFDid = orderListProduct;//='('+orderListCurrentProduct.getOrderId()+','+orderListCurrentProduct.getOrderSize()+')';
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void discountCount(){
        if(discountRealDeal){
            totalCostDiscount=totalCost-totalCost*0.2;
        }
        else if(discountHalfPrice) {
            OrderListProduct orderHalfPriceProduct = discountHPlist.get(1);

            for (Product product : productList) {
                if (product.getId().equals(orderHalfPriceProduct.getOrderId())) {
                    totalCostDiscount-=(Double.parseDouble(product.getCostBySize(orderHalfPriceProduct.getOrderSize())
                            .replace(',', '.'))) * 0.5;
                }
            }
            if (discountFreeDrink) {
                for (Product product : productList) {
                    if (product.getId().equals(discountFDid.getOrderId())) {
                        totalCostDiscount-=(Double.parseDouble(product.getCostBySize(discountFDid.getOrderSize())
                                .replace(',', '.')));
                    }
                }
            }
        }
    }
    public CheckoutCalculatedDto getFinalCost(){
        CheckoutCalculatedDto checkoutCalculatedDto;

        if(discountRealDeal){//discount 20%
            checkoutCalculatedDto= new CheckoutCalculatedDto(
                    Double.toString(totalCost),
                    Double.toString(totalCostDiscount),
                    "3",
                    "");
        }

        else if(discountHalfPrice){
            //create list of products with half price discount
            String discountIds='('+discountHPlist.get(0).getOrderId()+','+discountHPlist.get(0).getOrderSize()+"),("+
                    discountHPlist.get(1).getOrderId()+','+discountHPlist.get(1).getOrderSize()+')';

            if(discountFreeDrink){//half price + free drink discount
                //add drink to discount list
                discountIds+=",("+discountFDid.getOrderId()+','+discountFDid.getOrderSize()+')';

                checkoutCalculatedDto= new CheckoutCalculatedDto(
                        Double.toString(totalCost),
                        Double.toString(totalCostDiscount),
                        "2",discountIds);
            }
            else{//only half price discount
                checkoutCalculatedDto= new CheckoutCalculatedDto(
                        Double.toString(totalCost),
                        Double.toString(totalCostDiscount),
                        "1",discountIds);
            }
        }

        else{//no discount
            checkoutCalculatedDto= new CheckoutCalculatedDto(
                    Double.toString(totalCost),
                    Double.toString(totalCostDiscount),
                    "0",
                    "");
        }

        return checkoutCalculatedDto;
    }
}
