import modules.CDR;
import modules.SiteDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        connectToDB();
        String filename = "cdr_1830208061";
        CDR cdrData = CDRParser.parseCDR(filename);

        try {
            Rating.FIH(cdrData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private static void connectToDB() {
        String DB_NAME = "Billing";
        String USER = "postgres";
        String PASS = "0000";
//        String PASS = "1502654";    //omar pass
//        String PASS = "1502654";    //ayman pass
//        String PASS = "1502654";    //abdo pass
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            new SiteDAO(DB_NAME,USER,PASS);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("database connection error "+ e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
