package com.example.appslure.ocwdelivery.BE;

import java.io.Serializable;

/**
 * Created by appslure on 22-12-2015.
 */
public class HomeScreenBE implements Serializable{

    private String id;
    private String name;
    private String mobile;
    private String address;
    private String orderType;
    private String dryClean;
    private String paidCloth;
    private String freeCloth;
    private String wallet;
    private String hubName;
    private String hubAddress;
    private String token;
    private String pickup;



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHubName() {
        return hubName;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }

    public String getHubAddress() {
        return hubAddress;
    }

    public void setHubAddress(String hubAddress) {
        this.hubAddress = hubAddress;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getFreeCloth() {
        return freeCloth;
    }

    public void setFreeCloth(String freeCloth) {
        this.freeCloth = freeCloth;
    }

    public String getPaidCloth() {
        return paidCloth;
    }

    public void setPaidCloth(String paidCloth) {
        this.paidCloth = paidCloth;
    }

    public String getDryClean() {
        return dryClean;
    }

    public void setDryClean(String dryClean) {
        this.dryClean = dryClean;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
