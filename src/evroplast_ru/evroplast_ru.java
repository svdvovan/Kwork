package evroplast_ru;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by SretenskyVD on 20.08.2020.
 */
public class evroplast_ru {
    public static void main(String[] args) throws IOException {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/evroplast_ru/evroplast.cer.jks");
        String Tovar = "Клей";
        String Manual_category =Tovar;
//        String Manual_Proizvoditel = "Цветомания";

        String Path = "https://evroplast.ru/adhesive/";
//keytool -import -v -file S:/ProjectJava/Kwork/src/evroplast_ru/evroplast.cer -keystore S:/ProjectJava/Kwork/src/evroplast_ru/evroplast.cer.jks -storepass drowssap

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
//Path+"?PAGEN_1="+ Page;
        //  int Page = 26;
        int Page = 1;
        for (int count = 1; count <= LastPage; count++) {
            String  Path2 = Path+"?PAGEN_1="+ Page;
//            String  Path2 = Path;



            Document doc1 = Jsoup.connect(Path2).get();

//            Elements links3 = doc1.getElementsByClass("e-new-catalogue-item e-new-catalogue-h");
            Elements links3 = doc1.getElementsByClass("e-new-catalogue-item");
            int yyy = 0;
            int yyyy =1;
            for (Element link3 : links3) {

//                String dataID = doc1.getElementsByClass("button-text catalog__button\n" +
//                        "\t\t\t\t\tcatalog__button-price addToBasket").get(yyy).attr("data-id");
//                System.out.println(dataID);

//                String NameProduct = doc1.getElementsByClass("noo-product-title").select("h3").get(yyy).text();
//                System.out.println(NameProduct);
//                String Description_main = doc1.getElementsByClass("noo-product-excerpt").get(yyy).text();
//                System.out.println(Description_main);
////
//                String MainPrice = doc1.getElementsByClass("quantity").get(yyy).select("input").attr("data-price");
//                System.out.println(MainPrice);
                String MainPrice = doc1.getElementsByClass("e-new-item-price").get(yyy).text();
                System.out.println(MainPrice);

                System.out.println();
                String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                System.out.println(addressUrl3);





                try {
                    Document doc4 = Jsoup.connect(addressUrl3)
//                            .proxy("201.174.52.27", 49229)
//                            .timeout(50000)
//                            .ignoreHttpErrors(true)
//                            .ignoreContentType(true)
//                            .followRedirects(true)
//                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                            .get();




//                    String Category = doc4.getElementsByClass("active").select("span").first().text();
//);
                    String Category = Manual_category;
                    System.out.println(Category);

                    String NameProduct = doc4.getElementsByTag("h1").first().text();
                    System.out.println(NameProduct);
//                    String NamePrduct =   doc4.getElementsByClass("al-title title").text();
//                    System.out.println(NamePrduct);

                    String sku =   doc4.getElementsByClass("goodsDataMainModificationsList").select("[name=id]").attr("value");
                    System.out.println(sku);
//
//                    String  Pictures =   doc4.getElementsByClass("product-simple-image").select("a").attr("abs:href");
//                    System.out.println(Pictures);

//                    String smallCategory =   doc4.getElementsByClass("posted_in").select("a").text();
//                    System.out.println(smallCategory);



//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                    //     String Proizvoditel = Manual_Proizvoditel;
//                    String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                    System.out.println(Proizvoditel);

                    String Description  = doc4.getElementsByClass("main-info").html();
                    System.out.println(Description);

                    String material  = doc4.getElementsByClass("prod-info-material").text();
                    System.out.println(material);



                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);
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
                    try {
                    Elements features = doc4.getElementsByClass("param-item");

                    int z11 = 0;
                    int y311 = 14;
                    int y411 =15;

                    for (Element featureses : features) {

                        String ATTRIBUTE = doc4.getElementsByClass("param-name").get(z11).text();
                        String ATTRIBUTE2 =doc4.getElementsByClass("param-val").get(z11).text();
                        Cell cell5555 = row.createCell(y311);
                        Cell cell6666 = row.createCell(y411);
                        System.out.println(ATTRIBUTE + " " +ATTRIBUTE2);
                        cell5555.setCellValue(ATTRIBUTE);
                        cell6666.setCellValue(ATTRIBUTE2);
                        y311=y311+2;
                        y411=y411+2;
                        z11++;
                    }

                }
                catch (java.lang.NullPointerException e){
                    e.printStackTrace();
                }
///////////////////////////////////
                    String ATTRIBUTE111 = doc4.getElementsByClass("param-item-flex").text();
                    System.out.println(ATTRIBUTE111);

//////////////////////////////////
//
//                    try {
//                        Elements features = doc4.getElementsByClass("param-left-main");
//
//                        int z11f = 0;
//                        int z11ff = 1;
//                        int y311f = 24;
//                        int y411f =25;
//
//                        for (Element featureses : features) {
//
//                            String ATTRIBUTEf = doc4.getElementsByClass("param-item-flex").get(z11f).select("span").text();
//                            String ATTRIBUTE2f =doc4.getElementsByClass("param-item-flex").get(z11ff).select("span").text();
//                            Cell cell5555 = row.createCell(y311f);
//                            Cell cell6666 = row.createCell(y411f);
//                            System.out.println(ATTRIBUTEf + " " +ATTRIBUTE2f);
//                            cell5555.setCellValue(ATTRIBUTEf);
//                            cell6666.setCellValue(ATTRIBUTE2f);
//                            y311f=y311f+2;
//                            y411f=y411f+2;
//                            z11f++;
//                            z11ff++;
//                        }
//
//                    }
//                    catch (java.lang.NullPointerException e){
//                        e.printStackTrace();
//                    }
//



///////////////////////////////////
                    String MainFoto = "https://evroplast.ru/"+ doc4.getElementsByClass("sp-big").attr("data-src");
                    System.out.println(MainFoto);

                    String MainFoto2= "https://evroplast.ru/"+ doc4.getElementsByClass("param-section-block").select("img").attr("src");
                    System.out.println(MainFoto2);
//
//                    String Model = doc4.getElementsByClass("list-unstyled").first().select("li").get(1).text();
//                    System.out.println(Model);

/////////////////////////////////////////////////////////
                    try {
                        Elements pictures = doc4.getElementsByClass("sp-thumbnail");

                        int z = 0;
                        //                      int y3 = 6;
                        int y3 = 6;
                        for (Element picture : pictures) {
                            System.out.println("https://evroplast.ru/"+pictures.get(z).attr("src"));

                            String Foto = "https://evroplast.ru/"+ pictures.get(z).attr("src");
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
                    cell227.setCellValue(NameProduct.substring(0, 1).toUpperCase()+NameProduct.substring(1) );

//
                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(Manual_category);

//                    Cell cell22411 = row.createCell(2);
//                    cell22411.setCellValue(smallCategory);

                    Cell cell224 = row.createCell(3);
                    cell224.setCellValue(MainPrice);

                    Cell cell2242 = row.createCell(4);
                    cell2242.setCellValue(Description);


                    Cell cell2242flex = row.createCell(24);
                    cell2242flex.setCellValue(ATTRIBUTE111);

//                    Cell cell22422 = row.createCell(5);
//                    cell22422.setCellValue(Proizvoditel);
//
//                    Cell cell224221 = row.createCell(6);
//                    cell224221.setCellValue(Pictures);
//
                    Cell cell2242211 = row.createCell(5);
                    cell2242211.setCellValue(MainFoto);


                    Cell cell224foto2 = row.createCell(30);
                    cell224foto2.setCellValue(MainFoto2);

                    Cell cell224mat = row.createCell(31);
                    cell224mat.setCellValue(material);



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
