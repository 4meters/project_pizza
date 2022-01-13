package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.dto.ProductDto;
import com.pizzaserver.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper implements Converter<Product, ProductDto> {

    @Override
    public Product convert(ProductDto productDto) {
        Product product = new Product.Builder()
                .withId(productDto.getId())
                .withType(productDto.getType())
                .withName(productDto.getName())
                .description(productDto.getDescription())
                .withCostS(productDto.getCostS())
                .withCostM(productDto.getCostM())
                .withCostL(productDto.getCostL())
                .withCostU(productDto.getCostU()).build();

        return product;
    }
}
