import modules.CDR;
import modules.SiteDAO;
import modules.Users;
import modules.ContractCons;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JRException, FileNotFoundException {
        connectToDB();
        String filename = "cdr_1830208061";
        //String filename =  GetLastCDR.getLastModified();
        System.out.println(filename);
        CDR cdrData = CDRParser.parseCDR(filename);

//        List<ContractCons> cons = new ArrayList<>();
//        cons.add(new ContractCons("01126498473","voice",50));
//        cons.add(new ContractCons("01020546847","data",20));
//        cons.add(new ContractCons("01020546847","data",20));
//
//        Users users = new Users(123456789,"Mohammed Ashraf",24,"24-tree street cairo egypt");
//        InvoiceGenerator.generate(cons,users,300);

        try {
            Rating.FIH(cdrData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void connectToDB() {
        String DB_NAME = "Billing";
        String USER = "postgres";
  //      String PASS = "1502654";
        //String PASS = "0000";    //omar pass
//        String PASS = "1502654";    //ayman pass
        String PASS = "0000";    //abdo pass
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
