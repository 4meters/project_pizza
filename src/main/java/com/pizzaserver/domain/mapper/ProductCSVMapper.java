package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.entity.Product;
import com.pizzaserver.domain.object.ProductCSV;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCSVMapper implements Converter<List<Product>, List<ProductCSV>>{
    @Override
    public List<Product> convert(List<ProductCSV> from) {
        List<Product> productList = new ArrayList<>();
        for(ProductCSV productCSV : from){
            Product product = Product.Builder.aProduct()
                    .withId(Integer.parseInt(productCSV.getId()))
                    .withName(productCSV.getName())
                    .withType(Integer.parseInt(productCSV.getType()))
                    .description(productCSV.getDescription())
                    .withCostS(productCSV.getCostS())
                    .withCostM(productCSV.getCostM())
                    .withCostL(productCSV.getCostL())
                    .withCostU(productCSV.getCostU())
                    .build();
            productList.add(product);
        }
        return productList; //test - reading csv and creating query - no execution
    }
}
