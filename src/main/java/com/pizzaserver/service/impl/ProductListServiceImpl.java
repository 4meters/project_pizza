package com.pizzaserver.service.impl;

import com.pizzaserver.domain.dto.ProductListDto;
import com.pizzaserver.domain.object.Product;
import com.pizzaserver.domain.mapper.Converter;
import com.pizzaserver.domain.repository.ProductListRepository;
import com.pizzaserver.service.ProductListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service for ProductListApiController
 */
@Service
public class ProductListServiceImpl implements ProductListService {
    private final ProductListRepository productListRepository;
    private final Converter<ProductListDto, ArrayList<Product>> productListMapper;

    public ProductListServiceImpl(ProductListRepository productListRepository,
                                  Converter<ProductListDto, ArrayList<Product>> productListMapper) {
        this.productListRepository = productListRepository;
        this.productListMapper = productListMapper;
    }


    @Override
    public ProductListDto getProductList() {
        return productListMapper.convert(productListRepository.getProductList());
    }
}
