package com.pizzaserver.domain.mapper;

import com.pizzaserver.domain.entity.Product;

import java.util.List;

public class ProductCSVMapper implements Converter<String, List<Product>>{
    @Override
    public String convert(List<Product> from) {
        String insertQuery="";
        String insertLine="";
        for(Product product : from){
            insertLine="INSERT INTO pizza.products_combined (id, type, description, costS, costM, costL, costU)\" +\n" +
                    "            \" values ("+ product.getId()+","+ product.getType()+","+ product.getDescription()+","
                    + product.getCostS()+","+ product.getCostM()+","+ product.getCostL()+","+ product.getCostU()+")";
        }
        insertQuery+=insertLine;
        return insertQuery; //test - reading csv and creating query - no execution
    }
}
