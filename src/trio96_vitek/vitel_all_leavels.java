package trio96_vitek;

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
 * Created by SretenskyVD on 29.11.2018.
 */
public class vitel_all_leavels {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/trio96_vitek/vitekru.crt.jks");

//        String Tovar = "tehnika-dlya-kuhni/hot-beverages-making/kettles/";

        //      String PathLevel2 = "https://vitek.ru/catalog/tehnika-dlya-kuhni/hot-beverages-making/";
     //   String PathLevel3 = "https://vitek.ru/catalog/tehnika-dlya-kuhni/";
        String PathLevel4 = "https://vitek.ru/catalog/";

//        String Path = "https://vitek.ru/catalog/"+Tovar;

        String CatalogName = "vitek_all_leavels";
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


//        int Page = 0;
//        for (int count = 1; count <= LastPage; count++) {
//            String  Path2 = Path+ "?page=" + Page;

/////////////////////leavel4
        Document docLeavel4 = Jsoup.connect(PathLevel4).get();
        Elements LinkLeavel4s = docLeavel4.getElementsByClass("main-page--block");
        int yLevel4 = 0;

        for (Element LinkLeavel4 : LinkLeavel4s) {

            String addressUrlLeavel4 = (LinkLeavel4s.get(yLevel4).select("a[href]").attr("abs:href"));


/////////////////////leavel3

        Document docLeavel3 = Jsoup.connect(addressUrlLeavel4).get();
        Elements LinkLeavel3s = docLeavel3.getElementsByClass("main-page--block");
        int yLevel3 = 0;

        for (Element LinkLeavel3 : LinkLeavel3s) {

            String addressUrlLeavel3 = (LinkLeavel3s.get(yLevel3).select("a[href]").attr("abs:href"));


            Document docLeavel2 = Jsoup.connect(addressUrlLeavel3).get();
            Elements LinkLeavel2s = docLeavel2.getElementsByClass("category-item");


            ////////////////////////////////////////////////////////// //    Здесь подключаемся к категории товара

            int yLevel2 = 0;
            for (Element LinkLeavel2 : LinkLeavel2s) {
                String addressUrlLeavel2 = (LinkLeavel2s.get(yLevel2).select("a[href]").attr("abs:href"));


//            Document doc1 = Jsoup.connect(Path).get();
                Document doc1 = Jsoup.connect(addressUrlLeavel2).get();

                Elements links3 = doc1.getElementsByClass("product-item");


                String Category = doc1.getElementsByTag("h1").text();
                System.out.println(Category);


///////////////////////////////////////////////////////        //Здесь перебираем ссылки товара
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

                        String NameProduct = doc4.getElementsByClass("productPage-title").text();
                        System.out.println(NameProduct);

                        String MainPrice = doc4.getElementsByClass("product-buy-info").select("span").text();
                        System.out.println(MainPrice);

                        String Proizvoditel55 = "Vitek";
                        System.out.println(Proizvoditel55);


                        String SDescr = doc4.getElementsByClass("tab-pane ").html();
                        System.out.println(SDescr);

                        String MainPictures1 = doc4.getElementsByClass("swiper-wrapper").select("img").attr("src");
                        String MainPictures = "https://vitek.ru" + MainPictures1;
                        System.out.println(MainPictures);

                        int rowCount = sheet.getLastRowNum();
                        Row row = sheet.createRow(++rowCount);

                        Elements table = doc4.select("table");
                        Iterator<Element> ite = table.select("td").iterator();
                        Elements row2 = table.select("td");

                        int y2 = 35;
                        for (Element rows : row2) {

                            String Har = ite.next().text();
                            System.out.print(Har);
                            Cell cell1000 = row.createCell(y2);
                            cell1000.setCellValue(Har);
                            y2++;
                        }

                        try {
                            Elements pictures = doc4.getElementsByClass("swiper-slide");

                            int z = 0;
                            int y3 = 6;
                            for (Element picture : pictures) {
                                System.out.println(pictures.get(z).select("img").attr("src"));

                                String Foto = "https://vitek.ru" + pictures.get(z).select("img").attr("src");
                                Cell cell5555 = row.createCell(y3);
                                cell5555.setCellValue(Foto);
                                y3++;
                                z++;
                            }
                        } catch (java.lang.NullPointerException e) {
                            e.printStackTrace();
                        }
///////////////////////////////////////////////////////////


                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(NameProduct);


                        Cell cell224 = row.createCell(2);
                        cell224.setCellValue(MainPrice);

                        Cell cell2243 = row.createCell(3);
                        cell2243.setCellValue(Proizvoditel55);

                        Cell cell227 = row.createCell(4);
                        cell227.setCellValue(Category);


                        Cell cell22434 = row.createCell(5);
                        cell22434.setCellValue(MainPictures);

                        Cell cell225 = row.createCell(25);
                        cell225.setCellValue(SDescr);


                    } catch (java.lang.IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (java.net.SocketTimeoutException e) {
                        e.printStackTrace();
                    } catch (java.lang.IndexOutOfBoundsException e) {
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
                yLevel2++;
            }
            yLevel3++;
        }
            yLevel4++;
        }
    }
}
