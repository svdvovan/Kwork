package nailsmania_ua.nailsmania;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by SretenskyVD on 19.07.2019.
 */
public class ua {
    ////keytool -import -v -file S:/ProjectJava/Kwork/src/nailsmania_ua/nailsmania/nailsmania_ua.crt -keystore S:/ProjectJava/Kwork/src/nailsmania_ua/nailsmania/nailsmania_ua.crt.jks -storepass drowssap

    public static void main(String[] args) throws IOException {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/nailsmania_ua/nailsmania/nailsmania_ua.crt.jks");
        String Tovar = "Наборы гель-лаков";
        String Manual_category = Tovar;

        String Path = "https://nailsmania.ua/nabory-gel-lakov";

        String CatalogName = Tovar;
        int LastPage = 8;
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
            String  Path2 = Path+ "/page-" + Page;

        Document doc1 = Jsoup.connect(Path2).get();



        Elements links3 = doc1.getElementsByTag("h4");
        int yyy = 0;
        int yyyy = 1;
        for (Element link3 : links3) {

            System.out.println();
            String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
            System.out.println(addressUrl3);


            try {
                Document doc4 = Jsoup.connect(addressUrl3)
////                            .proxy("201.174.52.27", 49229)
//                        .timeout(50000)
//                        .ignoreHttpErrors(true)
//                        .ignoreContentType(true)
//                        .followRedirects(true)
//                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                        .get();

                int rowCount = sheet.getLastRowNum();
                Row row = sheet.createRow(++rowCount);

                String NameProduct = doc4.getElementsByTag("h1").text();
                System.out.println(NameProduct);

//                String Proizvoditel = doc4.getElementsByClass("product-description col-sm-12 col-md-6 col-lg-7").select("li").get(0).text();
//                System.out.println(Proizvoditel);

                String Proizvoditel = doc4.getElementsByClass("product-description col-sm-12 col-md-6 col-lg-7").select("li").select("a").text();
                System.out.println(Proizvoditel);


//                String ID = doc4.getElementsByClass("product-description col-sm-12 col-md-6 col-lg-7").select("li").get(1).text();
//                System.out.println(ID);


                String ID = doc4.getElementsByClass("product-description col-sm-12 col-md-6 col-lg-7").select("li").last().text();
                System.out.println(ID);





                String MainPrice = doc4.getElementsByClass("product-description col-sm-12 col-md-6 col-lg-7").select("p").select("span[class=price-new]").text();
                System.out.println(MainPrice);

                String OldPrice = doc4.getElementsByClass("product-description col-sm-12 col-md-6 col-lg-7").select("p").select("span[class=price-old]").text();
                System.out.println(OldPrice);
///////////////////

                Elements table = doc4.getElementsByClass("table table-bordered");
                Iterator<Element> ite = table.select("td").iterator();
                Elements row2 = table.select("td");

                int y2 = 8;

                for (Element rows : row2) {

                    String Har = ite.next().text();

                    System.out.print(Har);


                    Cell cell1000 = row.createCell(y2);
                    cell1000.setCellValue(Har);


                    y2++;

                }
/////////////////////////////////////////////
                System.out.println();
                String Description = doc4.getElementsByClass("tab-pane active").text();
                System.out.println(Description);


                String  MainFoto = doc4.getElementsByClass("thumbnail").attr("href");
                System.out.println(MainFoto);
                Cell cell555523 = row.createCell(49);
                cell555523.setCellValue(MainFoto+",");

///////////////////////////////////////

                try {
                    Elements Category = doc4.getElementsByClass("breadcrumb").select("li");
                    int z1 = 0;
                    int y8 = 35;
                    for (Element Categorys : Category) {
                        System.out.println( Category.get(z1).select("a").text());

                        String Bredcrump = Category.get(z1).select("a").text();
                        Cell cell5555xsw = row.createCell(y8);
                        cell5555xsw.setCellValue(Bredcrump+">");
                        y8++;
                        z1++;
                    }
                } catch (java.lang.NullPointerException e) {
                    e.printStackTrace();
                }


///////////////////////////////////////


                try {
                    Elements pictures = doc4.getElementsByClass("thumbnails-mini  owl-carousel").select("a");
                    int z = 0;
                    int y3 = 50;
                    for (Element picture : pictures) {
                        System.out.println( pictures.get(z).select("a").attr("href"));

                        String Foto = pictures.get(z).select("a").attr("href");
                        Cell cell5555 = row.createCell(y3);
                        cell5555.setCellValue(Foto+",");
                        y3++;
                        z++;
                    }
                } catch (java.lang.NullPointerException e) {
                    e.printStackTrace();
                }


                String Category = Manual_category;
                System.out.println(Category);


//
                Cell cell2279 = row.createCell(0);
                cell2279.setCellValue(ID);

                Cell cell227 = row.createCell(1);
                cell227.setCellValue(NameProduct);

//
//                Cell cell1 = row.createCell(1);
//                cell1.setCellValue(Manual_category);

//                    Cell cell22411 = row.createCell(2);
//                    cell22411.setCellValue(smallCategory);
//
                Cell cell224 = row.createCell(2);
                cell224.setCellValue(MainPrice);

                Cell cell224z = row.createCell(3);
                cell224z.setCellValue(OldPrice);


                Cell cell2242 = row.createCell(4);
                cell2242.setCellValue(Description);

                Cell cell555523cc = row.createCell(48);
                cell555523cc.setCellValue(Proizvoditel);

//                Cell cell2242x = row.createCell(5);
//                cell2242x.setCellValue(Specifica);


            } catch (java.lang.IllegalArgumentException e) {
                e.printStackTrace();
            } catch (java.net.SocketTimeoutException e) {
                e.printStackTrace();
            } catch (java.lang.IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (java.lang.NullPointerException e) {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println();
            System.out.println();
            yyy++;
            yyyy++;


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
