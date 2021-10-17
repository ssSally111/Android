package com.example.fragment;

public class Hobbies {
    String tag, name, type, recommend, synopsis;
    int img;

    public Hobbies() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img=img;
    }

    public Hobbies(String tag, String name, String type, String recommend, String synopsis, int img) {
        this.tag = tag;
        this.name = name;
        this.type = type;
        this.recommend = recommend;
        this.synopsis = synopsis;
        this.img =img;
    }
}
