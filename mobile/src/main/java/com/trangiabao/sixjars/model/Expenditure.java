package com.trangiabao.sixjars.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Expenditure extends RealmObject {

    @PrimaryKey
    private String id;
    private Date date;
    private Double amount;
    private Jar jar;
    private ExpenditureType expenditureType;
    private String detail;

    public Expenditure() {
        super();
        this.id = "";
        this.date = new Date();
        this.amount = 0.0;
        this.jar = new Jar();
        this.expenditureType = new ExpenditureType();
        this.detail = "";
    }

    public Expenditure(String id, Date date, Double amount, Jar jar, ExpenditureType expenditureType, String detail) {
        super();
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.jar = jar;
        this.expenditureType = expenditureType;
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

    public Jar getJar() {
        return jar;
    }

    public void setJar(Jar jar) {
        this.jar = jar;
    }

    public ExpenditureType getExpenditureType() {
        return expenditureType;
    }

    public void setExpenditureType(ExpenditureType expenditureType) {
        this.expenditureType = expenditureType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
