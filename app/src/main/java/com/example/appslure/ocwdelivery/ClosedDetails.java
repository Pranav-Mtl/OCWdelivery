package com.example.appslure.ocwdelivery;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.appslure.ocwdelivery.BE.ClosedListBE;

public class ClosedDetails extends AppCompatActivity {

    TextView id,name,mobile,address,wallet,warehouseName,warehouseAddress,dryClean,paid,free,token,amountcollected;

    ClosedListBE objClosedListBE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_details);
        initialize();
    }

    private void initialize(){
        id= (TextView) findViewById(R.id.closed_id);
        name= (TextView) findViewById(R.id.closed_name);
        mobile= (TextView) findViewById(R.id.closed_mobile);
        address= (TextView) findViewById(R.id.closed_address);
        //wallet= (TextView) findViewById(R.id.closed_previous_balance);
        warehouseName= (TextView) findViewById(R.id.closed_hub_name);
        warehouseAddress= (TextView) findViewById(R.id.closed_hub_address);
        dryClean= (TextView) findViewById(R.id.closed_dry_clean);
        paid= (TextView) findViewById(R.id.closed_paid_cloth);
        free= (TextView) findViewById(R.id.closed_free_cloth);
        token= (TextView) findViewById(R.id.closed_token);
        amountcollected= (TextView) findViewById(R.id.closed_amount);

        objClosedListBE= (ClosedListBE) getIntent().getSerializableExtra("ClosedListBE");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        id.setText("Bookind ID: OCW" + objClosedListBE.getId());
        name.setText("Name: "+objClosedListBE.getName());
        mobile.setText(objClosedListBE.getMobile());
        address.setText(objClosedListBE.getAddress());
        warehouseName.setText("WareHouse Name: "+objClosedListBE.getHubName());
        warehouseAddress.setText("WareHouse Address: "+objClosedListBE.getHubAddress());

        dryClean.setText(objClosedListBE.getDryClean());
        paid.setText(objClosedListBE.getPaidCloth());
        free.setText(objClosedListBE.getFreeCloth());
        token.setText(objClosedListBE.getToken());
        int wallet=Integer.valueOf(objClosedListBE.getAmountCollected());
        amountcollected.setText(Math.abs(wallet)+"");

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
}


