package com.example.popeyes.auth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.popeyes.R;
import com.example.popeyes.routes.Constant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mLastName,
            mFirstName,
            mRegister,
            mPhoneReg,
            mAddressReg,
            mUsernameReg,
            mPasswordReg;

    private Button mButtonRegister;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar mToolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViews();

        mProgressDialog = new ProgressDialog(this);
        mButtonRegister.setOnClickListener(this);
    }

    private void findViews() {
        mLastName = findViewById(R.id.lastNameEditText);
        mFirstName = findViewById(R.id.mFirstNameEditText);
        mRegister = findViewById(R.id.mRegisterEditText);
        mPhoneReg = findViewById(R.id.mPhoneNumberEditText);
        mAddressReg = findViewById(R.id.mAddressEditText);
        mUsernameReg = findViewById(R.id.mUsernameEditText);
        mPasswordReg = findViewById(R.id.mPasswordEditText);

        mButtonRegister = findViewById(R.id.mButtonRegister);
    }

    @Override
    public void onClick(View view) {
        if (view == mButtonRegister) {
            UserRegister();
        }
    }

    private void clear() {
        mLastName.setText("");
        mFirstName.setText("");
        mRegister.setText("");
        mPhoneReg.setText("");
        mAddressReg.setText("");
        mUsernameReg.setText("");
        mPasswordReg.setText("");
        mPasswordReg.setText("");
    }

    private void UserRegister() {
        Map<String, String> params = new HashMap<>();
        params.put("c_lastname", mLastName.getText().toString());
        params.put("c_firstname", mFirstName.getText().toString());
        params.put("c_register", mRegister.getText().toString());
        params.put("c_phone", mPhoneReg.getText().toString());
        params.put("c_address", mAddressReg.getText().toString());
        params.put("c_username", mUsernameReg.getText().toString());
        params.put("c_password", mPasswordReg.getText().toString());

        mProgressDialog.setMessage("Loading");
        mProgressDialog.show();

        AndroidNetworking.post(Constant.CREATE)
                .addBodyParameter(params)
                .setTag("popeyes")
                .addHeaders("accept", "application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        clear();
                        Log.w("MO_SDA", response.toString());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.w("MO_SDA", anError.toString());
                    }
                });
    }
}


