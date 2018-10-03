package com.risein.llcs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.risein.llcs.utils.Apis;
import com.risein.llcs.utils.BasicInformation;
import com.risein.llcs.utils.InternetStatus;
import com.risein.llcs.utils.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Risein on 9/24/2016.
 */

public class PrintSheet extends AppCompatActivity {

    private TextView printDate,printCname,printPname,printPhone,printAdd,printEmail,printProject,printCon,
    printOffer,printArea,printIl,printPl,printAol,printTl,printSf,printFal,printRdlength,printRdwidth,printRdheight,printRdvolume,
    printOcDbt, printOcWbt, printOcRh, printOcGr, printOcBtu, printDcDbt, printDcWbt, printDcRh, printDcGr, printDcBtu,printpermeation,printpersonnal,
            print_windowLoad,print_fixed_door, printMl,printTml,printDcr,printMr,requiredDcDbt,requiredDcWbt, requiredDcRh, requiredDcGr, requiredDcBtu;

    LinearLayout printlytnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_sheet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in);
        /*add toolbar to actionbar*/
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        printOutput();
        getAllDtata();

        printlytnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InternetStatus.isConnectingToInternet(PrintSheet.this)) {
                    new PrintSheet.SendTempAsync(PrintSheet.this, BasicInformation.getDvalue(), BasicInformation.getDbtValue()).execute();
                }

               /* Intent inprint=new Intent(PrintSheet.this,SystemFlow.class);
                startActivity(inprint);*/
            }
        });
    }

    private void printOutput(){
        printlytnext= (LinearLayout) findViewById(R.id.print_lytnext);

        printDate= (TextView) findViewById(R.id.print_date);
        printCname= (TextView) findViewById(R.id.print_cname);
        printPname= (TextView) findViewById(R.id.print_pname);
        printPhone= (TextView) findViewById(R.id.print_phone);
        printAdd= (TextView) findViewById(R.id.print_add);
        printEmail= (TextView) findViewById(R.id.print_email);
        printProject= (TextView) findViewById(R.id.print_project);
        printCon= (TextView) findViewById(R.id.print_con);
        printOffer= (TextView) findViewById(R.id.print_offer);
        printArea= (TextView) findViewById(R.id.print_area);
        //inputActivity
        printRdlength= (TextView) findViewById(R.id.print_rdlength);
        printRdwidth= (TextView) findViewById(R.id.print_rdwidth);
        printRdheight= (TextView) findViewById(R.id.print_rdheight);
        printRdvolume= (TextView) findViewById(R.id.print_volume);

        printOcDbt= (TextView) findViewById(R.id.print_ocdbt);
        printOcWbt= (TextView) findViewById(R.id.print_ocwbt);
        printOcRh= (TextView) findViewById(R.id.print_ocrh);
        printOcGr= (TextView) findViewById(R.id.print_ocgrs);
        printOcBtu= (TextView) findViewById(R.id.print_ocbhu);

        printDcDbt= (TextView) findViewById(R.id.print_dcdbt);
        printDcWbt= (TextView) findViewById(R.id.print_dcwbt);
        printDcRh= (TextView) findViewById(R.id.print_dcrh);
        printDcGr= (TextView) findViewById(R.id.print_dcgrs);
        printDcBtu= (TextView) findViewById(R.id.print_dcbtu);


        requiredDcDbt= (TextView) findViewById(R.id.required_dcdbt);
        requiredDcWbt= (TextView) findViewById(R.id.required_dcwbt);
        requiredDcRh= (TextView) findViewById(R.id.required_dcrh);
        requiredDcGr= (TextView) findViewById(R.id.required_dcgrs);
        requiredDcBtu= (TextView) findViewById(R.id.required_dcbtu);

        printpermeation=(TextView) findViewById(R.id.print_permeation);
        printpersonnal=(TextView) findViewById(R.id.print_personnal);
        print_windowLoad=(TextView) findViewById(R.id.print_windowLoad);
        print_fixed_door=(TextView) findViewById(R.id.print_fixed_door);

        printIl= (TextView) findViewById(R.id.print_infilt);
        printPl= (TextView) findViewById(R.id.print_product_load);
        printAol= (TextView) findViewById(R.id.print_aol);
        printTl= (TextView) findViewById(R.id.print_tl);
        printSf= (TextView) findViewById(R.id.print_sf);
        printFal= (TextView) findViewById(R.id.print_fal);

        printMl= (TextView) findViewById(R.id.print_ml);
        printTml= (TextView) findViewById(R.id.print_tml);
        printDcr= (TextView) findViewById(R.id.print_dcr);
        printMr= (TextView) findViewById(R.id.print_mr);

    }

    private void getAllDtata(){

        printDate.setText(BasicInformation.getDate().toString());
        printCname.setText(BasicInformation.getCname().toString());
        printPname.setText(BasicInformation.getPname().toString());
        printPhone.setText(BasicInformation.getPhone().toString());
        printAdd.setText(BasicInformation.getAdd().toString());
        printEmail.setText(BasicInformation.getEmail().toString());
        printProject.setText(BasicInformation.getProjectname().toString());
        printCon.setText(BasicInformation.getConsulname().toString());
        printOffer.setText(BasicInformation.getOffer().toString());
        printArea.setText(BasicInformation.getArea().toString());

        //get inputActivity data
        printRdlength.setText(BasicInformation.getRmLength().toString());
        printRdwidth.setText(BasicInformation.getRmWidth().toString());
        printRdheight.setText(BasicInformation.getRmHeight().toString());
        printRdvolume.setText(BasicInformation.getRmVolume().toString());

        printOcDbt.setText(BasicInformation.getAcDbt().toString());
        printOcWbt.setText(BasicInformation.getAcWbt().toString());
        printOcRh.setText(BasicInformation.getAcRh().toString());
        printOcGr.setText(BasicInformation.getAcGr().toString());
        printOcBtu.setText(BasicInformation.getAcBtu().toString());

        printDcDbt.setText(BasicInformation.getDcDbt().toString());
        printDcWbt.setText(BasicInformation.getDcWbt().toString());
        printDcRh.setText(BasicInformation.getDcRh().toString());
        printDcGr.setText(BasicInformation.getDcGr().toString());
        printDcBtu.setText(BasicInformation.getDcBtu().toString());

        double ddryValue=Double.parseDouble(BasicInformation.getDcDbt().toString())+3.6;
        double drhValue=Double.parseDouble(BasicInformation.getDcRh().toString())+5;
          requiredDcDbt.setText(String.format("%.2f",ddryValue));
        requiredDcWbt.setText(BasicInformation.getDcWbt2().toString());
           requiredDcRh.setText(String.format("%.2f",drhValue));
            requiredDcGr.setText(BasicInformation.getDcGr2().toString());
        requiredDcBtu.setText(BasicInformation.getDcBtu2().toString());

        Log.w("getPermeation",">>"+BasicInformation.getPermeation().toString());
        Log.w("getOccupacyCal",">>"+BasicInformation.getOccupacyCal().toString());

        printpermeation.setText(BasicInformation.getPermeation().toString());
        printpersonnal.setText(BasicInformation.getOccupacyCal().toString());
        print_windowLoad.setText(BasicInformation.getMoisture().toString());
        print_fixed_door.setText(BasicInformation.getPassbox().toString());

        printIl.setText(BasicInformation.getInfilterationLoad().toString());
        printPl.setText(BasicInformation.getProductLoad().toString());
        printAol.setText(BasicInformation.getAnyotherLoad().toString());
        printTl.setText(BasicInformation.getTotalLoad().toString());
        printSf.setText(BasicInformation.getSafetyFactor().toString());
        printFal.setText(BasicInformation.getFreshAir().toString());

        printMl.setText(BasicInformation.getLatentLoad().toString());
        printTml.setText(BasicInformation.getTLatentLoad().toString());
        printDcr.setText(BasicInformation.getDehumidifier().toString());
        printMr.setText(BasicInformation.getModel().toString());

    }
    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


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
            String outlet_output = "0.0";
            try {
                String status = obj.getString("status");
                String msg = obj.getString("msg");


                if (status.equals("1")) {
                    outlet_output = obj.getString("moister");
                    //Toast.makeText(context, "" + outlet_output, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                }
                Log.w("else data", "else temp output" + outlet_output);
                Intent inprint=new Intent(PrintSheet.this,SystemFlow.class);
                inprint.putExtra("temp_value",outlet_output);
                startActivity(inprint);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
