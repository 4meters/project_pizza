package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.dto.ProductDto;
import com.pizzaserver.domain.entity.Product;
import org.springframework.stereotype.Component;

/**
 * Converts ArrayList of ProductCSV to ProductListDto
 */
@Component
public class ProductMapper implements Converter<ProductDto, Product> {

    @Override //TODO add builder to dto
    public ProductDto convert(Product product) {
        ProductDto productDto =new ProductDto(
                product.getType(), product.getDescription(), product.getName(), product.getCostS(), product.getCostM(),
                product.getCostL(),product.getCostU()
                );
        return productDto;
    }
}
