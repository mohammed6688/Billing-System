package com.example.customer_care_app.modules;

public class ServicePackage {
    int id;
    String service_type;
    int units;

    public ServicePackage(int id, String service_type, int units) {
        this.id = id;
        this.service_type = service_type;
        this.units = units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
