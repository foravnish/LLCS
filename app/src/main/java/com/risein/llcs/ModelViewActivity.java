package com.risein.llcs;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.risein.llcs.helper.AppController;
import com.risein.llcs.utils.Apis;
import com.risein.llcs.utils.AppUtils;
import com.risein.llcs.utils.BasicInformation;
import com.risein.llcs.utils.FilePath;
import com.risein.llcs.utils.InputInformation;
import com.risein.llcs.utils.JSONParser;
import com.risein.llcs.utils.MyPreference;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.picasso.Picasso;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.risein.llcs.SystemFlow.dehumidifierValue;

public class ModelViewActivity  extends AppCompatActivity {

    Button button_print, btn_finish, btn_madd, btn_msave, btn_send_pdf;
    ImageView img_model;
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    public static double a3Value = 0.0;
    public static double dehumidifierValue = 0.0;
    public static double a4Value = 0.0;
    public static double a5Value = 0.0;
    public static double t6Value = 0.0;
    public static double abs6Value = 0.0;
    public static double outLet_value = 0.0;
    public static String a9Value;
    public static double t8Value = 0.0;
    public static double abs8Value = 0.0;
    public static double a10Value = 0.0;
    String t9Value;
    public static double a8Value = 0.0;
    public static double ReactLoad = 0.0;
    public static double Steam = 0.0;
    public static Double precoil = 0.0;
    public static Double postCoil = 0.0;
    public static String Enthalpy1 = "0.0";

    public static String mediaEfficiency = "0.50";
    public static double afterRecovery = 0.0;
    public static Double current = 0.0;
    public static Double current2 = 0.0;
    public static double hrSavings = 0.0;
    public static double hrSavings2 = 0.0;

    public static double ddryValue1 = 0.0;
    public static double drhValue1 = 0.0;
    String precool, afterecool, heatrec;

    public ProgressDialog progress;
    Boolean flag = false;
    Intent intent;

    String TO, CC, SUBJECT, MESSAGE;
    String url;
    Document document;
    Uri uri;
    int serverResponseCode = 0;
    String upLoadServerUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_view);

        button_print = (Button) findViewById(R.id.button_print);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        btn_madd = (Button) findViewById(R.id.btn_madd);
        btn_msave = (Button) findViewById(R.id.btn_msave);
//        btn_send_pdf=(Button)findViewById(R.id.btn_send_pdf);

        img_model = (ImageView) findViewById(R.id.img_model);

        precool = getIntent().getStringExtra("precool");
        afterecool = getIntent().getStringExtra("aftercool");
        heatrec = getIntent().getStringExtra("heatrecovery");

        progress = new ProgressDialog(ModelViewActivity.this);
        progress.setMessage("Loading Image......");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);

        dehumidifierValue = Double.parseDouble(BasicInformation.getDehumidifier().toString());

        Log.d("dgfhbfdhfghjfg", BasicInformation.getPSA().toString());

//        new GetModelImage(ModelViewActivity.this).execute();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.GET_IMGApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("fgdfgdfgdfgd", "" + response);
                        //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                        try {
                            progress.dismiss();
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");
                            String msg = obj.getString("msg");
                            JSONObject object = obj.getJSONObject("item");

                            url = object.optString("image");
                            Log.d("dfgfdgdfgdfgdf", status);
                            Log.d("dfgfdgdfgdfgdf", obj.toString());
                            if (status.equals("1")) {
                                Picasso.with(getApplicationContext()).load(url).placeholder(R.color.colorPrimary).error(R.color.transparet_grey).into(img_model);

                            } else {
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                progress.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ModelViewActivity.this, "Please check Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();

                Log.d("fdgdgdfgdfg", BasicInformation.getModel().toString());
                Log.d("fdgdgdfgdfg", BasicInformation.getPSA().toString());

                map.put("model_no", BasicInformation.getModel().toString());
                map.put("type", BasicInformation.getPSA().toString());
//                    map.put("model_no","CRD-350");
//                    map.put("type","PSA");

                map.put("pname", "casilica");
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);


        if (Double.parseDouble(BasicInformation.getAhu().toString()) < dehumidifierValue) {
            a9Value = String.valueOf(dehumidifierValue);

        } else {
            a9Value = BasicInformation.getAhu().toString();
        }

//        button_print.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
////                progress = new ProgressDialog(ModelViewActivity.this);
////                progress.setMessage("Loading...");
////                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
////                progress.setIndeterminate(true);
////                progress.setCancelable(false);
////                progress.show();
//                button_print.setText("Generating PDF");
//                button_print.setTextSize(14);
//                button_print.setAllCaps(false);
////                button_print.setEnabled(false);
//                return true;
//            }
//        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ModelViewActivity.this, MainActivity.class);
                intent.putExtra("valueintent", "1");
                startActivity(intent);
            }
        });

        img_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppUtils.showFullImageDialog(ModelViewActivity.this, url, BasicInformation.getModel().toString());
            }
        });
        btn_madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag == true) {
                    //Toast.makeText(ModelViewActivity.this, "yes", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ModelViewActivity.this, MainActivity.class);
                    intent.putExtra("valueintent", "2");
                    startActivity(intent);
                } else if (flag == false) {
                    Toast.makeText(ModelViewActivity.this, "Please click on print button.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_msave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (flag == true) {
                    final Dialog dialog = new Dialog(ModelViewActivity.this);
                    dialog.setContentView(R.layout.popup_mail);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    dialog.setTitle("Custom Dialog");

                    Button btn_okay = (Button) dialog.findViewById(R.id.btn_okay);
                    Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);

                    final TextView editTextTo = (TextView) dialog.findViewById(R.id.editTextTo);
                    final TextView editTextcc = (TextView) dialog.findViewById(R.id.editTextcc);
                    final TextView editTextSubject = (TextView) dialog.findViewById(R.id.editTextSubject);
                    final TextView editTextMessage = (TextView) dialog.findViewById(R.id.editTextMessage);


                    dialog.show();
                    btn_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    btn_okay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            intent = new Intent(Intent.ACTION_SEND);

                            TO = editTextTo.getText().toString();
                            CC = editTextcc.getText().toString();
                            SUBJECT = editTextSubject.getText().toString();
                            MESSAGE = editTextMessage.getText().toString();

                            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{TO});
                            intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
                            intent.putExtra(Intent.EXTRA_CC, CC);
                            intent.putExtra(Intent.EXTRA_TEXT, MESSAGE);

                            intent.setType("text/plain");

                            File root = new File(Environment.getExternalStorageDirectory() + "/Casilica");

                            File file = new File(root.getAbsolutePath(), "PDF-" + BasicInformation.getOffer().toString() +"-"+BasicInformation.getOfferCode().toString()+ ".pdf");
                            Log.d("dsfdsgfdsgdfgdf",String.valueOf(file));

                            Uri uri = Uri.fromFile(file);
                            Log.d("dsfdsgfdsgdfgdf",String.valueOf(uri));
                            intent.putExtra(Intent.EXTRA_STREAM, uri);

                            startActivity(Intent.createChooser(intent, "Select Email Sending App :"));
                            dialog.dismiss();
                        }
                    });
                } else if (flag == false) {
                    Toast.makeText(ModelViewActivity.this, "Please click on print button.", Toast.LENGTH_SHORT).show();
                }


            }
        });

//        btn_send_pdf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });

        button_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                 Toast.makeText(getApplicationContext(), "Please wait...while geterateing PDF file.", Toast.LENGTH_SHORT).show();
//                button_print.setText("generating PDF");


                flag = true;
                try {

                    createPdfWrapper();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }


            }

        });
//        Log.d("fgddgdfgdfg",precool);
//        Log.d("fgddgdfgdfg",afterecool);


    }

    private void uploadPDF() {






        new sendfeedbackValue().execute();










//        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Casilica");
//        if (!docsFolder.exists()) {
//            docsFolder.mkdir();
//            //  Log.i(TAG, "Created a new directory for PDF");
//        }
//
//        pdfFile = new File(docsFolder.getAbsolutePath(), "PDF-" + BasicInformation.getOffer().toString() + ".pdf");
//
//        Log.d("gfdhfhfhfghdgfhf",pdfFile.toString());
//
//        uri = Uri.fromFile(pdfFile);
//
//
////        String fileName = sourceFileUri;
////        String fileName = String.valueOf(uri);
//        String fileName = pdfFile.toString();
//
//
//        HttpURLConnection conn = null;
//        DataOutputStream dos = null;
//        String lineEnd = "\r\n";
//        String twoHyphens = "--";
//        String boundary = "*****";
//        int bytesRead, bytesAvailable, bufferSize;
//        byte[] buffer;
//        int maxBufferSize = 1 * 1024 * 1024;
////        File sourceFile = new File(String.valueOf(uri));
//        File sourceFile = new File(pdfFile.toString());
//
//        if (!sourceFile.isFile()) {
//
//
//            Log.e("uploadFile", "Source File not exist :"
//                    + String.valueOf(uri));
//
//            runOnUiThread(new Runnable() {
//                public void run() {
//                    // messageText.setText("Source File not exist :"+uploadFilePath + "" + uploadFileName);
//                }
//            });
//
//
//        } else {
//            try {
//
//                // open a URL connection to the Servlet
//                FileInputStream fileInputStream = new FileInputStream(sourceFile);
//                URL url = new URL("http://casilica.com/pagecalculator/ws/savepdf.php");
//
//                // Open a HTTP  connection to  the URL
//                conn = (HttpURLConnection) url.openConnection();
//                conn.setDoInput(true); // Allow Inputs
//                conn.setDoOutput(true); // Allow Outputs
//                conn.setUseCaches(false); // Don't use a Cached Copy
//                conn.setRequestMethod("POST");
//                conn.setRequestProperty("Connection", "Keep-Alive");
//                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
//                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//                conn.setRequestProperty("file_name", fileName);
//
//                dos = new DataOutputStream(conn.getOutputStream());
//
//                dos.writeBytes(twoHyphens + boundary + lineEnd);
//                //dos.writeBytes("Content-Disposition: form-data; name="uploaded_file";filename=""+ fileName + """ + lineEnd);
//
//                dos.writeBytes(lineEnd);
//
//                // create a buffer of  maximum size
//                bytesAvailable = fileInputStream.available();
//
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                buffer = new byte[bufferSize];
//
//                // read file and write it into form...
//                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//                while (bytesRead > 0) {
//
//                    dos.write(buffer, 0, bufferSize);
//                    bytesAvailable = fileInputStream.available();
//                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//                }
//
//                // send multipart form data necesssary after file data...
//                dos.writeBytes(lineEnd);
//                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//
//                // Responses from the server (code and message)
//                serverResponseCode = conn.getResponseCode();
//                String serverResponseMessage = conn.getResponseMessage();
//
//                Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
//
//                if (serverResponseCode == 200) {
//
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//
////                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
////                                    +" http://www.androidexample.com/media/uploads/"
////                                    +uploadFileName;
//
//                            // messageText.setText(msg);
//                            Toast.makeText(ModelViewActivity.this, "File Upload Complete.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//
//                //close the streams //
//                fileInputStream.close();
//                dos.flush();
//                dos.close();
//
//            } catch (MalformedURLException ex) {
//
//
//                ex.printStackTrace();
//
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        //messageText.setText("MalformedURLException Exception : check script url.");
//                        Toast.makeText(ModelViewActivity.this, "MalformedURLException", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
//            } catch (final Exception e) {
//
//
//                e.printStackTrace();
//
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        // messageText.setText("Got Exception : see logcat ");
//                        Toast.makeText(ModelViewActivity.this, "Got Exception : see logcat ", Toast.LENGTH_SHORT).show();
//                        Log.d("fdgfdgdfgdfhgdd",e.toString());
//                    }
//                });
////                Log.e("Upload file to server Exception", "Exception : "+ e.getMessage(), e);
//            }
//        }




//        String name = "PDF-" +BasicInformation.getOffer().toString() + ".pdf";
//
//        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Casilica");
//        if (!docsFolder.exists()) {
//            docsFolder.mkdir();
//            //  Log.i(TAG, "Created a new directory for PDF");
//        }
//
//        pdfFile = new File(docsFolder.getAbsolutePath(),"PDF-" +BasicInformation.getOffer().toString() + ".pdf");
//
//        uri= Uri.fromFile(pdfFile);
//        String path = FilePath.getPath(this, uri);
//
//        Log.d("sdfgfdsgdfgf", String.valueOf(uri));
//        Log.d("sdfgfdsgdfgf", String.valueOf(pdfFile));
//
//        if (path == null) {
//
//            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
//        } else {
//            //Uploading code
//            try {
//                String uploadId = UUID.randomUUID().toString();
//
//
//
//                Toast.makeText(this, "uploaded", Toast.LENGTH_SHORT).show();
//                //Creating a multi part request
//                new MultipartUploadRequest(this, uploadId, Apis.save_pdfApi)
//                        .addFileToUpload(path, "pdf") //Adding file
////                        .addParameter("user_id", MyPreference.loadLoginid(ModelViewActivity.this)) //Adding text parameter to the request
////                        .addParameter("document_id", "10") //Adding text parameter to the request
//                        .addParameter("file_name", String.valueOf(pdfFile)) //Adding text parameter to the request
//                        .setNotificationConfig(new UploadNotificationConfig())
//                        .setMaxRetries(5)
//
//                        .startUpload(); //Starting the upload
//
//
//                Log.d("ghfghjfgjfg",MyPreference.loadLoginid(ModelViewActivity.this));
//                Log.d("ghfghjfgjfg",String.valueOf(pdfFile));
//
//
//
//            } catch (Exception exc) {
//                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }





    }

    private void createPdfWrapper() throws FileNotFoundException,DocumentException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }

            return;
        }else {

            createPdf();
        }
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Casilica");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            //  Log.i(TAG, "Created a new directory for PDF");
        }


//        if (BasicInformation.getOffer().toString().equals(null)){
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            pdfFile = new File(docsFolder.getAbsolutePath(),"PDF-" + sdf.format(Calendar.getInstance().getTime()) + ".pdf");
//        }
//        else{
//            pdfFile = new File(docsFolder.getAbsolutePath(),"PDF-" +BasicInformation.getOffer().toString() + ".pdf");
//        }

        pdfFile = new File(docsFolder.getAbsolutePath(),"PDF-" +BasicInformation.getOffer().toString()+"-"+BasicInformation.getOfferCode().toString() + ".pdf");

        Log.d("fdgdfgdfghdfhfdg",pdfFile.toString());
        OutputStream output = new FileOutputStream(pdfFile);

        document= new Document();
        PdfWriter writer = PdfWriter.getInstance(document, output);

        writer.setPageEvent(new MyFooter());

        document.open();
        Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD);
        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.UNDERLINE|Font.BOLD);


        Paragraph preface11 = new Paragraph("Date: "+ BasicInformation.getDate().toString());
        preface11.setAlignment(Element.ALIGN_RIGHT);
        document.add(preface11);

        //document.add(new Paragraph("\n "));

        document.add(new Paragraph("M/s "+BasicInformation.getCompnyname().toString(),font1));
        document.add(new Paragraph("Address: " +BasicInformation.getAdd().toString()));
        document.add(new Paragraph("Client Name: "+BasicInformation.getCname().toString()));
        document.add(new Paragraph("Phone: "+BasicInformation.getPhone().toString()));
        document.add(new Paragraph("Email: "+BasicInformation.getEmail().toString()));

        document.add(new Paragraph("\nProject Name: "+BasicInformation.getProjectname().toString()));
        document.add(new Paragraph("Consultant: "+BasicInformation.getConsulname().toString()));
        document.add(new Paragraph("Application: "+"Storage"));
        document.add(new Paragraph("Project Location: "+BasicInformation.getProjectadd().toString()));
        document.add(new Paragraph("Offer No: "+BasicInformation.getOffer().toString()+"-"+BasicInformation.getOfferCode().toString()));
        document.add(new Paragraph("Kind Attn: "+BasicInformation.getPname().toString(),font1));

        document.add(new Paragraph("Sub: Dehumidifier for Humidity Control ",font2));
//        document.add(new Paragraph("Sub: Dehumidifier for Humidity Control \n "));

        document.add(new Paragraph("\nDear Sir,"));

        document.add(new Paragraph(getString(R.string.t4)));

        document.newPage();
        document.add(new Paragraph("\n Summary: ",font1));
        document.add(new Paragraph(getString(R.string.q_summ)));
        document.add(new Paragraph("A.C. Humidin Air Systems",font1));

        //txt_login_detail.setText(MyPreference.loadUsername(Qsummary.this)+"\n"+ MyPreference.loadUserid(Qsummary.this)+"\n"+MyPreference.loadUsermobile(Qsummary.this)+"\n"+MyPreference.loadUserEmail(Qsummary.this)+"\nwww.casilica.com");
        document.add(new Paragraph("\n"+MyPreference.loadUsername(ModelViewActivity.this)));
        document.add(new Paragraph(MyPreference.loadUserid(ModelViewActivity.this)));
        document.add(new Paragraph(MyPreference.loadUsermobile(ModelViewActivity.this)));
        document.add(new Paragraph(MyPreference.loadUserEmail(ModelViewActivity.this)));
        document.add(new Paragraph("www.casilica.com"));


        document.newPage();

        Paragraph preface = new Paragraph("\n"+getString(R.string.tsummary),font1);
        preface.setAlignment(Element.ALIGN_CENTER);

        document.add(preface);
        document.add(new Paragraph("                "+getString(R.string.tdehumidifier)+" \n ",font1));

        PdfPTable table = new PdfPTable(new float[] { 1, 1 });
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("Area/AHU No");
        table.addCell(BasicInformation.getArea().toString());

        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j=0;j<cells.length;j++){
            cells[j].setBackgroundColor(BaseColor.WHITE);
        }

        table.addCell("Room Volume (CFT):");
        table.addCell(BasicInformation.getRmVolume().toString());

        table.completeRow();
        table.addCell("Persons (NOS):");
        table.addCell(BasicInformation.getNperson().toString());

        table.completeRow();;
        table.addCell("Fresh Air Qty (CFM)");
        table.addCell(BasicInformation.getFaq().toString());

        table.completeRow();;
        table.addCell("Infiltration Air Qty (CFM)");
        table.addCell(BasicInformation.getInfilterationLoadCfm().toString());

        table.completeRow();;
        table.addCell("Model");
//        double dtsdesignrhValue=Double.parseDouble(BasicInformation.getModel().toString());
        table.addCell(BasicInformation.getModel().toString());

        table.completeRow();;
        table.addCell("Design RH (%)");
//        double tsdesigntpValue=Double.parseDouble(BasicInformation.getDcRh().toString())+5;
        double tsdesigntpValue=Double.parseDouble(BasicInformation.getDcRh().toString());
        table.addCell(String.format("%.2f",tsdesigntpValue));

        table.completeRow();;
        table.addCell("Design Temp (ºF)");
//        double dtsdesignrhValue=Double.parseDouble(BasicInformation.getDcDbt().toString())+3.6;
        double dtsdesignrhValue=Double.parseDouble(BasicInformation.getDcDbt().toString());
        table.addCell(String.format("%.2f",dtsdesignrhValue));

        table.completeRow();;
        table.addCell("Deh Air Qty (CFM)");
        if (!BasicInformation.getSflowDehumidifier().isEmpty()){

            table.addCell(BasicInformation.getSflowDehumidifier().toString());
        }else {

            table.addCell(BasicInformation.getDehumidifier().toString());
        }

        document.add(table);

        document.newPage();

        Paragraph preface2 = new Paragraph("\n"+getString(R.string.our_scope),font1);
        preface2.setAlignment(Element.ALIGN_CENTER);
        document.add(preface2);

        document.add(new Paragraph("               "+getString(R.string.our_scopehading)+" \n",font1));

        PdfPTable table2 = new PdfPTable(new float[] { 1, 1 ,1});
        table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell("Dehumidifier Model");
        table2.addCell("Nominal capacity");
        table2.addCell("Designed to deliver\n" +
                "Capacity ");

        table2.setHeaderRows(1);
        PdfPCell[] cells2 = table.getRow(0).getCells();
        for (int j=0;j<cells.length;j++){
            cells2[j].setBackgroundColor(BaseColor.GRAY);
        }

        table2.addCell(BasicInformation.getModel().toString());
        table2.addCell(BasicInformation.getScop1().toString());
        table2.addCell(BasicInformation.getScop2().toString());

        document.add(table2);

        document.add(new Paragraph("\n1. Pre Cool Housing & Coil : "+precool));
        document.add(new Paragraph("2. After Cool Housing & Coil : "+afterecool));
        document.add(new Paragraph("3. Process fan & motor arrangement.\n"+"   * "+BasicInformation.getQ4().toString()+"\n"+"   * Fan Make: Humidin Fan motor make: Siemens/Crompton/ABB/Equivalent make"));
        document.add(new Paragraph("4. Reactivation fan and motor arrangement\n"+"   * "+BasicInformation.getQ5().toString()+"”\n" +
                "   * Fan Make: Humidin Fan motor make: Siemens /Crompton/ABB/Equivalent make"));
        document.add(new Paragraph("5. Filters for Process\n" +
                "   * "+BasicInformation.getQ6().toString()));
        document.add(new Paragraph("6. Reactivation Air\n" +
                "   * Media: Synthetic Non-Woven Media"));

        document.add(new Paragraph("7. Regeneration Heat Source\n" +
                "   * 100% Electric Heater"));
        document.add(new Paragraph("8. Heat Recovery :"+heatrec));
        document.add(new Paragraph("9. Skid mounted unit with no foundation required"));
        document.add(new Paragraph("10.Volume Control Dampers for Process & Supply Air\n" +
                "    * Type: Boxed, opposed blade type manually operated"));
        document.add(new Paragraph("11. Control panel with all inbuilt electrical controls for dehumidifier smooth operation\n" +
                "   * Ultra Intelligent Series (UI) – It is used to automate the machine in order to map, study and"));
        document.add(new Paragraph("    even modulate the entire machine remotely. It can also be connected to the BMS easily."));
        document.add(new Paragraph(getString(R.string.osw_13)));
        document.add(new Paragraph("\nExclusions from our scope : ",font1));
        document.add(new Paragraph(getString(R.string.exclusionscope)));
        document.add(new Paragraph("Anything not specifically mentioned in our Scope of Supply",font2));
        document.newPage();

Log.d("fdgdfgdfgdfghdfhd",BasicInformation.getRolltype().toString());

        if (BasicInformation.getRolltype().toString().equals("sales")) {
            /////  Price Page
            Paragraph preface3 = new Paragraph("\n " + getString(R.string.q_commer), font1);
            preface3.setAlignment(Element.ALIGN_CENTER);
            document.add(preface3);

            document.add(new Paragraph("\n The following is our competitive quote as per recommended models.\n "));

            PdfPTable table3 = new PdfPTable(new float[]{1, 1});
            table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell("Application");
            table3.addCell(BasicInformation.getArea().toString());


            table3.setHeaderRows(1);
            PdfPCell[] cells3 = table.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells3[j].setBackgroundColor(BaseColor.GRAY);
            }

            table3.addCell("Model No.");
            table3.addCell(BasicInformation.getModel().toString());
            table3.completeRow();

            table3.addCell("Price per unit (INR)\n" +
                    "(w/o taxes &amp; duties)");
//        double price = Double.parseDouble(BasicInformation.getQuot_price());
//        double dis = Double.parseDouble(BasicInformation.getQuot_discount());
//        double dis_value=price*dis/100;
//        double act_val=price-dis_value;
//        table3.addCell(BasicInformation.getQuot_price()+" "+dis_value+" "+act_val);
            table3.addCell(BasicInformation.getQuot_price());
            table3.completeRow();

            table3.addCell("Qty");
            table3.addCell(BasicInformation.getQuot_qty());
            table3.completeRow();

//        table3.addCell("Discount");
//        table3.addCell(BasicInformation.getQuot_discount()+" %");
//        table3.completeRow();

            table3.addCell("Total Value (Rs)");
            table3.addCell(BasicInformation.getQuot_t_price());
            table3.completeRow();

            document.add(table3);

            document.add(new Paragraph("The above prices includes", font1));
            document.add(new Paragraph("Dehumidifier with Pre and Post Cooling – Yes\n"));

            document.add(new Paragraph("The prices mentioned above are Ex-Works exclusive of any Taxes and Duties.\n\n", font1));
            document.add(new Paragraph("TAXES & DUTIES are included in the above price which are as follows:", font1));
            document.add(new Paragraph("* Packing shall be charged extra @ 3%"));
            document.add(new Paragraph("* Insurance shall be 0.5%"));
            document.add(new Paragraph("* Freight, Octroi, if applicable shell be extra"));
            document.add(new Paragraph("* GST @ 18% Shall be applicable on the basic price + packing +insurance +Freight.\n\n"));

            Chunk p11 = new Chunk("DELIVERY: ", font1);
            Chunk p21 = new Chunk("8 weeks from the date of Purchase Order, Technical Approval, Commercial\n" +
                    "Clearance whichever is later\n\n");
            document.add(p11);
            document.add(p21);

            //document.add(new Paragraph("DELIVERY: 8 weeks from the date of Purchase Order, Technical Approval, Commercial\n" +
            //"Clearance whichever is later\n\n"));

            document.add(new Paragraph("SUPERVISION CHARGES (per unit) - Rs. 12,500/-", font1));

            Chunk p12 = new Chunk("\nPAYMENT TERMS : ", font1);
            Chunk p22 = new Chunk("Advance - 50% along with the order & Balance with full Taxes & Duties\n" +
                    "against Performa Invoice prior to Dispatch in favor of “A.C. HUMIDIN AIR SYSTEMS” located at\n" +
                    "B-6, Sector A3, Tronica City, Loni, UP-201102, INDIA\n\n");
            document.add(p12);
            document.add(p22);

//        document.add(new Paragraph("\nPAYMENT TERMS : Advance - 50% along with the order & Balance with full Taxes & Duties\n" +
//                "against Performa Invoice prior to Dispatch in favor of “A.C. HUMIDIN AIR SYSTEMS” located at\n" +
//                "B-6, Sector A3, Tronica City, Loni, UP-201102, INDIA\n\n"));


            Chunk p1 = new Chunk("VALIDITY OF QUOTE : ", font1);
            Chunk p2 = new Chunk("30 days from the quotation date");
            document.add(p1);
            document.add(p2);
            //  document.add(new Paragraph("VALIDITY OF QUOTE : 30 days from the quotation date"));

            document.add(new Paragraph("\nYours faithfully,"));
            document.add(new Paragraph("For A.C. HUMIDIN AIR SYSTEMS", font1));


            document.add(new Paragraph("\n" + MyPreference.loadUsername(ModelViewActivity.this) + "\n" +
                    MyPreference.loadUserid(ModelViewActivity.this) + "\n" +
                    MyPreference.loadUsermobile(ModelViewActivity.this) + "\n" +
                    MyPreference.loadUserEmail(ModelViewActivity.this) + "\n" +
                    "www.casilica.com"));

        }
        ///  End Price page
        document.newPage();


//// Input Sheet Begin

        Paragraph preface4input = new Paragraph(getString(R.string.inputsheet),font1);
        preface4input.setAlignment(Element.ALIGN_CENTER);
        document.add(preface4input);

        document.add(new Paragraph("               Ambient Condition(Monsoon): \n ",font1));
        PdfPTable table4_1 = new PdfPTable(new float[] {1, 1,1,1,1});
        table4_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_1.addCell("Dry Bulb");
        table4_1.addCell("Wet Bulb");
        table4_1.addCell("RH ");
        table4_1.addCell("Gr/#");
        table4_1.addCell("Enthalpy ");
        table4_1.completeRow();



        table4_1.addCell(InputInformation.getAmbDBT().toString()+" (ºF)");
        table4_1.addCell(InputInformation.getAmbWBT().toString()+" (ºF)");
        table4_1.addCell(InputInformation.getAmbRH().toString()+" %");
        table4_1.addCell(InputInformation.getAmbgr().toString()+"");
        table4_1.addCell(InputInformation.getAmbenthaply().toString());
        table4_1.completeRow();
        document.add(table4_1);


        document.add(new Paragraph("\n               Design Condition: \n ",font1));
        PdfPTable table4_2 = new PdfPTable(new float[] {1, 1,1,1,1});
        table4_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_2.addCell("Dry Bulb");
        table4_2.addCell("Wet Bulb");
        table4_2.addCell("RH ");
        table4_2.addCell("Gr/#");
        table4_2.addCell("Enthalpy ");
        table4_2.completeRow();

        table4_2.addCell(InputInformation.getDcDBT().toString()+" (ºF)");
        table4_2.addCell(InputInformation.getDcWBT().toString()+" (ºF)");
        table4_2.addCell(InputInformation.getDcRH().toString()+" %");
        table4_2.addCell(InputInformation.getDcgr().toString()+"");
        table4_2.addCell(InputInformation.getDcenthaply().toString());
        table4_2.completeRow();
        document.add(table4_2);


        document.add(new Paragraph("\n"));
        PdfPTable table4_3 = new PdfPTable(new float[] {1, 1});
        table4_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_3.addCell("Delta Grains");
        table4_3.addCell("F1 (Due to gain difference)");
        table4_3.completeRow();
        table4_3.addCell(InputInformation.getDeltagain().toString());
        table4_3.addCell(InputInformation.getF1_1().toString());
        table4_3.completeRow();
        document.add(table4_3);


        Toast.makeText(this, " "+InputInformation.getDRLength().toString(), Toast.LENGTH_SHORT).show();
        document.add(new Paragraph("\n               Dimension Room: \n ",font1));
        PdfPTable table4_4 = new PdfPTable(new float[] {1, 1,1,1});
        table4_4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_4.addCell("Length");
        table4_4.addCell("Width");
        table4_4.addCell("Height");
        table4_4.addCell("Volume");
        table4_4.completeRow();

//        table4_4.addCell(InputInformation.getDRLength().toString()+" ft");
//        table4_4.addCell(InputInformation.getDRWidth().toString()+" ft");
//        table4_4.addCell(InputInformation.getDRheight().toString()+" ft");
//        table4_4.addCell(InputInformation.getDRvolume().toString());

        table4_4.addCell(InputInformation.getDimroomlen().toString()+" ft");
        table4_4.addCell(InputInformation.getDimroomwid().toString()+" ft");
        table4_4.addCell(InputInformation.getDimroomhei().toString()+" ft");
        table4_4.addCell(InputInformation.getDimroomvol().toString()+" cu.ft.");
        table4_4.completeRow();

        if (!InputInformation.getDimroomlen1().toString().isEmpty()){
            table4_4.addCell(InputInformation.getDimroomlen1().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomwid1().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomhei1().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomvol1().toString()+" cu.ft.");
            table4_4.completeRow();
        }
        if (!InputInformation.getDimroomlen2().toString().isEmpty()){
            table4_4.addCell(InputInformation.getDimroomlen2().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomwid2().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomhei2().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomvol2().toString()+" cu.ft.");
            table4_4.completeRow();
        }
        if (!InputInformation.getDimroomlen3().toString().isEmpty()){
            table4_4.addCell(InputInformation.getDimroomlen3().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomwid3().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomhei3().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomvol3().toString()+" cu.ft.");
            table4_4.completeRow();
        }
        if (!InputInformation.getDimroomlen4().toString().isEmpty()){
            table4_4.addCell(InputInformation.getDimroomlen4().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomwid4().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomhei4().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomvol4().toString()+" cu.ft.");
            table4_4.completeRow();
        }
        if (!InputInformation.getDimroomlen5().toString().isEmpty()){
            table4_4.addCell(InputInformation.getDimroomlen5().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomwid5().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomhei5().toString()+" ft");
            table4_4.addCell(InputInformation.getDimroomvol5().toString()+" cu.ft.");
            table4_4.completeRow();
        }

        document.add(table4_4);

        document.add(new Paragraph("\n"));
        PdfPTable table4_5 = new PdfPTable(new float[] {1, 1,1});
        table4_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_5.addCell("F2");
        table4_5.addCell("F3");
        table4_5.addCell("F4 ");
        table4_5.completeRow();
        table4_5.addCell(InputInformation.getF2().toString());
        table4_5.addCell(InputInformation.getF3().toString());
        table4_5.addCell(InputInformation.getF4().toString());
        table4_5.completeRow();
        document.add(table4_5);


        document.add(new Paragraph("\n               Door Load: \n ",font1));
        PdfPTable table4_6 = new PdfPTable(new float[] {1, 1,1});
        table4_6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_6.addCell("Width");
        table4_6.addCell("Height");
        table4_6.addCell("Door Opening");
        table4_6.completeRow();




        table4_6.addCell(InputInformation.getDLWidth().toString()+" ft");
        table4_6.addCell(InputInformation.getDLheight().toString()+" ft");
        table4_6.addCell(InputInformation.getDLDoorOpening().toString());
        table4_6.completeRow();

        if (!InputInformation.getDLWidth1().toString().isEmpty()) {
            table4_6.addCell(InputInformation.getDLWidth1().toString() + " ft");
            table4_6.addCell(InputInformation.getDLheight1().toString() + " ft");
            table4_6.addCell(InputInformation.getDLDoorOpening1().toString());
            table4_6.completeRow();
        }
        if (!InputInformation.getDLWidth2().toString().isEmpty()) {
            table4_6.addCell(InputInformation.getDLWidth2().toString() + " ft");
            table4_6.addCell(InputInformation.getDLheight2().toString() + " ft");
            table4_6.addCell(InputInformation.getDLDoorOpening2().toString());
            table4_6.completeRow();
        }
        if (!InputInformation.getDLWidth3().toString().isEmpty()) {
            table4_6.addCell(InputInformation.getDLWidth3().toString() + " ft");
            table4_6.addCell(InputInformation.getDLheight3().toString() + " ft");
            table4_6.addCell(InputInformation.getDLDoorOpening3().toString());
            table4_6.completeRow();
        }
        if (!InputInformation.getDLWidth4().toString().isEmpty()) {
            table4_6.addCell(InputInformation.getDLWidth4().toString() + " ft");
            table4_6.addCell(InputInformation.getDLheight4().toString() + " ft");
            table4_6.addCell(InputInformation.getDLDoorOpening4().toString());
            table4_6.completeRow();
        }
        if (!InputInformation.getDLWidth5().toString().isEmpty()) {
            table4_6.addCell(InputInformation.getDLWidth5().toString() + " ft");
            table4_6.addCell(InputInformation.getDLheight5().toString() + " ft");
            table4_6.addCell(InputInformation.getDLDoorOpening5().toString());
            table4_6.completeRow();
        }





        document.add(table4_6);


        document.add(new Paragraph("\n               Condition Outside the Door Room: \n ",font1));
        PdfPTable table4_7_1 = new PdfPTable(new float[] {1, 1,1,1});
        table4_7_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        table4_7_1.addCell("Dry Bulb");
        table4_7_1.addCell("Wet Bulb");
        table4_7_1.addCell("RH %");
        table4_7_1.addCell("Gr/#");
        table4_7_1.completeRow();
        table4_7_1.addCell(InputInformation.getDRLength().toString()+" (ºF)");
        table4_7_1.addCell(InputInformation.getDRWidth().toString()+" (ºF)");
        table4_7_1.addCell(InputInformation.getDRheight().toString());
        table4_7_1.addCell(InputInformation.getDRvolume().toString());
        table4_7_1.completeRow();
        document.add(table4_7_1);

        document.add(new Paragraph("\n"));
        PdfPTable table4_7 = new PdfPTable(new float[] {1, 1,1,1,1});
        table4_7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_7.addCell("Delta Gr");
        table4_7.addCell("F1 ");
        table4_7.addCell("No of person");
        table4_7.addCell("Activity Level");
        table4_7.addCell("Gr/Hr");
        table4_7.completeRow();
        table4_7.addCell(InputInformation.getDeltaGr().toString());
        table4_7.addCell(InputInformation.getCOTF1().toString());
        table4_7.addCell(InputInformation.getNoperson().toString());
        table4_7.addCell(InputInformation.getActivitylevel().toString());
        table4_7.addCell(InputInformation.getActLevel().toString());
        table4_7.completeRow();
        document.add(table4_7);


        if(!InputInformation.getDLWidth1().toString().isEmpty()){
            document.newPage();
        }

        document.add(new Paragraph("\n               Fixed Opening Dimensions: \n ",font1));
        PdfPTable table4_12 = new PdfPTable(new float[] {1, 1,1,1});
        table4_12.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_12.addCell("Height");
        table4_12.addCell("Width");
        table4_12.addCell("Depth");
        table4_12.addCell("Volume");
        table4_12.completeRow();

        table4_12.addCell(InputInformation.getFODheight().toString()+" ft");
        table4_12.addCell(InputInformation.getFODWidth().toString()+" ft");
        table4_12.addCell(InputInformation.getFODDept().toString()+" ft");
        table4_12.addCell(InputInformation.getFODvolume().toString()+" cu.ft.");
        table4_12.completeRow();

        if(!InputInformation.getFODheight1().toString().isEmpty()){
        table4_12.addCell(InputInformation.getFODheight1().toString()+" ft");
        table4_12.addCell(InputInformation.getFODWidth1().toString()+" ft");
        table4_12.addCell(InputInformation.getFODDept1().toString()+" ft");
        table4_12.addCell(InputInformation.getFODvolume1().toString()+" cu.ft.");
        table4_12.completeRow();}

        if(!InputInformation.getFODheight2().toString().isEmpty()){
            table4_12.addCell(InputInformation.getFODheight2().toString()+" ft");
        table4_12.addCell(InputInformation.getFODWidth2().toString()+" ft");
        table4_12.addCell(InputInformation.getFODDept2().toString()+" ft");
        table4_12.addCell(InputInformation.getFODvolume2().toString()+" cu.ft.");
        table4_12.completeRow();}

        if(!InputInformation.getFODheight3().toString().isEmpty()){
            table4_12.addCell(InputInformation.getFODheight3().toString()+" ft");
        table4_12.addCell(InputInformation.getFODWidth3().toString()+" ft");
        table4_12.addCell(InputInformation.getFODDept3().toString()+" ft");
        table4_12.addCell(InputInformation.getFODvolume3().toString()+" cu.ft.");
        table4_12.completeRow();}

        if(!InputInformation.getFODheight4().toString().isEmpty()){
            table4_12.addCell(InputInformation.getFODheight4().toString()+" ft");
        table4_12.addCell(InputInformation.getFODWidth4().toString()+" ft");
        table4_12.addCell(InputInformation.getFODDept4().toString()+" ft");
        table4_12.addCell(InputInformation.getFODvolume4().toString()+" cu.ft.");
        table4_12.completeRow();}

        if(!InputInformation.getFODheight5().toString().isEmpty()){
            table4_12.addCell(InputInformation.getFODheight5().toString()+" ft");
        table4_12.addCell(InputInformation.getFODWidth5().toString()+" ft");
        table4_12.addCell(InputInformation.getFODDept5().toString()+" ft");
        table4_12.addCell(InputInformation.getFODvolume5().toString()+" cu.ft.");
        table4_12.completeRow();}


        document.add(table4_12);

        if(InputInformation.getDLWidth1().toString().isEmpty()){
            document.newPage();
        }


        document.add(new Paragraph("\n               Condition Outside the Fixed Opening: \n ",font1));
        PdfPTable table4_8 = new PdfPTable(new float[] {1, 1,1,1});
        table4_8.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_8.addCell("Dry Bulb");
        table4_8.addCell("Wet Bulb");
        table4_8.addCell("RH %");
        table4_8.addCell("Gr/#");
        table4_8.completeRow();






        table4_8.addCell(InputInformation.getCofoDbt().toString()+" (ºF)");
        table4_8.addCell(InputInformation.getCofoWbt().toString()+" (ºF)");
        table4_8.addCell(InputInformation.getCofoRh().toString());
        table4_8.addCell(InputInformation.getCOFO_gr().toString());
//
        table4_8.completeRow();
        document.add(table4_8);



        document.add(new Paragraph("\n"));

        PdfPTable table4_13 = new PdfPTable(new float[] {1, 1});
        table4_13.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_13.addCell("Delta Gr");
        table4_13.addCell(InputInformation.getDelta2().toString());
        table4_13.completeRow();

        table4_13.addCell("F1 (Due to grain difference)");
        table4_13.addCell(InputInformation.getF2DTGT().toString());
        table4_13.completeRow();

        table4_13.addCell("Positive Pressure (Pa)");
        table4_13.addCell(InputInformation.getPositivePressure().toString());
        table4_13.completeRow();

        table4_13.addCell("Fresh Air CFM");
        table4_13.addCell(InputInformation.getCFM().toString());
        table4_13.completeRow();

        document.add(table4_13);


        document.add(new Paragraph("\n               Fresh Air Quantity: \n ",font1));
        PdfPTable table4_14 = new PdfPTable(new float[] {1, 1,1,1,1});
        table4_14.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_14.addCell("Dry Bulb");
        table4_14.addCell("Wet Bulb");
        table4_14.addCell("RH %");
        table4_14.addCell("Gr/#");
        table4_14.addCell("Enthalpy ");
        table4_14.completeRow();
        table4_14.addCell(InputInformation.getFAQDBT().toString()+" (ºF)");
        table4_14.addCell(InputInformation.getFAQWBT().toString()+" (ºF)");
        table4_14.addCell(InputInformation.getFAQRH().toString());
        table4_14.addCell(InputInformation.getFAQgr().toString());
        table4_14.addCell(InputInformation.getFAQenthaply().toString());
        table4_14.completeRow();
        document.add(table4_14);



        document.add(new Paragraph("\n"));

        PdfPTable table4_15 = new PdfPTable(new float[] {1, 1});
        table4_15.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        table4_15.addCell("Delta Gr");
        table4_15.addCell(InputInformation.getDelta3().toString());
        table4_15.completeRow();

        table4_15.addCell("Exhaust Air Quantity");
        table4_15.addCell(InputInformation.getEAQ().toString());
        table4_15.completeRow();

        table4_15.addCell("Infiltrated Air Quantity");
        table4_15.addCell(InputInformation.getIAQ().toString());
        table4_15.completeRow();

        document.add(table4_15);



        document.add(new Paragraph("\n               Infiltrated Air Quantity: \n ",font1));
        PdfPTable table4_9 = new PdfPTable(new float[] {1, 1,1,1,1});
        table4_9.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_9.addCell("Dry Bulb");
        table4_9.addCell("Wet Bulb");
        table4_9.addCell("RH %");
        table4_9.addCell("Gr/#");
        table4_9.addCell("Delta Gr");
        table4_9.completeRow();
        table4_9.addCell(InputInformation.getIAQDBT().toString()+" (ºF)");
        table4_9.addCell(InputInformation.getIAQWBT().toString()+" (ºF)");
        table4_9.addCell(InputInformation.getIAQRH().toString());
        table4_9.addCell(InputInformation.getIAQgr().toString());
        table4_9.addCell(InputInformation.getDelta4().toString());
        table4_9.completeRow();
        document.add(table4_9);


        document.add(new Paragraph("\n"));

        PdfPTable table4_10 = new PdfPTable(new float[] {1, 1});
        table4_10.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

//        table4_10.addCell("Delta Gr");
//        table4_10.addCell(InputInformation.getDelta4().toString());
//        table4_10.completeRow();

        table4_10.addCell("Design ADP");
        table4_10.addCell(InputInformation.getDesignADP().toString()+" (ºF)");
        table4_10.completeRow();

        table4_10.addCell("Heat Load of Each Area");
        table4_10.addCell(InputInformation.getHeatLoad().toString()+" Btu/Hr");
        table4_10.completeRow();

        table4_10.addCell("CFM");
        table4_10.addCell(InputInformation.getCFM2().toString());
        table4_10.completeRow();

        table4_10.addCell("AHU Capicity");
        table4_10.addCell(InputInformation.getAHU().toString() +" CFM");
        table4_10.completeRow();

        table4_10.addCell("External Static Pressure Required");
        table4_10.addCell(InputInformation.getExtgernalStatic().toString()+" Pa");
        table4_10.completeRow();

        table4_10.addCell("Filtration ");
        table4_10.addCell(InputInformation.getFiltration().toString());
        table4_10.completeRow();

        document.add(table4_10);



        document.add(new Paragraph("\n               Product  Latent Load: \n ",font1));
        PdfPTable table4_11 = new PdfPTable(new float[] {1, 1,1,1});
        table4_11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4_11.addCell("Product Load");
        table4_11.addCell("IMC");
        table4_11.addCell("FMC");
        table4_11.addCell("Drying Time");
        table4_11.completeRow();
        table4_11.addCell(InputInformation.getPLAPl().toString()+" kgs/hr");
        table4_11.addCell(InputInformation.getPlaImc().toString()+" kgs");
        table4_11.addCell(InputInformation.getPlaFmc().toString()+" kgs");
        table4_11.addCell(InputInformation.getPLA_DryTbl().toString()+" kgs");
        table4_11.completeRow();
        document.add(table4_11);






        document.newPage();


        ///// Input sheet End




        Paragraph preface4 = new Paragraph(getString(R.string.outsheet),font1);
        preface4.setAlignment(Element.ALIGN_CENTER);
        document.add(preface4);


        PdfPTable table4 = new PdfPTable(new float[] {1, 1});
        table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4.addCell("Date");
        table4.addCell(BasicInformation.getDate().toString());
        table4.completeRow();

        table4.addCell("Client Name");
        table4.addCell(BasicInformation.getCname().toString());
        table4.completeRow();

        table4.addCell("Person Name");
        table4.addCell(BasicInformation.getPname().toString());
        table4.completeRow();

        table4.addCell("Phone Number");
        table4.addCell(BasicInformation.getPhone().toString());
        table4.completeRow();

        table4.addCell("Address");
        table4.addCell(BasicInformation.getAdd().toString());
        table4.completeRow();

        table4.addCell("Email ID");
        table4.addCell(BasicInformation.getEmail    ().toString());
        table4.completeRow();

        table4.addCell("Project Name");
        table4.addCell(BasicInformation.getProjectname().toString());
        table4.completeRow();

        table4.addCell("Consultant Name");
        table4.addCell(BasicInformation.getConsulname().toString());
        table4.completeRow();

        table4.addCell("Offer No");
        table4.addCell(BasicInformation.getOffer().toString()+"-"+BasicInformation.getOfferCode().toString());
        table4.completeRow();

        table4.addCell("Area/Application");
        table4.addCell(BasicInformation.getArea().toString());
        table4.completeRow();

        document.add(table4);

        document.add(new Paragraph("               Room Dimensions: \n ",font1));

        PdfPTable table5 = new PdfPTable(new float[] { 1, 1,1,1});
        table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table5.addCell("Length");
        table5.addCell("Width ");
        table5.addCell("Height ");
        table5.addCell("Volume ");
        table5.completeRow();
        table5.addCell(BasicInformation.getRmLength().toString()+"(ft)");
        table5.addCell(BasicInformation.getRmWidth  ().toString()+"(ft)");
        table5.addCell(BasicInformation.getRmHeight ().toString()+"(ft)");
        table5.addCell(BasicInformation.getRmVolume().toString()+"(cu.ft)");

        table5.completeRow();

        document.add(table5);

        document.add(new Paragraph("               Outside Condition: \n ",font1));

        PdfPTable table6 = new PdfPTable(new float[] { 1, 1,1,1,1});
        table6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table6.addCell("DBT");
        table6.addCell("WBT");
        table6.addCell("RH");
        table6.addCell("GRS");
        table6.addCell("BTU/#");
        table6.completeRow();
        table6.addCell(BasicInformation.getAcDbt().toString()+" (ºF)");
        table6.addCell(BasicInformation.getAcWbt().toString()+" (ºF)");
        table6.addCell(BasicInformation.getAcRh().toString()+" %");
        table6.addCell(BasicInformation.getAcGr().toString()+" /#");
        table6.addCell(BasicInformation.getAcBtu().toString()+"");

        table6.completeRow();

        document.add(table6);

        document.add(new Paragraph("               Design Conditions: \n ",font1));

        PdfPTable table7 = new PdfPTable(new float[] { 1, 1,1,1,1});
        table7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table7.addCell("DBT");
        table7.addCell("WBT");
        table7.addCell("RH%");
        table7.addCell("GRS");
        table7.addCell("BTU/#");
        table7.completeRow();
        table7.addCell(BasicInformation.getDcDbt().toString()+" (ºF)");
        table7.addCell(BasicInformation.getDcWbt().toString()+" (ºF)");
        table7.addCell(BasicInformation.getDcRh().toString()+" %");
        table7.addCell(BasicInformation.getDcGr().toString()+" /#");
        table7.addCell(BasicInformation.getDcBtu().toString()+ "");

        table7.completeRow();

        document.add(table7);

        document.add(new Paragraph("               Required Conditions: \n ",font1));

        PdfPTable table8 = new PdfPTable(new float[] { 1, 1,1,1,1});
        table8.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table8.addCell("DBT");
        table8.addCell("WBT");
        table8.addCell("RH");
        table8.addCell("GRS");
        table8.addCell("BTU/#");
        table8.completeRow();
        double ddryValue=Double.parseDouble(BasicInformation.getDcDbt().toString())+3.6;
        double drhValue=Double.parseDouble(BasicInformation.getDcRh().toString())+5;
        table8.addCell(String.format("%.2f",ddryValue)+"(ºF)");
        table8.addCell(BasicInformation.getDcWbt2().toString()+"(ºF)");
        table8.addCell(String.format("%.2f",drhValue)+" %");
        table8.addCell(String.format("%.2f", Double.parseDouble(BasicInformation.getDcGr2().toString().toString()))+" /#");
        table8.addCell(BasicInformation.getDcBtu2().toString()+" ");

        table8.completeRow();

        document.add(table8);

        document.add(new Paragraph("\n"));

        PdfPTable table9= new PdfPTable(new float[] { 1, 1});
        table9.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table9.addCell("");
        table9.addCell("Grs/hr");
        table9.completeRow();

        table9.addCell("Permeation Load");
        table9.addCell(BasicInformation.getPermeation().toString());
        table9.completeRow();

        table9.addCell("Personnal load");
        table9.addCell(BasicInformation.getOccupacyCal().toString());
        table9.completeRow();

        table9.addCell("Fixed Opening");
        table9.addCell(BasicInformation.getMoisture().toString());
        table9.completeRow();

        table9.addCell("Door Load");
        table9.addCell(BasicInformation.getPassbox().toString());
        table9.completeRow();

        table9.addCell("Infiltration Load");
        table9.addCell(BasicInformation.getInfilterationLoad().toString());
        table9.completeRow();

        table9.addCell("Product Load");
        table9.addCell(BasicInformation.getProductLoad().toString());
        table9.completeRow();

        table9.addCell("Any Other Load");
        table9.addCell(BasicInformation.getAnyotherLoad().toString());
        table9.completeRow();

        table9.addCell("Total Load");
        table9.addCell(BasicInformation.getTotalLoad().toString());
        table9.completeRow();

        table9.addCell("Safety Factor(5%)");
        table9.addCell(BasicInformation.getSafetyFactor().toString());
        table9.completeRow();

        table9.addCell("Moisture load' ( R )");
        table9.addCell(BasicInformation.getLatentLoad().toString());
        table9.completeRow();

        table9.addCell("Fresh Air Load(F)");
        table9.addCell(BasicInformation.getFreshAir().toString());
        table9.completeRow();

        table9.addCell("Total latent");
        table9.addCell(BasicInformation.getTLatentLoad().toString());
        table9.completeRow();

        table9.addCell("Dehumidifier CFM");
        table9.addCell(BasicInformation.getDehumidifier().toString());
        table9.completeRow();

        table9.addCell("Model");
        table9.addCell(BasicInformation.getModel().toString());
        table9.completeRow();

        document.add(table9);

        document.newPage();


        Paragraph preface5 = new Paragraph("System Flow",font1);
        preface5.setAlignment(Element.ALIGN_CENTER);
        document.add(preface5);

        try {

            Drawable d = getResources ().getDrawable (R.drawable.systempro);
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] bitmapData = stream.toByteArray();
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(bitmapData);
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getWidth()) * 90;
            image.scalePercent(scaler);
            document.add(image);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Paragraph preface6 = new Paragraph("System Flow",font1);
//        preface6.setAlignment(Element.ALIGN_CENTER);
//        document.add(preface6);

        document.add(new Paragraph("               Design Conditions: \n ",font1));

        PdfPTable table10 = new PdfPTable(new float[] { 1,1,1});
        table10.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table10.addCell("Temp(F)");
        table10.addCell("RH %");
        table10.addCell("Grs/#");

        table10.completeRow();
        table10.addCell(BasicInformation.getDcDbt().toString().toString());
        table10.addCell(BasicInformation.getDcRh().toString().toString());
        table10.addCell(BasicInformation.getDcGr().toString().toString());

        table10.completeRow();

        document.add(table10);

        document.add(new Paragraph("               Required Conditions: \n ",font1));

        PdfPTable table11 = new PdfPTable(new float[] { 1, 1,1});
        table11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table11.addCell("Temp(F)");
        table11.addCell("RH %");
        table11.addCell("Grs/#");



        table11.completeRow();
        ddryValue1=Double.parseDouble(BasicInformation.getDcDbt().toString())+3.6;
        drhValue1=Double.parseDouble(BasicInformation.getDcRh().toString())+5;

        table11.addCell(String.format("%.2f",ddryValue1));
        table11.addCell(String.format("%.2f",drhValue1));
        table11.addCell(String.format("%.2f", Double.parseDouble(BasicInformation.getDcGr2().toString().toString())));


        table11.completeRow();

        document.add(table11);

        Paragraph preface7 = new Paragraph();
        preface7.add(" Area :"+BasicInformation.getArea().toString());
        preface7.add(" \n Model :"+BasicInformation.getModel().toString());
        preface7.add(" \n Latent Load :"+BasicInformation.getLatentLoad().toString()+" \n ");
        preface7.setAlignment(Element.ALIGN_CENTER);
        document.add(preface7);

        String valueFaq=BasicInformation.getFaq().toString();
        String valueahu=BasicInformation.getAhu().toString();

        if (!valueFaq.equals("0")&&!valueahu.equals("0")){
            a3Value=Double.parseDouble(valueahu)-Double.parseDouble(valueFaq);
        }
        else {
            a3Value=dehumidifierValue-Double.parseDouble(BasicInformation.getFaq().toString());
        }

        PdfPTable table12 = new PdfPTable(new float[] { 1, 1,1,1});
        table12.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table12.addCell("");
        table12.addCell("Airflow (cfm)");
        table12.addCell("Temp (F)");
        table12.addCell("Abs (Grs/#)");
        table12.completeRow();

        table12.addCell("A");
        table12.addCell(BasicInformation.getFaq().toString());
        table12.addCell(BasicInformation.getAcDbt().toString());
        table12.addCell(BasicInformation.getAcGr().toString());
        table12.completeRow();

        table12.addCell("B");
        table12.addCell(BasicInformation.getFaq().toString());
        table12.addCell(BasicInformation.getFaqDry().toString());
        table12.addCell(BasicInformation.getPreGr().toString());
        table12.completeRow();

        table12.addCell("C");
        table12.addCell(String.format("%.2f",a3Value));
        table12.addCell(String.format("%.2f",ddryValue));
        table12.addCell(BasicInformation.getDcGr().toString());
        table12.completeRow();

        table12.addCell("C1");
        if (!BasicInformation.getDehumidifier().toString().isEmpty()&& !BasicInformation.getFaq().toString().isEmpty()){

            if (Double.parseDouble(BasicInformation.getFaq().toString())>Double.parseDouble(BasicInformation.getDehumidifier().toString())){

                dehumidifierValue=Double.parseDouble(valueFaq);

                a4Value=dehumidifierValue-Double.parseDouble(valueFaq);
                BasicInformation.setSflowDehumidifier(String.format("%.2f", dehumidifierValue));
                String modelNo = InputActivity.getModel(dehumidifierValue);
                BasicInformation.setModel(modelNo);
            }


            else {
                dehumidifierValue=Double.parseDouble(BasicInformation.getDehumidifier().toString());
                a4Value=dehumidifierValue-Double.parseDouble(valueFaq);
            }
        }

        table12.addCell(String.format("%.2f",a4Value));
        table12.addCell(String.format("%.2f",ddryValue));
        table12.addCell(BasicInformation.getDcGr().toString());
        table12.completeRow();

        table12.addCell("D");
        a5Value=Double.parseDouble(a9Value)-dehumidifierValue;
        table12.addCell(String.format("%.2f",a5Value));
        table12.addCell(String.format("%.2f",ddryValue));
        table12.addCell(BasicInformation.getDcGr().toString());
        table12.completeRow();

        table12.addCell("D1");
        table12.addCell(String.format("%.2f",dehumidifierValue));
        t6Value=(a4Value*ddryValue+Double.parseDouble(BasicInformation.getFaq().toString())*Double.parseDouble(BasicInformation.getFaqDry().toString()))/dehumidifierValue;

        table12.addCell(String.format("%.2f",t6Value));
        abs6Value=(a4Value*Double.parseDouble(BasicInformation.getDcGr().toString())+Double.parseDouble(BasicInformation.getFaq().toString())*Double.parseDouble(BasicInformation.getPreGr().toString()))/dehumidifierValue;
        table12.addCell(String.format("%.2f",abs6Value));
        table12.completeRow();

        table12.addCell("E");
        table12.addCell(String.format("%.2f",dehumidifierValue));
        table12.addCell(String.format(BasicInformation.getTemp_val().toString()));
        if (BasicInformation.getOutlet().toString().isEmpty()){
            outLet_value=Double.parseDouble(BasicInformation.getROutlet().toString());
        }
        else {
            outLet_value =Double.parseDouble(BasicInformation.getOutlet().toString());
        }

        table12.addCell(String.format("%.2f",outLet_value));
        table12.completeRow();

        table12.addCell("F");
        if (Double.parseDouble(BasicInformation.getAhu().toString())<dehumidifierValue){
            a9Value=String.valueOf(dehumidifierValue);

        }
        else {
            a9Value=BasicInformation.getAhu().toString();
        }

        if (!valueFaq.equals("0")&&!valueahu.equals("0")){
            a3Value=Double.parseDouble(valueahu)-Double.parseDouble(valueFaq);
        }
        else {
            a3Value=dehumidifierValue-Double.parseDouble(BasicInformation.getFaq().toString());
        }

        table12.addCell(a9Value);
        a8Value=Double.parseDouble(a9Value);
        t8Value=(dehumidifierValue*Double.parseDouble(BasicInformation.getTemp_val().toString())+a5Value*ddryValue)/a8Value;

        table12.addCell(String.format("%.2f",t8Value));

        abs8Value=(dehumidifierValue
                *outLet_value
                +a5Value
                *Double.parseDouble(BasicInformation.getDcGr().toString()))/Double.parseDouble(a9Value);
        table12.addCell(String.format("%.2f",abs8Value));
        table12.completeRow();

        table12.addCell("G");
        table12.addCell(a9Value);
        if(BasicInformation.getAdp().toString().equals("")||BasicInformation.getAdp().toString().equals(null)||BasicInformation.getAdp().toString().equals("0")){
            table12.addCell("55");
            t9Value="55";
        }
        else {
            table12.addCell(BasicInformation.getAdp().toString());
            t9Value=BasicInformation.getAdp().toString();
        }
        table12.addCell(String.format("%.2f",abs8Value));
        table12.completeRow();

        table12.addCell("H");
        a10Value=dehumidifierValue/3;

        table12.addCell(String.format("%.2f",a10Value));
        table12.addCell(BasicInformation.getAcDbt().toString());
        table12.addCell(BasicInformation.getAcGr().toString());
        table12.completeRow();

        table12.addCell("I");
        table12.addCell(String.format("%.2f",a10Value));
        table12.addCell("284");
        table12.addCell("Abs(Grs/#)");
        table12.completeRow();

        document.add(table12);

        document.newPage();

        document.add(new Paragraph(" \n \n \n"));

        PdfPTable table13 = new PdfPTable(new float[] { 1, 1});
        table13.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        ReactLoad=((284-Double.parseDouble(BasicInformation.getAcDbt().toString()))*1.08*a10Value)/3412;
        Steam=((284-Double.parseDouble(BasicInformation.getAcDbt().toString()))*1.08*a10Value)/(900*2.205);

        table13.addCell("React Load (KW)");
        table13.addCell(String.format("%.2f",ReactLoad));
        table13.completeRow();

        table13.addCell("Steam (Kgs/hr)");
        table13.addCell(String.format("%.2f",Steam));
        table13.completeRow();

        document.add(table13);

        document.add(new Paragraph(" \n "));

        PdfPTable table14 = new PdfPTable(new float[] { 1, 1,1});
        table14.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table14.addCell("Enthalpy ambient");

        String Enthalpy=BasicInformation.getAcBtu().toString();
        table14.addCell(Enthalpy);
        table14.addCell("Btu/#");
        table14.completeRow();

        table14.addCell("Enthalpy at "+BasicInformation.getFaqDry()+" F");
        if (Double.parseDouble(BasicInformation.getFaqDry())==55){
            Enthalpy1="23.378";
        }
        else {
            Enthalpy1=BasicInformation.getFaqBtu().toString();
        }

        table14.addCell(Enthalpy1);
        table14.addCell("Btu/#");
        table14.completeRow();


        table14.addCell("Precoil TOR");
        if (InputActivity.isPrecool==true){
            double prStep1=Double.parseDouble(BasicInformation.getAcBtu().toString())-Double.parseDouble(BasicInformation.getFaqBtu().toString());
            double prStep2=(prStep1*60)/14;
            double prStep3=prStep2*Double.parseDouble(BasicInformation.getFaq().toString());
            //double prStep4=prStep3/12000;
            //precoil=((Double.parseDouble(BasicInformation.getAcBtu().toString())-Double.parseDouble(BasicInformation.getFaqBtu().toString()))*(60/14)*Double.parseDouble(BasicInformation.getFaq().toString()))/12000;
            precoil=prStep3/12000;
            table14.addCell(String.format("%.2f",precoil));
        }
        else {

            String nonPre="N/A";
            table14.addCell(nonPre);
        }

        table14.addCell("TOR");
        table14.completeRow();

        table14.addCell("Post Coil TOR");
        postCoil=(t8Value-Double.parseDouble(t9Value))*(1.08*Double.parseDouble(a9Value))/12000;
        table14.addCell(String.format("%.2f",postCoil));
        table14.addCell("TOR");
        table14.completeRow();
        document.add(table14);


        Paragraph preface8 = new Paragraph("With Heat Recovery \n ",font1);
        preface8.setAlignment(Element.ALIGN_CENTER);
        document.add(preface8);

        PdfPTable table15 = new PdfPTable(new float[] { 1, 1,1});
        table15.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table15.addCell("");
        table15.addCell("Electric");
        table15.addCell("Stream");
        table15.completeRow();

        table15.addCell("With Heat Recovery");
        table15.addCell("(KW)");
        table15.addCell("Kgs/Hr");
        table15.completeRow();

        table15.addCell("Media Efficiency");
        double srStep1=208-Double.parseDouble(BasicInformation.getAcDbt().toString());
        double srStep2=Double.parseDouble(mediaEfficiency)*srStep1;
        double srStep3=srStep2+Double.parseDouble(BasicInformation.getAcDbt().toString());
        double srStep4=284-srStep3;
        double srStep5=srStep4*1.08*a10Value;
        double srStep6=srStep5/3412;
        afterRecovery=srStep6;
        table15.addCell(mediaEfficiency);
          table15.addCell(mediaEfficiency);
        table15.completeRow();

        table15.addCell("Current kW");
        current=ReactLoad;
        current2=Steam;
        table15.addCell(String.format("%.2f",current));
          table15.addCell(String.format("%.2f",current2));
        table15.completeRow();

        table15.addCell("After Recovery");
        table15.addCell(String.format("%.2f",afterRecovery));
          table15.addCell(String.format("%.2f",afterRecovery));
        table15.completeRow();

        table15.addCell("kW/hr Savings");

        hrSavings=current-afterRecovery;
        hrSavings2=current2-afterRecovery;
        table15.addCell(String.format("%.2f",hrSavings));
          table15.addCell(String.format("%.2f",hrSavings2));
        table15.completeRow();

        document.add(table15);

        document.newPage();


        Paragraph preface9 = new Paragraph("Drawing \n ",font1);
        preface9.setAlignment(Element.ALIGN_CENTER);
        document.add(preface9);



//            Log.d("fdgdfgdfhgdf",url);

//        try {
//            String imageUrl = "http://casilica.com/casimage/CRD_350_psa.jpg";
//            Image image = Image.getInstance(new URL(imageUrl));
//            document.add(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try{
//            Drawable d = getResources ().getDrawable (R.drawable.newimg);
//            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
//            byte[] bitmapData = stream.toByteArray();
//            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(bitmapData);
//            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
//                    - document.rightMargin() - 0) / image.getWidth()) * 100;
//            image.scalePercent(scaler);
//            document.add(image);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        if (url==null){
            //Log.d("fdgdfghfdhddfghd","if");
                    try{
            Drawable d = getResources ().getDrawable (R.drawable.splash);
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] bitmapData = stream.toByteArray();
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(bitmapData);
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getWidth()) * 100;
            image.scalePercent(scaler);
            document.add(image);
                        document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }

        else{
            //Log.d("fdgdfghfdhddfghd","else");
            new DownloadImageTask().execute();
        }
        uploadPDF();
        previewPdf();

    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
            Intent testIntent = new Intent(Intent.ACTION_VIEW);
            testIntent.setType("application/pdf");
            List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (list.size() > 0) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                 uri= Uri.fromFile(pdfFile);
                intent.setDataAndType(uri, "application/pdf");

                startActivity(intent);
            }else{
                //progress.dismiss();

                Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
            }
    }

    private class MyFooter extends PdfPageEventHelper {

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            //code skeleton to write page header

            try
            {
                PdfContentByte cb = writer.getDirectContent();
                Drawable d = getResources ().getDrawable (R.drawable.new_11);
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitmapData = stream.toByteArray();
                com.itextpdf.text.Image imgSoc = com.itextpdf.text.Image.getInstance(bitmapData);
                imgSoc.scaleToFit(110,1000);
                imgSoc.setAbsolutePosition(430, 10);

                cb.addImage(imgSoc);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            try
            {
                PdfContentByte cb = writer.getDirectContent();
                Drawable d = getResources ().getDrawable (R.drawable.new_22);
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitmapData = stream.toByteArray();
                com.itextpdf.text.Image imgSoc = com.itextpdf.text.Image.getInstance(bitmapData);
                imgSoc.scaleToFit(130,1000);
                imgSoc.setAbsolutePosition(30, 800);

                cb.addImage(imgSoc);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }


    }

    private class DownloadImageTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            connect();
            return null;
        }

        private void connect() {

            try {
                String imageUrl = url;
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(new URL(imageUrl));

                float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                        - document.rightMargin() - 0) / image.getWidth()) * 100;
                image.scalePercent(scaler);

                document.add(image);
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

        }
    }

    private class sendfeedbackValue extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();

        private static final String TAG_STATUS = "status";
        private static final String TAG_MESSAGE = "msg";

        HashMap<String, String> params = new HashMap<>();


        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(ModelViewActivity.this);
            progress.setCancelable(false);
            progress.setTitle("Please wait...");
            progress.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {


                File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Casilica");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            //  Log.i(TAG, "Created a new directory for PDF");
        }

        pdfFile = new File(docsFolder.getAbsolutePath(), "PDF-" + BasicInformation.getOffer().toString() +"-"+BasicInformation.getOfferCode().toString()+ ".pdf");

        Log.d("gfdhfhfhfghdgfhf",pdfFile.toString());

        uri = Uri.fromFile(pdfFile);


//        String fileName = sourceFileUri;
//        String fileName = String.valueOf(uri);
        String filepath = pdfFile.toString();
        String fileName = "PDF-" + BasicInformation.getOffer().toString() +"-"+BasicInformation.getOfferCode().toString()+ ".pdf";

                Log.d("fgdgdfghdfhd",filepath);
                Log.d("fgdgdfghdfhd",fileName);
                JSONObject jsonObject= uploadImage(ModelViewActivity.this,filepath,fileName);

                if (jsonObject != null) {
                    //Log.w("lavkush result", jsonObject.getString("msg"));
                    return jsonObject;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject json) {
            String message = "";
            String data = "";

            if (progress.isShowing())
                progress.dismiss();

            if (json != null) {
                try {
                    Log.d("dsfgdgdfgdfhdf",json.toString());
                    message = json.getString("msg");
                 
                   
                  
                  
                    Toast.makeText(ModelViewActivity.this, ""+"PDF Successfully Generated.", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private JSONObject uploadImage(Context context, String sourceImageFile, String fileName) {

        Log.e("sourceImageFile", ": " + sourceImageFile);
        File sourceFile = new File(sourceImageFile);
        String result = null;
        Log.e("FindPlayerPageAsync", "File...::::" + sourceFile + " : " + sourceFile.exists());
        Log.e("file name", ": " + fileName);
        JSONObject jsonObject = null;
        try {
            final MediaType MEDIA_TYPE_PNG = sourceImageFile.endsWith("pdf") ?
                    MediaType.parse("image/png") : MediaType.parse("application/pdf");

            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)

//                    .addFormDataPart("id", MyPrefrences.getEmpId(Expances.this))
//                    .addFormDataPart("date_time", date.getText().toString())
//                    .addFormDataPart("category", spinerValue)


                    .addFormDataPart("user_id", MyPreference.loadLoginid(ModelViewActivity.this))
                    .addFormDataPart("document_id", String.valueOf(fileName))
                    .addFormDataPart("file_name", fileName, RequestBody.create(MEDIA_TYPE_PNG, sourceFile))
                    .build();

//            Log.d("fdgdfghdfhdgvfdgfdfdg",MyPrefrences.getEmpId(Expances.this));
//            Log.d("fdgdfghdfhdgvfdgfdfdg",date.getText().toString());
//            Log.d("fdgdfghdfhdgvfdgfdfdg",spinerValue);
//            Log.d("fdgdfghdfhdgvfdgfdfdg",amount.getText().toString());
//            Log.d("fdgdfghdfhdgvfdgfdfdg",remark.getText().toString());
//            Log.d("fdgdfghdfhdgvfdgfdfdg",fileName);
//            Log.d("fdgdfghdfhdgvfdgfdfdg",sourceFile.toString());

            com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                    .url(Apis.save_pdfApi)
                    .post(requestBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(10, TimeUnit.SECONDS);
            client.setWriteTimeout(10, TimeUnit.SECONDS);
            client.setReadTimeout(10, TimeUnit.SECONDS);
            Log.e("request", ":url:  " + request.urlString() + ", header: " + request.headers() + ", body " + request.body());
            com.squareup.okhttp.Response response = client.newCall(request).execute();
            result = response.body().string();
            jsonObject = new JSONObject(result);
            Log.e("response", ": " + result);
            return jsonObject;
        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Log.e("FindPlayerPageAsync", "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Log.e("FindPlayerPageAsync", "Other Error: " + e.getLocalizedMessage());
        }
        return jsonObject;
    }


//    public class GetModelImage extends AsyncTask<String, JSONObject, JSONObject> {
//        Context context;
//        String designgr, dbt;
//        public double outlet_output;
//
//        public GetModelImage(Context context) {
//            this.context = context;
//            this.designgr = designgr;
//            this.dbt = dbt;
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//            JSONObject jsonObject;
//            HashMap<String, String> hashMap = new HashMap<>();
//            hashMap.put("model_no", BasicInformation.getModel().toString());
//            hashMap.put("type", BasicInformation.getPSA().toString());
//            hashMap.put("pname", "casilica");
//
//            Log.d("gdfgdghfghhfg",BasicInformation.getModel().toString());
//            Log.d("gdfgdghfghhfg",BasicInformation.getPSA().toString());
//
//            JSONParser jsonParser = new JSONParser();
//            jsonObject = jsonParser.makeHttpRequest(Apis.GET_IMGApi, "GET", hashMap);
//
//            return jsonObject;
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject obj) {
//            super.onPostExecute(obj);
//            try {
//                String status = obj.getString("status");
//                String msg = obj.getString("msg");
//                String url=obj.getString("");
//                Log.d("dfgfdgdfgdfgdf",status);
//                Log.d("dfgfdgdfgdfgdf",obj.toString());
//                if (status.equals("1")){
//                    Picasso.with(context).load(url).placeholder(R.color.colorPrimary).error(R.color.transparet_grey).into(img_model);
//
//                }
//                else {
//                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }


}