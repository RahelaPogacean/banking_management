package businessLayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GenerateReports {

    public static ArrayList<String> reports = new ArrayList<String>();

    public GenerateReports(){

    }

    public String toString() {

        String results = "+";
        for (int i = 0; i < reports.size(); i++) {
            results += " " + reports.get(i);
        }
        return results;
    }

    public  void createListOfOperations() throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter("report.txt", String.valueOf(StandardCharsets.UTF_8));

        for(int i=0; i<reports.size(); i++){

            writer.println(reports.get(i));
        }
        writer.println("\n");
        writer.close();

    }

}
