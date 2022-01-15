package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.dto.ProductListDto;
import com.pizzaserver.domain.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Converts ArrayList of ProductCSV to ProductListDto
 */
@Component
public class ProductListMapper implements Converter<ProductListDto, List<Product>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductListMapper.class);

    @Override
    public ProductListDto convert(List<Product> productList) {
        //LOGGER.info(productList.get(0).toString());
        return new ProductListDto(productList);
    }
}
