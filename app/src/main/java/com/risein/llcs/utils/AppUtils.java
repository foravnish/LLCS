package com.risein.llcs.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.risein.llcs.ModelViewActivity;
import com.risein.llcs.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import uk.co.senab.photoview.PhotoViewAttacher;

public class AppUtils {


	public static final String STARTTIMEIS = "firsttimestart";
	public static final String FIRSTRUN = "firstrun";
	public static final String PLAY_LIST_AARTI = "aarti_in_playlist";
	public static final String DATA_BASE_FLAG = "db_flag";


	public static void showFullImageDialog(Context context, String imageid, String titlename) {
		final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
		dialog.getWindow()
				.getAttributes().windowAnimations = R.style.DialogAnimation;
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.showfullimage);
		ImageView back_img = (ImageView) dialog.findViewById(R.id.back_img);
		ImageView zoom_image = (ImageView) dialog.findViewById(R.id.fact_image);
		AppUtils.showImage(context,imageid,zoom_image);
		//zoom_image.setImageResource(imageid);
		PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(zoom_image);
		//photoViewAttacher.onDrag(2,2);
		photoViewAttacher.update();
		TextView title = (TextView) dialog.findViewById(R.id.title);
		title.setText(titlename);
		back_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.cancel();
			}
		});
		dialog.show();
	}



	public static String imagename(Context context, Uri currImageURI) {
		String displayName = "";
		File file = new File(currImageURI.toString());
		String uriString = currImageURI.toString();
		if (uriString.startsWith("content://")) {
			Cursor cursor = null;
			try {
				cursor = context.getContentResolver().query(currImageURI, null, null, null, null);
				if (cursor != null && cursor.moveToFirst()) {
					displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
					Log.e("display name content", ": " + displayName);
				}
			} finally {
				cursor.close();
			}
		} else if (uriString.startsWith("file://")) {
			displayName = file.getName();
			Log.e("display name file", ": " + displayName);
		}
		Log.e("display name ", ": " + displayName);
		return displayName;
	}


	public static String getPathFromUri(final Context context, final Uri uri) {
		boolean isAfterKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
		// DocumentProvider
		///storage/emulated/0/Download/Amit-1.pdf
		Log.e("Uri Authority ", "uri:" + uri.getAuthority());
		if (isAfterKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			if ("com.android.externalstorage.documents".equals(
					uri.getAuthority())) {// ExternalStorageProvider
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];
				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				} else {
					return "/stroage/" + type + "/" + split[1];
				}
			} else if ("com.android.providers.downloads.documents".equals(
					uri.getAuthority())) {// DownloadsProvider
				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
				return getDataColumn(context, contentUri, null, null);
			} else if ("com.android.providers.media.documents".equals(
					uri.getAuthority())) {// MediaProvider
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];
				Uri contentUri = null;
				contentUri = MediaStore.Files.getContentUri("external");
				final String selection = "_id=?";
				final String[] selectionArgs = new String[]{
						split[1]
				};
				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		} else if ("content".equalsIgnoreCase(uri.getScheme())) {//MediaStore
			return getDataColumn(context, uri, null, null);
		} else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
			return uri.getPath();
		}
		return null;
	}


	public static String getDataColumn(Context context, Uri uri, String selection,
									   String[] selectionArgs) {
		Cursor cursor = null;
		final String[] projection = {
				MediaStore.Files.FileColumns.DATA
		};
		try {
			cursor = context.getContentResolver().query(
					uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int cindex = cursor.getColumnIndexOrThrow(projection[0]);
				return cursor.getString(cindex);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}


	public static void showImage(Context context, String url, ImageView imageView) {
		if (!url.isEmpty() && url != null) {
			Picasso.with(context).load(url).placeholder(R.color.colorPrimary).error(R.color.colorRedittxt).into(imageView);
		}

	}

	public static Bitmap getImageBitmapFromUrl(String src)
	{
		Bitmap bm = null;
		try {
			URL url = new URL(src);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			if(conn.getResponseCode() != 200)
			{
				return bm;
			}
			conn.connect();
			InputStream is = conn.getInputStream();

			BufferedInputStream bis = new BufferedInputStream(is);
			try
			{
				bm = BitmapFactory.decodeStream(bis);
			}
			catch(OutOfMemoryError ex)
			{
				bm = null;
			}
			bis.close();
			is.close();
		} catch (Exception e) {}

		return bm;
	}


	public static boolean isExternalStorageReadOnly() {
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
			return true;
		}
		return false;
	}

	public static boolean isExternalStorageAvailable() {
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
			return true;
		}
		return false;
	}

}