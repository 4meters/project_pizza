package com.pizzaserver.domain.object;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores information about product
 */
public class ProductCSV {
    private String id, type, name, description;
    private Float costS, costM, costL, costU;

    public ProductCSV() {
    }

    public ProductCSV(String[] product) {
        this.id = product[0];
        this.type = product[1];       //value 0 for pizzas, 1 for drinks, 2 for set of products
        this.name = product[2];
        this.description = product[3];
        product[4]=product[4].replace(',','.');
        product[5]=product[5].replace(',','.');
        product[6]=product[6].replace(',','.');
        product[7]=product[7].replace(',','.');
        this.costS = Float.valueOf(product[4]);
        this.costM = Float.valueOf(product[5]);
        this.costL = Float.valueOf(product[6]);
        this.costU = Float.valueOf(product[7]);
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

    public Float getCostS() {
        return costS;
    }

    public Float getCostM() {
        return costM;
    }

    public Float getCostL() {
        return costL;
    }

    public Float getCostU() {
        return costU;
    }

    public Float getCostBySize(String size){
        Map<String, Float> map = new HashMap<>();
        map.put("S",costS);
        map.put("M",costM);
        map.put("L",costL);
        map.put("U",costU);
        return map.get(size);
    }
}
