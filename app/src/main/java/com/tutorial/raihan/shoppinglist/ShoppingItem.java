package com.tutorial.raihan.shoppinglist;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by raihanyuwono on 10/28/17.
 */

public class ShoppingItem extends RealmObject {

    private String name;
    private String quantity;
    private boolean completed;

    @PrimaryKey
    private String id;

    private long timeStamp;

    public ShoppingItem(String name, String quantity, boolean completed, String id, long timeStamp) {
        this.name = name;
        this.quantity = quantity;
        this.completed = completed;
        this.id = id;
        this.timeStamp = timeStamp;
    }

    public ShoppingItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
