package com.pizzaserver.domain.repository;

import com.pizzaserver.domain.object.ProductCSV;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for reading CSV file with list of products
 * <p>
 * Returns ArrayList of ProductCSV
 */
@Repository
public class ProductListRepository {

    public ProductListRepository() {
    }

    public ArrayList<ProductCSV> getProductList() {

        String csvFile = "productCSVList.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        ArrayList<ProductCSV> productCSVList = new ArrayList <>();
        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                String[] product = line.split(cvsSplitBy);
                productCSVList.add(new ProductCSV(product));
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
        return productCSVList;
    }
}
