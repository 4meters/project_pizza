package com.pizzaserver.domain.dto;

import com.pizzaserver.domain.entity.Product;

import java.util.List;

/**
 * Stores product list read from CSV file.
 */
public class ProductListDto {

    private List<Product> productList;

    public ProductListDto(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
