package com.pizzaserver.domain.model;

import com.pizzaserver.domain.dto.ProductDto;
import com.pizzaserver.domain.mapper.ProductCSVMapper;
import com.pizzaserver.domain.mapper.ProductDtoMapper;
import com.pizzaserver.domain.mapper.ProductListMapper;
import com.pizzaserver.domain.mapper.ProductMapper;
import com.pizzaserver.domain.repository.ProductListRepository;
import com.pizzaserver.domain.repository.ProductRepository;
import com.pizzaserver.domain.repository.UserRepository;
import com.pizzaserver.service.ProductService;
import com.pizzaserver.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class EditProductTest {
    //private final CheckoutCalculate checkoutCalculate = new CheckoutCalculate();
    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;


    @InjectMocks
    ProductService productService = new ProductServiceImpl(productRepository, new ProductListMapper(),userRepository,
            new ProductListRepository(), new ProductMapper(), new ProductCSVMapper(), new ProductDtoMapper());


    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Tag("CheckoutCalculate")
    @Test
    public void editProduct() {
        ProductDto productToEdit = new ProductDto.Builder().id(77).name("TEST").description("Test desc").type(0)
                .costS((float) 33.00).costM((float)44).costL((float)55).costU(null).build();
        productService.editProduct(productToEdit);

        ProductDto productInDb = productService.getProduct("77");
        assertEquals(productToEdit, productInDb);

    }

}