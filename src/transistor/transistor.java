package transistor;

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
 * Created by SretenskyVD on 01.03.2019.
 */
public class transistor {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/transistor/transistor.crt.jks");
//keytool -import -v -file S:/ProjectJava/Kwork/src/\transistor/crt.crt -keystore S:/ProjectJava/Kwork/src/\transistor/\transistor.crt.jks -storepass drowssap
        String Tovar = "алюмПрофильстр2";
        String Manual_category =Tovar;
//        String Manual_Proizvoditel = "Orac Decor";

        String Path = "https://transistor.ru/catalog/profile/56/?PAGEN_1=2/";


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

        //  int Page = 12;
        int Page = 1;
        for (int count = 1; count <= LastPage; count++) {
//            String  Path2 = Path+ "?PAGEN_1=" + Page;
//            String  Path2 = Path+ "?PAGEN_1=" + Page;
            String  Path2 = Path;
//


            Document doc1 = Jsoup.connect(Path2).get();
            Elements links3 = doc1.getElementsByClass("catalog-list__item-value name  more-width");
//            Elements links3 = doc1.getElementsByClass("catalog-list__item-value name ");
            int yyy = 0;

            for (Element link3 : links3) {

                String ID = doc1.getElementsByClass("catalog-list__item-value articul").get(yyy).text();
                System.out.println(ID);

                String MainPrice = doc1.getElementsByClass("catalog-list__item-value price").get(yyy).text();
                System.out.println(MainPrice);

                String Category = doc1.getElementsByClass("buttons-crumbs").select("span").last().text();
                System.out.println(Category);



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



                    String NamePrduct =   doc4.getElementsByClass("catalog-cart__h3").get(1).text();
                    System.out.println(NamePrduct);



                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);


                    Elements table = doc4.getElementsByClass("catalog-cart__tabs__content-section active-tab");
                    Iterator<Element> ite = table.select("div").iterator();

                    Elements row2 = table.select("div");

                    int y2 = 15;

                    for (Element rows : row2) {

                        String Har = ite.next().text();

                        System.out.print(Har);


                        Cell cell1000 = row.createCell(y2);
                        cell1000.setCellValue(Har);



                        y2++;

                    }


//////////////////////////////////////////////////////////

                    try {
                        Elements chars = doc4.getElementsByClass("chars").select("li");

                        int z = 0;
                        int y3 = 16;
                        int y4 =17;
                        for (Element charses : chars) {


                            String ATTRIBUTE = chars.get(z).select("p").first().text();
                            String ATTRIBUTE2 = chars.get(z).select("p").get(1).text();

                            System.out.println(ATTRIBUTE + " " +ATTRIBUTE2);


                            Cell cell5555 = row.createCell(y3);
                            Cell cell6666 = row.createCell(y4);


                            cell5555.setCellValue(ATTRIBUTE);
                            cell6666.setCellValue(ATTRIBUTE2);
                            y3=y3+2;
                            y4=y4+2;

                            z++;
                        }
                    }
                    catch (java.lang.NullPointerException e){
                        e.printStackTrace();
                    }













/////////////////////////////////////////////////////////
                    try {
                        Elements pictures = doc4.getElementsByClass("catalog-cart__content-big-images").select("a");

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


                    Cell cell227 = row.createCell(0);
                    cell227.setCellValue(ID);


                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(Category);


                    Cell cell224 = row.createCell(2);
                    cell224.setCellValue(MainPrice);

                    Cell cell2242 = row.createCell(3);
                    cell2242.setCellValue(NamePrduct);

//                    Cell cell22422 = row.createCell(4);
//                    cell22422.setCellValue(Proizvoditel);

//                    Cell cell224221 = row.createCell(5);
//                    cell224221.setCellValue(MainFoto);
//
//                    Cell cell22422112 = row.createCell(15);
//                    cell22422112.setCellValue(Description);

//                    Cell cell2242211 = row.createCell(14);
//                    cell2242211.setCellValue(MainFoto);

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
