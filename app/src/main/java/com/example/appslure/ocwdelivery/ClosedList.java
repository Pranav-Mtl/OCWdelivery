package com.example.appslure.ocwdelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appslure.ocwdelivery.Adapter.ClosedListAdapter;
import com.example.appslure.ocwdelivery.Adapter.HomeScreenAdapter;
import com.example.appslure.ocwdelivery.BE.ClosedListBE;
import com.example.appslure.ocwdelivery.BE.HomeScreenBE;
import com.example.appslure.ocwdelivery.Configuration.RecyclerItemClickListener;
import com.example.appslure.ocwdelivery.Configuration.Util;
import com.example.appslure.ocwdelivery.Constant.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ClosedList extends AppCompatActivity {

    RecyclerView listBooking;
    ProgressDialog progressDialog;
    ClosedListAdapter objClosedListAdapter;
    String deliveryID;
    int xx,yy;

    Calendar cal;
    TextView tvHeading;

    ClosedListBE objClosedListBE;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_list);
        initialize();

        if(Util.isInternetConnection(ClosedList.this))
            new GetListData().execute(deliveryID);
        else
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();


        listBooking.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        objClosedListBE.setName(Constant.closeduserName[position]);
                        objClosedListBE.setId(Constant.closedorderID[position]);
                        objClosedListBE.setAddress(Constant.closeduserAddress[position]);
                        objClosedListBE.setMobile(Constant.closeduserMobile[position]);
                        objClosedListBE.setHubName(Constant.closedWareName[position]);
                        objClosedListBE.setHubAddress(Constant.closedWareAddress[position]);
                        objClosedListBE.setDryClean(Constant.closedDryClean[position]);
                        objClosedListBE.setPaidCloth(Constant.closedPaid[position]);
                        objClosedListBE.setFreeCloth(Constant.closedFree[position]);
                        objClosedListBE.setToken(Constant.closedtoken[position]);
                        objClosedListBE.setAmountCollected(Constant.closedAmount[position]);
                    startActivity(new Intent(getApplicationContext(), ClosedDetails.class).putExtra("ClosedListBE", objClosedListBE));

                    }
                }));

    }

    private void initialize(){
        listBooking= (RecyclerView) findViewById(R.id.closed_list);
        tvHeading= (TextView) findViewById(R.id.tv_heading);

        listBooking.setHasFixedSize(true);
        objClosedListBE=new ClosedListBE();


        progressDialog=new ProgressDialog(ClosedList.this);

        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listBooking.setLayoutManager(llm);

        deliveryID= Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_USER_ID);

        try{
            cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
            String date = dateFormat.format(cal.getTime());
            tvHeading.setText("Closed by you: "+date);
        }catch (Exception e){

        }



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

    private class GetListData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            objClosedListAdapter=new ClosedListAdapter(getApplicationContext(),params[0]);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                listBooking.setAdapter(objClosedListAdapter);

                if(objClosedListAdapter.getItemCount()==0){

                }
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }


}
