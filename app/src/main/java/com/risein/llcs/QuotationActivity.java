package com.risein.llcs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.risein.llcs.utils.BasicInformation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Risein on 9/28/2016.
 */

public class QuotationActivity extends AppCompatActivity {

    private TextView text1,q_add,printCname,printPname,printPhone,printAdd,printEmail,printCon,printArea,printOffer,subtxt,
            dec1,quotationdate,print_qcname;
    Button qnext,print_qutatn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in);
 /*add toolbar to actionbar*/
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        qnext= (Button) findViewById(R.id.q_next);
        print_qutatn= (Button) findViewById(R.id.print_qutatn);

        print_qcname= (TextView) findViewById(R.id.print_qcname);
        quotationdate= (TextView) findViewById(R.id.quotation_date);
        text1= (TextView) findViewById(R.id.txt1);
        q_add= (TextView) findViewById(R.id.q_add);
        subtxt= (TextView) findViewById(R.id.sub);
        printPhone= (TextView) findViewById(R.id.print_phone1);
        printEmail= (TextView) findViewById(R.id.print_email1);
        printCname= (TextView) findViewById(R.id.print_cname1);
        printCon= (TextView) findViewById(R.id.print_con1);
        printArea= (TextView) findViewById(R.id.print_area1);
        printAdd= (TextView) findViewById(R.id.print_add1);
        printOffer= (TextView) findViewById(R.id.print_offer1);
        printPname= (TextView) findViewById(R.id.print_pname1);
        dec1= (TextView) findViewById(R.id.dec1);


        quotationdate.setText("Date:"+BasicInformation.getDate().toString());
        text1.setText(BasicInformation.getCompnyname().toString());
        q_add.setText(BasicInformation.getAdd());
        print_qcname.setText("Client Name : " +BasicInformation.getCname().toString());
        printPhone.setText("Phone : " +BasicInformation.getPhone().toString());
        printEmail.setText("Email : "+ BasicInformation.getEmail().toString());
        printCname.setText("Project Name : " +BasicInformation.getProjectname().toString());
        printCon.setText("Consultant : " +BasicInformation.getConsulname().toString());
        printArea.setText("Application : " +BasicInformation.getArea().toString());
        printAdd.setText("Project Location : " +BasicInformation.getProjectadd().toString());
        printOffer.setText("Offer No : " +BasicInformation.getOffer().toString());
        printPname.setText("Kind Attn : " +BasicInformation.getPname().toString());
        subtxt.setText(R.string.t3);
        dec1.setText(R.string.t4);

        qnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qnextin=new Intent(QuotationActivity.this,Qsummary.class);
                startActivity(qnextin);
            }
        });

        print_qutatn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* createPDF();
                Toast.makeText(QuotationActivity.this,"Pdf Created",Toast.LENGTH_SHORT).show();*/
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




}
