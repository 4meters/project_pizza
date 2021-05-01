package com.example.pizzaserver.service.impl;

import com.example.pizzaserver.domain.dto.ProductListDto;
import com.example.pizzaserver.domain.entity.Product;
import com.example.pizzaserver.domain.mapper.Converter;
import com.example.pizzaserver.domain.repository.ProductListRepository;
import com.example.pizzaserver.service.ProductListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
