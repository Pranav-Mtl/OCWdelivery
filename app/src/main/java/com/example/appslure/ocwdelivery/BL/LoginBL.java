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
public class LoginBL {

    Context context;

    public String loginRecord(String phone,String password,String gcm_id,Context mcontext)
    {
            context=mcontext;
            String result = fetRecord(phone, password,gcm_id);
            String finalvalue = validate(result);

        return finalvalue;
    }

    private String fetRecord(String phone,String password,String gcm_id) {
        String text = null;

        try
        {
            //http://oneclickwash.com/webservices/employee_login.php?mob=9876543210&password=abc
            String URL="mob="+phone+"&password="+password+"&gcm_id="+gcm_id;
            text= RestFullWS.serverRequest(URL,Constant.WS_LOGIN);

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

            if(status.equalsIgnoreCase(Constant.WS_RESPONSE_SUCCESS))
            {
                String user_id=jsonObject.get("employee_id").toString();
                Util.setSharedPrefrenceValue(context, Constant.PREFS_NAME, Constant.SP_USER_ID, user_id);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return status;
    }

}
