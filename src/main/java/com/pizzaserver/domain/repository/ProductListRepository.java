package com.pizzaserver.domain.repository;

import com.pizzaserver.domain.entity.Product;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Repository
public class ProductListRepository {

    public ProductListRepository() {
    }

    public ArrayList<Product> getProductList() {

        String csvFile = "productList.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        ArrayList<Product> ProductList= new ArrayList <>();
        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                String[] product = line.split(cvsSplitBy);
                ProductList.add(new Product(product));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ProductList;
    }
}
