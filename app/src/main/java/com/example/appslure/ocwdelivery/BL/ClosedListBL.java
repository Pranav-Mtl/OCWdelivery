package com.example.appslure.ocwdelivery.BL;

import com.example.appslure.ocwdelivery.Constant.Constant;
import com.example.appslure.ocwdelivery.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 25-12-2015.
 */
public class ClosedListBL {

    public String getClosedList(String id)
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
            text= RestFullWS.serverRequest(URL, Constant.WS_CLOSED_LIST);

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
        }else {
            status=Constant.WS_RESPONSE_SUCCESS;
            JSONParser jsonP = new JSONParser();
            try {
                Object obj = jsonP.parse(strValue);
                JSONArray jsonArrayObject = (JSONArray) obj;

                Constant.closedorderID = new String[jsonArrayObject.size()];
                Constant.closeduserName = new String[jsonArrayObject.size()];
                Constant.closeduserMobile = new String[jsonArrayObject.size()];
                Constant.closeduserAddress = new String[jsonArrayObject.size()];
                Constant.closeddeliveryDate = new String[jsonArrayObject.size()];
                Constant.closeddeliveryTime = new String[jsonArrayObject.size()];
                Constant.closeddeliveryType = new String[jsonArrayObject.size()];


                Constant.closedtoken = new String[jsonArrayObject.size()];

                Constant.closedAmount = new String[jsonArrayObject.size()];
                Constant.closedDryClean = new String[jsonArrayObject.size()];
                Constant.closedFree = new String[jsonArrayObject.size()];
                Constant.closedPaid = new String[jsonArrayObject.size()];
                Constant.closedWareAddress = new String[jsonArrayObject.size()];
                Constant.closedWareName = new String[jsonArrayObject.size()];



                for (int i = 0; i < jsonArrayObject.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(i).toString());
                    Constant.closedorderID[i] = jsonObject.get("user_booking_id").toString();
                    Constant.closeduserName[i] = jsonObject.get("user_name").toString();
                    Constant.closeduserMobile[i] = jsonObject.get("phone").toString();
                    Constant.closeduserAddress[i] = jsonObject.get("address").toString();
                    Constant.closeddeliveryDate[i] = jsonObject.get("date").toString();
                    Constant.closeddeliveryTime[i] = jsonObject.get("time").toString();
                    Constant.closeddeliveryType[i] = jsonObject.get("user_type").toString();
                    Constant.closedtoken[i]=jsonObject.get("token").toString();

                    Constant.closedAmount[i] = jsonObject.get("amount").toString();
                    Constant.closedDryClean[i] = jsonObject.get("drycleaning_cloth").toString();
                    Constant.closedFree[i] = jsonObject.get("free_cloth").toString();
                    Constant.closedPaid[i] = jsonObject.get("paid_cloth").toString();
                    Constant.closedWareAddress[i] = jsonObject.get("warehouse_address").toString();
                    Constant.closedWareName[i]=jsonObject.get("warehouse_name").toString();



                }


            } catch (Exception e) {


            }
        }
        return status;
    }
}
