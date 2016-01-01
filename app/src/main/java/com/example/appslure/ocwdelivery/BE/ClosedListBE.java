package com.example.appslure.ocwdelivery.BE;

import java.io.Serializable;

/**
 * Created by appslure on 29-12-2015.
 */
public class ClosedListBE implements Serializable {

    private String id,name,mobile,address,status,wallet,hubName,hubAddress,dryClean,paidCloth,freeCloth,token,amountCollected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
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

    public String getDryClean() {
        return dryClean;
    }

    public void setDryClean(String dryClean) {
        this.dryClean = dryClean;
    }

    public String getPaidCloth() {
        return paidCloth;
    }

    public void setPaidCloth(String paidCloth) {
        this.paidCloth = paidCloth;
    }

    public String getFreeCloth() {
        return freeCloth;
    }

    public void setFreeCloth(String freeCloth) {
        this.freeCloth = freeCloth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(String amountCollected) {
        this.amountCollected = amountCollected;
    }
}
