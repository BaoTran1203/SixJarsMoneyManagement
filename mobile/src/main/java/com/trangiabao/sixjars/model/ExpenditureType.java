package com.trangiabao.sixjars.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ExpenditureType extends RealmObject {

    @PrimaryKey
    private String id;
    private String type;
    private String description;

    public ExpenditureType() {
        super();
        this.id = "a7500f91-1f6e-412b-8b75-30203801b62b";
        this.type = "Unknown";
        this.description = "Data is generated dynamically";
    }

    public ExpenditureType(String id, String type, String description) {
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
