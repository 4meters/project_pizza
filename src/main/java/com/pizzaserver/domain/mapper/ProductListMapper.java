package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.dto.ProductListDto;
import com.pizzaserver.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Converts ArrayList of ProductCSV to ProductListDto
 */
@Component
public class ProductListMapper implements Converter<ProductListDto, List<Product>> {

    @Override
    public ProductListDto convert(List<Product> productList) {
        return new ProductListDto(productList);
    }
}
