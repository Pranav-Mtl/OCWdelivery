package com.example.appslure.ocwdelivery.BL;

import android.content.Context;

import com.example.appslure.ocwdelivery.Configuration.Util;
import com.example.appslure.ocwdelivery.Constant.Constant;
import com.example.appslure.ocwdelivery.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 22-12-2015.
 */
public class DeliverySuccessBL {

    public String deliverySuccess(String bookingID,String empID,String cloth,String token,String amount,String type)
    {

        String result = fetRecord(bookingID,empID,cloth,token,amount,type);
        String finalvalue = validate(result);

        return finalvalue;
    }
    private String fetRecord(String bookingID,String empID,String cloth,String token,String amount,String type) {
        String text = null;

        try
        {

            String URL="user_booking_id="+bookingID+"&emp_id="+empID+"&no_of_cloth="+cloth+"&token="+token+"&amount="+amount+"&type="+type;
            text= RestFullWS.serverRequest(URL, Constant.WS_SUCCESS);

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

        JSONParser jsonP=new JSONParser();
        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;
            JSONObject jsonObject=(JSONObject)jsonP.parse(jsonArrayObject.get(0).toString());
            status=jsonObject.get("result").toString();



        } catch (Exception e) {

            e.printStackTrace();
        }
        return status;
    }

}
