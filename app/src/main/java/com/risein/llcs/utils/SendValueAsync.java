package com.risein.llcs.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.risein.llcs.InputActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Risein on 11/14/2016.
 */

public class SendValueAsync extends AsyncTask<String, JSONObject, JSONObject> {
    String Out_letApi = "http://www.riseintechnology.com/sampletemplate/pagecalculator/ws/moisturedetail.php";
    Context context;
    String designgr, dbt;
    public static double outlet_output;

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
        JSONParser jsonParser = new JSONParser();
        jsonObject = jsonParser.makeHttpRequest(Out_letApi, "GET", hashMap);

        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject obj) {
        super.onPostExecute(obj);
        try {
            String status = obj.getString("status");
            String msg = obj.getString("msg");
            Toast.makeText(context, ""+obj, Toast.LENGTH_LONG).show();


            if (status.equals("1")) {
                outlet_output = Double.valueOf(obj.getString("moisture"));
            } else {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
