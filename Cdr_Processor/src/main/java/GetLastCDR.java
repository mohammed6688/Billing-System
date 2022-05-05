import java.io.File;

public class GetLastCDR {

    public static File getLastModified()
    {
        File directory = new File("D:\\ITI\\billing\\project\\Billing-System\\Cdr_Processor\\CDRFiles");
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
//        long lastModifiedTime = 0;
//        System.out.println(lastModifiedTime);
        File chosenFile = null;

        if (files != null)
        {
            for (File file : files)
            {
                if (file.lastModified() > lastModifiedTime)
                {
//                    System.out.println(file.lastModified() );
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        System.out.println(chosenFile);
        return chosenFile;

    }

}
