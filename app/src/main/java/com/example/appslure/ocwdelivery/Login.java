package com.example.appslure.ocwdelivery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appslure.ocwdelivery.BL.LoginBL;
import com.example.appslure.ocwdelivery.Configuration.Util;
import com.example.appslure.ocwdelivery.Constant.Constant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText etUserName,etPassword;
    String strUserName,strPassword;
    Button btnDone;

    LoginBL objLoginBL;

    ProgressDialog progressDialog;

    GoogleCloudMessaging gcmObj;

    Context applicationContext;
    String gcmID;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

    }

    private void initialize(){
        etUserName= (EditText) findViewById(R.id.login_username);
        etPassword= (EditText) findViewById(R.id.login_password);
        btnDone= (Button) findViewById(R.id.login_done);

        btnDone.setOnClickListener(this);
        progressDialog=new ProgressDialog(Login.this);

        objLoginBL=new LoginBL();

        applicationContext=getApplicationContext();

        gcmID=Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_GCM_ID);

        if(gcmID==null){
            if (checkPlayServices()) {
                registerInBackground();
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_done:

                if(validate())
                    if(Util.isInternetConnection(Login.this))
                        new CheckLogin().execute(strUserName,strPassword,gcmID);
                    else
                        Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        // When Play services not found in device
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                // Show Error dialog to install Play services
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {

                finish();
            }
            return false;
        } else {

        }
        return true;
    }

    /*--------------------------GCM KEY ----------------------------------------*/
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcmObj == null) {
                        gcmObj = GoogleCloudMessaging
                                .getInstance(applicationContext);
                    }
                    gcmID = gcmObj
                            .register(Constant.GOOGLE_PROJ_ID);
                    msg = "Registration ID :" + gcmID;

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                if (!TextUtils.isEmpty(gcmID)) {
                    //Toast.makeText(getApplicationContext(),"GSM"+gcmID,Toast.LENGTH_SHORT).show();
                    Util.setSharedPrefrenceValue(applicationContext,Constant.PREFS_NAME,Constant.SP_GCM_ID,gcmID);
                } else {
                    /*Toast.makeText(
                       applicationContext,
                            "Reg ID Creation Failed.\n\nEither you haven't enabled Internet or GCM server is busy right now. Make sure you enabled Internet and try registering again after some time."
                                    + msg, Toast.LENGTH_LONG).show();*/
                }
            }
        }.execute(null, null, null);
    }

    private boolean validate(){
        boolean flag=true;

        strUserName=etUserName.getText().toString();
        strPassword=etPassword.getText().toString();

        if(strUserName.trim().length()==0){
            etUserName.setError("Required");
            flag=false;
        }

        if(strPassword.trim().length()==0){
            etPassword.setError("Required");
            flag=false;
        }

        return flag;
    }

    private class CheckLogin extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objLoginBL.loginRecord(params[0],params[1],params[2],getApplicationContext());
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                if(Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(s)){
                    startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                }
                else
                    Toast.makeText(getApplicationContext(),"Invalid Mobile or Password",Toast.LENGTH_SHORT).show();
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }

        }
    }

    @Override
    public void onBackPressed() {

    }
}
