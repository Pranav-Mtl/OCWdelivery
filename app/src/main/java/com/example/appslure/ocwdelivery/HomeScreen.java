package com.example.appslure.ocwdelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appslure.ocwdelivery.Adapter.HomeScreenAdapter;
import com.example.appslure.ocwdelivery.BE.HomeScreenBE;
import com.example.appslure.ocwdelivery.Configuration.RecyclerItemClickListener;
import com.example.appslure.ocwdelivery.Configuration.Util;
import com.example.appslure.ocwdelivery.Constant.Constant;

public class HomeScreen extends AppCompatActivity {

    RecyclerView listBooking;
    ProgressDialog progressDialog;
    HomeScreenAdapter objHomeScreenAdapter;
    String deliveryID;
    int xx,yy;

    HomeScreenBE objHomeScreenBE;

    FloatingActionButton btnFilter;

    ImageButton btnSignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initialize();

        listBooking.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        objHomeScreenBE.setId(Constant.orderID[position]);
                        objHomeScreenBE.setName(Constant.userName[position]);
                        objHomeScreenBE.setMobile(Constant.userMobile[position]);
                        objHomeScreenBE.setAddress(Constant.userAddress[position]);
                        objHomeScreenBE.setDryClean(Constant.clothDryClean[position]);
                        objHomeScreenBE.setOrderType(Constant.orderType[position]);
                        objHomeScreenBE.setWallet(Constant.walletAmount[position]);
                        objHomeScreenBE.setPaidCloth(Constant.clothPaid[position]);
                        objHomeScreenBE.setFreeCloth(Constant.clothFree[position]);
                        objHomeScreenBE.setHubAddress(Constant.hubAddress[position]);
                        objHomeScreenBE.setHubName(Constant.hubName[position]);
                        objHomeScreenBE.setToken(Constant.token[position]);

                        if (Constant.deliveryType[position].equalsIgnoreCase("pick")) {
                            startActivity(new Intent(getApplicationContext(), PickupDetail.class).putExtra("HomeScreenBE", objHomeScreenBE));
                        } else if (Constant.deliveryType[position].equalsIgnoreCase("drop")) {
                            startActivity(new Intent(getApplicationContext(), DropoffDetail.class).putExtra("HomeScreenBE", objHomeScreenBE));
                        }
                    }
                }));

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ClosedList.class));
            }
        });

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.setSharedPrefrenceValue(getApplicationContext(), Constant.PREFS_NAME, Constant.SP_USER_ID, null);
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        if(Util.isInternetConnection(HomeScreen.this))
            new GetListData().execute(deliveryID);
        else
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
    }

    private void initialize(){
        listBooking= (RecyclerView) findViewById(R.id.booking_list);
        btnFilter= (FloatingActionButton) findViewById(R.id.fabButton);
        btnSignout= (ImageButton) findViewById(R.id.btn_signout);


        listBooking.setHasFixedSize(true);
        progressDialog=new ProgressDialog(HomeScreen.this);

        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listBooking.setLayoutManager(llm);

        deliveryID= Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_USER_ID);

        objHomeScreenBE=new HomeScreenBE();

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        popupSize();



    }

    private void popupSize(){
        Display display = getWindowManager().getDefaultDisplay();

        int width = display.getWidth();
        int height = display.getHeight();

        // System.out.println("width" + width + "height" + height);

        if(width>=1000 && height>=1500){
            xx=700;
            yy=650;
        }
        else if(width>=700 && height>=1000)
        {
            xx=550;
            yy=500;
        }
        else
        {
            xx=450;
            yy=400;
        }

    }

    private class GetListData extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            objHomeScreenAdapter=new HomeScreenAdapter(getApplicationContext(),params[0]);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                listBooking.setAdapter(objHomeScreenAdapter);

                if(objHomeScreenAdapter.getItemCount()==0){

                }
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
