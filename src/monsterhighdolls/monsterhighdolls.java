package monsterhighdolls;

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
 * Created by SretenskyVD on 23.12.2019.
 */
public class monsterhighdolls {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/monsterhighdolls/monsterhighdolls.ru.cer.jks");
        String Tovar = "Пуллип";
        String Manual_category =Tovar;
//        String Manual_Proizvoditel = "Цветомания";

        String Path = "https://monsterhighdolls.ru/shop/pullip-kukla";
 //       String Path = "http://www.funkofunatic.ru/";
//keytool -import -v -file S:/ProjectJava/Kwork/src/monsterhighdolls/monsterhighdolls.ru.cer -keystore S:/ProjectJava/Kwork/src/monsterhighdolls/monsterhighdolls.ru.cer.jks -storepass drowssap

        String CatalogName = Tovar;
        int LastPage = 3;
        Workbook wb = new HSSFWorkbook();
//    XSSFWorkbook wb = new XSSFWorkbook();
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
        int Page = 2;
        for (int count = 1; count <= LastPage; count++) {
            String  Path2 = Path+";"+ Page;
//        String  Path2 = Path;



            Document doc1 = Jsoup.connect(Path2).get();

            Elements links3 = doc1.getElementsByClass("item-thumb");
            int yyy = 0;
            for (Element link3 : links3) {

//                String dataID = doc1.getElementsByClass("button-text catalog__button\n" +
//                        "\t\t\t\t\tcatalog__button-price addToBasket").get(yyy).attr("data-id");
//                System.out.println(dataID);

//                String NameProduct = doc1.getElementsByClass("catalog__name").get(yyy).text();
//                System.out.println(NameProduct);
//
//                String oldPrice = doc1.getElementsByClass("item-ttlt").get(yyy).text();
//                System.out.println(oldPrice);
//                String MainPrice = doc1.getElementsByClass("item-ttlt").get(yyy).text();
//                System.out.println(Na);
//                String NamePrduct = doc1.getElementsByClass("item-ttl").get(yyy).text();
//                System.out.println(NamePrduct);

                String NamePrduct = doc1.getElementsByClass("item-ttl").get(yyy).select("a").attr("title");
                System.out.println(NamePrduct);

                System.out.println();
                String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                System.out.println(addressUrl3);





                try {
                    Document doc4 = Jsoup.connect(addressUrl3)
//                            .proxy("34.95.167.51", 8080)
//                            .timeout(20000)
//                            .ignoreHttpErrors(true)
//                            .ignoreContentType(true)
//                            .followRedirects(true)
//                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                            .get();




//                    String Category = doc4.getElementsByClass("active").select("span").first().text();
//);
                    String Category = Manual_category;
                    System.out.println(Category);

                    String MainPrice = doc4.getElementsByClass("newprc").first().text();
                    System.out.println(MainPrice);

//                    String Izmerenie = doc4.getElementsByClass("produnit").text();
//                    System.out.println(Izmerenie);


//                    String NamePrduct =   doc4.getElementsByTag("h1").text();
//                    System.out.println(NamePrduct);



//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                    //     String Proizvoditel = Manual_Proizvoditel;
//                    String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                    System.out.println(Proizvoditel);

//                    String SKU  = doc4.getElementsByClass("product-num").text();
//                    System.out.println(SKU);

                    String SKU  = doc4.getElementsByClass("sku_wrapper").text();
                    System.out.println(SKU);

                    //.select("[name=id]").attr("value");

//                     String Description  = doc4.getElementsByTag("div").select("[itemprop=description]").select("p").text();
                    String Description  = doc4.getElementsByClass("ds-tab").select("[id=mode-dsc]").html();
//                    String Description2 = Description.toString();   <div id="mode-dsc"
                    System.out.println(Description);

//                    String Description_text  = doc4.getElementsByClass("tab-content").text();
//                    System.out.println(Description_text);
//
                    String Izmerenie  = doc4.getElementsByClass("tagged_as").text();
                    System.out.println(Izmerenie);
//
                    String breadcrumbs_last  = doc4.getElementsByClass("posted_in").text();
                    System.out.println(breadcrumbs_last);

                    String MainFoto = doc4.getElementsByClass("woocommerce-product-gallery__image").select("a").attr("abs:href");
                    System.out.println(MainFoto);

                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);

//
                    Cell cell227p = row.createCell(0);
                    cell227p.setCellValue(SKU);

                    Cell cell227 = row.createCell(1);
                    cell227.setCellValue(NamePrduct);


                    Cell cell1 = row.createCell(2);
                    cell1.setCellValue(Tovar);

//
                    Cell cell224 = row.createCell(3);
                    cell224.setCellValue(MainPrice);


                    Cell cell224x = row.createCell(4);
                    cell224x.setCellValue(Izmerenie);

                    Cell cell22411 = row.createCell(5);
                    cell22411.setCellValue(breadcrumbs_last);
//
//                    Cell cell224111 = row.createCell(6);
//                    cell224111.setCellValue(breadcrumbs_last);


                    Cell cell224221 = row.createCell(7);
                    cell224221.setCellValue(MainFoto);

                    Cell cell2242 = row.createCell(30);
                    cell2242.setCellValue(Description);


//                    try {
//                        Elements pictures = doc4.getElementsByClass("p-view__slider").select("a");
//
//                        int z = 0;
//                        //                      int y3 = 6;
//                        int y3 = 8;
//                        for (Element picture : pictures) {
//                            System.out.println(pictures.get(z).select("a").attr("abs:href"));
//
//                            String Foto =  pictures.get(z).select("a").attr("href");

//                        String AllFoto =  doc4.getElementsByClass("image").get(z).select("a").attr("abs:href");
//                        System.out.println(AllFoto);

//                        File f = new File(Foto);
//                        String FILENAME = "test/unikma/"  + CatalogName + "/" + f.getName();
//                        String SvDPDFURL = Foto;
//                        File file = new File(FILENAME);
//                        URL url = new URL(SvDPDFURL);
//                        FileUtils.copyURLToFile(url, file);
//
//                            Cell cell5555 = row.createCell(y3);
//                            cell5555.setCellValue("https://www.grandline.ru/"+Foto);
//                            y3++;
//
//
//                            z++;
//                        }
//                    }
//                    catch (java.lang.NullPointerException e){
//                        e.printStackTrace();
//                    }
//


//////////////////////////////////////////
//                    try {
//                        Element h2 = doc4.getElementsByClass("p-view__features-list").first();
//                        Iterator<Element> ite = h2.select("dt").iterator();
//
//                        Elements row2 = h2.select("dd");
//
//                        int y2 = 42;
//
//                        for (Element rows : row2) {
//
//                            String Har = ite.next().text();
//
//                            System.out.print(Har);
//
//
//                            Cell cell1000 = row.createCell(y2);
//                            cell1000.setCellValue(Har);
//
//
//
//                            y2=y2+2;
//
//                        }
//                    }
//                    catch (java.util.NoSuchElementException e){
//                        e.printStackTrace();
//                    }
///////////////////////////////////////////
//                    try {
//                        Element h22 = doc4.getElementsByClass("p-view__features-list").first();
//                        Iterator<Element> ite2 = h22.select("dd").iterator();
//
//                        Elements row22 = h22.select("dd");
//
//                        int y22 = 43;
//
//                        for (Element rows3 : row22) {
//
//                            String Har2 = ite2.next().text();
//
//                            System.out.print(Har2);
//
//
//                            Cell cell1000 = row.createCell(y22);
//                            cell1000.setCellValue(Har2);
//
//
//
//                            y22=y22+2;
//
//                        }
//
//                    }
//                    catch (java.lang.NullPointerException e) {
//                        e.printStackTrace();
//                    }
///////////////////////////////////////////






//////////////////////////////////////////////////////////
                try {
                    Elements features = doc4.getElementsByClass("shop-options").select("li");

                    int z11 = 0;
                    int y311 = 14;
                    int y411 =15;
//                    Cell cell5555 = row.createCell(y311);
//                    Cell cell6666 = row.createCell(y411);


                    for (Element featureses : features) {

                        String ATTRIBUTE = doc4.getElementsByClass("opt").get(z11).text();
                        String ATTRIBUTE2 =doc4.getElementsByClass("val").get(z11).text();

                        System.out.println(ATTRIBUTE + " " +ATTRIBUTE2);
//                    System.out.println(ATTRIBUTE);
                        Cell cell5555 = row.createCell(y311);
                        Cell cell6666 = row.createCell(y411);
                        cell5555.setCellValue(ATTRIBUTE);
                        cell6666.setCellValue(ATTRIBUTE2);
                        y311=y311+2;
                        y411=y411+2;
//
                        z11++;
                    }

                }
                catch (java.lang.NullPointerException e){
                    e.printStackTrace();
                }




///////////////////////////////////
//
//
//                    String Model = doc4.getElementsByClass("list-unstyled").first().select("li").get(1).text();
//                    System.out.println(Model);

/////////////////////////////////////////////////////////
                try {
                    Elements pictures = doc4.getElementsByClass("shop-fleft").select("li");

                    int z = 0;
                    //                      int y3 = 6;
                    int y3 = 35;
                    for (Element picture : pictures) {
                        System.out.println(pictures.get(z).select("a[href]").attr("abs:href"));
//                        String Foto =  pictures.get(z).select("a").attr("href");
                        String Foto =pictures.get(z).select("a[href]").attr("abs:href");
//                        File f = new File(Foto);
//                        String FILENAME = "test/unikma/"  + CatalogName + "/" + f.getName();
//                        String SvDPDFURL = Foto;
//                        File file = new File(FILENAME);
//                        URL url = new URL(SvDPDFURL);
//                        FileUtils.copyURLToFile(url, file);

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
//                    Cell cell2279 = row.createCell(0);
//                    cell2279.setCellValue(dataID);

//                Cell cell227 = row.createCell(1);
//                cell227.setCellValue(NamePrduct);
//
//
//                Cell cell1 = row.createCell(2);
//                cell1.setCellValue(Tovar);
//
////
//                Cell cell224 = row.createCell(3);
//                cell224.setCellValue(MainPrice);
//

//
//                    Cell cell224222 = row.createCell(23);
//                    cell224222.setCellValue(Description_text);

//                    Cell cell22422 = row.createCell(5);
//                    cell22422.setCellValue(Proizvoditel);

//
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
