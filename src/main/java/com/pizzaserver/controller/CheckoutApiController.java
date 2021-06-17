package com.pizzaserver.controller;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.dto.CheckoutDto;
import com.pizzaserver.service.CheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class CheckoutApiController {

    private CheckoutService checkoutService;
    private final Logger LOGGER= LoggerFactory.getLogger(CheckoutApiController.class);

    @Autowired
    public CheckoutApiController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    /**
     * Api for calculating order cost
     * @param checkoutDto orderlist
     * @return ordercost, ordercost after discounts, discount type, discount productlist
     */
    @PostMapping(value="/checkout")
    public ResponseEntity<CheckoutCalculatedDto> orderCheckout(@RequestBody CheckoutDto checkoutDto){
        LOGGER.info("### Method orderCheckout!");
        LOGGER.info("### orderList: {}",
                checkoutDto.getOrderList());
        CheckoutCalculatedDto checkoutCalculatedDto=checkoutService.getCartTotalCost(checkoutDto);

        return new ResponseEntity<>(checkoutCalculatedDto, HttpStatus.OK);
    }

}
