package com.tutorial.raihan.shoppinglist;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by raihanyuwono on 10/28/17.
 */

public class ItemInactiveViewHolder extends RecyclerView.ViewHolder {

    CheckBox itemStatus;
    TextView itemName;
    ImageView itemAction;

    public ItemInactiveViewHolder(View itemView) {
        super(itemView);
        this.itemStatus = itemView.findViewById(R.id.inactive_item_status);
        this.itemName = itemView.findViewById(R.id.inactive_item_name);
        this.itemAction = itemView.findViewById(R.id.inactive_item_action);
    }

    public void bind(String itemName){
        this.itemName.setText(itemName);
        this.itemName.setPaintFlags(this.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

}
