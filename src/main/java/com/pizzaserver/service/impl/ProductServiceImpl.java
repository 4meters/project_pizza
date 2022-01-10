package com.pizzaserver.service.impl;

import com.pizzaserver.domain.dto.ProductDto;
import com.pizzaserver.domain.dto.ProductListDto;
import com.pizzaserver.domain.entity.Product;
import com.pizzaserver.domain.mapper.Converter;
import com.pizzaserver.domain.mapper.ProductDtoMapper;
import com.pizzaserver.domain.mapper.ProductMapper;
import com.pizzaserver.domain.repository.ProductListRepository;
import com.pizzaserver.domain.repository.ProductRepository;
import com.pizzaserver.domain.repository.UserRepository;
import com.pizzaserver.service.ProductService;
import org.springframework.stereotype.Service;

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
    private final Converter<Product, ProductDto> productDtoMapper;


    //private final Converter<String, List<Product>> productCSVMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              Converter<ProductListDto, List<com.pizzaserver.domain.entity.Product>> productListMapper,
                              UserRepository userRepository, ProductListRepository productListRepository,
                              ProductMapper productMapper,
                              ProductDtoMapper productDtoMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productListMapper = productListMapper;
        this.productListRepository = productListRepository;
        this.productMapper = productMapper;
        this.productDtoMapper = productDtoMapper;
    }


    @Override
    public ProductListDto getProductList() {
        return productListMapper.convert(productRepository.findAll());
    }

    @Override
    public boolean updateDatabase(String token) {

        //INSTEAD OF CREATING "KARUZELA" USE repository.save() !!!

        /*User user = userRepository.findOneByToken(token);
        if(user!=null){
            if(user.getLogin().equals("admin")){ //TODO split ProductCSV to ProductCSV entity and ProductCSV
                List<Product> productList = new ProductListRepository().getProductList();
                String insertQuery=productCSVMapper.convert(productList);
                productRepository.deleteAll();
                //productRepository.
                //productRepository.insertProduct();

                //write impl
                return false;
            }
            return false;
        }*/ //TODO later
        return false;
        //check admin token
        //read csv with written method
        //convert to sql query
    }

    @Override
    public boolean addProduct(ProductDto productDto) {
        Product product = productDtoMapper.convert(productDto);
        try {
            //productRepository.deleteById(product.getId());
            productRepository.save(product);
            //productRepository.insertProduct(product.getId(), product.getType(), product.getDescription(), product.getName(),
            //        product.getCostS(), product.getCostM(), product.getCostL(), product.getCostU());
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean editProduct(ProductDto productDto) {
        Product product = productDtoMapper.convert(productDto);
        try {
            productRepository.deleteById(product.getId());
            productRepository.save(product);
            //productRepository.updateProduct(product.getId(), product.getType(), product.getDescription(), product.getName(),
            //        product.getCostS(), product.getCostM(), product.getCostL(), product.getCostU());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteProduct(String productId) {
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
        Product product = productRepository.findOneById(id);
        if(product!=null){
            ProductDto productDto = productMapper.convert(product);
            return productDto;
        }
        else{
            return null;
        }
    }
}
