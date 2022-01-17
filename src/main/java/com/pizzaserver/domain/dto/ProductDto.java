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
    
    private ProductDto(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.name = builder.name;
        this.description = builder.description;
        this.costS = builder.costS;
        this.costM = builder.costM;
        this.costL = builder.costL;
        this.costU = builder.costU;
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


    public static final class Builder {
        Integer id;
        Integer type;
        String name;
        String description;
        Float costS;
        Float costM;
        Float costL;
        Float costU;

        public Builder() {
        }

        public static Builder aProductDto() {
            return new Builder();
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder type(Integer type) {
            this.type = type;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder costS(Float costS) {
            this.costS = costS;
            return this;
        }

        public Builder costM(Float costM) {
            this.costM = costM;
            return this;
        }

        public Builder costL(Float costL) {
            this.costL = costL;
            return this;
        }

        public Builder costU(Float costU) {
            this.costU = costU;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(this);
        }
    }
}
