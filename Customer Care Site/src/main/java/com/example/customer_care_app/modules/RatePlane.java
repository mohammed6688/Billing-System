package com.example.customer_care_app.modules;

public class RatePlane {
    int id;
    String commercial_name;
    int voice_service;
    int cross_voice_service;
    int data_service;
    int sms_service;
    int roaming_service;
    int additional_minutes_service;
    int additional_sms_service;
    int additional_data_service;
    int additional_roaming_service;
    int fee;

    public RatePlane(int id, String commercial_name, int voice_service, int cross_voice_service, int data_service, int sms_service, int roaming_service, int additional_minutes_service, int additional_sms_service, int additional_data_service, int additional_roaming_service, int fee) {
        this.id = id;
        this.commercial_name = commercial_name;
        this.voice_service = voice_service;
        this.cross_voice_service = cross_voice_service;
        this.data_service = data_service;
        this.sms_service = sms_service;
        this.roaming_service = roaming_service;
        this.additional_minutes_service = additional_minutes_service;
        this.additional_sms_service = additional_sms_service;
        this.additional_data_service = additional_data_service;
        this.additional_roaming_service = additional_roaming_service;
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommercial_name() {
        return commercial_name;
    }

    public void setCommercial_name(String commercial_name) {
        this.commercial_name = commercial_name;
    }

    public int getVoice_service() {
        return voice_service;
    }

    public void setVoice_service(int voice_service) {
        this.voice_service = voice_service;
    }

    public int getCross_voice_service() {
        return cross_voice_service;
    }

    public void setCross_voice_service(int cross_voice_service) {
        this.cross_voice_service = cross_voice_service;
    }

    public int getData_service() {
        return data_service;
    }

    public void setData_service(int data_service) {
        this.data_service = data_service;
    }

    public int getSms_service() {
        return sms_service;
    }

    public void setSms_service(int sms_service) {
        this.sms_service = sms_service;
    }

    public int getRoaming_service() {
        return roaming_service;
    }

    public void setRoaming_service(int roaming_service) {
        this.roaming_service = roaming_service;
    }

    public int getAdditional_minutes_service() {
        return additional_minutes_service;
    }

    public void setAdditional_minutes_service(int additional_minutes_service) {
        this.additional_minutes_service = additional_minutes_service;
    }

    public int getAdditional_sms_service() {
        return additional_sms_service;
    }

    public void setAdditional_sms_service(int additional_sms_service) {
        this.additional_sms_service = additional_sms_service;
    }

    public int getAdditional_data_service() {
        return additional_data_service;
    }

    public void setAdditional_data_service(int additional_data_service) {
        this.additional_data_service = additional_data_service;
    }

    public int getAdditional_roaming_service() {
        return additional_roaming_service;
    }

    public void setAdditional_roaming_service(int additional_roaming_service) {
        this.additional_roaming_service = additional_roaming_service;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
