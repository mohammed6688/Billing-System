package com.example.customer_care_app.modules;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDAO {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    private Connection con;
    public static SiteDAO instanceData;
    PreparedStatement stmt;

    public SiteDAO(String dbname, String user, String pass) throws SQLException {
        this.connect(dbname, user, pass);
    }

    private void connect(String dbname, String user, String pass) throws SQLException {
        this.con = DriverManager.getConnection(DB_URL + dbname, user, pass);
        instanceData = this;
    }

    public int addUser(String national_id, String name, String age, String address) throws SQLException {
        stmt = this.con.prepareStatement("insert into bscs.users(national_id,u_name,age,address) values(?,?,?,?)");
        stmt.setInt(1, Integer.parseInt(national_id));
        stmt.setString(2, name);
        stmt.setInt(3, Integer.parseInt(age));
        stmt.setString(4, address);

        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        System.out.println(rs);
        if (rs != null) {
            System.out.println("user added");
            return 1;
        } else {
            return -1;
        }
    }

    public List<RatePlane> getRatePlanes() throws SQLException {
        stmt = this.con.prepareStatement("select * from bscs.rateplanes");
        ResultSet rs = stmt.executeQuery();
        List<RatePlane> ratePlanes = new ArrayList<>();

        while (rs.next()) {
            ratePlanes.add(new RatePlane(
                    rs.getInt("id"),
                    rs.getString("commercial_name"),
                    rs.getInt("voice_service"),
                    rs.getInt("cross_voice_service"),
                    rs.getInt("data_service"),
                    rs.getInt("sms_service"),
                    rs.getInt("roaming_service"),
                    rs.getInt("additional_minutes_service"),
                    rs.getInt("additional_sms_service"),
                    rs.getInt("additional_data_service"),
                    rs.getInt("additional_roaming_service"),
                    rs.getInt("fee")));
        }

        System.out.println("in get rate plane");
        return ratePlanes;
    }

    public RatePlane getRatePlane(String id) throws SQLException {
        stmt = this.con.prepareStatement("select * from bscs.rateplanes where id = ?");
        stmt.setInt(1,Integer.parseInt(id));
        ResultSet rs = stmt.executeQuery();
        List<RatePlane> ratePlanes = new ArrayList<>();

        while (rs.next()) {
            ratePlanes.add(new RatePlane(
                    rs.getInt("id"),
                    rs.getString("commercial_name"),
                    rs.getInt("voice_service"),
                    rs.getInt("cross_voice_service"),
                    rs.getInt("data_service"),
                    rs.getInt("sms_service"),
                    rs.getInt("roaming_service"),
                    rs.getInt("additional_minutes_service"),
                    rs.getInt("additional_sms_service"),
                    rs.getInt("additional_data_service"),
                    rs.getInt("additional_roaming_service"),
                    rs.getInt("fee")));
        }

        if (ratePlanes.size()!=0){
            return ratePlanes.get(0);
        }else {
            return null;
        }
    }

    public int addContract(String national_id, String rateplane, String msisdn,String discount, String freeUnits) throws SQLException {
        stmt = this.con.prepareStatement("insert into bscs.contract(msisdn,rateplane_id,userid,discount,additional_sp) values(?,?,?,?,?)");
        stmt.setString(1, msisdn);
        stmt.setInt(2, Integer.parseInt(rateplane));
        stmt.setInt(3, Integer.parseInt(national_id));
        stmt.setInt(4, Integer.parseInt(discount));
        stmt.setInt(5, Integer.parseInt(freeUnits));

        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        System.out.println(rs);
        if (rs != null) {
            System.out.println("contract added");
            RatePlane uRatePlane=getRatePlane(rateplane);
            updateUsage(freeUnits,uRatePlane,national_id);
            return 1;
        } else {
            return -1;
        }
    }

    private void updateUsage(String freeUnits, RatePlane rateplane,String uid) throws SQLException {
        List<ServicePackage> servicePackages =getServicePackage();
        int freeUnit=getFreeUnit(freeUnits);
        stmt = this.con.prepareStatement("update bscs.contract set current_voice = ? , current_cross_voice = ? ,current_data = ? , current_sms = ? ,current_roaming=?,current_additional_sp=? where userid = ?;");
        stmt.setInt(1, getServiceUnits(rateplane.getVoice_service(),servicePackages));
        stmt.setInt(2, getServiceUnits(rateplane.getCross_voice_service(),servicePackages));
        stmt.setInt(3, getServiceUnits(rateplane.getData_service(),servicePackages));
        stmt.setInt(4, getServiceUnits(rateplane.getSms_service(),servicePackages));
        stmt.setInt(5, getServiceUnits(rateplane.getRoaming_service(),servicePackages));
        stmt.setInt(6, freeUnit);
        stmt.setInt(7, Integer.parseInt(uid));

        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        System.out.println(rs);
        if (rs != null) {
            System.out.println("usage updated");
        } else {
            System.out.println("error while updating usage");
        }
    }

    private int getFreeUnit(String freeUnits) throws SQLException {
        stmt = this.con.prepareStatement("select * from bscs.service_package where id = ?");
        stmt.setInt(1,Integer.parseInt(freeUnits));
        ResultSet rs = stmt.executeQuery();
        List<ServicePackage> servicePackage = new ArrayList<>();

        while (rs.next()) {
            servicePackage.add(new ServicePackage(
                    rs.getInt("id"),
                    rs.getString("service_type"),
                    rs.getInt("units")
            ));
        }
        return servicePackage.get(0).getUnits();
    }

    private int getServiceUnits(int voice_service,List<ServicePackage> servicePackages) throws SQLException {
        for (ServicePackage servicePackage : servicePackages){
            if (servicePackage.getId()==voice_service){
                return servicePackage.getUnits();
            }
        }
        return 0;
    }

    public List<Users> getUsers() throws SQLException {
        stmt = this.con.prepareStatement("select * from bscs.users");
        ResultSet rs = stmt.executeQuery();
        List<Users> users = new ArrayList<>();

        while (rs.next()) {
            users.add(new Users(
                    rs.getInt("national_id"),
                    rs.getString("u_name"),
                    rs.getInt("age"),
                    rs.getString("address")
            ));
        }
        return users;
    }

    public List<ServicePackage> getServicePackage() throws SQLException {
        stmt = this.con.prepareStatement("select * from bscs.service_package");
        ResultSet rs = stmt.executeQuery();
        List<ServicePackage> servicePackage = new ArrayList<>();

        while (rs.next()) {
            servicePackage.add(new ServicePackage(
                    rs.getInt("id"),
                    rs.getString("service_type"),
                    rs.getInt("units")
            ));
        }
        return servicePackage;
    }
    public List<ServicePackage> getServicePackage(int service) throws SQLException {
        String str = null;
        switch (service){
            case 1:
                str = "voice";
                break;
            case 2:
                str = "cross_voice";
                break;
            case 3:
                str = "data";
                break;
            case 4:
                str = "sms";
                break;
            case 5:
                str = "roaming";
                break;

        }
        stmt = this.con.prepareStatement("select * from bscs.service_package where service_type = '"+str+"'");
        ResultSet rs = stmt.executeQuery();
        List<ServicePackage> servicePackage = new ArrayList<>();

        while (rs.next()) {
            servicePackage.add(new ServicePackage(
                    rs.getInt("id"),
                    rs.getString("service_type"),
                    rs.getInt("units")
            ));
        }
        return servicePackage;
    }

    public int addServicePackage(String type, String units) throws SQLException {
        stmt = this.con.prepareStatement("insert into bscs.service_package(service_type,units) values(?,?)");
        stmt.setString(1, type);
        stmt.setInt(2, Integer.parseInt(units));

        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        System.out.println(rs);
        if (rs != null) {
            System.out.println("service package added");
            return 1;
        } else {
            return -1;
        }
    }
    public int addRatePlan (RatePlane ratePlane) throws SQLException {
        stmt = this.con.prepareStatement("insert into bscs.rateplanes(commercial_name,voice_service,cross_voice_service," +
                                            "data_service,sms_service,roaming_service,additional_minutes," +
                                            "additional_sms,additional_data,additional_roaming,fee)" +
                                            " values(?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, ratePlane.getCommercial_name());
        stmt.setInt(2, ratePlane.getVoice_service());
        stmt.setInt(3,ratePlane.getCross_voice_service());
        stmt.setInt(4, ratePlane.getData_service());
        stmt.setInt(5, ratePlane.getSms_service());
        stmt.setInt(6, ratePlane.getRoaming_service());
        stmt.setInt(7,ratePlane.getAdditional_minutes_service() );
        stmt.setInt(8, ratePlane.getAdditional_sms_service());
        stmt.setInt(9, ratePlane.getAdditional_data_service());
        stmt.setInt(10, ratePlane.getAdditional_roaming_service());
        stmt.setInt(11, ratePlane.getFee());
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        System.out.println(rs);
        if (rs != null) {
            System.out.println("service package added");
            return 1;
        } else {
            return -1;
        }
    }

    public List<ServicePackage> getFreeUnits() throws SQLException {
        stmt = this.con.prepareStatement("select * from bscs.service_package where service_type = ?");
        stmt.setString(1,"free");
        ResultSet rs = stmt.executeQuery();
        List<ServicePackage> servicePackage = new ArrayList<>();

        while (rs.next()) {
            servicePackage.add(new ServicePackage(
                    rs.getInt("id"),
                    rs.getString("service_type"),
                    rs.getInt("units")
            ));
        }
        return servicePackage;
    }
}
