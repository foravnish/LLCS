package com.risein.llcs;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
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
import com.risein.llcs.helper.AppController;
import com.risein.llcs.utils.Apis;
import com.risein.llcs.utils.MyPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    Spinner genderSpiner,companyType,industry,contaryListSpiner;
    List<String> gender_cat = new ArrayList<String>();
    List<String> company_cat = new ArrayList<String>();
    List<String> industry_cat = new ArrayList<String>();
    List<String> contaryList_cat = new ArrayList<String>();
    DatePickerDialog datePickerDialog;
    TextView txt_name,txt_nameLast,companyName,desigination,address,mobile,website,txt_email,txt_phone;
    TextView d_o_b;
    Button btn_register;
    String genderValue,comTypeValue,industryValue,conturyValue;
    String dob,phone;
    TextInputLayout industryText1;
    AutoCompleteTextView industryText;
    Boolean flag;
    public ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        genderSpiner=(Spinner)findViewById(R.id.genderSpiner);
        companyType=(Spinner)findViewById(R.id.companyType);
        industry=(Spinner)findViewById(R.id.industry);
        contaryListSpiner=(Spinner)findViewById(R.id.contaryListSpiner);
        industryText1=(TextInputLayout)findViewById(R.id.industryText1);
        industryText=(AutoCompleteTextView)findViewById(R.id.industryText);

        txt_name=(TextView)findViewById(R.id.txt_name);
        txt_nameLast=(TextView)findViewById(R.id.txt_nameLast);
        companyName=(TextView)findViewById(R.id.companyName);
        desigination=(TextView)findViewById(R.id.desigination);
        address=(TextView)findViewById(R.id.address);
        mobile=(TextView)findViewById(R.id.mobile);
        website=(TextView)findViewById(R.id.website);
        txt_email=(TextView)findViewById(R.id.txt_email);
        txt_phone=(TextView)findViewById(R.id.txt_phone);
        d_o_b=(TextView)findViewById(R.id.d_o_b);


        progress = new ProgressDialog(Registration.this);
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);


        d_o_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();

                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Registration.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
//                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                d_o_b.setText(dayOfMonth + "/" + (monthOfYear + 1)+ "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btn_register=(Button) findViewById(R.id.btn_register);



        gender_cat.add("Select Gender *");
        gender_cat.add("Male");
        gender_cat.add("Female");
        gender_cat.add("Transgender");
        final ArrayAdapter<String> genderadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender_cat);
        genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpiner.setAdapter(genderadapter);

        company_cat.add("Select Company Type *");
        company_cat.add("Enduser");
        company_cat.add("Architect");
        company_cat.add("Consultant");
        company_cat.add("Contractor");
        company_cat.add("Trader ");
        ArrayAdapter<String> companyadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, company_cat);
        companyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companyType.setAdapter(companyadapter);

        industry_cat.add("Select Industry *");
        industry_cat.add("Pharma");
        industry_cat.add("Glass");
        industry_cat.add("Food");
        industry_cat.add("Electronics");
        industry_cat.add("Aviation ");
        industry_cat.add("other please specify");
        ArrayAdapter<String> industryadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, industry_cat);
        industryadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        industry.setAdapter(industryadapter);


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Apis.GET_fetch_data, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        contaryList_cat.add(jsonObject.optString("c_name"));
                    }
                    ArrayAdapter<String> contaryListSpinerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, contaryList_cat);
                    contaryListSpinerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                    contaryListSpiner.setAdapter(contaryListSpinerAdapter);
                    contaryListSpiner.setSelection(97);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registration.this, "Please connect to the internet...", Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);




        genderSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(Registration.this, ""+genderSpiner.getSelectedItem(), Toast.LENGTH_SHORT).show();
                genderValue=genderSpiner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        companyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(Registration.this, ""+companyType.getSelectedItem(), Toast.LENGTH_SHORT).show();
                comTypeValue=companyType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        industry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(Registration.this, ""+industry.getSelectedItem(), Toast.LENGTH_SHORT).show();
                industryValue=industry.getSelectedItem().toString();
                if (industryValue.equals("other please specify")){
                    industryText1.setVisibility(View.VISIBLE);

                    flag=true;
                }
                else{
                    industryText1.setVisibility(View.GONE);
                    flag=false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        contaryListSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(Registration.this, ""+industry.getSelectedItem(), Toast.LENGTH_SHORT).show();
                conturyValue=contaryListSpiner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (txt_name.getText().toString().equals("")){
                    txt_name.setError("Enter First Name");
                }
                if (txt_nameLast.getText().toString().equals("")){
                    txt_nameLast.setError("Enter Last Name");
                }
                if (d_o_b.getText().toString().equals("")){
                    d_o_b.setError("Enter Date of Birth");
                }
                if (genderSpiner.getSelectedItemPosition()==0){
                    Toast.makeText(Registration.this, "Select gender", Toast.LENGTH_SHORT).show();
                }
                if (companyName.getText().toString().equals("")){
                    companyName.setError("Enter Company Name");
                }
                if (companyType.getSelectedItemPosition()==0){
                    Toast.makeText(Registration.this, "Select company type", Toast.LENGTH_SHORT).show();
                }
                if (desigination.getText().toString().equals("")){
                    desigination.setError("Enter Desigination");
                }
                if (address.getText().toString().equals("")){
                    address.setError("Enter Address");
                }
                if (mobile.getText().toString().equals("")){
                    mobile.setError("Enter Mobile no.");
                }
                    if (mobile.getText().toString().length()!=10){
                        mobile.setError("Enter 10 digit Mobile no.");
                        Toast.makeText(Registration.this, "Enter 10 digit Mobile no.", Toast.LENGTH_SHORT).show();
                    }
                if (txt_email.getText().toString().equals("")){
                    txt_email.setError("Enter Email Id");
                }

                if (isValidEmail(txt_email.getText().toString())==false){
                    txt_email.setError("Enter valid Email");
                    Toast.makeText(Registration.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
                }


                if (genderSpiner.getSelectedItemPosition()==0){
                    Toast.makeText(Registration.this, "Select gender", Toast.LENGTH_SHORT).show();
                }
                if (industry.getSelectedItemPosition()==0){
                    Toast.makeText(Registration.this, "Select Industary", Toast.LENGTH_SHORT).show();
                }
                if (contaryListSpiner.getSelectedItemPosition()==0){
                    Toast.makeText(Registration.this, "Select Contrary", Toast.LENGTH_SHORT).show();
                }


                if (!txt_name.getText().toString().equals("")&& !txt_nameLast.getText().toString().equals("")&& !companyName.getText().toString().equals("")
                        && !desigination.getText().toString().equals("")&& !address.getText().toString().equals("")&& !mobile.getText().toString().equals("")
                        && !txt_email.getText().toString().equals("") && !d_o_b.getText().toString().equals("")
                        && genderSpiner.getSelectedItemPosition()!=0 && companyType.getSelectedItemPosition()!=0
                        && industry.getSelectedItemPosition()!=0 && companyType.getSelectedItemPosition()!=0
                        && isValidEmail(txt_email.getText().toString())==true && mobile.getText().toString().length()==10){
                    saveInformation();
                    progress.show();
//                    Toast.makeText(Registration.this, "now", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void saveInformation() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST ,Apis.GET_Registration,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        Log.d("sfgsdgdsdgdfgs",response);
                        //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");
                            String msg = obj.getString("msg");

                            if (status.equals("1")){
                                Intent sintent = new Intent(Registration.this, LoginActivity.class);
                                sintent.putExtra("valueintent","1");
                                startActivity(sintent);

                                Toast.makeText(Registration.this, "Sent Successfully...", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(Registration.this, msg, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.dismiss();
                        Toast.makeText(Registration.this,"Please check Internet Connection...",Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<String,String>();


                map.put("first_name",txt_name.getText().toString());
                map.put("last_name",txt_nameLast.getText().toString());
                map.put("gender",genderValue);
                map.put("d_o_b",d_o_b.getText().toString());
                map.put("company_name",companyName.getText().toString());
                map.put("company_type",comTypeValue);
                map.put("designation",desigination.getText().toString());
                map.put("office_address",address.getText().toString());
                map.put("phone_no",txt_phone.getText().toString());
                map.put("mobile_no",mobile.getText().toString());
                map.put("email_id",txt_email.getText().toString());
                if (flag==true) {
                    map.put("industry",  industryText.getText().toString());
                    Log.d("dfgdfgdfgdfhgd","true");
                }
                else{
                    map.put("industry",industryValue);
                    Log.d("dfgdfgdfgdfhgd","false");
                }
                map.put("country",conturyValue);
                map.put("website",website.getText().toString());


//                Log.d("first_name",txt_name.getText().toString());
//                Log.d("last_name",txt_nameLast.getText().toString());
//                Log.d("gender",genderValue);
//                Log.d("d_o_b",d_o_b.getText().toString());
//                Log.d("company_name",companyName.getText().toString());
//                Log.d("company_type",comTypeValue);
//                Log.d("designation",desigination.getText().toString());
//                Log.d("office_address",address.getText().toString());
//                Log.d("phone_no",txt_phone.getText().toString());
//                Log.d("email_id",txt_email.getText().toString());
//                Log.d("mobile_no",mobile.getText().toString());
//                Log.d("industry",industryValue);
//                Log.d("country",conturyValue);
//                Log.d("website",website.getText().toString());

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
//          RequestQueue requestQueue = Volley.newRequestQueue(this);
//         requestQueue.add(stringRequest);
    }
}
