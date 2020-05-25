package com.example.popeyes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.popeyes.adapter.FoodMenuAdapter;
import com.example.popeyes.auth.LoginActivity;
import com.example.popeyes.R;
import com.example.popeyes.routes.Constant;
import com.example.popeyes.utils.FoodItem;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private FoodMenuAdapter mAdapter;
    private ArrayList<FoodItem> mList = new ArrayList<>();
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");
        findViews();
        setAdapter();
        setSupportActionBar(mToolbar);
        mNavigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        sda();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void findViews() {
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mNavigationView = findViewById(R.id.mNavigationView);
        mToolbar = findViewById(R.id.mToolbar);
        mDrawerLayout = findViewById(R.id.mDrawerLayout);

    }

    private void setAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new FoodMenuAdapter(mList, new FoodMenuAdapter.OnClicked() {
            @Override
            public void onClickListener(int id) {
                launchDesc(id);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void sda() {
        AndroidNetworking.get(Constant.SELECT)
                .setPriority(Priority.LOW)
                .setTag("popeyes")
                .addHeaders("accept", "application/json")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            mList.add(new FoodItem
                                    .Builder()
                                    .setFromJSONObject(response.optJSONObject(i))
                                    .build()
                            );
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.w("MO_SDA", anError.getErrorBody());
                    }
                });
    }

    private void launchDesc(int id) {
        Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_drawer, menu);
        return true;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.Login) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        return true;
    }
}



