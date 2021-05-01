package com.example.pizzaserver.controller;

import com.example.pizzaserver.domain.dto.ProductListDto;
import com.example.pizzaserver.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/api")
public class ProductListApiController {
    private final ProductListService productListService;

    @Autowired
    public ProductListApiController(ProductListService productListService) {
        this.productListService = productListService;
    }

    @CrossOrigin
    @GetMapping(value="/get-productlist")
    public ResponseEntity<ProductListDto> getProductList(){
        ProductListDto productListDto = productListService.getProductList();
        return new ResponseEntity<>(productListDto, HttpStatus.OK);
    }


}
