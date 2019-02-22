package lareek_ru;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by SretenskyVD on 20.12.2018.
 */
public class lareek {
    public static void main(String[] args) throws IOException {

        String Tovar = "Ларек";

        String Path = "http://www.lemonlux.shop/collection/all";


        String CatalogName = Tovar;
        int LastPage = 97;
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

        //  int Page = 59;
        int Page = 1;
        for (int count = 1; count <= LastPage; count++) {
            String  Path2 = Path+ "?page=" + Page;




            Document doc1 = Jsoup.connect(Path2).get();

            Elements links3 = doc1.getElementsByClass("product-link");
            int yyy = 0;
            for (Element link3 : links3) {

//                String ID = doc1.getElementsByClass("productID").get(yyy).text();
//                System.out.println(ID);

                String MainPrice = doc1.getElementsByClass("price in-card").get(yyy).text();
                System.out.println(MainPrice);

                System.out.println();
                String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                System.out.println(addressUrl3);





                try {
                    Document doc4 = Jsoup.connect(addressUrl3)
//                            .proxy("201.174.52.27", 49229)
                            .timeout(50000)
                            .ignoreHttpErrors(true)
                            .ignoreContentType(true)
                            .followRedirects(true)
                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                            .get();




                    String Category = doc4.getElementsByClass("breadcrumb-item").select("[data-breadcrumbs=2]").text();
                    System.out.println(Category);


                    String NamePrduct =   doc4.getElementsByTag("h1").text();
                    System.out.println(NamePrduct);

                    String Proizvoditel =   doc4.getElementsByClass("breadcrumb-item").select("[data-breadcrumbs=3]").text();
                    System.out.println(Proizvoditel);

//                    String Description =   doc4.getElementsByClass("tab-block-inner editor").first().html();
//
                    String Description =   doc4.getElementsByClass("tab-block").first().html();
//                    String Description =   doc4.getElementsByClass("tab-block-inner editor").html();
                    System.out.println(Description);

                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);

//////////////////
                    Elements table = doc4.getElementsByClass("table table-bordered table-striped table-hover");
                    Elements row2 = table.select("td");
                    Iterator<Element> ite = table.select("td").iterator();
                   Iterator<Element> ite2 = table.select("td").iterator();


                    int y2 = 25;
                    for (Element rows : row2) {

                        String Har = ite.next().text();
                        String Har2 = ite2.next().text();
                        System.out.print(Har);
                        System.out.print(Har2);
                        Cell cell1000 = row.createCell(y2+1);
                        cell1000.setCellValue("Характеристики->"+Har);
                        Cell cell2000 = row.createCell(y2);
                        cell2000.setCellValue(Har2);

                        y2=y2+2;

                    }



/////////////////////////////////////////////////////////
                    try {
                        Elements pictures = doc4.getElementsByClass("slide-inner image-container");

                        int z = 0;
                        int y3 = 6;
                        for (Element picture : pictures) {
                            System.out.println(pictures.get(z).select("a").attr("abs:href"));

                            String Foto = pictures.get(z).select("a").attr("abs:href");
                            Cell cell5555 = row.createCell(y3);
                            cell5555.setCellValue(Foto);
                            y3++;
                            z++;
                        }
                    }
                    catch (java.lang.NullPointerException e){
                        e.printStackTrace();
                    }
///////////////////////////////////////////////////////////


                    String NoSpace = NamePrduct.replaceAll(" ", "_");

                    Cell cell227 = row.createCell(0);
                    cell227.setCellValue(NoSpace+"_"+Page+"_"+yyy);


                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(Category);


                    Cell cell224 = row.createCell(2);
                    cell224.setCellValue(MainPrice);

                    Cell cell2242 = row.createCell(3);
                    cell2242.setCellValue(NamePrduct);

                    Cell cell22422 = row.createCell(4);
                    cell22422.setCellValue(Proizvoditel);

                    Cell cell22472 = row.createCell(5);
                    cell22472.setCellValue(Description);

                }catch (java.lang.IllegalArgumentException e){
                    e.printStackTrace();}

                catch (java.net.SocketTimeoutException e) {
                    e.printStackTrace();
                }
                catch (java.lang.IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                catch (java.lang.NullPointerException e) {
                    e.printStackTrace();
                }


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
