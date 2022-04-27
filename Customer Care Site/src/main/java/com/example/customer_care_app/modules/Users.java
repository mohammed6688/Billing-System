package com.example.customer_care_app.modules;

public class Users {
    int national_id;
    String u_name;
    int age;
    String address;

    public Users(int national_id, String u_name, int age, String address) {
        this.national_id = national_id;
        this.u_name = u_name;
        this.age = age;
        this.address = address;
    }

    public int getNational_id() {
        return national_id;
    }

    public void setNational_id(int national_id) {
        this.national_id = national_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
