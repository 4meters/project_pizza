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
import javafx.application.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment =  SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class CheckoutApiTest {

    //private final CheckoutCalculate checkoutCalculate = new CheckoutCalculate();
    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService = new ProductServiceImpl(productRepository,
            new ProductListMapper(),userRepository, new ProductListRepository(), new ProductMapper(),
            new ProductCSVMapper(), new ProductDtoMapper());;

    @InjectMocks
    CheckoutService checkoutService = new CheckoutServiceImpl(productService);

    //private String orderList;
    //private String cost;

    /*@Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ProductService productService = new ProductServiceImpl(productRepository,
                new ProductListMapper(),userRepository, new ProductListRepository(), new ProductMapper(),
                new ProductCSVMapper(), new ProductDtoMapper());
        CheckoutService checkoutService = new CheckoutServiceImpl(productService);
    }*/
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Tag("CheckoutCalculate")
    @ParameterizedTest
    @CsvSource(value={
            "(20,S,1) : 21.00",
    }, delimiter = ':')
    public void checkCartCostCalculation(String orderList, String cost) {
        CheckoutDto checkoutDto = new CheckoutDto.Builder().orderList(orderList).build();
        CheckoutCalculatedDto checkoutCalculatedDto = checkoutService.getCartTotalCost(
                checkoutDto);
        //Mockito.when(productRepository.findAll()).thenReturn(getProducts());
   /* CheckoutMapper checkoutMapper = new CheckoutMapper();
    ArrayList<OrderListProduct> orderListL = checkoutMapper.convert(orderList);
    CheckoutCalculate checkoutCalculate = new CheckoutCalculate(orderListL, productService);
    assertEquals(Float.valueOf(cost), Float.valueOf(checkoutCalculate.getCheckoutCalculatedDto().getCost()));*/
        System.out.println(checkoutCalculatedDto.getCost());
        System.out.println("  87  "+productService.getProduct("4"));
        assertEquals(Float.valueOf(cost), Float.valueOf(checkoutCalculatedDto.getCost()));
        //assertEquals(21.00, 21.00);

    }
}
