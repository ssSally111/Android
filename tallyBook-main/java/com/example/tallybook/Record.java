package com.example.tallybook;

public class Record {
    private String detail;
    private double content;
    private Type type;
    private String date;
    private final String key;

    public enum Type {
        spending, income
    }

    public Record(Type type, double content, String detail, String date,String key) {
        this.detail = detail;
        this.content = content;
        this.date = date;
        this.type = type;
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getContent() {
        return content;
    }

    public void setContent(double content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }
}
