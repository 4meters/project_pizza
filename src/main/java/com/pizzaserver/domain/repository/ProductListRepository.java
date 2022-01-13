package com.pizzaserver.domain.repository;

import com.pizzaserver.domain.object.ProductCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductListRepository.class);

    public ProductListRepository() {
    }

    public ArrayList<ProductCSV> getProductList() {

        String csvFile = "productListUpdate.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        ArrayList<ProductCSV> productCSVList = new ArrayList <>();
        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                String[] product = line.split(cvsSplitBy);
                LOGGER.info(product[4]);
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
