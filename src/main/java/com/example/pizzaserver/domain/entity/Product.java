package com.example.pizzaserver.domain.entity;

public class Product {
    private String id, type, name, description;
    private String costS, costM, costL, costU;

    public Product() {
    }

    public Product(String[] product) {
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
}
