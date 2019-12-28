package tsvetomania.tut2;

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
 * Created by SretenskyVD on 04.09.2019.
 */
public class tut2 {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/tut/tut_ru.crt.jks");
        String Tovar = "Тепловизоры_для_смартфонов";
        String Manual_category =Tovar;
        String MyProxy = "200.240.244.7";
        int MyPort = 8080;
//        String Manual_Proizvoditel = "Цветомания";
        //    String MyUserAgent=  "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36"
        String MyUserAgent1= "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 5.0; Trident/4.0; InfoPath.1; SV1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 3.0.04506.30)";
        String MyUserAgent2= "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36)";
//        String Path = "https://unikma.ru/catalog/zabory_iz_svarnykh_paneley/?PAGEN_2=";

        String Path = "https://tut.ru/catalog/izmeritelnye_pribory/teplovizory_dlya_smartfonov/";

//keytool -import -v -file S:/ProjectJava/Kwork/src/tut/tut_ru.crt -keystore S:/ProjectJava/Kwork/src/tut/tut_ru.crt.jks -storepass drowssap

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
//        int CountPage = (Page+LastPage)-1;
        for (int count = 1; count <= LastPage; count++) {
            String  Path2 = Path+"?PAGEN_1="+ Page;
//            String  Path2 = Path;

            int RandomTime3 = (int) (Math.random() * ((30000 -7000 ) + 1)) + 7000;

//            Document doc1 = Jsoup.connect(Path2).get();
            Document doc1 = Jsoup.connect(Path2)
//            .proxy(MyProxy, MyPort)
                    .timeout(RandomTime3)
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .followRedirects(true)
                    .userAgent(MyUserAgent2)
                    .get();



            Elements links3 = doc1.getElementsByClass("item-title secEl_title");
            int yyy = 0;
            for (Element link3 : links3) {



//                String NameProduct = doc1.getElementsByClass("catalog__name").get(yyy).text();
//                System.out.println(NameProduct);
//

                System.out.println();
                String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                System.out.println(addressUrl3);

                int RandomTime = (int) (Math.random() * ((30000 -7000 ) + 1)) + 7000;



                try {
  //                  Document doc4 = Jsoup.connect(addressUrl3).get();
                    Document doc4 = Jsoup.connect(addressUrl3)
 //                           .proxy(MyProxy, MyPort)
                            .timeout(RandomTime)
                            .ignoreHttpErrors(true)
                            .ignoreContentType(true)
                            .followRedirects(true)
                            .userAgent(MyUserAgent2)
                            .get();

//                    String dataID = doc1.getElementsByClass("wish_item to").get(yyy).attr("data-item");
                    String dataID = doc4.getElementsByClass("detail_page_article").text();
                    System.out.println(dataID);

//                    String MainPrice = doc1.getElementsByClass("price_value").get(yyy).text();
                    String MainPrice = doc1.getElementsByClass("cost prices clearfix").get(yyy).text();
                    System.out.println(MainPrice);


//                    String Category = doc4.getElementsByClass("active").select("span").first().text();
//);
                    String Category = Manual_category;
                    System.out.println(Category);


                    String NamePrduct =   doc4.getElementsByTag("h1").text();
                    System.out.println(NamePrduct);



//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                    //     String Proizvoditel = Manual_Proizvoditel;
//                    String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                    System.out.println(Proizvoditel);

                    String Description  = doc4.getElementsByClass("detail_text").html();
                    System.out.println(Description);

                    String AllSub =   doc4.getElementsByTag("meta").select("[itemprop=category]").attr("content");
                    System.out.println(AllSub);

                    String SubCategory  = doc4.getElementsByClass("menu-item active").text();
                    System.out.println(SubCategory);


                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);


                    Elements table = doc4.getElementsByClass("props_list");
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

///////////////////////////////////
                    String SuperMainFoto = doc4.getElementsByClass("current").first().select("a").attr("abs:href");
                    System.out.println(SuperMainFoto);
//
//                    String Model = doc4.getElementsByClass("list-unstyled").first().select("li").get(1).text();
//                    System.out.println(Model);

/////////////////////////////////////////////////////////
                    try {
                        Elements pictures = doc4.getElementsByClass("slides_block").select("li");

                        int z = 0;
                        //                      int y3 = 6;
                        int y3 = 6;
                        for (Element picture : pictures) {
                            System.out.println(pictures.get(z).select("li").attr("data-big_img"));
                            String Foto = "https://tut.ru" + pictures.get(z).select("li").attr("data-big_img");
//                            File f = new File(Foto);
//                            String FILENAME = "test/unikma/"  + CatalogName + "/" + f.getName();
//                            String SvDPDFURL = Foto;
//                            File file = new File(FILENAME);
//                            URL url = new URL(SvDPDFURL);
//                            FileUtils.copyURLToFile(url, file);

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
                    cell2279.setCellValue(dataID);

                    Cell cell227 = row.createCell(1);
                    cell227.setCellValue(NamePrduct);


                    Cell cell1 = row.createCell(2);
                    cell1.setCellValue(Tovar);

                    Cell cell1e = row.createCell(3);
                    cell1e.setCellValue(SubCategory);

//
                    Cell cell224 = row.createCell(4);
                    cell224.setCellValue(MainPrice);

                    Cell cell2242 = row.createCell(5);
                    cell2242.setCellValue(Description);


                    Cell cell224211 = row.createCell(6);
                    cell224211.setCellValue(AllSub);

                    Cell cell224211y = row.createCell(7);
                    cell224211y.setCellValue(SuperMainFoto);

//                    Cell cell22422 = row.createCell(5);
//                    cell22422.setCellValue(Proizvoditel);

//                    Cell cell224221 = row.createCell(6);
//                    cell224221.setCellValue(MainFoto);
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
//                    FileOutputStream fileOut1 = new FileOutputStream("book_" + CatalogName + "_с_" + Page + "_до_"+CountPage+ ".xls");
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
            try {
                int RandomTime2 = (int) (Math.random() * ((20000 -1000 ) + 1)) + 1000;
                Thread.sleep(RandomTime2);
            } catch(InterruptedException ex) {}
            Page++;
        }

    }
}
