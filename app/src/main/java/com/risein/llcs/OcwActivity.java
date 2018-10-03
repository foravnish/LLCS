package com.risein.llcs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.risein.llcs.helper.AppController;
import com.risein.llcs.utils.Apis;
import com.risein.llcs.utils.BasicInformation;
import com.risein.llcs.utils.InputInformation;
import com.risein.llcs.utils.MyPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Risein on 9/29/2016.
 */
public class OcwActivity extends AppCompatActivity {

    public static String precoolValue="Yes", aftercoolValue="Yes",heatValue="Yes",heatOffer,psaValue="PSA", preWithpost="Yes",
    osw4="",osw5="",osw6 ="";
    public ProgressDialog progress;
    TextView txtoswhead,osw_model,osw_capacity,osw_demodifier,osw4id,osw5id,osw6id,txt_offer_no;
    Button btnqtosw,btn_offer_no;
    private String[] preCool;
    Spinner spnr_pre_cool,spnr_after_cool,spnr_heat;
    public static String dmodOcw,oswcapacity,heatSource,controlPanel;

    public static String  controlPanelValue = null,heatSourceValue= null ;

    public static boolean isOffer=true;
    String isEqual;

    int counter=0;


    RadioGroup radioheat,rg_control;
    RadioButton radioh1, radioh2, radioh3;
    RadioButton rg_radioh1, rg_radioh2;

    public static String eOfferNo="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osw);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in);
 /*add toolbar to actionbar*/
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findId();

        txt_offer_no.setText(eOfferNo);

        preCool = getResources().getStringArray(R.array.pre_cool);

        ArrayAdapter<String> dataprecool = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, preCool);
        dataprecool.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_pre_cool.setAdapter(dataprecool);


        ArrayAdapter<String> dataAftercool = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, preCool);
        dataAftercool.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_after_cool.setAdapter(dataAftercool);

        ArrayAdapter<String> dataHeat = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, preCool);
        dataHeat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_heat.setAdapter(dataHeat);





        spnr_pre_cool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                precoolValue = adapter.getItemAtPosition(position).toString();

                    if (precoolValue.equalsIgnoreCase("Yes") && aftercoolValue.equalsIgnoreCase("Yes")) {
                        osw4id.setText("SP: " + "6.5”");
                        osw5id.setText("SP: " + "2.4”");
                        psaValue="PSA";
                        preWithpost="Yes";
                        isOffer=true;
                        osw6id.setText("Media: " + "Prefilter – 20 micron & HEPA Filter – 0.3 micron");
                        osw4="SP: " + "6.5”";
                        osw5="SP: " + "2.4”";
                        osw6 ="Media: " + "Prefilter – 20 micron & HEPA Filter – 0.3 micron";

                    } else if (precoolValue.equalsIgnoreCase("Yes") && aftercoolValue.equalsIgnoreCase("No")) {
                        osw4id.setText("SP: " + "4”");
                        osw5id.setText("SP: " + "2.4”");
                        psaValue="PS";
                        preWithpost="No";
                        isOffer=true;
                        osw6id.setText("Media: " + "Prefilter –20 micron & Fine Filter – 10 micron ");
                        osw4="SP: " + "4”";
                        osw5="SP: " + "2.4”";
                        osw6 ="Media: " + "Prefilter –20 micron & Fine Filter – 10 micron ";
                    } else if (precoolValue.equalsIgnoreCase("No") && aftercoolValue.equalsIgnoreCase("Yes")) {
                        osw4id.setText("SP: " + "4.5”");
                        osw5id.setText("SP: " + "2.4”");
                        psaValue="SA";
                        preWithpost="No";
                        isOffer=true;
                        osw6id.setText("Media: " + "Prefilter – 20 micron & HEPA Filter - 0.3 micron");
                        osw4="SP: " + "4.5”";
                        osw5="SP: " + "2.4”";
                        osw6 ="Media: " + "Prefilter – 20 micron & HEPA Filter - 0.3 micron";
                    } else if (precoolValue.equalsIgnoreCase("No") && aftercoolValue.equalsIgnoreCase("No")) {
                        osw4id.setText("SP: " + "3”");
                        osw5id.setText("SP: " + "2.4”");
                        psaValue="S";
                        preWithpost="No";
                        isOffer=true;
                        osw6id.setText("Media: " + "Prefilter – 20 micron");
                        osw4="SP: " + "3”";
                        osw5="SP: " + "2.4”";
                        osw6 ="Media: " + "Prefilter – 20 micron";
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spnr_after_cool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

                // On selecting a spinner item
                aftercoolValue = adapter.getItemAtPosition(position).toString();

                if (precoolValue.equalsIgnoreCase("Yes")&&aftercoolValue.equalsIgnoreCase("Yes")) {
                    osw4id.setText("SP: "+ "6.5”");
                    osw5id.setText("SP: "+ "2.4”");
                    psaValue="PSA";
                    preWithpost="Yes";
                    isOffer=true;
                    osw6id.setText("Media: "+ "Prefilter – 20 micron & HEPA Filter – 0.3 micron");

                    osw4="SP: " + "6.5”";
                    osw5="SP: " + "2.4”";
                    osw6 ="Media: " + "Prefilter – 20 micron & HEPA Filter – 0.3 micron";
                } else if (precoolValue.equalsIgnoreCase("Yes")&&aftercoolValue.equalsIgnoreCase("No")) {
                    osw4id.setText("SP: "+ "4”");
                    osw5id.setText("SP: "+ "2.4”");
                    psaValue="PS";
                    preWithpost="No";
                    isOffer=true;
                    osw6id.setText("Media: "+ "Prefilter –20 micron & Fine Filter – 10 micron ");
                    osw4="SP: " + "4”";
                    osw5="SP: " + "2.4”";
                    osw6 ="Media: " + "Prefilter –20 micron & Fine Filter – 10 micron ";

                } else if (precoolValue.equalsIgnoreCase("No")&&aftercoolValue.equalsIgnoreCase("Yes")) {
                    osw4id.setText("SP: "+ "4.5”");
                    osw5id.setText("SP: "+ "2.4”");
                    psaValue="SA";
                    preWithpost="No";
                    isOffer=true;
                    osw6id.setText("Media: "+ "Prefilter – 20 micron & HEPA Filter - 0.3 micron");
                    osw4="SP: " + "4.5”";
                    osw5="SP: " + "2.4”";
                    osw6 ="Media: " + "Prefilter – 20 micron & HEPA Filter - 0.3 micron";
                } else if (precoolValue.equalsIgnoreCase("No")&&aftercoolValue.equalsIgnoreCase("No")) {
                    osw4id.setText("SP: "+ "3”");
                    osw5id.setText("SP: "+ "2.4”");
                    psaValue="S";
                    preWithpost="No";
                    isOffer=true;
                    osw6id.setText("Media: "+ "Prefilter – 20 micron");
                    osw4="SP: " + "3”";
                    osw5="SP: " + "2.4”";
                    osw6 ="Media: " + "Prefilter – 20 micron";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });



        spnr_heat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                heatValue = adapter.getItemAtPosition(position).toString();

                if (position==0){
                    isOffer=true;
                    heatOffer="HR";
                }
                else if (position==1){
                    isOffer=true;
                    heatOffer="NA";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        osw_model.setText(BasicInformation.getModel().toString());


        String capacity=BasicInformation.getModel().toString().replace("CRD-","");


        if (!BasicInformation.getSflowDehumidifier().isEmpty()){
            dmodOcw=BasicInformation.getSflowDehumidifier().toString();
            oswcapacity=BasicInformation.getSflowDehumidifier().toString();

            osw_demodifier.setText(BasicInformation.getSflowDehumidifier().toString());
//            osw_capacity.setText(BasicInformation.getSflowDehumidifier().toString());
            osw_capacity.setText(capacity.concat("0"));
        }else {
            dmodOcw=BasicInformation.getDehumidifier().toString();
            oswcapacity=BasicInformation.getDehumidifier().toString();

            osw_demodifier.setText(BasicInformation.getDehumidifier().toString());
//            osw_capacity.setText(BasicInformation.getDehumidifier().toString());
            osw_capacity.setText(capacity.concat("0"));
        }


        // check Dehumidifier and AHU value....
           isEqual=getRange(Double.valueOf(dmodOcw),Double.valueOf(BasicInformation.getAhu()));
        // end


        txtoswhead.setText(R.string.our_scope);
        //txtosw.setText(R.string.ourscopwork);

        btnqtosw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (txt_offer_no.getText().equals("")){
                    Toast.makeText(OcwActivity.this, "Please click on OFFER NO.", Toast.LENGTH_SHORT).show();
                }
                else {


                    ///// Check roll type

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.LOGIN_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("gfdgdfgdfgdfgdfgds",""+response);
                                    //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();

                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        String roltype = obj.getString("role");
                                        //String msg = obj.getString("msg");
                                        BasicInformation.setRolltype(obj.getString("role"));
                                        if (roltype.equals("sales")){

                                            Intent in = new Intent(OcwActivity.this, QcommercialActivity.class);
                                            in.putExtra("precool", precoolValue);
                                            in.putExtra("aftercool", aftercoolValue);
                                            in.putExtra("heatrecovery", heatValue);
                                            in.putExtra("offerno", eOfferNo);
                                            in.putExtra("simple", isEqual);

                                            startActivity(in);



                                            BasicInformation.setScop1(osw_capacity.getText().toString());
                                            BasicInformation.setScop2(osw_demodifier.getText().toString());

                                            BasicInformation.setQ4(osw4id.getText().toString());
                                            BasicInformation.setQ5(osw5id.getText().toString());
                                            BasicInformation.setQ6(osw6id.getText().toString());

                                        }
                                        else {
                                            BasicInformation.setScop1(osw_capacity.getText().toString());
                                            BasicInformation.setScop2(osw_demodifier.getText().toString());

                                            BasicInformation.setQ4(osw4id.getText().toString());
                                            BasicInformation.setQ5(osw5id.getText().toString());
                                            BasicInformation.setQ6(osw6id.getText().toString());

                                            Intent in =new Intent(OcwActivity.this,ModelViewActivity.class);

                                            in.putExtra("precool",precoolValue);
                                            in.putExtra("aftercool",aftercoolValue);
                                            in.putExtra("heatrecovery",heatValue);
                                            startActivity(in);

                                            saveinformation();

                                            BasicInformation.setQuot_price("");
                                            BasicInformation.setQuot_t_price("");
                                            BasicInformation.setQuot_qty("");
                                            BasicInformation.setQuot_discount("");


                                            //Toast.makeText(OcwActivity.this, msg, Toast.LENGTH_LONG).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                   // Toast.makeText(OcwActivity.this,"Please check Internet Connection",Toast.LENGTH_LONG ).show();
                                }
                            })

                            {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> map = new HashMap<String,String>();

                           // Log.w("device id",""+id);
                            map.put("username",MyPreference.loadUsername2(OcwActivity.this));
                            map.put("password",MyPreference.loadPassword(OcwActivity.this));
                            Log.d("sdfsdgfgdsdgfds",MyPreference.loadUsername2(OcwActivity.this));
                            Log.d("sdfsdgfgdsdgfds",MyPreference.loadPassword(OcwActivity.this));
                           // map.put("device_id",id);
                            return map;
                        }
                    };
                    AppController.getInstance().addToRequestQueue(stringRequest);
                    ///// End












                }
            }
        });

        btn_offer_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOffer==true) {
                    getOfferNo(OcwActivity.this);
                }
                else {
                    txt_offer_no.setText(eOfferNo);
                }


               /* counter+=1;
                // radioh1 = (RadioButton) radioheat.findViewById(radioheat.getCheckedRadioButtonId());
                // get selected radio button from radioGroup
                int selectedId = radioheat.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioh1 = (RadioButton) radioheat.findViewById(selectedId);

               *//* Toast.makeText(OcwActivity.this,
                        radioh1.getText(), Toast.LENGTH_SHORT).show();*//*
                heatSource=radioh1.getText().toString();
                int selectedId1 = rg_control.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                rg_radioh1 = (RadioButton) rg_control.findViewById(selectedId1);

                *//*Toast.makeText(OcwActivity.this,
                        rg_radioh1.getText(), Toast.LENGTH_SHORT).show();*//*
                controlPanel=rg_radioh1.getText().toString();

                String controlPanelValue = null,heatSourceValue= null ;

                if (rg_radioh1.getText().toString().contains("Basic Model")){

                    controlPanelValue="B";

                }
                else if (rg_radioh1.getText().toString().contains("Ultra Intelligent Series")){
                    controlPanelValue="UI";
                }



                 if (radioh1.getText().toString().contains("Steam Coil with")){
                    heatSourceValue="SE";
                }
                else if (radioh1.getText().toString().contains("Electric Heater")){

                    heatSourceValue="E";

                }
                else if (radioh1.getText().toString().contains("Only Steam Coil")){
                    heatSourceValue="S";
                }

                eOfferNo="Offer No.: "+counter+"-"+controlPanelValue+"-"+heatSourceValue+"-"+psaValue+"-"+heatOffer+"-"+BasicInformation.getProjectadd().toString();
                txt_offer_no.setText("Offer No.: "+counter+"-"+controlPanelValue+"-"+heatSourceValue+"-"+psaValue+"-"+heatOffer+"-"+BasicInformation.getProjectadd().toString());*/


            }
        });

    }

    private void saveinformation() {
        progress = new ProgressDialog(OcwActivity.this);
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
                        Toast.makeText(OcwActivity.this,"Please check Internet Connection",Toast.LENGTH_LONG ).show();
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
                map.put("user_id", MyPreference.loadLoginid(OcwActivity.this));
                map.put("Offer_No", getIntent().getExtras().getString("offerno"));
                map.put("discount", ""+"%");

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
                Log.d("user_id", MyPreference.loadLoginid(OcwActivity.this));
                Log.d("Offer_No", getIntent().getExtras().getString("offerno"));
                Log.d("discount", "");

                Log.d("username", MyPreference.loadUsername(OcwActivity.this));

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


//    private void saveinformation() {
//                progress = new ProgressDialog(OcwActivity.this);
//                progress.setCancelable(false);
//                progress.setTitle("Please wait...");
//                progress.show();
//                         StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.GET_SaveInformation,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Log.w("response is",""+response);
//                                progress.dismiss();
//                                //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
////                                try {
////                                    JSONObject obj = new JSONObject(response);
////                                    String status = obj.getString("status");
////                                    String msg = obj.getString("msg");
////
////                                    if (status.equals("1")){
////
////                                        Intent in=new Intent(OcwActivity.this,InputActivity.class);
////                                        startActivity(in);
////                                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
////                                    }
////                                    else {
////                                        Toast.makeText(OcwActivity.this, msg, Toast.LENGTH_LONG).show();
////                                    }
////
////                                } catch (JSONException e) {
////                                    e.printStackTrace();
////                                }
//
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(OcwActivity.this,"Please check Internet Connection",Toast.LENGTH_LONG ).show();
//                                progress.dismiss();
//                            }
//                        }){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//
//                        Map<String,String> map = new HashMap<String,String>();
//
//
//                        map.put("company_name",InputInformation.getCompanyname().toString());
//                        map.put("client_name",InputInformation.getClientname().toString());
//                        map.put("person_name",InputInformation.getPersonaname().toString());
//                        map.put("phone",InputInformation.getPersonphone().toString());
//                        map.put("address",InputInformation.getAddress().toString());
//                        map.put("email",InputInformation.getEmailid().toString());
//                        map.put("project_name",InputInformation.getProjectname().toString());
//                        map.put("Project_Location",InputInformation.getProjectlocation().toString());
//                        map.put("Consultant",InputInformation.getConsaltname().toString());
//                        map.put("Application",InputInformation.getApplication().toString());
//                        map.put("user_id", MyPreference.loadLoginid(OcwActivity.this));
//                        map.put("Offer_No", txt_offer_no.getText().toString());
//
//                        Log.d("company_name",InputInformation.getCompanyname().toString());
//                        Log.d("client_name",InputInformation.getClientname().toString());
//                        Log.d("person_name",InputInformation.getPersonaname().toString());
//                        Log.d("phone",InputInformation.getPersonphone().toString());
//                        Log.d("address",InputInformation.getAddress().toString());
//                        Log.d("email",InputInformation.getEmailid().toString());
//                        Log.d("project_name",InputInformation.getProjectname().toString());
//                        Log.d("Project_Location",InputInformation.getProjectlocation().toString());
//                        Log.d("Consultant",InputInformation.getConsaltname().toString());
//                        Log.d("Application",InputInformation.getApplication().toString());
//                        Log.d("user_id", MyPreference.loadLoginid(OcwActivity.this));
//                        Log.d("Offer_No", txt_offer_no.getText().toString());
//
//                        Log.d("username", MyPreference.loadUsername(OcwActivity.this));
//
//                        return map;
//                    }
//                };
//                AppController.getInstance().addToRequestQueue(stringRequest);
//    }

    private String getRange(double cfm,double ahu) {

        String isSimple = "no";

        if (cfm <= 110 && ahu <= 110) {
            isSimple = "yes";
        } else if ((cfm >= 111 && cfm <= 210)&& (ahu >= 111 && ahu <= 210)) {
            isSimple = "yes";
        } else if ((cfm >= 211 && cfm <= 430) && (ahu >= 211 && ahu <= 430)) {
            isSimple = "yes";
        } else if ((cfm >= 431 && cfm <= 700) && (ahu >= 431 && ahu <= 700)) {
            isSimple = "yes";
        } else if ((cfm >= 701 && cfm <= 1100) && (ahu >= 701 && ahu <= 1100)) {
            isSimple = "yes";
        } else if ((cfm >= 1101 && cfm <= 1350) && (ahu >= 1101 && ahu <= 1350)) {
            isSimple = "yes";
        } else if ((cfm >= 1351 && cfm <= 1900) && (ahu >= 1351 && ahu <= 1900)) {
            isSimple = "yes";
        } else if ((cfm >= 1901 && cfm <= 2200)&& (ahu >= 1901 && ahu <= 2200)) {
            isSimple = "yes";
        } else if ((cfm >= 2201 && cfm <= 2800) && (ahu >= 2201 && ahu <= 2800)) {
            isSimple = "yes";
        } else if ((cfm >= 2801 && cfm <= 3300) && (ahu >= 2801 && ahu <= 3300)) {
            isSimple = "yes";
        } else if ((cfm >= 3301 && cfm <= 3800) && (ahu >= 3301 && ahu <= 3800)) {
            isSimple = "yes";
        } else if ((cfm >= 3801 && cfm <= 4100) && (ahu >= 3801 && ahu <= 4100)) {
            isSimple = "yes";
        } else if ((cfm >= 4101 && cfm <= 4900) && (ahu >= 4101 && ahu <= 4900)) {
            isSimple = "yes";
        } else if ((cfm >= 4901 && cfm <= 5500) && (ahu >= 4901 && ahu <= 5500)) {
            isSimple = "yes";
        } else if ((cfm >= 5501 && cfm <= 6500) && (ahu >= 5501 && ahu <= 6500)) {
            isSimple = "yes";
        } else if ((cfm >= 6501 && cfm <= 8000) && (ahu >= 6501 && ahu <= 8000)) {
            isSimple = "yes";
        } else if ((cfm >= 8001 && cfm <= 10500) && (ahu >= 8001 && ahu <= 10500)) {
            isSimple = "yes";
        } else if ((cfm >= 10501 && cfm <= 13000) && (ahu >= 10501 && ahu <= 13000)) {
            isSimple = "yes";
        } else if ((cfm >= 13001 && cfm <= 17000) && (ahu >= 13001 && ahu <= 17000)) {
            isSimple = "yes";
        } else if ((cfm >= 17001 && cfm <= 21000) && (ahu >= 17001 && ahu <= 21000)) {
            isSimple = "yes";
        }
        else {
            isSimple = "no";
        }
        return isSimple;
    }

    private void findId(){
        btnqtosw= (Button) findViewById(R.id.btn_nextosw);

        txtoswhead=(TextView) findViewById(R.id.txt_osw_head);

        osw_model=(TextView) findViewById(R.id.osw_model);
        osw_capacity=(TextView) findViewById(R.id.osw_capacity);
        osw_demodifier=(TextView) findViewById(R.id.osw_demodifier);

        osw4id=(TextView) findViewById(R.id.osw4id);
        osw5id=(TextView) findViewById(R.id.osw5id);
        osw6id=(TextView) findViewById(R.id.osw6id);

        spnr_pre_cool= (Spinner) findViewById(R.id.spnr_pre_cool);
        spnr_after_cool= (Spinner) findViewById(R.id.spnr_after_cool);
        spnr_heat= (Spinner) findViewById(R.id.spnr_heat);

        radioheat = (RadioGroup) findViewById(R.id.radioheat);
        rg_control = (RadioGroup) findViewById(R.id.rg_control);

        txt_offer_no=(TextView) findViewById(R.id.txt_offer_no);
        btn_offer_no=(Button) findViewById(R.id.btn_offer_no);

        radioheat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    isOffer=true;
                }
            }
        });

        rg_control.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    isOffer=true;
                }

            }
        });

    }

    private  void getOfferNo (final Context con) {
        //Toast.makeText(con,"Driver status send....",Toast.LENGTH_LONG ).show();

        //final String driver_id = SaveLoginInformation.getdId();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.offer_noApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(con, response, Toast.LENGTH_LONG).show();
                        try {
                            JSONObject obj = new JSONObject(response);
                            //String status = obj.getString("status");
                            String r_number = obj.getString("price");

//                            if (status.equals("1")) {
                                isOffer=false;

                                // radioh1 = (RadioButton) radioheat.findViewById(radioheat.getCheckedRadioButtonId());
                                // get selected radio button from radioGroup
                                int selectedId = radioheat.getCheckedRadioButtonId();

                                // find the radiobutton by returned id
                                radioh1 = (RadioButton) radioheat.findViewById(selectedId);

               /* Toast.makeText(OcwActivity.this,
                        radioh1.getText(), Toast.LENGTH_SHORT).show();*/
                                heatSource=radioh1.getText().toString();
                                int selectedId1 = rg_control.getCheckedRadioButtonId();
                                // find the radiobutton by returned id
                                rg_radioh1 = (RadioButton) rg_control.findViewById(selectedId1);

                /*Toast.makeText(OcwActivity.this,
                        rg_radioh1.getText(), Toast.LENGTH_SHORT).show();*/
                                controlPanel=rg_radioh1.getText().toString();


                                if (rg_radioh1.getText().toString().contains("Basic Model")){
                                    controlPanelValue="B";
                                }
                                else if (rg_radioh1.getText().toString().contains("Ultra Intelligent Series")){
                                    controlPanelValue="UI";
                                }
                                if (radioh1.getText().toString().contains("Steam Coil with")){
                                    heatSourceValue="SE";
                                }
                                else if (radioh1.getText().toString().contains("Electric Heater")){
                                    heatSourceValue="E";
                                }
                                else if (radioh1.getText().toString().contains("Only Steam Coil")){
                                    heatSourceValue="S";
                                }
                                eOfferNo= r_number+"-"+controlPanelValue+"-"+heatSourceValue+"-"+psaValue+"-"+heatOffer+"-"+BasicInformation.getProjectadd().toString();
                                txt_offer_no.setText("Offer No.: "+r_number+"-"+controlPanelValue+"-"+heatSourceValue+"-"+psaValue+"-"+heatOffer+"-"+BasicInformation.getProjectadd().toString());

                                BasicInformation.setOffer(r_number+"-"+controlPanelValue+"-"+heatSourceValue+"-"+psaValue+"-"+heatOffer+"-"+BasicInformation.getProjectadd().toString());
                                BasicInformation.setPSA(psaValue);

//                            } else {
//                                Toast.makeText(con, status, Toast.LENGTH_LONG).show();
//                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(con, "Please check Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map =new HashMap<>();

                map.put("user_id", MyPreference.loadLoginid(OcwActivity.this));
                Log.d("user_id", MyPreference.loadLoginid(OcwActivity.this));
//                map.put("password",sPassword);
//                map.put("device_id",id);

                return map;
            }

        };
        //stringRequest.setShouldCache(false);// no caching url...{

        AppController.getInstance().addToRequestQueue(stringRequest);
       /* RequestQueue requestQueue = Volley.newRequestQueue(con);
        requestQueue.add(stringRequest);*/

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
}
