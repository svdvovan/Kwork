package optimum_lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by SretenskyVD on 05.10.2018.
 */
public class optimum_lab_1 {
    public static void main(String[] args) throws IOException {
        String Hostname = "http://www.optimum-lab.ru/";
         String Path = "http://www.optimum-lab.ru/category/himreaktivy/";


        String CatalogName = "med1";
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet1 = wb.createSheet(CatalogName);
        FileOutputStream fileOut = new FileOutputStream("book_" + CatalogName + ".xls");


        try {
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();


        }
        Sheet sheet = wb.getSheetAt(0);

        Document doc1 = Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("list-group sidebar-nav-v1 collapse in fa-icons margin-bottom-30");
        int y = 0;
        for (Element link1 : links1) {
            System.out.println();
            String addressUrl = (links1.get(y).select("a[href]").attr("abs:href"));

            System.out.println(addressUrl);



            Document doc2 = Jsoup.connect(addressUrl).get();
            Elements links2 = doc2.getElementsByClass("subcat theme-border-color-hover theme-shadow-color");


            int yy = 0;
            for (Element link2 : links2) {
                System.out.println();
                String addressUrl2 = (links2.get(yy).select("a[href]").attr("abs:href"));
                System.out.println(addressUrl2);

                Document doc3 = Jsoup.connect(addressUrl2).get();
                String Cat1 = doc3.getElementsByTag("title").text();
                System.out.println(Cat1);

                Elements links3 = doc3.getElementsByClass("product-info");
//                String Cat2 = doc3.getElementsByTag("title").text();
//                System.out.println(Cat2);
                int yyy = 0;
                for (Element link3 : links3) {
                    System.out.println();
                    String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                    System.out.println(addressUrl3);
//                    String Cat2 = doc3.getElementsByTag("title").text();
//                    System.out.println(Cat2);

                    Document doc4 = Jsoup.connect(addressUrl3).get();



                String NameProduct = doc4.getElementsByTag("h1").text();
                  System.out.println(NameProduct);

                String MainPrice = doc4.getElementsByClass("price nowrap").text();
                System.out.println(MainPrice);

                String ID_product1 = doc4.getElementsByClass("add2cart").select("input[type=hidden]").attr("value");
                System.out.println(ID_product1);

                    String KOD_product1 = doc4.getElementsByClass("hint").first().text();  //всегда ли код первым?
                    System.out.println(KOD_product1);

                    String Proizvoditel = doc4.getElementsByClass("tab-pane fade in active").select("p").first().text(); //всегда ли первый
                    System.out.println(Proizvoditel);

//                    String Description0 = doc4.getElementsByClass("product-summary margin-bottom-20").text();
//                    System.out.println(Description0);


                String Description = doc4.getElementsByClass("description").html();
                System.out.println(Description);


                int rowCount = sheet.getLastRowNum();
                Row row = sheet.createRow(++rowCount);

                Elements pictures = doc4.getElementsByClass("image").select("a");

                int z = 0;
                int y3 = 11;

                for (Element picture : pictures) {
                    System.out.println( pictures.get(z).select("a").attr("abs:href"));

                    String Foto = pictures.get(z).select("a").attr("abs:href");
//                    File f = new File(Foto);
//                    String FileName = f.getName();


                    Cell cell11 = row.createCell(y3);
       //             cell11.setCellValue(FileName);
                    cell11.setCellValue(Foto);
                    y3++;

                    z++;

                }


                Cell cell227 = row.createCell(0);
                cell227.setCellValue(KOD_product1);



                Cell cell1 = row.createCell(1);
                cell1.setCellValue(NameProduct);


                Cell cell223 = row.createCell(3);
                cell223.setCellValue(Description);


                    Cell cell226 = row.createCell(4);
                    cell226.setCellValue(Cat1);


                    Cell cell224 = row.createCell(5);
                    cell224.setCellValue(MainPrice);

                    Cell cell225 = row.createCell(14);
                    cell225.setCellValue(Proizvoditel);



                    yyy++;
                }
                yy++;
            }

            System.out.println();
            y++;
            try {
                FileOutputStream fileOut1 = new FileOutputStream("book_" + CatalogName + ".xls");
                wb.write(fileOut1);
                fileOut1.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }



        }


    }
}
