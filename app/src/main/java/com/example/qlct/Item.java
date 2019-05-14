package com.example.qlct;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;

public class Item extends RealmObject implements Serializable {

    private int id;
    private int type; //type=1: Item; type=2: Expensis
    private String name;
    private String topic;
    private Date time;
    private String note;
    private String amount;
    private String url;

    public Item() {
    }

    public Item(int id, int type, String name, String topic, Date time, String note, String amount, String url) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.topic = topic;
        this.time = time;
        this.note = note;
        this.amount = amount;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                "type='" + type + '\'' +
                "name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", time=" + time +
                ", note='" + note + '\'' +
                ", amount='" + amount + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
