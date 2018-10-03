package com.risein.llcs.utils;

/**
 * Created by Risein on 11/15/2016.
 */

public class Apis {

    private static String Base_Url = "http://casilica.com/pagecalculator/ws/";
//    private static String Base_Url = "http://www.riseintechnology.com/WebServices/casilica/pagecalculator/ws/";
    //private static String Base_Url = "http://ecscript.com/sampletemplate/casilica/pagecalculator/ws/";
    public static String LOGIN_URL = Base_Url + "add_user.php";
    public static String JsonURL = Base_Url + "status.php";
    public static String Out_letApi = Base_Url + "moisturedetail.php";
    public static String temp_outApi = Base_Url + "cashilica.php";
//    public static String offer_noApi = Base_Url + "rand_number.php";
//    public static String offer_noApi = Base_Url + "demo.php";
    public static String offer_noApi = Base_Url + "offer_no_generate.php";
    public static String save_pdfApi = Base_Url + "savepdf.php";
    public static String GET_IMGApi = Base_Url + "get_model.php";
    public static String GET_PRICEApi = Base_Url + "get_price_table.php";
    public static String GET_SaveInformation = Base_Url + "client.php";
    public static String GET_Registration = Base_Url + "new_request.php";
    public static String GET_fetch_data = Base_Url + "fetch_data.php";

}
