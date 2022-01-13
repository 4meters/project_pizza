package com.pizzaserver.domain.dto;

import java.util.HashMap;
import java.util.Map;

public class ProductDto {

    Integer id;
    Integer type;
    String name;
    String description;
    Float costS;
    Float costM;
    Float costL;
    Float costU;

    public ProductDto(Integer id, Integer type, String name, String description, Float costS, Float costM, Float costL, Float costU) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.costS = costS;
        this.costM = costM;
        this.costL = costL;
        this.costU = costU;
    }
    /*public ProductDto(String id, String type, String name, String description, Float costS, Float costM, Float costL, Float costU) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.costS =costS;
        this.costM = costM;
        this.costL = costL;
        this.costU = costU;
    }*/

    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
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
