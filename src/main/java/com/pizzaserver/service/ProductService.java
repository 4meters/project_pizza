package com.pizzaserver.service;

import com.pizzaserver.domain.dto.ProductDto;
import com.pizzaserver.domain.dto.ProductListDto;

public interface ProductService {
    ProductListDto getProductList();

    boolean updateDatabase(String token);

    boolean addProduct(ProductDto productDto);

    boolean editProduct(ProductDto productDto);

    boolean deleteProduct(Integer productId);

    ProductDto getProduct(String id);
}
