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

    public ItemActiveViewHolder(View itemView) {
        super(itemView);
        this.itemStatus = itemView.findViewById(R.id.active_item_status);
        this.itemName = itemView.findViewById(R.id.active_item_name);
        this.itemQuantity = itemView.findViewById(R.id.active_item_quantity);
        this.itemAction = itemView.findViewById(R.id.active_item_action);
    }

    public void bind(String itemName, String itemQuantity) {
        this.itemStatus.setChecked(false);
        this.itemName.setText(itemName);
        this.itemQuantity.setText(itemQuantity);
    }


}
