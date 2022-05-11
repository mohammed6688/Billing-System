import modules.CDR;
import modules.SiteDAO;
import modules.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        SiteDAO.connectToDB();
        //String filename = "cdr_1830208061";
        String filename =  GetLastCDR.getLastModified();
        CDR cdrData = CDRParser.parseCDR(filename);
        Rating.FIH(cdrData);
        //CDRParser.moveCDR(filename);
    }
}
