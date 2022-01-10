package com.pizzaserver.domain.model;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.entity.Product;
import com.pizzaserver.domain.object.OrderListProduct;
import com.pizzaserver.domain.object.ProductOnReceipt;
import com.pizzaserver.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Calculates total cost, and cost after discount also gives discount type
 * <p>
 * CheckoutDto → CheckoutCalculatedDto
 */
public class CheckoutCalculate {

    ArrayList<OrderListProduct> orderListSplitted;
    CheckoutCalculatedDto checkoutCalculatedDto;
    //ArrayList<ProductCSV> productCSVList;
    ProductService productService;


    public CheckoutCalculate(ArrayList<OrderListProduct> orderListSplitted, ProductService productService) {
        this.orderListSplitted = orderListSplitted;
        this.productService = productService;
        //this.productCSVList = new ProductListRepository().getProductList();
    }


    private void checkoutCount() {
        double totalCost = 0.0;
        for (OrderListProduct orderListProduct : orderListSplitted) {
            totalCost+=Double.parseDouble(calculateSingleProduct(orderListProduct).replace(',', '.'));
        }
        checkoutCountDiscount(totalCost);
    }

    private void checkoutCountDiscount(double totalCost) {
        checkoutCalculatedDto = new CheckoutDiscountCalculate(orderListSplitted, totalCost, productService).getFinalCost();
    }

    public CheckoutCalculatedDto getCheckoutCalculatedDto() {
        checkoutCount();
        return checkoutCalculatedDto;
    }

    public ArrayList<ProductOnReceipt> getCheckoutProducts(){ //forPdfGenerator
        List<Product> productList = productService.getProductList().getProductList();
        ArrayList<ProductOnReceipt> productOnReceiptList=new ArrayList<>();
        Map<Integer, Callable> map = new HashMap<>();
        for(OrderListProduct orderListProduct : orderListSplitted){
            //query for productId
            for(Product product : productList) {
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
        List<Product> productList = productService.getProductList().getProductList();
        double totalCost=0.0;
        double cost;
        int count;
        for(Product product : productList){
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
