package com.tutorial.raihan.shoppinglist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by raihanyuwono on 10/28/17.
 */

public class ItemActiveViewHolder extends RecyclerView.ViewHolder {

    CheckBox itemStatus;
    TextView itemName, itemQuantity;
    ImageView itemAction;

    public ItemActiveViewHolder(View itemView, CheckBox itemStatus, TextView itemName, TextView itemQuantity, ImageView itemAction) {
        super(itemView);
        this.itemStatus = itemStatus;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemAction = itemAction;
    }

}
