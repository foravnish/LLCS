package com.risein.llcs;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
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
import com.risein.llcs.inputvalidation.ValidInput;
import com.risein.llcs.utils.Apis;
import com.risein.llcs.utils.BasicInformation;
import com.risein.llcs.utils.InputInformation;
import com.risein.llcs.utils.MyPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText vDate,vComname,vCname,vPname,vPhone,vAddress,vEmail,vProject,edt_project_loc,vConsultant,vOffer,vArea;
    TextView next;
    BasicInformation binfo;
    private String[] sgender;
    private Spinner selectGender;
    RequestQueue requestQueue;
    String status;
    public ProgressDialog progress;

    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{
             android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String offerCode;
        String sum = new String();

        double n1 = 20.2;
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

        Log.d("gfdgdfhgdfh",offerCode);





//        String offerCode;
//
//        double n1=10.5;
//
//        int n= (int) n1;
////        int n= 10;
//
//        double n2= n1-n;
//
//        String number = String.valueOf(n1);
//        number = number.substring(number.indexOf(".")).substring(1);
//
//
//        int val = 0,reverse = 0;
//        String s = null;
//        s= String.valueOf(n);
//
//        String sum = new String();
//
//
//        while( n>0 )
//        {
//            reverse = reverse * 10;
//            reverse = reverse + n%10;
//            n = n/10;
//        }
//
//        Log.d("fdgdfgdffgfdg", String.valueOf(reverse));
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
//                s="Z";
//                sum=sum+s;
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
//
//        Log.d("gfdgdfhgdfh",offerCode);



        //Adding toolbar to activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Hello");
        setSupportActionBar(toolbar);
        //hideKeyboard();
        checkPermissions();
        inputField();

        sgender = getResources().getStringArray(R.array.s_gender);
        todayDate();

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sgender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectGender.setAdapter(genderAdapter);

       // Toast.makeText(getApplicationContext(), "You are "+ MyPreference.loadRolltype(getApplicationContext()), Toast.LENGTH_SHORT).show();


        if (getIntent().getStringExtra("valueintent").equals("1")){

            vComname.setText("");
            vCname.setText("");
            vPname.setText("");
            vPhone.setText("");
            vAddress.setText("");
            vEmail.setText("");
            vProject.setText("");
            edt_project_loc.setText("");
            vConsultant.setText("");

        }
        else if (getIntent().getStringExtra("valueintent").equals("2")){

            vComname= (EditText) findViewById(R.id.edt_comname);
            vCname= (EditText) findViewById(R.id.edt_cname);
            vPname= (EditText) findViewById(R.id.edt_pname);
            vPhone= (EditText) findViewById(R.id.edt_pnumber);
            vAddress= (EditText) findViewById(R.id.edt_address);
            vEmail= (EditText) findViewById(R.id.edt_email);
            vProject= (EditText) findViewById(R.id.edt_project);
            edt_project_loc= (EditText) findViewById(R.id.edt_project_loc);
            vConsultant= (EditText) findViewById(R.id.edt_consultant);

//            binfo.setCompnyname(inputComnyname);
//            binfo.setCname(inputCname);
//            binfo.setPname(incompname);
//            binfo.setPhone(inputPhone);
//            binfo.setAdd(inputAdd);
//            binfo.setEmail(inputEmail);
//            binfo.setProjectname(inputProject);
//            binfo.setProjectadd(edt_project_loc.getText().toString());
//            binfo.setConsulname(inputConsul);

            vComname.setText(binfo.getCompnyname().toString());
            vCname.setText(binfo.getCname().toString());
            vPname.setText(binfo.getPname().toString());
            vPhone.setText(binfo.getPhone().toString());
            vAddress.setText(binfo.getAdd().toString());
            vEmail.setText(binfo.getEmail().toString());
            vProject.setText(binfo.getProjectname().toString());
            edt_project_loc.setText(binfo.getProjectadd().toString());
            vConsultant.setText(binfo.getConsulname().toString());


        }

//        Log.d("fgvdfgddfghdfd1",binfo.getCompnyname());


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(MainActivity.this);
                progress.setCancelable(false);
                progress.setTitle("Please wait...");
                progress.show();
               /* Intent in1=new Intent(MainActivity.this,InputActivity.class);
                startActivity(in1);*/



                requestQueue = Volley.newRequestQueue(MainActivity.this);


//                 output = (TextView) findViewById(R.id.jsonData);
                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, Apis.JsonURL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                progress.dismiss();
                                try{
                                    status =response.getString("status");
                                   // Toast.makeText(MainActivity.this,"status is ="+status,Toast.LENGTH_SHORT).show();
                                    if (status.equals("1")){
                                       /* Intent in1=new Intent(MainActivity.this,InputActivity.class);
                                        startActivity(in1);*/
                                        if (ValidInput.validateInput(vDate,vCname,vPname,vPhone,vAddress,vEmail,vProject,vConsultant,
                                                vArea,MainActivity.this)) {

                                            String inputDate=vDate.getText().toString();
                                            String inputComnyname=vComname.getText().toString();
                                            String inputCname=vCname.getText().toString();
                                            String getGender=selectGender.getSelectedItem().toString();
                                            String inputPname=vPname.getText().toString();
                                            String incompname=getGender+" " +inputPname;
                                            String inputPhone=vPhone.getText().toString();
                                            String inputAdd=vAddress.getText().toString();
                                            String inputEmail=vEmail.getText().toString();
                                            String inputProject=vProject.getText().toString();
                                            String inputConsul=vConsultant.getText().toString();
                                            //String inputOffer=vOffer.getText().toString();
                                            String inputArea=vArea.getText().toString();

                                            binfo.setDate(inputDate);
                                            binfo.setCompnyname(inputComnyname);
                                            binfo.setCname(inputCname);
                                            binfo.setPname(incompname);
                                            binfo.setPhone(inputPhone);
                                            binfo.setAdd(inputAdd);
                                            binfo.setEmail(inputEmail);
                                            binfo.setProjectname(inputProject);
                                            binfo.setProjectadd(edt_project_loc.getText().toString());
                                            binfo.setConsulname(inputConsul);
                                            binfo.setOffer("N/A");
                                            binfo.setArea(inputArea);

//                                            Log.w("user id", "is " +inputCname);
//                                            Log.w("Today Date", "is " + inputPname);

                                            hideKeyboard();

                                            saveInformation();

                                            final Dialog dialog = new Dialog(MainActivity.this);
                                            dialog.setContentView(R.layout.dialog_pop_layout);
                                            Button button=(Button)dialog.findViewById(R.id.ok) ;

                                            final CheckBox checkbox=(CheckBox)dialog.findViewById(R.id.checkbox) ;



                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    if (checkbox.isChecked()){
                                                        dialog.dismiss();
                                                            Intent in=new Intent(MainActivity.this,InputActivity.class);
                                                                startActivity(in);
                                                    }
                                                    else{
                                                        Toast.makeText(MainActivity.this, "Please tap on I Agree", Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            });
                                            dialog.show();

//

                                        }

                                    }



                                    //output.setText(data);
                                }catch(JSONException e){e.printStackTrace();}
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley","Error");
                                progress.dismiss();
                            }
                        }
                );
                requestQueue.add(jor);






               /* if (ValidInput.validateInput(vDate,vCname,vPname,vPhone,vAddress,vEmail,vProject,vConsultant,
                        vArea,MainActivity.this)) {


                    String inputDate=vDate.getText().toString();
                    String inputCname=vCname.getText().toString();
                    String getGender=selectGender.getSelectedItem().toString();
                    String inputPname=vPname.getText().toString();
                    String incompname=getGender+" " +inputPname;
                    String inputPhone=vPhone.getText().toString();
                    String inputAdd=vAddress.getText().toString();
                    String inputEmail=vEmail.getText().toString();
                    String inputProject=vProject.getText().toString();
                    String inputConsul=vConsultant.getText().toString();
                    //String inputOffer=vOffer.getText().toString();
                    String inputArea=vArea.getText().toString();


                    binfo.setDate(inputDate);
                    binfo.setCname(inputCname);
                    binfo.setPname(incompname);
                    binfo.setPhone(inputPhone);
                    binfo.setAdd(inputAdd);
                    binfo.setEmail(inputEmail);
                    binfo.setProjectname(inputProject);
                    binfo.setConsulname(inputConsul);
                    binfo.setOffer("N/A");
                    binfo.setArea(inputArea);

                    Log.w("user id", "is " +inputCname);
                    Log.w("Today Date", "is " + inputPname);


                    hideKeyboard();

                    Intent in=new Intent(MainActivity.this,InputActivity.class);
                    startActivity(in);
                }*/

            }

            private void saveInformation() {

                InputInformation.setCompanyname(vComname.getText().toString());
                InputInformation.setClientname(vCname.getText().toString());
                InputInformation.setPersonaname(selectGender.getSelectedItem().toString()+" "+vPname.getText().toString());
                InputInformation.setPersonphone(vPhone.getText().toString());
                InputInformation.setAddress(vAddress.getText().toString());
                InputInformation.setEmailid(vEmail.getText().toString());
                InputInformation.setProjectname(vProject.getText().toString());
                InputInformation.setProjectlocation(edt_project_loc.getText().toString());
                InputInformation.setConsaltname(vConsultant.getText().toString());
                InputInformation.setApplication(vArea.getText().toString());



//                progress = new ProgressDialog(MainActivity.this);
//                progress.setCancelable(false);
//                progress.setTitle("Please wait...");
//                progress.show();

//                StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.GET_SaveInformation,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Log.w("response is",""+response);
//                                progress.dismiss();
//                                //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
//                                try {
//                                    JSONObject obj = new JSONObject(response);
//                                    String status = obj.getString("status");
//                                    String msg = obj.getString("msg");
//
//                                    if (status.equals("1")){
//
//                                        Intent in=new Intent(MainActivity.this,InputActivity.class);
//                                        startActivity(in);
//                                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
//                                    }
//                                    else {
//                                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
//                                    }
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(MainActivity.this,"Please check Internet Connection",Toast.LENGTH_LONG ).show();
//                                progress.dismiss();
//                            }
//                        }){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//
//                        Map<String,String> map = new HashMap<String,String>();
//
//                        map.put("company_name",vComname.getText().toString());
//                        map.put("client_name",vCname.getText().toString());
//                        map.put("person_name",selectGender.getSelectedItem().toString()+" "+vPname.getText().toString());
//                        map.put("phone",vPhone.getText().toString());
//                        map.put("address",vAddress.getText().toString());
//                        map.put("email",vEmail.getText().toString());
//                        map.put("project_name",vProject.getText().toString());
//                        map.put("Project_Location",edt_project_loc.getText().toString());
//                        map.put("Consultant",vConsultant.getText().toString());
//                        map.put("Application",vArea.getText().toString());
//
//                        Log.d("company_name",vComname.getText().toString());
//                        Log.d("client_name",vCname.getText().toString());
//                        Log.d("person",selectGender.getSelectedItem().toString()+" "+vPname.getText().toString());
//                        Log.d("phone",vPhone.getText().toString());
//                        Log.d("address",vAddress.getText().toString());
//                        Log.d("email",vEmail.getText().toString());
//                        Log.d("project_name",vProject.getText().toString());
//                        Log.d("Project_Location",edt_project_loc.getText().toString());
//                        Log.d("Consultant",vConsultant.getText().toString());
//                        Log.d("Application",vArea.getText().toString());
//
//
//                        return map;
//                    }
//                };
//                AppController.getInstance().addToRequestQueue(stringRequest);
            }
        });
    }

    public void inputField(){
        selectGender = (Spinner) findViewById(R.id.spnrgender);

        vDate= (EditText) findViewById(R.id.edt_date);
        vComname= (EditText) findViewById(R.id.edt_comname);
        vCname= (EditText) findViewById(R.id.edt_cname);
        vPname= (EditText) findViewById(R.id.edt_pname);
        vPhone= (EditText) findViewById(R.id.edt_pnumber);
        vAddress= (EditText) findViewById(R.id.edt_address);
        vEmail= (EditText) findViewById(R.id.edt_email);
        vProject= (EditText) findViewById(R.id.edt_project);
        edt_project_loc= (EditText) findViewById(R.id.edt_project_loc);
        vConsultant= (EditText) findViewById(R.id.edt_consultant);
       // vOffer= (EditText) findViewById(R.id.edt_offer_no);
        vArea= (EditText) findViewById(R.id.edt_area);
        next= (TextView) findViewById(R.id.next);
    }

    public void todayDate(){
        Calendar c = Calendar.getInstance();
        // System.out.println("Current time => "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        vDate.setText(formattedDate);
    }

    public void hideKeyboard()
    {
        if(getCurrentFocus()!=null)
        {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    //Creating option menu to add logout feature
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Adding logout option here
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            OcwActivity.isOffer=false;
            MyPreference.signOut(MainActivity.this);
            finish();
            Intent log = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(log);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                //all permissions were granted
                //checkGpsStatus();
                break;
        }
    }


    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS, grantResults);
        }
    }

}
