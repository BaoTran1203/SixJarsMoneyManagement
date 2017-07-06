package com.trangiabao.sixjars.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Expenditure extends RealmObject {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("jar")
    @Expose
    private Jar jar;
    @SerializedName("type")
    @Expose
    private ExpenditureType type;
    @SerializedName("detail")
    @Expose
    private String detail;

    public Expenditure() {
    }

    public Expenditure(Integer id, String datetime, Double amount, Jar jar, ExpenditureType type, String detail) {
        this.id = id;
        this.datetime = datetime;
        this.amount = amount;
        this.jar = jar;
        this.type = type;
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Jar getJar() {
        return jar;
    }

    public void setJar(Jar jar) {
        this.jar = jar;
    }

    public ExpenditureType getType() {
        return type;
    }

    public void setType(ExpenditureType type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
