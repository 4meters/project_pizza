package com.pizzaserver.domain.model;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.object.Product;
import com.pizzaserver.domain.repository.ProductListRepository;

import java.util.ArrayList;

public class CheckoutDiscountCalculate {

    private boolean discountHalfPrice=false; //discount type 1
    private boolean discountFreeDrink=false; //2 (HP+FD)
    private boolean discountRealDeal=false; //3        //order over 100zł is cheaper by 20% of its price
    private int pizzaCount=0; //pizzas count
    private ArrayList<OrderListProduct> discountHPlist;
    private OrderListProduct discountFDid;
    private double totalCost;
    private double totalCostDiscount=0;

    private ArrayList<Product> productList;
    private ArrayList<OrderListProduct> orderListSplitted;

    public CheckoutDiscountCalculate(ArrayList<OrderListProduct> orderListSplitted, double totalCost) {
        this.totalCost=totalCost;
        this.orderListSplitted=orderListSplitted;

        discountHPlist=new ArrayList<>();
        productList=new ProductListRepository().getProductList();
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
                    switch (orderHalfPriceProduct.getOrderSize()) {
                        case "S": {
                            totalCostDiscount-=(Double.parseDouble(product.getCostS().replace(',', '.'))) * 0.5;
                            break;
                        }
                        case "M": {
                            totalCostDiscount-=(Double.parseDouble(product.getCostM().replace(',', '.'))) * 0.5;
                            break;
                        }
                        case "L": {
                            totalCostDiscount-=(Double.parseDouble(product.getCostL().replace(',', '.'))) * 0.5;
                            break;
                        }
                        case "U": {
                            totalCostDiscount-=(Double.parseDouble(product.getCostU().replace(',', '.'))) * 0.5;
                            break;
                        }
                    }
                }
            }
            if (discountFreeDrink) {
                for (Product product : productList) {
                    if (product.getId().equals(discountFDid.getOrderId())) {
                        switch (discountFDid.getOrderSize()) {
                            case "S": {
                                totalCostDiscount-=(Double.parseDouble(product.getCostS().replace(',', '.')));
                                break;
                            }
                            case "M": {
                                totalCostDiscount-=(Double.parseDouble(product.getCostM().replace(',', '.')));
                                break;
                            }
                            case "L": {
                                totalCostDiscount-=(Double.parseDouble(product.getCostL().replace(',', '.')));
                                break;
                            }
                            case "U": {
                                totalCostDiscount-=(Double.parseDouble(product.getCostU().replace(',', '.')));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public CheckoutCalculatedDto getFinalCost(){
        CheckoutCalculatedDto checkoutCalculatedDto;
        if(discountRealDeal){
            checkoutCalculatedDto= new CheckoutCalculatedDto(Double.toString(totalCost),Double.toString(totalCostDiscount),"3","");
        }
        else if(discountHalfPrice){
            String discountIds='('+discountHPlist.get(0).getOrderId()+','+discountHPlist.get(0).getOrderSize()+"),("+
                    discountHPlist.get(1).getOrderId()+','+discountHPlist.get(1).getOrderSize()+')';
            if(discountFreeDrink){
                discountIds+=",("+discountFDid.getOrderId()+','+discountFDid.getOrderSize()+')';
                checkoutCalculatedDto= new CheckoutCalculatedDto(Double.toString(totalCost),Double.toString(totalCostDiscount),"2",discountIds);
            }
            else{
                checkoutCalculatedDto= new CheckoutCalculatedDto(Double.toString(totalCost),Double.toString(totalCostDiscount),"1",discountIds);
            }
        }
        else{
            checkoutCalculatedDto= new CheckoutCalculatedDto(Double.toString(totalCost),Double.toString(totalCostDiscount),"0","");
        }

        return checkoutCalculatedDto;
    }
}
