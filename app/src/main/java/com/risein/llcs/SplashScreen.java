package com.risein.llcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.risein.llcs.utils.InternetStatus;
import com.risein.llcs.utils.MyPreference;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by adm on 8/5/2016.
 */
public class SplashScreen extends AppCompatActivity {

    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.splash);
        //getSupportActionBar().hide();

        contactList = new ArrayList<>();

        if (MyPreference.loadUserstatus(SplashScreen.this) &&
                InternetStatus.isConnectingToInternet(SplashScreen.this)) {

            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        intent.putExtra("valueintent","1");
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();
        }
        else {
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }


    /*class CricketPage extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();

       *//* String schoolName=editSchoolView.getText().toString();
        String collegeName=editcollegeView.getText().toString();
        String workplace=editworkView.getText().toString();
        String playingArea1=editarea1View.getText().toString();
        String playingArea2=editarea2View.getText().toString();
        String playingArea3=editarea3View.getText().toString();*//*

        private static final String TAG_STATUS = "status";
        private static final String TAG_MESSAGE = "msg";

        HashMap<String, String> params = new HashMap<>();

        @Override
        protected void onPreExecute() {
            //pDialog = new ProgressDialog(SplashScreen.this);
            //pDialog.setCancelable(false);
           // pDialog.setMessage("Please wait...");
            //pDialog.show();

           *//* params.put("image_name", " ");
            params.put("school", schoolName);
            params.put("college",collegeName);
            params.put("workplace", workplace);
            params.put("playing_area1", playingArea1);
            params.put("playing_area2",playingArea2);
            params.put("playing_area3", playingArea3);*//*
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {
                JSONObject json = jsonParser.makeHttpRequest("http://cricapi.com/api/cricket/", "GET", params);
                Log.d("upload ", "------------" + json);
                if (json != null) {
                    Log.d("upload result", json.toString());

                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        protected void onPostExecute(JSONObject json) {
            String success = "";
            String message = "";
            String data = "";
            *//*if (pDialog.isShowing())
                pDialog.dismiss();*//*


            if (json != null) {
                try {

                    success=json.getString("cache");

                    JSONArray ja = json.getJSONArray("data");

                    for(int i=0; i < ja.length(); i++) {

                        JSONObject jsonObject = ja.getJSONObject(i);
                        HashMap<String, String> contact = new HashMap<>();

                        // int id = Integer.parseInt(jsonObject.optString("id").toString());
                        String uid = jsonObject.getString("unique_id");
                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");
                        contact.put("unique_id",uid);
                        contact.put("title",title);
                        contact.put("description",description);
                        // adding contact to contact list
                        contactList.add(contact);
                        data += "Blog Number " + (i + 1) + " \n Blog Name= " + title + " \n unique_id= " + uid + " \n\n\n\n ";
                    }
                   // Toast.makeText(SplashScreen.this, "" + data, Toast.LENGTH_SHORT).show();
                    //Log.d("unique_id>>>>",contactList.get(0).get("unique_id"));

                   *//* success = json.getString("status");
                    message = json.getString("msg");
                    Toast.makeText(UpgradeActivity.this, "" + success, Toast.LENGTH_SHORT).show();
                    Log.d("message login>>>>",message);*//*
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
           *//* Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(2000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();*//*
            if (success.equals("true")) {
               *//* Intent intent;




                intent = new Intent(SplashScreen.this, UpgradeActivity.class);
                startActivity(intent);*//*

                Thread timerThread = new Thread(){
                    public void run(){
                        try{
                            sleep(2000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }finally{
                            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                            intent.putExtra("myscore",contactList);
                            startActivity(intent);
                        }
                    }
                };
                timerThread.start();



            } else {
                Toast.makeText(SplashScreen.this, "not true", Toast.LENGTH_SHORT).show();
            }
        }
    }*/



}
