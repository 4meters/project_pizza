package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.dto.ProductDto;
import com.pizzaserver.domain.entity.Product;
import org.springframework.stereotype.Component;

/**
 * Converts Product to ProductDto
 */
@Component
public class ProductMapper implements Converter<ProductDto, Product> {

    @Override
    public ProductDto convert(Product product) {
        ProductDto productDto =new ProductDto.Builder().id(product.getId())
                .name(product.getName())
                .type(product.getType())
                .description(product.getDescription())
                .costS(product.getCostS())
                .costM(product.getCostM())
                .costL(product.getCostL())
                .costU(product.getCostU()).build();
        return productDto;
    }
}
