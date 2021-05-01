package com.example.pizzaserver.domain.mapper;

import com.example.pizzaserver.domain.dto.ProductListDto;
import com.example.pizzaserver.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductListMapper implements Converter<ProductListDto, ArrayList<Product>> {
    @Override
    public ProductListDto convert(ArrayList<Product> productList) {
        return new ProductListDto(productList);
    }
}
