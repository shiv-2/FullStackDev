package com.gentech.basicPrograms.products;

public class Product {
    private String pName;
    private String price;
    public Product(){

    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public Product(String pName,String price){
        super();
        this.pName=pName;
        this.price=price;
    }
}
