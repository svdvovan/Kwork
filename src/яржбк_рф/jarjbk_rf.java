package яржбк_рф;

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
 * Created by SretenskyVD on 05.02.2019.
 */
public class jarjbk_rf { public static void main(String[] args) throws IOException {

    String Tovar = "Мостовые конструкции";
    String Manual_category =Tovar;
//    String Manual_Proizvoditel = "Orac Decor";

    String Path = "http://xn--90ajnv8e.xn--p1ai/catalog/mostovye-konstruktsii/";


    String CatalogName = Tovar;
    int LastPage = 1;
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
        String  Path2 = Path+ "?PAGEN_1=" + Page;
//            String  Path2 = Path;



        Document doc1 = Jsoup.connect(Path2).get();

        Elements links3 = doc1.getElementsByClass("col-md-4 col-sm-6 col-xs-6");
        int yyy = 0;
        for (Element link3 : links3) {

//            String ID = doc1.getElementsByClass("sku").get(yyy).text();
//            System.out.println(ID);

            String MainPrice = doc1.getElementsByClass("price_new").get(yyy).text();
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




//                    String Category = doc4.getElementsByClass("active").select("span").first().text();
//);
                String Category = doc4.getElementsByClass("dropdown-submenu dropdown-toggle active").select("a").first().text();

//                String Category = Manual_category;
                System.out.println(Category);


                String NamePrduct =   doc4.getElementsByTag("h1").text();
                System.out.println(NamePrduct);



//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                //     String Proizvoditel = Manual_Proizvoditel;
//                String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                System.out.println(Proizvoditel);

                int rowCount = sheet.getLastRowNum();
                Row row = sheet.createRow(++rowCount);


//                Elements table = doc4.getElementsByClass("table table-bordered");
//                Iterator<Element> ite = table.select("td").iterator();
//
//                Elements row2 = table.select("td");
//
//                int y2 = 15;
//
//                for (Element rows : row2) {
//
//                    String Har = ite.next().text();
//
//                    System.out.print(Har);
//
//
//                    Cell cell1000 = row.createCell(y2);
//                    cell1000.setCellValue(Har);
//
//
//
//                    y2++;
//
//                }

///////////////////////////////////
                String MainFoto = doc4.getElementsByClass("slides items").select("a").attr("abs:href");


                String Opisanie = doc4.getElementsByClass("content").text();
                System.out.println(Opisanie);

//                String Model = doc4.getElementsByClass("list-unstyled").first().select("li").get(1).text();
//                System.out.println(Model);

/////////////////////////////////////////////////////////
//                try {
//                    Elements pictures = doc4.getElementsByClass("image-additional").select("a");
//
//                    int z = 0;
//                    int y3 = 6;
//                    for (Element picture : pictures) {
//                        System.out.println(pictures.get(z).select("a").attr("abs:href"));
//
//                        String Foto = pictures.get(z).select("a").attr("abs:href");
//                        Cell cell5555 = row.createCell(y3);
//                        cell5555.setCellValue(Foto);
//                        y3++;
//                        z++;
//                    }
//                }
//                catch (java.lang.NullPointerException e){
//                    e.printStackTrace();
//                }
///////////////////////////////////////////////////////////


//                Cell cell227 = row.createCell(0);
//                cell227.setCellValue(ID);


                Cell cell1 = row.createCell(1);
                cell1.setCellValue(Category);


                Cell cell224 = row.createCell(2);
                cell224.setCellValue(MainPrice);

                Cell cell2242 = row.createCell(3);
                cell2242.setCellValue(NamePrduct);

//                Cell cell22422 = row.createCell(4);
//                cell22422.setCellValue(Proizvoditel);

                Cell cell224221 = row.createCell(4);
                cell224221.setCellValue(MainFoto);

//                Cell cell2242211 = row.createCell(14);
//                cell2242211.setCellValue(MainFoto);

                Cell cell3242211 = row.createCell(5);
                cell3242211.setCellValue(Opisanie);

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
