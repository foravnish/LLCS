package com.risein.llcs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.risein.llcs.utils.BasicInformation;

/**
 * Created by Risein on 9/29/2016.
 */

public class TechnicalSummary extends AppCompatActivity {
TextView txtqtsummhead,hdtwo,tsarea,tsrmvalume,tsperson,tsfaq,tsiaq,
        tsmodel,tsdesignrh,tsdesigntp,tsdaq;
    Button btnqtsum;

    public  static String dehValue="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_tsummary);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in);
 /*add toolbar to actionbar*/
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnqtsum= (Button) findViewById(R.id.btn_qtsum);

        txtqtsummhead= (TextView) findViewById(R.id.txt_qtsumm_head);
        hdtwo= (TextView) findViewById(R.id.hd_two);
        //...........
        tsarea= (TextView) findViewById(R.id.ts_area);
        tsrmvalume= (TextView) findViewById(R.id.ts_rmvalume);
        tsperson= (TextView) findViewById(R.id.ts_person);
        tsfaq= (TextView) findViewById(R.id.ts_faq);
        tsiaq= (TextView) findViewById(R.id.ts_iaq);
        tsmodel= (TextView) findViewById(R.id.ts_model);
        tsdesignrh= (TextView) findViewById(R.id.ts_designrh);
        tsdesigntp= (TextView) findViewById(R.id.ts_designtp);
        tsdaq= (TextView) findViewById(R.id.ts_daq);

        tsData();
        //.....
        txtqtsummhead.setText(R.string.tsummary);
        hdtwo.setText(R.string.tdehumidifier);
        btnqtsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qtIn=new Intent(TechnicalSummary.this,OcwActivity.class);
                startActivity(qtIn);
            }
        });

    }

    private void tsData(){
        double dtsdesignrhValue=Double.parseDouble(BasicInformation.getDcRh().toString())+5;
        double tsdesigntpValue=Double.parseDouble(BasicInformation.getDcDbt().toString())+3.6;

        double dtsdesignrhValue1=Double.parseDouble(BasicInformation.getDcRh().toString());
        double tsdesigntpValue1=Double.parseDouble(BasicInformation.getDcDbt().toString());


        Log.w("TechnicalSummary","dtsdesignrhValue :"+dtsdesignrhValue+" tsdesigntpValue :"+tsdesigntpValue);
        tsarea.setText(BasicInformation.getArea().toString());
        tsperson.setText(BasicInformation.getNperson().toString());
        tsrmvalume.setText(BasicInformation.getRmVolume().toString());
        tsfaq.setText(BasicInformation.getFaq().toString());
        tsiaq.setText(BasicInformation.getInfilterationLoadCfm().toString());
        tsdesignrh.setText(String.format("%.2f",dtsdesignrhValue1));
        tsdesigntp.setText(String.format("%.2f",tsdesigntpValue1));
        tsmodel.setText(BasicInformation.getModel().toString());

        if (!BasicInformation.getSflowDehumidifier().isEmpty()){
            dehValue=BasicInformation.getSflowDehumidifier().toString();
            tsdaq.setText(BasicInformation.getSflowDehumidifier().toString());
        }else {
            dehValue=BasicInformation.getDehumidifier().toString();
            tsdaq.setText(BasicInformation.getDehumidifier().toString());
        }

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
