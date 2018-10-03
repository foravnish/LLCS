package com.risein.llcs.inputvalidation;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.risein.llcs.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Risein on 9/24/2016.
 */

public class ValidInput {

    public static boolean isValidDateFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
		/*if (date == null) {
			// Invalid date format
		} else {
			// Valid date format
		}*/
        return date != null;
    }

    public static boolean isTextViewEmpty(TextView mInput) {
        if (mInput.length() == 0)
            return true;
        else
            return false;
    }

    public static boolean isEditViewEmpty(EditText mInput) {
        if (mInput.length() == 0)
            return true;
        else
            return false;
    }



    public static boolean validateInput(EditText date,EditText cname,EditText pname,
                                        EditText phone,EditText addr,EditText email,EditText project,EditText consul,
                                       EditText area,Context con) {


        String inputDate=date.getText().toString();
        String inputCname=cname.getText().toString();
        String inputPname=pname.getText().toString();
        String inputPhone=phone.getText().toString();
        String inputAdd=addr.getText().toString();
        String inputEmail=email.getText().toString();
        String inputProject=project.getText().toString();
        String inputConsul=consul.getText().toString();
        //String inputOffer=offer.getText().toString();
        String inputArea=area.getText().toString();

        boolean dateformat= ValidInput.isValidDateFormat("dd-MM-yyyy",date.getText().toString());






        if (TextUtils.isEmpty(inputDate))
        {
            date.setError("Oops! Date field blank");
            date.requestFocus();
            return false;
        }

        else if ((dateformat)==false){
            date.setError("Incorrect Date Format ");
            date.requestFocus();
            return false;

        }

        else if (TextUtils.isEmpty(inputCname))
        {
            cname.setError("Oops! Client Name field blank");
            cname.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(inputPname))
        {
            pname.setError("Oops! Person Name field blank");
            pname.requestFocus();
            return false;
        }
        else if (inputPhone.length()<=5)
        {
            phone.setError("Invalid Phone no.");
            phone.requestFocus();
            return false;

        }
        else if (TextUtils.isEmpty(inputAdd))
        {
            addr.setError("Oops! Address field blank");
            addr.requestFocus();
            return false;
        }

        else if (TextUtils.isEmpty(inputEmail))
        {
            email.setError("Oops! Email field blank");
            email.requestFocus();
            return false;
        }

        else if (isEmailValid(inputEmail) == false)
        {
            email.setError("Invalid Email");
            email.requestFocus();
            return false;

        }

        else if (TextUtils.isEmpty(inputProject))
        {
            project.setError("Oops! Project name field blank");
            project.requestFocus();
            return false;
        }

        else if (TextUtils.isEmpty(inputConsul))
        {
            consul.setError("Oops! Consultant name field blank");
            consul.requestFocus();
            return false;
        }
       /* else if (TextUtils.isEmpty(inputOffer))
        {
            offer.setError("Oops! Offer no field blank");
            offer.requestFocus();
            return false;
        }*/
        else if (TextUtils.isEmpty(inputArea))
        {
            area.setError("Oops! Area field blank");
            area.requestFocus();
            return false;
        }



        return true;
    }


    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }




    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isNameValid(String name) {
        boolean isValid = false;

        String expression = "^[a-zA-Z]{1,50}$";
        CharSequence inputStr = name;

        Pattern pattern = Pattern.compile(expression, Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        } else if (name.length() > 1) {
            if (name.contains(" ")) {
                isValid = true;
            }

        }
        return isValid;
    }

    public boolean isEditTextEmpty(EditText mInput) {
        if (mInput.length() == 0)
            return true;
        else
            return false;
    }

}
