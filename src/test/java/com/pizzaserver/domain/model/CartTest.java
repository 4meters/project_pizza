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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CartTest {
    //private final CheckoutCalculate checkoutCalculate = new CheckoutCalculate();
    @InjectMocks
    UserRepository userRepository;

    @InjectMocks
    ProductRepository productRepository;

    ProductService productService = new ProductServiceImpl(productRepository,
            new ProductListMapper(),userRepository, new ProductListRepository(), new ProductMapper(),
            new ProductCSVMapper(), new ProductDtoMapper());
    CheckoutService checkoutService = new CheckoutServiceImpl(productService);

    private String orderList;
    private Float cost;

    @Tag("CheckoutCalculate")
    @ParameterizedTest
    @CsvSource(value={
            "(2,S,1) : 21.00",
    }, delimiter = ':')
    public void checkCartCostCalculation(String orderList, String cost) {
        CheckoutCalculatedDto checkoutCalculatedDto = checkoutService.getCartTotalCost(new CheckoutDto(orderList));

       /* CheckoutMapper checkoutMapper = new CheckoutMapper();
        ArrayList<OrderListProduct> orderListL = checkoutMapper.convert(orderList);
        CheckoutCalculate checkoutCalculate = new CheckoutCalculate(orderListL, productService);
        assertEquals(Float.valueOf(cost), Float.valueOf(checkoutCalculate.getCheckoutCalculatedDto().getCost()));*/
        assertEquals(Float.valueOf(cost), Float.valueOf(checkoutCalculatedDto.getCost()));

    }

}