package com.pizzaserver.controller;

import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.dto.CheckoutDto;
import com.pizzaserver.service.CheckoutService;
import com.pizzaserver.service.impl.CheckoutServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class CheckoutApiController {

    private CheckoutService checkoutService;
    private final Logger LOGGER= LoggerFactory.getLogger(CheckoutApiController.class);

    @RequestMapping(value="/checkout", method= RequestMethod.POST)
    public ResponseEntity<CheckoutCalculatedDto> orderCheckout(@RequestBody CheckoutDto checkoutDto){
        LOGGER.info("### Method orderCheckout!");
        LOGGER.info("### orderList: {}",
                checkoutDto.getOrderList());
        checkoutService=new CheckoutServiceImpl();
        CheckoutCalculatedDto checkoutCalculatedDto=checkoutService.getCartTotalCost(checkoutDto.getOrderList());

        return new ResponseEntity<>(checkoutCalculatedDto, HttpStatus.OK);
    }

}
