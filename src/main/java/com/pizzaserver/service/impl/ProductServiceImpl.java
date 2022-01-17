package com.pizzaserver.service.impl;

import com.pizzaserver.domain.dto.ProductDto;
import com.pizzaserver.domain.dto.ProductListDto;
import com.pizzaserver.domain.entity.Product;
import com.pizzaserver.domain.entity.User;
import com.pizzaserver.domain.mapper.Converter;
import com.pizzaserver.domain.mapper.ProductDtoMapper;
import com.pizzaserver.domain.mapper.ProductMapper;
import com.pizzaserver.domain.object.ProductCSV;
import com.pizzaserver.domain.repository.ProductListRepository;
import com.pizzaserver.domain.repository.ProductRepository;
import com.pizzaserver.domain.repository.UserRepository;
import com.pizzaserver.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for ProductListApiController
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductListRepository productListRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final Converter<ProductListDto, List<com.pizzaserver.domain.entity.Product>> productListMapper;
    private final Converter<ProductDto, Product> productMapper;
    private final Converter<List<Product>, List<ProductCSV>> productCSVMapper;
    private final Converter<Product, ProductDto> productDtoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    //private final Converter<String, List<Product>> productCSVMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              Converter<ProductListDto, List<Product>> productListMapper,
                              UserRepository userRepository, ProductListRepository productListRepository,
                              ProductMapper productMapper,
                              Converter<List<Product>, List<ProductCSV>> productCSVMapper, ProductDtoMapper productDtoMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productListMapper = productListMapper;
        this.productListRepository = productListRepository;
        this.productMapper = productMapper;
        this.productCSVMapper = productCSVMapper;
        this.productDtoMapper = productDtoMapper;
    }


    @Override
    public ProductListDto getProductList() {
        LOGGER.info(productRepository.toString());
        return productListMapper.convert(productRepository.findAll());
    }

    @Override
    public boolean updateDatabase(String token) {

        User user = userRepository.findOneByToken(token);
        if(user!=null){
            if(user.getLogin().equals("admin")){
                ArrayList<ProductCSV> productCSVList = new ProductListRepository().getProductList();
                List<Product> productList = productCSVMapper.convert(productCSVList);
                productRepository.deleteAll();
                for(Product product : productList){
                    productRepository.save(product);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean addProduct(ProductDto productDto) {
        Product product = productDtoMapper.convert(productDto);
        //try {
            productRepository.save(product);
            return true;
        //}
        //catch (Exception e){
         //   return false;
        //}

    }

    @Override
    public boolean editProduct(ProductDto productDto) {
        Product product = productDtoMapper.convert(productDto);
        try {
            productRepository.deleteById(product.getId());//maybe add check if products exists and skip saving if not?
            productRepository.save(product);
            return true;
        }
        catch (Exception e){
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        try{
            productRepository.deleteById(productId);
            return true;
        }
        catch (Exception e){
            return false;
        }


    }

    @Override
    public ProductDto getProduct(String id) {
        Product product = productRepository.findById(Integer.parseInt(id));
        if(product!=null){
            ProductDto productDto = productMapper.convert(product);
            return productDto;
        }
        else{
            return null;
        }
    }
}
