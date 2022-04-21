import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CDRParser {

    public static void parseCDR(String fileName) {
        CSVReader reader = null;
        String ext = "CDRFiles/"+fileName+".csv";
        System.out.println(ext);
        try {
            reader = new CSVReader(new FileReader(ext));
            String[] nextline;

            while((nextline=reader.readNext()) != null){

                for(String token : nextline){

                    System.out.println(token);

                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File Not Exist");
        }
        catch (IOException | CsvValidationException e){
            System.out.println("There is no new lines");
        }

    }
}
