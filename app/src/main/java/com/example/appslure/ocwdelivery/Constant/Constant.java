package com.example.appslure.ocwdelivery.Constant;

/**
 * Created by appslure on 21-12-2015.
 */
public class Constant {

    public static  String STREMAILADDREGEX="^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$"; //EMAIL REGEX
    public static String PREFS_NAME="MyPrefsFile";

    public static final String MSG_KEY = "m";
    public static final String GOOGLE_PROJ_ID ="35388647227";

    public static String WS_HTTP="Http";
    public static String WS_DOMAIN_NAME="oneclickwash.in";
    public static String WS_PATH="/webservices/";

    public static String WS_RESPONSE_SUCCESS="success";
    public static String WS_RESPONSE_FAILURE="failure";

    public static String SP_USER_ID="user_id";
    public static String SP_GCM_ID="gcm_id";

    public static String WS_LOGIN="employee_login.php";
    public static String WS_LIST="emp_booking_list.php";
    public static String WS_CLOSED_LIST="emp_current_list.php";
    public static String WS_SUCCESS="confirm_emp.php";
    public static String WS_FAILED="fail_emp.php";



    /* variable for home screen list*/

    public static String orderID[];
    public static String userName[];
    public static String deliveryType[];
    public static String userMobile[];
    public static String userAddress[];
    public static String deliveryDate[];
    public static String deliveryTime[];
    public static String clothDryClean[];
    public static String clothFree[];
    public static String clothPaid[];
    public static String walletAmount[];
    public static String orderType[];
    public static String hubName[];
    public static String hubAddress[];
    public static String token[];

    /* variable for CLOSED screen list*/

    public static String closedorderID[];
    public static String closeduserName[];
    public static String closeddeliveryType[];
    public static String closeduserMobile[];
    public static String closeduserAddress[];
    public static String closeddeliveryDate[];
    public static String closeddeliveryTime[];
    public static String closedtoken[];

    public static String closedDryClean[];
    public static String closedFree[];
    public static String closedPaid[];
    public static String closedAmount[];
    public static String closedWareName[];
    public static String closedWareAddress[];


    /*[
    {
        "user_booking_id": "5",
        "user_type": "pick",
        "user_name": "sushant",
        "address": "sector 12A gurgaon",
        "phone": "8882154987",
        "type": "Normal",
        "drycleaning_cloth": "",
        "free_cloth": "",
        "paid_cloth": "",
        "amount": "0",
        "date": "2015-12-29",
        "time": "07:15 PM-09:15 PM",
        "token": "",
        "warehouse_name": "DLF Phase 3",
        "warehouse_address": "DLF Phase 3,Gurgaon,122018",
        "t": 1451396700
    }
]*/

}
