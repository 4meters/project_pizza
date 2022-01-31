package com.pizzaserver.service.impl;

import com.pizzaserver.domain.dto.ProductDto;
import com.pizzaserver.domain.mapper.ProductCSVMapper;
import com.pizzaserver.domain.mapper.ProductDtoMapper;
import com.pizzaserver.domain.mapper.ProductListMapper;
import com.pizzaserver.domain.mapper.ProductMapper;
import com.pizzaserver.domain.repository.ProductListRepository;
import com.pizzaserver.domain.repository.ProductRepository;
import com.pizzaserver.domain.repository.UserRepository;
import com.pizzaserver.service.ProductService;
import javafx.application.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment =  SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
class ProductServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;


    @InjectMocks
    ProductService productService = new ProductServiceImpl(productRepository, new ProductListMapper(),userRepository,
            new ProductListRepository(), new ProductMapper(), new ProductCSVMapper(), new ProductDtoMapper());


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void addProduct() {
        productService.addProduct(new ProductDto.Builder()
                .id(321)
                .type(2)
                .name("Test")
                .description("")
                .costS((float) 18.9)
                .costM((float) 28.9)
                .costL((float) 38.9)
                .costU(null).build());
    }
}