package com.pizzaserver.domain.dto;

import com.pizzaserver.domain.entity.Product;

import java.util.ArrayList;

public class ProductListDto {
    private ArrayList<Product> productList;

    public ProductListDto(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
