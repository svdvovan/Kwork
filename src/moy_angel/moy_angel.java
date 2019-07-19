package moy_angel;

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
 * Created by SretenskyVD on 18.06.2019.
 */
public class moy_angel {
    ////keytool -import -v -file S:/ProjectJava/Kwork/src/moy_angel/moy-angel_com.crt -keystore S:/ProjectJava/Kwork/src/moy_angel/moy-angel_com.crt.jks -storepass drowssap

    public static void main(String[] args) throws IOException {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/moy_angel/moy-angel_com.crt.jks");
        String Tovar = "Полотенца с уголком";
        String Manual_category =Tovar;

        String Path2 = "https://moy-angel.com/polotenca-s-ugolkom.html";
//        String Path = " https://www.songnomik.ru/products/detskaya-odezhda-optom-dlya-novorozhdennyh/";
//keytool -import -v -file S:/ProjectJava/Kwork/src/supertelo906090/supertelo906090ru.crt -keystore S:/ProjectJava/Kwork/src/supertelo906090/supertelo906090ru.crt.jks -storepass drowssap

        String CatalogName = Tovar;
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


            Document doc1 = Jsoup.connect(Path2).get();

            Elements links3 = doc1.getElementsByClass("thumbTitle");
            int yyy = 0;
            int yyyy =1;
            for (Element link3 : links3) {

                System.out.println();
                String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                System.out.println("Товар:"+addressUrl3);

               String  MainPrice =doc1.getElementsByClass("thumbPrice").get(yyy).text();
                System.out.println(MainPrice);

                try {
                    Document doc4 = Jsoup.connect(addressUrl3)
//                            .proxy("201.174.52.27", 49229)
                            .timeout(50000)
                            .ignoreHttpErrors(true)
                            .ignoreContentType(true)
                            .followRedirects(true)
                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                            .get();

                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);

                    String NameProduct = doc4.getElementsByTag("h1").text();
                    System.out.println(NameProduct);
                    String ID = doc4.getElementsByClass("product-title").next().text();
                    System.out.println(ID);
///////////////////////////

                    Elements table = doc4.getElementsByClass("vendorenabled").first().select("table").select("tr");
                                  Iterator<Element> ite = table.select("td").iterator();
                    Elements row2 = table.select("td");

                    int y2 = 7;

                    for (Element rows : row2) {

                        String Har = ite.next().text();

                        System.out.print(Har);


                        Cell cell1000 = row.createCell(y2);
                        cell1000.setCellValue(Har);



                        y2++;

                    }
/////////////////////////////////////////////
                    System.out.println();
                    String  Description = doc4.getElementsByClass("tab-pane active").text();
                    System.out.println(Description);


                    String  MainFoto = doc4.getElementsByClass("image").select("img").attr("src");
                    System.out.println(MainFoto);
                    Cell cell555523 = row.createCell(34);
                    cell555523.setCellValue("https://moy-angel.com" + MainFoto);

///////////////////////////////////////


                    try {
                        Elements pictures = doc4.getElementsByClass("image-additional").select("img");
//                        Elements pictures = doc4.select("ul").select("[id=thumblist]");

                        int z = 0;
                        int y3 = 35;
                        for (Element picture : pictures) {
                            System.out.println("https://moy-angel.com" +pictures.get(z).select("img").attr("src"));

                            String Foto = "https://moy-angel.com" + pictures.get(z).select("img").attr("src");
                            Cell cell5555 = row.createCell(y3);
                            cell5555.setCellValue(Foto);
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

                        Cell cell227 = row.createCell(2);
                        cell227.setCellValue(NameProduct);

//
                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(Manual_category);

//                    Cell cell22411 = row.createCell(2);
//                    cell22411.setCellValue(smallCategory);
//
                        Cell cell224 = row.createCell(3);
                        cell224.setCellValue(MainPrice);

                        Cell cell2242 = row.createCell(4);
                        cell2242.setCellValue(Description);


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


    }
}
