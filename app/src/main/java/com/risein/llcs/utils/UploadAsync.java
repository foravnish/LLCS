package com.risein.llcs.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;


/**
 * Created by user on 9/29/2016.
 */
public class UploadAsync extends AsyncTask<String, String, String> {
    private ProgressDialog pd;
    private Context c;
    private String path;
    String url = "", fileName;
    static String TAG = "UploadAsync";

    public UploadAsync(Context c, String url, String path, String fileName) {
        this.c = c;
        this.path = path;
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = uploadImage(url, path, fileName);
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = ProgressDialog.show(c, "Uploading", "Please Wait");
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        pd.dismiss();
        if (result != null) {
            JSONObject jsonObject;
            //c.startActivity(new Intent(c, InputsheetActivity.class));
            try {
                jsonObject = new JSONObject(result);
                if (jsonObject.getString("status").equals("1")) {
                    Toast.makeText(c, "Send Successfully", Toast.LENGTH_SHORT).show();
                    //c.startActivity(new Intent(c, InputsheetActivity.class));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    ///////////////
    public static String uploadImage(String url, String sourceImageFile, String fileName) {

        try {
            File sourceFile = new File(sourceImageFile);

            Log.e(TAG, "File...::::" + sourceFile + " : " + sourceFile.exists());

            final MediaType MEDIA_TYPE_PNG = MediaType.parse("pdf");

            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("file_name", fileName, RequestBody.create(MEDIA_TYPE_PNG, sourceFile))
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            Log.e("response", ": " + response.body().string());
            return response.body().string();

        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Log.e(TAG, "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
        }
        return null;
    }
}
