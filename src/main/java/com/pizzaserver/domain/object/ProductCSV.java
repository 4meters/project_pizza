package com.pizzaserver.domain.object;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores information about product
 */
public class ProductCSV {
    private String id, type, name, description;
    private String costS, costM, costL, costU;

    public ProductCSV() {
    }

    public ProductCSV(String[] product) {
        this.id = product[0];
        this.type = product[1];       //value 0 for pizzas, 1 for drinks, 2 for set of products
        this.name = product[2];
        this.description = product[3];
        this.costS = product[4];
        this.costM = product[5];
        this.costL = product[6];
        this.costU = product[7];
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCostS() {
        return costS;
    }

    public String getCostM() {
        return costM;
    }

    public String getCostL() {
        return costL;
    }

    public String getCostU() {
        return costU;
    }

    public String getCostBySize(String size){
        Map<String, String> map = new HashMap<>();
        map.put("S",costS);
        map.put("M",costM);
        map.put("L",costL);
        map.put("U",costU);
        return map.get(size).replace(',','.');
    }
}
