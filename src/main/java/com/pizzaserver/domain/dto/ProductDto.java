package com.pizzaserver.domain.dto;

import java.util.HashMap;
import java.util.Map;

public class ProductDto {

    String id;
    String type;
    String name;
    String description;
    String costS;
    String costM;
    String costL;
    String costU;

    public ProductDto(String type, String name, String description, String costS, String costM, String costL, String costU) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.costS = costS;
        this.costM = costM;
        this.costL = costL;
        this.costU = costU;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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
