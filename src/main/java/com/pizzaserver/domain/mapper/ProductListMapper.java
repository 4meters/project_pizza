package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.dto.ProductListDto;
import com.pizzaserver.domain.object.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductListMapper implements Converter<ProductListDto, ArrayList<Product>> {
    @Override
    public ProductListDto convert(ArrayList<Product> productList) {
        return new ProductListDto(productList);
    }
}
