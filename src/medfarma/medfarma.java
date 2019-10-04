package medfarma;

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
 * Created by SretenskyVD on 04.10.2019.
 */
public class medfarma {
      public static void main(String[] args) throws IOException {
//        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/bestdigitals/bestdigitals_ru.crt.jks");
        String Tovar = "Индийские дженерики";
        String Manual_category =Tovar;
//        String Manual_Proizvoditel = "Цветомания";

//        String Path = "https://unikma.ru/catalog/zabory_iz_svarnykh_paneley/?PAGEN_2=";
        String Path = "http://xn--80aakd8ad1a4a.xn--p1ai/indiyskie-dzheneriki.html";
//keytool -import -v -file S:/ProjectJava/Kwork/src/bestdigitals/bestdigitals_ru.crt -keystore S:/ProjectJava/Kwork/src/bestdigitals/bestdigitals_ru.crt.jks -storepass drowssap

        String CatalogName = Tovar;
        int LastPage = 1;
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
//    int Page = 1;
//    for (int count = 1; count <= LastPage; count++) {
//        String  Path2 = Path+ Page;
        String  Path2 = Path;



        Document doc1 = Jsoup.connect(Path2).get();

        Elements links3 = doc1.getElementsByClass("block-good-name");
        int yyy = 0;
        for (Element link3 : links3) {

//                String dataID = doc1.getElementsByClass("button-text catalog__button\n" +
//                        "\t\t\t\t\tcatalog__button-price addToBasket").get(yyy).attr("data-id");
//                System.out.println(dataID);

//                String NameProduct = doc1.getElementsByClass("catalog__name").get(yyy).text();
//                System.out.println(NameProduct);
//
//            String MainPrice = doc1.getElementsByClass("fs16").get(yyy).text();
//            System.out.println(MainPrice);


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
                String Category = Manual_category;
                System.out.println(Category);

                String MainPrice = doc4.getElementsByClass("price-wrap coll ").text();
                System.out.println(MainPrice);


                String NamePrduct =   doc4.getElementsByClass("good_name").text();
                System.out.println(NamePrduct);



//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                //     String Proizvoditel = Manual_Proizvoditel;
//                    String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                    System.out.println(Proizvoditel);

                String SKU  = doc4.getElementsByClass("good_art").text();
                System.out.println(SKU);

                String Description  = doc4.getElementsByClass("good_desc").text();
                System.out.println(Description);

                String Description2  = doc4.getElementsByClass("good_desc").html();
                System.out.println(Description2);

                int rowCount = sheet.getLastRowNum();
                Row row = sheet.createRow(++rowCount);

                String Table = doc4.getElementsByClass("d_price_list").html();

                Cell cell227p = row.createCell(0);
                cell227p.setCellValue(SKU);

                Cell cell227 = row.createCell(1);
                cell227.setCellValue(NamePrduct);


                Cell cell1 = row.createCell(2);
                cell1.setCellValue(Tovar);

//
                Cell cell224 = row.createCell(3);
                cell224.setCellValue(MainPrice);


//                Elements table = doc4.getElementsByClass("proptogh proptog col1");
//                Iterator<Element> ite = table.select("td").iterator();
//
//                Elements row2 = table.select("td");
//
//                int y2 = 25;
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








                try {
                    Elements pictures = doc4.getElementsByClass("good_th").select("a");

                    int z = 0;
                    //                      int y3 = 6;
                    int y3 = 6;
                    for (Element picture : pictures) {
                        System.out.println(pictures.get(z).select("a").attr("abs:href"));

                        String Foto =  pictures.get(z).select("a").attr("href");

//                        String AllFoto =  doc4.getElementsByClass("image").get(z).select("a").attr("abs:href");
//                        System.out.println(AllFoto);

//                        File f = new File(Foto);
//                        String FILENAME = "test/unikma/"  + CatalogName + "/" + f.getName();
//                        String SvDPDFURL = Foto;
//                        File file = new File(FILENAME);
//                        URL url = new URL(SvDPDFURL);
//                        FileUtils.copyURLToFile(url, file);

                        Cell cell5555 = row.createCell(y3);
                        cell5555.setCellValue("http://xn--80aakd8ad1a4a.xn--p1ai"+Foto);
                        y3++;


                        z++;
                    }
                }
                catch (java.lang.NullPointerException e){
                    e.printStackTrace();
                }
/////////////////////////////////////////////////////////////

                Elements table = doc4.getElementsByClass("d_price_list");
                Iterator<Element> ite = table.select("td").iterator();

                Elements row2 = table.select("td");

                int y2 = 25;

                for (Element rows : row2) {

                    String Har = ite.next().text();

                    System.out.print(Har);


                    Cell cell1000 = row.createCell(y2);
                    cell1000.setCellValue(Har);



                    y2++;

                }



//////////////////////////////////////////////////////////
//                try {
//                    Elements features = doc4.getElementsByClass("features").get(1).select("div");
//
//                    int z11 = 0;
//                    int y311 = 14;
//                    int y411 =15;
////                    Cell cell5555 = row.createCell(y311);
////                    Cell cell6666 = row.createCell(y411);
//
//
//                    for (Element featureses : features) {
//
//                        String ATTRIBUTE = doc4.getElementsByClass("name coll").get(z11).text();
//                        String ATTRIBUTE2 =doc4.getElementsByClass("value coll").get(z11).text();
//
//                        System.out.println(ATTRIBUTE + " " +ATTRIBUTE2);
////                    System.out.println(ATTRIBUTE);
//                        Cell cell5555 = row.createCell(y311);
//                        Cell cell6666 = row.createCell(y411);
//                        cell5555.setCellValue(ATTRIBUTE);
//                        cell6666.setCellValue(ATTRIBUTE2);
//                        y311=y311+2;
//                        y411=y411+2;
////
//                        z11++;
//                    }
//
//                }
//                catch (java.lang.NullPointerException e){
//                    e.printStackTrace();
//                }
//
//
//

///////////////////////////////////
                    String MainFoto = doc4.getElementsByClass("main_img").select("a").attr("abs:href");
                System.out.println(MainFoto);
//
//                    String Model = doc4.getElementsByClass("list-unstyled").first().select("li").get(1).text();
//                    System.out.println(Model);

/////////////////////////////////////////////////////////
//                try {
//                    Elements pictures = doc4.getElementsByClass("more-img-wrap");
//
//                    int z = 0;
//                    //                      int y3 = 6;
//                    int y3 = 75;
//                    for (Element picture : pictures) {
//                        System.out.println(pictures.get(z).select("a[href]").attr("abs:href"));
//                        String Foto =  pictures.get(z).select("a").attr("href");
////                        File f = new File(Foto);
////                        String FILENAME = "test/unikma/"  + CatalogName + "/" + f.getName();
////                        String SvDPDFURL = Foto;
////                        File file = new File(FILENAME);
////                        URL url = new URL(SvDPDFURL);
////                        FileUtils.copyURLToFile(url, file);
//
//                        Cell cell5555 = row.createCell(y3);
//                        cell5555.setCellValue(Foto);
//                        y3++;
//
//
//                        z++;
//                    }
//                }
//                catch (java.lang.NullPointerException e){
//                    e.printStackTrace();
//                }
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

                Cell cell2242 = row.createCell(4);
                cell2242.setCellValue(Description);

                Cell cell2242qq = row.createCell(23);
                cell2242qq.setCellValue(Table);

                Cell cell2242qq1 = row.createCell(22);
                cell2242qq1.setCellValue(Description2);

//                    Cell cell22422 = row.createCell(5);
//                    cell22422.setCellValue(Proizvoditel);

                    Cell cell224221 = row.createCell(5);
                    cell224221.setCellValue(MainFoto);
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


//        }
//        System.out.println(Page);
//        Page++;
        }

    }

    }


