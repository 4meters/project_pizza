package com.pizzaserver.domain.dto;

import com.pizzaserver.domain.object.Product;

import java.util.ArrayList;

/**
 * Stores product list read from CSV file.
 */
public class ProductListDto {

    private ArrayList<Product> productList;

    public ProductListDto(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
