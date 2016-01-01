package com.example.appslure.ocwdelivery.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appslure.ocwdelivery.BL.ClosedListBL;
import com.example.appslure.ocwdelivery.BL.HomeScreenBL;
import com.example.appslure.ocwdelivery.Constant.Constant;
import com.example.appslure.ocwdelivery.R;

/**
 * Created by appslure on 25-12-2015.
 */
public class ClosedListAdapter extends  RecyclerView.Adapter<ClosedListAdapter.ClosedListHolder>  {

    Context mContext;
    ClosedListBL objHomeScreenBL;

    public ClosedListAdapter(Context context,String id){
        mContext=context;
        objHomeScreenBL=new ClosedListBL();
        objHomeScreenBL.getClosedList(id);

    }

    @Override
    public ClosedListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(mContext).
                inflate(R.layout.home_screen_list_raw, parent, false);

        return new ClosedListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClosedListHolder holder, int position) {
        holder.name.setText(Constant.closeduserName[position]);
        holder.date.setText(Constant.closeddeliveryDate[position]);
        holder.time.setText(Constant.closeddeliveryTime[position]);
        holder.address.setText(Constant.closeduserAddress[position]);
        holder.mobile.setText(Constant.closeduserMobile[position]);
        holder.orderType.setText(Constant.closeddeliveryType[position]);
    }

    @Override
    public int getItemCount() {
        if(Constant.closedorderID==null)
            return 0;
        return Constant.closedorderID.length;
    }

    public static class ClosedListHolder extends RecyclerView.ViewHolder {

        TextView name,orderType,mobile,address,date,time;
        public ClosedListHolder(View gridView) {
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
