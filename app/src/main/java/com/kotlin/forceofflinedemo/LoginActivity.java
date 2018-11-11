package com.kotlin.forceofflinedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private EditText et_username, et_password;
    private Button btn_login;
    private CheckBox cb_remember_password;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initData();
        initListener();
    }

    private void initViews() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        btn_login = findViewById(R.id.btn_login);

        cb_remember_password = findViewById(R.id.cb_remember_password);
    }

    private void initData() {
        pref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        boolean isRemember = pref.getBoolean("remember_password", false);

        if (isRemember) {
            String userName = pref.getString("username", "");
            String password = pref.getString("password", "");
            et_username.setText(userName);
            et_password.setText(password);
            cb_remember_password.setChecked(true);
        }
    }

    private void initListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (userName.equals("test") && password.equals("123456")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    editor = pref.edit();

                    if (cb_remember_password.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("username", userName);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                } else {
                    Toast.makeText(LoginActivity.this, "username or password is invaild!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
