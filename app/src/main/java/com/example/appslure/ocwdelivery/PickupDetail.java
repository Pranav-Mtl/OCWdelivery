package com.example.appslure.ocwdelivery;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appslure.ocwdelivery.BE.HomeScreenBE;
import com.example.appslure.ocwdelivery.BL.DeliveryFailedBL;
import com.example.appslure.ocwdelivery.BL.DeliverySuccessBL;
import com.example.appslure.ocwdelivery.Configuration.Util;
import com.example.appslure.ocwdelivery.Constant.Constant;

public  class PickupDetail extends AppCompatActivity implements View.OnClickListener{

    TextView tvId,tvName,tvMobile,tvAddress,tvPreviousAmount,etDryClean,tvHubName,tvHubAddress;
    EditText etCloth,etToken,etAmount;
    LinearLayout llDryClean;
    Button btnSuccess,btnFailure;

    HomeScreenBE objHomeScreenBE;

    DeliverySuccessBL objDeliverySuccessBL;
    DeliveryFailedBL objDeliveryFailedBL;

    String strCloth,strAmount,strToken;
    String empID,bookingID;

    ProgressDialog progressDialog;

    int xx,yy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_detail);
        initialize();
    }

    private void initialize(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        objDeliverySuccessBL=new DeliverySuccessBL();
        objDeliveryFailedBL=new DeliveryFailedBL();

        tvId= (TextView) findViewById(R.id.pick_id);
        tvName= (TextView) findViewById(R.id.pick_name);
        tvMobile= (TextView) findViewById(R.id.pick_mobile);
        tvAddress= (TextView) findViewById(R.id.pick_address);
        tvPreviousAmount= (TextView) findViewById(R.id.pick_previous_amount);
        tvHubName= (TextView) findViewById(R.id.pick_hub_name);
        tvHubAddress= (TextView) findViewById(R.id.pick_hub_address);

        etCloth= (EditText) findViewById(R.id.pick_clothes);
        etToken= (EditText) findViewById(R.id.pick_token);
        etAmount= (EditText) findViewById(R.id.pick_amount);
        etDryClean= (TextView) findViewById(R.id.pick_dryclean);

        llDryClean= (LinearLayout) findViewById(R.id.ll_pick_dryclean);

        btnSuccess= (Button) findViewById(R.id.pick_success);
        btnFailure= (Button) findViewById(R.id.pick_failed);

        progressDialog=new ProgressDialog(PickupDetail.this);

        btnFailure.setOnClickListener(this);
        btnSuccess.setOnClickListener(this);
        tvMobile.setOnClickListener(this);

        objHomeScreenBE= (HomeScreenBE) getIntent().getSerializableExtra("HomeScreenBE");

        if(objHomeScreenBE.getOrderType().equalsIgnoreCase("Y")){
            llDryClean.setVisibility(View.VISIBLE);
            etDryClean.setText(objHomeScreenBE.getDryClean());
        }

        tvId.setText("Booking ID: OCW"+objHomeScreenBE.getId());
        tvName.setText("Name: "+objHomeScreenBE.getName());

        tvMobile.setPaintFlags(tvMobile.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMobile.setText(objHomeScreenBE.getMobile());

        tvAddress.setText(objHomeScreenBE.getAddress());

        int wallet=Integer.valueOf(objHomeScreenBE.getWallet());
        if(wallet<0){
            tvPreviousAmount.setText("Amount to be Collect: ₹" + Math.abs(wallet));
        }
        else
            tvPreviousAmount.setText("Amount to be Collect: ₹0");

        tvHubAddress.setText("Ware House Address: "+objHomeScreenBE.getHubAddress());
        tvHubName.setText("Ware House Name: "+objHomeScreenBE.getHubName());

        bookingID=objHomeScreenBE.getId();
        empID= Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_USER_ID);

        popupSize();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {


            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pick_success:
                strCloth=etCloth.getText().toString();
                strAmount=etAmount.getText().toString();
                strToken=etToken.getText().toString();

                if(strCloth.length()>0)
                    if(strAmount.length()>0)
                        if(strToken.length()>0)
                            new Success().execute(bookingID,empID,strCloth,strToken,strAmount,"pick");

                break;
            case R.id.pick_failed:
                showDialogFailed(PickupDetail.this);
                break;
            case R.id.pick_mobile:
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+objHomeScreenBE.getMobile()));
                    startActivity(callIntent);
                }catch (SecurityException e){

                }
                break;
        }

    }

    private class Success extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objDeliverySuccessBL.deliverySuccess(params[0],params[1],params[2],params[3],params[4],params[5]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if (Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(s)) {
                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                    //finish();
                } else
                    Toast.makeText(getApplicationContext(), "Something went wrong. Please try again?", Toast.LENGTH_SHORT).show();
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }

    private void popupSize(){
        Display display = getWindowManager().getDefaultDisplay();

        int width = display.getWidth();
        int height = display.getHeight();

        // System.out.println("width" + width + "height" + height);

        if(width>=1000){
            xx=700;
            yy=650;
        }
        else if(width>=700)
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


    /* popup for add referal */
    private void showDialogFailed(Context context){
        // x -->  X-Cordinate
        // y -->  Y-Cordinate

        final TextView tvTitle;
        Button btnClosePopup,btnsave;
        RadioGroup rgPayment;
        final RadioButton rbYes,rbNo;
        final EditText etClothes;

        final Dialog dialog  = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.layout_payment_popup);
        dialog.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER;
        wmlp.width=xx;
        wmlp.height=yy;




        btnClosePopup = (Button) dialog.findViewById(R.id.popup_cancel);
        btnsave= (Button) dialog.findViewById(R.id.popup_add);
        rgPayment= (RadioGroup) dialog.findViewById(R.id.group_payment);
        tvTitle= (TextView) dialog.findViewById(R.id.popup_title);
        rbYes= (RadioButton) dialog.findViewById(R.id.payment_cash);
        rbNo= (RadioButton) dialog.findViewById(R.id.payment_online);
        etClothes= (EditText) dialog.findViewById(R.id.popup_clothes);

        tvTitle.setText(getResources().getString(R.string._pickup_fail));
        //tvMsg.setText(getResources().getString(R.string._referral_message));
        btnClosePopup.setText(getResources().getString(R.string._pickup_cancel));
        btnsave.setText(getResources().getString(R.string._pickup_save));
        rbYes.setText(getResources().getString(R.string._pickup_yes));
        rbNo.setText(getResources().getString(R.string._pickup_no));

        rbYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rv = (RadioButton) v;
                if (rv.isChecked()) {
                    etClothes.setVisibility(View.VISIBLE);
                } else {
                    etClothes.setVisibility(View.GONE);
                }
            }
        });

        rbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rv = (RadioButton) v;
                if (rv.isChecked()) {
                    etClothes.setVisibility(View.GONE);
                } else {
                    etClothes.setVisibility(View.GONE);
                }
            }
        });

        btnClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(SellerQuestionExpandable.this,edittext.getText().toString(),Toast.LENGTH_LONG).show();
                dialog.dismiss();
                //finish();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           if(rbYes.isChecked()){
                                               if(etClothes.getText().toString().length()>0){
                                                   new Failed().execute(bookingID,etClothes.getText().toString(),"pick");
                                                   //startActivity(new Intent(getApplicationContext(), HomeScreen.class).putExtra("PlaceOrderBE", objPlaceOrderBE));
                                                   dialog.dismiss();
                                               }
                                               else
                                                   etClothes.setError("Required ");
                                           }
                                           else if(rbNo.isChecked()){
                                               dialog.dismiss();
                                           }


                                       }
                                   }

        );


        dialog.show();
    }

    private class Failed extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objDeliveryFailedBL.deliveryFalied(params[0], params[1], params[2]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                if(Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(s)){
                    startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Something went wrong. Please try again",Toast.LENGTH_SHORT).show();

            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }

}
