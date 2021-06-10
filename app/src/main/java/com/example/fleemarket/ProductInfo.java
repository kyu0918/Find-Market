package com.example.fleemarket;

public class ProductInfo {
    private String pName;
    private String pPrice;


    public ProductInfo(String pName, String pPrice){
        this.pName = pName;
        this.pPrice = pPrice;
    }


    public String getpName(){
        return this.pName;
    }
    public void setpName(String pName){
        this.pName = pName;
    }
    public String getpPrice(){
        return this.pPrice;
    }
    public void setpPrice(String pPrice){ this.pPrice = pPrice; }

}

