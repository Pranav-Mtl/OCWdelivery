package com.example.appslure.ocwdelivery.BL;

import com.example.appslure.ocwdelivery.Constant.Constant;
import com.example.appslure.ocwdelivery.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 22-12-2015.
 */
public class DeliveryFailedBL {
    public String deliveryFalied(String bookingID,String type,String reason)
    {
        String result = fetRecord(bookingID,type,reason);
        String finalvalue = validate(result);

        return finalvalue;
    }
    private String fetRecord(String bookingID,String type,String reason) {
        String text = null;

        try
        {

            //fail_emp.php?user_booking_id=&type=&reason=
            String URL="user_booking_id="+bookingID+"&type="+type+"&reason="+reason;
            text= RestFullWS.serverRequest(URL, Constant.WS_FAILED);

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
