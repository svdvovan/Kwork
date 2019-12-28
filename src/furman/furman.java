package furman;

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
 * Created by SretenskyVD on 27.08.2019.
 */
public class furman {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/furman/furman_ua.crt.jks");
        String Tovar = "Все для ногтей";
        String Manual_category =Tovar;

        String Path = "https://furman.ua/295-vygodnaya-cena";
//keytool -import -v -file S:/ProjectJava/Kwork/src/furman/furman_ua.crt -keystore S:/ProjectJava/Kwork/src/furman/furman_ua.crt.jks -storepass drowssap

        String CatalogName = Tovar;
//        int LastPage = 1;
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
//        int Page = 1;
//        for (int count = 1; count <= LastPage; count++) {
//            String  Path2 = Path+ Page;
            String  Path2 = Path;



            Document doc1 = Jsoup.connect(Path2).get();

            Elements links3 = doc1.getElementsByClass("isotopeCat cat0 col-xs-12 col-sm-6 col-md-3 clearfix hidestart");
            int yyy = 0;
            for (Element link3 : links3) {


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

                    String Category = doc4.getElementsByTag("title").text();
                    System.out.println(Category);



                    Elements links4 = doc4.getElementsByClass("thumbnail-inner");
                    int yyyy = 0;
                    for (Element link4 : links4) {

                        String addressUrl4 = (links4.get(yyyy).select("a[href]").attr("abs:href"));
                        System.out.println(addressUrl4);
                        Document doc5 = Jsoup.connect(addressUrl4).get();


                        String NamePrduct = doc5.getElementsByTag("title").text();
                        System.out.println(NamePrduct);

                        String MainPrice = doc5.getElementsByClass("current-price").text();
                        System.out.println(MainPrice);

                        String OldPrice = doc5.getElementsByClass("regular-price").text();
                        System.out.println(OldPrice);

//                        String dataID = doc5.getElementsByTag("span").select("[itemprop=sku]").text();
                        String dataID = doc5.getElementsByClass("product-reference").text();
                        System.out.println(dataID);


//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                        //     String Proizvoditel = Manual_Proizvoditel;
//                    String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                    System.out.println(Proizvoditel);

                        String Description = doc5.getElementsByClass("product-description").text();
                        System.out.println(Description);

//                        String MainFoto = doc5.getElementsByClass("thumb-container").select("img").attr("data-image-large-src");
//                        System.out.println(MainFoto);


                        int rowCount = sheet.getLastRowNum();
                        Row row = sheet.createRow(++rowCount);

//
//                        Elements table = doc4.getElementsByClass("proptogh proptog col1");
//                        Iterator<Element> ite = table.select("td").iterator();
//
//                        Elements row2 = table.select("td");
//
//                        int y2 = 25;
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
//                            y2++;
//
//                        }

///////////////////////////////////
//                    String MainFoto = doc5.getElementsByClass("thumbnail-inner").select("a").select("img").attr("src");
//                        System.out.println(MainFoto);
//                    String Model = doc4.getElementsByClass("list-unstyled").first().select("li").get(1).text();
//                    System.out.println(Model);

/////////////////////////////////////////////////////////
                        try {
                            Elements pictures = doc5.getElementsByClass("product-images owl-carousel js-modal-product-images").first().select("img");
                            // Elements pictures = doc5.getElementsByClass("thumb-container").first().select("img");

                            int z = 0;
                            //                      int y3 = 6;
                            int y3 = 15;
                            for (Element picture : pictures) {
                                System.out.println(pictures.get(z).select("img").attr("data-image-large-src"));
                                String Foto = pictures.get(z).select("img").attr("data-image-large-src");
//                                File f = new File(Foto);
//                                String FILENAME = "test/unikma/" + CatalogName + "/" + f.getName();
//                                String SvDPDFURL = Foto;
//                                File file = new File(FILENAME);
//                                URL url = new URL(SvDPDFURL);
//                                FileUtils.copyURLToFile(url, file);

                                Cell cell5555 = row.createCell(y3);
                                cell5555.setCellValue(Foto);
                                y3++;


                                z++;
                            }
                        } catch (java.lang.NullPointerException e) {
                            e.printStackTrace();
                        }
///////////////////////////////////////////////////////////

//
                    Cell cell2279 = row.createCell(0);
                    cell2279.setCellValue(dataID);



                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(Tovar+">");


                        Cell cell227u = row.createCell(2);
                        cell227u.setCellValue(Category);

                        Cell cell227 = row.createCell(3);
                        cell227.setCellValue(NamePrduct);


//
//
                    Cell cell224 = row.createCell(4);
                        cell224.setCellValue(MainPrice);


                        Cell cell224wer = row.createCell(5);
                        cell224wer.setCellValue(OldPrice);

                        Cell cell2242 = row.createCell(6);
                        cell2242.setCellValue(Description);

//                    Cell cell22422 = row.createCell(5);
//                    cell22422.setCellValue(Proizvoditel);

//                    Cell cell224221 = row.createCell(6);
//                    cell224221.setCellValue(MainFoto);
//
//                    Cell cell2242211 = row.createCell(14);
//                    cell2242211.setCellValue(MainFoto);
                        yyyy++;
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


//            }
//            System.out.println(Page);
//            Page++;
        }

    }
}
