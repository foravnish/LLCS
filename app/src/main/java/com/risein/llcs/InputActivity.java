package com.risein.llcs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.risein.llcs.utils.Apis;
import com.risein.llcs.utils.BasicInformation;
import com.risein.llcs.utils.InputInformation;
import com.risein.llcs.utils.InternetStatus;
import com.risein.llcs.utils.JSONParser;
import com.risein.llcs.utils.MyPreference;
import com.risein.llcs.utils.SendValueAsync;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Risein on 9/20/2016.
 */

public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    double PERMEATION_LOAD, OCCUPACY, INFILTERATION_LOAD, PRODUCT_LOAD, ANYOTHER_LOAD, TOTAL_LOAD, SAFETY_FACTOR,
            LATENT_M_LOAD, FRESH_AIR_LOAD, TOTAL_LATENT, DC_Cfm,MIXED_GR, M_RECOMMENDED;
    RadioGroup rgImpSi;
    RadioButton radioImp, radioSi, rBtnGroup2, rBtnGroup3;


    Button btnshow, btnUpdate, btnPy, btnew, bm_btn0, bm_btn1, bm_btn2, bm_btn3,pp_output,btu_output,
            bm_btn4, bm_btn5, bm_btn6, bm_btn7, bm_btn8, bm_btn9, dl_btn0, dl_btn1, dl_btn2, dl_btn3, dl_btn4, dl_btn5,
            fod_btn0, fod_btn1, fod_btn2, fod_btn3, fod_btn4, fod_btn5;
    LinearLayout dmlyt0, dmlyt1, dmlyt2, dmlyt3, dmlyt4, dmlyt5, fod_lyt0, fod_lyt1, fod_lyt2, fod_lyt3, fod_lyt4, fod_lyt5,
            dmlyt6, dmlyt7, dmlyt8, dmlyt9, dllyt0, dllyt1, dllyt2, dllyt3, dllyt4, dllyt5;
    EditText bm_l0, bm_w0, bm_h0, bm_l1, bm_w1, bm_h1, bm_l2, bm_w2, bm_h2, bm_l3, bm_w3, bm_h3, bm_l4, bm_w4, bm_h4, bm_l5, bm_w5, bm_h5,
            bm_l6, bm_w6, bm_h6, bm_l7, bm_w7, bm_h7, bm_l8, bm_w8, bm_h8, bm_l9, bm_w9, bm_h9, dlw0, dlh0, dldop0, dlw1, dlh1, dldop1, dlw2, dlh2, dldop2,
            dlw3, dlh3, dldop3, dlw4, dlh4, dldop4, dlw5, dlh5, dldop5, fod_l0, fod_w0, fod_d0, fod_l1, fod_w1, fod_d1, fod_l2, fod_w2, fod_d2,
            fod_l3, fod_w3, fod_d3, fod_l4, fod_w4, fod_d4, fod_l5, fod_w5, fod_d5;

    TextView bm_v0, bm_v1, bm_v2, bm_v3, bm_v4, bm_v5, bm_v6, bm_v7, bm_v8, bm_v9, fod_v0, fod_v1, fod_v2, fod_v3, fod_v4, fod_v5,p_outpressure,btu_cfm;


    private EditText inDrybulb, inWetbulb, designDry, designwet, inRh, designRh, inPll, inNumberofperson, outDoorDrybulb, outDoorWetbulb, ophDoor1, ophDoor2, ophDoor3, ophDoor4,
            occupancy, fixedopningDrybulb, fixedopningWetbulb, fixedopningRh,p_pressure, faqCfm, faqDrybulb, faqWetbulb, faqRh, outDoorRh,exhaust_edt,
            eaqCfm, ahuCapacity, infiltrationLaqCfm, iacDrybulb, iacWetbulb, iacRh, plImc, plFmc, plWt, plBywt, plDirLoad, plDt, inHlea,
            designAdp, inEspr, inFiltration;

    EditText designDry2, designwet2, designRh2;
    TextView designbtu2,designGr2;

    DecimalFormat decimalFormat;
    /*inGr,inEnthalpy,designGr,designbtu,*/
   /* ,faqGr,faqeEnthalpy*/
    /*,outDoorGr*/
    /*fixedopningGr*
     */


    public static boolean isPrecool=false;
    boolean isIsclick = false, isaddList = false, ispload = false;

    private TextView inGr, inEnthalpy, getvalue, topF1, inF2, txtTvolume, f3value, f4value, btnnext1, outDoorf1, cofoF1,
            txtTlength, txtTwidth, txtThight, fdf1, designGr, designbtu, deltaGr, activityvalue, outDoorGr, outDoordeltaGr,
            iacGr, faqGr, faqEnthalpy, iacdeltaGr, faqdeltaGr, fixedopningdeltaGr, faqeEnthalpy, fixedopningGr;

    private Spinner spinner1, spinner2, apnrActivityLevel;

    ToggleButton codToggle, cofoToggle, faqToggle;

    private double TOTAL_VOLUME = 0, TOTAL_LENGTH = 0, TOTAL_WIDTH = 0, TOTAL_HEIGHT = 0;
    //double ingr = 0, dgr = 0;

    private double  doorTotalWidth=0,doorTotalHeight=0, fixedTotalWidth=0,fixedTotalHeight=0;

    boolean isclick = false, isfixedopningAc = false, isCodAc = false, isfixedclick = false;

    private Button btn, dlbtn, btnfixed, btnback, btn_nextone, btnproductload, psyreset, psycal, psycalDesign,psycalDesign2, psyresetDesign,
            codcal, codreset, cofocal, coforeset, faqcal, faqreset, iaccal, iacreset;


    LinearLayout lytpload, codbtnlyt, cofobtnlyt;
    int i = 0, t = 0, t1 = 0, fix = 0, counter = 0, dlcounter = 0, fodcounter = 0;
    int m = 0, dr = 0, fdr = 0, activityPos = 0;
    // local members
    String f3Item, f4Item, ssportItem3;
    String siImp = "";
    private double outlet_output;
    //class members
    private String[] f3data;
    private String[] f4data;
    private String[] activitylevel;
    BasicInformation bim;
    ArrayList<HashMap<String, String>> hwhdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_sheet2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in);
 /*add toolbar to actionbar*/
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inputField();
        // hideKeyboard();
        hwhdata = new ArrayList<>();
        hwhdata.clear();
        f3data = getResources().getStringArray(R.array.f3_list);
        f4data = getResources().getStringArray(R.array.f4_list);
        activitylevel = getResources().getStringArray(R.array.activity_level);
        inF2.setText("0.0");

        radioSi = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());
        radioImp = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());
        siImp = radioSi.getText().toString();
        // onlyGr();
        //grGalculation();
        // inputPsy();
        //dynamicView(btn);
        //dynamicDView(btn2);
        //fixedDView(btnfixed);
        fFactors();
        redioChange();
        productLoad();
        //setZero();
        removeClick();
        dmData();
        String name12 = bim.getCname();


    }


    public void fFactors() {
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, f3data);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, f4data);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);

        ArrayAdapter<String> dataActivityLevel = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, activitylevel);
        dataActivityLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        apnrActivityLevel.setAdapter(dataActivityLevel);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                f3Item = adapter.getItemAtPosition(position).toString();

                if (position == 1) {
                    f3value.setText("1.0");
                } else if (position == 2) {
                    f3value.setText("0.3");
                } else if (position == 3) {
                    f3value.setText("0.5");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                f4Item = adapter.getItemAtPosition(position).toString();

                if (position == 1) {
                    f4value.setText("0.50");
                } else if (position == 2) {
                    f4value.setText(" 0.67");
                } else if (position == 3) {
                    f4value.setText("0.75");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        apnrActivityLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                activityvalue.setText("0.0");
                activityPos = position;
                //Toast.makeText(InputActivity.this, ""+apnrActivityLevel.getSelectedItem(), Toast.LENGTH_SHORT).show();
                InputInformation.setActivitylevel(apnrActivityLevel.getSelectedItem().toString());
                //Toast.makeText(InputActivity.this,"position is = "+position,Toast.LENGTH_LONG).show();

                actvityLevelValue(designDry, activityvalue, activityPos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }


    // only gr value

    private void productLoad() {


        plImc.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {

                    if (!(plFmc.getText().length() == 0) && !(plDt.getText().length() == 0)) {

                        double imcValue = Double.parseDouble(plImc.getText().toString());
                        double fmcValue = Double.parseDouble(plFmc.getText().toString());
                        double dtValue = Double.parseDouble(plDt.getText().toString());
                        double diffKgs = imcValue - fmcValue;
                        double productOutput = diffKgs / dtValue;
                        inPll.setText(String.valueOf(productOutput));

                    }
                }
            }
        });

        plFmc.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {

                    if (!(plImc.getText().length() == 0) && !(plDt.getText().length() == 0)) {

                        double imcValue = Double.parseDouble(plImc.getText().toString());
                        double fmcValue = Double.parseDouble(plFmc.getText().toString());
                        double dtValue = Double.parseDouble(plDt.getText().toString());
                        double diffKgs = imcValue - fmcValue;
                        double productOutput = diffKgs / dtValue;
                        inPll.setText(String.valueOf(productOutput));

                    }
                }
            }
        });

        plDt.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {

                    if (!(plImc.getText().length() == 0) && !(plFmc.getText().length() == 0)) {

                        double imcValue = Double.parseDouble(plImc.getText().toString());
                        double fmcValue = Double.parseDouble(plFmc.getText().toString());
                        double dtValue = Double.parseDouble(plDt.getText().toString());
                        double diffKgs = imcValue - fmcValue;
                        double productOutput = diffKgs / dtValue;
                        inPll.setText(String.valueOf(productOutput));

                    }
                }
            }
        });


    }

    //in SI
    private void getPsyIs(EditText tdb, EditText twb, EditText rh, TextView Wgr, TextView Henth) {
        DecimalFormat form = new DecimalFormat("0.00");
        Log.w("tdb method is : ", " " + tdb.getText().toString());
        Log.w("twb method is : ", " " + twb.getText().toString());
        if (!(tdb.getText().toString().matches("")) && !(twb.getText().toString().matches(""))) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", SI = "SI";
            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtTwb = Double.parseDouble(twb.getText().toString());


            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, edtTdb, Twb, edtTwb, h, SI);
            double psyGr = PsyCalculation.psych(101325, Tdb, edtTdb, Twb, edtTwb, W, SI);
            double psyRh = PsyCalculation.psych(101325, Tdb, edtTdb, Twb, edtTwb, RH, SI);

            double outputRr = psyRh * 100;

            Henth.setText("" + psyEnthalpy);
            Wgr.setText("" + psyGr);
            rh.setText("" + outputRr);
        } else if ((!tdb.getText().toString().isEmpty()) && (!rh.getText().toString().isEmpty())) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", SI = "SI";

            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtRh = Double.parseDouble(rh.getText().toString());
            double outputRh = edtRh / 100;

            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, edtTdb, RH, outputRh, h, SI);
            double psyGr = PsyCalculation.psych(101325, Tdb, edtTdb, RH, outputRh, W, SI);
            double psyTwb = PsyCalculation.psych(101325, Tdb, edtTdb, RH, outputRh, Twb, SI);

            Henth.setText(String.format("%.2f", psyEnthalpy));
            Wgr.setText(String.format("%.2f", psyGr));
            twb.setText(String.format("%.2f", psyTwb));

        }

    }


    //in IMP
    private void getPsyImp(EditText tdb, EditText twb, EditText rh, TextView Wgr, TextView Henth) {
        Log.d("fdgdfghdfgdhgfd","true");
        DecimalFormat form = new DecimalFormat("0.00");
        if (!(tdb.getText().toString().matches("")) && !(twb.getText().toString().matches(""))) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", imp = "SI";



            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtTwb = Double.parseDouble(twb.getText().toString());
            double impTdb = (edtTdb - 32) / 1.8;
            double impTwb = (edtTwb - 32) / 1.8;

            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, impTdb, Twb, impTwb, h, imp);
            double psyGr = PsyCalculation.psych(101325, Tdb, impTdb, Twb, impTwb, W, imp);
            double psyRh = PsyCalculation.psych(101325, Tdb, impTdb, Twb, impTwb, RH, imp);


            double outputGr = (15.4324 * 453.592 * psyGr);
            double outputRr = psyRh * 100;
            double psyEnthalpy1=(psyEnthalpy * 0.429923);
            double outputEnt = psyEnthalpy1+8;
            //double outputEnt = (psyEnthalpy * 0.429923);

            Henth.setText(String.format("%.2f", outputEnt));
            Wgr.setText(String.format("%.2f", outputGr));
            Log.d("gfdhfddhgfhfg1", String.valueOf(outputGr));
            rh.setText(String.format("%.2f", outputRr));
        } else if ((!tdb.getText().toString().isEmpty()) && (!rh.getText().toString().isEmpty())) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", imp = "SI";

            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtRh = Double.parseDouble(rh.getText().toString());
            double impTdb = (edtTdb - 32) / 1.8;
            double outputRh = edtRh / 100;
            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, impTdb, RH, outputRh, h, imp);
            double psyGr = PsyCalculation.psych(101325, Tdb, impTdb, RH, outputRh, W, imp);
            double psyTwb = PsyCalculation.psych(101325, Tdb, impTdb, RH, outputRh, Twb, imp);

            double outputGr = (15.4324 * 453.592 * psyGr);
            double psyEnthalpy1=(psyEnthalpy * 0.429923);
            double outputEnt = psyEnthalpy1+8;
            //double outputEnt = (psyEnthalpy * 0.429923);
            double outputTwb = (psyTwb * 1.8) + 32;
           /* Henth.setText("" + outputEnt);
            Wgr.setText("" + outputGr);
            twb.setText("" + outputTwb);*/

            Henth.setText(String.format("%.2f", outputEnt));
            Wgr.setText(String.format("%.2f", outputGr));
            twb.setText(String.format("%.2f", outputTwb));
            Log.d("gfdhfddhgfhfg2", String.valueOf(outputGr));
        }

    }

    private void getPsyImp2(EditText tdb, EditText twb, EditText rh, TextView Wgr, TextView Henth) {
        Log.d("fdgdfghdfgdhgfd","true");
        DecimalFormat form = new DecimalFormat("0.00");
        if (!(tdb.getText().toString().matches("")) && !(twb.getText().toString().matches(""))) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", imp = "SI";



            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtTwb = Double.parseDouble(twb.getText().toString());
            double impTdb = (edtTdb - 32) / 1.8;
            double impTwb = (edtTwb - 32) / 1.8;

            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, impTdb, Twb, impTwb, h, imp);
            double psyGr = PsyCalculation.psych(101325, Tdb, impTdb, Twb, impTwb, W, imp);
            double psyRh = PsyCalculation.psych(101325, Tdb, impTdb, Twb, impTwb, RH, imp);


            double outputGr = (15.4324 * 453.592 * psyGr);
            double outputRr = psyRh * 100;
            double psyEnthalpy1=(psyEnthalpy * 0.429923);
            double outputEnt = psyEnthalpy1+8;
            //double outputEnt = (psyEnthalpy * 0.429923);

            Henth.setText(String.format("%.2f", outputEnt));
            Wgr.setText(String.format("%.2f", outputGr));
            Log.d("gfdhfddhgfhfg3", String.valueOf(outputGr));
            rh.setText(String.format("%.2f", outputRr));
            BasicInformation.setDcGr2(String.valueOf(outputGr));
            BasicInformation.setDcWbt2(String.format("%.2f", outputEnt));
            BasicInformation.setDcBtu2(String.format("%.2f", outputEnt));

        } else if ((!tdb.getText().toString().isEmpty()) && (!rh.getText().toString().isEmpty())) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", imp = "SI";

            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtRh = Double.parseDouble(rh.getText().toString());
            double impTdb = (edtTdb - 32) / 1.8;
            double outputRh = edtRh / 100;
            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, impTdb, RH, outputRh, h, imp);
            double psyGr = PsyCalculation.psych(101325, Tdb, impTdb, RH, outputRh, W, imp);
            double psyTwb = PsyCalculation.psych(101325, Tdb, impTdb, RH, outputRh, Twb, imp);

            double outputGr = (15.4324 * 453.592 * psyGr);
            double psyEnthalpy1=(psyEnthalpy * 0.429923);
            double outputEnt = psyEnthalpy1+8;
            //double outputEnt = (psyEnthalpy * 0.429923);
            double outputTwb = (psyTwb * 1.8) + 32;
           /* Henth.setText("" + outputEnt);
            Wgr.setText("" + outputGr);
            twb.setText("" + outputTwb);*/

            Henth.setText(String.format("%.2f", outputEnt));
            Wgr.setText(String.format("%.2f", outputGr));
            twb.setText(String.format("%.2f", outputTwb));

            BasicInformation.setDcGr2(String.valueOf(outputGr));
            BasicInformation.setDcWbt2(String.format("%.2f", outputTwb));
            BasicInformation.setDcBtu2(String.format("%.2f", outputEnt));

            Log.d("gfdhfddhgfhfg4", String.valueOf(outputGr));
        }

    }

    //only RH and Gr value

    private void getonlyRGPsyIs(EditText tdb, EditText twb, EditText rh, TextView Wgr) {
        DecimalFormat form = new DecimalFormat("0.00");

        if ((!tdb.getText().toString().isEmpty()) && (!twb.getText().toString().isEmpty())) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", SI = "SI";
            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtTwb = Double.parseDouble(twb.getText().toString());


            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, edtTdb, Twb, edtTwb, h, SI);
            double psyGr = PsyCalculation.psych(101325, Tdb, edtTdb, Twb, edtTwb, W, SI);
            double psyRh = PsyCalculation.psych(101325, Tdb, edtTdb, Twb, edtTwb, RH, SI);

            double outputRr = psyRh * 100;

            Wgr.setText(String.format("%.2f", psyGr));
            rh.setText(String.format("%.2f", outputRr));
        } else if ((!tdb.getText().toString().isEmpty()) && (!rh.getText().toString().isEmpty())) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", SI = "SI";

            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtRh = Double.parseDouble(rh.getText().toString());
            double outputRh = edtRh / 100;

            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, edtTdb, RH, outputRh, h, SI);
            double psyGr = PsyCalculation.psych(101325, Tdb, edtTdb, RH, outputRh, W, SI);
            double psyTwb = PsyCalculation.psych(101325, Tdb, edtTdb, RH, outputRh, Twb, SI);


            Wgr.setText(String.format("%.2f", psyGr));
            twb.setText(String.format("%.2f", psyTwb));

        }
    }
    //end this method


    //cod imp

    private void getonlyRGPsyImp(EditText tdb, EditText twb, EditText rh, TextView Wgr) {
        DecimalFormat form = new DecimalFormat("0.00");

        if ((!tdb.getText().toString().isEmpty()) && (!twb.getText().toString().isEmpty())) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", imp = "SI";


            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtTwb = Double.parseDouble(twb.getText().toString());
            double impTdb = (edtTdb - 32) / 1.8;
            double impTwb = (edtTwb - 32) / 1.8;

            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, impTdb, Twb, impTwb, h, imp);
            double psyGr = PsyCalculation.psych(101325, Tdb, impTdb, Twb, impTwb, W, imp);
            double psyRh = PsyCalculation.psych(101325, Tdb, impTdb, Twb, impTwb, RH, imp);


            double outputGr = (15.4324 * 453.592 * psyGr);
            double outputRr = psyRh * 100;
            double outputEnt = (psyEnthalpy * 0.429923);

            Wgr.setText(String.format("%.2f", outputGr));
            rh.setText(String.format("%.2f", outputRr));
        } else if ((!tdb.getText().toString().isEmpty()) && (!rh.getText().toString().isEmpty())) {
            String Tdb = "Tdb", Twb = "Twb", RH = "RH", W = "W", h = "h", imp = "SI";

            double edtTdb = Double.parseDouble(tdb.getText().toString());
            double edtRh = Double.parseDouble(rh.getText().toString());
            double impTdb = (edtTdb - 32) / 1.8;
            double outputRh = edtRh / 100;
            double psyEnthalpy = PsyCalculation.psych(101325, Tdb, impTdb, RH, outputRh, h, imp);
            double psyGr = PsyCalculation.psych(101325, Tdb, impTdb, RH, outputRh, W, imp);
            double psyTwb = PsyCalculation.psych(101325, Tdb, impTdb, RH, outputRh, Twb, imp);
            double outputGr = (15.4324 * 453.592 * psyGr);
            double outputEnt = (psyEnthalpy * 0.429923);
            double outputTwb = (psyTwb * 1.8) + 32;

            Wgr.setText(String.format("%.2f", outputGr));
            twb.setText(String.format("%.2f", outputTwb));

        }

    }


//

    private void getAllDeltaGr(TextView topGr, TextView bottomGr, TextView deltaText, TextView f1Txt) {


        if (siImp.equals("IMP")) {
            if (!(topGr.getText().toString().matches("")) && !(bottomGr.getText().toString()).matches("")) {

                double dgr = Double.parseDouble(bottomGr.getText().toString());
                Log.w("input activity", " " + dgr);
                double ingr = Double.parseDouble(topGr.getText().toString());
                Log.w("input activity>>", " " + ingr);
                //Math.subtractExact(ingr,dgr);
                double ar = ingr - dgr;
                String dvalue = String.valueOf(ar);
                deltaText.setText(String.format("%.2f", ar));
                fOneValue(deltaText, f1Txt);
            }
        } else if (siImp.equals("SI")) {
            if (!(topGr.getText().toString().matches("")) && !(bottomGr.getText().toString().matches(""))) {
                DecimalFormat df = new DecimalFormat("#.#");
                double dgr = Double.parseDouble(bottomGr.getText().toString());
                Log.w("input activity", " " + dgr);
                double ingr = Double.parseDouble(topGr.getText().toString());
                Log.w("input activity>>", " " + ingr);
                //Math.subtractExact(ingr,dgr);
                double ar = ((ingr * 15.4324 * 453.592) - (dgr * 15.4324 * 453.592));
                String dvalue = String.valueOf(ar);
                Log.w("ar value", "before: " + ar + ", after " + df.format(ar));
                deltaText.setText(df.format(ar));
                // deltaText.setText(String.format("%.2f", ar));
                fOneValue(deltaText, f1Txt);
                //codAcValue();
            }
        }


    }


    private static void resetInput(EditText tdb, EditText twb, EditText rh, TextView Wgr, TextView Henth) {

        tdb.getText().clear();
        twb.getText().clear();
        rh.getText().clear();
        Wgr.setText("0.0");
        Henth.setText("0.0");
    }

    private static void resetInputCod(EditText tdb, EditText twb, EditText rh, TextView Wgr) {

        tdb.getText().clear();
        twb.getText().clear();
        rh.getText().clear();
        Wgr.setText("0.0");
    }


    // Top f1 value

    private void fOneValue(TextView deltaValue, TextView f1Data) {

        if (deltaValue.getText().length() != 0) {

            String dra = deltaValue.getText().toString();
            double deltatop = Double.parseDouble(dra);
            Log.w("my data is >>", " " + dra);

            if (deltatop > 120.0) {

                double deltapls = deltatop - 120.0;
                double getF1 = 3.00 + deltapls * .024;

                f1Data.setText(String.format("%.2f", getF1));
            }
            else if (Double.valueOf(dra)<35.0) {
                f1Data.setText(R.string.f1_35);
            }
            else if (dra.equals("35.0")) {
                f1Data.setText(R.string.f1_35);
            } else if ((deltatop > 35.0) && (deltatop < 40.0)) {
                double topKey = 35.0;
                double bttomKey = 40.0;
                double topVal = 1.0;
                double bttomVal = 1.12;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("40.0")) {
                f1Data.setText(R.string.f1_40);
            } else if ((deltatop > 40.0) && (deltatop < 50.0)) {
                double topKey = 40.0;
                double bttomKey = 50.0;
                double topVal = 1.12;
                double bttomVal = 1.35;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("50.0")) {
                f1Data.setText(R.string.f1_50);
            } else if ((deltatop > 50.0) && (deltatop < 60.0)) {
                double topKey = 40.0;
                double bttomKey = 60.0;
                double topVal = 1.35;
                double bttomVal = 1.59;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("60.0")) {
                f1Data.setText(R.string.f1_60);
            } else if ((deltatop > 60.0) && (deltatop < 70.0)) {
                double topKey = 60.0;
                double bttomKey = 70.0;
                double topVal = 1.59;
                double bttomVal = 1.82;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("70.0")) {
                f1Data.setText(R.string.f1_70);
            } else if ((deltatop > 70.0) && (deltatop < 80.0)) {
                double topKey = 70.0;
                double bttomKey = 80.0;
                double topVal = 1.82;
                double bttomVal = 2.06;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("80.0")) {
                f1Data.setText(R.string.f1_80);
            } else if ((deltatop > 80.0) && (deltatop < 90.0)) {
                double topKey = 80.0;
                double bttomKey = 90.0;
                double topVal = 2.06;
                double bttomVal = 2.29;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("90.0")) {
                f1Data.setText(R.string.f1_90);
            } else if ((deltatop > 90.0) && (deltatop < 100.0)) {
                double topKey = 90.0;
                double bttomKey = 100.0;
                double topVal = 2.29;
                double bttomVal = 2.53;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("100.0")) {
                f1Data.setText(R.string.f1_100);
            } else if ((deltatop > 100.0) && (deltatop < 110.0)) {
                double topKey = 100.0;
                double bttomKey = 110.0;
                double topVal = 2.53;
                double bttomVal = 2.76;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("110.0")) {
                f1Data.setText(R.string.f1_110);
            } else if ((deltatop > 110.0) && (deltatop < 120.0)) {
                double topKey = 110.0;
                double bttomKey = 120.0;
                double topVal = 2.76;
                double bttomVal = 3.00;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("120.0")) {
                f1Data.setText(R.string.f1_120);
            }
                    /*else {
                        topF1.setText("2.0");
                    }*/

        }

    }


    // Top f1 value

    private void actvityLevelValue(EditText designDbt, TextView viewActivity, int pos) {

        Log.d("fdgdfghdfgdhgfd","true");
        if (designDbt.getText().length() != 0) {

            String dra = designDbt.getText().toString();
            double deltatop = Double.parseDouble(dra);
            Log.w("my data is >>", " " + dra);

            if (pos == 1) {

                if (deltatop > 90) {
                    viewActivity.setText("1870");
                } else if (dra.equals("60") || (deltatop < 60)) {
                    viewActivity.setText("400");
                } else if ((deltatop > 60) && (deltatop < 65)) {
                    double topKey = 60;
                    double bttomKey = 65;
                    double topVal = 400;
                    double bttomVal = 530;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getF1 = muldata + topVal;
                    String finalDelta = String.valueOf(getF1);

                    viewActivity.setText(String.format("%.2f", getF1));

                } else if (dra.equals("65")) {
                    viewActivity.setText("530");
                } else if ((deltatop > 65) && (deltatop < 70)) {
                    double topKey = 65;
                    double bttomKey = 70;
                    double topVal = 530;
                    double bttomVal = 670;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("70")) {
                    viewActivity.setText("670");
                } else if ((deltatop > 70) && (deltatop < 75)) {
                    double topKey = 70;
                    double bttomKey = 75;
                    double topVal = 670;
                    double bttomVal = 900;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("75")) {
                    viewActivity.setText("900");
                } else if ((deltatop > 75) && (deltatop < 80)) {
                    double topKey = 75;
                    double bttomKey = 80;
                    double topVal = 900;
                    double bttomVal = 1180;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("80")) {
                    viewActivity.setText("1180");
                } else if ((deltatop > 80) && (deltatop < 85)) {
                    double topKey = 80;
                    double bttomKey = 85;
                    double topVal = 1180;
                    double bttomVal = 1525;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("85")) {
                    viewActivity.setText("1525");
                } else if ((deltatop > 85) && (deltatop < 90)) {
                    double topKey = 85;
                    double bttomKey = 90;
                    double topVal = 1525;
                    double bttomVal = 1870;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("90")) {
                    viewActivity.setText("1870");

                }
            } else if (pos == 2) {

                if (deltatop > 90) {
                    viewActivity.setText("4000");
                } else if (dra.equals("60") || (deltatop < 60)) {
                    viewActivity.setText("1300");
                } else if ((deltatop > 60) && (deltatop < 65)) {
                    double topKey = 60;
                    double bttomKey = 65;
                    double topVal = 1300;
                    double bttomVal = 1630;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getF1 = muldata + topVal;
                    String finalDelta = String.valueOf(getF1);

                    viewActivity.setText(String.format("%.2f", getF1));

                } else if (dra.equals("65")) {
                    viewActivity.setText("1630");
                } else if ((deltatop > 65) && (deltatop < 70)) {
                    double topKey = 65;
                    double bttomKey = 70;
                    double topVal = 1630;
                    double bttomVal = 2060;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("70")) {
                    viewActivity.setText("2060");
                } else if ((deltatop > 70) && (deltatop < 75)) {
                    double topKey = 70;
                    double bttomKey = 75;
                    double topVal = 2060;
                    double bttomVal = 2540;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("75")) {
                    viewActivity.setText("2540");
                } else if ((deltatop > 75) && (deltatop < 80)) {
                    double topKey = 75;
                    double bttomKey = 80;
                    double topVal = 2540;
                    double bttomVal = 3040;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("80")) {
                    viewActivity.setText("3040");
                } else if ((deltatop > 80) && (deltatop < 85)) {
                    double topKey = 80;
                    double bttomKey = 85;
                    double topVal = 3040;
                    double bttomVal = 3550;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("85")) {
                    viewActivity.setText("3550");
                } else if ((deltatop > 85) && (deltatop < 90)) {
                    double topKey = 85;
                    double bttomKey = 90;
                    double topVal = 3550;
                    double bttomVal = 4000;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("90")) {
                    viewActivity.setText("4000");

                }


            } else if (pos == 3) {


                if (deltatop > 90) {
                    viewActivity.setText("5000");
                } else if (dra.equals("60") || (deltatop < 60)) {
                    viewActivity.setText("1960");
                } else if ((deltatop > 60) && (deltatop < 65)) {
                    double topKey = 60;
                    double bttomKey = 65;
                    double topVal = 1960;
                    double bttomVal = 2400;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getF1 = muldata + topVal;
                    String finalDelta = String.valueOf(getF1);

                    viewActivity.setText(String.format("%.2f", getF1));

                } else if (dra.equals("65")) {
                    viewActivity.setText("2400");
                } else if ((deltatop > 65) && (deltatop < 70)) {
                    double topKey = 65;
                    double bttomKey = 70;
                    double topVal = 2400;
                    double bttomVal = 2920;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("70")) {
                    viewActivity.setText("2920");
                } else if ((deltatop > 70) && (deltatop < 75)) {
                    double topKey = 70;
                    double bttomKey = 75;
                    double topVal = 2920;
                    double bttomVal = 3450;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("75")) {
                    viewActivity.setText("3450");
                } else if ((deltatop > 75) && (deltatop < 80)) {
                    double topKey = 75;
                    double bttomKey = 80;
                    double topVal = 3450;
                    double bttomVal = 3950;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("80")) {
                    viewActivity.setText("3950");
                } else if ((deltatop > 80) && (deltatop < 85)) {
                    double topKey = 80;
                    double bttomKey = 85;
                    double topVal = 3950;
                    double bttomVal = 4450;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("85")) {
                    viewActivity.setText("4450");
                } else if ((deltatop > 85) && (deltatop < 90)) {
                    double topKey = 85;
                    double bttomKey = 90;
                    double topVal = 4450;
                    double bttomVal = 5000;

                    double deltapls = deltatop - topKey;
                    double keyDff = (bttomKey - topKey);
                    double valDff = (bttomVal - topVal);
                    double oneUnitVal = (valDff / keyDff);
                    double muldata = oneUnitVal * deltapls;

                    double getActivity = muldata + topVal;
                    String finalDelta = String.valueOf(getActivity);

                    viewActivity.setText(String.format("%.2f", getActivity));

                } else if (dra.equals("90")) {
                    viewActivity.setText("5000");

                }


            }


        }

    }


    ///////////

    private void fOneValue1(EditText deltaValue, TextView f1Data) {

        if (deltaValue.getText().length() != 0) {

            String dra = deltaValue.getText().toString();
            double deltatop = Double.parseDouble(dra);
            Log.w("my data is >>", " " + dra);

            if (dra.equals("35.0")) {
                f1Data.setText(R.string.f1_35);
            } else if ((deltatop > 35.0) && (deltatop < 40.0)) {
                double topKey = 35.0;
                double bttomKey = 40.0;
                double topVal = 1.0;
                double bttomVal = 1.12;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("40.0")) {
                f1Data.setText(R.string.f1_40);
            } else if ((deltatop > 40.0) && (deltatop < 50.0)) {
                double topKey = 40.0;
                double bttomKey = 50.0;
                double topVal = 1.12;
                double bttomVal = 1.35;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("50.0")) {
                f1Data.setText(R.string.f1_50);
            } else if ((deltatop > 50.0) && (deltatop < 60.0)) {
                double topKey = 40.0;
                double bttomKey = 60.0;
                double topVal = 1.35;
                double bttomVal = 1.59;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("60.0")) {
                f1Data.setText(R.string.f1_60);
            } else if ((deltatop > 60.0) && (deltatop < 70.0)) {
                double topKey = 60.0;
                double bttomKey = 70.0;
                double topVal = 1.59;
                double bttomVal = 1.82;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("70.0")) {
                f1Data.setText(R.string.f1_70);
            } else if ((deltatop > 70.0) && (deltatop < 80.0)) {
                double topKey = 70.0;
                double bttomKey = 80.0;
                double topVal = 1.82;
                double bttomVal = 2.06;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("80.0")) {
                f1Data.setText(R.string.f1_80);
            } else if ((deltatop > 80.0) && (deltatop < 90.0)) {
                double topKey = 80.0;
                double bttomKey = 90.0;
                double topVal = 2.06;
                double bttomVal = 2.29;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("90.0")) {
                f1Data.setText(R.string.f1_90);
            } else if ((deltatop > 90.0) && (deltatop < 100.0)) {
                double topKey = 90.0;
                double bttomKey = 100.0;
                double topVal = 2.29;
                double bttomVal = 2.53;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("100.0")) {
                f1Data.setText(R.string.f1_100);
            } else if ((deltatop > 100.0) && (deltatop < 110.0)) {
                double topKey = 100.0;
                double bttomKey = 110.0;
                double topVal = 2.53;
                double bttomVal = 2.76;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("110.0")) {
                f1Data.setText(R.string.f1_110);
            } else if ((deltatop > 110.0) && (deltatop < 120.0)) {
                double topKey = 110.0;
                double bttomKey = 120.0;
                double topVal = 2.76;
                double bttomVal = 3.00;

                double deltapls = deltatop - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (bttomVal - topVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * deltapls;

                double getF1 = muldata + topVal;
                String finalDelta = String.valueOf(getF1);

                f1Data.setText(String.format("%.2f", getF1));

            } else if (dra.equals("120.0")) {
                f1Data.setText(R.string.f1_120);
            }
                    /*else {
                        topF1.setText("2.0");
                    }*/

        }

    }

    ////////////

    // end Top f2 value


    private void fTwoValue(TextView roomTValue, TextView f2Data) {

        if (roomTValue.getText().length() != 0) {

            String dra = roomTValue.getText().toString();
            double rmTvalum = Double.parseDouble(dra);
            double finalRmValue = rmTvalum / 1000;

            String finaldatais = String.valueOf(finalRmValue);


            Log.w("my finalRmValue is >>", " " + finalRmValue);

            if ((finalRmValue >= 0.0) && (finalRmValue < 11.0)) {

                double topKey = 0.0;
                double bttomKey = 10.0;
                double topVal = 0.65;
                double bttomVal = 0.65;
                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));
            } else if (finaldatais.equals("10.0")) {
                f2Data.setText(R.string.f2_10);
            } else if ((finalRmValue >= 11.0) && (finalRmValue < 20.0)) {
                double topKey = 10;
                double bttomKey = 20.0;
                double topVal = 0.65;
                double bttomVal = 0.58;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));


            } else if (finaldatais.equals("20.0")) {
                f2Data.setText(R.string.f2_20);
            } else if ((finalRmValue > 20.0) && (finalRmValue < 25.0)) {
                double topKey = 20.0;
                double bttomKey = 25.0;
                double topVal = 0.58;
                double bttomVal = 0.55;
                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("25.0")) {
                f2Data.setText(R.string.f2_25);

            } else if ((finalRmValue > 25.0) && (finalRmValue < 30.0)) {
                double topKey = 25.0;
                double bttomKey = 30.0;
                double topVal = 0.55;
                double bttomVal = 0.53;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("30.0")) {
                f2Data.setText(R.string.f2_30);
            } else if ((finalRmValue > 30.0) && (finalRmValue < 35.0)) {
                double topKey = 30.0;
                double bttomKey = 35.0;
                double topVal = 0.53;
                double bttomVal = 0.51;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("35.0")) {
                f2Data.setText(R.string.f2_35);
            } else if ((finalRmValue > 35.0) && (finalRmValue < 40.0)) {
                double topKey = 35.0;
                double bttomKey = 40.0;
                double topVal = 0.51;
                double bttomVal = 0.49;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("40.0")) {
                f2Data.setText(R.string.f2_40);
            } else if ((finalRmValue > 40.0) && (finalRmValue < 45.0)) {
                double topKey = 40.0;
                double bttomKey = 35.0;
                double topVal = 0.51;
                double bttomVal = 0.48;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("45.0")) {
                f2Data.setText(R.string.f2_45);
            } else if ((finalRmValue > 45.0) && (finalRmValue < 50.0)) {
                double topKey = 45.0;
                double bttomKey = 50.0;
                double topVal = 0.48;
                double bttomVal = 0.46;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("50.0")) {
                f2Data.setText(R.string.f2_50);
            } else if ((finalRmValue > 50.0) && (finalRmValue < 55.0)) {
                double topKey = 50.0;
                double bttomKey = 55.0;
                double topVal = 0.46;
                double bttomVal = 0.45;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("55.0")) {
                f2Data.setText(R.string.f2_55);
            } else if ((finalRmValue > 55.0) && (finalRmValue < 60.0)) {
                double topKey = 55.0;
                double bttomKey = 60.0;
                double topVal = 0.45;
                double bttomVal = 0.44;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("60.0")) {
                f2Data.setText(R.string.f2_60);
            } else if ((finalRmValue > 60.0) && (finalRmValue < 65.0)) {
                double topKey = 60.0;
                double bttomKey = 65.0;
                double topVal = 0.44;
                double bttomVal = 0.43;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("65.0")) {
                f2Data.setText(R.string.f2_65);
            } else if ((finalRmValue > 65.0) && (finalRmValue < 70.0)) {
                double topKey = 65.0;
                double bttomKey = 70.0;
                double topVal = 0.43;
                double bttomVal = 0.42;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("70.0")) {
                f2Data.setText(R.string.f2_70);
            } else if ((finalRmValue > 70.0) && (finalRmValue < 75.0)) {
                double topKey = 70.0;
                double bttomKey = 75.0;
                double topVal = 0.42;
                double bttomVal = 0.43;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("75.0")) {
                f2Data.setText(R.string.f2_75);
            } else if ((finalRmValue > 75.0) && (finalRmValue < 80.0)) {
                double topKey = 75.0;
                double bttomKey = 80.0;
                double topVal = 0.43;
                double bttomVal = 0.4;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("80.0")) {
                f2Data.setText(R.string.f2_80);
            } else if ((finalRmValue > 80.0) && (finalRmValue < 85.0)) {
                double topKey = 80.0;
                double bttomKey = 85.0;
                double topVal = 0.4;
                double bttomVal = 0.39;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("85.0")) {
                f2Data.setText(R.string.f2_80);
            } else if ((finalRmValue > 85.0) && (finalRmValue < 90.0)) {
                double topKey = 85.0;
                double bttomKey = 90.0;
                double topVal = 0.39;
                double bttomVal = 0.38;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("90.0")) {
                f2Data.setText(R.string.f2_90);
            } else if ((finalRmValue > 90.0) && (finalRmValue < 95.0)) {
                double topKey = 90.0;
                double bttomKey = 95.0;
                double topVal = 0.38;
                double bttomVal = 0.38;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("95.0")) {
                f2Data.setText(R.string.f2_95);
            } else if ((finalRmValue > 95.0) && (finalRmValue < 100.0)) {
                double topKey = 95.0;
                double bttomKey = 100.0;
                double topVal = 0.38;
                double bttomVal = 0.37;

                double rmValueapls = finalRmValue - topKey;
                double keyDff = (bttomKey - topKey);
                double valDff = (topVal - bttomVal);
                double oneUnitVal = (valDff / keyDff);
                double muldata = oneUnitVal * rmValueapls;

                //double getF2 = muldata + bttomVal;
                double getF2 = topVal-muldata;
                String finalDelta = String.valueOf(getF2);

                f2Data.setText(String.format("%.2f", getF2));

            } else if (finaldatais.equals("100.0")) {
                f2Data.setText(R.string.f2_100);
            }


        }
    }

    // end Top f2 value


    public void inputField() {

        //static field

        // volume field
        bm_v0 = (TextView) findViewById(R.id.bm_v0);
        bm_v1 = (TextView) findViewById(R.id.bm_v1);
        bm_v2 = (TextView) findViewById(R.id.bm_v2);
        bm_v3 = (TextView) findViewById(R.id.bm_v3);
        bm_v4 = (TextView) findViewById(R.id.bm_v4);
        bm_v5 = (TextView) findViewById(R.id.bm_v5);
        bm_v6 = (TextView) findViewById(R.id.bm_v6);
        bm_v7 = (TextView) findViewById(R.id.bm_v7);
        bm_v8 = (TextView) findViewById(R.id.bm_v8);
        bm_v9 = (TextView) findViewById(R.id.bm_v9);


//Remove btn
        bm_btn0 = (Button) findViewById(R.id.bm_btn0);
        bm_btn1 = (Button) findViewById(R.id.bm_btn1);
        bm_btn2 = (Button) findViewById(R.id.bm_btn2);
        bm_btn3 = (Button) findViewById(R.id.bm_btn3);
        bm_btn4 = (Button) findViewById(R.id.bm_btn4);
        bm_btn5 = (Button) findViewById(R.id.bm_btn5);
        bm_btn6 = (Button) findViewById(R.id.bm_btn6);
        bm_btn7 = (Button) findViewById(R.id.bm_btn7);
        bm_btn8 = (Button) findViewById(R.id.bm_btn8);
        bm_btn9 = (Button) findViewById(R.id.bm_btn9);

        pp_output= (Button) findViewById(R.id.pp_output);
        btu_output= (Button) findViewById(R.id.btu_output);


        //DM field
        bm_l0 = (EditText) findViewById(R.id.bm_l0);
        bm_w0 = (EditText) findViewById(R.id.bm_w0);
        bm_h0 = (EditText) findViewById(R.id.bm_h0);

        bm_l1 = (EditText) findViewById(R.id.bm_l1);
        bm_w1 = (EditText) findViewById(R.id.bm_w1);
        bm_h1 = (EditText) findViewById(R.id.bm_h1);

        bm_l2 = (EditText) findViewById(R.id.bm_l2);
        bm_w2 = (EditText) findViewById(R.id.bm_w2);
        bm_h2 = (EditText) findViewById(R.id.bm_h2);

        bm_l3 = (EditText) findViewById(R.id.bm_l3);
        bm_w3 = (EditText) findViewById(R.id.bm_w3);
        bm_h3 = (EditText) findViewById(R.id.bm_h3);

        bm_l4 = (EditText) findViewById(R.id.bm_l4);
        bm_w4 = (EditText) findViewById(R.id.bm_w4);
        bm_h4 = (EditText) findViewById(R.id.bm_h4);

        bm_l5 = (EditText) findViewById(R.id.bm_l5);
        bm_w5 = (EditText) findViewById(R.id.bm_w5);
        bm_h5 = (EditText) findViewById(R.id.bm_h5);

        bm_l6 = (EditText) findViewById(R.id.bm_l6);
        bm_w6 = (EditText) findViewById(R.id.bm_w6);
        bm_h6 = (EditText) findViewById(R.id.bm_h6);

        bm_l7 = (EditText) findViewById(R.id.bm_l7);
        bm_w7 = (EditText) findViewById(R.id.bm_w7);
        bm_h7 = (EditText) findViewById(R.id.bm_h7);

        bm_l8 = (EditText) findViewById(R.id.bm_l8);
        bm_w8 = (EditText) findViewById(R.id.bm_w8);
        bm_h8 = (EditText) findViewById(R.id.bm_h8);

        bm_l9 = (EditText) findViewById(R.id.bm_l9);
        bm_w9 = (EditText) findViewById(R.id.bm_w9);
        bm_h9 = (EditText) findViewById(R.id.bm_h9);


        dmlyt0 = (LinearLayout) findViewById(R.id.dm_lyt0);
        dmlyt1 = (LinearLayout) findViewById(R.id.dm_lyt1);
        dmlyt2 = (LinearLayout) findViewById(R.id.dm_lyt2);
        dmlyt3 = (LinearLayout) findViewById(R.id.dm_lyt3);
        dmlyt4 = (LinearLayout) findViewById(R.id.dm_lyt4);
        dmlyt5 = (LinearLayout) findViewById(R.id.dm_lyt5);
        dmlyt6 = (LinearLayout) findViewById(R.id.dm_lyt6);
        dmlyt7 = (LinearLayout) findViewById(R.id.dm_lyt7);
        dmlyt8 = (LinearLayout) findViewById(R.id.dm_lyt8);
        dmlyt9 = (LinearLayout) findViewById(R.id.dm_lyt9);

        //door load field

        dlw0 = (EditText) findViewById(R.id.dlw0);
        dlh0 = (EditText) findViewById(R.id.dlh0);
        dldop0 = (EditText) findViewById(R.id.dldop0);

        dlw1 = (EditText) findViewById(R.id.dlw1);
        dlh1 = (EditText) findViewById(R.id.dlh1);
        dldop1 = (EditText) findViewById(R.id.dldop1);

        dlw2 = (EditText) findViewById(R.id.dlw2);
        dlh2 = (EditText) findViewById(R.id.dlh2);
        dldop2 = (EditText) findViewById(R.id.dldop2);

        dlw3 = (EditText) findViewById(R.id.dlw3);
        dlh3 = (EditText) findViewById(R.id.dlh3);
        dldop3 = (EditText) findViewById(R.id.dldop3);

        dlw4 = (EditText) findViewById(R.id.dlw4);
        dlh4 = (EditText) findViewById(R.id.dlh4);
        dldop4 = (EditText) findViewById(R.id.dldop4);

        dlw5 = (EditText) findViewById(R.id.dlw5);
        dlh5 = (EditText) findViewById(R.id.dlh5);
        dldop5 = (EditText) findViewById(R.id.dldop5);


        dl_btn0 = (Button) findViewById(R.id.dl_btn0);
        dl_btn1 = (Button) findViewById(R.id.dl_btn1);
        dl_btn2 = (Button) findViewById(R.id.dl_btn2);
        dl_btn3 = (Button) findViewById(R.id.dl_btn3);
        dl_btn4 = (Button) findViewById(R.id.dl_btn4);
        dl_btn5 = (Button) findViewById(R.id.dl_btn5);


        dllyt0 = (LinearLayout) findViewById(R.id.dl_lyt0);
        dllyt1 = (LinearLayout) findViewById(R.id.dl_lyt1);
        dllyt2 = (LinearLayout) findViewById(R.id.dl_lyt2);
        dllyt3 = (LinearLayout) findViewById(R.id.dl_lyt3);
        dllyt4 = (LinearLayout) findViewById(R.id.dl_lyt4);
        dllyt5 = (LinearLayout) findViewById(R.id.dl_lyt5);

        p_outpressure= (TextView) findViewById(R.id.p_outpressure);
        btu_cfm= (TextView) findViewById(R.id.btu_cfm);

        //fixed opning

        fod_v0 = (TextView) findViewById(R.id.fod_v0);
        fod_v1 = (TextView) findViewById(R.id.fod_v1);
        fod_v2 = (TextView) findViewById(R.id.fod_v2);
        fod_v3 = (TextView) findViewById(R.id.fod_v3);
        fod_v4 = (TextView) findViewById(R.id.fod_v4);
        fod_v5 = (TextView) findViewById(R.id.fod_v5);


        fod_l0 = (EditText) findViewById(R.id.fod_l0);
        fod_w0 = (EditText) findViewById(R.id.fod_w0);
        fod_d0 = (EditText) findViewById(R.id.fod_d0);

        fod_l1 = (EditText) findViewById(R.id.fod_l1);
        fod_w1 = (EditText) findViewById(R.id.fod_w1);
        fod_d1 = (EditText) findViewById(R.id.fod_d1);

        fod_l2 = (EditText) findViewById(R.id.fod_l2);
        fod_w2 = (EditText) findViewById(R.id.fod_w2);
        fod_d2 = (EditText) findViewById(R.id.fod_d2);

        fod_l3 = (EditText) findViewById(R.id.fod_l3);
        fod_w3 = (EditText) findViewById(R.id.fod_w3);
        fod_d3 = (EditText) findViewById(R.id.fod_d3);

        fod_l4 = (EditText) findViewById(R.id.fod_l4);
        fod_w4 = (EditText) findViewById(R.id.fod_w4);
        fod_d4 = (EditText) findViewById(R.id.fod_d4);

        fod_l5 = (EditText) findViewById(R.id.fod_l5);
        fod_w5 = (EditText) findViewById(R.id.fod_w5);
        fod_d5 = (EditText) findViewById(R.id.fod_d5);


        fod_btn0 = (Button) findViewById(R.id.fod_btn0);
        fod_btn1 = (Button) findViewById(R.id.fod_btn1);
        fod_btn2 = (Button) findViewById(R.id.fod_btn2);
        fod_btn3 = (Button) findViewById(R.id.fod_btn3);
        fod_btn4 = (Button) findViewById(R.id.fod_btn4);
        fod_btn5 = (Button) findViewById(R.id.fod_btn5);


        fod_lyt0 = (LinearLayout) findViewById(R.id.fod_lyt0);
        fod_lyt1 = (LinearLayout) findViewById(R.id.fod_lyt1);
        fod_lyt2 = (LinearLayout) findViewById(R.id.fod_lyt2);
        fod_lyt3 = (LinearLayout) findViewById(R.id.fod_lyt3);
        fod_lyt4 = (LinearLayout) findViewById(R.id.fod_lyt4);
        fod_lyt5 = (LinearLayout) findViewById(R.id.fod_lyt5);


        //end static field


        rgImpSi = (RadioGroup) findViewById(R.id.radioGsiImp);
        psycalDesign = (Button) findViewById(R.id.psycalDesign);
        psycalDesign2 = (Button) findViewById(R.id.psycalDesign2);
        psyreset = (Button) findViewById(R.id.psyreset);
        psycal = (Button) findViewById(R.id.psycal);
        psyreset = (Button) findViewById(R.id.psyreset);
        psyresetDesign = (Button) findViewById(R.id.psyresetDesign);
        codbtnlyt = (LinearLayout) findViewById(R.id.cod_btn_lyt);
        cofobtnlyt = (LinearLayout) findViewById(R.id.cofo_btn_lyt);


        codcal = (Button) findViewById(R.id.codcal);
        codreset = (Button) findViewById(R.id.codreset);
        cofocal = (Button) findViewById(R.id.cofocal);
        coforeset = (Button) findViewById(R.id.coforeset);
        faqcal = (Button) findViewById(R.id.faqcal);
        faqreset = (Button) findViewById(R.id.faqreset);
        iaccal = (Button) findViewById(R.id.iaccal);
        iacreset = (Button) findViewById(R.id.iacreset);


        codToggle = (ToggleButton) findViewById(R.id.toggleButton1);
        cofoToggle = (ToggleButton) findViewById(R.id.toggleOutside);
        faqToggle = (ToggleButton) findViewById(R.id.togglefresh);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        apnrActivityLevel = (Spinner) findViewById(R.id.spnr_activity_level);
        activityvalue = (TextView) findViewById(R.id.activity_value);

        lytpload = (LinearLayout) findViewById(R.id.lyt_pload);

        //buttons id

        btnproductload = (Button) findViewById(R.id.btn_productload);
        btn = (Button) findViewById(R.id.btn);
        dlbtn = (Button) findViewById(R.id.dlbtn);
        btnfixed = (Button) findViewById(R.id.btnfixed);

        btnback = (Button) findViewById(R.id.btn_back);
        btn_nextone = (Button) findViewById(R.id.btn_nextone);


        //Ambient
        inDrybulb = (EditText) findViewById(R.id.edt_drytemp);
        inWetbulb = (EditText) findViewById(R.id.edt_wttemp);
        inRh = (EditText) findViewById(R.id.edt_rh);
        inGr = (TextView) findViewById(R.id.edt_gr);
        inEnthalpy = (TextView) findViewById(R.id.edt_enthalpy);

        //Design
        designDry = (EditText) findViewById(R.id.design_dry);
        designwet = (EditText) findViewById(R.id.design_wet);
        designRh = (EditText) findViewById(R.id.design_rh);
        designGr = (TextView) findViewById(R.id.design_gr);
        designbtu = (TextView) findViewById(R.id.design_btu);

        designDry2 = (EditText) findViewById(R.id.design_dry2);
        designwet2 = (EditText) findViewById(R.id.design_wet2);
        designRh2 = (EditText) findViewById(R.id.design_rh2);
        designGr2 = (TextView) findViewById(R.id.design_gr2);
        designbtu2 = (TextView) findViewById(R.id.design_btu2);

        //edt_no_person
        inNumberofperson = (EditText) findViewById(R.id.edt_no_person);
        //edt_f2
        inF2 = (TextView) findViewById(R.id.edt_f2);
        //edt_cod_dbt
        outDoorDrybulb = (EditText) findViewById(R.id.edt_cod_dbt);
        //edt_cod_wbt
        outDoorWetbulb = (EditText) findViewById(R.id.edt_cod_wbt);
        //edt_cod_rh
        outDoorRh = (EditText) findViewById(R.id.edt_cod_rh);
        //edt_cod_gr
        outDoorGr = (TextView) findViewById(R.id.edt_cod_gr);
        //cod_delta_gr
        outDoordeltaGr = (TextView) findViewById(R.id.cod_delta_gr);
        //edt_cod_f1
        outDoorf1 = (TextView) findViewById(R.id.edt_cod_f1);

        fixedopningDrybulb = (EditText) findViewById(R.id.cofo_dry_bulb);
        //cofo_wet_blue
        fixedopningWetbulb = (EditText) findViewById(R.id.cofo_wet_blue);
        //cofo_rh
        fixedopningRh = (EditText) findViewById(R.id.cofo_rh);
        //cofo_gr
        fixedopningGr = (TextView) findViewById(R.id.cofo_gr);
        //cofo_delta_gr
        fixedopningdeltaGr = (TextView) findViewById(R.id.cofo_delta_gr);
        //cofo_f1
        cofoF1 = (TextView) findViewById(R.id.cofo_f1);
        //p_pressure
        p_pressure = (EditText) findViewById(R.id.p_pressure);
        //faq_cfm
        faqCfm = (EditText) findViewById(R.id.faq_cfm);
        //faq_dry_bulb
        faqDrybulb = (EditText) findViewById(R.id.faq_dry_bulb);
        //faq_wet_bulb
        faqWetbulb = (EditText) findViewById(R.id.faq_wet_bulb);
        //faq_rh
        faqRh = (EditText) findViewById(R.id.faq_rh);
        //faq_gr
        faqGr = (TextView) findViewById(R.id.faq_gr);
        //faq_enthalpy
        faqeEnthalpy = (TextView) findViewById(R.id.faq_enthalpy);
        //faq_delta_gr
        faqdeltaGr = (TextView) findViewById(R.id.faq_delta_gr);
        //exhaust_edt
        exhaust_edt = (EditText) findViewById(R.id.exhaust_edt);
        //infiltration_edt
        infiltrationLaqCfm = (EditText) findViewById(R.id.infiltration_edt);
        //iac_dry_bulb
        iacDrybulb = (EditText) findViewById(R.id.iac_dry_bulb);
        //iac_wet_bulb
        iacWetbulb = (EditText) findViewById(R.id.iac_wet_bulb);
        //iac_rh
        iacRh = (EditText) findViewById(R.id.iac_rh);
        //iac_gr
        iacGr = (TextView) findViewById(R.id.iac_gr);
        //iac_delta_gr
        iacdeltaGr = (TextView) findViewById(R.id.iac_delta_gr);

        //plImc
        plImc = (EditText) findViewById(R.id.pl_imc);
        //plFmc
        plFmc = (EditText) findViewById(R.id.pl_fmc);
        //plWt

        //dt_imc
        plDt = (EditText) findViewById(R.id.pl_dt);


        //edt_hlea
        inHlea = (EditText) findViewById(R.id.edt_hlea);
        //txt_ahu
        ahuCapacity = (EditText) findViewById(R.id.txt_ahu);
        //edt_design_adp
        designAdp = (EditText) findViewById(R.id.edt_design_adp);
        //edt_espr
        inEspr = (EditText) findViewById(R.id.edt_espr);
        //edt_filtration
        inFiltration = (EditText) findViewById(R.id.edt_filtration);

        //Delta gr
        //getvalue = (TextView) findViewById(R.id.getvalue);
        deltaGr = (TextView) findViewById(R.id.delta_gr);
        topF1 = (TextView) findViewById(R.id.txt_f1);
        txtTvolume = (TextView) findViewById(R.id.txt_tvolume);
        txtTlength = (TextView) findViewById(R.id.txt_tlength);
        txtTwidth = (TextView) findViewById(R.id.txt_twidth);
        txtThight = (TextView) findViewById(R.id.txt_theight);
        //in_platent_load_
        inPll = (EditText) findViewById(R.id.in_platent_load);

        f3value = (TextView) findViewById(R.id.f3_value);
        f4value = (TextView) findViewById(R.id.f4_value);
        /*btnnext1=(TextView) findViewById(R.id.btnnext12);*/

        //make clickable
        outDoordeltaGr.setOnClickListener(this);
        fixedopningdeltaGr.setOnClickListener(this);
        faqdeltaGr.setOnClickListener(this);
        iacdeltaGr.setOnClickListener(this);
        //outDoorf1.setOnClickListener(this);
        codToggle.setOnClickListener(this);
        cofoToggle.setOnClickListener(this);
        faqToggle.setOnClickListener(this);
        btn_nextone.setOnClickListener(this);
        btnproductload.setOnClickListener(this);
        psycal.setOnClickListener(this);
        psycalDesign.setOnClickListener(this);
        psycalDesign2.setOnClickListener(this);
        psyreset.setOnClickListener(this);
        psyresetDesign.setOnClickListener(this);

        codcal.setOnClickListener(this);
        codreset.setOnClickListener(this);

        cofocal.setOnClickListener(this);
        coforeset.setOnClickListener(this);

        faqcal.setOnClickListener(this);
        faqreset.setOnClickListener(this);

        iaccal.setOnClickListener(this);
        iacreset.setOnClickListener(this);
        btn.setOnClickListener(this);
        dlbtn.setOnClickListener(this);
        btnfixed.setOnClickListener(this);

        pp_output.setOnClickListener(this);
        btu_output.setOnClickListener(this);


    }

    //show value when toggle is ac
    private void codAcValue() {
        if (isCodAc == true) {
            if (!(inDrybulb.getText().toString().length() == 0) && !(inWetbulb.getText().toString().length() == 0) && !(inRh.getText().toString().length() == 0) && !(inGr.getText().toString().length() == 0)) {
                outDoorDrybulb.setText(inDrybulb.getText().toString());
                outDoorWetbulb.setText(inWetbulb.getText().toString());
                outDoorRh.setText(inRh.getText().toString());
                outDoorGr.setText(inGr.getText().toString());

                if (!(deltaGr.getText().toString().length() == 0)) {
                    outDoordeltaGr.setText(deltaGr.getText().toString());
                    outDoorf1.setText(topF1.getText().toString());
                }

                outDoorDrybulb.setFocusable(false);
                outDoorDrybulb.setFocusableInTouchMode(false);
                outDoorDrybulb.setClickable(false);
                outDoorDrybulb.setCursorVisible(false);


                outDoorWetbulb.setFocusable(false);
                outDoorWetbulb.setFocusableInTouchMode(false);
                outDoorWetbulb.setClickable(false);
                outDoorWetbulb.setCursorVisible(false);

                outDoorRh.setFocusable(false);
                outDoorRh.setFocusableInTouchMode(false);
                outDoorRh.setClickable(false);
                outDoorRh.setCursorVisible(false);

                outDoordeltaGr.setClickable(false);
                //outDoorf1.setClickable(false);

            }
        }
        if (isfixedopningAc == true) {
            if (!(inDrybulb.getText().toString().length() == 0) && !(inWetbulb.getText().toString().length() == 0) && !(inRh.getText().toString().length() == 0) && !(inGr.getText().toString().length() == 0)) {
                fixedopningDrybulb.setText(inDrybulb.getText().toString());
                fixedopningWetbulb.setText(inWetbulb.getText().toString());
                fixedopningRh.setText(inRh.getText().toString());
                fixedopningGr.setText(inGr.getText().toString());
                fixedopningdeltaGr.setText(deltaGr.getText().toString());
                cofoF1.setText(topF1.getText().toString());

                fixedopningDrybulb.setFocusable(false);
                fixedopningDrybulb.setFocusableInTouchMode(false);
                fixedopningDrybulb.setClickable(false);
                fixedopningDrybulb.setCursorVisible(false);


                fixedopningWetbulb.setFocusable(false);
                fixedopningWetbulb.setFocusableInTouchMode(false);
                fixedopningWetbulb.setClickable(false);
                fixedopningWetbulb.setCursorVisible(false);

                fixedopningRh.setFocusable(false);
                fixedopningRh.setFocusableInTouchMode(false);
                fixedopningRh.setClickable(false);
                fixedopningRh.setCursorVisible(false);

                fixedopningdeltaGr.setClickable(false);
                //cofoF1.setClickable(false);
            }
        }
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pp_output:

                getPPcfm(p_pressure,p_outpressure);

                break;

            case R.id.btu_output:

                getBtu(inHlea,designDry,designAdp,btu_cfm);

                break;


            case R.id.toggleButton1:

                outDoorDrybulb.setText("");
                outDoorWetbulb.setText("");
                outDoorRh.setText("");
                outDoorGr.setText("");
                outDoordeltaGr.setText("");
                outDoorf1.setText("");
                if (codToggle.isChecked()) {
                    isCodAc = true;
                    codAcValue();
                    codbtnlyt.setVisibility(View.GONE);

                } else {
                    isCodAc = false;
                    outDoorDrybulb.setFocusable(true);
                    outDoorDrybulb.setFocusableInTouchMode(true);
                    outDoorDrybulb.setClickable(true);
                    outDoorDrybulb.setCursorVisible(true);


                    outDoorWetbulb.setFocusable(true);
                    outDoorWetbulb.setFocusableInTouchMode(true);
                    outDoorWetbulb.setClickable(true);
                    outDoorWetbulb.setCursorVisible(true);

                    outDoorRh.setFocusable(true);
                    outDoorRh.setFocusableInTouchMode(true);
                    outDoorRh.setClickable(true);
                    outDoorRh.setCursorVisible(true);

                    outDoordeltaGr.setClickable(true);
                    outDoorf1.setClickable(true);
                    codbtnlyt.setVisibility(View.VISIBLE);

                }

                break;


            case R.id.toggleOutside:
                fixedopningDrybulb.setText("");
                fixedopningWetbulb.setText("");
                fixedopningRh.setText("");
                fixedopningGr.setText("");
                fixedopningdeltaGr.setText("");
                cofoF1.setText("");
                if (cofoToggle.isChecked()) {
                    isfixedopningAc = true;

                    codAcValue();
                    cofobtnlyt.setVisibility(View.GONE);


                } else {
                    isfixedopningAc = false;
                    fixedopningDrybulb.setFocusable(true);
                    fixedopningDrybulb.setFocusableInTouchMode(true);
                    fixedopningDrybulb.setClickable(true);
                    fixedopningDrybulb.setCursorVisible(true);


                    fixedopningWetbulb.setFocusable(true);
                    fixedopningWetbulb.setFocusableInTouchMode(true);
                    fixedopningWetbulb.setClickable(true);
                    fixedopningWetbulb.setCursorVisible(true);

                    fixedopningRh.setFocusable(true);
                    fixedopningRh.setFocusableInTouchMode(true);
                    fixedopningRh.setClickable(true);
                    fixedopningRh.setCursorVisible(true);

                    fixedopningdeltaGr.setClickable(true);
                    //cofoF1.setClickable(true);
                    cofobtnlyt.setVisibility(View.VISIBLE);
                }

                break;


            case R.id.togglefresh:
                faqDrybulb.getText().clear();
                faqWetbulb.getText().clear();
                faqRh.getText().clear();
                faqGr.setText("");
                faqeEnthalpy.setText("");
                faqdeltaGr.setText("");

                if (faqToggle.isChecked()) {
                    isPrecool=true;
                    faqRh.setText("100");
                    faqRh.setFocusable(false);
                    faqRh.setFocusableInTouchMode(false);
                    faqRh.setClickable(false);
                    faqRh.setCursorVisible(false);

                } else {
                    isPrecool=false;
                    faqRh.setFocusable(true);
                    faqRh.setFocusableInTouchMode(true);
                    faqRh.setClickable(true);
                    faqRh.setCursorVisible(true);
                }

                break;


            case R.id.btn_productload:

                if (ispload == false) {
                    lytpload.setVisibility(View.VISIBLE);
                    btnproductload.setText("-");
                    ispload = true;
                } else {
                    ispload = false;
                    lytpload.setVisibility(View.GONE);
                    btnproductload.setText("+");
                }


                break;

            case R.id.psyreset:

                resetInput(inDrybulb, inWetbulb, inRh, inGr, inEnthalpy);
                deltaGr.setText("0.0");
                topF1.setText("0.0");
                break;

            case R.id.psyresetDesign:

                resetInput(designDry, designwet, designRh, designGr, designbtu);
                deltaGr.setText("0.0");
                topF1.setText("0.0");
                activityvalue.setText("0.0");
                break;

            case R.id.codreset:

                resetInputCod(outDoorDrybulb, outDoorWetbulb, outDoorRh, outDoorGr);
                outDoorf1.setText("0.0");
                outDoordeltaGr.setText("0.0");

                break;

            case R.id.coforeset:

                resetInputCod(fixedopningDrybulb, fixedopningWetbulb, fixedopningRh, fixedopningGr);
                cofoF1.setText("0.0");
                fixedopningdeltaGr.setText("0.0");

                break;

            case R.id.faqreset:

                resetInput(faqDrybulb, faqWetbulb, faqRh, faqGr, faqeEnthalpy);
                faqdeltaGr.setText("0.0");

                break;

            case R.id.iacreset:

                resetInputCod(iacDrybulb, iacWetbulb, iacRh, iacGr);
                iacdeltaGr.setText("0.0");

                break;

//Base calculation
            case R.id.psycal:
                if (inDrybulb.getText().toString().matches("") && inWetbulb.getText().toString().matches("") && inRh.getText().toString().matches("")) {

                    Toast.makeText(InputActivity.this, "Pleas fill atlist two input field", Toast.LENGTH_SHORT).show();
                } else if (inDrybulb.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas enter DBT value", Toast.LENGTH_SHORT).show();
                } else if ((inWetbulb.getText().toString().matches("")) && ((inRh.getText().toString().matches("")))) {
                    Toast.makeText(InputActivity.this, "Pleas enter WBT OR RH value", Toast.LENGTH_SHORT).show();
                } else if (!(inWetbulb.getText().toString().matches("")) || (!(inRh.getText().toString().matches("")))) {
                   /* inWetbulb.setText("0");*/
                    radioSi = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());

                    siImp = radioSi.getText().toString();

                    if (siImp.equals("IMP")) {
                        getPsyImp(inDrybulb, inWetbulb, inRh, inGr, inEnthalpy);

                        getAllDeltaGr(inGr, designGr, deltaGr, topF1);

                        codAcValue();
                    } else if (siImp.equals("SI")) {
                        getPsyIs(inDrybulb, inWetbulb, inRh, inGr, inEnthalpy);
                        getAllDeltaGr(inGr, designGr, deltaGr, topF1);

                        codAcValue();
                    }
                }

                break;


            case R.id.psycalDesign:

                if (designRh.getText().toString().equals("")){
                    double d1= Double.parseDouble(designDry.getText().toString());
                    double d1d=d1+3.6;
                    designDry2.setText(String.valueOf(d1d));
                    designRh2.setText("");
                    designwet2.setText(designwet2.getText().toString());
                }
                else {
                    double d1 = Double.parseDouble(designDry.getText().toString());
                    double d1d = d1 + 3.6;
                    double r1 = Double.parseDouble(designRh.getText().toString());
                    double r1r = r1 + 5;

                    Log.d("ghgfhfgjgfgjfg", String.valueOf(d1d));
                    Log.d("ghgfhfgjgfgjfg", String.valueOf(r1r));

                    designDry2.setText(String.valueOf(d1d));
                    designRh2.setText(String.valueOf(r1r));

                }

                if (designDry.getText().toString().matches("") && designwet.getText().toString().matches("") && designRh.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas fill atlist two input field", Toast.LENGTH_SHORT).show();
                } else if (designDry.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas enter DBT value", Toast.LENGTH_SHORT).show();
                } else if ((designwet.getText().toString().matches("")) && ((designRh.getText().toString().matches("")))) {
                    Toast.makeText(InputActivity.this, "Pleas enter WBT OR RH value", Toast.LENGTH_SHORT).show();
                } else if (!(designwet.getText().toString().matches("")) || (!(designRh.getText().toString().matches("")))) {
                    actvityLevelValue(designDry, activityvalue, activityPos);

                    radioSi = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());

                    siImp = radioSi.getText().toString();

                    if (siImp.equals("IMP")) {
                        getPsyImp(designDry, designwet, designRh, designGr, designbtu);
//                          getPsyImp2(designDry, designwet, designRh, designGr, designbtu);
                        getPsyImp2(designDry2, designwet2, designRh2, designGr2, designbtu2);
                        getAllDeltaGr(inGr, designGr, deltaGr, topF1);
                        codAcValue();
                    } else if (siImp.equals("SI")) {
                        getPsyIs(designDry, designwet, designRh, designGr, designbtu);
                        getAllDeltaGr(inGr, designGr, deltaGr, topF1);
                        codAcValue();
                    }

                }

                break;


            case R.id.psycalDesign2:

                if (designDry2.getText().toString().matches("") && designwet2.getText().toString().matches("") && designRh2.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas fill atlist two input field", Toast.LENGTH_SHORT).show();
                } else if (designDry2.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas enter DBT value", Toast.LENGTH_SHORT).show();
                } else if ((designwet2.getText().toString().matches("")) && ((designRh2.getText().toString().matches("")))) {
                    Toast.makeText(InputActivity.this, "Pleas enter WBT OR RH value", Toast.LENGTH_SHORT).show();
                } else if (!(designwet2.getText().toString().matches("")) || (!(designRh2.getText().toString().matches("")))) {
                    actvityLevelValue(designDry2, activityvalue, activityPos);

                    radioSi = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());

                    siImp = radioSi.getText().toString();

                    if (siImp.equals("IMP")) {
                        getPsyImp2(designDry2, designwet2, designRh2, designGr2, designbtu2);
//                          getPsyImp2(designDry, designwet, designRh, designGr, designbtu);
                        getAllDeltaGr(inGr, designGr2, deltaGr, topF1);
                        codAcValue();
                    } else if (siImp.equals("SI")) {
                        getPsyIs(designDry, designwet, designRh, designGr, designbtu);
                        getAllDeltaGr(inGr, designGr, deltaGr, topF1);
                        codAcValue();
                    }

                }

                break;

            case R.id.codcal:

                if (outDoorDrybulb.getText().toString().matches("") && outDoorWetbulb.getText().toString().matches("") && outDoorRh.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas fill atlist two input field", Toast.LENGTH_SHORT).show();
                } else if (outDoorDrybulb.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas enter DBT value", Toast.LENGTH_SHORT).show();
                } else if ((outDoorWetbulb.getText().toString().matches("")) && ((outDoorRh.getText().toString().matches("")))) {
                    Toast.makeText(InputActivity.this, "Pleas enter WBT OR RH value", Toast.LENGTH_SHORT).show();
                } else if (!(outDoorWetbulb.getText().toString().matches("")) || (!(outDoorRh.getText().toString().matches("")))) {
                    radioSi = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());

                    siImp = radioSi.getText().toString();

                    if (siImp.equals("IMP")) {
                        getonlyRGPsyImp(outDoorDrybulb, outDoorWetbulb, outDoorRh, outDoorGr);
                        getAllDeltaGr(outDoorGr, designGr, outDoordeltaGr, outDoorf1);
                    } else if (siImp.equals("SI")) {
                        getonlyRGPsyIs(outDoorDrybulb, outDoorWetbulb, outDoorRh, outDoorGr);
                        getAllDeltaGr(outDoorGr, designGr, outDoordeltaGr, outDoorf1);
                    }

                }


                break;

            case R.id.cofocal:

                if (fixedopningDrybulb.getText().toString().matches("") && fixedopningWetbulb.getText().toString().matches("") && fixedopningRh.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas fill atlist two input field", Toast.LENGTH_SHORT).show();
                } else if (fixedopningDrybulb.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas enter DBT value", Toast.LENGTH_SHORT).show();
                } else if ((fixedopningWetbulb.getText().toString().matches("")) && ((fixedopningRh.getText().toString().matches("")))) {
                    Toast.makeText(InputActivity.this, "Pleas enter WBT OR RH value", Toast.LENGTH_SHORT).show();
                } else if (!(fixedopningWetbulb.getText().toString().matches("")) || (!(fixedopningRh.getText().toString().matches("")))) {
                    radioSi = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());

                    siImp = radioSi.getText().toString();

                    if (siImp.equals("IMP")) {
                        getonlyRGPsyImp(fixedopningDrybulb, fixedopningWetbulb, fixedopningRh, fixedopningGr);
                        getAllDeltaGr(fixedopningGr, designGr, fixedopningdeltaGr, cofoF1);
                    } else if (siImp.equals("SI")) {
                        getonlyRGPsyIs(fixedopningDrybulb, fixedopningWetbulb, fixedopningRh, fixedopningGr);
                        getAllDeltaGr(fixedopningGr, designGr, fixedopningdeltaGr, cofoF1);
                    }

                }

                break;

            case R.id.faqcal:

                if (faqDrybulb.getText().toString().matches("") && faqWetbulb.getText().toString().matches("") && faqRh.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas fill atlist two input field", Toast.LENGTH_SHORT).show();
                } else if (faqDrybulb.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas enter DBT value", Toast.LENGTH_SHORT).show();
                } else if ((faqWetbulb.getText().toString().matches("")) && ((faqRh.getText().toString().matches("")))) {
                    Toast.makeText(InputActivity.this, "Pleas enter WBT OR RH value", Toast.LENGTH_SHORT).show();
                } else if (!(faqWetbulb.getText().toString().matches("")) || (!(faqRh.getText().toString().matches("")))) {
                    radioSi = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());

                    siImp = radioSi.getText().toString();


                    if (siImp.equals("IMP")) {
                        getPsyImp(faqDrybulb, faqWetbulb, faqRh, faqGr, faqeEnthalpy);
                        if (!(faqGr.getText().toString().length() == 0) && !(designGr.getText().toString().length() == 0)) {
                            double iacGrdata = Double.parseDouble(faqGr.getText().toString());
                            double topdesign = Double.parseDouble(designGr.getText().toString());
                            double codDeltaGr = iacGrdata - topdesign;
                            String dvalue = String.valueOf(codDeltaGr);
                            faqdeltaGr.setText(String.format("%.2f", codDeltaGr));
                            //String.format("%.2f",codDeltaGr)
                        }
                    } else if (siImp.equals("SI")) {
                        getPsyIs(faqDrybulb, faqWetbulb, faqRh, faqGr, faqeEnthalpy);
                        if (!(faqGr.getText().toString().length() == 0) && !(designGr.getText().toString().length() == 0)) {
                            double iacGrdata = Double.parseDouble(faqGr.getText().toString());
                            double topdesign = Double.parseDouble(designGr.getText().toString());
                            double codDeltaGr = ((iacGrdata * 15.4324 * 453.592) - (topdesign * 15.4324 * 453.592));
                            String dvalue = String.valueOf(codDeltaGr);
                            faqdeltaGr.setText(String.format("%.2f", codDeltaGr));
                        }
                    }

                }


                break;

            case R.id.iaccal:

                /*if (inDrybulb.getText().toString().matches("")&&inWetbulb.getText().toString().matches("")&&inRh.getText().toString().matches("")){
                    inDrybulb.setText("0");
                    inWetbulb.setText("0");
                    inRh.setText("0");
                }
                else if (inDrybulb.getText().toString().matches("")){
                    inWetbulb.setText("0");

                }
                else if (inWetbulb.getText().toString().matches("")){
                    inWetbulb.setText("0");
                }
                else if (inRh.getText().toString().matches("")){
                    inRh.setText("0");
                }
                 if (designDry.getText().toString().matches("")&&designwet.getText().toString().matches("")&&designRh.getText().toString().matches("")){
                    designDry.setText("0");
                    designwet.setText("0");
                    designRh.setText("0");
                }

                else if (designDry.getText().toString().matches("")){
                    designDry.setText("0");
                }
                else if (designwet.getText().toString().matches("")){
                    designwet.setText("0");
                }
                else if (designRh.getText().toString().matches("")){
                    designRh.setText("0");
                }

                 if (outDoorDrybulb.getText().toString().matches("")&&outDoorWetbulb.getText().toString().matches("")&&outDoorRh.getText().toString().matches("")){
                    outDoorDrybulb.setText("0");
                    outDoorWetbulb.setText("0");
                    outDoorRh.setText("0");
                }

                else if (outDoorDrybulb.getText().toString().matches("")){
                    outDoorDrybulb.setText("0");
                }
                else if (outDoorWetbulb.getText().toString().matches("")){
                    outDoorWetbulb.setText("0");
                }
                else if (outDoorRh.getText().toString().matches("")){
                    outDoorRh.setText("0");
                }

                 if (fixedopningDrybulb.getText().toString().matches("")&&fixedopningWetbulb.getText().toString().matches("")&&fixedopningRh.getText().toString().matches("")){
                    fixedopningDrybulb.setText("0");
                    fixedopningWetbulb.setText("0");
                    fixedopningRh.setText("0");
                }

                else if (fixedopningDrybulb.getText().toString().matches("")){
                    fixedopningDrybulb.setText("0");
                }
                else if (fixedopningWetbulb.getText().toString().matches("")){
                    fixedopningWetbulb.setText("0");
                }
                else if (fixedopningRh.getText().toString().matches("")){
                    fixedopningRh.setText("0");
                }


                 if (faqDrybulb.getText().toString().matches("")&&faqWetbulb.getText().toString().matches("")&&faqRh.getText().toString().matches("")){
                    faqDrybulb.setText("0");
                    faqWetbulb.setText("0");
                    faqRh.setText("0");
                }


                else if (faqDrybulb.getText().toString().matches("")){
                    faqDrybulb.setText("0");
                }
                else if (faqWetbulb.getText().toString().matches("")){
                    faqWetbulb.setText("0");
                }
                else if (faqRh.getText().toString().matches("")){
                    faqRh.setText("0");
                }


                 if (iacDrybulb.getText().toString().matches("")&&iacWetbulb.getText().toString().matches("")&&iacRh.getText().toString().matches("")){
                    iacDrybulb.setText("0");
                    iacWetbulb.setText("0");
                    iacRh.setText("0");
                }

                 if (iacDrybulb.getText().toString().matches("")){
                    iacDrybulb.setText("0");
                }
                 if (iacWetbulb.getText().toString().matches("")){
                    iacWetbulb.setText("0");
                }
                 if (iacRh.getText().toString().matches("")){
                    iacRh.setText("0");
                }

                radioSi = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());

                siImp = radioSi.getText().toString();
                cofoF1.setText(" ");
                if (siImp.equals("IMP")){
                    getonlyRGPsyImp(iacDrybulb,iacWetbulb,iacRh,iacGr);
                    if (!(iacGr.getText().toString().length() == 0) && !(designGr.getText().toString().length() == 0)) {
                        double iacGrdata = Double.parseDouble(iacGr.getText().toString());
                        double topdesign = Double.parseDouble(designGr.getText().toString());
                        double codDeltaGr = iacGrdata - topdesign;
                        String dvalue = String.valueOf(codDeltaGr);
                        iacdeltaGr.setText(dvalue);
                    }
                }
                else if (siImp.equals("SI")) {
                    getonlyRGPsyIs(iacDrybulb, iacWetbulb, iacRh, iacGr);

                    if (!(iacGr.getText().toString().length() == 0) && !(designGr.getText().toString().length() == 0)) {
                        double iacGrdata = Double.parseDouble(iacGr.getText().toString());
                        double topdesign = Double.parseDouble(designGr.getText().toString());
                        double codDeltaGr=((iacGrdata*15.4324*453.592)-(topdesign*15.4324*453.592));
                        String dvalue = String.valueOf(codDeltaGr);
                        iacdeltaGr.setText(dvalue);
                    }
                }*/

                if (iacDrybulb.getText().toString().matches("") && iacWetbulb.getText().toString().matches("") && iacRh.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas fill atlist two input field", Toast.LENGTH_SHORT).show();
                } else if (iacDrybulb.getText().toString().matches("")) {
                    Toast.makeText(InputActivity.this, "Pleas enter DBT value", Toast.LENGTH_SHORT).show();
                } else if ((iacWetbulb.getText().toString().matches("")) && ((iacRh.getText().toString().matches("")))) {
                    Toast.makeText(InputActivity.this, "Pleas enter WBT OR RH value", Toast.LENGTH_SHORT).show();
                } else if (!(iacWetbulb.getText().toString().matches("")) || (!(iacRh.getText().toString().matches("")))) {

                    radioSi = (RadioButton) rgImpSi.findViewById(rgImpSi.getCheckedRadioButtonId());

                    siImp = radioSi.getText().toString();
                    if (siImp.equals("IMP")) {
                        getonlyRGPsyImp(iacDrybulb, iacWetbulb, iacRh, iacGr);
                        if (!(iacGr.getText().toString().length() == 0) && !(designGr.getText().toString().length() == 0)) {
                            double iacGrdata = Double.parseDouble(iacGr.getText().toString());
                            double topdesign = Double.parseDouble(designGr.getText().toString());
                            double codDeltaGr = iacGrdata - topdesign;
                            String dvalue = String.valueOf(codDeltaGr);
                            iacdeltaGr.setText(String.format("%.2f", codDeltaGr));
                        }
                    } else if (siImp.equals("SI")) {
                        getonlyRGPsyIs(iacDrybulb, iacWetbulb, iacRh, iacGr);

                        if (!(iacGr.getText().toString().length() == 0) && !(designGr.getText().toString().length() == 0)) {
                            double iacGrdata = Double.parseDouble(iacGr.getText().toString());
                            double topdesign = Double.parseDouble(designGr.getText().toString());
                            double codDeltaGr = ((iacGrdata * 15.4324 * 453.592) - (topdesign * 15.4324 * 453.592));
                            String dvalue = String.valueOf(codDeltaGr);
                            iacdeltaGr.setText(String.format("%.2f", codDeltaGr));
                        }
                    }

                }


                break;

            case R.id.btn:

                if (counter == 0) {
                    dmlyt0.setVisibility(View.VISIBLE);
                    counter += 1;
                } else if (counter == 1) {
                    dmlyt1.setVisibility(View.VISIBLE);
                    counter += 1;
                } else if (counter == 2) {
                    dmlyt2.setVisibility(View.VISIBLE);
                    counter += 1;
                } else if (counter == 3) {
                    dmlyt3.setVisibility(View.VISIBLE);
                    counter += 1;
                } else if (counter == 4) {
                    dmlyt4.setVisibility(View.VISIBLE);
                    counter += 1;
                } else if (counter == 5) {
                    dmlyt5.setVisibility(View.VISIBLE);
                    counter += 1;
                } else if (counter == 6) {
                    dmlyt6.setVisibility(View.VISIBLE);
                    counter += 1;
                } else if (counter == 7) {
                    dmlyt7.setVisibility(View.VISIBLE);
                    counter += 1;
                } else if (counter == 8) {
                    dmlyt8.setVisibility(View.VISIBLE);
                    counter += 1;
                } else if (counter == 9) {
                    dmlyt9.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.dlbtn:

                if (dlcounter == 0) {
                    dllyt0.setVisibility(View.VISIBLE);
                    dlcounter += 1;
                } else if (dlcounter == 1) {
                    dllyt1.setVisibility(View.VISIBLE);
                    dlcounter += 1;
                } else if (dlcounter == 2) {
                    dllyt2.setVisibility(View.VISIBLE);
                    dlcounter += 1;
                } else if (dlcounter == 3) {
                    dllyt3.setVisibility(View.VISIBLE);
                    dlcounter += 1;
                } else if (dlcounter == 4) {
                    dllyt4.setVisibility(View.VISIBLE);
                    dlcounter += 1;
                } else if (dlcounter == 5) {
                    dllyt5.setVisibility(View.VISIBLE);
                    //counter+=1;
                }

                break;

            case R.id.btnfixed:

                if (fodcounter == 0) {
                    fod_lyt0.setVisibility(View.VISIBLE);
                    fodcounter += 1;
                } else if (fodcounter == 1) {
                    fod_lyt1.setVisibility(View.VISIBLE);
                    fodcounter += 1;
                } else if (fodcounter == 2) {
                    fod_lyt2.setVisibility(View.VISIBLE);
                    fodcounter += 1;
                } else if (fodcounter == 3) {
                    fod_lyt3.setVisibility(View.VISIBLE);
                    fodcounter += 1;
                } else if (fodcounter == 4) {
                    fod_lyt4.setVisibility(View.VISIBLE);
                    fodcounter += 1;
                } else if (fodcounter == 5) {
                    fod_lyt5.setVisibility(View.VISIBLE);
                    //counter+=1;
                }

                break;


            case R.id.btn_nextone:
                getOutLet1(designGr, designDry);
                // Avnish Code


               // getPsyImp(designDry, designwet1, designRh1, designGr1, designbtu1);


                InputInformation.setAmbDBT(inDrybulb .getText().toString());
                InputInformation.setAmbWBT(inWetbulb.getText().toString());
                InputInformation.setAmbRH(inRh .getText().toString());
                InputInformation.setAmbgr(inGr .getText().toString());
                InputInformation.setAmbenthaply(inEnthalpy .getText().toString());

                InputInformation.setDcDBT(designDry.getText().toString());
                InputInformation.setDcWBT(designwet.getText().toString());
                InputInformation.setDcRH(designRh.getText().toString());
                InputInformation.setDcgr(designGr.getText().toString());
                InputInformation.setDcenthaply(designbtu.getText().toString());

                InputInformation.setDeltagain(deltaGr.getText().toString());
                InputInformation.setF1_1(topF1.getText().toString());


//                InputInformation.setDRLength(txtTlength.getText().toString());
//                InputInformation.setDRWidth(txtTwidth.getText().toString());
//                InputInformation.setDRheight(txtThight.getText().toString());
//                InputInformation.setDRvolume(txtTvolume.getText().toString());


                InputInformation.setDimroomlen(bm_l0.getText().toString());
                InputInformation.setDimroomwid(bm_w0.getText().toString());
                InputInformation.setDimroomhei(bm_h0.getText().toString());
                InputInformation.setDimroomvol(bm_v0.getText().toString());

                InputInformation.setDimroomlen1(bm_l1.getText().toString());
                InputInformation.setDimroomwid1(bm_w1.getText().toString());
                InputInformation.setDimroomhei1(bm_h1.getText().toString());
                InputInformation.setDimroomvol1(bm_v1.getText().toString());

                InputInformation.setDimroomlen2(bm_l2.getText().toString());
                InputInformation.setDimroomwid2(bm_w2.getText().toString());
                InputInformation.setDimroomhei2(bm_h2.getText().toString());
                InputInformation.setDimroomvol2(bm_v2.getText().toString());

                InputInformation.setDimroomlen3(bm_l3.getText().toString());
                InputInformation.setDimroomwid3(bm_w3.getText().toString());
                InputInformation.setDimroomhei3(bm_h3.getText().toString());
                InputInformation.setDimroomvol3(bm_v3.getText().toString());

                InputInformation.setDimroomlen4(bm_l4.getText().toString());
                InputInformation.setDimroomwid4(bm_w4.getText().toString());
                InputInformation.setDimroomhei4(bm_h4.getText().toString());
                InputInformation.setDimroomvol4(bm_v4.getText().toString());

                InputInformation.setDimroomlen5(bm_l5.getText().toString());
                InputInformation.setDimroomwid5(bm_w5.getText().toString());
                InputInformation.setDimroomhei5(bm_h5.getText().toString());
                InputInformation.setDimroomvol5(bm_v5.getText().toString());



                InputInformation.setF2(inF2.getText().toString());
                InputInformation.setF3(f3value.getText().toString());
                InputInformation.setF4(f4value.getText().toString());

                InputInformation.setDLWidth(dlw0.getText().toString());
                InputInformation.setDLheight(dlh0.getText().toString());
                InputInformation.setDLDoorOpening(dldop0.getText().toString());

                        InputInformation.setDLWidth1(dlw1.getText().toString());
                        InputInformation.setDLheight1(dlh1.getText().toString());
                        InputInformation.setDLDoorOpening1(dldop1.getText().toString());

                        InputInformation.setDLWidth2(dlw2.getText().toString());
                        InputInformation.setDLheight2(dlh2.getText().toString());
                        InputInformation.setDLDoorOpening2(dldop2.getText().toString());

                        InputInformation.setDLWidth3(dlw3.getText().toString());
                        InputInformation.setDLheight3(dlh3.getText().toString());
                        InputInformation.setDLDoorOpening3(dldop3.getText().toString());

                        InputInformation.setDLWidth4(dlw4.getText().toString());
                        InputInformation.setDLheight4(dlh4.getText().toString());
                        InputInformation.setDLDoorOpening4(dldop4.getText().toString());

                        InputInformation.setDLWidth5(dlw5.getText().toString());
                        InputInformation.setDLheight5(dlh5.getText().toString());
                        InputInformation.setDLDoorOpening5(dldop5.getText().toString());


                InputInformation.setDRLength(outDoorDrybulb.getText().toString());
                InputInformation.setDRWidth(outDoorWetbulb.getText().toString());
                InputInformation.setDRheight(outDoorRh.getText().toString());
                InputInformation.setDRvolume(outDoorGr.getText().toString());

                InputInformation.setDeltaGr(outDoordeltaGr.getText().toString());
                InputInformation.setCOTF1(outDoorf1.getText().toString());
                InputInformation.setNoperson(inNumberofperson.getText().toString());
                InputInformation.setActLevel(activityvalue.getText().toString());

                InputInformation.setFODheight(fod_l0.getText().toString());
                InputInformation.setFODWidth(fod_w0.getText().toString());
                InputInformation.setFODDept(fod_d0.getText().toString());
                InputInformation.setFODvolume(fod_v0.getText().toString());

                        InputInformation.setFODheight1(fod_l1.getText().toString());
                        InputInformation.setFODWidth1(fod_w1.getText().toString());
                        InputInformation.setFODDept1(fod_d1.getText().toString());
                        InputInformation.setFODvolume1(fod_v1.getText().toString());

                        InputInformation.setFODheight2(fod_l2.getText().toString());
                        InputInformation.setFODWidth2(fod_w2.getText().toString());
                        InputInformation.setFODDept2(fod_d2.getText().toString());
                        InputInformation.setFODvolume2(fod_v2.getText().toString());

                        InputInformation.setFODheight3(fod_l3.getText().toString());
                        InputInformation.setFODWidth3(fod_w3.getText().toString());
                        InputInformation.setFODDept3(fod_d3.getText().toString());
                        InputInformation.setFODvolume3(fod_v3.getText().toString());

                        InputInformation.setFODheight4(fod_l4.getText().toString());
                        InputInformation.setFODWidth4(fod_w4.getText().toString());
                        InputInformation.setFODDept4(fod_d4.getText().toString());
                        InputInformation.setFODvolume4(fod_v4.getText().toString());

                        InputInformation.setFODheight5(fod_l5.getText().toString());
                        InputInformation.setFODWidth5(fod_w5.getText().toString());
                        InputInformation.setFODDept5(fod_d5.getText().toString());
                        InputInformation.setFODvolume5(fod_v5.getText().toString());


                InputInformation.setCofoDbt(fixedopningDrybulb.getText().toString());
                InputInformation.setCofoWbt(fixedopningWetbulb.getText().toString());
                InputInformation.setCofoRh(fixedopningRh.getText().toString());
                InputInformation.setCOFO_gr(fixedopningGr.getText().toString());

                InputInformation.setDelta2(fixedopningdeltaGr.getText().toString());
                InputInformation.setF2DTGT(cofoF1.getText().toString());
                InputInformation.setPositivePressure(p_pressure.getText().toString());
                InputInformation.setCFM(faqCfm.getText().toString());

                InputInformation.setFAQDBT(faqDrybulb.getText().toString());
                InputInformation.setFAQWBT(faqWetbulb.getText().toString());
                InputInformation.setFAQRH(faqRh.getText().toString());
                InputInformation.setFAQgr(faqGr.getText().toString());
                InputInformation.setFAQenthaply(faqeEnthalpy .getText().toString());

                InputInformation.setDelta3(faqdeltaGr.getText().toString());
                InputInformation.setEAQ(exhaust_edt.getText().toString());
                InputInformation.setIAQ(infiltrationLaqCfm.getText().toString());


                InputInformation.setIAQDBT(iacDrybulb.getText().toString());
                InputInformation.setIAQWBT(iacWetbulb.getText().toString());
                InputInformation.setIAQRH(iacRh.getText().toString());
                InputInformation.setIAQgr(iacGr.getText().toString());

                InputInformation.setDelta4(iacdeltaGr.getText().toString());
                InputInformation.setDesignADP(designAdp.getText().toString());
                InputInformation.setHeatLoad(inHlea.getText().toString());
                InputInformation.setCFM2(btu_cfm.getText().toString());
                InputInformation.setAHU(ahuCapacity.getText().toString());
                InputInformation.setExtgernalStatic(inEspr.getText().toString());
                InputInformation.setFiltration(inFiltration.getText().toString());

                InputInformation.setPLAPl(inPll.getText().toString());
                InputInformation.setPlaImc(plImc.getText().toString());
                InputInformation.setPlaFmc(plFmc.getText().toString());
                InputInformation.setPLA_DryTbl(plDt.getText().toString());

                break;


        }
    }

    // print calculation

    private void printCalculation(double outletData) {
        // if( !(inDrybulb.getText().toString().length()==0)&&!(inFiltration.getText().toString().length()==0)&&!(inWetbulb.getText().toString().length()==0)){
        //input value

        String ocdbt = inDrybulb.getText().toString();
        String ocwbt = inWetbulb.getText().toString();
        String ocrh = inRh.getText().toString();
        String ocgrs = inGr.getText().toString();
        String ocbtu = inEnthalpy.getText().toString();

        String dcdbt = designDry.getText().toString();
        String dcwbt = designwet.getText().toString();
        String dcrh = designRh.getText().toString();
        String dcgrs = designGr.getText().toString();
        String dcbtu = designbtu.getText().toString();
        //total room dimensions
        String troomdim = txtTvolume.getText().toString();
        String troomlen = txtTlength.getText().toString();
        String troomwid = txtTwidth.getText().toString();
        String troomhig = txtThight.getText().toString();


        //PERMEATION calculation
        double tdataValue = Double.valueOf(txtTvolume.getText().toString());
        double topDeltaDr = Double.valueOf(deltaGr.getText().toString());
        double topF1Value = Double.valueOf(topF1.getText().toString());
        double F2Value = Double.valueOf(inF2.getText().toString());
        double F3Value = Double.valueOf(f3value.getText().toString());
        double F4Value = Double.valueOf(f4value.getText().toString());
        PERMEATION_LOAD = ((tdataValue * topDeltaDr * topF1Value * F2Value * F3Value * F4Value) / 14);

        //faqCfm
        double exhausCfmData=0.0;
        if (exhaust_edt.getText().toString().matches("")) {
            exhausCfmData=0.0;
            Log.w("exhausCfmData", ">>" + exhausCfmData);
        } else {
            exhausCfmData = Double.valueOf(exhaust_edt.getText().toString());

            Log.w("exhausCfmData", ">>" + exhausCfmData);

        }


        double faqCfmData=0.0;

        if(!p_outpressure.getText().toString().matches("")&&!faqCfm.getText().toString().matches("")){

            if(Double.valueOf(faqCfm.getText().toString())< Double.valueOf(p_outpressure.getText().toString())){
                faqCfmData=Double.valueOf(p_outpressure.getText().toString())+exhausCfmData;

                Log.w("faqCfmData load if if", ">>" + faqCfmData);
            }else {
                faqCfmData=Double.valueOf(faqCfm.getText().toString())+exhausCfmData;
                Log.w("faqCfmData load if else", ">>" + faqCfmData);
            }
        }
        else{ if (!faqCfm.getText().toString().matches("")) {
            faqCfmData = Double.valueOf(faqCfm.getText().toString())+exhausCfmData;

            Log.w("faqCfmData load if else", ">>" + faqCfmData);

        }else if(!p_outpressure.getText().toString().matches("")) {
            faqCfmData=Double.valueOf(p_outpressure.getText().toString())+exhausCfmData;

            Log.w("faqCfmData load if else", ">>" + faqCfmData);
        }
        else if(p_outpressure.getText().toString().matches("")&&faqCfm.getText().toString().matches("")&&!exhaust_edt.getText().toString().matches("")) {
            faqCfmData=Double.valueOf(exhaust_edt.getText().toString());
            Log.w("faqCfmData load if else", ">>" + faqCfmData);
        }else {
            faqCfmData=0.0+exhausCfmData;
            Log.w("faqCfmData load if else", ">>" + faqCfmData);
        }

        }


        Log.w("P load topDeltaDr ", ">>" + tdataValue + "deltagr" + topDeltaDr + "f1 is" + topF1Value + "f2" + F2Value + "f3" + F3Value + "f4" + F4Value);
        Log.w("permeation load", ">>" + (7500 * 131.9 * 3.264 * 0.65 * 1 * .75) / 14);

        //OCCUPACY calculation Or personnal load
        double activityLevel;

        if (inNumberofperson.getText().toString().matches("") && activityvalue.getText().toString().matches("")) {
            inNumberofperson.setText("0.0");
            activityvalue.setText("0.0");
            double noOfPerson = Double.valueOf(inNumberofperson.getText().toString());
             activityLevel = Double.valueOf(activityvalue.getText().toString());
            OCCUPACY = noOfPerson * activityLevel;
            Log.w("occupacy", ">>" + OCCUPACY);
        } else if (inNumberofperson.getText().toString().matches("")) {
            inNumberofperson.setText("0.0");

            double noOfPerson = Double.valueOf(inNumberofperson.getText().toString());
            activityLevel = Double.valueOf(activityvalue.getText().toString());
            OCCUPACY = noOfPerson * activityLevel;
            Log.w("occupacy", ">>" + OCCUPACY);
        } else if (activityvalue.getText().toString().matches("")) {
            activityvalue.setText("0.0");
            double noOfPerson = Double.valueOf(inNumberofperson.getText().toString());
            activityLevel = Double.valueOf(activityvalue.getText().toString());
            OCCUPACY = noOfPerson * activityLevel;
            Log.w("occupacy", ">>" + OCCUPACY);
        } else {
            double noOfPerson = Double.valueOf(inNumberofperson.getText().toString());
             activityLevel = Double.valueOf(activityvalue.getText().toString());
            OCCUPACY = noOfPerson * activityLevel;
            Log.w("occupacy", ">>" + OCCUPACY);

        }

        //  double outletData = getOutLet(designGr, designDry);

        //double outletMixedData=getOutLetMixed(designGr,designDry);
       /*Toast.makeText(InputActivity.this,"outLetMixed data = "+outletMixedData+"out is= "+outletData,Toast.LENGTH_SHORT).show();*/
        doorLoadCal();
        fixedLoadCal();

        //INFILTERATION Opening
        if (infiltrationLaqCfm.getText().toString().matches("")) {
            infiltrationLaqCfm.setText("0.0");
            double cfmInfiltration = Double.valueOf(infiltrationLaqCfm.getText().toString());
            double fixedValue = 4.285714286;
            double infiltrationDeltaGr = Double.valueOf(iacdeltaGr.getText().toString());
            INFILTERATION_LOAD = cfmInfiltration * fixedValue * infiltrationDeltaGr;
            Log.w("infilteration", ">>" + INFILTERATION_LOAD);
        } else {
            double cfmInfiltration = Double.valueOf(infiltrationLaqCfm.getText().toString());
            double fixedValue = 4.285714286;
            double infiltrationDeltaGr = Double.valueOf(iacdeltaGr.getText().toString());
            INFILTERATION_LOAD = cfmInfiltration * fixedValue * infiltrationDeltaGr;
            Log.w("infilteration", ">>" + INFILTERATION_LOAD);
        }

        //PRODUCT load
        if (inPll.getText().toString().matches("")) {
            inPll.setText("0.0");
            String inputpl = inPll.getText().toString();
            double pl = Double.valueOf(inputpl);
            double pValue = 2.205;
            double pValue2 = 7000;
            PRODUCT_LOAD = pl * pValue * pValue2;
            ANYOTHER_LOAD = 0;
            TOTAL_LOAD = PERMEATION_LOAD + ANYOTHER_LOAD + INFILTERATION_LOAD + OCCUPACY + doorLoadCal() + fixedLoadCal() + PRODUCT_LOAD;
            SAFETY_FACTOR = TOTAL_LOAD * 0.05;
            LATENT_M_LOAD = TOTAL_LOAD + SAFETY_FACTOR;
        } else {
            String inputpl = inPll.getText().toString();
            double pl = Double.valueOf(inputpl);
            double pValue = 2.205;
            double pValue2 = 7000;
            PRODUCT_LOAD = pl * pValue * pValue2;
            ANYOTHER_LOAD = 0;
            TOTAL_LOAD = PERMEATION_LOAD + ANYOTHER_LOAD + INFILTERATION_LOAD + OCCUPACY + doorLoadCal() + fixedLoadCal() + PRODUCT_LOAD;
            SAFETY_FACTOR = TOTAL_LOAD * 0.05;
            LATENT_M_LOAD = TOTAL_LOAD + SAFETY_FACTOR;
        }
        //fresh air load
        if (faqCfm.getText().toString().matches("")||faqCfm.getText().toString().isEmpty()) {
            //faqCfm.setText("0.0");
            //double faqCfmData = Double.valueOf(faqCfm.getText().toString());
            //double faqdeltaGrData = Double.valueOf(faqdeltaGr.getText().toString());

            FRESH_AIR_LOAD = 0.0;
        } else {
            //double faqCfmData = Double.valueOf(faqCfm.getText().toString());
            double faqdeltaGrData=0.0;
            if(faqdeltaGr.getText().toString().matches("")){
                faqdeltaGrData=0.0;
            }else {
                faqdeltaGrData = Double.valueOf(faqdeltaGr.getText().toString());
            }

            FRESH_AIR_LOAD = faqCfmData * 4.285714286 * faqdeltaGrData;


        }

        TOTAL_LATENT = LATENT_M_LOAD + FRESH_AIR_LOAD;

        Double.valueOf(String.format("%.2f", TOTAL_LATENT));
        /*double tValueIs=Double.valueOf(String.format("%.2f", TOTAL_LATENT))*14;*/
        // double tValueIs=Double.valueOf(String.format("%.2f", TOTAL_LATENT))*14;

        double dGrvalueIs = Double.valueOf(dcgrs);
        double tlcfm = (TOTAL_LATENT / 60) * 14;

       /* DC_Cfm = (((TOTAL_LATENT / 60) * 14) / (dGrvalueIs - (outletData * 7)));*/

        DC_Cfm = (((LATENT_M_LOAD / 60) * 14) / (dGrvalueIs - (outletData * 7)));

        //BasicInformation.setOutlet(String.format("%.2f", value_outlet));
        Log.w("OUTLET IS ", ">>" + outletData);

        //if fresh air cfm !=0
        if (faqCfm.getText().toString().matches("")||faqCfm.getText().toString().isEmpty()) {
            BasicInformation.setDehumidifier(String.format("%.2f", DC_Cfm));
            String modelNo = getModel(DC_Cfm);
            BasicInformation.setModel(modelNo);
            Intent in = new Intent(InputActivity.this, PrintSheet.class);
            startActivity(in);
        } else {
            String dbtValue = "0.0";
            double faqtempData=0.0,faqgrData=0.0;

            try {
                faqtempData = new Double(faqDrybulb.getText().toString());


            } catch (NumberFormatException e) {
                faqtempData = 0; // your default value
            }

            try {
                faqgrData = new Double(faqGr.getText().toString());


            } catch (NumberFormatException e) {
                faqgrData = 0; // your default value
            }
            //double faqCfmData = Double.valueOf(faqCfm.getText().toString());

            double designtempData = Double.valueOf(dcdbt);
            double designgrData = Double.valueOf(dcgrs);

            double MIXED_TEMP=((DC_Cfm*designtempData)+(faqCfmData*faqtempData))/(DC_Cfm+faqCfmData);

            MIXED_GR=((DC_Cfm*designgrData)+(faqCfmData*faqgrData))/(DC_Cfm+faqCfmData);

            Log.w("MIXED_TEMP ", ">>" + MIXED_TEMP);
            Log.w("out MIXED_GR is ", ">>" + MIXED_GR);

            double mixed_grvalue = (MIXED_GR / 7);
            double mixed_dbt_value = (MIXED_TEMP - 32) * 5 / 9;

            Log.w("mixed_grvalue", "if mixdoutlet" + mixed_grvalue);
            Log.w("mixed_dbt_value", "if mixdoutlet" + mixed_dbt_value);

            if (mixed_dbt_value == 10 && mixed_dbt_value < 10) {
                dbtValue = "10";
            } else if (mixed_dbt_value > 10 && mixed_dbt_value < 11 || mixed_dbt_value == 11) {
                dbtValue = "11";
            } else if (mixed_dbt_value > 11 && mixed_dbt_value < 12 || mixed_dbt_value == 12) {
                dbtValue = "12";
            } else if (mixed_dbt_value > 12 && mixed_dbt_value < 13 || mixed_dbt_value == 13) {
                dbtValue = "13";
            } else if (mixed_dbt_value > 13 && mixed_dbt_value < 14 || mixed_dbt_value == 14) {
                dbtValue = "14";
            } else if (mixed_dbt_value > 14 && mixed_dbt_value < 15 || mixed_dbt_value == 15) {
                dbtValue = "15";
            } else if (mixed_dbt_value > 15 && mixed_dbt_value < 16 || mixed_dbt_value == 16) {
                dbtValue = "16";
            } else if (mixed_dbt_value > 16 && mixed_dbt_value < 17 || mixed_dbt_value == 17) {
                dbtValue = "17";
            } else if (mixed_dbt_value > 17 && mixed_dbt_value < 18 || mixed_dbt_value == 18) {
                dbtValue = "18";
            } else if (mixed_dbt_value > 18 && mixed_dbt_value < 19 || mixed_dbt_value == 19) {
                dbtValue = "19";
            } else if (mixed_dbt_value > 19 && mixed_dbt_value < 20 || mixed_dbt_value == 20) {
                dbtValue = "20";
            } else if (mixed_dbt_value > 20 && mixed_dbt_value < 21 || mixed_dbt_value == 21) {
                dbtValue = "21";
            } else if (mixed_dbt_value > 21 && mixed_dbt_value < 22 || mixed_dbt_value == 22) {
                dbtValue = "22";
            } else if (mixed_dbt_value > 22 && mixed_dbt_value < 23 || mixed_dbt_value == 23) {
                dbtValue = "23";
            } else if (mixed_dbt_value > 23 && mixed_dbt_value < 24 || mixed_dbt_value == 24) {
                dbtValue = "24";
            } else if (mixed_dbt_value > 24 && mixed_dbt_value < 25 || mixed_dbt_value == 25) {
                dbtValue = "25";
            } else if (mixed_dbt_value > 25 && mixed_dbt_value < 26 || mixed_dbt_value == 26) {
                dbtValue = "26";
            } else if (mixed_dbt_value > 26 && mixed_dbt_value < 27 || mixed_dbt_value == 27) {
                dbtValue = "27";
            } else if (mixed_dbt_value > 27 && mixed_dbt_value < 28 || mixed_dbt_value == 28) {
                dbtValue = "28";
            } else if (mixed_dbt_value > 28 && mixed_dbt_value < 29 || mixed_dbt_value == 29) {
                dbtValue = "29";
            } else if (mixed_dbt_value > 29 && mixed_dbt_value < 30 || mixed_dbt_value == 30) {
                dbtValue = "30";
            }

            // Toast.makeText(InputActivity.this, " " + dbt_value, Toast.LENGTH_SHORT).show();
            if (InternetStatus.isConnectingToInternet(InputActivity.this)) {
                //BasicInformation.setDvalue(String.valueOf(mixed_grvalue));
                //BasicInformation.setDbtValue(dbtValue);
                new SendMixdValueAsync(InputActivity.this, String.valueOf(mixed_grvalue), dbtValue).execute();
            }

        }


        Log.w("Dc out let is ", ">>" + tlcfm);
        Log.w("out let is ", ">>" + outletData);

        Log.w("divTvalue ", ">>" + DC_Cfm);

        BasicInformation.setAcDbt(ocdbt);
        BasicInformation.setAcWbt(ocwbt);
        BasicInformation.setAcRh(ocrh);
        BasicInformation.setAcGr(ocgrs);
        BasicInformation.setAcBtu(ocbtu);

        BasicInformation.setDcDbt(dcdbt);
        BasicInformation.setDcWbt(dcwbt);
        BasicInformation.setDcRh(dcrh);
        BasicInformation.setDcGr(dcgrs);
        BasicInformation.setDcBtu(dcbtu);

        BasicInformation.setRmLength(troomlen);
        BasicInformation.setRmWidth(troomwid);
        BasicInformation.setRmHeight(troomhig);
        BasicInformation.setRmVolume(troomdim);



        //set InfilterationLoad Cfm
        BasicInformation.setInfilterationLoadCfm(infiltrationLaqCfm.getText().toString());

        BasicInformation.setActivityLevel(String.format("%.2f", activityLevel));

        /*String.format("%.2f", PERMEATION_LOAD);*/
        BasicInformation.setPermeation(String.format("%.2f", PERMEATION_LOAD));

        BasicInformation.setOccupacyCal(String.format("%.2f", OCCUPACY));

        BasicInformation.setInfilterationLoad(String.format("%.2f", INFILTERATION_LOAD));

        BasicInformation.setProductLoad(String.format("%.2f", PRODUCT_LOAD));
        BasicInformation.setAnyotherLoad(String.format("%.2f", ANYOTHER_LOAD));

        BasicInformation.setTotalLoad(String.format("%.2f", TOTAL_LOAD));
        BasicInformation.setSafetyFactor(String.format("%.2f", SAFETY_FACTOR));
        BasicInformation.setLatentLoad(String.format("%.2f", LATENT_M_LOAD));
        BasicInformation.setFreshAir(String.format("%.2f", FRESH_AIR_LOAD));

        BasicInformation.setTLatentLoad(String.format("%.2f", TOTAL_LATENT));

        BasicInformation.setNperson(String.valueOf(inNumberofperson.getText().toString()));
// set system flow value

        if(ahuCapacity.getText().toString().isEmpty()){
            Log.e("ahuCapacity is if", ">>");
            BasicInformation.setAhu("0");
        }else {
            BasicInformation.setAhu(ahuCapacity.getText().toString());
            Log.e("ahuCapacity is else", ">>"+ahuCapacity.getText().toString());
        }

        if(designAdp.getText().toString().isEmpty()){
            Log.e("ahuCapacity is if", ">>");
            BasicInformation.setAdp("0");
        }else {
            BasicInformation.setAdp(designAdp.getText().toString());
            Log.e("ahuCapacity is else", ">>"+designAdp.getText().toString());
        }

        if(faqDrybulb.getText().toString().isEmpty()){
            Log.e("faqDrybulb is if", ">>");
            BasicInformation.setFaqdry("0");
        }else {
            Log.e("faqDrybulb is else", ">>"+faqDrybulb.getText().toString());
            BasicInformation.setFaqdry(faqDrybulb.getText().toString());
        }

        if(faqGr.getText().toString().isEmpty()){
            Log.e("faqGr is if", ">>");
            BasicInformation.setPreGr("0");
        }else {
            Log.e("faqGr is else", ">>"+faqGr.getText().toString());
            BasicInformation.setPreGr(faqGr.getText().toString());
        }

        if(faqeEnthalpy.getText().toString().isEmpty()){
            Log.e("faqeEnthalpy is if", ">>");
            BasicInformation.setFaqBtu("0");
        }else {
            Log.e("faqeEnthalpy is else", ">>"+faqeEnthalpy.getText().toString());
            BasicInformation.setFaqBtu(faqeEnthalpy.getText().toString());
        }


        BasicInformation.setFaq(String.format("%.2f", faqCfmData));
        Log.e("faqCfmData is else", ">>"+faqCfmData);


        Log.w("product load", ">>" + PRODUCT_LOAD);
        Log.w("any other load", ">>" + ANYOTHER_LOAD);
        Log.w("total load", ">>" + TOTAL_LOAD);
        Log.w("safety factor", ">>" + SAFETY_FACTOR);
        Log.w("latent load", ">>" + LATENT_M_LOAD);
        Log.w("freesh air load", ">>" + FRESH_AIR_LOAD);


        Log.w("TOTAL_LATENT ", ">>" + TOTAL_LATENT);

        Log.w("outletData", ">>" + outletData);
        Log.w("DC_REQUIRED", ">>" + DC_Cfm);



    }

    public static String getModel(double cfm) {

        String modelNo = "";

        if (cfm <= 110) {
            modelNo = "CRD-10";
        } else if (cfm >= 111 && cfm <= 210) {

            modelNo = "CRD-20";
        } else if (cfm >= 211 && cfm <= 430) {
            modelNo = "CRD-35";
        } else if (cfm >= 431 && cfm <= 700) {
            modelNo = "CRD-60";
        } else if (cfm >= 701 && cfm <= 1100) {
            modelNo = "CRD-90";
        } else if (cfm >= 1101 && cfm <= 1350) {
            modelNo = "CRD-125";
        } else if (cfm >= 1351 && cfm <= 1900) {
            modelNo = "CRD-150";
        } else if (cfm >= 1901 && cfm <= 2200) {
            modelNo = "CRD-200";
        } else if (cfm >= 2201 && cfm <= 2800) {
            modelNo = "CRD-250";
        } else if (cfm >= 2801 && cfm <= 3300) {
            modelNo = "CRD-300";
        } else if (cfm >= 3301 && cfm <= 3800) {
            modelNo = "CRD-350";
        } else if (cfm >= 3801 && cfm <= 4100) {
            modelNo = "CRD-400";
        } else if (cfm >= 4101 && cfm <= 4900) {
            modelNo = "CRD-450";
        } else if (cfm >= 4901 && cfm <= 5500) {
            modelNo = "CRD-500";
        } else if (cfm >= 5501 && cfm <= 6500) {
            modelNo = "CRD-620";
        } else if (cfm >= 6501 && cfm <= 8000) {
            modelNo = "CRD-750";
        } else if (cfm >= 8001 && cfm <= 10500) {
            modelNo = "CRD-850";
        } else if (cfm >= 10501 && cfm <= 13000) {
            modelNo = "CRD-1200";
        } else if (cfm >= 13001 && cfm <= 17000) {
            modelNo = "CRD-1500";
        } else if (cfm >= 17001 && cfm <= 21000) {
            modelNo = "CRD-1900";
        }

        return modelNo;
    }


    private void dlRemove(LinearLayout layout, EditText eWidth, EditText eHeight, EditText eDop) {

        layout.setVisibility(View.GONE);


        eWidth.getText().clear();
        eHeight.getText().clear();
        eDop.getText().clear();


        eWidth.setText("0");
        eHeight.setText("0");
        eDop.setText("0");


    }

    //fixed opning remove
    private void fixedRemove(LinearLayout layout, EditText eLength, EditText eWidth, EditText eDepth, TextView tv) {

        layout.setVisibility(View.GONE);


        eLength.getText().clear();
        eWidth.getText().clear();
        eDepth.getText().clear();
        tv.setText("0.0");

        eLength.setText("0");
        eWidth.setText("0");
        eDepth.setText("0");
        tv.setText("0");


    }


    //all static field
    private void itemeRemove(LinearLayout layout, EditText eLength, EditText eWidth, EditText eHeight, TextView tv) {

        layout.setVisibility(View.GONE);


        eLength.getText().clear();
        eWidth.getText().clear();
        eHeight.getText().clear();
        //tv.setText("0");
        txtTvolume.setText("0");
        inF2.setText("0");

        eLength.setText("0");
        eWidth.setText("0");
        eHeight.setText("0");
        tv.setText("0");
        double v0 = Double.parseDouble(bm_v0.getText().toString());
        double v1 = Double.parseDouble(bm_v1.getText().toString());
        double v2 = Double.parseDouble(bm_v2.getText().toString());
        double v3 = Double.parseDouble(bm_v3.getText().toString());
        double v4 = Double.parseDouble(bm_v4.getText().toString());
        double v5 = Double.parseDouble(bm_v5.getText().toString());
        double v6 = Double.parseDouble(bm_v6.getText().toString());
        double v7 = Double.parseDouble(bm_v7.getText().toString());
        double v8 = Double.parseDouble(bm_v8.getText().toString());
        double v9 = Double.parseDouble(bm_v9.getText().toString());

        double tvalum = v0 + v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8 + v9;

        double l0 = Double.parseDouble(bm_l0.getText().toString());
        double l1 = Double.parseDouble(bm_l1.getText().toString());
        double l2 = Double.parseDouble(bm_l2.getText().toString());
        double l3 = Double.parseDouble(bm_l3.getText().toString());
        double l4 = Double.parseDouble(bm_l4.getText().toString());
        double l5 = Double.parseDouble(bm_l5.getText().toString());
        double l6 = Double.parseDouble(bm_l6.getText().toString());
        double l7 = Double.parseDouble(bm_l7.getText().toString());
        double l8 = Double.parseDouble(bm_l8.getText().toString());
        double l9 = Double.parseDouble(bm_l9.getText().toString());

        double tLength = l0 + l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9;

        double w0 = Double.parseDouble(bm_w0.getText().toString());
        double w1 = Double.parseDouble(bm_w1.getText().toString());
        double w2 = Double.parseDouble(bm_w2.getText().toString());
        double w3 = Double.parseDouble(bm_w3.getText().toString());
        double w4 = Double.parseDouble(bm_w4.getText().toString());
        double w5 = Double.parseDouble(bm_w5.getText().toString());
        double w6 = Double.parseDouble(bm_w6.getText().toString());
        double w7 = Double.parseDouble(bm_w7.getText().toString());
        double w8 = Double.parseDouble(bm_w8.getText().toString());
        double w9 = Double.parseDouble(bm_w9.getText().toString());

        double tWidth = w0 + w1 + w2 + w3 + w4 + w5 + w6 + w7 + w8 + w9;


        double h0 = Double.parseDouble(bm_h0.getText().toString());
        double h1 = Double.parseDouble(bm_h1.getText().toString());
        double h2 = Double.parseDouble(bm_h2.getText().toString());
        double h3 = Double.parseDouble(bm_h3.getText().toString());
        double h4 = Double.parseDouble(bm_h4.getText().toString());
        double h5 = Double.parseDouble(bm_h5.getText().toString());
        double h6 = Double.parseDouble(bm_h6.getText().toString());
        double h7 = Double.parseDouble(bm_h7.getText().toString());
        double h8 = Double.parseDouble(bm_h8.getText().toString());
        double h9 = Double.parseDouble(bm_h9.getText().toString());

        double tHight = h0 + h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8 + h9;
        TOTAL_VOLUME += tvalum;
        txtTlength.setText(String.valueOf(tLength));
        txtTwidth.setText(String.valueOf(tWidth));
        txtThight.setText(String.valueOf(tHight));
        txtTvolume.setText(String.valueOf(tvalum));
        fTwoValue(txtTvolume, inF2);


    }

    private void fixedvolume(EditText eLength, EditText eWidth, EditText depth, TextView tValume) {

        if (eLength.getText().toString().matches("")) {
            eLength.setText("0");
        }
        if (eWidth.getText().toString().matches("")) {
            eWidth.setText("0");
        }
        if (depth.getText().toString().matches("")) {
            depth.setText("0");
        }
        if (tValume.getText().toString().matches("")) {
            tValume.setText("0");
        }
        double tlength = Double.parseDouble(eLength.getText().toString());
        double twidth = Double.parseDouble(eWidth.getText().toString());
        double theight = Double.parseDouble(depth.getText().toString());
        double mul = tlength * twidth * theight;
        tValume.setText(String.format("%.2f", mul));

    }

    private void dmTvolume(EditText eLength, EditText eWidth, EditText eHeight, TextView tValume) {
        double tlength,twidth,theight;

            try {
                tlength = new Double(eLength.getText().toString());


            } catch (NumberFormatException e) {
                tlength = 0; // your default value
            }

        try {

            twidth = new Double(eWidth.getText().toString());


        } catch (NumberFormatException e) {
            twidth = 0; // your default value
        }

        try {

            theight = new Double(eHeight.getText().toString());

        } catch (NumberFormatException e) {
            theight = 0; // your default value
        }

        /*tlength = Double.parseDouble(eLength.getText().toString());
        twidth = Double.parseDouble(eWidth.getText().toString());
        theight = Double.parseDouble(eHeight.getText().toString());*/
        double mul = tlength * twidth * theight;
        tValume.setText(String.format("%.2f", mul));
        double v0 = Double.parseDouble(bm_v0.getText().toString());
        double v1 = Double.parseDouble(bm_v1.getText().toString());
        double v2 = Double.parseDouble(bm_v2.getText().toString());
        double v3 = Double.parseDouble(bm_v3.getText().toString());
        double v4 = Double.parseDouble(bm_v4.getText().toString());
        double v5 = Double.parseDouble(bm_v5.getText().toString());
        double v6 = Double.parseDouble(bm_v6.getText().toString());
        double v7 = Double.parseDouble(bm_v7.getText().toString());
        double v8 = Double.parseDouble(bm_v8.getText().toString());
        double v9 = Double.parseDouble(bm_v9.getText().toString());

        double tvalum = v0 + v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8 + v9;



        // start the length
        double l0,l1,l2,l3,l4,l5,l6,l7,l8,l9;

        try {
            l0 = new Double(bm_l0.getText().toString());


        } catch (NumberFormatException e) {
            l0 = 0; // your default value
        }

        try {
            l1 = new Double(bm_l1.getText().toString());


        } catch (NumberFormatException e) {
            l1 = 0; // your default value
        }

        try {
            l2 = new Double(bm_l2.getText().toString());


        } catch (NumberFormatException e) {
            l2 = 0; // your default value
        }

        try {
            l3 = new Double(bm_l3.getText().toString());


        } catch (NumberFormatException e) {
            l3 = 0; // your default value
        }

        try {
            l4 = new Double(bm_l4.getText().toString());


        } catch (NumberFormatException e) {
            l4 = 0; // your default value
        }

        try {
            l5 = new Double(bm_l5.getText().toString());


        } catch (NumberFormatException e) {
            l5 = 0; // your default value
        }

        try {
            l6 = new Double(bm_l6.getText().toString());


        } catch (NumberFormatException e) {
            l6 = 0; // your default value
        }

        try {
            l7 = new Double(bm_l7.getText().toString());


        } catch (NumberFormatException e) {
            l7 = 0; // your default value
        }

        try {
            l8 = new Double(bm_l8.getText().toString());


        } catch (NumberFormatException e) {
            l8 = 0; // your default value
        }

        try {
            l9 = new Double(bm_l9.getText().toString());

        } catch (NumberFormatException e) {
            l9 = 0; // your default value
        }




        /*double l0 = Double.parseDouble(bm_l0.getText().toString());
        double l1 = Double.parseDouble(bm_l1.getText().toString());
        double l2 = Double.parseDouble(bm_l2.getText().toString());
        double l3 = Double.parseDouble(bm_l3.getText().toString());
        double l4 = Double.parseDouble(bm_l4.getText().toString());
        double l5 = Double.parseDouble(bm_l5.getText().toString());
        double l6 = Double.parseDouble(bm_l6.getText().toString());
        double l7 = Double.parseDouble(bm_l7.getText().toString());
        double l8 = Double.parseDouble(bm_l8.getText().toString());
        double l9 = Double.parseDouble(bm_l9.getText().toString());*/

        double tLength = l0 + l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9;

// start the width
        double w0,w1,w2,w3,w4,w5,w6,w7,w8,w9;

        try {
            w0 = new Double(bm_w0.getText().toString());


        } catch (NumberFormatException e) {
            w0 = 0; // your default value
        }

        try {
            w1 = new Double(bm_w1.getText().toString());


        } catch (NumberFormatException e) {
            w1 = 0; // your default value
        }

        try {
            w2 = new Double(bm_w2.getText().toString());


        } catch (NumberFormatException e) {
            w2 = 0; // your default value
        }

        try {
            w3 = new Double(bm_w3.getText().toString());


        } catch (NumberFormatException e) {
            w3 = 0; // your default value
        }

        try {
            w4 = new Double(bm_w4.getText().toString());


        } catch (NumberFormatException e) {
            w4 = 0; // your default value
        }

        try {
            w5 = new Double(bm_w5.getText().toString());


        } catch (NumberFormatException e) {
            w5 = 0; // your default value
        }

        try {
            w6 = new Double(bm_w6.getText().toString());


        } catch (NumberFormatException e) {
            w6 = 0; // your default value
        }

        try {
            w7 = new Double(bm_w7.getText().toString());


        } catch (NumberFormatException e) {
            w7 = 0; // your default value
        }

        try {
            w8 = new Double(bm_w8.getText().toString());


        } catch (NumberFormatException e) {
            w8 = 0; // your default value
        }

        try {
            w9 = new Double(bm_w9.getText().toString());

        } catch (NumberFormatException e) {
            w9 = 0; // your default value
        }


       /* w0 = Double.parseDouble(bm_w0.getText().toString());
        double w1 = Double.parseDouble(bm_w1.getText().toString());
        double w2 = Double.parseDouble(bm_w2.getText().toString());
        double w3 = Double.parseDouble(bm_w3.getText().toString());
        double w4 = Double.parseDouble(bm_w4.getText().toString());
        double w5 = Double.parseDouble(bm_w5.getText().toString());
        double w6 = Double.parseDouble(bm_w6.getText().toString());
        double w7 = Double.parseDouble(bm_w7.getText().toString());
        double w8 = Double.parseDouble(bm_w8.getText().toString());
        double w9 = Double.parseDouble(bm_w9.getText().toString());*/

        double tWidth = w0 + w1 + w2 + w3 + w4 + w5 + w6 + w7 + w8 + w9;


        // start the height
        double h0,h1,h2,h3,h4,h5,h6,h7,h8,h9;

        try {
            h0 = new Double(bm_h0.getText().toString());


        } catch (NumberFormatException e) {
            h0 = 0; // your default value
        }

        try {
            h1 = new Double(bm_h1.getText().toString());


        } catch (NumberFormatException e) {
            h1 = 0; // your default value
        }

        try {
            h2 = new Double(bm_h2.getText().toString());


        } catch (NumberFormatException e) {
            h2 = 0; // your default value
        }

        try {
            h3 = new Double(bm_h3.getText().toString());


        } catch (NumberFormatException e) {
            h3 = 0; // your default value
        }

        try {
            h4 = new Double(bm_h4.getText().toString());


        } catch (NumberFormatException e) {
            h4 = 0; // your default value
        }

        try {
            h5 = new Double(bm_h5.getText().toString());


        } catch (NumberFormatException e) {
            h5 = 0; // your default value
        }

        try {
            h6 = new Double(bm_h6.getText().toString());


        } catch (NumberFormatException e) {
            h6 = 0; // your default value
        }

        try {
            h7 = new Double(bm_h7.getText().toString());


        } catch (NumberFormatException e) {
            h7 = 0; // your default value
        }

        try {
            h8 = new Double(bm_h8.getText().toString());


        } catch (NumberFormatException e) {
            h8 = 0; // your default value
        }

        try {
            h9 = new Double(bm_h9.getText().toString());

        } catch (NumberFormatException e) {
            h9 = 0; // your default value
        }





       /* double h0 = Double.parseDouble(bm_h0.getText().toString());
        double h1 = Double.parseDouble(bm_h1.getText().toString());
        double h2 = Double.parseDouble(bm_h2.getText().toString());
        double h3 = Double.parseDouble(bm_h3.getText().toString());
        double h4 = Double.parseDouble(bm_h4.getText().toString());
        double h5 = Double.parseDouble(bm_h5.getText().toString());
        double h6 = Double.parseDouble(bm_h6.getText().toString());
        double h7 = Double.parseDouble(bm_h7.getText().toString());
        double h8 = Double.parseDouble(bm_h8.getText().toString());
        double h9 = Double.parseDouble(bm_h9.getText().toString());*/

        double tHight = h0 + h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8 + h9;

        TOTAL_VOLUME += tvalum;
        txtTlength.setText(String.format("%.2f", tLength));
        txtTwidth.setText(String.format("%.2f", tWidth));
        txtThight.setText(String.format("%.2f", tHight));
        txtTvolume.setText(String.format("%.2f", tvalum));
        fTwoValue(txtTvolume, inF2);
    }

    //fixed opning get volume
    private void fodTvolume(EditText eLength, EditText eWidth, EditText eHeight, TextView tValume) {

        double tlength,twidth,theight;

        try {
            tlength = new Double(eLength.getText().toString());
        } catch (NumberFormatException e) {
            tlength = 0; // your default value
        }

        try {
            twidth = new Double(eWidth.getText().toString());
        } catch (NumberFormatException e) {
            twidth = 0; // your default value
        }

        try {
            theight = new Double(eHeight.getText().toString());
        } catch (NumberFormatException e) {
            theight = 0; // your default value
        }

       /* double tlength = Double.parseDouble(eLength.getText().toString());
        double twidth = Double.parseDouble(eWidth.getText().toString());
        double theight = Double.parseDouble(eHeight.getText().toString());*/
        double mul = tlength * twidth * theight;
        tValume.setText(String.format("%.2f", mul));

    }


    private void dmData() {
        bm_l0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w0.getText().toString().isEmpty()
                        && !bm_h0.getText().toString().isEmpty()) {
                    dmTvolume(bm_l0, bm_w0, bm_h0, bm_v0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l0.getText().toString().isEmpty()
                        && !bm_h0.getText().toString().isEmpty()) {
                    dmTvolume(bm_l0, bm_w0, bm_h0, bm_v0);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_h0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w0.getText().toString().isEmpty()
                        && !bm_l0.getText().toString().isEmpty()) {
                    dmTvolume(bm_l0, bm_w0, bm_h0, bm_v0);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //

        bm_l1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w1.getText().toString().isEmpty()
                        && !bm_h1.getText().toString().isEmpty()) {
                    dmTvolume(bm_l1, bm_w1, bm_h1, bm_v1);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l1.getText().toString().isEmpty()
                        && !bm_h1.getText().toString().isEmpty()) {
                    dmTvolume(bm_l1, bm_w1, bm_h1, bm_v1);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_h1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w1.getText().toString().isEmpty()
                        && !bm_l1.getText().toString().isEmpty()) {
                    dmTvolume(bm_l1, bm_w1, bm_h1, bm_v1);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ///.....

        bm_l2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w2.getText().toString().isEmpty()
                        && !bm_h2.getText().toString().isEmpty()) {
                    dmTvolume(bm_l2, bm_w2, bm_h2, bm_v2);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l2.getText().toString().isEmpty()
                        && !bm_h2.getText().toString().isEmpty()) {
                    dmTvolume(bm_l2, bm_w2, bm_h2, bm_v2);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bm_h2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w2.getText().toString().isEmpty()
                        && !bm_l2.getText().toString().isEmpty()) {
                    dmTvolume(bm_l2, bm_w2, bm_h2, bm_v2);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //

        bm_l3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w3.getText().toString().isEmpty()
                        && !bm_h3.getText().toString().isEmpty()) {
                    dmTvolume(bm_l3, bm_w3, bm_h3, bm_v3);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l3.getText().toString().isEmpty()
                        && !bm_h3.getText().toString().isEmpty()) {
                    dmTvolume(bm_l3, bm_w3, bm_h3, bm_v3);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bm_h3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w3.getText().toString().isEmpty()
                        && !bm_l3.getText().toString().isEmpty()) {
                    dmTvolume(bm_l3, bm_w3, bm_h3, bm_v3);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //

        bm_l4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w4.getText().toString().isEmpty()
                        && !bm_h4.getText().toString().isEmpty()) {
                    dmTvolume(bm_l4, bm_w4, bm_h4, bm_v4);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l4.getText().toString().isEmpty()
                        && !bm_h4.getText().toString().isEmpty()) {
                    dmTvolume(bm_l4, bm_w4, bm_h4, bm_v4);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bm_h4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w4.getText().toString().isEmpty()
                        && !bm_l4.getText().toString().isEmpty()) {
                    dmTvolume(bm_l4, bm_w4, bm_h4, bm_v4);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_l5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w5.getText().toString().isEmpty()
                        && !bm_h5.getText().toString().isEmpty()) {
                    dmTvolume(bm_l5, bm_w5, bm_h5, bm_v5);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l5.getText().toString().isEmpty()
                        && !bm_h5.getText().toString().isEmpty()) {
                    dmTvolume(bm_l5, bm_w5, bm_h5, bm_v5);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bm_h5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w5.getText().toString().isEmpty()
                        && !bm_l5.getText().toString().isEmpty()) {
                    dmTvolume(bm_l5, bm_w5, bm_h5, bm_v5);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //
        bm_l6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w6.getText().toString().isEmpty()
                        && !bm_h6.getText().toString().isEmpty()) {
                    dmTvolume(bm_l6, bm_w6, bm_h6, bm_v6);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l6.getText().toString().isEmpty()
                        && !bm_h6.getText().toString().isEmpty()) {
                    dmTvolume(bm_l6, bm_w6, bm_h6, bm_v6);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bm_h6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w6.getText().toString().isEmpty()
                        && !bm_l6.getText().toString().isEmpty()) {
                    dmTvolume(bm_l6, bm_w6, bm_h6, bm_v6);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//....
        bm_l7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w7.getText().toString().isEmpty()
                        && !bm_h7.getText().toString().isEmpty()) {
                    dmTvolume(bm_l7, bm_w7, bm_h7, bm_v7);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l7.getText().toString().isEmpty()
                        && !bm_h7.getText().toString().isEmpty()) {
                    dmTvolume(bm_l7, bm_w7, bm_h7, bm_v7);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bm_h7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w7.getText().toString().isEmpty()
                        && !bm_l7.getText().toString().isEmpty()) {
                    dmTvolume(bm_l7, bm_w7, bm_h7, bm_v7);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //....
        bm_l8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w8.getText().toString().isEmpty()
                        && !bm_h8.getText().toString().isEmpty()) {
                    dmTvolume(bm_l8, bm_w8, bm_h8, bm_v8);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l8.getText().toString().isEmpty()
                        && !bm_h8.getText().toString().isEmpty()) {
                    dmTvolume(bm_l8, bm_w8, bm_h8, bm_v8);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bm_h8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w8.getText().toString().isEmpty()
                        && !bm_l8.getText().toString().isEmpty()) {
                    dmTvolume(bm_l8, bm_w8, bm_h8, bm_v8);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //....
        bm_l9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w9.getText().toString().isEmpty()
                        && !bm_h9.getText().toString().isEmpty()) {
                    dmTvolume(bm_l9, bm_w9, bm_h9, bm_v9);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bm_w9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_l9.getText().toString().isEmpty()
                        && !bm_h9.getText().toString().isEmpty()) {
                    dmTvolume(bm_l9, bm_w9, bm_h9, bm_v9);
                    //fTwoValue(txtTvolume,inF2);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bm_h9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !bm_w9.getText().toString().isEmpty()
                        && !bm_l9.getText().toString().isEmpty()) {
                    dmTvolume(bm_l9, bm_w9, bm_h9, bm_v9);
                    //fTwoValue(txtTvolume,inF2);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //fixed opning TextChange......
        fod_l0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w0.getText().toString().isEmpty()
                        && !fod_d0.getText().toString().isEmpty()) {
                    fodTvolume(fod_l0, fod_w0, fod_d0, fod_v0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fod_w0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_l0.getText().toString().isEmpty()
                        && !fod_d0.getText().toString().isEmpty()) {
                    fodTvolume(fod_l0, fod_w0, fod_d0, fod_v0);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fod_d0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w0.getText().toString().isEmpty()
                        && !fod_l0.getText().toString().isEmpty()) {
                    fodTvolume(fod_l0, fod_w0, fod_d0, fod_v0);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //

        fod_l1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w1.getText().toString().isEmpty()
                        && !fod_d1.getText().toString().isEmpty()) {
                    fodTvolume(fod_l1, fod_w1, fod_d1, fod_v1);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fod_w1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_l1.getText().toString().isEmpty()
                        && !fod_d1.getText().toString().isEmpty()) {
                    fodTvolume(fod_l1, fod_w1, fod_d1, fod_v1);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fod_d1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w1.getText().toString().isEmpty()
                        && !fod_l1.getText().toString().isEmpty()) {
                    fodTvolume(fod_l1, fod_w1, fod_d1, fod_v1);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ///.....

        fod_l2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w2.getText().toString().isEmpty()
                        && !fod_d2.getText().toString().isEmpty()) {
                    fodTvolume(fod_l2, fod_w2, fod_d2, fod_v2);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fod_w2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_l2.getText().toString().isEmpty()
                        && !fod_d2.getText().toString().isEmpty()) {
                    fodTvolume(fod_l2, fod_w2, fod_d2, fod_v2);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        fod_d2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w2.getText().toString().isEmpty()
                        && !fod_l2.getText().toString().isEmpty()) {
                    fodTvolume(fod_l2, fod_w2, fod_d2, fod_v2);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //

        fod_l3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w3.getText().toString().isEmpty()
                        && !fod_d3.getText().toString().isEmpty()) {
                    fodTvolume(fod_l3, fod_w3, fod_d3, fod_v3);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fod_w3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_l3.getText().toString().isEmpty()
                        && !fod_d3.getText().toString().isEmpty()) {
                    fodTvolume(fod_l3, fod_w3, fod_d3, fod_v3);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        fod_d3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w3.getText().toString().isEmpty()
                        && !fod_l3.getText().toString().isEmpty()) {
                    fodTvolume(fod_l3, fod_w3, fod_d3, fod_v3);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //

        fod_l4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w4.getText().toString().isEmpty()
                        && !fod_d4.getText().toString().isEmpty()) {
                    fodTvolume(fod_l4, fod_w4, fod_d4, fod_v4);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fod_w4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_l4.getText().toString().isEmpty()
                        && !fod_d4.getText().toString().isEmpty()) {
                    fodTvolume(fod_l4, fod_w4, fod_d4, fod_v4);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        fod_d4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w4.getText().toString().isEmpty()
                        && !fod_l4.getText().toString().isEmpty()) {
                    fodTvolume(fod_l4, fod_w4, fod_d4, fod_v4);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fod_l5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w5.getText().toString().isEmpty()
                        && !fod_d5.getText().toString().isEmpty()) {
                    fodTvolume(fod_l5, fod_w5, fod_d5, fod_v5);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fod_w5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_l5.getText().toString().isEmpty()
                        && !fod_d5.getText().toString().isEmpty()) {
                    fodTvolume(fod_l5, fod_w5, fod_d5, fod_v5);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        fod_d5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !fod_w5.getText().toString().isEmpty()
                        && !fod_l5.getText().toString().isEmpty()) {
                    fodTvolume(fod_l5, fod_w5, fod_d5, fod_v5);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private void removeClick() {


        bm_btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt0, bm_l0, bm_w0, bm_h0, bm_v0);
            }
        });


        bm_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt1, bm_l1, bm_w1, bm_h1, bm_v1);

            }
        });

        bm_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt2, bm_l2, bm_w2, bm_h2, bm_v2);

            }
        });

        bm_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt3, bm_l3, bm_w3, bm_h3, bm_v3);

            }
        });

        bm_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt4, bm_l4, bm_w4, bm_h4, bm_v4);

            }
        });

      /*  bm_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt4,bm_l4,bm_w4,bm_h4);

            }
        });*/

        bm_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt5, bm_l5, bm_w5, bm_h5, bm_v5);
            }
        });

        bm_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt6, bm_l6, bm_w6, bm_h6, bm_v6);
            }
        });

        bm_btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt7, bm_l7, bm_w7, bm_h7, bm_v7);
            }
        });

        bm_btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt8, bm_l8, bm_w8, bm_h8, bm_v8);

            }
        });

        bm_btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemeRemove(dmlyt9, bm_l9, bm_w9, bm_h9, bm_v9);
                counter = 0;

            }
        });


        //door load remove button

        dl_btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlRemove(dllyt0, dlw0, dlh0, dldop0);
            }
        });


        dl_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlRemove(dllyt1, dlw1, dlh1, dldop1);

            }
        });

        dl_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlRemove(dllyt2, dlw2, dlh2, dldop2);

            }
        });

        dl_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlRemove(dllyt3, dlw2, dlh2, dldop2);

            }
        });

        dl_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlRemove(dllyt4, dlw4, dlh4, dldop4);

            }
        });


        dl_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlRemove(dllyt5, dlw5, dlh5, dldop5);
                dlcounter = 0;
            }
        });
        //fixed opning
        fod_btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fixedRemove(fod_lyt0, fod_l0, fod_w0, fod_d0, fod_v0);
            }
        });


        fod_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fixedRemove(fod_lyt1, fod_l1, fod_w1, fod_d1, fod_v1);

            }
        });

        fod_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fixedRemove(fod_lyt2, fod_l2, fod_w2, fod_d2, fod_v2);

            }
        });

        fod_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fixedRemove(fod_lyt3, fod_l3, fod_w3, fod_d3, fod_v3);

            }
        });

        fod_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fixedRemove(fod_lyt4, fod_l4, fod_w4, fod_d4, fod_v4);

            }
        });


        fod_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fixedRemove(fod_lyt5, fod_l5, fod_w5, fod_d5, fod_v5);
                dlcounter = 0;
            }
        });
    }

    private void setZero() {

        bm_l0.setText("0");
        bm_w0.setText("0");
        bm_h0.setText("0");
        bm_v0.setText("0");

        bm_l1.setText("0");
        bm_w1.setText("0");
        bm_h1.setText("0");
        bm_v1.setText("0");

        bm_l2.setText("0");
        bm_w2.setText("0");
        bm_h2.setText("0");
        bm_v2.setText("0");

        bm_l3.setText("0");
        bm_w3.setText("0");
        bm_h3.setText("0");
        bm_v3.setText("0");

        bm_l4.setText("0");
        bm_w4.setText("0");
        bm_h4.setText("0");
        bm_v4.setText("0");

        bm_l5.setText("0");
        bm_w5.setText("0");
        bm_h5.setText("0");
        bm_v5.setText("0");

        bm_l6.setText("0");
        bm_w6.setText("0");
        bm_h6.setText("0");
        bm_v6.setText("0");

        bm_l7.setText("0");
        bm_w7.setText("0");
        bm_h7.setText("0");
        bm_v7.setText("0");

        bm_l8.setText("0");
        bm_w8.setText("0");
        bm_h8.setText("0");
        bm_v8.setText("0");

        bm_l9.setText("0");
        bm_w9.setText("0");
        bm_h9.setText("0");
        bm_v9.setText("0");

        txtTlength.setText("0");
        txtTwidth.setText("0");
        txtThight.setText("0");
        txtTvolume.setText("0");
        //door load

        dlw0.setText("0");
        dlh0.setText("0");
        dldop0.setText("0");


        dlw1.setText("0");
        dlh1.setText("0");
        dldop1.setText("0");

        dlw2.setText("0");
        dlh2.setText("0");
        dldop2.setText("0");

        dlw3.setText("0");
        dlh3.setText("0");
        dldop3.setText("0");

        dlw4.setText("0");
        dlh4.setText("0");
        dldop4.setText("0");

        dlw5.setText("0");
        dlh5.setText("0");
        dldop5.setText("0");

        //fixed opning
        fod_l0.setText("0");
        fod_w0.setText("0");
        fod_d0.setText("0");
        fod_v0.setText("0");

        fod_l1.setText("0");
        fod_w1.setText("0");
        fod_d1.setText("0");
        fod_v1.setText("0");

        fod_l2.setText("0");
        fod_w2.setText("0");
        fod_d2.setText("0");
        fod_v2.setText("0");

        fod_l3.setText("0");
        fod_w3.setText("0");
        fod_d3.setText("0");
        fod_v3.setText("0");

        fod_l4.setText("0");
        fod_w4.setText("0");
        fod_d4.setText("0");
        fod_v4.setText("0");

        fod_l5.setText("0");
        fod_w5.setText("0");
        fod_d5.setText("0");
        fod_v5.setText("0");

    }
    //end static field

    //redio button

    private void redioChange() {
        rgImpSi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioImp:
                        Toast.makeText(InputActivity.this, "IMP", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radioSi:
                        Toast.makeText(InputActivity.this, "SI", Toast.LENGTH_SHORT).show();
                        break;

                }


            }
        });
    }


    //no of fixed opning

    private void noOfFixedOpning() {

        double fodLengtht0, fodLengtht1, fodLengtht2, fodLengtht3, fodLengtht4, fodLengtht5;


        try {
            fodLengtht0 = new Double(fod_l0.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht0 = 0; // your default value
        }

        try {
            fodLengtht1 = new Double(fod_l1.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht1 = 0; // your default value
        }

        try {
            fodLengtht2 = new Double(fod_l2.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht2 = 0; // your default value
        }
        try {
            fodLengtht3 = new Double(fod_l3.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht3 = 0; // your default value
        }
        try {
            fodLengtht4 = new Double(fod_l4.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht4 = 0; // your default value
        }
        try {
            fodLengtht5 = new Double(fod_l5.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht5 = 0; // your default value
        }


        fixedTotalHeight = fodLengtht0 + fodLengtht1 + fodLengtht2 + fodLengtht3 + fodLengtht4 + fodLengtht5;


        double fodWidth0, fodWidth1, fodWidth2, fodWidth3, fodWidth4, fodWidth5;
        try {
            fodWidth0 = new Double(fod_w0.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth0 = 0; // your default value
        }

        try {
            fodWidth1 = new Double(fod_w1.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth1 = 0; // your default value
        }

        try {
            fodWidth2 = new Double(fod_w2.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth2 = 0; // your default value
        }
        try {
            fodWidth3 = new Double(fod_w3.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth3 = 0; // your default value
        }
        try {
            fodWidth4 = new Double(fod_w4.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth4 = 0; // your default value
        }
        try {
            fodWidth5 = new Double(fod_w5.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth5 = 0; // your default value
        }
        fixedTotalWidth = fodWidth0 + fodWidth1 + fodWidth2 + fodWidth3 + fodWidth4 + fodWidth5;

    }
    //end fixed opning


    //no of door
    private void noOfDoor() {

        double dlWidth0, dlWidth1, dlWidth2, dlWidth3, dlWidth4, dlWidth5;

        try {
            dlWidth0 = new Double(dlw0.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth0 = 0; // your default value
        }

        try {
            dlWidth1 = new Double(dlw1.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth1 = 0; // your default value
        }

        try {
            dlWidth2 = new Double(dlw2.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth2 = 0; // your default value
        }
        try {
            dlWidth3 = new Double(dlw3.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth3 = 0; // your default value
        }
        try {
            dlWidth4 = new Double(dlw4.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth4 = 0; // your default value
        }
        try {
            dlWidth5 = new Double(dlw5.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth5 = 0; // your default value
        }


        doorTotalWidth = dlWidth0 + dlWidth1 + dlWidth2 + dlWidth3 + dlWidth4 + dlWidth5;


        double dlHeight0, dlHeight1, dlHeight2, dlHeight3, dlHeight4, dlHeight5;

        try {
            dlHeight0 = new Double(dlh0.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight0 = 0; // your default value
        }

        try {
            dlHeight1 = new Double(dlh1.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight1 = 0; // your default value
        }

        try {
            dlHeight2 = new Double(dlh2.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight2 = 0; // your default value
        }
        try {
            dlHeight3 = new Double(dlh3.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight3 = 0; // your default value
        }
        try {
            dlHeight4 = new Double(dlh4.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight4 = 0; // your default value
        }
        try {
            dlHeight5 = new Double(dlh5.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight5 = 0; // your default value
        }
        doorTotalHeight = dlHeight0 + dlHeight1 + dlHeight2 + dlHeight3 + dlHeight4 + dlHeight5;

    }



    //end no of door




    private double doorLoadCal() {

        double outDeltagr = Double.parseDouble(outDoordeltaGr.getText().toString());
        double outF1 = Double.parseDouble(outDoorf1.getText().toString());

        double dlWidth0,dlHeight0,dlDooroh0;

        try {
            dlWidth0 = new Double(dlw0.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth0 = 0; // your default value
        }

        try {
            dlHeight0 = new Double(dlh0.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight0 = 0; // your default value
        }

        try {
            dlDooroh0 = new Double(dldop0.getText().toString());
        } catch (NumberFormatException e) {
            dlDooroh0 = 0; // your default value
        }


       /* double dlWidth0 = Double.parseDouble(dlw0.getText().toString());
        double dlHeight0 = Double.parseDouble(dlh0.getText().toString());
        double dlDooroh0 = Double.parseDouble(dldop0.getText().toString());*/
        double doorLoadValue0 = (((dlWidth0 * dlHeight0) * outDeltagr * outF1 * dlDooroh0) / 7);



        double dlWidth1,dlHeight1,dlDooroh1;

        try {
            dlWidth1 = new Double(dlw1.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth1 = 0; // your default value
        }

        try {
            dlHeight1 = new Double(dlh1.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight1 = 0; // your default value
        }

        try {
            dlDooroh1 = new Double(dldop1.getText().toString());
        } catch (NumberFormatException e) {
            dlDooroh1 = 0; // your default value
        }



       /* double dlWidth1 = Double.parseDouble(dlw1.getText().toString());
        double dlHeight1 = Double.parseDouble(dlh1.getText().toString());
        double dlDooroh1 = Double.parseDouble(dldop1.getText().toString());*/
        double doorLoadValue1 = (((dlWidth1 * dlHeight1) * outDeltagr * outF1 * dlDooroh1) / 7);


        double dlWidth2,dlHeight2,dlDooroh2;

        try {
            dlWidth2 = new Double(dlw2.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth2 = 0; // your default value
        }

        try {
            dlHeight2 = new Double(dlh2.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight2 = 0; // your default value
        }

        try {
            dlDooroh2 = new Double(dldop2.getText().toString());
        } catch (NumberFormatException e) {
            dlDooroh2 = 0; // your default value
        }
       /* double dlWidth2 = Double.parseDouble(dlw2.getText().toString());
        double dlHeight2 = Double.parseDouble(dlh2.getText().toString());
        double dlDooroh2 = Double.parseDouble(dldop2.getText().toString());*/
        double doorLoadValue2 = (((dlWidth2 * dlHeight2) * outDeltagr * outF1 * dlDooroh2) / 7);


        double dlWidth3,dlHeight3,dlDooroh3;

        try {
            dlWidth3 = new Double(dlw3.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth3 = 0; // your default value
        }

        try {
            dlHeight3 = new Double(dlh3.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight3 = 0; // your default value
        }

        try {
            dlDooroh3 = new Double(dldop3.getText().toString());
        } catch (NumberFormatException e) {
            dlDooroh3 = 0; // your default value
        }
       /* double dlWidth3 = Double.parseDouble(dlw3.getText().toString());
        double dlHeight3 = Double.parseDouble(dlh3.getText().toString());
        double dlDooroh3 = Double.parseDouble(dldop3.getText().toString());*/
        double doorLoadValue3 = (((dlWidth3 * dlHeight3) * outDeltagr * outF1 * dlDooroh3) / 7);



        double dlWidth4,dlHeight4,dlDooroh4;

        try {
            dlWidth4 = new Double(dlw4.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth4 = 0; // your default value
        }

        try {
            dlHeight4 = new Double(dlh4.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight4 = 0; // your default value
        }

        try {
            dlDooroh4 = new Double(dldop4.getText().toString());
        } catch (NumberFormatException e) {
            dlDooroh4 = 0; // your default value
        }
        /*double dlWidth4 = Double.parseDouble(dlw4.getText().toString());
        double dlHeight4 = Double.parseDouble(dlh4.getText().toString());
        double dlDooroh4 = Double.parseDouble(dldop4.getText().toString());*/
        double doorLoadValue4 = (((dlWidth4 * dlHeight4) * outDeltagr * outF1 * dlDooroh4) / 7);


        double dlWidth5,dlHeight5,dlDooroh5;

        try {
            dlWidth5 = new Double(dlw5.getText().toString());
        } catch (NumberFormatException e) {
            dlWidth5 = 0; // your default value
        }

        try {
            dlHeight5 = new Double(dlh5.getText().toString());
        } catch (NumberFormatException e) {
            dlHeight5 = 0; // your default value
        }

        try {
            dlDooroh5 = new Double(dldop5.getText().toString());
        } catch (NumberFormatException e) {
            dlDooroh5 = 0; // your default value
        }
        /*double dlWidth5 = Double.parseDouble(dlw5.getText().toString());
        double dlHeight5 = Double.parseDouble(dlh5.getText().toString());
        double dlDooroh5 = Double.parseDouble(dldop5.getText().toString());*/
        double doorLoadValue5 = (((dlWidth5 * dlHeight5) * outDeltagr * outF1 * dlDooroh5) / 7);

        double totalPassbox = doorLoadValue0 + doorLoadValue1 + doorLoadValue2 + doorLoadValue3 + doorLoadValue4 + doorLoadValue5;
        BasicInformation.setPassbox(String.format("%.2f", totalPassbox));
        //Toast.makeText(InputActivity.this,"doorLoadValue0"+totalPassbox,Toast.LENGTH_SHORT).show();
        return totalPassbox;
    }

    //fixed load cal
    private double fixedLoadCal() {
        double fixedLoadValue0, fixedLoadValue1, fixedLoadValue2, fixedLoadValue3, fixedLoadValue4, fixedLoadValue5;
        double fixegDeltagr = Double.parseDouble(fixedopningdeltaGr.getText().toString());
        double fixedF1 = Double.parseDouble(cofoF1.getText().toString());

        double fodLengtht0,fodWidth0,fodDepth0;

        try {
            fodLengtht0 = new Double(fod_l0.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht0 = 0; // your default value
        }

        try {

            fodWidth0 = new Double(fod_w0.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth0 = 0; // your default value
        }

        try {
            fodDepth0 = new Double(fod_d0.getText().toString());
        } catch (NumberFormatException e) {
            fodDepth0 = 0; // your default value
        }

       /* double fodLengtht0 = Double.parseDouble(fod_l0.getText().toString());
        double fodWidth0 = Double.parseDouble(fod_w0.getText().toString());
        double fodDepth0 = Double.parseDouble(fod_d0.getText().toString());*/
        if (fodDepth0 == 0.0) {
            fodDepth0 = 1.0;
            fixedLoadValue0 = (((fodLengtht0 * fodWidth0) * 300 / fodDepth0 * fixegDeltagr * fixedF1) / 14);
        } else {
            fixedLoadValue0 = (((fodLengtht0 * fodWidth0) * 300 / fodDepth0 * fixegDeltagr * fixedF1) / 14);
        }




        double fodLengtht1,fodWidth1,fodDepth1;

        try {
            fodLengtht1 = new Double(fod_l1.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht1 = 0; // your default value
        }

        try {
            fodWidth1 = new Double(fod_w1.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth1 = 0; // your default value
        }

        try {
            fodDepth1 = new Double(fod_d1.getText().toString());
        } catch (NumberFormatException e) {
            fodDepth1 = 0; // your default value
        }

        /*double fodLengtht1 = Double.parseDouble(fod_l1.getText().toString());
        double fodWidth1 = Double.parseDouble(fod_w1.getText().toString());
        double fodDepth1 = Double.parseDouble(fod_d1.getText().toString());*/

        if (fodDepth1 == 0.0) {
            fodDepth1 = 1.0;
            fixedLoadValue1 = (((fodLengtht1 * fodWidth1) * 300 / fodDepth1 * fixegDeltagr * fixedF1) / 14);
        } else {
            fixedLoadValue1 = (((fodLengtht1 * fodWidth1) * 300 / fodDepth1 * fixegDeltagr * fixedF1) / 14);
        }


        double fodLengtht2,fodWidth2,fodDepth2;

        try {
            fodLengtht2 = new Double(fod_l2.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht2 = 0; // your default value
        }

        try {
            fodWidth2 = new Double(fod_w2.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth2 = 0; // your default value
        }

        try {
            fodDepth2 = new Double(fod_d2.getText().toString());
        } catch (NumberFormatException e) {
            fodDepth2 = 0; // your default value
        }

       /* double fodLengtht2 = Double.parseDouble(fod_l2.getText().toString());
        double fodWidth2 = Double.parseDouble(fod_w2.getText().toString());
        double fodDepth2 = Double.parseDouble(fod_d2.getText().toString());*/

        if (fodDepth2 == 0.0) {
            fodDepth2 = 1.0;
            fixedLoadValue2 = (((fodLengtht2 * fodWidth2) * 300 / fodDepth2 * fixegDeltagr * fixedF1) / 14);
        } else {
            fixedLoadValue2 = (((fodLengtht2 * fodWidth2) * 300 / fodDepth2 * fixegDeltagr * fixedF1) / 14);
        }


        double fodLengtht3,fodWidth3,fodDepth3;

        try {
            fodLengtht3 = new Double(fod_l3.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht3 = 0; // your default value
        }

        try {
            fodWidth3 = new Double(fod_w3.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth3 = 0; // your default value
        }

        try {
            fodDepth3 = new Double(fod_d3.getText().toString());
        } catch (NumberFormatException e) {
            fodDepth3 = 0; // your default value
        }



       /* double fodLengtht3 = Double.parseDouble(fod_l3.getText().toString());
        double fodWidth3 = Double.parseDouble(fod_w3.getText().toString());
        double fodDepth3 = Double.parseDouble(fod_d3.getText().toString());*/

        if (fodDepth3 == 0.0) {
            fodDepth3 = 1.0;
            fixedLoadValue3 = (((fodLengtht3 * fodWidth3) * 300 / fodDepth3 * fixegDeltagr * fixedF1) / 14);
        } else {
            fixedLoadValue3 = (((fodLengtht3 * fodWidth3) * 300 / fodDepth3 * fixegDeltagr * fixedF1) / 14);
        }


        double fodLengtht4,fodWidth4,fodDepth4;

        try {
            fodLengtht4 = new Double(fod_l4.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht4 = 0; // your default value
        }

        try {
            fodWidth4 = new Double(fod_w4.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth4 = 0; // your default value
        }

        try {
            fodDepth4 = new Double(fod_d4.getText().toString());
        } catch (NumberFormatException e) {
            fodDepth4 = 0; // your default value
        }

       /* double fodLengtht4 = Double.parseDouble(fod_l4.getText().toString());
        double fodWidth4 = Double.parseDouble(fod_w4.getText().toString());
        double fodDepth4 = Double.parseDouble(fod_d4.getText().toString());*/
        if (fodDepth4 == 0.0) {
            fodDepth4 = 1.0;
            fixedLoadValue4 = (((fodLengtht4 * fodWidth4) * 300 / fodDepth4 * fixegDeltagr * fixedF1) / 14);
        } else {
            fixedLoadValue4 = (((fodLengtht4 * fodWidth4) * 300 / fodDepth4 * fixegDeltagr * fixedF1) / 14);
        }


        double fodLengtht5,fodWidth5,fodDepth5;

        try {
            fodLengtht5 = new Double(fod_l5.getText().toString());
        } catch (NumberFormatException e) {
            fodLengtht5 = 0; // your default value
        }

        try {
            fodWidth5 = new Double(fod_w5.getText().toString());
        } catch (NumberFormatException e) {
            fodWidth5 = 0; // your default value
        }

        try {
            fodDepth5 = new Double(fod_d5.getText().toString());
        } catch (NumberFormatException e) {
            fodDepth5 = 0; // your default value
        }


       /* double fodLengtht5 = Double.parseDouble(fod_l5.getText().toString());
        double fodWidth5 = Double.parseDouble(fod_w5.getText().toString());
        double fodDepth5 = Double.parseDouble(fod_d5.getText().toString());*/
        if (fodDepth5 == 0.0) {
            fodDepth5 = 1.0;
            fixedLoadValue5 = (((fodLengtht5 * fodWidth5) * 300 / fodDepth5 * fixegDeltagr * fixedF1) / 14);
        } else {
            fixedLoadValue5 = (((fodLengtht5 * fodWidth5) * 300 / fodDepth5 * fixegDeltagr * fixedF1) / 14);
        }


        double totalMoistureingress = fixedLoadValue0 + fixedLoadValue1 + fixedLoadValue2 + fixedLoadValue3 + fixedLoadValue4 + fixedLoadValue5;
        BasicInformation.setMoisture(String.format("%.2f", totalMoistureingress));
        //Toast.makeText(InputActivity.this, "totalMoistureingress" + totalMoistureingress, Toast.LENGTH_SHORT).show();
        return totalMoistureingress;
    }


    public double getOutLet1(TextView grValue, EditText designDbt) {
        String deltavalue = "0.0";
        String dbtValue = "0.0";
        double outputData = 0.0;
        if ((designDbt.getText().toString() == "0.0") || designDbt.getText().equals("") || designDbt.getText().toString().length() == 0) {
            designDbt.setText("0.0");
            deltavalue = grValue.getText().toString();
            dbtValue = designDbt.getText().toString();
            Log.w("if data", "if outlet" + dbtValue);
        } else {
            deltavalue = grValue.getText().toString();
            dbtValue = designDbt.getText().toString();
            //getOutLetfromApi( Double.valueOf(dbtValue),Double.valueOf(""+0.65));
            //double dbt_value = Double.valueOf(dbtValue);

            double dvalue = (Double.parseDouble(deltavalue) / 7);
            double dbt_value = (Double.parseDouble(dbtValue) - 32) * 5 / 9;

            Log.w("dvalue", "if outlet" + dvalue);
            Log.w("dbt_value", "if outlet" + dbt_value);

            if (dbt_value == 10 && dbt_value < 10) {
                dbtValue = "10";
            } else if (dbt_value > 10 && dbt_value < 11 || dbt_value == 11) {
                dbtValue = "11";
            } else if (dbt_value > 11 && dbt_value < 12 || dbt_value == 12) {
                dbtValue = "12";
            } else if (dbt_value > 12 && dbt_value < 13 || dbt_value == 13) {
                dbtValue = "13";
            } else if (dbt_value > 13 && dbt_value < 14 || dbt_value == 14) {
                dbtValue = "14";
            } else if (dbt_value > 14 && dbt_value < 15 || dbt_value == 15) {
                dbtValue = "15";
            } else if (dbt_value > 15 && dbt_value < 16 || dbt_value == 16) {
                dbtValue = "16";
            } else if (dbt_value > 16 && dbt_value < 17 || dbt_value == 17) {
                dbtValue = "17";
            } else if (dbt_value > 17 && dbt_value < 18 || dbt_value == 18) {
                dbtValue = "18";
            } else if (dbt_value > 18 && dbt_value < 19 || dbt_value == 19) {
                dbtValue = "19";
            } else if (dbt_value > 19 && dbt_value < 20 || dbt_value == 20) {
                dbtValue = "20";
            } else if (dbt_value > 20 && dbt_value < 21 || dbt_value == 21) {
                dbtValue = "21";
            } else if (dbt_value > 21 && dbt_value < 22 || dbt_value == 22) {
                dbtValue = "22";
            } else if (dbt_value > 22 && dbt_value < 23 || dbt_value == 23) {
                dbtValue = "23";
            } else if (dbt_value > 23 && dbt_value < 24 || dbt_value == 24) {
                dbtValue = "24";
            } else if (dbt_value > 24 && dbt_value < 25 || dbt_value == 25) {
                dbtValue = "25";
            } else if (dbt_value > 25 && dbt_value < 26 || dbt_value == 26) {
                dbtValue = "26";
            } else if (dbt_value > 26 && dbt_value < 27 || dbt_value == 27) {
                dbtValue = "27";
            } else if (dbt_value > 27 && dbt_value < 28 || dbt_value == 28) {
                dbtValue = "28";
            } else if (dbt_value > 28 && dbt_value < 29 || dbt_value == 29) {
                dbtValue = "29";
            } else if (dbt_value > 29 && dbt_value < 30 || dbt_value == 30) {
                dbtValue = "30";
            }

            BasicInformation.setDvalue(String.valueOf(dvalue));
            BasicInformation.setDbtValue(dbtValue);
           // Toast.makeText(InputActivity.this, " " + dbt_value, Toast.LENGTH_SHORT).show();
            if (InternetStatus.isConnectingToInternet(InputActivity.this)) {
                //new SendTempAsync(InputActivity.this, String.valueOf(dvalue), dbtValue).execute();
                BasicInformation.setDvalue(String.valueOf(dvalue));
                BasicInformation.setDbtValue(dbtValue);
                new SendValueAsync(InputActivity.this, String.valueOf(dvalue), dbtValue).execute();


            }

        }


        return outputData;
    }


//    private double getOutLet(TextView grValue, EditText designDbt) {
//
//        String deltavalue = "0.0";
//        String dbtValue = "0.0";
//        double outputData = 0.0;
//        if ((designDbt.getText().toString() == "0.0") || designDbt.getText().equals("") || designDbt.getText().toString().length() == 0) {
//            designDbt.setText("0.0");
//            deltavalue = grValue.getText().toString();
//            dbtValue = designDbt.getText().toString();
//            Log.w("if data", "if outlet" + dbtValue);
//        } else {
//            deltavalue = grValue.getText().toString();
//            dbtValue = designDbt.getText().toString();
//            Log.w("else data", "else outlet" + dbtValue);
//        }
//
//
//        double dvalue = (Double.parseDouble(deltavalue) / 7);
//        double dbtvalue = (Double.parseDouble(dbtValue) - 32) * 5 / 9;
//
//        Log.w("Gr value is ", "" + dvalue);
//        Log.w("Dbt value is ", "" + dbtvalue);
//
//        if (dvalue < 1) {
//
//            if (dbtvalue > 10 && dbtvalue < 15) {
//
//                outputData = .25;
//            } else if (dbtvalue > 15 && dbtvalue < 20) {
//
//                outputData = .28;
//            } else if (dbtvalue > 20 && dbtvalue < 25) {
//                outputData = .3;
//            } else if (dbtvalue > 25 && dbtvalue < 30) {
//
//                outputData = .35;
//            } else if (dbtvalue == 30) {
//
//                outputData = .45;
//            }
//        } else if (dvalue == 1) {
//            if ((dbtvalue == 10)) {
//                outputData = 0.25;
//            }
//            if (dbtvalue == 15) {
//                outputData = 0.3125;
//            }
//            if (dbtvalue == 20) {
//                outputData = 0.375;
//            }
//            if (dbtvalue == 25) {
//                outputData = 0.375;
//            }
//            if (dbtvalue == 30) {
//                outputData = 0.45;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 1) && (dvalue < 1.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                double varDelta = dbtvalue - 10;
//
//                double topD = 1;
//                double bottomD = 1.5;
//
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.25 + varDelta * 0.01;
//                double bottomT = 0.3 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                double topD = 1;
//                double bottomD = 1.5;
//                double varDelta = dbtvalue - 15;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.3125 + varDelta * 0.01;
//                double bottomT = 0.36 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                double topD = 1;
//                double bottomD = 1.5;
//                double varDelta = dbtvalue - 20;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.375 + varDelta * 0.01;
//                double bottomT = 0.38 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//                double topD = 1;
//                double bottomD = 1.5;
//
//                double varDelta = dbtvalue - 25;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.375 + varDelta * 0.017;
//                double bottomT = 0.4 + varDelta * 0.017;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 30) {
//                double topD = 1;
//                double bottomD = 1.5;
//
//                double varDelta = dbtvalue - 30;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.45 + varDelta * 0.017;
//                double bottomT = 0.55 + varDelta * 0.017;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 1.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 0.3;
//            }
//            if (dbtvalue == 15) {
//                outputData = 0.36;
//            }
//            if (dbtvalue == 20) {
//                outputData = 0.38;
//            }
//            if (dbtvalue == 25) {
//                outputData = 0.4;
//            }
//            if (dbtvalue == 30) {
//                outputData = 0.55;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 1.5) && (dvalue < 2)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                double topD = 1.5;
//                double bottomD = 2;
//
//                double varDelta = dbtvalue - 10;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.3 + varDelta * 0.01;
//                double bottomT = 0.35 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                double topD = 1.5;
//                double bottomD = 2;
//
//                double varDelta = dbtvalue - 15;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.36 + varDelta * 0.01;
//                double bottomT = 0.41 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                double topD = 1.5;
//                double bottomD = 2;
//
//                double varDelta = dbtvalue - 20;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.38 + varDelta * 0.01;
//                double bottomT = 0.49 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//                double topD = 1.5;
//                double bottomD = 2;
//
//                double varDelta = dbtvalue - 25;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.4 + varDelta * 0.017;
//                double bottomT = 0.51 + varDelta * 0.017;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 30) {
//                double topD = 1.5;
//                double bottomD = 2;
//
//                double varDelta = dbtvalue - 30;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.55 + varDelta * 0.017;
//                double bottomT = 0.7 + varDelta * 0.017;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 2) {
//            if ((dbtvalue == 10)) {
//                outputData = 0.35;
//            } else if (dbtvalue == 15) {
//                outputData = 0.41;
//            } else if (dbtvalue == 20) {
//                outputData = 0.49;
//            } else if (dbtvalue == 25) {
//                outputData = 0.51;
//            } else if (dbtvalue == 30) {
//                outputData = 0.7;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 2) && (dvalue < 2.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                double topD = 2;
//                double bottomD = 2.5;
//
//                double varDelta = dbtvalue - 10;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.35 + varDelta * 0.01;
//                double bottomT = 0.4 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                double topD = 2;
//                double bottomD = 2.5;
//
//                double varDelta = dbtvalue - 15;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.41 + varDelta * 0.01;
//                double bottomT = 0.5 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                double topD = 2;
//                double bottomD = 2.5;
//
//                double varDelta = dbtvalue - 20;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.49 + varDelta * 0.01;
//                double bottomT = 0.51 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//                double topD = 2;
//                double bottomD = 2.5;
//
//                double varDelta = dbtvalue - 25;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.51 + varDelta * 0.033;
//                double bottomT = 0.52 + varDelta * 0.033;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 30) {
//                double topD = 2;
//                double bottomD = 2.5;
//
//                double varDelta = dbtvalue - 30;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.7 + varDelta * 0.033;
//                double bottomT = 0.8 + varDelta * 0.033;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 2.5) {
//            if ((dbtvalue == 10)) {
//                outputData = .4;
//            }
//            if (dbtvalue == 15) {
//                outputData = 0.5;
//            }
//            if (dbtvalue == 20) {
//                outputData = 0.51;
//            }
//            if (dbtvalue == 25) {
//                outputData = 0.52;
//            }
//            if (dbtvalue == 30) {
//                outputData = 0.8;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 2.5) && (dvalue < 3)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                double topD = 2.5;
//                double bottomD = 3;
//
//                double varDelta = dbtvalue - 10;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.4 + varDelta * 0.01;
//                double bottomT = 0.495 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                double topD = 2.5;
//                double bottomD = 3;
//
//                double varDelta = dbtvalue - 15;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.5 + varDelta * 0.01;
//                double bottomT = 0.51 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                double topD = 2.5;
//                double bottomD = 3;
//
//                double varDelta = dbtvalue - 20;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.51 + varDelta * 0.01;
//                double bottomT = 0.52 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//                double topD = 2.5;
//                double bottomD = 3;
//
//                double varDelta = dbtvalue - 30;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.52 + varDelta * 0.033;
//                double bottomT = 0.7 + varDelta * 0.033;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 30) {
//                double topD = 2.5;
//                double bottomD = 3;
//
//                double varDelta = dbtvalue - 30;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.8 + varDelta * 0.033;
//                double bottomT = 0.9 + varDelta * 0.033;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 3) {
//            if ((dbtvalue == 10)) {
//                outputData = 0.495;
//            } else if (dbtvalue == 15) {
//                outputData = 0.51;
//            } else if (dbtvalue == 20) {
//                outputData = 0.52;
//            } else if (dbtvalue == 25) {
//                outputData = 0.7;
//            } else if (dbtvalue == 30) {
//                outputData = 0.9;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 3) && (dvalue < 3.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                double topD = 3;
//                double bottomD = 3.5;
//
//                double varDelta = dbtvalue - 10;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.495 + varDelta * 0.01;
//                double bottomT = 0.52 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                double topD = 3;
//                double bottomD = 3.5;
//
//                double varDelta = dbtvalue - 15;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.51 + varDelta * 0.01;
//                double bottomT = 0.6 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                double topD = 3;
//                double bottomD = 3.5;
//
//                double varDelta = dbtvalue - 20;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.52 + varDelta * 0.01;
//                double bottomT = 0.75 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//                double topD = 3;
//                double bottomD = 3.5;
//
//                double varDelta = dbtvalue - 25;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.7 + varDelta * 0.044;
//                double bottomT = 0.9 + varDelta * 0.044;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 30) {
//                double topD = 3;
//                double bottomD = 3.5;
//
//                double varDelta = dbtvalue - 30;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.9 + varDelta * 0.044;
//                double bottomT = 1.05 + varDelta * 0.044;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 3.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 0.52;
//            } else if (dbtvalue == 15) {
//                outputData = 0.6;
//            } else if (dbtvalue == 20) {
//                outputData = 0.75;
//            } else if (dbtvalue == 25) {
//                outputData = 0.9;
//            } else if (dbtvalue == 30) {
//                outputData = 1.05;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 3.5) && (dvalue < 4)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                double topD = 3.5;
//                double bottomD = 4;
//
//                double varDelta = dbtvalue - 10;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.52 + varDelta * 0.01;
//                double bottomT = 0.65 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                double topD = 3.5;
//                double bottomD = 4;
//
//                double varDelta = dbtvalue - 15;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.6 + varDelta * 0.01;
//                double bottomT = 0.7 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                double topD = 3.5;
//                double bottomD = 4;
//
//                double varDelta = dbtvalue - 20;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.75 + varDelta * 0.01;
//                double bottomT = 0.85 + varDelta * 0.01;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                double topD = 3.5;
//                double bottomD = 4;
//
//                double varDelta = dbtvalue - 25;
//                double diffDValue = bottomD - topD;
//
//                double topT = 0.9 + varDelta * 0.044;
//                double bottomT = 1.05 + varDelta * 0.044;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            }
//            if (dbtvalue >= 30) {
//                double topD = 3.5;
//                double bottomD = 4;
//
//                double varDelta = dbtvalue - 30;
//                double diffDValue = bottomD - topD;
//
//                double topT = 1.05 + varDelta * 0.044;
//                double bottomT = 1.22 + varDelta * 0.044;
//
//                double diffTValue = bottomT - topT;
//
//                double vDiv = diffTValue / diffDValue;
//
//                outputData = vDiv * diffDValue + topT;
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 4) {
//            if ((dbtvalue == 10)) {
//                outputData = 0.65;
//            }
//            if (dbtvalue == 15) {
//                outputData = 0.7;
//            }
//            if (dbtvalue == 20) {
//                outputData = 0.85;
//            }
//            if (dbtvalue == 25) {
//                outputData = 1.05;
//            }
//            if (dbtvalue == 30) {
//                outputData = 1.22;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 4) && (dvalue < 4.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(4, 4.5, dbtvalue, 10, 0.65, 0.75, .01);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(4, 4.5, dbtvalue, 15, 0.7, 0.85, .01);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(4, 4.5, dbtvalue, 20, 0.85, 1, .01);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(4, 4.5, dbtvalue, 25, 1.05, 1.2, 0.082);
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(4, 4.5, dbtvalue, 30, 1.22, 1.42, 0.082);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 4.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 0.75;
//            }
//            if (dbtvalue == 15) {
//                outputData = 0.85;
//            }
//            if (dbtvalue == 20) {
//                outputData = 1;
//            }
//            if (dbtvalue == 25) {
//                outputData = 1.2;
//            }
//            if (dbtvalue == 30) {
//                outputData = 1.42;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 4.5) && (dvalue < 5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(4.5, 5, dbtvalue, 10, 0.75, 0.85, .01);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(4.5, 5, dbtvalue, 15, 0.85, 0.96, .01);
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(4.5, 5, dbtvalue, 20, 1, 1.15, .01);
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(4.5, 5, dbtvalue, 25, 1.2, 1.375, .092);
//
//            } else if (dbtvalue >= 30) {
//                outputData = diffOutLet(4.5, 5, dbtvalue, 30, 1.42, 1.62, 0.092);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 5) {
//            if ((dbtvalue == 10)) {
//                outputData = 85;
//            }
//            if (dbtvalue == 15) {
//                outputData = 96;
//            }
//            if (dbtvalue == 20) {
//                outputData = 1.15;
//            }
//            if (dbtvalue == 25) {
//                outputData = 1.375;
//            }
//            if (dbtvalue == 30) {
//                outputData = 1.62;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 5) && (dvalue < 5.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(5, 5.5, dbtvalue, 10, 0.85, 0.95, .01);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(5, 5.5, dbtvalue, 15, 0.96, 1.1, .01);
//
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(5, 5.5, dbtvalue, 20, 1.15, 1.35, .01);
//
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(5, 5.5, dbtvalue, 25, 1.375, 1.55, 0.10);
//
//
//            } else if (dbtvalue >= 30) {
//                outputData = diffOutLet(5, 5.5, dbtvalue, 30, 1.62, 1.82, 0.10);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 5.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 0.95;
//            }
//            if (dbtvalue == 15) {
//                outputData = 1.1;
//            }
//            if (dbtvalue == 20) {
//                outputData = 1.35;
//            }
//            if (dbtvalue == 25) {
//                outputData = 1.55;
//            }
//            if (dbtvalue == 30) {
//                outputData = 1.82;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 5.5) && (dvalue < 6)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(5.5, 6, dbtvalue, 10, 0.95, 1, .01);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(5.5, 6, dbtvalue, 15, 1.1, 1.25, .01);
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(5.5, 6, dbtvalue, 20, 1.35, 1.5, .01);
//
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(5.5, 6, dbtvalue, 25, 1.55, 1.75, 0.13);
//
//            } else if (dbtvalue >= 30) {
//                outputData = diffOutLet(5.5, 6, dbtvalue, 30, 1.82, 2, 0.13);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 6) {
//            if ((dbtvalue == 10)) {
//                outputData = 1;
//            }
//            if (dbtvalue == 15) {
//                outputData = 1.25;
//            }
//            if (dbtvalue == 20) {
//                outputData = 1.5;
//            }
//            if (dbtvalue == 25) {
//                outputData = 1.75;
//            }
//            if (dbtvalue == 30) {
//                outputData = 2;
//            }
//        }
//        // B/W two value....
//
//        else if ((dvalue > 6) && (dvalue < 6.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(6, 6.5, dbtvalue, 10, 1, 1.12, 0.015);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(6, 6.5, dbtvalue, 15, 1.25, 1.45, 0.015);
//
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(6, 6.5, dbtvalue, 20, 1.5, 1.7, .015);
//
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(6, 6.5, dbtvalue, 25, 1.75, 2, .14);
//
//
//            } else if (dbtvalue >= 30) {
//                outputData = diffOutLet(6, 6.5, dbtvalue, 30, 2, 2.3, .14);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 6.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 1.12;
//            }
//            if (dbtvalue == 15) {
//                outputData = 1.45;
//            }
//            if (dbtvalue == 20) {
//                outputData = 1.7;
//            }
//            if (dbtvalue == 25) {
//                outputData = 2;
//            }
//            if (dbtvalue == 30) {
//                outputData = 2.3;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 6.5) && (dvalue < 7)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(6.5, 7, dbtvalue, 10, 1.12, 1.25, 0.015);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(6.5, 7, dbtvalue, 15, 1.45, 1.65, .015);
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(6.5, 7, dbtvalue, 20, 1.7, 1.9, .015);
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(6.5, 7, dbtvalue, 25, 2, 2.2, .15);
//
//            } else if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(6.5, 7, dbtvalue, 30, 2.3, 2.5, .15);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 7) {
//            if ((dbtvalue == 10)) {
//                outputData = 1.25;
//            }
//            if (dbtvalue == 15) {
//                outputData = 1.65;
//            }
//            if (dbtvalue == 20) {
//                outputData = 1.9;
//            }
//            if (dbtvalue == 25) {
//                outputData = 2.2;
//            }
//            if (dbtvalue == 30) {
//                outputData = 2.5;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 7) && (dvalue < 7.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(7, 7.5, dbtvalue, 10, 1.25, 1.45, .02);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(7, 7.5, dbtvalue, 15, 1.65, 1.8, .02);
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(7, 7.5, dbtvalue, 20, 1.9, 2.15, .02);
//
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(7, 7.5, dbtvalue, 25, 2.2, 2.45, .16);
//
//            } else if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(7, 7.5, dbtvalue, 30, 2.5, 2.8, .16);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 7.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 1.45;
//            }
//            if (dbtvalue == 15) {
//                outputData = 1.8;
//            }
//            if (dbtvalue == 20) {
//                outputData = 2.15;
//            }
//            if (dbtvalue == 25) {
//                outputData = 2.45;
//            }
//            if (dbtvalue == 30) {
//                outputData = 2.8;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 7.5) && (dvalue < 8)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(7.5, 8, dbtvalue, 10, 1.45, 1.6, .038);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(7.5, 8, dbtvalue, 15, 1.8, 2, .038);
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(7.5, 8, dbtvalue, 20, 2.15, 2.4, .038);
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(7.5, 8, dbtvalue, 25, 2.45, 2.7, .18);
//
//
//            } else if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(7.5, 8, dbtvalue, 30, 2.8, 3, .18);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 8) {
//            if ((dbtvalue == 10)) {
//                outputData = 1.6;
//            }
//            if (dbtvalue == 15) {
//                outputData = 2;
//            }
//            if (dbtvalue == 20) {
//                outputData = 2.4;
//            }
//            if (dbtvalue == 25) {
//                outputData = 2.7;
//            }
//            if (dbtvalue == 30) {
//                outputData = 3;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 8) && (dvalue < 8.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(8, 8.5, dbtvalue, 10, 1.6, 1.85, .038);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(8, 8.5, dbtvalue, 15, 2, 2.5, .038);
//
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(8, 8.5, dbtvalue, 20, 2.4, 2.6, .038);
//
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(8, 8.5, dbtvalue, 25, 2.7, 3, .19);
//
//
//            } else if (dbtvalue >= 30) {
//                outputData = diffOutLet(8, 8.5, dbtvalue, 30, 3, 3.5, .19);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 8.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 1.85;
//            }
//            if (dbtvalue == 15) {
//                outputData = 2.25;
//            }
//            if (dbtvalue == 20) {
//                outputData = 2.6;
//            }
//            if (dbtvalue == 25) {
//                outputData = 3;
//            }
//            if (dbtvalue == 30) {
//                outputData = 3.35;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 8.5) && (dvalue < 9)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(8.5, 9, dbtvalue, 10, 1.85, 2.1, .038);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(8.5, 9, dbtvalue, 15, 2.25, 2.45, .038);
//
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(8.5, 9, dbtvalue, 20, 2.6, 2.85, .038);
//
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(8.5, 9, dbtvalue, 25, 3, 3.35, .19);
//
//            } else if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(8.5, 9, dbtvalue, 30, 3.35, 3.65, .19);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 9) {
//            if ((dbtvalue == 10)) {
//                outputData = 2.1;
//            }
//            if (dbtvalue == 15) {
//                outputData = 2.45;
//            }
//            if (dbtvalue == 20) {
//                outputData = 2.85;
//            }
//            if (dbtvalue == 25) {
//                outputData = 3.35;
//            }
//            if (dbtvalue == 30) {
//                outputData = 3.65;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 9) && (dvalue < 9.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(9, 9.5, dbtvalue, 10, 2.1, 2.65, .038);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(9, 9.5, dbtvalue, 15, 2.45, 3.15, .038);
//
//
//            } else if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(9, 9.5, dbtvalue, 20, 2.85, 3.2, .038);
//
//            } else if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(9, 9.5, dbtvalue, 25, 3.35, 3.5, .19);
//
//            } else if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(9, 9.5, dbtvalue, 30, 3.65, 3.9, .19);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 9.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 2.65;
//            }
//            if (dbtvalue == 15) {
//                outputData = 3.15;
//            }
//            if (dbtvalue == 20) {
//                outputData = 3.2;
//            }
//            if (dbtvalue == 25) {
//                outputData = 3.5;
//            }
//            if (dbtvalue == 30) {
//                outputData = 3.9;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 9.5) && (dvalue < 10)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(9.5, 10, dbtvalue, 10, 2.65, 2.45, .038);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(9.5, 10, dbtvalue, 15, 3.15, 2.9, .038);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(9.5, 10, dbtvalue, 20, 3.2, 3.4, .038);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(9.5, 10, dbtvalue, 25, 3.5, 3.9, .19);
//
//            }
//            if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(9.5, 10, dbtvalue, 30, 3.9, 4.35, .19);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 10) {
//            if ((dbtvalue == 10)) {
//                outputData = 2.45;
//            }
//            if (dbtvalue == 15) {
//                outputData = 2.9;
//            }
//            if (dbtvalue == 20) {
//                outputData = 3.4;
//            }
//            if (dbtvalue == 25) {
//                outputData = 3.9;
//            }
//            if (dbtvalue == 30) {
//                outputData = 4.35;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 10) && (dvalue < 10.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(10, 10.5, dbtvalue, 10, 2.45, 3, .05);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(10, 10.5, dbtvalue, 15, 2.9, 3.35, .05);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(10, 10.5, dbtvalue, 20, 3.4, 3.65, .05);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(10, 10.5, dbtvalue, 25, 3.9, 4.12, .25);
//
//
//            }
//            if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(10, 10.5, dbtvalue, 30, 4.35, 4.6, .25);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 10.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 3;
//            }
//            if (dbtvalue == 15) {
//                outputData = 3.35;
//            }
//            if (dbtvalue == 20) {
//                outputData = 3.65;
//            }
//            if (dbtvalue == 25) {
//                outputData = 4.12;
//            }
//            if (dbtvalue == 30) {
//                outputData = 4.6;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 10.5) && (dvalue < 11)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(10.5, 11, dbtvalue, 10, 3, 3.15, .05);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(10.5, 11, dbtvalue, 15, 3.35, 3.6, .05);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(10.5, 11, dbtvalue, 20, 3.65, 3.95, .05);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(10.5, 11, dbtvalue, 25, 4.12, 4.45, .05);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(10.5, 11, dbtvalue, 30, 4.6, 4.9, .05);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 11) {
//            if ((dbtvalue == 10)) {
//                outputData = 3.15;
//            }
//            if (dbtvalue == 15) {
//                outputData = 3.6;
//            }
//            if (dbtvalue == 20) {
//                outputData = 3.95;
//            }
//            if (dbtvalue == 25) {
//                outputData = 4.45;
//            }
//            if (dbtvalue == 30) {
//                outputData = 4.9;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 11) && (dvalue < 11.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(11, 11.5, dbtvalue, 10, 3.15, 3.45, .05);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(11, 11.5, dbtvalue, 15, 3.6, 3.75, .05);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(11, 11.5, dbtvalue, 10, 3.95, 4.3, .05);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(11, 11.5, dbtvalue, 25, 4.45, 4.75, 0.25);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(11, 11.5, dbtvalue, 30, 4.9, 5.3, 0.25);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 11.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 3.45;
//            }
//            if (dbtvalue == 15) {
//                outputData = 3.75;
//            }
//            if (dbtvalue == 20) {
//                outputData = 4.3;
//            }
//            if (dbtvalue == 25) {
//                outputData = 4.75;
//            }
//            if (dbtvalue == 30) {
//                outputData = 5.3;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 11.5) && (dvalue < 12)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(11.5, 12, dbtvalue, 10, 3.45, 3.65, .05);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(11.5, 12, dbtvalue, 15, 3.75, 4.15, .05);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(11.5, 12, dbtvalue, 20, 4.3, 4.6, .05);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(11.5, 12, dbtvalue, 25, 4.75, 5.15, .25);
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(11.5, 12, dbtvalue, 30, 5.3, 5.3, .25);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 12) {
//            if ((dbtvalue == 10)) {
//                outputData = 3.65;
//            }
//            if (dbtvalue == 15) {
//                outputData = 4.15;
//            }
//            if (dbtvalue == 20) {
//                outputData = 4.6;
//            }
//            if (dbtvalue == 25) {
//                outputData = 5.15;
//            }
//            if (dbtvalue == 30) {
//                outputData = 5.6;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 12) && (dvalue < 12.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(12, 12.5, dbtvalue, 10, 3.65, 3.95, .06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(12, 12.5, dbtvalue, 15, 4.15, 4.45, .06);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(12, 12.5, dbtvalue, 20, 4.6, 4.9, .06);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(12, 12.5, dbtvalue, 25, 5.15, 5.45, .28);
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(12, 12.5, dbtvalue, 30, 5.6, 5.95, .28);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 12.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 3.95;
//            }
//            if (dbtvalue == 15) {
//                outputData = 4.45;
//            }
//            if (dbtvalue == 20) {
//                outputData = 4.9;
//            }
//            if (dbtvalue == 25) {
//                outputData = 5.45;
//            }
//            if (dbtvalue == 30) {
//                outputData = 5.95;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 12.5) && (dvalue < 13)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(12.5, 13, dbtvalue, 10, 3.95, 4, .06);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(12.5, 13, dbtvalue, 15, 4.45, 4.75, .06);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(12.5, 13, dbtvalue, 20, 4.9, 5.25, .06);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(12.5, 13, dbtvalue, 25, 5.45, 5.8, .28);
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(12.5, 13, dbtvalue, 30, 5.95, 6.35, .28);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 13) {
//            if ((dbtvalue == 10)) {
//                outputData = 4;
//            }
//            if (dbtvalue == 15) {
//                outputData = 4.75;
//            }
//            if (dbtvalue == 20) {
//                outputData = 5.25;
//            }
//            if (dbtvalue == 25) {
//                outputData = 5.8;
//            }
//            if (dbtvalue == 30) {
//                outputData = 6.35;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 13) && (dvalue < 13.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(13, 13.5, dbtvalue, 10, 4, 4.4, 0.06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(13, 13.5, dbtvalue, 15, 4.75, 5, 0.06);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(13, 13.5, dbtvalue, 20, 5.25, 5.6, .06);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(13, 13.5, dbtvalue, 25, 5.8, 6.2, .28);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(13, 13.5, dbtvalue, 30, 6.35, 6.75, .28);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 13.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 4.4;
//            }
//            if (dbtvalue == 15) {
//                outputData = 5;
//            }
//            if (dbtvalue == 20) {
//                outputData = 5.6;
//            }
//            if (dbtvalue == 25) {
//                outputData = 6.2;
//            }
//            if (dbtvalue == 30) {
//                outputData = 6.75;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 13.5) && (dvalue < 14)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(13.5, 14, dbtvalue, 10, 4.4, 4.6, .06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(13.5, 14, dbtvalue, 15, 5, 5.95, .06);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(13.5, 14, dbtvalue, 20, 5.6, 5.9, .06);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(13.5, 14, dbtvalue, 25, 6.2, 6.49, .28);
//
//
//            }
//            if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(13.5, 14, dbtvalue, 30, 6.75, 7.1, .28);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 14) {
//            if ((dbtvalue == 10)) {
//                outputData = 4.6;
//            }
//            if (dbtvalue == 15) {
//                outputData = 5.95;
//            }
//            if (dbtvalue == 20) {
//                outputData = 5.9;
//            }
//            if (dbtvalue == 25) {
//                outputData = 6.49;
//            }
//            if (dbtvalue == 30) {
//                outputData = 7.1;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 14) && (dvalue < 14.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(14, 14.5, dbtvalue, 10, 4.6, 5, .06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(14, 14.5, dbtvalue, 15, 5.95, 5.65, .06);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(14, 14.5, dbtvalue, 20, 5.9, 6.3, .06);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(14, 14.5, dbtvalue, 25, 6.49, 6.85, .31);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(14, 14.5, dbtvalue, 30, 7.1, 7.5, .31);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 14.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 5;
//            }
//            if (dbtvalue == 15) {
//                outputData = 5.65;
//            }
//            if (dbtvalue == 20) {
//                outputData = 6.3;
//            }
//            if (dbtvalue == 25) {
//                outputData = 6.85;
//            }
//            if (dbtvalue == 30) {
//                outputData = 7.5;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 14.5) && (dvalue < 15)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(14.5, 15, dbtvalue, 10, 5, 5.2, .06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(14.5, 15, dbtvalue, 15, 5.65, 6, .06);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(14.5, 15, dbtvalue, 20, 6.3, 6.65, .06);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(14.5, 15, dbtvalue, 25, 6.85, 7.25, .31);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(14.5, 15, dbtvalue, 30, 7.5, 7.85, .31);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 15) {
//            if ((dbtvalue == 10)) {
//                outputData = 5.2;
//            }
//            if (dbtvalue == 15) {
//                outputData = 6;
//            }
//            if (dbtvalue == 20) {
//                outputData = 6.65;
//            }
//            if (dbtvalue == 25) {
//                outputData = 7.25;
//            }
//            if (dbtvalue == 30) {
//                outputData = 7.85;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 15) && (dvalue < 15.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(15, 15.5, dbtvalue, 10, 5.2, 5.6, .06);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(15, 15.5, dbtvalue, 15, 6, 6.3, .06);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(15, 15.5, dbtvalue, 20, 6.5, 6.95, .06);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(15, 15.5, dbtvalue, 25, 7.25, 7.5, .31);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(15, 15.5, dbtvalue, 30, 7.85, 8.25, .31);
//
//
//            }
//        } else if (dvalue == 15.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 5.6;
//            }
//            if (dbtvalue == 15) {
//                outputData = 6.3;
//            }
//            if (dbtvalue == 20) {
//                outputData = 6.95;
//            }
//            if (dbtvalue == 25) {
//                outputData = 7.5;
//            }
//            if (dbtvalue == 30) {
//                outputData = 8.25;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 15.5) && (dvalue < 16)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(15.5, 16, dbtvalue, 10, 5.6, 5.95, 06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(15.5, 16, dbtvalue, 15, 6.3, 6.6, .06);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(15.5, 16, dbtvalue, 20, 6.95, 7.3, .06);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(15.5, 16, dbtvalue, 25, 7.5, 7.9, .31);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(15.5, 16, dbtvalue, 30, 8.25, 8.6, .31);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 16) {
//            if ((dbtvalue == 10)) {
//                outputData = 5.95;
//            }
//            if (dbtvalue == 15) {
//                outputData = 6.6;
//            }
//            if (dbtvalue == 20) {
//                outputData = 7.3;
//            }
//            if (dbtvalue == 25) {
//                outputData = 7.9;
//            }
//            if (dbtvalue == 30) {
//                outputData = 8.6;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 16) && (dvalue < 16.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(16, 16.5, dbtvalue, 10, 5.95, 6.3, .06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(16, 16.5, dbtvalue, 15, 6.6, 7, .06);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(16, 16.5, dbtvalue, 20, 7.3, 7.65, .06);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(16, 16.5, dbtvalue, 25, 7.9, 8.5, .34);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(16, 16.5, dbtvalue, 30, 8.6, 8.95, .34);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 16.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 6.3;
//            }
//            if (dbtvalue == 15) {
//                outputData = 7;
//            }
//            if (dbtvalue == 20) {
//                outputData = 7.65;
//            }
//            if (dbtvalue == 25) {
//                outputData = 8.5;
//            }
//            if (dbtvalue == 30) {
//                outputData = 8.95;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 16.5) && (dvalue < 17)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(16.5, 17, dbtvalue, 10, 6.3, 6.52, .06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(16.5, 17, dbtvalue, 15, 7, 7.3, .06);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(16.5, 17, dbtvalue, 20, 7.65, 8.05, .06);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(16.5, 17, dbtvalue, 25, 8.5, 8.7, .34);
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(16.5, 17, dbtvalue, 30, 8.95, 9.45, .34);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 17) {
//            if ((dbtvalue == 10)) {
//                outputData = 6.52;
//            }
//            if (dbtvalue == 15) {
//                outputData = 7.3;
//            }
//            if (dbtvalue == 20) {
//                outputData = 8.05;
//            }
//            if (dbtvalue == 25) {
//                outputData = 8.7;
//            }
//            if (dbtvalue == 30) {
//                outputData = 9.45;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 17) && (dvalue < 17.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(17, 17.5, dbtvalue, 10, 6.52, 6.95, .06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(17, 17.5, dbtvalue, 15, 7.3, 7.65, .06);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(17, 17.5, dbtvalue, 20, 8.05, 8.38, .06);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(17, 17.5, dbtvalue, 25, 8.7, 9.05, .34);
//
//            }
//            if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(17, 17.5, dbtvalue, 30, 9.45, 9.75, .34);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 17.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 6.95;
//            }
//            if (dbtvalue == 15) {
//                outputData = 7.65;
//            }
//            if (dbtvalue == 20) {
//                outputData = 8.38;
//            }
//            if (dbtvalue == 25) {
//                outputData = 9.05;
//            }
//            if (dbtvalue == 30) {
//                outputData = 9.75;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 17.5) && (dvalue < 18)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(17.5, 18, dbtvalue, 10, 6.95, 7.2, .06);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(17.5, 18, dbtvalue, 15, 7.65, 8, .06);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(17.5, 18, dbtvalue, 20, 8.38, 8.65, .06);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(17.5, 18, dbtvalue, 25, 9.05, 9.4, .34);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(17.5, 18, dbtvalue, 10, 9.75, 10.25, .34);
//
//
//            }
//        }
//
//        // End B/W two value
//
//        else if (dvalue == 18) {
//            if ((dbtvalue == 10)) {
//                outputData = 7.2;
//            }
//            if (dbtvalue == 15) {
//                outputData = 8;
//            }
//            if (dbtvalue == 20) {
//                outputData = 8.65;
//            }
//            if (dbtvalue == 25) {
//                outputData = 9.4;
//            }
//            if (dbtvalue == 30) {
//                outputData = 10.25;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 18) && (dvalue < 18.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(18, 18.5, dbtvalue, 10, 7.2, 7.5, .094);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(18, 18.5, dbtvalue, 15, 8, 8.35, .094);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(18, 18.5, dbtvalue, 20, 8.65, 9.12, .094);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(18, 18.5, dbtvalue, 25, 9.4, 9.85, .47);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(18, 18.5, dbtvalue, 30, 10.25, 10.65, .47);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 18.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 7.5;
//            }
//            if (dbtvalue == 15) {
//                outputData = 8.35;
//            }
//            if (dbtvalue == 20) {
//                outputData = 9.12;
//            }
//            if (dbtvalue == 25) {
//                outputData = 9.85;
//            }
//            if (dbtvalue == 30) {
//                outputData = 10.65;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 18.5) && (dvalue < 19)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(18.5, 19, dbtvalue, 10, 7.5, 7.9, .094);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(18.5, 19, dbtvalue, 15, 8.35, 8.65, .094);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(18.5, 19, dbtvalue, 20, 9.12, 9.5, .094);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(18.5, 19, dbtvalue, 25, 9.85, 10.25, .47);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(18.5, 19, dbtvalue, 30, 10.65, 11, .47);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 19) {
//            if ((dbtvalue == 10)) {
//                outputData = 7.9;
//            }
//            if (dbtvalue == 15) {
//                outputData = 8.65;
//            }
//            if (dbtvalue == 20) {
//                outputData = 9.5;
//            }
//            if (dbtvalue == 25) {
//                outputData = 10.25;
//            }
//            if (dbtvalue == 30) {
//                outputData = 11;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 19) && (dvalue < 19.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(19, 19.5, dbtvalue, 10, 7.9, 8.1, .094);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(19, 19.5, dbtvalue, 15, 8.65, 9.15, .094);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(19, 19.5, dbtvalue, 20, 9.5, 9.8, .094);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(19, 19.5, dbtvalue, 25, 10.25, 10.65, .47);
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(19, 19.5, dbtvalue, 30, 11, 11.5, .47);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 19.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 8.1;
//            }
//            if (dbtvalue == 15) {
//                outputData = 9.15;
//            }
//            if (dbtvalue == 20) {
//                outputData = 9.8;
//            }
//            if (dbtvalue == 25) {
//                outputData = 10.65;
//            }
//            if (dbtvalue == 30) {
//                outputData = 11.5;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 19.5) && (dvalue < 20)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//
//                outputData = diffOutLet(19.5, 20, dbtvalue, 10, 8.1, 8.45, .094);
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(19.5, 20, dbtvalue, 15, 9.15, 9.35, .094);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(19.5, 20, dbtvalue, 20, 9.8, 10.25, .094);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(19.5, 20, dbtvalue, 25, 10.65, 11, .47);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(19.5, 20, dbtvalue, 30, 11.5, 11.95, .47);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 20) {
//            if ((dbtvalue == 10)) {
//                outputData = 8.45;
//            }
//            if (dbtvalue == 15) {
//                outputData = 9.35;
//            }
//            if (dbtvalue == 20) {
//                outputData = 10.25;
//            }
//            if (dbtvalue == 25) {
//                outputData = 11;
//            }
//            if (dbtvalue == 30) {
//                outputData = 11.95;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 20) && (dvalue < 20.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(20, 20.5, dbtvalue, 10, 8.45, 8.65, .094);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(20, 20.5, dbtvalue, 15, 9.35, 9.65, .094);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(20, 20.5, dbtvalue, 20, 10.25, 10.65, .094);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(20, 20.5, dbtvalue, 25, 11, 11.45, .47);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(20, 20.5, dbtvalue, 30, 11.95, 12.35, .47);
//
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 20.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 8.65;
//            }
//            if (dbtvalue == 15) {
//                outputData = 9.68;
//            }
//            if (dbtvalue == 20) {
//                outputData = 10.68;
//            }
//            if (dbtvalue == 25) {
//                outputData = 11.45;
//            }
//            if (dbtvalue == 30) {
//                outputData = 12.35;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 20.5) && (dvalue < 21)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(20.5, 21, dbtvalue, 10, 8.65, 9, .094);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(20.5, 21, dbtvalue, 15, 9.68, 10, .094);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(20.5, 21, dbtvalue, 20, 10.65, 11, .094);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(20.5, 21, dbtvalue, 25, 11.45, 11.85, .47);
//
//
//            }
//            if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(20.5, 21, dbtvalue, 30, 12.35, 12.65, .47);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 21) {
//            if ((dbtvalue == 10)) {
//                outputData = 9;
//            }
//            if (dbtvalue == 15) {
//                outputData = 10;
//            }
//            if (dbtvalue == 20) {
//                outputData = 11;
//            }
//            if (dbtvalue == 25) {
//                outputData = 11.85;
//            }
//            if (dbtvalue == 30) {
//                outputData = 12.65;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 21) && (dvalue < 21.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(21, 21.5, dbtvalue, 10, 9, 9.35, .094);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(21, 21.5, dbtvalue, 15, 10, 10.45, .094);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(21, 21.5, dbtvalue, 20, 11, 11.33, .094);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(21, 21.5, dbtvalue, 25, 11.85, 12.25, .47);
//
//            }
//            if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(21, 21.5, dbtvalue, 30, 12.65, 13.25, .47);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 21.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 9.35;
//            }
//            if (dbtvalue == 15) {
//                outputData = 10.45;
//            }
//            if (dbtvalue == 20) {
//                outputData = 11.33;
//            }
//            if (dbtvalue == 25) {
//                outputData = 12.25;
//            }
//            if (dbtvalue == 30) {
//                outputData = 13.25;
//            }
//        }
//
//        // B/W two value....
//
//        else if ((dvalue > 21.5) && (dvalue < 22)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(21.5, 22, dbtvalue, 10, 9.35, 9.65, .094);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//
//                outputData = diffOutLet(21.5, 22, dbtvalue, 15, 10.45, 10.75, .094);
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(21.5, 22, dbtvalue, 20, 11.33, 11.65, .094);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(21.5, 22, dbtvalue, 25, 12.25, 12.5, .47);
//
//
//            }
//            if (dbtvalue >= 30) {
//
//                outputData = diffOutLet(21.5, 22, dbtvalue, 30, 13.25, 13.5, .47);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 22) {
//            if ((dbtvalue == 10)) {
//                outputData = 9.65;
//            } else if (dbtvalue == 15) {
//                outputData = 10.75;
//            } else if (dbtvalue == 20) {
//                outputData = 11.65;
//            } else if (dbtvalue == 25) {
//                outputData = 12.5;
//            } else if (dbtvalue == 30) {
//                outputData = 13.5;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 22) && (dvalue < 22.5)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(22, 22.5, dbtvalue, 10, 9.65, 10, .094);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(22, 22.5, dbtvalue, 15, 10.75, 11.2, .094);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//                outputData = diffOutLet(22, 22.5, dbtvalue, 20, 11.65, 12.15, .094);
//
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//                outputData = diffOutLet(22, 22.5, dbtvalue, 25, 12.5, 13, .47);
//
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(22, 22.5, dbtvalue, 30, 13.5, 13.95, .47);
//
//
//            }
//        }
//
//        // End B/W two value
//
//        else if (dvalue == 22.5) {
//            if ((dbtvalue == 10)) {
//                outputData = 10;
//            } else if (dbtvalue == 15) {
//                outputData = 11.2;
//            } else if (dbtvalue == 20) {
//                outputData = 12.15;
//            } else if (dbtvalue == 25) {
//                outputData = 13;
//            } else if (dbtvalue == 30) {
//                outputData = 13.95;
//            }
//        }
//
//
//        // B/W two value....
//
//        else if ((dvalue > 22.5) && (dvalue < 23)) {
//
//            if (dbtvalue >= 10 && dbtvalue < 15) {
//                outputData = diffOutLet(22.5, 23, dbtvalue, 10, 10, 10.25, .094);
//
//
//            } else if (dbtvalue >= 15 && dbtvalue < 20) {
//                outputData = diffOutLet(22.5, 23, dbtvalue, 15, 11.2, 11.4, .094);
//
//
//            }
//
//            if (dbtvalue >= 20 && dbtvalue < 25) {
//
//                outputData = diffOutLet(22.5, 23, dbtvalue, 20, 12.15, 12.45, .094);
//
//            }
//            if (dbtvalue >= 25 && dbtvalue < 30) {
//
//                outputData = diffOutLet(22.5, 23, dbtvalue, 25, 13, 13.1, .47);
//
//            }
//            if (dbtvalue >= 30) {
//                outputData = diffOutLet(22.5, 23, dbtvalue, 30, 13.95, 14.4, .47);
//
//            }
//        }
//
//        // End B/W two value
//
//
//        else if (dvalue == 23) {
//            if ((dbtvalue == 10)) {
//                outputData = 10.25;
//            } else if (dbtvalue == 15) {
//                outputData = 11.4;
//            } else if (dbtvalue == 20) {
//                outputData = 12.45;
//            } else if (dbtvalue == 25) {
//                outputData = 13.1;
//            } else if (dbtvalue == 30) {
//                outputData = 14.4;
//            }
//        }
//
//
//        return outputData;
//    }


    private double diffOutLet(double topd, double bottomd, double dbtdiff, double dbtvalue, double topt, double bottomt, double diffvalue) {
        double topD = topd;
        double bottomD = bottomd;

        double varDelta = dbtvalue - dbtdiff;
        double diffDValue = bottomD - topD;

        double topT = topt + varDelta * diffvalue;
        double bottomT = bottomt + varDelta * diffvalue;

        double diffTValue = bottomT - topT;

        double vDiv = diffTValue / diffDValue;

        double outputData = vDiv * diffDValue + topT;

        return outputData;
    }


    // outLeat mixed data
    private double getOutLetMixed(TextView grValue, EditText designDbt) {
        String deltavalue = "0.0";
        String dbtValue = "0.0";
        double outputData = 0.0;
        if ((designDbt.getText().toString() == "0.0") || designDbt.getText().equals("") || designDbt.getText().toString().length() == 0) {
            designDbt.setText("0.0");
            deltavalue = grValue.getText().toString();
            dbtValue = designDbt.getText().toString();
            Log.w("if data", "if outlet" + dbtValue);
        } else {
            deltavalue = grValue.getText().toString();
            dbtValue = designDbt.getText().toString();
            Log.w("else data", "else outlet" + dbtValue);
        }

        double dvalue = (Double.parseDouble(deltavalue) / 7);

        double dbtvalue = (Double.parseDouble(dbtValue) - 32) * 5 / 9;

       /* Toast.makeText(InputActivity.this,"delta value is"+dvalue+"dbt value is"+dbtvalue,Toast.LENGTH_SHORT).show();*/
       /* Toast.makeText(InputActivity.this,"outLetMixed data = "+outletMixedData+"out is= "+outletData,Toast.LENGTH_SHORT).show();*/
        Log.w("delta value is ", ">>" + dvalue);

        Log.w("dbt value is ", ">>" + dbtvalue);
        if (dvalue < 1 && (dbtvalue == 10 || dbtvalue == 15 || dbtvalue == 20 || dbtvalue == 25 || dbtvalue == 30)) {

            outputData = R.string.delta1_0_10;
        } else if (dvalue == 1.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_1_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_1_10 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_1_15;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_1_15 + varDelta * 0.88;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_1_20;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_1_20 + varDelta * 0.88;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_1_25;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_1_25 + varDelta * 0.88;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_1_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 1) && (dvalue < 1.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 1;
                double bottomD = 1.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1_10;
                double bottomT = R.string.delta1_1s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_1_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 1;
                double bottomD = 1.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1_15;
                double bottomT = R.string.delta1_1s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_1_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 1;
                double bottomD = 1.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1_20;
                double bottomT = R.string.delta1_1s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_1_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 1;
                double bottomD = 1.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1_25;
                double bottomT = R.string.delta1_1s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                double fValue = vDiv * diffDValue + R.string.delta1_1_25;
            }
            if (dbtvalue >= 30) {
                double topD = 1;
                double bottomD = 1.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1_30;
                double bottomT = R.string.delta1_1s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_1_30;
            }
        }

        // End B/W two value


        else if (dvalue == 1.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_1s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_1s_10 + varDelta * 0.88;

            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_1s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_1s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_1s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {

                outputData = R.string.delta1_1s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_1s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_1s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_1s_30;
            }
        }
        // B/W two value....

        else if ((dvalue > 1.5) && (dvalue < 2)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 1.5;
                double bottomD = 2;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1s_10;
                double bottomT = R.string.delta1_2_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_1s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 1.5;
                double bottomD = 2;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1s_15;
                double bottomT = R.string.delta1_2_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_1s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 1.5;
                double bottomD = 2;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1s_20;
                double bottomT = R.string.delta1_2_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_1s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 1.5;
                double bottomD = 2;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1s_25;
                double bottomT = R.string.delta1_2_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_1s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 1.5;
                double bottomD = 2;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_1s_30;
                double bottomT = R.string.delta1_2_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_1s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 2.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_2_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_2_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_2_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_2_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_2_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_2_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_2_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_2_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_2_30;
            }

        }
        // B/W two value....

        else if ((dvalue > 2) && (dvalue < 2.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 2;
                double bottomD = 2.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2_10;
                double bottomT = R.string.delta1_2s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_2_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 2;
                double bottomD = 2.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2_15;
                double bottomT = R.string.delta1_2s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_2_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 2;
                double bottomD = 2.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2_20;
                double bottomT = R.string.delta1_2s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_2_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 2;
                double bottomD = 2.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2_25;
                double bottomT = R.string.delta1_2s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_2_25;
            }
            if (dbtvalue >= 30) {
                double topD = 2;
                double bottomD = 2.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2_30;
                double bottomT = R.string.delta1_2s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_2_30;
            }
        }

        // End B/W two value


        else if (dvalue == 2.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_2s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_2s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_2s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_2s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_2s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_2s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_2s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_2s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_2s_30;
            }
        }
        // B/W two value....

        else if ((dvalue > 2.5) && (dvalue < 3)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 2.5;
                double bottomD = 3;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2s_10;
                double bottomT = R.string.delta1_3_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_2s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 2.5;
                double bottomD = 3;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2s_15;
                double bottomT = R.string.delta1_3_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 2.5;
                double bottomD = 3;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2s_20;
                double bottomT = R.string.delta1_3_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_2s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 2.5;
                double bottomD = 3;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2s_25;
                double bottomT = R.string.delta1_3_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_2s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 2.5;
                double bottomD = 3;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_2s_30;
                double bottomT = R.string.delta1_3_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_2s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 3.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_3_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_3_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_3_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_3_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_3_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_3_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_3_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_3_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_3_30;
            }
        }
        // B/W two value....

        else if ((dvalue > 3) && (dvalue < 3.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 3;
                double bottomD = 3.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3_10;
                double bottomT = R.string.delta1_3s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 3;
                double bottomD = 3.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3_15;
                double bottomT = R.string.delta1_3s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 3;
                double bottomD = 3.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3_20;
                double bottomT = R.string.delta1_3s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 3;
                double bottomD = 3.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3_25;
                double bottomT = R.string.delta1_3s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3_25;
            }
            if (dbtvalue >= 30) {
                double topD = 3;
                double bottomD = 3.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3_30;
                double bottomT = R.string.delta1_3s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3_30;
            }
        }

        // End B/W two value


        else if (dvalue == 3.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_3s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_3s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_3s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_3s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_3s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_3s_15;
                ;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_3s_20;
                ;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_3s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_3s_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 3.5) && (dvalue < 4)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 3.5;
                double bottomD = 4;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3s_10;
                double bottomT = R.string.delta1_4_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 3.5;
                double bottomD = 4;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3s_15;
                double bottomT = R.string.delta1_4_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 3.5;
                double bottomD = 4;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3s_20;
                double bottomT = R.string.delta1_4_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 3.5;
                double bottomD = 4;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3s_25;
                double bottomT = R.string.delta1_4_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 3.5;
                double bottomD = 4;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_3s_30;
                double bottomT = R.string.delta1_4_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_3s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 4.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_4_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_4_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_4_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_4_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_4_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_4_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_4_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_4_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_4_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 4) && (dvalue < 4.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 4;
                double bottomD = 4.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4_10;
                double bottomT = R.string.delta1_4s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_4_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 4;
                double bottomD = 4.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4_15;
                double bottomT = R.string.delta1_4s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_4_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 4;
                double bottomD = 4.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4_20;
                double bottomT = R.string.delta1_4s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_4_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 4;
                double bottomD = 4.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4_25;
                double bottomT = R.string.delta1_4s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_4_25;
            }
            if (dbtvalue >= 30) {
                double topD = 4;
                double bottomD = 4.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4_30;
                double bottomT = R.string.delta1_4s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_4_30;
            }
        }

        // End B/W two value


        else if (dvalue == 4.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_4s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_4s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_4s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_4s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_4s_25 + varDelta * 0.88;
            }

            if (dbtvalue == 15) {
                outputData = R.string.delta1_4s_15;
            }
            if (dbtvalue == 20) {
                outputData = R.string.delta1_4s_20;
            }
            if (dbtvalue == 25) {
                outputData = R.string.delta1_4s_25;
            }
            if (dbtvalue == 30) {
                outputData = R.string.delta1_4s_30;
            }
        }


        // B/W two value....

        else if ((dvalue > 4.5) && (dvalue < 5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 4.5;
                double bottomD = 5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4s_10;
                double bottomT = R.string.delta1_5_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_4s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 4.5;
                double bottomD = 5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4s_15;
                double bottomT = R.string.delta1_5_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 4.5;
                double bottomD = 5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4s_20;
                double bottomT = R.string.delta1_5_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_4s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 4.5;
                double bottomD = 5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4s_25;
                double bottomT = R.string.delta1_5_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_4s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 4.5;
                double bottomD = 5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_4s_30;
                double bottomT = R.string.delta1_5_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_4s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 5.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_5_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_5_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_5_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_5_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_5_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_5_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_5_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_5_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_5_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 5) && (dvalue < 5.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 5;
                double bottomD = 5.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5_10;
                double bottomT = R.string.delta1_5s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 4.5;
                double bottomD = 5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5_15;
                double bottomT = R.string.delta1_5s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 4.5;
                double bottomD = 5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5_20;
                double bottomT = R.string.delta1_5s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 4.5;
                double bottomD = 5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5_25;
                double bottomT = R.string.delta1_5s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5_25;
            }
            if (dbtvalue >= 30) {
                double topD = 4.5;
                double bottomD = 5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5_30;
                double bottomT = R.string.delta1_5s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5_30;
            }
        }

        // End B/W two value


        else if (dvalue == 5.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_5s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_5_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_5_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_5_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_5_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_5s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_5s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_5s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_5s_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 5.5) && (dvalue < 6)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 5.5;
                double bottomD = 6;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5s_10;
                double bottomT = R.string.delta1_6_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 5.5;
                double bottomD = 6;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5s_15;
                double bottomT = R.string.delta1_6_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 5.5;
                double bottomD = 6;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5s_20;
                double bottomT = R.string.delta1_6_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 5.5;
                double bottomD = 6;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5s_25;
                double bottomT = R.string.delta1_6_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 5.5;
                double bottomD = 6;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_5s_30;
                double bottomT = R.string.delta1_6_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_5s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 6.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_6_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_6_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_6_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_6_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_6_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_6_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_6_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_6_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_6_30;
            }
        }


        // B/W two value....

        else if ((dvalue > 6) && (dvalue < 6.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 6;
                double bottomD = 6.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6_10;
                double bottomT = R.string.delta1_6s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 6;
                double bottomD = 6.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6_15;
                double bottomT = R.string.delta1_6s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 6;
                double bottomD = 6.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6_20;
                double bottomT = R.string.delta1_6s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 6;
                double bottomD = 6.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6_25;
                double bottomT = R.string.delta1_6s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6_25;
            }
            if (dbtvalue >= 30) {
                double topD = 6;
                double bottomD = 6.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6_30;
                double bottomT = R.string.delta1_6s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6_30;
            }
        }

        // End B/W two value


        else if (dvalue == 6.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_6s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_6s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_6s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_6s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_6s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_6s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_6s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_6s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_6s_30;
            }
        }


        // B/W two value....

        else if ((dvalue > 6.5) && (dvalue < 7)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 6.5;
                double bottomD = 7;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6s_10;
                double bottomT = R.string.delta1_7_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 6.5;
                double bottomD = 7;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6s_15;
                double bottomT = R.string.delta1_7_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 6.5;
                double bottomD = 7;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6s_20;
                double bottomT = R.string.delta1_7_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 6.5;
                double bottomD = 7;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6s_25;
                double bottomT = R.string.delta1_7_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6_25;
            }
            if (dbtvalue >= 30) {
                double topD = 6.5;
                double bottomD = 7;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_6s_30;
                double bottomT = R.string.delta1_7_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_6s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 7.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_7_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_7_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_7_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_7_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_7_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_7_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_7_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_7_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_7_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 7) && (dvalue < 7.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 7;
                double bottomD = 7.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7_10;
                double bottomT = R.string.delta1_7s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 7;
                double bottomD = 7.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7_15;
                double bottomT = R.string.delta1_7s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 7;
                double bottomD = 7.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7_20;
                double bottomT = R.string.delta1_7s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 7;
                double bottomD = 7.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7_25;
                double bottomT = R.string.delta1_7s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7_25;
            }
            if (dbtvalue >= 30) {
                double topD = 7;
                double bottomD = 7.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7_30;
                double bottomT = R.string.delta1_7s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7_30;
            }
        }

        // End B/W two value


        else if (dvalue == 7.5) {
            if ((dbtvalue == 10)) {

                outputData = R.string.delta1_7s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_7s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_7s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_7s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_7s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_7s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_7s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_7s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_7s_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 7.5) && (dvalue < 8)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 7.5;
                double bottomD = 8;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7s_10;
                double bottomT = R.string.delta1_8_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 7.5;
                double bottomD = 8;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7s_15;
                double bottomT = R.string.delta1_8_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 7.5;
                double bottomD = 8;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7s_20;
                double bottomT = R.string.delta1_8_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 7.5;
                double bottomD = 8;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7s_25;
                double bottomT = R.string.delta1_8_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 7.5;
                double bottomD = 8;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_7s_30;
                double bottomT = R.string.delta1_8_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_7s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 8.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_8_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_8_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_8_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_8_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_8_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_8_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_8_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_8_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_8_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 8) && (dvalue < 8.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 8;
                double bottomD = 8.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8_10;
                double bottomT = R.string.delta1_8s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 8;
                double bottomD = 8.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8_15;
                double bottomT = R.string.delta1_8s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 8;
                double bottomD = 8.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8_20;
                double bottomT = R.string.delta1_8s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 8;
                double bottomD = 8.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8_25;
                double bottomT = R.string.delta1_8s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8_25;
            }
            if (dbtvalue >= 30) {
                double topD = 8;
                double bottomD = 8.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8_30;
                double bottomT = R.string.delta1_8s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8_30;
            }
        }

        // End B/W two value


        else if (dvalue == 8.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_8s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_8s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_8s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_8s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_8s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_8s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_8s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_8s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_8s_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 8.5) && (dvalue < 9)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 8.5;
                double bottomD = 9;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8s_10;
                double bottomT = R.string.delta1_9_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 8.5;
                double bottomD = 9;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8s_15;
                double bottomT = R.string.delta1_9_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 8.5;
                double bottomD = 9;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8s_20;
                double bottomT = R.string.delta1_9_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 8.5;
                double bottomD = 9;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8s_25;
                double bottomT = R.string.delta1_9_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 8.5;
                double bottomD = 9;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_8s_30;
                double bottomT = R.string.delta1_9_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_8s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 9.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_9_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_9_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_9_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_9_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_9_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_9_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_9_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_9_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_9_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 9) && (dvalue < 9.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 9;
                double bottomD = 9.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9_10;
                double bottomT = R.string.delta1_9s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 9;
                double bottomD = 9.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9_15;
                double bottomT = R.string.delta1_9s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 9;
                double bottomD = 9.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9_20;
                double bottomT = R.string.delta1_9s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 9;
                double bottomD = 9.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9_25;
                double bottomT = R.string.delta1_9s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9_25;
            }
            if (dbtvalue >= 30) {
                double topD = 9;
                double bottomD = 9.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9_30;
                double bottomT = R.string.delta1_9s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9_30;
            }
        }

        // End B/W two value


        else if (dvalue == 9.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_9s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_9s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_9s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_9s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_9s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_9s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_9s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_9s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_9s_30;
            }
        }


        // B/W two value....

        else if ((dvalue > 9.5) && (dvalue < 10)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 9.5;
                double bottomD = 10;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9s_10;
                double bottomT = R.string.delta1_10_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 9.5;
                double bottomD = 10;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9s_15;
                double bottomT = R.string.delta1_10_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 9.5;
                double bottomD = 10;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9s_20;
                double bottomT = R.string.delta1_10_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 9.5;
                double bottomD = 10;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9s_25;
                double bottomT = R.string.delta1_10_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 9.5;
                double bottomD = 10;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_9s_30;
                double bottomT = R.string.delta1_10_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_9s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 10.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_10_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_10_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_10_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_10_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_10_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_10_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_10_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_10_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_10_30;
            }
        }

        // B/W two value....

        else if ((dvalue > 10) && (dvalue < 10.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 10;
                double bottomD = 10.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10_10;
                double bottomT = R.string.delta1_10s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 9.5;
                double bottomD = 10;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10_15;
                double bottomT = R.string.delta1_10s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 9.5;
                double bottomD = 10;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10_20;
                double bottomT = R.string.delta1_10s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 9.5;
                double bottomD = 10;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10_25;
                double bottomT = R.string.delta1_10s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10_25;
            }
            if (dbtvalue >= 30) {
                double topD = 9.5;
                double bottomD = 10;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10_30;
                double bottomT = R.string.delta1_10s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10_30;
            }
        }

        // End B/W two value


        else if (dvalue == 10.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_10s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_10s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_10s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_10s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_10s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_10s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_10s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_10s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_10s_30;
            }
        }


        // B/W two value....

        else if ((dvalue > 10.5) && (dvalue < 11)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 10.5;
                double bottomD = 11;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10s_10;
                double bottomT = R.string.delta1_11_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 10.5;
                double bottomD = 11;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10s_15;
                double bottomT = R.string.delta1_11_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 10.5;
                double bottomD = 11;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10s_20;
                double bottomT = R.string.delta1_11_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 10.5;
                double bottomD = 11;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10s_25;
                double bottomT = R.string.delta1_11_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 10.5;
                double bottomD = 11;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_10s_30;
                double bottomT = R.string.delta1_11_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_10s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 11.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_11_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_11_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_11_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_11_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_11_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_11_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_11_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_11_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_11_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 11) && (dvalue < 11.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 11;
                double bottomD = 11.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11_10;
                double bottomT = R.string.delta1_11s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 11;
                double bottomD = 11.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11_15;
                double bottomT = R.string.delta1_11s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 11;
                double bottomD = 11.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11_20;
                double bottomT = R.string.delta1_11s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 11;
                double bottomD = 11.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11_25;
                double bottomT = R.string.delta1_11s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11_25;
            }
            if (dbtvalue >= 30) {
                double topD = 11;
                double bottomD = 11.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11_30;
                double bottomT = R.string.delta1_11s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11_30;
            }
        }

        // End B/W two value


        else if (dvalue == 11.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_11s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_11s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_11s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_11s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_11s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_11s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_11s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_11s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_11s_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 11.5) && (dvalue < 12)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 11.5;
                double bottomD = 12;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11s_10;
                double bottomT = R.string.delta1_12_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 11.5;
                double bottomD = 12;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11s_15;
                double bottomT = R.string.delta1_12_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 11.5;
                double bottomD = 12;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11s_20;
                double bottomT = R.string.delta1_12_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 11.5;
                double bottomD = 12;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11s_25;
                double bottomT = R.string.delta1_12_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 11.5;
                double bottomD = 12;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_11s_30;
                double bottomT = R.string.delta1_12_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_11s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 12.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_12_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_12_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_12_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_12_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_12_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_12_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_12_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_12_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_12_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 12) && (dvalue < 12.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 12;
                double bottomD = 12.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12_10;
                double bottomT = R.string.delta1_12s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 12;
                double bottomD = 12.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12_15;
                double bottomT = R.string.delta1_12s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 12;
                double bottomD = 12.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12_20;
                double bottomT = R.string.delta1_12s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 12;
                double bottomD = 12.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12_25;
                double bottomT = R.string.delta1_12s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12_25;
            }
            if (dbtvalue >= 30) {
                double topD = 12;
                double bottomD = 12.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12_30;
                double bottomT = R.string.delta1_12s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12_30;
            }
        }

        // End B/W two value


        else if (dvalue == 12.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_12s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_12s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_12s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_12s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_12s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_12s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_12s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_12s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_12s_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 12.5) && (dvalue < 13)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 12.5;
                double bottomD = 13;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12s_10;
                double bottomT = R.string.delta1_13_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 12.5;
                double bottomD = 13;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12s_15;
                double bottomT = R.string.delta1_13_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 12.5;
                double bottomD = 13;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12s_20;
                double bottomT = R.string.delta1_13_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 12.5;
                double bottomD = 13;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12s_25;
                double bottomT = R.string.delta1_13_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 12.5;
                double bottomD = 13;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_12s_30;
                double bottomT = R.string.delta1_13_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_12s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 13.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_13_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_13_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_13_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_13_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_13_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_13_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_13_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta_13_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_13_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 13) && (dvalue < 13.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 13;
                double bottomD = 13.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13_10;
                double bottomT = R.string.delta1_13s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 13;
                double bottomD = 13.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13_15;
                double bottomT = R.string.delta1_13s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 13;
                double bottomD = 13.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13_20;
                double bottomT = R.string.delta1_13s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 13;
                double bottomD = 13.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13_25;
                double bottomT = R.string.delta1_13s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13_25;
            }
            if (dbtvalue >= 30) {
                double topD = 13;
                double bottomD = 13.5;
                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13_30;
                double bottomT = R.string.delta1_13s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13_30;
            }
        }

        // End B/W two value


        else if (dvalue == 13.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_13s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_13s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_13s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_13s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_13s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_13s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_13s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_13s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_13s_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 13.5) && (dvalue < 14)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 13.5;
                double bottomD = 14;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13s_10;
                double bottomT = R.string.delta1_14_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 13.5;
                double bottomD = 14;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13s_15;
                double bottomT = R.string.delta1_14_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 13.5;
                double bottomD = 14;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13s_20;
                double bottomT = R.string.delta1_14_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 13.5;
                double bottomD = 14;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13s_25;
                double bottomT = R.string.delta1_14_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 13.5;
                double bottomD = 14;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_13s_30;
                double bottomT = R.string.delta1_14_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_13s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 14.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_14_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_14_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_14_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_14_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_14_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_14_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_14_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_14_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_14_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 14) && (dvalue < 14.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 14;
                double bottomD = 14.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14_10;
                double bottomT = R.string.delta1_14s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 14;
                double bottomD = 14.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14_15;
                double bottomT = R.string.delta1_14s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 14;
                double bottomD = 14.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14_20;
                double bottomT = R.string.delta1_14s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 14;
                double bottomD = 14.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14_25;
                double bottomT = R.string.delta1_14s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14_25;
            }
            if (dbtvalue >= 30) {
                double topD = 14;
                double bottomD = 14.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14_30;
                double bottomT = R.string.delta1_14s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14_30;
            }
        }

        // End B/W two value


        else if (dvalue == 14.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_14s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_14s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_14s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_14s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_14s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_14s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_14s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_14s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_14s_30;
            }
        }


        // B/W two value....

        else if ((dvalue < 14.5) && (dvalue < 15)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 14.5;
                double bottomD = 15;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14s_10;
                double bottomT = R.string.delta1_15_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 14.5;
                double bottomD = 15;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14s_15;
                double bottomT = R.string.delta1_15_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 14.5;
                double bottomD = 15;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14s_20;
                double bottomT = R.string.delta1_15_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 14.5;
                double bottomD = 15;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14s_25;
                double bottomT = R.string.delta1_15_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 14.5;
                double bottomD = 15;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_14s_30;
                double bottomT = R.string.delta1_15_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_14s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 15.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_15_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_15_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_15_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_15_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_15_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_15_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_15_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_15_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_15_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 15) && (dvalue < 15.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 15;
                double bottomD = 15.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15_10;
                double bottomT = R.string.delta1_15s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 15;
                double bottomD = 15.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15_15;
                double bottomT = R.string.delta1_15s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 15;
                double bottomD = 15.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15_20;
                double bottomT = R.string.delta1_15s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 15;
                double bottomD = 15.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15_25;
                double bottomT = R.string.delta1_15s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15_25;
            }
            if (dbtvalue >= 30) {
                double topD = 15;
                double bottomD = 15.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15_30;
                double bottomT = R.string.delta1_15s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15_30;
            }
        }

        // End B/W two value


        else if (dvalue == 15.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_15s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_15s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_15s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_15s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_15s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_15s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_15s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_15s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_15s_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 15.5) && (dvalue < 16)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 15.5;
                double bottomD = 16;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15s_10;
                double bottomT = R.string.delta1_16_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 15.5;
                double bottomD = 16;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15s_15;
                double bottomT = R.string.delta1_16_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 15.5;
                double bottomD = 16;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15s_20;
                double bottomT = R.string.delta1_16_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 15.5;
                double bottomD = 16;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15s_25;
                double bottomT = R.string.delta1_16_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 15.5;
                double bottomD = 16;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_15s_30;
                double bottomT = R.string.delta1_16_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_15s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 16.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_16_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_16_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_16_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_16_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_16_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_16_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_16_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_16_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_16_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 16) && (dvalue < 16.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 16;
                double bottomD = 16.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16_10;
                double bottomT = R.string.delta1_16s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 16;
                double bottomD = 16.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16_15;
                double bottomT = R.string.delta1_16s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 16;
                double bottomD = 16.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16_20;
                double bottomT = R.string.delta1_16s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 16;
                double bottomD = 16.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16_25;
                double bottomT = R.string.delta1_16s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16_25;
            }
            if (dbtvalue >= 30) {
                double topD = 16;
                double bottomD = 16.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16_30;
                double bottomT = R.string.delta1_16s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16_30;
            }
        }

        // End B/W two value


        else if (dvalue == 16.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_16s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_16s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_16s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_16s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_16s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_16s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_16s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_16s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_16s_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 16.5) && (dvalue < 17)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 16.5;
                double bottomD = 17;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16s_10;
                double bottomT = R.string.delta1_17_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 16.5;
                double bottomD = 17;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16s_15;
                double bottomT = R.string.delta1_17_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 16.5;
                double bottomD = 17;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16s_20;
                double bottomT = R.string.delta1_17_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 16.5;
                double bottomD = 17;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16s_25;
                double bottomT = R.string.delta1_17_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 16.5;
                double bottomD = 17;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_16s_30;
                double bottomT = R.string.delta1_17_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_16s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 17.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_17_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_17_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_17_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_17_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_17_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_17_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_17_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_17_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_17_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 17) && (dvalue < 17.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 17;
                double bottomD = 17.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_17_10;
                double bottomT = R.string.delta1_17s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_17_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 17;
                double bottomD = 17.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_17_15;
                double bottomT = R.string.delta1_17s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_17_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 17;
                double bottomD = 17.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_17_20;
                double bottomT = R.string.delta1_17s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_17_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 17;
                double bottomD = 17.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_17_25;
                double bottomT = R.string.delta1_17s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_17_25;
            }
            if (dbtvalue >= 30) {
                double topD = 17;
                double bottomD = 17.5;

                double diffDValue = bottomD - topD;

                double topT = 58;
                double bottomT = 58.5;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 58;
            }
        }

        // End B/W two value


        else if (dvalue == 17.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_17s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_17s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_17s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_17s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_17s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_17s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_17s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_17s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_17s_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 17.5) && (dvalue < 18)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 17.5;
                double bottomD = 18;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_17s_10;
                double bottomT = R.string.delta1_18_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_17s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 17.5;
                double bottomD = 18;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_17s_15;
                double bottomT = R.string.delta1_18_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_17s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 17.5;
                double bottomD = 18;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_17s_20;
                double bottomT = R.string.delta1_18_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_17s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 17.5;
                double bottomD = 18;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_17s_25;
                double bottomT = R.string.delta1_18_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_17s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 17.5;
                double bottomD = 18;

                double diffDValue = bottomD - topD;

                double topT = 58.5;
                double bottomT = 58.75;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 58.5;
            }
        }

        // End B/W two value


        else if (dvalue == 18.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_18_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_18_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_18_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_18_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_18_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_18_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_18_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_18_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_18_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 18) && (dvalue < 18.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 18;
                double bottomD = 18.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18_10;
                double bottomT = R.string.delta1_18s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 18;
                double bottomD = 18.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18_15;
                double bottomT = R.string.delta1_18s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 18;
                double bottomD = 18.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18_20;
                double bottomT = R.string.delta1_18s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 18;
                double bottomD = 18.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18_25;
                double bottomT = R.string.delta1_18s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18_25;
            }
            if (dbtvalue >= 30) {
                double topD = 18;
                double bottomD = 18.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18_30;
                double bottomT = R.string.delta1_18s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18_30;
            }
        }

        // End B/W two value


        else if (dvalue == 18.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_18s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_18s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_18s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_18s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_18s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_18s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_18s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_18s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_18s_30;
            }
        }


        // B/W two value....

        else if ((dvalue < 18.5) && (dvalue < 19)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 18.5;
                double bottomD = 19;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18s_10;
                double bottomT = R.string.delta1_19_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 18.5;
                double bottomD = 19;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18s_15;
                double bottomT = R.string.delta1_19_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 18.5;
                double bottomD = 19;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18s_20;
                double bottomT = R.string.delta1_19_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 18.5;
                double bottomD = 19;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18s_25;
                double bottomT = R.string.delta1_19_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 18.5;
                double bottomD = 19;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_18s_30;
                double bottomT = R.string.delta1_19_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_18s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 19.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_19_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_19_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_19_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_19_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_19_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_19_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_19_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_19_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_19_30;
            }
        }


        // B/W two value....

        else if ((dvalue < 19) && (dvalue < 19.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 19;
                double bottomD = 19.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19_10;
                double bottomT = R.string.delta1_19s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 19;
                double bottomD = 19.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19_15;
                double bottomT = R.string.delta1_19s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 19;
                double bottomD = 19.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19_20;
                double bottomT = R.string.delta1_19s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 19;
                double bottomD = 19.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19_25;
                double bottomT = R.string.delta1_19s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19_25;
            }
            if (dbtvalue >= 30) {
                double topD = 19;
                double bottomD = 19.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19_30;
                double bottomT = R.string.delta1_19s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19_30;
            }
        }

        // End B/W two value


        else if (dvalue == 19.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_19s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_19s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_19s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_19s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_19s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_19s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_19s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_19s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_19s_30;
            }
        }


        // B/W two value....

        else if ((dvalue < 19.5) && (dvalue < 20)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 19.5;
                double bottomD = 20;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19s_10;
                double bottomT = R.string.delta1_20_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 19.5;
                double bottomD = 20;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19s_15;
                double bottomT = R.string.delta1_20_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 19.5;
                double bottomD = 20;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19s_20;
                double bottomT = R.string.delta1_20_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 19.5;
                double bottomD = 20;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19s_25;
                double bottomT = R.string.delta1_20_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 19.5;
                double bottomD = 20;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_19s_30;
                double bottomT = R.string.delta1_20_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_19s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 20.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_20_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_20_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_20_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_20_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_20_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_20_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_20_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_20_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_20_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 20) && (dvalue < 20.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 20;
                double bottomD = 20.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_20_10;
                double bottomT = R.string.delta1_20s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_20_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 20;
                double bottomD = 20.5;

                double diffDValue = bottomD - topD;

                double topT = 0.0;
                double bottomT = 0.0;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 0.0;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 20;
                double bottomD = 20.5;

                double diffDValue = bottomD - topD;

                double topT = 0.0;
                double bottomT = 0.0;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 0.0;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 20;
                double bottomD = 20.5;

                double diffDValue = bottomD - topD;

                double topT = 56.87;
                double bottomT = 0.0;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 56.87;
            }
            if (dbtvalue >= 30) {
                double topD = 20;
                double bottomD = 20.5;

                double diffDValue = bottomD - topD;

                double topT = 59.5;
                double bottomT = 59.6;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 59.5;
            }
        }

        // End B/W two value


        else if (dvalue == 20.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_20s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_20s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_20s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_20s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_20s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_20s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_20s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_20s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_20s_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 20.5) && (dvalue < 21)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 20.5;
                double bottomD = 21;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_20s_10;
                double bottomT = R.string.delta1_21_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_20s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 20.5;
                double bottomD = 21;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_20s_15;
                double bottomT = R.string.delta1_21_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_20s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 20.5;
                double bottomD = 21;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_20s_20;
                double bottomT = R.string.delta1_21_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_20s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 20.5;
                double bottomD = 21;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_20s_25;
                double bottomT = R.string.delta1_21_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_20s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 20.5;
                double bottomD = 21;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_20s_30;
                double bottomT = R.string.delta1_21_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_20s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 21.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_21_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_21_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_21_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_21_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_21_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_21_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_21_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_21_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_21_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 21) && (dvalue < 21.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 21;
                double bottomD = 21.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21_10;
                double bottomT = R.string.delta1_21s_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 21;
                double bottomD = 21.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21_15;
                double bottomT = R.string.delta1_21s_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 21;
                double bottomD = 21.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21_20;
                double bottomT = R.string.delta1_21s_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 21;
                double bottomD = 21.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21_25;
                double bottomT = R.string.delta1_21s_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21_25;
            }
            if (dbtvalue >= 30) {
                double topD = 21;
                double bottomD = 21.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21_30;
                double bottomT = R.string.delta1_21s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21_30;
            }
        }

        // End B/W two value


        else if (dvalue == 21.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_21s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_21s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_21s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_21s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_21s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_21s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_21s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_21s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_21s_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 21.5) && (dvalue < 22)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 21.5;
                double bottomD = 22;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21s_10;
                double bottomT = R.string.delta1_22_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 21.5;
                double bottomD = 22;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21s_15;
                double bottomT = R.string.delta1_22_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 21.5;
                double bottomD = 22;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21s_20;
                double bottomT = R.string.delta1_22_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 21.5;
                double bottomD = 22;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21s_25;
                double bottomT = R.string.delta1_22_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 21.5;
                double bottomD = 22;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_21s_30;
                double bottomT = R.string.delta1_22_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_21s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 22.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_22_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_22_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_22_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_22_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_22_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_22_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_22_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_22_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_22_30;
            }
        }


        // B/W two value....

        else if ((dvalue < 22) && (dvalue < 22.5)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 22;
                double bottomD = 22.5;

                double diffDValue = bottomD - topD;

                double topT = 0.0;
                double bottomT = 0.0;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 0.0;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 22;
                double bottomD = 22.5;

                double diffDValue = bottomD - topD;

                double topT = 0.0;
                double bottomT = 0.0;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 0.0;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 22;
                double bottomD = 22.5;

                double diffDValue = bottomD - topD;

                double topT = 0.0;
                double bottomT = 0.0;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_22_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 22;
                double bottomD = 22.5;

                double diffDValue = bottomD - topD;

                double topT = 0.0;
                double bottomT = 0.0;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 0.0;
            }
            if (dbtvalue >= 30) {
                double topD = 22;
                double bottomD = 22.5;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_22_30;
                double bottomT = R.string.delta1_22s_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + 6;
            }
        }

        // End B/W two value


        else if (dvalue == 22.5) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_22s_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_22s_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_22s_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_22s_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_22s_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_22s_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_22s_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_22s_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_22s_30;
            }
        }

        // B/W two value....

        else if ((dvalue < 22.5) && (dvalue < 23)) {

            if (dbtvalue >= 10 && dbtvalue < 15) {
                double topD = 22.5;
                double bottomD = 23;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_22s_10;
                double bottomT = R.string.delta1_23_10;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_22s_10;
            } else if (dbtvalue >= 15 && dbtvalue < 20) {
                double topD = 22.5;
                double bottomD = 23;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_22s_15;
                double bottomT = R.string.delta1_23_15;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_22s_15;
            }

            if (dbtvalue >= 20 && dbtvalue < 25) {
                double topD = 22.5;
                double bottomD = 23;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_22s_20;
                double bottomT = R.string.delta1_23_20;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_22s_20;
            }
            if (dbtvalue >= 25 && dbtvalue < 30) {
                double topD = 22.5;
                double bottomD = 23;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_22s_25;
                double bottomT = R.string.delta1_23_25;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_22s_25;
            }
            if (dbtvalue >= 30) {
                double topD = 22.5;
                double bottomD = 23;

                double diffDValue = bottomD - topD;

                double topT = R.string.delta1_22s_30;
                double bottomT = R.string.delta1_23_30;

                double diffTValue = bottomT - topT;

                double vDiv = diffTValue / diffDValue;

                outputData = vDiv * diffDValue + R.string.delta1_22s_30;
            }
        }

        // End B/W two value


        else if (dvalue == 23.00) {
            if ((dbtvalue == 10)) {
                outputData = R.string.delta1_23_10;
            } else if (10 < dbtvalue && dbtvalue < 15) {
                double varDelta = dbtvalue - 10;
                outputData = R.string.delta1_23_10 + varDelta * 0.88;
            } else if (15 < dbtvalue && dbtvalue < 20) {
                double varDelta = dbtvalue - 15;
                outputData = R.string.delta1_23_15 + varDelta * 0.88;
            } else if (20 < dbtvalue && dbtvalue < 25) {
                double varDelta = dbtvalue - 20;
                outputData = R.string.delta1_23_20 + varDelta * 0.88;
            } else if (25 < dbtvalue && dbtvalue < 30) {
                double varDelta = dbtvalue - 25;
                outputData = R.string.delta1_23_25 + varDelta * 0.88;
            } else if (dbtvalue == 15) {
                outputData = R.string.delta1_23_15;
            } else if (dbtvalue == 20) {
                outputData = R.string.delta1_23_20;
            } else if (dbtvalue == 25) {
                outputData = R.string.delta1_23_25;
            } else if (dbtvalue == 30) {
                outputData = R.string.delta1_23_30;
            }
        }


        return outputData;
    }


    public void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    //////////////////////////////////////////////////////////////////////////////

    public class SendValueAsync extends AsyncTask<String, JSONObject, JSONObject> {
        Context context;
        String designgr, dbt;
        public double outlet_output;

        public SendValueAsync(Context context, String designgr, String dbt) {
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
            Log.d("gdfgdghfghhfg",dbt);
            Log.d("gdfgdghfghhfg",designgr);
            JSONParser jsonParser = new JSONParser();
            jsonObject = jsonParser.makeHttpRequest(Apis.Out_letApi, "GET", hashMap);


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
                    outlet_output = Double.valueOf(obj.getString("moisture"));
                    //Toast.makeText(context, "" + outlet_output, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                }
                Log.w("else data", "else outlet" + outlet_output);
                Double value_outlet=outlet_output*7;

                BasicInformation.setROutlet(String.format("%.2f", value_outlet));
                printCalculation(outlet_output);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }





    public class SendMixdValueAsync extends AsyncTask<String, JSONObject, JSONObject> {
        Context context;
        String designgr, dbt;
        public double outlet_output;
        private ProgressDialog dialog;


        public SendMixdValueAsync(Context context, String designgr, String dbt) {

            this.context = context;
            this.designgr = designgr;
            this.dbt = dbt;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(InputActivity.this);
            dialog.setMessage("Please wait..");
            dialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonObject;
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("temp", dbt);
            hashMap.put("gradient", designgr);
            JSONParser jsonParser = new JSONParser();
            jsonObject = jsonParser.makeHttpRequest(Apis.Out_letApi, "GET", hashMap);

            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject obj) {
            super.onPostExecute(obj);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            double outlet_output = 0;
            try {
                String status = obj.getString("status");
                String msg = obj.getString("msg");


                if (status.equals("1")) {
                    outlet_output = Double.valueOf(obj.getString("moisture"));
                    //Toast.makeText(context, "" + outlet_output, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                }
                Log.w("else data", "else mixed outlet" + outlet_output);

                Double value_outlet=outlet_output*7;

                BasicInformation.setOutlet(String.format("%.2f", value_outlet));
                mixedCfm(outlet_output);
                Intent in = new Intent(InputActivity.this, PrintSheet.class);
                startActivity(in);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void mixedCfm(double outletData) {

        DC_Cfm = (((TOTAL_LATENT / 60) * 14) / (MIXED_GR - (outletData * 7)));

        BasicInformation.setDehumidifier(String.format("%.2f", DC_Cfm));
        String modelNo = getModel(DC_Cfm);
        BasicInformation.setModel(modelNo);
        Log.w("mixdCFM", "value mixed cfm" + DC_Cfm);
    }

    private void getBtu(EditText edtbtu,EditText edtdt,EditText edtadp,TextView txtcfm){
        double btuCfm=0.0;
        if(!edtbtu.getText().toString().isEmpty()&&!edtdt.getText().toString().isEmpty()&&!edtadp.getText().toString().isEmpty()){
            btuCfm=Double.valueOf(edtbtu.getText().toString())/((Double.valueOf(edtdt.getText().toString())-Double.valueOf(edtadp.getText().toString()))*1.08);
            txtcfm.setText(String.format("%.2f",btuCfm));
        }
        else if (!edtbtu.getText().toString().isEmpty()&&!edtdt.getText().toString().isEmpty()){

            btuCfm=Double.valueOf(edtbtu.getText().toString())/((Double.valueOf(edtdt.getText().toString())-55)*1.08);
            txtcfm.setText(String.format("%.2f",btuCfm));
        }

    }


    private void getPPcfm(EditText edtpp,TextView txtppout){
        double ppPa=0.0;
        double RmHeight=0.0;
        double RmWidth=0.0;

        noOfDoor();
        noOfFixedOpning();
        if(!edtpp.getText().toString().isEmpty()){
            ppPa=Double.valueOf(edtpp.getText().toString());
        }
        else {
            ppPa=0.0;
        }

       /* if(!txtThight.getText().toString().isEmpty()){
            RmHeight=Double.valueOf(txtThight.getText().toString());
        }
        else {
            RmHeight=0.0;
        }

        if(!txtTwidth.getText().toString().isEmpty()){
            RmWidth=Double.valueOf(txtTwidth.getText().toString());
        }
        else {
            RmWidth=0.0;
        }*/


        double gapmm=3;
        double gapFeet=gapmm*0.0032808398950131 ;
        double ppMmwg=ppPa/10;

        //double areaOutput=(RmHeight*gapFeet*2)+(RmWidth*gapFeet*2);
        double areaOutput=(doorTotalHeight*gapFeet*2)+(doorTotalWidth*gapFeet*2)+(fixedTotalWidth*fixedTotalHeight);
        double cfmOut=areaOutput*2610* Math.pow((ppMmwg/25.4),0.5);
        txtppout.setText(String.format("%.2f",cfmOut));
        Log.w("pp value is","ppPa "+ppPa + "ppMmwg "+ppMmwg + "RmHeight "+doorTotalHeight + "RmWidth "+doorTotalWidth + "areaOutput " +areaOutput + "cfmOut "+cfmOut);
    }

    ///////////////////////////////////

    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
