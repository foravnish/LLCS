package com.risein.llcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.provider.Settings.Secure;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.risein.llcs.helper.AppController;
import com.risein.llcs.utils.Apis;
import com.risein.llcs.utils.MyPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Risein on 10/20/2016.
 */

public class LoginActivity  extends AppCompatActivity {

EditText sUserName,sPass;
    Button signinbutton;
    Button registration_button;

    String uName,sPassword,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.login_page);
        //getSupportActionBar().hide();

        signinbutton= (Button) findViewById(R.id.sign_in_button);
        registration_button= (Button) findViewById(R.id.registration_button);

        sUserName= (EditText) findViewById(R.id.signin_username);
        sPass= (EditText) findViewById(R.id.signin_pass);

        id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
        //Toast.makeText(LoginActivity.this,"device id is"+id,Toast.LENGTH_SHORT).show();

        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loginValid()) {

                    userLogin();

                   /* Intent sintent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(sintent);*/
                }
            }
        });

        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,Registration.class));
            }
        });
    }

    private boolean loginValid(){

        if (TextUtils.isEmpty(sUserName.getText().toString()))
        {
            sUserName.setError("Oops! User Name field blank");
            sUserName.requestFocus();
            return false;

        }

        else if (TextUtils.isEmpty(sPass.getText().toString()))
        {
            sPass.setError("Oops! Password field blank");
            sPass.requestFocus();
            return false;
        }

        return true;
    }

     private void userLogin() {
         uName=sUserName.getText().toString();
         sPassword=sPass.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("response is",""+response);
                        //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");
                            String msg = obj.getString("msg");

                             if (status.equals("1")){
                                Intent sintent = new Intent(LoginActivity.this, MainActivity.class);
                                sintent.putExtra("valueintent","1");
                                startActivity(sintent);
                                MyPreference.saveUserstatus(LoginActivity.this, true);
                                MyPreference.saveLoginid(LoginActivity.this, obj.getString("id"));
                                MyPreference.saveUserid(LoginActivity.this, obj.getString("designation"));
                                MyPreference.saveUserEmail(LoginActivity.this, obj.getString("email"));
                                MyPreference.saveUsername(LoginActivity.this, obj.getString("name"));
                                MyPreference.saveUsermobile(LoginActivity.this, obj.getString("mobile"));
                                MyPreference.saveRolltype(LoginActivity.this, obj.getString("role"));

                                MyPreference.saveUsername2(LoginActivity.this, uName);
                                MyPreference.savePassword(LoginActivity.this, sPassword);

                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"Please check Internet Connection",Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<String,String>();

                Log.w("device id",""+id);
                map.put("username",uName);
                map.put("password",sPassword);
                map.put("device_id",id);
                return map;
            }
        };
         AppController.getInstance().addToRequestQueue(stringRequest);
       /* RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/
       /* Log.e("lavkush",""+stringRequest);*/
    }

}