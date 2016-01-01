package com.example.appslure.ocwdelivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appslure.ocwdelivery.Configuration.Util;
import com.example.appslure.ocwdelivery.Constant.Constant;

public class Splash extends AppCompatActivity {

    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        userId= Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_USER_ID);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (userId == null) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 1000);
    }
}
