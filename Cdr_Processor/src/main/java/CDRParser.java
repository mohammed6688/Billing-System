import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import modules.CDR;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CDRParser {
    public static String[] cdrdata = new String[1];

    public static CDR parseCDR(String fileName) {
        CSVReader reader = null;
        String ext = "CDRFiles/"+fileName+".csv";
        CDR cdrinfo = null;
        try {
            reader = new CSVReader(new FileReader(ext));
            String[] nextline;
            cdrinfo = new CDR();

            if((nextline=reader.readNext()) != null){

                cdrdata = nextline;

                /* for(String token : cdrdata) {
                    System.out.println(token);
                } */

                cdrinfo.setId(Integer.parseInt(cdrdata[0]));
                cdrinfo.setSource_msisdn(cdrdata[1]);
                cdrinfo.setTerminated_msisdn(cdrdata[2]);
                cdrinfo.setTimestamp(cdrdata[3]);
                cdrinfo.setDuration(Integer.parseInt(cdrdata[4]));
                cdrinfo.setRate(Integer.parseInt(cdrdata[5]));
                cdrinfo.setService_id(Integer.parseInt(cdrdata[6]));
                cdrinfo.setRatePlan_id(Integer.parseInt(cdrdata[7]));

            } else {
                System.out.println("File is Empty");
            }

        }
        catch (FileNotFoundException ex){
            System.out.println(ex.toString());
            System.out.println("File Not Exist");
        }
        catch (IOException | CsvValidationException e){
            System.out.println("There is no new lines");
        }

        return cdrinfo;
    }
}
