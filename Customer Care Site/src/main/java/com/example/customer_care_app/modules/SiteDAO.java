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

    public void addUser(String national_id, String name, String age, String address) throws SQLException {
        System.out.println("here");
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
//            return 1;
        } else {
//            return -1;
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
}
