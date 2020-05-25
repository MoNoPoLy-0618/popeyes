package com.example.popeyes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.popeyes.R;
import com.example.popeyes.utils.FoodItem;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class DescriptionActivity extends AppCompatActivity {

    private int id;
    private FoodItem foodItem;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        if (getIntent() == null || getIntent().getExtras() == null) {
            Toast.makeText(this, "Item NOT FOUND!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        id = getIntent().getIntExtra("id", 0);

        findViews();
        getSingleCategory();
    }

    private void findViews() {
        mImageView = findViewById(R.id.mImageView);
    }

    private void getSingleCategory() {
        AndroidNetworking.get("URL")
                .setPriority(Priority.MEDIUM)
                .setTag("test")
                .addHeaders("accept", "application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        foodItem = new FoodItem.Builder()
                                .setFromJSONObject(response)
                                .build();
                        setView();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void setView() {
        Picasso.with(this)
                .load(foodItem.getImage())
                .into(mImageView);
    }
}
