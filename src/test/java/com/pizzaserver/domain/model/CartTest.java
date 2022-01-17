package com.pizzaserver.domain.model;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.dto.CheckoutDto;
import com.pizzaserver.domain.mapper.ProductCSVMapper;
import com.pizzaserver.domain.mapper.ProductDtoMapper;
import com.pizzaserver.domain.mapper.ProductListMapper;
import com.pizzaserver.domain.mapper.ProductMapper;
import com.pizzaserver.domain.repository.ProductListRepository;
import com.pizzaserver.domain.repository.ProductRepository;
import com.pizzaserver.domain.repository.UserRepository;
import com.pizzaserver.service.CheckoutService;
import com.pizzaserver.service.ProductService;
import com.pizzaserver.service.impl.CheckoutServiceImpl;
import com.pizzaserver.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CartTest {
    //private final CheckoutCalculate checkoutCalculate = new CheckoutCalculate();
    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;


    ProductService productService;

    CheckoutService checkoutService;

    //private String orderList;
    //private String cost;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ProductService productService = new ProductServiceImpl(productRepository,
                new ProductListMapper(),userRepository, new ProductListRepository(), new ProductMapper(),
                new ProductCSVMapper(), new ProductDtoMapper());
        CheckoutService checkoutService = new CheckoutServiceImpl(productService);
    }

    @Tag("CheckoutCalculate")
    @ParameterizedTest
    @CsvSource(value={
            "(2,S,1) : 21.00",
    }, delimiter = ':')
    public void checkCartCostCalculation(String orderList, String cost) {
        CheckoutCalculatedDto checkoutCalculatedDto = checkoutService.getCartTotalCost(
                new CheckoutDto.Builder().orderList(orderList).build());

       /* CheckoutMapper checkoutMapper = new CheckoutMapper();
        ArrayList<OrderListProduct> orderListL = checkoutMapper.convert(orderList);
        CheckoutCalculate checkoutCalculate = new CheckoutCalculate(orderListL, productService);
        assertEquals(Float.valueOf(cost), Float.valueOf(checkoutCalculate.getCheckoutCalculatedDto().getCost()));*/
        assertEquals(Float.valueOf(cost), Float.valueOf(checkoutCalculatedDto.getCost()));

    }

}