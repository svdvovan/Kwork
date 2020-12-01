package ArchiCAD;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by SretenskyVD on 11.02.2020.
 */
public class archicad {
    public static void main(String[] args) throws IOException {
        String Path = "http://192.168.0.4:22352/license_monitoring.html";
        Document doc4 = Jsoup.connect(Path).get();

       try(FileWriter fileOut = new FileWriter("book_1.txt")) {
           String ArchiCAD_24_1 = doc4.getElementsByClass("product_code_row product_code_row_101074").select("td[class=column_used column_ul assignment_status assignment_summary]").get(0).text();
           System.out.println(ArchiCAD_24_1);
           fileOut.write(ArchiCAD_24_1);
           fileOut.flush();
       }
       catch(IOException ex){

       }
        try(FileWriter fileOut2 = new FileWriter("book_2.txt")) {
            String ArchiCAD_24_3 = doc4.getElementsByClass("product_code_row product_code_row_101074").select("td[class=column_used column_ul assignment_status assignment_summary]").get(1).text();
            System.out.println(ArchiCAD_24_3);
            fileOut2.write(ArchiCAD_24_3);
            fileOut2.flush();
        }
        catch(IOException ex){

        }

        }
    }

