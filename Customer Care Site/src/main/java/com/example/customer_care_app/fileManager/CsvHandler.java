package com.example.customer_care_app.fileManager;

import com.example.customer_care_app.modules.CDR;
import com.opencsv.CSVWriter;


import java.io.*;
import java.net.URL;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Random;

public class CsvHandler {
    public void writeDataLineByLine(CDR cdr) throws IOException {
        // first create file object for file placed at location
        // specified by filepath
        URL resource = getClass().getResource("/");
        String path = resource.getPath();
        Random random = new Random();
        System.out.println(path);
        int int_random = random.nextInt(2147483647);
        path = path.replace("Customer%20Care%20Site/target/classes/", "Cdr_Processor/CDRFiles/cdr_" + int_random + ".CSV");
        File file = new File(path);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            // adding header to csv
            //   String[] header = { "ID", "A_party_MSISDN", "B_party_MSISDN" ,"startDate","StartHour","RatePlan_Id"
            //  ,"ServicePackage_Id","Event_Type","duration_sec"};
            //  writer.writeNext(header);

            // add data to csv

            String[] data1 = {String.valueOf(cdr.getId()), cdr.getSource_msisdn(), cdr.getTerminated_msisdn(),
                    String.valueOf(cdr.getTimestamp()), String.valueOf(cdr.getDuration()), String.valueOf(cdr.getRate()),
                    String.valueOf(cdr.getService_id()),
                    String.valueOf(cdr.getRatePlan_id())};
            writer.writeNext(data1);


            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        CDR cdr = new CDR(10, "013110677878", "01347964655", String.valueOf(ts), 80, 0, 1, 3);
        CsvHandler cv = new CsvHandler();
        cv.writeDataLineByLine(cdr);
    }


}
