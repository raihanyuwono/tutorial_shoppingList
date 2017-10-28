package com.tutorial.raihan.shoppinglist;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    private Realm dbRealm;

    private List<ShoppingItem> shoppingItemList;

    private RecyclerView shoppingItems;
    private RecyclerView.Adapter shoppingItemsAdapter = new RecyclerView.Adapter() {

        private final int ITEM_ACTIVE = 1;
        private final int ITEM_INACTIVE = 2;
        private final int SUBHEADER = 3;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v;

            switch (viewType) {
                case ITEM_ACTIVE:
                    v = getLayoutInflater().inflate(R.layout.item_active, parent, false);
                    return new ItemActiveViewHolder(v,
                            (CheckBox) v.findViewById(R.id.item_status),
                            (TextView) v.findViewById(R.id.item_name),
                            (TextView) v.findViewById(R.id.item_quantity),
                            (ImageView) v.findViewById(R.id.item_action));
                case ITEM_INACTIVE:
                    v = getLayoutInflater().inflate(R.layout.item_inactive, parent, false);
                    return new ItemInactiveViewHolder(v,
                            (CheckBox) v.findViewById(R.id.item_status),
                            (TextView) v.findViewById(R.id.item_name),
                            (ImageView) v.findViewById(R.id.item_action));
                case SUBHEADER:
                    v = getLayoutInflater().inflate(R.layout.subheader, parent, false);
                    return new SubheaderViewHolder(v);
                default:
                    return null;
            }

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ShoppingItem currentItem = shoppingItemList.get(position);
            if (currentItem.getTimeStamp() == -1) return;
            else if (currentItem.isCompleted()) {
                ItemInactiveViewHolder newHolder = (ItemInactiveViewHolder) holder;
                newHolder.itemName.setText(currentItem.getName());
                newHolder.itemName.setPaintFlags(newHolder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                ItemActiveViewHolder newHolder = (ItemActiveViewHolder) holder;
                newHolder.itemName.setText(currentItem.getName());
                newHolder.itemQuantity.setText(currentItem.getQuantity());
                newHolder.itemStatus.setChecked(false);
            }
        }

        @Override
        public int getItemCount() {
            return shoppingItemList.size();
        }

        @Override
        public int getItemViewType(int position) {
            ShoppingItem currentItem = shoppingItemList.get(position);
            if (currentItem.getTimeStamp() == -1) return SUBHEADER;
            else if (currentItem.isCompleted()) return ITEM_INACTIVE;
            else return ITEM_ACTIVE;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(configuration);
        dbRealm = Realm.getDefaultInstance();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                intent.putExtra("TITLE", "Add item");
                startActivityForResult(intent, 1);
            }
        });

        shoppingItems = findViewById(R.id.shopping_items);
        shoppingItems.setLayoutManager(new LinearLayoutManager(this));

        initializedShoppingItemList();
        shoppingItems.setAdapter(shoppingItemsAdapter);
    }

    private void initializedShoppingItemList() {
        shoppingItemList = new ArrayList<>();
        RealmResults<ShoppingItem> activeItemResult = dbRealm.where(ShoppingItem.class)
                .equalTo("completed", false)
                .findAllSorted("timeStamp", Sort.DESCENDING);
        RealmResults<ShoppingItem> inactiveItemResult = dbRealm.where(ShoppingItem.class)
                .equalTo("completed", true)
                .findAllSorted("timeStamp", Sort.DESCENDING);

        ShoppingItem subheader = new ShoppingItem();
        subheader.setTimeStamp(-1);

        for (ShoppingItem item : activeItemResult) shoppingItemList.add(item);
        shoppingItemList.add(subheader);
        for (ShoppingItem item : inactiveItemResult) shoppingItemList.add(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            initializedShoppingItemList();
            shoppingItemsAdapter.notifyDataSetChanged();
        }
    }
}
