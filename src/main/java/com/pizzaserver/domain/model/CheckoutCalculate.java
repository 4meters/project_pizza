package com.pizzaserver.domain.model;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.object.OrderListProduct;
import com.pizzaserver.domain.object.Product;
import com.pizzaserver.domain.object.ProductOnReceipt;
import com.pizzaserver.domain.repository.ProductListRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Calculates total cost, and cost after discount also gives discount type
 * CheckoutDto --> CheckoutCalculatedDto
 */

public class CheckoutCalculate {
    ArrayList<OrderListProduct> orderListSplitted;
    CheckoutCalculatedDto checkoutCalculatedDto;
    ArrayList<Product> productList;


    public CheckoutCalculate(ArrayList<OrderListProduct> orderListSplitted) {
        this.orderListSplitted = orderListSplitted;
        this.productList = new ProductListRepository().getProductList();
    }


    private void checkoutCount() {
        double totalCost = 0.0;
        for (OrderListProduct orderListProduct : orderListSplitted) {
            totalCost+=Double.parseDouble(calculateSingleProduct(orderListProduct).replace(',', '.'));
        }
        checkoutCountDiscount(totalCost);
    }

    private void checkoutCountDiscount(double totalCost) {
        checkoutCalculatedDto = new CheckoutDiscountCalculate(orderListSplitted, totalCost).getFinalCost();
    }

    public CheckoutCalculatedDto getCheckoutCalculatedDto() {
        checkoutCount();
        return checkoutCalculatedDto;
    }

    public ArrayList<ProductOnReceipt> getCheckoutProducts(){ //forPdfGenerator
        ArrayList<ProductOnReceipt> productOnReceiptList=new ArrayList<>();
        Map<Integer, Callable> map = new HashMap<>();
        for(OrderListProduct orderListProduct : orderListSplitted){
            for(Product product: productList) {
                if(product.getId().equals(orderListProduct.getOrderId())) {
                    String totalCost=calculateSingleProduct(orderListProduct);
                    productOnReceiptList.add(new ProductOnReceipt(
                            product.getName(),
                            orderListProduct.getOrderSize(),
                            totalCost,
                            orderListProduct.getOrderCount(),
                            product.getType()
                    ));
                }
            }

        }
        return productOnReceiptList;
    }

    private String calculateSingleProduct(OrderListProduct orderListProduct){
        double totalCost=0.0;
        double cost;
        int count;
        for(Product product:productList){
            if(product.getId().equals(orderListProduct.getOrderId())){
                cost=Double.parseDouble(product.getCostBySize(orderListProduct.getOrderSize()));
                count=Integer.parseInt(orderListProduct.getOrderCount());
                totalCost=count*cost;
                break;
            }
        }
        return Double.toString(totalCost);
    }
}
