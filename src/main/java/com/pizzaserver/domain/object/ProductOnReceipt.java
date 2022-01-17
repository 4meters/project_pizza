package com.pizzaserver.domain.object;

/**
 * Stores information about product for pdf generating
 */
public class ProductOnReceipt {
    private String name;
    private String size;
    private String cost; //cost total
    private String count;
    private String type;


    public ProductOnReceipt() {
    }

    public ProductOnReceipt(String name, String size, String cost, String count, String type) {
        this.name = name;
        this.size=size;
        this.cost=cost;
        this.count=count;
        this.type=type;
    }


    public String getCount() {
        return count;
    }
    public String getName() {
        return name;
    }
    public String getSize() {
        return size;
    }


    public String getCost(){return cost;}

    public String getType() {
        return type;
    }


}
