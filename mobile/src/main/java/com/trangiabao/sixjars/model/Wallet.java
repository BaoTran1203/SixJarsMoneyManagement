package com.trangiabao.sixjars.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Wallet extends RealmObject {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("amount_all_time")
    @Expose
    private Float amountAllTime;
    @SerializedName("amount_available")
    @Expose
    private Float amountAvailable;
    @SerializedName("amount_expend")
    @Expose
    private Float amountExpend;

    public Wallet() {
    }

    public Wallet(Integer id, String type, Float amountAllTime, Float amountAvailable, Float amountExpend) {
        super();
        this.id = id;
        this.type = type;
        this.amountAllTime = amountAllTime;
        this.amountAvailable = amountAvailable;
        this.amountExpend = amountExpend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getAmountAllTime() {
        return amountAllTime;
    }

    public void setAmountAllTime(Float amountAllTime) {
        this.amountAllTime = amountAllTime;
    }

    public Float getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Float amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Float getAmountExpend() {
        return amountExpend;
    }

    public void setAmountExpend(Float amountExpend) {
        this.amountExpend = amountExpend;
    }
}
