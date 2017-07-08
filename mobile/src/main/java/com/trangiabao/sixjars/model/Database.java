package com.trangiabao.sixjars.model;

import java.util.List;

public class Database {

    private List<Jar> jars;
    private List<RevenueType> revenue_types;
    private List<ExpenditureType> expenditure_types;
    private List<Revenue> revenues;
    private List<Expenditure> expenditure1s;

    public Database() {
    }

    public Database(List<Jar> jars, List<RevenueType> revenue_types, List<ExpenditureType> expenditure_types, List<Revenue> revenues, List<Expenditure> expenditure1s) {
        this.jars = jars;
        this.revenue_types = revenue_types;
        this.expenditure_types = expenditure_types;
        this.revenues = revenues;
        this.expenditure1s = expenditure1s;
    }

    public List<Jar> getJars() {
        return jars;
    }

    public void setJars(List<Jar> jars) {
        this.jars = jars;
    }

    public List<RevenueType> getRevenue_types() {
        return revenue_types;
    }

    public void setRevenue_types(List<RevenueType> revenue_types) {
        this.revenue_types = revenue_types;
    }

    public List<ExpenditureType> getExpenditure_types() {
        return expenditure_types;
    }

    public void setExpenditure_types(List<ExpenditureType> expenditure_types) {
        this.expenditure_types = expenditure_types;
    }

    public List<Revenue> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<Revenue> revenues) {
        this.revenues = revenues;
    }

    public List<Expenditure> getExpenditure1s() {
        return expenditure1s;
    }

    public void setExpenditure1s(List<Expenditure> expenditure1s) {
        this.expenditure1s = expenditure1s;
    }
}
