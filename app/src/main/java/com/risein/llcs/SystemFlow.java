package com.risein.llcs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.risein.llcs.utils.Apis;
import com.risein.llcs.utils.AppUtils;
import com.risein.llcs.utils.BasicInformation;
import com.risein.llcs.utils.InternetStatus;
import com.risein.llcs.utils.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Risein on 9/28/2016.
 */

public class SystemFlow extends AppCompatActivity {

    TextView ddry,drh,dgr,requddry,requdrh,requdgr,flow_area,flow_model,flow_rload,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,t1,t2,t3,t4,t5,
            t6,t7,t8,t9,t10,t11,abs1,abs2,abs3,abs4,abs5,abs6,abs7,abs8,abs9,abs10,abs11;

    TextView react_load,steam;
    TextView enthe_amb,t_enth_at,enth_at,precoil_tor,postcoil_tor;
    TextView media_eff,current_kw,after_recovery,hr_saving;
    TextView media_eff2,current_kw2,after_recovery2,hr_saving2;
    ImageView imageViewsf;
    LinearLayout sFlownext;

    public static String datatValue;

    public static double t7Value=0.0;

    public static double afterRecovery=0.0;
    public static double outLet_value=0.0;
    public static double dehumidifierValue=0.0;
    public static double a3Value=0.0;
    public static double a4Value=0.0;
    public static String a9Value;
    public static double a5Value=0.0;
    public static double a8Value=0.0;
    public static double a10Value=0.0;

    public static double ReactLoad=0.0;
    public static double Steam=0.0;
    public static double ddryValue=0.0;
    public static double ddryValue1 =0.0;
    public static double drhValue1=0.0;
    public static double t6Value=0.0;
    public static double t8Value=0.0;

    public static double abs6Value=0.0;
    public static double abs8Value=0.0;

    public static String Enthalpy1="0.0";

    //Precoil TOR
    public static Double precoil=0.0;
    //Post Coil TOR
    public static Double postCoil=0.0;
    //Current kW
    public static Double current=0.0;
    public static Double current2=0.0;

    public static double hrSavings=0.0;
    public static double hrSavings2=0.0;

    public static String mediaEfficiency="0.50";

    String t9Value;



    //public static final String LOGIN_URL = "http://xucontact.com/api/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_flow);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in);
 /*add toolbar to actionbar*/
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        datatValue = getIntent().getExtras().getString("temp_value");

        BasicInformation.setTemp_val(datatValue);

        ddry= (TextView) findViewById(R.id.d_dry);
        drh= (TextView) findViewById(R.id.d_rh);
        dgr= (TextView) findViewById(R.id.d_gr);

        requddry= (TextView) findViewById(R.id.requ_d_dry);
        requdrh= (TextView) findViewById(R.id.requ_d_rh);
        requdgr= (TextView) findViewById(R.id.requ_d_gr);

        imageViewsf= (ImageView) findViewById(R.id.imageViewsf);
        flow_area= (TextView) findViewById(R.id.flow_area);
        flow_model= (TextView) findViewById(R.id.flow_model);
        flow_rload= (TextView) findViewById(R.id.flow_rload);

        a1= (TextView) findViewById(R.id.air1);
        a2= (TextView) findViewById(R.id.air2);
        a3= (TextView) findViewById(R.id.air3);
        a4= (TextView) findViewById(R.id.air4);
        a5= (TextView) findViewById(R.id.air5);
        a6= (TextView) findViewById(R.id.air6);
        a7= (TextView) findViewById(R.id.air7);
        a8= (TextView) findViewById(R.id.air8);
        a9= (TextView) findViewById(R.id.air9);
        a10= (TextView) findViewById(R.id.air10);
        a11= (TextView) findViewById(R.id.air11);


        abs1= (TextView) findViewById(R.id.abs1);
        abs2= (TextView) findViewById(R.id.abs2);
        abs3= (TextView) findViewById(R.id.abs3);
        abs4= (TextView) findViewById(R.id.abs4);
        abs5= (TextView) findViewById(R.id.abs5);
        abs6= (TextView) findViewById(R.id.abs6);
        abs7= (TextView) findViewById(R.id.abs7);
        abs8= (TextView) findViewById(R.id.abs8);
        abs9= (TextView) findViewById(R.id.abs9);
        abs10= (TextView) findViewById(R.id.abs10);
        abs11= (TextView) findViewById(R.id.abs11);


        t1= (TextView) findViewById(R.id.t1);
        t2= (TextView) findViewById(R.id.t2);
        t3= (TextView) findViewById(R.id.t3);
        t4= (TextView) findViewById(R.id.t4);
        t5= (TextView) findViewById(R.id.t5);
        t7= (TextView) findViewById(R.id.t7);
        t6= (TextView) findViewById(R.id.t6);
        t8= (TextView) findViewById(R.id.t8);
        t9= (TextView) findViewById(R.id.t9);
        t10= (TextView) findViewById(R.id.t10);
        t11= (TextView) findViewById(R.id.t11);


        react_load= (TextView) findViewById(R.id.react_load);
        steam= (TextView) findViewById(R.id.steam);

        enthe_amb= (TextView) findViewById(R.id.enthe_amb);
        t_enth_at=(TextView) findViewById(R.id.t_enth_at);
        enth_at= (TextView) findViewById(R.id.enth_at);
        precoil_tor= (TextView) findViewById(R.id.precoil_tor);
        postcoil_tor= (TextView) findViewById(R.id.postcoil_tor);

        media_eff= (TextView) findViewById(R.id.media_eff);
        current_kw= (TextView) findViewById(R.id.current_kw);
        after_recovery= (TextView) findViewById(R.id.after_recovery);
        hr_saving= (TextView) findViewById(R.id.hr_saving);

        media_eff2= (TextView) findViewById(R.id.media_eff2);
        current_kw2= (TextView) findViewById(R.id.current_kw2);
        after_recovery2= (TextView) findViewById(R.id.after_recovery2);
        hr_saving2= (TextView) findViewById(R.id.hr_saving2);
       /* imageViewsf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showFullImageDialog(SystemFlow.this,R.drawable.systempro,"System Flow");
            }
        });*/


        String valueFaq=BasicInformation.getFaq().toString();
        String valueahu=BasicInformation.getAhu().toString();


        dehumidifierValue=Double.parseDouble(BasicInformation.getDehumidifier().toString());
        /*double a3Value=0.0;
         double a4Value=0.0;
         String a9Value;
         double a5Value=0.0;
         double a8Value=0.0;
         double a10Value=0.0;
        double outLet_value=0.0;*/
        if (BasicInformation.getOutlet().toString().isEmpty()){
            outLet_value=Double.parseDouble(BasicInformation.getROutlet().toString());
        }
        else {
            outLet_value =Double.parseDouble(BasicInformation.getOutlet().toString());
        }


        if (!BasicInformation.getDehumidifier().toString().isEmpty()&& !BasicInformation.getFaq().toString().isEmpty()){

            if (Double.parseDouble(BasicInformation.getFaq().toString())>Double.parseDouble(BasicInformation.getDehumidifier().toString())){

                dehumidifierValue=Double.parseDouble(valueFaq);
                a4Value=dehumidifierValue-Double.parseDouble(valueFaq);
                BasicInformation.setSflowDehumidifier(String.format("%.2f", dehumidifierValue));
                String modelNo = InputActivity.getModel(dehumidifierValue);
                BasicInformation.setModel(modelNo);
            }


        else {
            dehumidifierValue=Double.parseDouble(BasicInformation.getDehumidifier().toString());
            a4Value=dehumidifierValue-Double.parseDouble(valueFaq);
        }
        }



        if (Double.parseDouble(BasicInformation.getAhu().toString())<dehumidifierValue){
            a9Value=String.valueOf(dehumidifierValue);

        }

        else {
            a9Value=BasicInformation.getAhu().toString();
        }

        if (!valueFaq.equals("0")&&!valueahu.equals("0")){
            a3Value=Double.parseDouble(valueahu)-Double.parseDouble(valueFaq);
        }
        else {
            a3Value=dehumidifierValue-Double.parseDouble(BasicInformation.getFaq().toString());
        }

        a5Value=Double.parseDouble(a9Value)-dehumidifierValue;
        //a8Value=a5Value+dehumidifierValue;
        a8Value=Double.parseDouble(a9Value);

        a10Value=dehumidifierValue/3;


        //React Load (KW)
       /* double ReactLoad=0.0;
        double Steam=0.0;*/
        ReactLoad=((284-Double.parseDouble(BasicInformation.getAcDbt().toString()))*1.08*a10Value)/3412;
        Steam=((284-Double.parseDouble(BasicInformation.getAcDbt().toString()))*1.08*a10Value)/(900*2.205);

        //afterRecovery=(ReactLoad-((((Double.parseDouble(mediaEfficiency)*(208-Double.parseDouble(BasicInformation.getAcDbt().toString())))+Double.parseDouble(BasicInformation.getAcDbt().toString()))*1.08*a10Value)/3412));

         double srStep1=208-Double.parseDouble(BasicInformation.getAcDbt().toString());
        double srStep2=Double.parseDouble(mediaEfficiency)*srStep1;
        double srStep3=srStep2+Double.parseDouble(BasicInformation.getAcDbt().toString());
        double srStep4=284-srStep3;
        double srStep5=srStep4*1.08*a10Value;
        double srStep6=srStep5/3412;
        afterRecovery=srStep6;
        //afterRecovery =(284-((((Double.parseDouble(mediaEfficiency)*(208-Double.parseDouble(BasicInformation.getAcDbt().toString())))+Double.parseDouble(BasicInformation.getAcDbt().toString()))*1.08*a10Value)/3412));


         ddryValue=Double.parseDouble(BasicInformation.getDcDbt().toString());
        double drhValue=Double.parseDouble(BasicInformation.getDcRh().toString());

         ddryValue1=Double.parseDouble(BasicInformation.getDcDbt().toString())+3.6;
         drhValue1=Double.parseDouble(BasicInformation.getDcRh().toString())+5;

        ddry.setText(BasicInformation.getDcDbt().toString().toString());
        drh.setText(BasicInformation.getDcRh().toString().toString());
        dgr.setText(BasicInformation.getDcGr().toString());


        requddry.setText(String.format("%.2f",ddryValue1));
        requdrh.setText(String.format("%.2f",drhValue1));
        requdgr.setText(String.format("%.2f", Double.parseDouble(BasicInformation.getDcGr2().toString().toString())));

        flow_area.setText(BasicInformation.getArea().toString());
        flow_model.setText(BasicInformation.getModel().toString());
        flow_rload.setText(BasicInformation.getLatentLoad().toString());

        a1.setText(BasicInformation.getFaq().toString());
        a2.setText(BasicInformation.getFaq().toString());
        a3.setText(String.format("%.2f",a3Value));
        a4.setText(String.format("%.2f",a4Value));
        a5.setText(String.format("%.2f",a5Value));
        a6.setText(String.format("%.2f",dehumidifierValue));

        a7.setText(String.format("%.2f",dehumidifierValue));
        //a8.setText(String.format("%.2f",a8Value));
        a8.setText(a9Value);
        a9.setText(a9Value);
        a10.setText(String.format("%.2f",a10Value));
        a11.setText(String.format("%.2f",a10Value));

       /* double t6Value=0.0;
        double t8Value=0.0;*/


        t6Value=(a4Value*ddryValue+Double.parseDouble(BasicInformation.getFaq().toString())*Double.parseDouble(BasicInformation.getFaqDry().toString()))/dehumidifierValue;

        t8Value=(dehumidifierValue*Double.parseDouble(datatValue)+a5Value*ddryValue)/a8Value;

        //t8Value=(dehumidifierValue*0.0+a5Value*ddryValue)/a8Value;

        t1.setText(BasicInformation.getAcDbt().toString());
        t2.setText(BasicInformation.getFaqDry().toString());
        t3.setText(String.format("%.2f",ddryValue));
        t4.setText(String.format("%.2f",ddryValue));
        t5.setText(String.format("%.2f",ddryValue));

        t6.setText(String.format("%.2f",t6Value));

        t7.setText(datatValue);
//        t7.setText(String.format("%.2f",datatValue));


        t8.setText(String.format("%.2f",t8Value));
        //t9.setText(BasicInformation.getAdp().toString());
        if(BasicInformation.getAdp().toString().equals("")||BasicInformation.getAdp().toString().equals(null)||BasicInformation.getAdp().toString().equals("0")){
            t9.setText("55");
            t9Value="55";
        }
        else {
            t9.setText(BasicInformation.getAdp().toString());
            t9Value=BasicInformation.getAdp().toString();
        }
        t10.setText(BasicInformation.getAcDbt().toString());
        t11.setText("284");


        /*double abs6Value=0.0;
        double abs8Value=0.0;*/

        abs6Value=(a4Value*Double.parseDouble(BasicInformation.getDcGr().toString())+Double.parseDouble(BasicInformation.getFaq().toString())*Double.parseDouble(BasicInformation.getPreGr().toString()))/dehumidifierValue;
        //abs6Value=(Double.parseDouble(BasicInformation.getDcDbt().toString())*Double.parseDouble(BasicInformation.getDcGr().toString())+Double.parseDouble(BasicInformation.getFaqDry().toString())*Double.parseDouble(BasicInformation.getPreGr().toString()))/t6Value;

        abs8Value=(dehumidifierValue
                *outLet_value
                +a5Value
                *Double.parseDouble(BasicInformation.getDcGr().toString()))/Double.parseDouble(a9Value);


        abs1.setText(BasicInformation.getAcGr().toString());
        abs2.setText(BasicInformation.getPreGr().toString());
        abs3.setText(BasicInformation.getDcGr().toString());
        abs4.setText(BasicInformation.getDcGr().toString());
        abs5.setText(BasicInformation.getDcGr().toString());
        abs6.setText(String.format("%.2f",abs6Value));
        abs7.setText(String.format("%.2f",outLet_value));
        abs8.setText(String.format("%.2f",abs8Value));
        abs9.setText(String.format("%.2f",abs8Value));
        abs10.setText(BasicInformation.getAcGr().toString());

        //React Load (KW)
       /* double ReactLoad=0.0;
        double Steam=0.0;
        ReactLoad=((284-Double.parseDouble(BasicInformation.getAcDbt().toString()))*1.08*a10Value)/3412;
        Steam=((284-Double.parseDouble(BasicInformation.getAcDbt().toString()))*1.08*a10Value)/(900*2.205);
*/
        react_load.setText(String.format("%.2f",ReactLoad));
        steam.setText(String.format("%.2f",Steam));
        //Enthalpy ambient


         String Enthalpy=BasicInformation.getAcBtu().toString();

        t_enth_at.setText("Enthalpy at "+BasicInformation.getFaqDry() + "F");

        //Enthalpy at 57 F
       /* String Enthalpy1="0.0";*/
        if (Double.parseDouble(BasicInformation.getFaqDry())==55){
            Enthalpy1="23.378";
        }
        else {
            Enthalpy1=BasicInformation.getFaqBtu().toString();
        }




        enthe_amb.setText(Enthalpy);
        enth_at.setText(Enthalpy1);

        /*//Precoil TOR
        Double precoil=0.0;*/

        if (InputActivity.isPrecool==true){
            double prStep1=Double.parseDouble(BasicInformation.getAcBtu().toString())-Double.parseDouble(BasicInformation.getFaqBtu().toString());
            double prStep2=(prStep1*60)/14;
            double prStep3=prStep2*Double.parseDouble(BasicInformation.getFaq().toString());
            //double prStep4=prStep3/12000;
            //precoil=((Double.parseDouble(BasicInformation.getAcBtu().toString())-Double.parseDouble(BasicInformation.getFaqBtu().toString()))*(60/14)*Double.parseDouble(BasicInformation.getFaq().toString()))/12000;
            precoil=prStep3/12000;;
            precoil_tor.setText(String.format("%.2f",precoil));
        }
        else {

            String nonPre="N/A";
            precoil_tor.setText(nonPre);
        }
        /*//Post Coil TOR
        Double postCoil=0.0;*/
        postCoil=(t8Value-Double.parseDouble(t9Value))*(1.08*Double.parseDouble(a9Value))/12000;
        postcoil_tor.setText(String.format("%.2f",postCoil));

        //With Heat Recovery
        //Media Efficiency


        media_eff.setText(mediaEfficiency);
        media_eff2.setText(mediaEfficiency);
        /*//Current kW
        Double current=0.0;*/
        current=ReactLoad;
        current2=Steam;

        current_kw.setText(String.format("%.2f",current));
        current_kw2.setText(String.format("%.2f",current2));

        //After Recovery
        after_recovery.setText(String.format("%.2f",afterRecovery));
        after_recovery2.setText(String.format("%.2f",afterRecovery));
        //kW/hr Savings

       hrSavings=current-afterRecovery;
       hrSavings2=current2-afterRecovery;
        hr_saving.setText(String.format("%.2f",hrSavings));
        hr_saving2.setText(String.format("%.2f",hrSavings2));

        sFlownext= (LinearLayout) findViewById(R.id.sflow_lytnext);
        sFlownext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //userLogin();
                Intent inprint=new Intent(SystemFlow.this,QuotationActivity.class);
                startActivity(inprint);
            }
        });

    }


    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
   /* private void userLogin() {
       // username = editTextUsername.getText().toString().trim();
        //password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Log.e("Ar-res",""+response);
                        // Toast.makeText(LoginActivity.this,"***"+response+"**"+response.contains("ok"),Toast.LENGTH_LONG).show();



                            Toast.makeText(SystemFlow.this,response,Toast.LENGTH_LONG).show();
                        try {
                            JSONObject obj=new JSONObject(response);
                            String statement=obj.getString("statement");
                            Toast.makeText(SystemFlow.this,statement,Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                           *//* StringTokenizer tokens = new StringTokenizer(response,":");
                            String first = tokens.nextToken();
                            String second = tokens.nextToken();
                            Log.d("v1",""+first+"vinat"+second);*//*


                            *//*Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("UserName" ,response);
                            Log.e("Ar-setUsername",""+username);
                            editor.commit();
                            startActivity(intent);
                            editTextPassword .setText("");*//*


                            *//*editTextPassword.setError("Check Password");*//*
                            //  Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SystemFlow.this,"Please check InterNet Connection",Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                //  map.put("rep_id",username);
                // map.put("password",password);
                map.put("username","swati123");
                map.put("password","swati");
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.e("arch",""+stringRequest);
    }
*/



    public class SendTempAsync extends AsyncTask<String, JSONObject, JSONObject> {
        Context context;
        String designgr, dbt;
        public double outlet_output;

        public SendTempAsync(Context context, String designgr, String dbt) {
            this.context = context;
            this.designgr = designgr;
            this.dbt = dbt;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonObject;
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("temp", dbt);
            hashMap.put("gradient", designgr);
            JSONParser jsonParser = new JSONParser();
            jsonObject = jsonParser.makeHttpRequest(Apis.temp_outApi, "GET", hashMap);

            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject obj) {
            super.onPostExecute(obj);
            double outlet_output = 0;
            try {
                String status = obj.getString("status");
                String msg = obj.getString("msg");


                if (status.equals("1")) {
                    outlet_output = Double.valueOf(obj.getString("moister"));
                    Toast.makeText(context, "" + outlet_output, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                }
                t7= (TextView) findViewById(R.id.t7);
                t7Value=outlet_output;
                t7.setText(String.format("%.2f",outlet_output));
                Log.w("else data", "else temp output" + outlet_output);
                //printCalculation(outlet_output);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
