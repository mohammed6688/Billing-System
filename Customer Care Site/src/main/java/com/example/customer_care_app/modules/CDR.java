package com.example.customer_care_app.modules;

import java.sql.Date;

public class CDR {
    private int ID;
    private String A_party_MSISDN,B_party_MSISDN;
    private Date startDate;
    private int StartHour;
    private int RatePlan_Id;
    private int ServicePackage_Id;
    private int Event_Type;
    private int duration_sec ;

    //-------------------------------------Constructors-------------------------

    public CDR(int ID, String a_party_MSISDN, String b_party_MSISDN, Date startDate, int startHour, int ratePlan_Id, int servicePackage_Id, int event_Type, int duration_sec) {
        this.ID = ID;
        A_party_MSISDN = a_party_MSISDN;
        B_party_MSISDN = b_party_MSISDN;
        this.startDate = startDate;
        StartHour = startHour;
        RatePlan_Id = ratePlan_Id;
        ServicePackage_Id = servicePackage_Id;
        Event_Type = event_Type;
        this.duration_sec = duration_sec;
    }

    //---------------------------------End of Constructors----------------------
    //-------------------------------------setters------------------------------
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setA_party_MSISDN(String a_party_MSISDN) {
        A_party_MSISDN = a_party_MSISDN;
    }

    public void setB_party_MSISDN(String b_party_MSISDN) {
        B_party_MSISDN = b_party_MSISDN;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartHour(int startHour) {
        StartHour = startHour;
    }

    public void setRatePlan_Id(int ratePlan_Id) {
        RatePlan_Id = ratePlan_Id;
    }

    public void setServicePackage_Id(int servicePackage_Id) {
        ServicePackage_Id = servicePackage_Id;
    }

    public void setEvent_Type(int event_Type) {
        Event_Type = event_Type;
    }

    public void setDuration_sec(int duration_sec) {
        this.duration_sec = duration_sec;
    }
    //-----------------------------------End of setters--------------------------------

    //-----------------------------------Getters---------------------------------------

    public int getID() {
        return ID;
    }

    public String getA_party_MSISDN() {
        return A_party_MSISDN;
    }

    public String getB_party_MSISDN() {
        return B_party_MSISDN;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getStartHour() {
        return StartHour;
    }

    public int getRatePlan_Id() {
        return RatePlan_Id;
    }

    public int getServicePackage_Id() {
        return ServicePackage_Id;
    }

    public int getEvent_Type() {
        return Event_Type;
    }

    public int getDuration_sec() {
        return duration_sec;
    }
    //------------------------------------End of Getters------------------------------------
}
