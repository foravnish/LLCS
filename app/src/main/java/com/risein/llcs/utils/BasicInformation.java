package com.risein.llcs.utils;

import android.util.Log;

/**
 * Created by Risein on 9/24/2016.
 */

public class BasicInformation {
    private static String date= "";
    private static String comnyname= "";
    private static String cname= "";
    private static String pname= "";
    private static String phone= "";
    private static String add= "";
    private static String mail= "";
    private static String projectname= "";
    private static String projectadd= "";
    private static String consulname= "";
    private static String offer= "";
    private static String area = "";

    //inputActivity class
    //Ambient condition
    private static String acdbt = "";
    private static String acwbt = "";
    private static String acrh = "";
    private static String acgr = "";
    private static String acbtu = "";
    //Design condition
    private static String dcdbt = "";
    private static String dcwbt = "";
    private static String dcwbt2 = "";
    private static String dcrh = "";
    private static String dcgr = "";
    private static String dcgr2 = "";
    private static String dcbtu = "";
    private static String dcbtu2 = "";

    //total room dimensions
    private static String rmlength = "";
    private static String rmwidth = "";
    private static String rmheight = "";
    private static String rmvolume = "";
    //permeation load
    private static String  permeation = "";
    //Occupacy
    private static String  occupacy = "";

    //INFILTERATION Opening
    private static String  infilteration = "";
    //INFILTERATION CFM
    private static String  infilteration_cfm = "";
    //PRODUCT load
    private static String  product = "";
    //ANYOTHER_LOAD
    private static String  anyotherload = "";
    //TOTAL_LOAD
    private static String  totalload = "";
    //SAFETY_FACTOR
    private static String  safetyfactor = "";
    //LATENT_M_LOAD
    private static String  latentload = "";
    //fresh air load
    private static String  freshair = "";
    //.......
    private static String  nperson = "";

    //pass box or door lood
    private static String  passbox = "";

    //fixed load or  moisture
    private static String  moisture = "";


    //Total latent
    private static String  tlatent = "";
    //dehumidifier
    private static String  dehumidifier = "";

    //model
    private static String  model = "";

    //faq
    private static String  faq = "";

    //ahu
    private static String  ahu = "";

    //faqdry
    private static String  faqdry = "";

    private static String  pregr = "";

    private static String  alevel = "";

    private static String  outlet = "";

    private static String  faqbtu = "";

    private static String  adp = "";

    private static String  dvalue = "";

    private static String  dbtValue = "";
    private static String  routlet = "";

    private static String  temp_val = "";


    private static String  quot_price = "";
    private static String  quot_t_price = "";
    private static String  quot_qty = "";
    private static String  quot_discount = "";


    private static String  scop1 = "";
    private static String  scop2 = "";

    private static String  q4 = "";
    private static String  q5 = "";
    private static String  q6 = "";

    private static String  PSA = "";

    private static String  offerCode = "";

    private static String  rolltype = "";

    public static void setRolltype(String rolltype) {
        BasicInformation.rolltype = rolltype;
    }

    public static String getRolltype() {
        return rolltype;
    }

    public static void setOfferCode(String offerCode) {
        BasicInformation.offerCode = offerCode;
    }

    public static String getOfferCode() {
        return offerCode;
    }

    public static String getPSA() {
        return PSA;
    }

    public static void setPSA(String PSA) {
        BasicInformation.PSA = PSA;
    }

    public static String getQ4() {
        return q4;
    }

    public static void setQ4(String q4) {
        BasicInformation.q4 = q4;
    }

    public static String getQ5() {
        return q5;
    }

    public static void setQ5(String q5) {
        BasicInformation.q5 = q5;
    }

    public static String getQ6() {
        return q6;
    }

    public static void setQ6(String q6) {
        BasicInformation.q6 = q6;
    }

    public static String getScop1() {
        return scop1;
    }

    public static void setScop1(String scop1) {
        BasicInformation.scop1 = scop1;
    }

    public static String getScop2() {
        return scop2;
    }

    public static void setScop2(String scop2) {
        BasicInformation.scop2 = scop2;
    }

    public static String getTemp_val() {
        return temp_val;
    }

    public static String getQuot_price() {
        return quot_price;
    }

    public static void setQuot_price(String quot_price) {
        BasicInformation.quot_price = quot_price;
    }

    public static String getQuot_t_price() {
        return quot_t_price;
    }

    public static void setQuot_t_price(String quot_t_price) {
        BasicInformation.quot_t_price = quot_t_price;
    }

    public static String getQuot_qty() {
        return quot_qty;
    }

    public static void setQuot_qty(String quot_qty) {
        BasicInformation.quot_qty = quot_qty;
    }

    public static void setQuot_discount(String quot_discount) {
        BasicInformation.quot_discount = quot_discount;
    }

    public static String getQuot_discount() {
        return quot_discount;
    }

    public static void setTemp_val(String temp_val) {
        BasicInformation.temp_val = temp_val;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        BasicInformation.date = date;
    }


    public static String getCompnyname() {
        return comnyname;
    }

    public static void setCompnyname(String comnyname) {
        BasicInformation.comnyname = comnyname;

    }

    public static String getCname() {
        return cname;
    }

    public static void setCname(String name) {
        Log.w("cname"," "+name);
        BasicInformation.cname = name;

    }

    public static String getPname() {
        return pname;
    }

    public static void setPname(String name) {
        BasicInformation.pname = name;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        BasicInformation.phone = phone;
    }

    public static String getAdd() {
        return add;
    }

    public static void setAdd(String add) {
        BasicInformation.add = add;
    }

    public static String getEmail() {
        return mail;
    }

    public static void setEmail(String mail) {
        BasicInformation.mail = mail;
    }

    public static String getProjectname() {
        return projectname;
    }

    public static void setProjectname(String projectname) {
        BasicInformation.projectname = projectname;
    }


    public static String getProjectadd() {
        return projectadd;
    }

    public static void setProjectadd(String projectadd) {
        BasicInformation.projectadd = projectadd;
    }



    public static String getConsulname() {
        return consulname;
    }

    public static void setConsulname(String consulname) {
        BasicInformation.consulname = consulname;
    }

    public static String getOffer() {
        return offer;
    }

    public static void setOffer(String offer) {
        BasicInformation.offer = offer;
    }



    public static String getArea() {
        return area;
    }

    public static void setArea(String area) {
        BasicInformation.area = area;
    }


    // inputActivity class save all data............

    //Ambient condition
    public static String getAcDbt() {
        return acdbt;
    }

    public static void setAcDbt(String acdbt) {
        BasicInformation.acdbt = acdbt;
    }

    public static String getAcWbt() {
        return acwbt;
    }

    public static void setAcWbt(String acwbt) {
        BasicInformation.acwbt = acwbt;
    }

    public static String getAcRh() {
        return acrh;
    }

    public static void setAcRh(String acrh) {
        BasicInformation.acrh = acrh;
    }

    public static String getAcGr() {
        return acgr;
    }

    public static void setAcGr(String acgr) {
        BasicInformation.acgr = acgr;
    }

    public static String getAcBtu() {
        return acbtu;
    }

    public static void setAcBtu(String acbtu) {
        BasicInformation.acbtu = acbtu;
    }

    //Design condition

    public static String getDcDbt() {
        return dcdbt;
    }

    public static void setDcDbt(String dcdbt) {
        BasicInformation.dcdbt = dcdbt;
    }

    public static String getDcWbt() {
        return dcwbt;
    }
    public static void setDcWbt(String dcwbt) {
        BasicInformation.dcwbt = dcwbt;
    }

    public static String getDcWbt2() {
        return dcwbt2;
    }
    public static void setDcWbt2(String dcwbt2) {
        BasicInformation.dcwbt2 = dcwbt2;
    }




    public static String getDcRh() {
        return dcrh;
    }

    public static void setDcRh(String dcrh) {
        BasicInformation.dcrh = dcrh;
    }

    public static String getDcGr() {
        return dcgr;
    }

    public static void setDcGr(String dcgr) {
        BasicInformation.dcgr = dcgr;
    }

    public static String getDcGr2() {
        return dcgr2;
    }

    public static void setDcGr2(String dcgr2) {
        BasicInformation.dcgr2 = dcgr2;
    }

    public static String getDcBtu() {
        return dcbtu;
    }
    public static void setDcBtu(String dcbtu) {
        BasicInformation.dcbtu = dcbtu;
    }


    public static String getDcBtu2() {
        return dcbtu2;
    }
    public static void setDcBtu2(String dcbtu2) {
        BasicInformation.dcbtu2 = dcbtu2;
    }


    //total room dimensions
    public static String getRmLength() {
        return rmlength;
    }

    public static void setRmLength(String rmlength) {
        BasicInformation.rmlength = rmlength;
    }

    public static String getRmWidth() {
        return rmwidth;
    }

    public static void setRmWidth(String rmwidth) {
        BasicInformation.rmwidth = rmwidth;
    }

    public static String getRmHeight() {
        return rmheight;
    }

    public static void setRmHeight(String rmheight) {
        BasicInformation.rmheight = rmheight;
    }

    public static String getRmVolume() {
        return rmvolume;
    }

    public static void setRmVolume(String rmvolume) {
        BasicInformation.rmvolume = rmvolume;
    }

    //PERMEATION calculation
    public static String getPermeation() {
        return permeation;
    }

    public static void setPermeation(String permeation) {
        BasicInformation.permeation = permeation;
    }

    //OCCUPACY calculation
    public static String getOccupacyCal() {
        return occupacy;
    }

    public static void setOccupacyCal(String occupacy) {
        BasicInformation.occupacy = occupacy;
    }

    //INFILTERATION Opening
    public static String getInfilterationLoad() {
        return infilteration;
    }

    public static void setInfilterationLoad(String infilteration) {
        BasicInformation.infilteration = infilteration;
    }

    //INFILTERATION CFM
    public static String getInfilterationLoadCfm() {
        return infilteration_cfm;
    }

    public static void setInfilterationLoadCfm(String infilteration_cfm) {
        BasicInformation.infilteration_cfm = infilteration_cfm;
    }



    //PRODUCT load
    public static String getProductLoad() {
        return product;
    }

    public static void setProductLoad(String product) {
        BasicInformation.product = product;
    }

    //ANYOTHER_LOAD
    public static String getAnyotherLoad() {
        return anyotherload;
    }

    public static void setAnyotherLoad(String anyotherload) {
        BasicInformation.anyotherload = anyotherload;
    }
    //TOTAL_LOAD
    public static String getTotalLoad() {
        return totalload;
    }

    public static void setTotalLoad(String totalload) {
        BasicInformation.totalload = totalload;
    }

    //SAFETY_FACTOR
    public static String getSafetyFactor() {
        return safetyfactor;
    }

    public static void setSafetyFactor(String safetyfactor) {
        BasicInformation.safetyfactor = safetyfactor;
    }

    //LATENT_M_LOAD
    public static String getLatentLoad() {
        return latentload;
    }

    public static void setLatentLoad(String latentload) {
        BasicInformation.latentload = latentload;
    }

    //fresh air load

    public static String getFreshAir() {
        return freshair;
    }

    public static void setFreshAir(String freshair) {
        BasicInformation.freshair = freshair;
    }
//...........
    public static String getNperson() {
        return nperson;
    }

    public static void setNperson(String nperson) {
        BasicInformation.nperson = nperson;
    }


    public static String getPassbox() {
        return  passbox;
    }

    public static void setPassbox(String  passbox) {
        BasicInformation. passbox =  passbox;
    }

    public static String getMoisture() {
        return moisture;
    }

    public static void setMoisture(String moisture) {
        BasicInformation. moisture =  moisture;
    }


    //Total LATENT_LOAD
    public static String getTLatentLoad() {
        return tlatent;
    }

    public static void setTLatentLoad(String tlatent) {
        BasicInformation.tlatent = tlatent;
    }


    //Dehumidifier
    public static String getDehumidifier() {
        return dehumidifier;
    }

    public static void setDehumidifier(String dehumidifier) {
        BasicInformation.dehumidifier = dehumidifier;
    }


    public static String getSflowDehumidifier() {
        return dehumidifier;
    }

    public static void setSflowDehumidifier(String dehumidifier) {
        BasicInformation.dehumidifier = dehumidifier;
    }

    //Model
    public static String getModel() {
        return model;
    }

    public static void setModel(String model) {
        BasicInformation.model = model;
    }

   //System flow


    public static String getFaq() {
        return faq;
    }

    public static void setFaq(String faq) {
        BasicInformation.faq = faq;
    }


    public static String getAhu() {
        return ahu;
    }

    public static void setAhu(String ahu) {
        BasicInformation.ahu = ahu;
    }


    public static String getAdp() {
        return adp;
    }

    public static void setAdp(String adp) {
        BasicInformation.adp = adp;
    }


    public static String getFaqDry() {
        return faqdry;
    }

    public static void setFaqdry(String faqdry) {
        BasicInformation.faqdry = faqdry;
    }


    public static String getPreGr() {
        return pregr;
    }

    public static void setPreGr(String pregr) {
        BasicInformation.pregr = pregr;
    }

    public static String getActivityLevel() {
        return alevel;
    }

    public static void setActivityLevel(String alevel) {
        BasicInformation.alevel = alevel;
    }


    public static String getROutlet() {
        return routlet;
    }

    public static void setROutlet(String routlet) {
        BasicInformation.routlet = routlet;
    }


    public static String getOutlet() {
        return outlet;
    }

    public static void setOutlet(String outlet) {
        BasicInformation.outlet = outlet;
    }


    public static String getFaqBtu() {
        return faqbtu;
    }

    public static void setFaqBtu(String faqbtu) {
        BasicInformation.faqbtu = faqbtu;
    }


    public static String getDvalue() {
        return dvalue;
    }

    public static void setDvalue(String dvalue) {
        BasicInformation.dvalue = dvalue;
    }


    public static String getDbtValue() {
        return dbtValue;
    }

    public static void setDbtValue(String dbtValue) {
        BasicInformation.dbtValue = dbtValue;
    }



}
