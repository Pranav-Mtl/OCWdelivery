package com.example.appslure.ocwdelivery.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appslure.ocwdelivery.BL.HomeScreenBL;
import com.example.appslure.ocwdelivery.Constant.Constant;
import com.example.appslure.ocwdelivery.HomeScreen;
import com.example.appslure.ocwdelivery.R;

/**
 * Created by appslure on 21-12-2015.
 */
public class HomeScreenAdapter extends  RecyclerView.Adapter<HomeScreenAdapter.BookingListHolder> {
    Context mContext;
    HomeScreenBL objHomeScreenBL;
    public HomeScreenAdapter(Context context,String id){
        mContext=context;
        objHomeScreenBL=new HomeScreenBL();
        objHomeScreenBL.getList(id);

    }

    @Override
    public BookingListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(mContext).
                inflate(R.layout.home_screen_list_raw, parent, false);

        return new BookingListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookingListHolder holder, int position) {

        holder.name.setText(Constant.userName[position]);
        holder.date.setText(Constant.deliveryDate[position]);
        holder.time.setText(Constant.deliveryTime[position]);
        holder.address.setText(Constant.userAddress[position]);
        holder.mobile.setText(Constant.userMobile[position]);
        holder.orderType.setText(Constant.deliveryType[position]);
    }

    @Override
    public int getItemCount() {
        if(Constant.orderID==null)
            return 0;
        return Constant.orderID.length;
    }

    public static class BookingListHolder extends RecyclerView.ViewHolder {

        TextView name,orderType,mobile,address,date,time;
        public BookingListHolder(View gridView) {
            super(gridView);

            name= (TextView) gridView.findViewById(R.id.list_name);
            orderType= (TextView) gridView.findViewById(R.id.list_order_type);
            mobile= (TextView) gridView.findViewById(R.id.list_mobile);
            address= (TextView) gridView.findViewById(R.id.list_address);
            date= (TextView) gridView.findViewById(R.id.list_date);
            time= (TextView) gridView.findViewById(R.id.list_time);


        }
    }

}
