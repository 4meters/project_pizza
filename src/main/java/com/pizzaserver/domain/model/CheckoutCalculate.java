package com.pizzaserver.domain.model;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.entity.Product;
import com.pizzaserver.domain.object.OrderListProduct;
import com.pizzaserver.domain.object.ProductOnReceipt;
import com.pizzaserver.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutCalculate.class);

    public CheckoutCalculate(ArrayList<OrderListProduct> orderListSplitted, ProductService productService) {
        this.orderListSplitted = orderListSplitted;
        System.out.println(orderListSplitted);
        this.productService = productService;
        System.out.println(this.productService);
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
                            product.getType().toString()
                    ));
                }
            }

        }
        return productOnReceiptList;
    }

    private String calculateSingleProduct(OrderListProduct orderListProduct){
        List<Product> productList = productService.getProductList().getProductList();
        System.out.println(productList);
        double totalCost=0.0;
        double cost;
        int count;
        LOGGER.info("Funkcja calculate");
        for(Product product : productList){
            System.out.println(product);
            if(product.getId().toString().equals(orderListProduct.getOrderId())){
                LOGGER.info("Znaleziono produkt");
                cost=product.getCostBySize(orderListProduct.getOrderSize());
                count=Integer.parseInt(orderListProduct.getOrderCount());
                totalCost=count*cost;
                break;
            }
        }
        return Double.toString(totalCost);
    }
}
