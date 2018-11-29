package optimum_lab;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class optimun_all {
    public static void main(String[] args) throws IOException {
        String Hostname = "http://www.optimum-lab.ru/";
         String Path = "http://www.optimum-lab.ru/category/laboratornaya-mebel/";


        String CatalogName = "med1";
        int LastPage = 67;
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


        int Page = 1;
                    for (int count = 1; count <= LastPage; count++) {
                String  Path2 = Path+ "?page=" + Page;

        //                       Document doc4 = Jsoup.connect(Path2).get()

        Document doc1 = Jsoup.connect(Path2).get();


          //      Elements links3 = doc1.getElementsByClass("product-info");
                        Elements links3 = doc1.getElementsByClass("fcart-name");


                int yyy = 0;
                for (Element link3 : links3) {
                    System.out.println();
                    String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                    System.out.println(addressUrl3);



                    Document doc4 = Jsoup.connect(addressUrl3).get();



                    String NameProduct = doc4.getElementsByTag("h1").text();
                    System.out.println(NameProduct);

                    String MainPrice = doc4.getElementsByClass("price nowrap").first().text();
                    System.out.println(MainPrice);

                    String Keyword = doc4.select("meta[name=Keywords]").attr("content");
                    System.out.println(Keyword);


                    String ID_product1 = doc4.getElementsByClass("add2cart").select("input[type=hidden]").attr("value");
                    System.out.println(ID_product1);

                    String KOD_product1 = doc4.getElementsByClass("hint").first().text();  //всегда ли код первым?
                    System.out.println(KOD_product1);

                    String Proizvoditel2 = doc4.getElementsByClass("tab-pane fade in active").select("p").first().text(); //всегда ли первый
                    System.out.println(Proizvoditel2);
//
//                    String Proizvoditel = doc4.select("p").get(5).text();
//                    System.out.println(Proizvoditel);
//
//                    String Proizvoditel3 = doc4.select("p").get(6).text();
//                    System.out.println(Proizvoditel3);
//
                    String Proizvoditel55 = doc4.getElementsByClass("description").next("p").text();
                    System.out.println(Proizvoditel55);

//                    String Proizvoditel4 = doc4.select("p").get(7).text();
//                    System.out.println(Proizvoditel4);


//                    String Description0 = doc4.getElementsByClass("product-summary margin-bottom-20").text();
//                    System.out.println(Description0);


                    String Description = doc4.getElementsByClass("description").html();
                    System.out.println(Description);


                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);

                    Elements pictures = doc4.getElementsByClass("image").select("a");

                    int z = 0;
                    int y3 = 25;

                    for (Element picture : pictures) {
                        System.out.println(pictures.get(z).select("a").attr("abs:href"));

                        String Foto = pictures.get(z).select("a").attr("abs:href");
//                    File f = new File(Foto);
//                    String FileName = f.getName();


                        Cell cell11 = row.createCell(y3);
                        //             cell11.setCellValue(FileName);
                        cell11.setCellValue(Foto);
                        y3++;

                        z++;

                    }


                    int t = 0;
                    int y4 = 35;

                    Elements breadcrumbs = doc4.getElementsByClass("breadcrumb").select("a");

                    for (Element breadcrumb : breadcrumbs) {
                        System.out.println(breadcrumbs.get(t).select("a").attr("abs:href"));

                        String Category = breadcrumbs.get(t).select("a").text();



                        Cell cell44 = row.createCell(y4);

                        cell44.setCellValue(Category);
                        y4++;

                        t++;

                    }

                    Cell cell227 = row.createCell(0);
                    cell227.setCellValue(KOD_product1);


                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(NameProduct);



                    Cell cell228 = row.createCell(2);
                    cell228.setCellValue(Keyword);

                    Cell cell223 = row.createCell(3);
                    cell223.setCellValue(Description);


//                    Cell cell226 = row.createCell(4);
//                    cell226.setCellValue(Cat1);


                    Cell cell224 = row.createCell(5);
                    cell224.setCellValue(MainPrice);

                    Cell cell229 = row.createCell(6);
                    cell229.setCellValue("RUB");

                    Cell cell230 = row.createCell(7);
                    cell230.setCellValue("шт.");

                    Cell cell825 = row.createCell(14);
                    cell825.setCellValue(Proizvoditel55);

//                    Cell cell725 = row.createCell(12);
//                    cell725.setCellValue(Proizvoditel3);
//
                    Cell cell625 = row.createCell(13);
                    cell625.setCellValue(Proizvoditel2);
////
//                    Cell cell225 = row.createCell(14);
//                    cell225.setCellValue(Proizvoditel);

                    Cell cell425 = row.createCell(21);
                    cell425.setCellValue(ID_product1);



            System.out.println();
            yyy++;
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
                        System.out.println(Page);
                        Page++;
                    }

    }

}
