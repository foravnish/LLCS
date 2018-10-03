package com.risein.llcs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.risein.llcs.helper.AppController;
import com.risein.llcs.utils.Apis;
import com.risein.llcs.utils.AppUtils;
import com.risein.llcs.utils.BasicInformation;
import com.risein.llcs.utils.InputInformation;
import com.risein.llcs.utils.MyPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Risein on 9/29/2016.
 */

public class QcommercialActivity extends AppCompatActivity {
    TextView txtcommr,txtcommhead,txtcommsubhead,c_model,txt_price,txt_price2,txt_tprice,cq_area;
    Button btn_cNext,button_cprint;
    String precool_data,aftercool_data,isSimple,heatrecovery;
    EditText edit_qlty,edit_discount;
    public ProgressDialog progress;
    public static String total_price,noof_qntity="1",noof_disc="1";

    public static String Mprice;
    String offerCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcomme);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in);
 /*add toolbar to actionbar*/
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getPrice(QcommercialActivity.this);
        precool_data = getIntent().getExtras().getString("precool");
        aftercool_data = getIntent().getExtras().getString("aftercool");
        heatrecovery = getIntent().getExtras().getString("heatrecovery");
        isSimple =getIntent().getExtras().getString("simple");

        btn_cNext= (Button) findViewById(R.id.btn_qsummary);
        button_cprint= (Button) findViewById(R.id.button_cprint);

        c_model= (TextView) findViewById(R.id.c_model);
        txtcommr= (TextView) findViewById(R.id.txt_commr);
        txtcommhead=(TextView) findViewById(R.id.txt_comm_head);
        txt_price= (TextView) findViewById(R.id.txt_price);
        txt_price2= (TextView) findViewById(R.id.txt_price2);
        txt_tprice= (TextView) findViewById(R.id.txt_tprice);
        cq_area= (TextView) findViewById(R.id.cq_area);
        edit_qlty= (EditText) findViewById(R.id.edit_qlty);
        edit_discount= (EditText) findViewById(R.id.edit_discount);


        txtcommsubhead=(TextView) findViewById(R.id.txt_comm_subhead);
        txtcommhead.setText(R.string.q_commer);
        txtcommsubhead.setText(R.string.q_subcommer);
        txtcommr.setText(R.string.q_commercial);
        cq_area.setText( BasicInformation.getArea().toString());
        c_model.setText(BasicInformation.getModel().toString());

        double priceValue = Double.parseDouble(txt_price.getText().toString());
        Mprice=String.valueOf(priceValue);
        noof_qntity="1";
        total_price=Mprice;

        btn_cNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                convertCode();
                saveinformation();

                BasicInformation.setQuot_price(txt_price.getText().toString());
                BasicInformation.setQuot_t_price(txt_tprice.getText().toString());
                BasicInformation.setQuot_qty(edit_qlty.getText().toString());
                BasicInformation.setQuot_discount(edit_discount.getText().toString());

                Intent in =new Intent(QcommercialActivity.this,ModelViewActivity.class);
                in.putExtra("precool",precool_data);
                in.putExtra("aftercool",aftercool_data);
                in.putExtra("heatrecovery",heatrecovery);
                startActivity(in);

            }


        });

        edit_qlty.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0&& !s.equals(null)) {
                    double priceValue = Double.parseDouble(txt_price.getText().toString());
                    //double t_quntity = Double.parseDouble(edit_qlty.getText().toString());
                    int t_quntity = Integer.valueOf(edit_qlty.getText().toString());
                    noof_qntity=String.valueOf(t_quntity);

                    double total_qntity_priice=priceValue*t_quntity;

                    int y = (int) Math.round(total_qntity_priice);

                   // Log.w("total_qntity_priice",">>"+total_qntity_priice);
                    total_price=String.valueOf(y);

                    txt_tprice.setText(String.valueOf(y));
                    //txt_tprice.setText(String.valueOf(total_qntity_priice));


                }
            }
        });

        edit_discount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0&& !s.equals(null)) {
                    double priceValue = Double.parseDouble(txt_price2.getText().toString());

                    double t_disc = Double.valueOf(edit_discount.getText().toString());
                    noof_qntity=String.valueOf(t_disc);

                    double total_qntity_priice=priceValue*t_disc/100;

                    //int y1 = (int) Math.round(total_qntity_priice);

                    int y= (int) (priceValue-total_qntity_priice);
                    // Log.w("total_qntity_priice",">>"+total_qntity_priice);
                    total_price=String.valueOf(y);

                    txt_tprice.setText(String.valueOf(y));
                    txt_price.setText(String.valueOf(y));
                    //txt_tprice.setText(String.valueOf(total_qntity_priice));


                }
            }
        });

        /*button_cprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(QcommercialActivity.this,"Pdf Created",Toast.LENGTH_SHORT).show();

            }
        });*/
    }

    private void convertCode() {

        double n1=Double.valueOf(edit_discount.getText().toString());

        String offerCode;
        String sum = new String();

        //double n1 = 20.2;
        int number2= (int) n1;

        if (number2<9){
            int n = Integer.parseInt(Integer.toString(number2).substring(0, 1));
            Log.d("hbfghfghfg1fgg", String.valueOf(n));
            String s = null;
            if (n==0){
                s="Z";
                sum=sum+s;
            }
            if (n==1){
                s="Y";
                sum=sum+s;
            }
            else if (n==2){
                s="X";
                sum=sum+s;
            }
            else if (n==3){
                s="W";
                sum=sum+s;
            }
            else if (n==4){
                s="V";
                sum=sum+s;
            }
            else if (n==5){
                s="U";
                sum=sum+s;
            }
            else if (n==6){
                s="T";
                sum=sum+s;
            }
            else if (n==7){
                s="S";
                sum=sum+s;
            }
            else if (n==8){
                s="R";
                sum=sum+s;
            }
            else if (n==9){
                s="Q";
                sum=sum+s;
            }
            else {
                s="Z";
                sum=sum+s;
            }
        }


        else if (number2>9){
            int n2 = Integer.parseInt(Integer.toString(number2).substring(0, 1));
            Log.d("hbfghfghfg1eff", String.valueOf(n2));

            String s2 = null;
            if (n2==0){
                s2="Z";
                sum=sum+s2;
            }
            if (n2==1){
                s2="Y";
                sum=sum+s2;
            }
            else if (n2==2){
                s2="X";
                sum=sum+s2;
            }
            else if (n2==3){
                s2="W";
                sum=sum+s2;
            }
            else if (n2==4){
                s2="V";
                sum=sum+s2;
            }
            else if (n2==5){
                s2="U";
                sum=sum+s2;
            }
            else if (n2==6){
                s2="T";
                sum=sum+s2;
            }
            else if (n2==7){
                s2="S";
                sum=sum+s2;
            }
            else if (n2==8){
                s2="R";
                sum=sum+s2;
            }
            else if (n2==9){
                s2="Q";
                sum=sum+s2;
            }
            else {
                s2="Z";
                sum=sum+s2;
            }


            int n = Integer.parseInt(Integer.toString(number2).substring(1, 2));
            Log.d("hbfghfghfg2", String.valueOf(n));

            String s = null;
//            if (n==0){
//                s="Z";
//                sum=sum+s;
//            }
            if (n==1){
                s="Y";
                sum=sum+s;
            }
            else if (n==2){
                s="X";
                sum=sum+s;
            }
            else if (n==3){
                s="W";
                sum=sum+s;
            }
            else if (n==4){
                s="V";
                sum=sum+s;
            }
            else if (n==5){
                s="U";
                sum=sum+s;
            }
            else if (n==6){
                s="T";
                sum=sum+s;
            }
            else if (n==7){
                s="S";
                sum=sum+s;
            }
            else if (n==8){
                s="R";
                sum=sum+s;
            }
            else if (n==9){
                s="Q";
                sum=sum+s;
            }
            else {
                s="Z";
                sum=sum+s;
            }
        }

        String number = String.valueOf(n1);
        number = number.substring(number.indexOf(".")).substring(1);
        Log.d("hbfghfghfg3", String.valueOf(number));

        if (number.equals("0")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"Z");
            offerCode="D"+sum+"Z";
        }
        else if (number.equals("1")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"Y");
            offerCode="D"+sum+"Y";
        }
        else if (number.equals("2")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"X");
            offerCode="D"+sum+"X";
        }
        else if (number.equals("3")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"W");
            offerCode="D"+sum+"W";
        }
        else if (number.equals("4")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"V");
            offerCode="D"+sum+"V";

        }else if (number.equals("5")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"U");
            offerCode="D"+sum+"U";
        }
        else if (number.equals("6")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"T");
            offerCode="D"+sum+"T";

        }
        else if (number.equals("7")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"S");
            offerCode="D"+sum+"S";

        }
        else if (number.equals("8")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"R");
            offerCode="D"+sum+"R";
        }
        else if (number.equals("9")){
            Log.d("dfgfdhbgfhfgh","D"+sum+"Q");
            offerCode="D"+sum+"Q";
        }
        else {
            Log.d("dfgfdhbgfhfgh","D"+sum);
            offerCode="D"+sum;
        }


        Log.d("dfgdfgdfgdfgdsf",offerCode);
        BasicInformation.setOfferCode(offerCode);

//        int n= (int) n1;
//        double n2= n1-n;
//
//        String number = String.valueOf(n1);
//        number = number.substring(number.indexOf(".")).substring(1);
//
//        int val = 0,reverse = 0;
//        String s = null;
//        s= String.valueOf(n);
//
//        String sum = new String();
//
//        while( n>0 )
//        {
//            reverse = reverse * 10;
//            reverse = reverse + n%10;
//            n = n/10;
//        }
//
//        //Log.d("fdgdfgdffgfdg", String.valueOf(reverse));
//        while(reverse>0){
//
//            n=reverse%10;
//            reverse = reverse / 10;
////            if (n==0){
////                s="Z";
////                sum=sum+s;
////            }
//            if (n==1){
//                s="Y";
//                sum=sum+s;
//            }
//            else if (n==2){
//                s="X";
//                sum=sum+s;
//            }
//            else if (n==3){
//                s="W";
//                sum=sum+s;
//            }
//            else if (n==4){
//                s="V";
//                sum=sum+s;
//            }
//            else if (n==5){
//                s="U";
//                sum=sum+s;
//            }
//            else if (n==6){
//                s="T";
//                sum=sum+s;
//            }
//            else if (n==7){
//                s="S";
//                sum=sum+s;
//            }
//            else if (n==8){
//                s="R";
//                sum=sum+s;
//            }
//            else if (n==9){
//                s="Q";
//                sum=sum+s;
//            }
//            else {
//                    s="Z";
//                    sum=sum+s;
//            }
//        }
//
//        if (number.equals("0")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"Z");
//            offerCode="D"+sum+"Z";
//        }
//        else if (number.equals("1")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"Y");
//            offerCode="D"+sum+"Y";
//        }
//        else if (number.equals("2")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"X");
//            offerCode="D"+sum+"X";
//        }
//        else if (number.equals("3")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"W");
//            offerCode="D"+sum+"W";
//        }
//        else if (number.equals("4")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"V");
//            offerCode="D"+sum+"V";
//
//        }else if (number.equals("5")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"U");
//            offerCode="D"+sum+"U";
//        }
//        else if (number.equals("6")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"T");
//            offerCode="D"+sum+"T";
//
//        }
//        else if (number.equals("7")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"S");
//            offerCode="D"+sum+"S";
//
//        }
//        else if (number.equals("8")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"R");
//            offerCode="D"+sum+"R";
//        }
//        else if (number.equals("9")){
//            Log.d("dfgfdhbgfhfgh","D"+sum+"Q");
//            offerCode="D"+sum+"Q";
//        }
//        else {
//            Log.d("dfgfdhbgfhfgh","D"+sum);
//            offerCode="D"+sum;
//        }

    }


    private void saveinformation() {
        progress = new ProgressDialog(QcommercialActivity.this);
        progress.setCancelable(false);
        progress.setTitle("Please wait...");
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.GET_SaveInformation,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("response is",""+response);
                        progress.dismiss();
                        //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
//                                try {
//                                    JSONObject obj = new JSONObject(response);
//                                    String status = obj.getString("status");
//                                    String msg = obj.getString("msg");
//
//                                    if (status.equals("1")){
//
//                                        Intent in=new Intent(OcwActivity.this,InputActivity.class);
//                                        startActivity(in);
//                                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
//                                    }
//                                    else {
//                                        Toast.makeText(OcwActivity.this, msg, Toast.LENGTH_LONG).show();
//                                    }
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(QcommercialActivity.this,"Please check Internet Connection",Toast.LENGTH_LONG ).show();
                        progress.dismiss();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<String,String>();


                map.put("company_name", InputInformation.getCompanyname().toString());
                map.put("client_name",InputInformation.getClientname().toString());
                map.put("person_name",InputInformation.getPersonaname().toString());
                map.put("phone",InputInformation.getPersonphone().toString());
                map.put("address",InputInformation.getAddress().toString());
                map.put("email",InputInformation.getEmailid().toString());
                map.put("project_name",InputInformation.getProjectname().toString());
                map.put("Project_Location",InputInformation.getProjectlocation().toString());
                map.put("Consultant",InputInformation.getConsaltname().toString());
                map.put("Application",InputInformation.getApplication().toString());
                map.put("user_id", MyPreference.loadLoginid(QcommercialActivity.this));
                map.put("Offer_No", getIntent().getExtras().getString("offerno")+"-"+offerCode);
                map.put("discount", edit_discount.getText().toString()+"%");

                Log.d("company_name",InputInformation.getCompanyname().toString());
                Log.d("client_name",InputInformation.getClientname().toString());
                Log.d("person_name",InputInformation.getPersonaname().toString());
                Log.d("phone",InputInformation.getPersonphone().toString());
                Log.d("address",InputInformation.getAddress().toString());
                Log.d("email",InputInformation.getEmailid().toString());
                Log.d("project_name",InputInformation.getProjectname().toString());
                Log.d("Project_Location",InputInformation.getProjectlocation().toString());
                Log.d("Consultant",InputInformation.getConsaltname().toString());
                Log.d("Application",InputInformation.getApplication().toString());
                Log.d("user_id", MyPreference.loadLoginid(QcommercialActivity.this));
                Log.d("Offer_No", getIntent().getExtras().getString("offerno")+"-"+offerCode);
                Log.d("discount", edit_discount.getText().toString());

                Log.d("username", MyPreference.loadUsername(QcommercialActivity.this));

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    @Override
    public void onResume(){
        super.onResume();
        // put your code here...

        double priceValue = Double.parseDouble(txt_price.getText().toString());
        Mprice=String.valueOf(priceValue);

        double priceTValue = Double.parseDouble(txt_tprice.getText().toString());
        total_price=String.valueOf(priceTValue);
    }

    private void getPrice(final Context con) {
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, Apis.GET_PRICEApi,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("model no. is",""+response);
                        //Toast.makeText(con, response, Toast.LENGTH_LONG).show();
                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");
                            String msg = obj.getString("msg");


                            if (status.equals("1")) {

                                JSONObject jsonObject= obj.getJSONObject("item");

                                Mprice=jsonObject.optString("price").toString();
                                total_price=Mprice;
                                //set image
                                Log.w("model no. is",""+Mprice);
                                txt_price.setText(Mprice);
                                txt_price2.setText(Mprice);

                                txt_tprice.setText(Mprice);

                            } else {
                                Toast.makeText(con, msg, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(con, "Please check InterNet Connection", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.w("ghfghfghfg",""+OcwActivity.controlPanelValue);
                Log.w("ghfghfghfg",""+OcwActivity.heatSourceValue);
                Log.w("ghfghfghfg",""+OcwActivity.heatValue);
                Log.w("ghfghfghfg",""+BasicInformation.getModel());
                Log.w("ghfghfghfg",""+BasicInformation.getAhu());
                Log.w("ghfghfghfg",""+isSimple);
                Log.w("ghfghfghfgAHU",InputInformation.getAHU().toString());

                String hrType=OcwActivity.heatValue.toLowerCase();
                String pType=OcwActivity.psaValue.toLowerCase();
                Log.w("ghfghfghfg",""+hrType);
                Log.w(" ",""+pType);
                Log.w("price_type is",""+OcwActivity.psaValue.toLowerCase());




                Map<String, String> map = new HashMap<String, String>();
                map.put("m_type",OcwActivity.controlPanelValue);
                map.put("s_type",OcwActivity.heatSourceValue);
                map.put("hr",hrType);

                if (InputInformation.getAHU().toString().isEmpty()){
                    map.put("hr1","0");
                    Log.d("fgdfgdfhdgfhbf","0");
                }
                else{
                map.put("hr1",InputInformation.getAHU().toString());
                    Log.d("fgdfgdfhdgfhbf",InputInformation.getAHU().toString());}

                map.put("model_no",BasicInformation.getModel());
                map.put("price_type",pType);
                map.put("post_cooling",BasicInformation.getAhu());
                map.put("simple_cond",isSimple);
                map.put("pname","casilica");

                Log.d("dfgdfgbfdhbfghbgfghfg",pType);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(con);
        requestQueue.add(stringRequest);
        //Log.e("lavkush", "" + stringRequest);
    }


    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean getRange(double cfm,double ahu) {

        boolean isSimple = false;

        if (cfm <= 110 && ahu <= 110) {
            isSimple = true;
        } else if ((cfm >= 111 && cfm <= 210)&& (ahu >= 111 && ahu <= 210)) {

            isSimple = true;
        } else if ((cfm >= 211 && cfm <= 430) && (ahu >= 211 && ahu <= 430)) {
            isSimple = true;
        } else if ((cfm >= 431 && cfm <= 700) && (ahu >= 431 && ahu <= 700)) {
            isSimple = true;
        } else if ((cfm >= 701 && cfm <= 1100) && (ahu >= 701 && ahu <= 1100)) {
            isSimple = true;
        } else if ((cfm >= 1101 && cfm <= 1350) && (ahu >= 1101 && ahu <= 1350)) {
            isSimple = true;
        } else if ((cfm >= 1351 && cfm <= 1900) && (ahu >= 1351 && ahu <= 1900)) {
            isSimple = true;
        } else if ((cfm >= 1901 && cfm <= 2200)&& (ahu >= 1901 && ahu <= 2200)) {
            isSimple = true;
        } else if ((cfm >= 2201 && cfm <= 2800) && (ahu >= 2201 && ahu <= 2800)) {
            isSimple = true;
        } else if ((cfm >= 2801 && cfm <= 3300) && (ahu >= 2801 && ahu <= 3300)) {
            isSimple = true;
        } else if ((cfm >= 3301 && cfm <= 3800) && (ahu >= 3301 && ahu <= 3800)) {
            isSimple = true;
        } else if ((cfm >= 3801 && cfm <= 4100) && (ahu >= 3801 && ahu <= 4100)) {
            isSimple = true;
        } else if ((cfm >= 4101 && cfm <= 4900) && (ahu >= 4101 && ahu <= 4900)) {
            isSimple = true;
        } else if ((cfm >= 4901 && cfm <= 5500) && (ahu >= 4901 && ahu <= 5500)) {
            isSimple = true;
        } else if ((cfm >= 5501 && cfm <= 6500) && (ahu >= 5501 && ahu <= 6500)) {
            isSimple = true;
        } else if ((cfm >= 6501 && cfm <= 8000) && (ahu >= 6501 && ahu <= 8000)) {
            isSimple = true;
        } else if ((cfm >= 8001 && cfm <= 10500) && (ahu >= 8001 && ahu <= 10500)) {
            isSimple = true;
        } else if ((cfm >= 10501 && cfm <= 13000) && (ahu >= 10501 && ahu <= 13000)) {
            isSimple = true;
        } else if ((cfm >= 13001 && cfm <= 17000) && (ahu >= 13001 && ahu <= 17000)) {
            isSimple = true;
        } else if ((cfm >= 17001 && cfm <= 21000) && (ahu >= 17001 && ahu <= 21000)) {
            isSimple = true;
        }
        else {
            isSimple = false;
        }

        return isSimple;
    }
}
