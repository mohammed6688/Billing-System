package com.example.customer_care_app.modules;

import java.sql.*;

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

}
