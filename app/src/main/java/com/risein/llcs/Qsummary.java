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
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.risein.llcs.utils.BasicInformation;
import com.risein.llcs.utils.MyPreference;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Risein on 9/29/2016.
 */

public class Qsummary extends AppCompatActivity {
TextView txtqsummary,txt_login_detail;
    Button btnqsummary,print_qsummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_summary);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_in);
 /*add toolbar to actionbar*/
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtqsummary= (TextView) findViewById(R.id.txt_qsummary);
        txt_login_detail= (TextView) findViewById(R.id.txt_login_detail);
        btnqsummary= (Button) findViewById(R.id.btn_qsummary);
        print_qsummary= (Button) findViewById(R.id.print_qsummary);

        txtqsummary.setText(R.string.q_summ);
        txt_login_detail.setText(MyPreference.loadUsername(Qsummary.this)+"\n"+ MyPreference.loadUserid(Qsummary.this)+"\n"+MyPreference.loadUsermobile(Qsummary.this)+"\n"+MyPreference.loadUserEmail(Qsummary.this)+"\nwww.casilica.com");

        btnqsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qsummin=new Intent(Qsummary.this,TechnicalSummary.class);
                startActivity(qsummin);
            }
        });

        print_qsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* createPDF();
                Toast.makeText(Qsummary.this,"Pdf Created",Toast.LENGTH_SHORT).show();*/
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
