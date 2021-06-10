package com.example.fleemarket;

public class ProductInfo {
    private String pName;
    private String pPrice;
    private String pAddress;


    public ProductInfo(String pName, String pPrice, String pAddress){
        this.pName = pName;
        this.pPrice = pPrice;
        this.pAddress = pAddress;
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
    public String getpAddress(){
        return this.pAddress;
    }
    public void setpAddress(String pAddress){
        this.pAddress = pAddress;
    }

}

