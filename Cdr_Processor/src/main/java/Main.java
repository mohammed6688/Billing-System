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
    public static void main(String[] args) throws JRException, FileNotFoundException,SQLException {
        SiteDAO.connectToDB();
        String filename = "cdr_1830208061";
        //String filename =  GetLastCDR.getLastModified();
        System.out.println(filename);
        CDR cdrData = CDRParser.parseCDR(filename);
        Rating.FIH(cdrData);
//        List<ContractCons> cons = new ArrayList<>();
//        cons.add(new ContractCons("01126498473","voice",50));
//        cons.add(new ContractCons("01020546847","data",20));
//        cons.add(new ContractCons("01020546847","data",20));
//
//        Users users = new Users(123456789,"Mohammed Ashraf",24,"24-tree street cairo egypt");
//        InvoiceGenerator.generate(cons,users,300);


    }
}
