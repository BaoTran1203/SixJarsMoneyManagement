package com.trangiabao.sixjars.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Database {

    @SerializedName("jars")
    @Expose
    private ArrayList<Jar> jars = new ArrayList<>();
    @SerializedName("revenue_types")
    @Expose
    private ArrayList<RevenueType> revenueTypes = new ArrayList<>();
    @SerializedName("expenditure_types")
    @Expose
    private ArrayList<ExpenditureType> expenditureTypes = new ArrayList<>();
    @SerializedName("wallets")
    @Expose
    private ArrayList<Wallet> wallets = new ArrayList<>();
    @SerializedName("revenues")
    @Expose
    private ArrayList<Revenue> revenues = new ArrayList<>();
    @SerializedName("expenditure1s")
    @Expose
    private ArrayList<Expenditure> expenditures = new ArrayList<>();

    public Database() {
    }

    public Database(ArrayList<Jar> jars,
                    ArrayList<RevenueType> revenueTypes,
                    ArrayList<ExpenditureType> expenditureTypes,
                    ArrayList<Wallet> wallets,
                    ArrayList<Revenue> revenues,
                    ArrayList<Expenditure> expenditures) {
        this.jars = jars;
        this.revenueTypes = revenueTypes;
        this.expenditureTypes = expenditureTypes;
        this.wallets = wallets;
        this.revenues = revenues;
        this.expenditures = expenditures;
    }

    public ArrayList<Jar> getJars() {
        return jars;
    }

    public void setJars(ArrayList<Jar> jars) {
        this.jars = jars;
    }

    public ArrayList<RevenueType> getRevenueTypes() {
        return revenueTypes;
    }

    public void setRevenueTypes(ArrayList<RevenueType> revenueTypes) {
        this.revenueTypes = revenueTypes;
    }

    public ArrayList<ExpenditureType> getExpenditureTypes() {
        return expenditureTypes;
    }

    public void setExpenditureTypes(ArrayList<ExpenditureType> expenditureTypes) {
        this.expenditureTypes = expenditureTypes;
    }

    public ArrayList<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(ArrayList<Wallet> wallets) {
        this.wallets = wallets;
    }

    public ArrayList<Revenue> getRevenues() {
        return revenues;
    }

    public void setRevenues(ArrayList<Revenue> revenues) {
        this.revenues = revenues;
    }

    public ArrayList<Expenditure> getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(ArrayList<Expenditure> expenditures) {
        this.expenditures = expenditures;
    }
}
