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

    private ProductListDto(Builder builder) {
        this.productList = builder.productList;
    }

    public List<Product> getProductList() {
        return productList;
    }


    public static final class Builder {
        private List<Product> productList;

        public Builder() {
        }

        public static Builder aProductListDto() {
            return new Builder();
        }

        public Builder productList(List<Product> productList) {
            this.productList = productList;
            return this;
        }

        public ProductListDto build() {
            return new ProductListDto(this);
        }
    }
}
