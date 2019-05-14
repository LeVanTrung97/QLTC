package com.example.qlct;

import android.os.AsyncTask;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {

    public Realm realm;

    public RealmController() {
        if (realm == null)
            realm = Realm.getDefaultInstance();

//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//
//        realm = Realm.getInstance(config);

    }

    public void addItem(Item item) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Item realmItem = realm.createObject(Item.class);
        realmItem.setId(item.getId());
        realmItem.setType(item.getType());
        realmItem.setName(item.getName());
        realmItem.setTopic(item.getTopic());
        realmItem.setTime(item.getTime());
        realmItem.setNote(item.getNote());
        realmItem.setAmount(item.getAmount());
        realmItem.setUrl(item.getUrl());
        realm.commitTransaction();
    }

    public void deleteItem(Item item) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Item> results = realm.where(Item.class).equalTo("id", item.getId()).findAll();
//        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void updateItem(Item item) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
//        RealmResults<Story> results = realm.where(Story.class).equalTo("title", story.getTitle()).findAll();
//        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public RealmResults<Item> getItem() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Item> results = realm.where(Item.class).findAll();
        realm.commitTransaction();
        return results;
    }

    public Item getItem(String id) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Item item = realm.where(Item.class).equalTo("id", id).findFirst();
        realm.commitTransaction();
        return item;
    }


}
