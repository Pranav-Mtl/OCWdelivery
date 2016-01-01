package com.example.appslure.ocwdelivery.BL;

import android.content.Context;

import com.example.appslure.ocwdelivery.Configuration.Util;
import com.example.appslure.ocwdelivery.Constant.Constant;
import com.example.appslure.ocwdelivery.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 21-12-2015.
 */
public class HomeScreenBL {

    public String getList(String id)
    {
        String result = fetRecord(id);
        String finalvalue = validate(result);

        return finalvalue;
    }

    private String fetRecord(String id) {
        String text = "";

        try
        {

            String URL="emp_id="+id;
            text= RestFullWS.serverRequest(URL, Constant.WS_LIST);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
        return text;
    }



    private String validate(String strValue)    {

        String status="";

        if(strValue.equalsIgnoreCase("[]")){
           status=Constant.WS_RESPONSE_FAILURE;
            Constant.orderID = new String[0];
        }else {
            status=Constant.WS_RESPONSE_SUCCESS;
            JSONParser jsonP = new JSONParser();
            try {
                Object obj = jsonP.parse(strValue);
                JSONArray jsonArrayObject = (JSONArray) obj;

                Constant.orderID = new String[jsonArrayObject.size()];
                Constant.userName = new String[jsonArrayObject.size()];
                Constant.userMobile = new String[jsonArrayObject.size()];
                Constant.userAddress = new String[jsonArrayObject.size()];
                Constant.deliveryDate = new String[jsonArrayObject.size()];
                Constant.deliveryTime = new String[jsonArrayObject.size()];
                Constant.deliveryType = new String[jsonArrayObject.size()];

                Constant.clothDryClean = new String[jsonArrayObject.size()];
                Constant.clothFree = new String[jsonArrayObject.size()];
                Constant.clothPaid = new String[jsonArrayObject.size()];
                Constant.walletAmount = new String[jsonArrayObject.size()];
                Constant.orderType = new String[jsonArrayObject.size()];

                Constant.hubName = new String[jsonArrayObject.size()];
                Constant.hubAddress = new String[jsonArrayObject.size()];
                Constant.token = new String[jsonArrayObject.size()];


                for (int i = 0; i < jsonArrayObject.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(i).toString());
                    Constant.orderID[i] = jsonObject.get("user_booking_id").toString();
                    Constant.userName[i] = jsonObject.get("user_name").toString();
                    Constant.userMobile[i] = jsonObject.get("phone").toString();
                    Constant.userAddress[i] = jsonObject.get("address").toString();
                    Constant.deliveryDate[i] = jsonObject.get("date").toString();
                    Constant.deliveryTime[i] = jsonObject.get("time").toString();
                    Constant.deliveryType[i] = jsonObject.get("user_type").toString();

                    Constant.clothDryClean[i] = jsonObject.get("drycleaning_cloth").toString();
                    Constant.clothFree[i] = jsonObject.get("free_cloth").toString();
                    Constant.clothPaid[i] = jsonObject.get("paid_cloth").toString();
                    Constant.walletAmount[i] = jsonObject.get("amount").toString();
                    Constant.orderType[i] = jsonObject.get("type").toString();
                    Constant.hubName[i] = jsonObject.get("warehouse_name").toString();
                    Constant.hubAddress[i] = jsonObject.get("warehouse_address").toString();
                    Constant.token[i]=jsonObject.get("token").toString();



                }


            } catch (Exception e) {


            }
        }
        return status;
    }
}
