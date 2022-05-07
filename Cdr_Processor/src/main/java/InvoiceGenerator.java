import modules.Invoice;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceGenerator {
    static final String fileName = "src/main/resources/JasperDesign.jrxml";
    static final String outFile = "src/main/resources/Reports.pdf";

    public static void generate(List<Invoice> invoice) throws FileNotFoundException, JRException {
        Map<String, Object> parameter  = new HashMap<String, Object>();

        JRBeanCollectionDataSource userCollectionDataSource =
                new JRBeanCollectionDataSource(invoice);
        parameter.put("studentDataSource", userCollectionDataSource);
        parameter.put("title", "Hi, I am Title");

        JasperReport jasperDesign = JasperCompileManager.compileReport(fileName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, parameter,
                new JREmptyDataSource());

        File file = new File(outFile);
        OutputStream outputSteam = new FileOutputStream(file);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);

        System.out.println("Report Generated!");
    }
}
