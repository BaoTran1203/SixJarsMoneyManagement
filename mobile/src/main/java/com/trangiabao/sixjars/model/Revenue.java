package com.trangiabao.sixjars.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Revenue extends RealmObject {

    @PrimaryKey
    private String id;
    private Date date;
    private Double amount;
    private RevenueType revenueType;
    private String detail;

    public Revenue() {
        super();
        this.id = "";
        this.date = new Date();
        this.amount = 0.0;
        this.revenueType = new RevenueType();
        this.detail = "";
    }

    public Revenue(String id, Date date, Double amount, RevenueType revenueType, String detail) {
        super();
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.revenueType = revenueType;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public RevenueType getRevenueType() {
        return revenueType;
    }

    public void setRevenueType(RevenueType revenueType) {
        this.revenueType = revenueType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
