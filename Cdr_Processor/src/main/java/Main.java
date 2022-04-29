import modules.SiteDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        connectToDB();
        String filename = "test";
        CDRParser.parseCDR(filename);

    }
    private static void connectToDB() {
        String DB_NAME = "billing project";
        String USER = "postgres";
        String PASS = "1502654";
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
