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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:

                ShoppingItem shoppingItem = new ShoppingItem(inputItemName.getText().toString(),
                        inputItemQuantity.getText().toString(),
                        false,
                        UUID.randomUUID().toString(),
                        System.currentTimeMillis());

                dbRealm.beginTransaction();
                dbRealm.copyToRealm(shoppingItem);
                /*ShoppingItem shoppingItem = dbRealm.createObject(ShoppingItem.class);
                shoppingItem.setName(inputItemName.getText().toString());
                shoppingItem.setQuantity(inputItemQuantity.getText().toString());
                shoppingItem.setCompleted(false);
                shoppingItem.setId(UUID.randomUUID().toString());
                shoppingItem.setTimeStamp(System.currentTimeMillis());
                dbRealm.commitTransaction();*/

                setResult(RESULT_OK);
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
