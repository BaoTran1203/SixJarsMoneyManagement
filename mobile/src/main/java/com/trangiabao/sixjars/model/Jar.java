package com.trangiabao.sixjars.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Jar extends RealmObject {

    @PrimaryKey
    private String name;
    private Integer percent;
    private String name_eng;
    private String description_eng;
    private String name_vie;
    private String description_vie;

    public Jar() {
        super();
        this.name = "";
        this.percent = 0;
        this.name_eng = "";
        this.description_eng = "";
        this.name_vie = "";
        this.description_vie = "";
    }

    public Jar(String name, Integer percent, String name_eng, String description_eng, String name_vie, String description_vie) {
        super();
        this.name = name;
        this.percent = percent;
        this.name_eng = name_eng;
        this.description_eng = description_eng;
        this.name_vie = name_vie;
        this.description_vie = description_vie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getName_eng() {
        return name_eng;
    }

    public void setName_eng(String name_eng) {
        this.name_eng = name_eng;
    }

    public String getDescription_eng() {
        return description_eng;
    }

    public void setDescription_eng(String description_eng) {
        this.description_eng = description_eng;
    }

    public String getName_vie() {
        return name_vie;
    }

    public void setName_vie(String name_vie) {
        this.name_vie = name_vie;
    }

    public String getDescription_vie() {
        return description_vie;
    }

    public void setDescription_vie(String description_vie) {
        this.description_vie = description_vie;
    }
}
