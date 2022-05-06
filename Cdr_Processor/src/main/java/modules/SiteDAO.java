package modules;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDAO {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    private Connection con;
    public static SiteDAO instanceData;
    PreparedStatement stmt;
    Statement NStmt;

    public SiteDAO(String dbname, String user, String pass) throws SQLException {
        this.connect(dbname, user, pass);
    }

    private void connect(String dbname, String user, String pass) throws SQLException {
        this.con = DriverManager.getConnection(DB_URL + dbname, user, pass);
        this.NStmt= this.con.createStatement();
        instanceData = this;
    }

    public void addUser(String national_id, String name, String age, String address) throws SQLException {
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

    public List<RatePlane> getRatePlane(int rpid) throws SQLException {
        stmt = this.con.prepareStatement("select * from bscs.rateplanes where id = ?");
        stmt.setInt(1,rpid);
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

    public void addContract(String national_id, String rateplane, String msisdn) throws SQLException {
        stmt = this.con.prepareStatement("insert into bscs.contract(msisdn,rateplane_id,userid) values(?,?,?)");
        stmt.setInt(1, Integer.parseInt(msisdn));
        stmt.setInt(2, Integer.parseInt(rateplane));
        stmt.setInt(3, Integer.parseInt(national_id));

        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        System.out.println(rs);
        if (rs != null) {
            System.out.println("contract added");
//            return 1;
        } else {
//            return -1;
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

    public Integer getDiscount(String msisdn) throws SQLException {
        stmt = this.con.prepareStatement("select discount from bscs.contract where msisdn = ?");
        stmt.setString(1, msisdn);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            return rs.getInt("discount");
        }
        return -1;
    }

    public Integer getAddFreeUnits(String msisdn) throws SQLException {
        //     stmt = this.con.prepareStatement("select units from bscs.service_package as s inner join bscs.contract as con ON con.msisdn = ? and con.additional_sp= s.id; ");
        stmt = this.con.prepareStatement("select current_additional_sp from bscs.contract as con where con.msisdn = ? ");

        stmt.setString(1, msisdn);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            return rs.getInt("current_additional_sp");
        }
        return -1;
    }

    public String getService(int id) throws SQLException {
        //     stmt = this.con.prepareStatement("select units from bscs.service_package as s inner join bscs.contract as con ON con.msisdn = ? and con.additional_sp= s.id; ");
        stmt = this.con.prepareStatement("select service_type from bscs.service_package  where id = ? ");

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            return rs.getString("service_type");
        }
        return null;
    }

    public int getUnits(String msisdn, String str) throws SQLException {
        System.out.println(str);
        // stmt = this.con.prepareStatement("select current_voice from bscs.contract  where msisdn = ? ");
        String SQL = "select "+str+" from bscs.contract  where msisdn ='"+msisdn+"' ;";

        ResultSet rs = NStmt.executeQuery(SQL);
        //  stmt.setString(1,str);
//     stmt.setString(1,msisdn);
//     stmt.setc
        //    ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            return rs.getInt(str);
        }
        return -1;
    }
    public void setUnits(String msisdn,String str,int nduration,int nfree) throws SQLException {
//     stmt = this.con.prepareStatement("update bscs.contracts SET ?=?,current_additional_sp=? where msisdn=?");
//     stmt.setString(1,str);
//     stmt.setInt(2, nduration);
//     stmt.setInt(3,nfree);
//     stmt.setString(4, msisdn);

        NStmt.executeUpdate("update bscs.contract SET "+str+" = "+nduration+",current_additional_sp= "+nfree+"  where msisdn ='"+msisdn+"' ;");
        //  stmt.executeUpdate();
        ResultSet rs = NStmt.getGeneratedKeys();

        System.out.println(rs);
        if (rs != null) {
            System.out.println("Rating added");

//            return 1;
        } else {
//            return -1;
        }
    }
    public void setRTX(CDR cdr) throws SQLException {
        stmt = this.con.prepareStatement("insert into rtx.consumption (source_msisdn,terminated_msisdn,time_stamp," +
                "duration,rate,service_id,rateplane_id) values(?,?,?,?,?,?,?)");
        stmt.setString(1, cdr.getSource_msisdn());
        stmt.setString(2, cdr.getTerminated_msisdn());
        stmt.setString(3,cdr.getTimestamp());
        stmt.setInt(4, cdr.getDuration());
        stmt.setInt(5, cdr.getRate());
        stmt.setInt(6, cdr.getService_id());
        stmt.setInt(7, cdr.getRatePlan_id());

        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        System.out.println(rs);
        if (rs != null) {
            System.out.println("Rating added");
//            return 1;
        } else {
//            return -1;
        }
    }

    public Contract getContract(String source_msisdn) throws SQLException {
        stmt = this.con.prepareStatement("select * from bscs.contract where msisdn = ?");
        stmt.setString(1,source_msisdn);
        ResultSet rs = stmt.executeQuery();
        List<Contract> contract = new ArrayList<>();

        while (rs.next()) {
            contract.add(new Contract(
                    rs.getInt("contract_id"),
                    rs.getInt("msisdn"),
                    rs.getInt("rateplane_id"),
                    rs.getInt("userid"),
                    rs.getInt("additional_sp"),
                    rs.getInt("current_voice"),
                    rs.getInt("current_cross_voice"),
                    rs.getInt("current_data"),
                    rs.getInt("current_sms"),
                    rs.getInt("current_roaming"),
                    rs.getInt("discount"),
                    rs.getInt("current_additional_sp")
            ));
        }
        if (contract.size()!=0){
            return contract.get(0);
        }else {
            return null;
        }
    }
}
