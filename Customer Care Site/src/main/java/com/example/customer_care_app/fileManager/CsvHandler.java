package com.example.customer_care_app.fileManager;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;

public class CsvHandler {
    public void writeDataLineByLine() {
        // first create file object for file placed at location
        // specified by filepath
        URL resource = getClass().getResource("/");
        String path = resource.getPath();
        path = path.replace("Customer%20Care%20Site/target/classes/WEB-INF/classes/", "Cdr_Processor");
        System.out.println(path);
//        File file = new File(filePath);
//        try {
//            // create FileWriter object with file as parameter
//            FileWriter outputfile = new FileWriter(file);
//
//            // create CSVWriter object filewriter object as parameter
//            CSVWriter writer = new CSVWriter(outputfile);
//            // adding header to csv
//            String[] header = { "ID", "A_party_MSISDN", "B_party_MSISDN" ,"startDate","StartHour","RatePlan_Id"
//            ,"ServicePackage_Id","Event_Type","duration_sec"};
//            writer.writeNext(header);
//
//            // add data to csv
//            String[] data1 = { "Aman", "10", "620" };
//            writer.writeNext(data1);
//            String[] data2 = { "Suraj", "10", "630" };
//            writer.writeNext(data2);
//
//            // closing writer connection
//            writer.close();
//        }
//        catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }


public static void main(String[] args){
        CsvHandler cv =new CsvHandler();
    cv.writeDataLineByLine();
}

}
