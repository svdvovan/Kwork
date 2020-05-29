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
           String ArchiCAD_17 = doc4.getElementsByClass("product_code_row product_code_row_101074").select("td[class=column_available column_ul assignment_status]").get(0).text();
           System.out.println(ArchiCAD_17);
           fileOut.write(ArchiCAD_17);
           fileOut.flush();
       }
       catch(IOException ex){

       }
        try(FileWriter fileOut2 = new FileWriter("book_2.txt")) {
            String ArchiCAD_23 = doc4.getElementsByClass("product_code_row product_code_row_101074").select("td[class=column_available column_ul assignment_status]").get(1).text();
            System.out.println(ArchiCAD_23);
            fileOut2.write(ArchiCAD_23);
            fileOut2.flush();
        }
        catch(IOException ex){

        }

        }
    }

