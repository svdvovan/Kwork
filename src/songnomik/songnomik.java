package songnomik;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by SretenskyVD on 28.05.2019.
 */
public class songnomik {
    ////keytool -import -v -file S:/ProjectJava/Kwork/src/songnomik/songnomikru.crt -keystore S:/ProjectJava/Kwork/src/songnomik/songnomikru.crt.jks -storepass drowssap

    public static void main(String[] args) throws IOException {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/songnomik/songnomikru.crt.jks");
        String Tovar = "В кроватку";
        String Manual_category =Tovar;

        String Path = "https://www.songnomik.ru/products/komplekty-v-detskuyu-krovatku-dlya-novorozhdennyh/";
//        String Path = " https://www.songnomik.ru/products/detskaya-odezhda-optom-dlya-novorozhdennyh/";
//keytool -import -v -file S:/ProjectJava/Kwork/src/supertelo906090/supertelo906090ru.crt -keystore S:/ProjectJava/Kwork/src/supertelo906090/supertelo906090ru.crt.jks -storepass drowssap

        String CatalogName = Tovar;
        int LastPage = 20;
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
            String  Path2 = Path + "?p="+ Page;




            Document doc1 = Jsoup.connect(Path2).get();

            Elements links3 = doc1.getElementsByClass("p-info col-md-8 col-sm-7");
            int yyy = 0;
            int yyyy =1;
            for (Element link3 : links3) {

                System.out.println();
                String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                System.out.println("Товар:"+addressUrl3);

                try {
                    Document doc4 = Jsoup.connect(addressUrl3)
//                            .proxy("201.174.52.27", 49229)
                            .timeout(50000)
                            .ignoreHttpErrors(true)
                            .ignoreContentType(true)
                            .followRedirects(true)
                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                            .get();



                    Elements PathColor = doc4.getElementsByClass("selectbox medium").select("option");
                    int countColor = 0;
                    for (Element PathColors : PathColor) {

                        String MainPathColor = "https://www.songnomik.ru"+(PathColor.get(countColor).attr("value"));

                        Document docPathColor = Jsoup.connect(MainPathColor).get();
                        System.out.println("Подкатегория:"+MainPathColor);
                        String NameProduct = docPathColor.getElementsByTag("h1").text();
                        String Price = docPathColor.getElementsByClass("col-md-12 col-sm-12 col-xs-8").text();
                        String Description = docPathColor.getElementsByClass("p-desc").select("p").html();
                        String BreadCrumb = docPathColor.getElementsByClass("breadcrumb").select("a").text();
                        System.out.println(BreadCrumb);


                        String sku =docPathColor.getElementsByClass("col-md-6 col-sm-3 col-xs-12 ").text();
                        System.out.println(sku);

                        int rowCount = sheet.getLastRowNum();
                        Row row = sheet.createRow(++rowCount);
///////////////////////////////////////////////////////
                        Element table = docPathColor.getElementsByClass("tab-content").first().tagName("br");
//                    Iterator<Element> ite = table.select(":has(br)").iterator();
//                    Elements row2 = table.select(":has(br)");
//
                    int y2 = 20;
//                    for (Element rows : row2) {
//                        String Har = ite.next().text();
//                        System.out.print(Har);
//                        Cell cell1000 = row.createCell(y2);
//                        cell1000.setCellValue(Har);

//
//                    }
                        String er = table.html();
                        Cell cell1000 = row.createCell(y2);
                        cell1000.setCellValue(er);
//                        System.out.println(er);

                        y2=y2+2;

////////////////////////////////////////////////////////


                        System.out.println(Description);
                        System.out.println(Price);
                        System.out.println(NameProduct);



//                    Document docColor = Jsoup.connect(Path2).get();
//                    Elements linksColor = doc1.getElementsByClass("p-info col-md-8 col-sm-7");

//                    String Category = doc4.getElementsByClass("active").select("span").first().text();
//);
                    String Category = Manual_category;
                    System.out.println(Category);

//                    String NameProduct = doc4.getElementsByTag("h1").first().text();
//                    System.out.println(NameProduct);
//                    String NamePrduct =   doc4.getElementsByClass("al-title title").text();
//                    System.out.println(NamePrduct);

//                    String sku =   doc4.getElementsByClass("goodsDataMainModificationsList").select("[name=id]").attr("value");
//                    System.out.println(sku);
//
//                    String  Pictures =   doc4.getElementsByClass("product-simple-image").select("a").attr("abs:href");
//                    System.out.println(Pictures);

//                    String smallCategory =   doc4.getElementsByClass("posted_in").select("a").text();
//                    System.out.println(smallCategory);



//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                    //     String Proizvoditel = Manual_Proizvoditel;
//                    String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                    System.out.println(Proizvoditel);

//                    String Description  = doc4.getElementsByClass("htmlDataBlock").html();
//                    System.out.println(Description);

//
//                    int rowCount = sheet.getLastRowNum();
//                    Row row = sheet.createRow(++rowCount);
//

//                    Elements table = doc4.getElementsByClass("woocommerce-product-details__short-description");
//                    Iterator<Element> ite = table.select("p").iterator();
//
//                    Elements row2 = table.select("p");
//
//                    int y2 = 7;
//
//                    for (Element rows : row2) {
//
//                        String Har = ite.next().text();
//
//                        System.out.print(Har);
//
//
//                        Cell cell1000 = row.createCell(y2);
//                        cell1000.setCellValue(Har);
//
//
//
//                        y2++;
//
//                    }

///////////////////////////////////
//                    String MainFoto = doc4.getElementsByClass("general-img popup-gallery").select("a").attr("abs:href");
//
//                    String Model = doc4.getElementsByClass("list-unstyled").first().select("li").get(1).text();
//                    System.out.println(Model);


/////////////////////////////////////////////////////////
                        try {
                            Elements size = docPathColor.getElementsByClass("size-view");

                            int z = 0;
                            //                      int y3 = 6;
                            int y3 = 16;
                            for (Element sizes : size) {
                                System.out.println(size.get(z).text());

                                String Size =  size.get(z).text();
                                Cell cell55565 = row.createCell(y3);
                                cell55565.setCellValue(Size);
                                y3++;
                                z++;
                            }
                        }
                        catch (java.lang.NullPointerException e){
                            e.printStackTrace();
                        }









/////////////////////////////////////////////////////////
                    try {
                        Elements pictures = docPathColor.getElementsByClass("p-thumb").select("a");

                        int z = 0;
                        //                      int y3 = 6;
                        int y3 = 6;
                        for (Element picture : pictures) {
                            System.out.println(pictures.get(z).select("a").attr("abs:href"));

                            String Foto =  pictures.get(z).select("a").attr("abs:href");
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

//
                    Cell cell2279 = row.createCell(0);
                    cell2279.setCellValue(sku);

                    Cell cell227 = row.createCell(2);
                    cell227.setCellValue(NameProduct);

//
                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(Manual_category);

//                    Cell cell22411 = row.createCell(2);
//                    cell22411.setCellValue(smallCategory);
//
                    Cell cell224 = row.createCell(3);
                    cell224.setCellValue(Price);

                    Cell cell2242 = row.createCell(4);
                    cell2242.setCellValue(Description);

                    Cell cell22422 = row.createCell(5);
                    cell22422.setCellValue(BreadCrumb);
//
//                    Cell cell224221 = row.createCell(6);
//                    cell224221.setCellValue(Pictures);
//
//                    Cell cell2242211 = row.createCell(5);
//                    cell2242211.setCellValue(MainFoto);


                        countColor++;
                    }
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
            System.out.println(Page);
            Page++;
        }

    }
}
