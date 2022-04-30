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

    public List<RatePlane> getRatePlane() throws SQLException {
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

    public int addContract(String national_id, String rateplane, String msisdn) throws SQLException {
        stmt = this.con.prepareStatement("insert into bscs.contract(msisdn,rateplane_id,userid) values(?,?,?)");
        stmt.setInt(1, Integer.parseInt(msisdn));
        stmt.setInt(2, Integer.parseInt(rateplane));
        stmt.setInt(3, Integer.parseInt(national_id));

        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        System.out.println(rs);
        if (rs != null) {
            System.out.println("contract added");
            return 1;
        } else {
            return -1;
        }
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
}
