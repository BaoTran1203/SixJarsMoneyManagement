package com.trangiabao.sixjars.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RevenueType extends RealmObject {

    @PrimaryKey
    private String id;
    private String type;
    private String description;

    public RevenueType() {
        super();
        this.id = "9c528365-6eab-494c-83d3-24ef5ee6df10";
        this.type = "Unknown";
        this.description = "Data is generated dynamically";
    }

    public RevenueType(String id, String type, String description) {
        super();
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
