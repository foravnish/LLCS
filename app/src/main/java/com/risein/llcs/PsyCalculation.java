package com.risein.llcs;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by Risein on 10/6/2016.
 */

public class PsyCalculation {

     /* String Tdb = null,Twb= null,h= null,SI= null;
        double valueis= PsyCalculation.psych(101325,Tdb,30,Twb,20,h,SI);
        Toast.makeText(MainActivity.this," value is "+valueis,Toast.LENGTH_LONG).show();*/

    public static double partpress(double P,double W){
/*
            ''' Function to compute partial vapor pressure in [kPa]
    From page 6.9 equation 38 in ASHRAE Fundamentals handbook (2005)
    P = ambient pressure [kPa]
    W = humidity ratio [kg/kg dry air]
            '''*/
        double result = P * W / (0.62198 + W);
        Log.d("partpress",">>>");
        return result;
    }


    public static double satpress(double Tdb){
        double result;
      /*      ''' Function to compute saturation vapor pressure in [kPa]
    ASHRAE Fundamentals handbood (2005) p 6.2, equation 5 and 6
    Tdb = Dry bulb temperature [degC]
    Valid from -100C to 200 C
    '''*/

        double C1 = -5674.5359;
        double C2 = 6.3925247;
        double C3 = -0.009677843;
        double C4 = 0.00000062215701;
        double C5 = 2.0747825E-09;
        double C6 = -9.484024E-13;
        double C7 = 4.1635019;
        double C8 = -5800.2206;
        double C9 = 1.3914993;
        double C10 = -0.048640239;
        double C11 = 0.000041764768;
        double C12 = -0.000000014452093;
        double C13 = 6.5459673;

        double TK = Tdb + 273.15 ;                   /* # Converts from degC to degK*/

        if (TK <= 273.15) {
            result = Math.exp(C1 / TK + C2 + C3 * TK + C4 * Math.pow(TK,2) + C5 * Math.pow(TK,3) +
                    C6 * Math.pow(TK,4) + C7 * Math.log(TK))/1000;
        }
        else {
            result = Math.exp(C8 / TK + C9 + C10 * TK + C11 * Math.pow(TK,2) + C12 * Math.pow(TK,3) +
                    C13 * Math.log(TK))/1000;
        }
        Log.d("satpress",">>>"+result);
        return result;
    }


    public static double Hum_rat(double Tdb, double Twb, double P){

           /* ''' Function to calculate humidity ratio [kg H2O/kg air]
    Given dry bulb and wet bulb temp inputs [degC]
    ASHRAE Fundamentals handbood (2005)
    Tdb = Dry bulb temperature [degC]
    Twb = Wet bulb temperature [degC]
    P = Ambient Pressure [kPa]
            '''*/

        double result;
        double Pws = satpress(Twb);
        double Ws = 0.62198 * Pws / (P - Pws) ;        /* # Equation 23, p6.8*/
        if (Tdb >= 0 ){                           /* # Equation 35, p6.9*/
            result = (((2501 - 2.326 * Twb) * Ws - 1.006 * (Tdb - Twb)) /
                    (2501 + 1.86 * Tdb - 4.186 * Twb));
        }
        else{                                  /* # Equation 37, p6.9*/
            result = (((2830 - 0.24 * Twb) * Ws - 1.006 * (Tdb - Twb)) /
                    (2830 + 1.86 * Tdb - 2.1 * Twb));
        }
        Log.d("Hum_rat",">>>"+result);

        return result;
    }



    public static double Hum_rat2(double Tdb, double RH, double P){
/*
            ''' Function to calculate humidity ratio [kg H2O/kg air]
    Given dry bulb and wet bulb temperature inputs [degC]
    ASHRAE Fundamentals handbood (2005)*/
   /* Tdb = Dry bulb temperature [degC]
    RH = Relative Humidity [Fraction or %]
    P = Ambient Pressure [kPa]
            '''*/
        double Pws = satpress(Tdb);
        double result = 0.62198*RH*Pws/(P - RH*Pws);   /* # Equation 22, 24, p6.8*/
        return result;
    }


    public static double Rel_hum(double Tdb, double Twb, double P){

     /*       ''' Calculates relative humidity ratio
    ASHRAE Fundamentals handbood (2005)
    Tdb = Dry bulb temperature [degC]
    Twb = Wet bulb temperature [degC]
    P = Ambient Pressure [kPa]
            '''*/

        double W = Hum_rat(Tdb, Twb, P);
        double result = partpress(P, W) / satpress(Tdb);   /*# Equation 24, p6.8*/
        return result;
    }

    public static double Rel_hum2(double Tdb, double W, double P) {

       /* '' ' Calculates the relative humidity given:
        Tdb = Dry bulb temperature[ degC]
        P = ambient pressure[kPa]
        W = humidity ratio[kg / kg dry air]
        '' '*/

        double Pw = partpress(P, W);
        double Pws = satpress(Tdb);
        double result = Pw / Pws;
        return result;
    }

    public static double Wet_bulb(double Tdb,double RH, double P){
/*
            ''' Calculates the Wet Bulb temp given:
    Tdb = Dry bulb temperature [degC]
    RH = Relative humidity ratio [Fraction or %]
    P = Ambient Pressure [kPa]
    Uses Newton-Rhapson iteration to converge quickly
    '''*/
        double result;
        double W_normal = Hum_rat2(Tdb, RH, P);
        result = Tdb;

   /* ' Solves to within 0.001% accuracy using Newton-Rhapson'*/
        double W_new = Hum_rat(Tdb, result, P);
        while (Math.abs((W_new - W_normal) / W_normal) > 0.00001) {
            double W_new2 = Hum_rat(Tdb, result - 0.001, P);
            double dw_dtwb = (W_new - W_new2) / 0.001;
            result = result - (W_new - W_normal) / dw_dtwb;
            W_new = Hum_rat(Tdb, result, P);
        }
        return result;
    }
    public static double Enthalpy_Air_H2O(double Tdb, double W){

           /* ''' Calculates enthalpy in kJ/kg (dry air) given:
    Tdb = Dry bulb temperature [degC]
    W = Humidity Ratio [kg/kg dry air]
    Calculations from 2005 ASHRAE Handbook - Fundamentals - SI P6.9 eqn 32
            '''*/

        double result = 1.006*Tdb + W*(2501 + 1.86*Tdb);
        return result;
    }

    public static double T_drybulb_calc(double h,double W){

        /*    ''' Calculates dry bulb Temp in deg C given:
    h = enthalpy [kJ/kg K]
    W = Humidity Ratio [kg/kg dry air]
    back calculated from enthalpy equation above
    ***Warning 0 state for Imp is ~0F, 0% RH ,and  1 ATM, 0 state
    for SI is 0C, 0%RH and 1 ATM
    '''*/
        double result = (h-(2501*W))/(1.006+(1.86*W));
        return result;
    }

    public static double Dew_point(double P,double W){

           /* ''' Function to compute the dew point temperature (deg C)*/
    /*From page 6.9 equation 39 and 40 in ASHRAE Fundamentals handbook (2005)
    P = ambient pressure [kPa]
    W = humidity ratio [kg/kg dry air]
    Valid for Dew Points less than 93 C
    '''
*/
        double result;
        double C14 = 6.54;
        double C15 = 14.526;
        double C16 = 0.7389;
        double C17 = 0.09486;
        double C18 = 0.4569;

        double Pw = partpress(P,W);
        double alpha = Math.log(Pw);
        double Tdp1 = C14 + C15*alpha + C16*Math.pow(alpha,2)+ C17*Math.pow(alpha,3) + C18*Math.pow(Pw,0.1984);
        double Tdp2 = 6.09 + 12.608*alpha + 0.4959*Math.pow(alpha,2);
        if (Tdp1 >= 0){
            result = Tdp1;
        }
        else{
            result = Tdp2;
        }
        return result;
    }

    public static double Dry_Air_Density(double P, double Tdb, double W){

            /*''' Function to compute the dry air density (kg_dry_air/m**3), using pressure
            [kPa], temperature [C] and humidity ratio
    From page 6.8 equation 28 ASHRAE Fundamentals handbook (2005)
            [rho_dry_air] = Dry_Air_Density(P, Tdb, w)
    Note that total density of air-h2o mixture is:
    rho_air_h2o = rho_dry_air * (1 + W)
    gas constant for dry air
    '''*/

        double R_da = 287.055;
        double result = 1000*P/(R_da*(273.15 + Tdb)*(1 + 1.6078*W));
        return result;
    }

    // main function for psy

    public static double psych(double P,String in0Type,double in0Val,String in1Type,double in1Val,String outType,String unitType) {
        String unitType1 = "Imp";
        double Tdb = 0,W = 0,Twb = 0,Dew=0,RH=0,h = 0;

        double outVal = 0;
        if ((in0Type != "h") && (in0Type!="W")&&(in0Type!="Tdb")) {
            Log.d("in0Type 1st if",">>>");
            outVal = 0;

        }
        else if (in0Type==in1Type) {
            Log.d("in0Type 1st elseif",">>>");
            outVal = 0;
        }

        if (unitType == "SI") {

            P = P / 1000;  /*#converts P to kPa*/
            Log.d("in0Type si",">>>"+P);

            if (in0Type == "Tdb") {
           /*:#assign the first input*/
                Tdb = in0Val;
                Log.d("in0Type tdp",">>>"+Tdb);
            }
            else if (in0Type=="W") {
                W = in0Val;
                Log.d("in0Type W",">>>"+W);
            }
            else if (in0Type=="h") {
                h = in0Val;
                Log.d("in0Type h",">>>"+h);
            }

            if (in1Type == "Tdb") {      /*:#assign the second input*/
                Tdb = in1Val;
                Log.d("in1Type Tdb",">>>"+Tdb);
            }
            else if (in1Type=="Twb") {
                Twb = in1Val;
                Log.d("in1Type Twb",">>>"+Twb);
            }
            else if (in1Type=="DP") {
                Dew = in1Val;
                Log.d("in1Type DP",">>>");
            }
            else if (in1Type=="RH") {
                RH = in1Val;
            }
            else if (in1Type=="W") {
                W = in1Val;
                Log.d("in1Type W",">>>");
            }
            else if (in1Type=="h") {
                h = in1Val;
                Log.d("in1Type h",">>>");
            }
        }
        else{
            Log.d("else in0Type Tdb",">>>");
            /*:#converts to SI if not already*/
            P = (P * 4.4482216152605) / (Math.pow(0.0254,2)* 1000);
            if (in0Type == "Tdb") {
                Tdb = (in0Val - 32) / 1.8;
            }
            else if (in0Type=="W") {
                W = in0Val;
            }
            else if (in0Type=="h") {
                h = ((in0Val * 1.055056) / 0.45359237) - 17.884444444;
            }

            if (in1Type == "Tdb") {
                Tdb = (in1Val - 32) / 1.8;
            }
            else if (in1Type=="Twb") {
                Twb = (in1Val - 32) / 1.8;
            }
            else if (in1Type=="DP") {
                Dew = (in1Val - 32) / 1.8;
            }
            else if (in1Type=="RH") {
                RH = in1Val;
            }
            else if (in1Type=="W") {
                W = in1Val;
            }
            else if (in1Type=="h"){
                h = ((in1Val * 1.055056) / 0.45359237) - 17.884444444;
            }
        }


        if (in0Type == "h" && in1Type=="W"){   /*:#calculate Tdb if not given*/
            Tdb = T_drybulb_calc(h, W);
        }
        else if(in0Type == "W" && in1Type == "h") {
            Tdb = T_drybulb_calc(h, W);

        }


        if (outType == "RH" || outType=="Twb") {   /* :#Find RH*/
            if (in1Type == "Twb") {           /*:#given Twb*/
                RH = Rel_hum(Tdb, Twb, P);
                Log.d("RH",">>>"+RH);
            }

            else if (in1Type=="DP") {    /* :#given Dew*/
                RH = satpress(Dew)/satpress(Tdb);
            }

               /* #elif in1Type=='RH':#given RH
                #RH already Set*/
            else if (in1Type=="W") {        /* :#given W*/
                RH = partpress(P, W) / satpress(Tdb);
            }
            else if (in1Type=="h") {
                W = (1.006 * Tdb - h) / (-(2501 + 1.86 * Tdb));
                RH = partpress(P, W) / satpress(Tdb);

            }
        }
        else{
            if (in0Type != "W"){       /* :#Find W*/
                if (in1Type == "Twb" ){    /*:#Given Twb*/
                    W = Hum_rat(Tdb, Twb, P);
                }

                else if (in1Type=="DP"){            /* :#Given Dew*/
                    W = 0.621945 * satpress(Dew) / (P - satpress(Dew));
            /*' Equation taken from eq 20 of 2009 Fundemental chapter 1'*/
                }

                else if (in1Type=="RH"){    /*:#Given RH*/
                    W = Hum_rat2(Tdb, RH, P);
           /* #elif in1Type=='W':#Given W
            #W already known*/
                }

                else if (in1Type=="h"){          /*:#Given h*/
                    W = (1.006 * Tdb - h) / (-(2501 + 1.86 * Tdb));
           /* ' Algebra from 2005 ASHRAE Handbook - Fundamentals - SI P6.9 eqn 32'*/
                }
            }
            else
                //Toast.makeText()

                Log.e("my data is ","invalid input varilables");
            /*printf("invalid input varilables");*/
        }

            /*#P, Tdb, and W are now availible*/

        if (outType == "Tdb"){
            outVal = Tdb;
        }
        else if (outType=="Twb"){      /* :#Request Twb*/
            outVal = Wet_bulb(Tdb, RH, P);
        }
        else if (outType=="DP"){      /* :#Request Dew*/
            outVal = Dew_point(P, W);
        }
        else if (outType=="RH") {                  /*:#Request RH*/
            outVal = RH;
        }
        else if (outType=="W"){     /* :#Request W*/
            outVal = W;
        }
        else if (outType=="WVP"){                   /* :#Request Pw*/
            outVal = partpress(P, W) * 1000;
        }
        else if (outType=="DSat"){   /*:#Request deg of sat*/
            outVal = W / Hum_rat2(Tdb, 1, P);
        }
            /*'the middle arg of Hum_rat2 is suppose to be RH.  RH is suppose to be 100%'*/
        else if (outType=="h") {           /* :#Request enthalpy*/
            outVal = Enthalpy_Air_H2O(Tdb, W);
            Log.d("Enthalpy_Air_H2O is", ">>>" + outVal);
        }
        else if (outType=="s"){         /*:#Request entropy*/
            outVal = 5 / 0;
        }
           /* 'don\'t have equation for Entropy, so I divided by zero to induce an error.'*/
        else if (outType=="SV"){          /* :#Request specific volume*/
            outVal = 1 / (Dry_Air_Density(P, Tdb, W));
        }
        else if (outType=="MAD") {                               /* :#Request density*/
            outVal = Dry_Air_Density(P, Tdb, W) * (1 + W);
        }


        if (unitType == "Imp" ) {                     /*:#Convert to Imperial units*/
            if (outType == "Tdb" || outType=="Twb" || outType=="DP"){
                outVal = 1.8 * outVal + 32;
            }

            else if (outType=="WVP"){
                outVal = outVal * Math.pow(0.0254,2)/ 4.448230531;
            }

            else if (outType=="h"){
                outVal = (outVal + 17.88444444444) * 0.45359237 / 1.055056;
            }

            else if (outType=="SV"){
                outVal = outVal * 0.45359265 / (Math.pow((12 * 0.0254),3));
            }

            else if (outType=="MAD"){
                outVal = outVal * Math.pow((12 * 0.0254),3) / 0.45359265;
            }
        }

        return outVal;
    }
}
