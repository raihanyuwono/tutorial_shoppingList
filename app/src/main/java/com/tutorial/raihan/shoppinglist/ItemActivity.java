package com.tutorial.raihan.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.UUID;

import io.realm.Realm;

public class ItemActivity extends AppCompatActivity {

    private EditText inputItemName, inputItemQuantity;
    private Realm dbRealm;

    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        if (getIntent().hasExtra("TITLE")) {
            setTitle(getIntent().getStringExtra("TITLE"));
        }

        inputItemName = findViewById(R.id.input_item_name);
        inputItemQuantity = findViewById(R.id.input_item_quantity);

        dbRealm = Realm.getDefaultInstance();

        if(getIntent().hasExtra("ITEM_NAME")){
            inputItemName.setText(getIntent().getStringExtra("ITEM_NAME"));
            inputItemQuantity.setText(getIntent().getStringExtra("ITEM_QUANTITY"));
            editMode = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_save && !editMode){
            dbRealm.beginTransaction();

            ShoppingItem shoppingItem = dbRealm.createObject(ShoppingItem.class, UUID.randomUUID().toString());
            shoppingItem.setName(inputItemName.getText().toString());
            shoppingItem.setQuantity(inputItemQuantity.getText().toString());
            shoppingItem.setCompleted(false);
            shoppingItem.setTimeStamp(System.currentTimeMillis());
            dbRealm.commitTransaction();

            setResult(RESULT_OK);
            finish();
        }

        if(item.getItemId() == R.id.action_save && editMode){
            dbRealm.beginTransaction();

            ShoppingItem shoppingItem = dbRealm.where(ShoppingItem.class)
                    .equalTo("id", getIntent().getStringExtra("ITEM_ID")).findFirst();
            shoppingItem.setName(inputItemName.getText().toString());
            shoppingItem.setQuantity(inputItemQuantity.getText().toString());
            shoppingItem.setTimeStamp(System.currentTimeMillis());
            dbRealm.commitTransaction();

            setResult(RESULT_OK);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
