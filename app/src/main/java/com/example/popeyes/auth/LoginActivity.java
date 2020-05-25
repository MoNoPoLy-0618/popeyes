package com.example.popeyes.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.popeyes.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mNameEdit, mPassEdit;
    private Button mLoginButton;
    private TextView mRegisterView;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_first);
        findViews();
        mRegisterView.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(this);

//        Map<String, String> params = new HashMap<>();
//        params.put("c_username", mUsername);
//        params.put("c_password", mPassword);
    }

    private void findViews() {
        mNameEdit = findViewById(R.id.mNameEdit);
        mPassEdit = findViewById(R.id.mPassEdit);
        mLoginButton = findViewById(R.id.mLoginButton);
        mRegisterView = findViewById(R.id.mRegisterView);
    }


    @Override
    public void onClick(View v) {
        if (v == mRegisterView) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}
