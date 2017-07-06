package com.trangiabao.sixjars.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Jar extends RealmObject {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("percent")
    @Expose
    private Integer percent;
    @SerializedName("name_eng")
    @Expose
    private String nameEng;
    @SerializedName("description_eng")
    @Expose
    private String descriptionEng;
    @SerializedName("name_vie")
    @Expose
    private String nameVie;
    @SerializedName("description_vie")
    @Expose
    private String descriptionVie;

    public Jar() {
    }

    public Jar(Integer id, String name, Integer percent, String nameEng, String descriptionEng, String nameVie, String descriptionVie) {
        super();
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.nameEng = nameEng;
        this.descriptionEng = descriptionEng;
        this.nameVie = nameVie;
        this.descriptionVie = descriptionVie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public String getNameVie() {
        return nameVie;
    }

    public void setNameVie(String nameVie) {
        this.nameVie = nameVie;
    }

    public String getDescriptionVie() {
        return descriptionVie;
    }

    public void setDescriptionVie(String descriptionVie) {
        this.descriptionVie = descriptionVie;
    }
}
