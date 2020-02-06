package dollsempire;

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
 * Created by SretenskyVD on 28.12.2019.
 */
public class dollsempire {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/dollsempire/dollsempire.ru.cer.jks");
        String Tovar = "Эвер Афтер Хай";
        String Manual_category =Tovar;

//        String Path = "https://dollsempire.ru/little_pullip_dolls.html";
//keytool -import -v -file S:/ProjectJava/Kwork/src/dollsempire/dollsempire.ru.cer -keystore S:/ProjectJava/Kwork/src/dollsempire/dollsempire.ru.cer.jks -storepass drowssap

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
///////////////////уровень категории
String PathCategory = "https://dollsempire.ru/ever_after_high.html";
        String Zag2 = "Эвер Афтер Хай";
        String TypeClass = "subcat-linkh";


        Document docCategory = Jsoup.connect(PathCategory).get();


        Elements linksCategory = docCategory.getElementsByClass(TypeClass);
        int CountCategory=0;

            for (Element linksCategorys : linksCategory) {

                String NameCategory = docCategory.getElementsByClass(TypeClass).text();
                System.out.println(NameCategory);
                String addressCategory = linksCategory.get(CountCategory).select("a[href]").attr("abs:href");
                String Broad = docCategory.getElementsByTag("h1").text();
                System.out.println(Broad);


//////////уровень подкатегории.
                int LastPage = 15;
                int Page = 1;
                for (int count = 1; count <= LastPage; count++) {
//                    String Path2 = addressCategory + "?page=" + Page;
                    String Path2 = addressCategory + "?page=" + Page;
//        String  Path2 = Path;
//                Document doc1 = Jsoup.connect(Path2).get();
                    try {
                        Document doc1 = Jsoup.connect(Path2).get();

                        Elements links3 = doc1.getElementsByClass("catpr-name");
                        int yyy = 0;
                        for (Element link3 : links3) {

                            System.out.println();
                            String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                            System.out.println(addressUrl3);

                            String CategoryAutoName = doc1.getElementsByTag("h1").text();
                            System.out.println(CategoryAutoName);


//////////уровень товара
                           String SravnenieCategoriy = "https://dollsempire.ru/_nabor:_sdelay_mylo_monster_high.html";
                            if (!addressUrl3.equals(SravnenieCategoriy)) {
                                try {
                                    Document doc4 = Jsoup.connect(addressUrl3)
//                            .proxy("34.95.167.51", 8080)
//                            .timeout(20000)
//                            .ignoreHttpErrors(true)
//                            .ignoreContentType(true)
//                            .followRedirects(true)
//                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                                            .get();

                                    String Category = Manual_category;
                                    System.out.println(Category);


                                    String MainPrice = doc4.getElementsByClass("new-pricef").text();
                                    System.out.println(MainPrice);
                                    String OldPrice = doc4.getElementsByClass("old-pricef").text();
                                    System.out.println(OldPrice);

                                    String NamePrduct = doc4.getElementsByTag("h1").text();
                                    System.out.println(NamePrduct);

                                    String Proizvoditel = doc4.getElementsByClass("descr-prf").first().text();
                                    System.out.println(Proizvoditel);
                                    String SKU = doc4.getElementsByClass("descr-prf").last().text();
                                    System.out.println(SKU);

                                    String FullBroad = doc4.getElementById("navigator").text();
                                    System.out.println(FullBroad);

//                                    String BroadLast = doc4.getElementById("curcatlink").text();
//                                    System.out.println(BroadLast);

                                    String BroadLast = doc4.getElementsByClass("leftm-link").text();
//                                    System.out.println(BroadLast);


//                            String Description = doc4.getElementById("descr").html();
//                            System.out.println(Description);


                                    String MainFoto = doc4.getElementsByClass("product-image").select("a").attr("abs:href");
                                    System.out.println(MainFoto);

                                    int rowCount = sheet.getLastRowNum();
                                    Row row = sheet.createRow(++rowCount);

//
                                    Cell cell227p = row.createCell(0);
                                    cell227p.setCellValue(SKU);

                                    Cell cell1 = row.createCell(2);
                                    cell1.setCellValue(Tovar);

                                    Cell cell224 = row.createCell(3);
                                    cell224.setCellValue(MainPrice);

                                    Cell cell224x = row.createCell(4);
                                    cell224x.setCellValue(OldPrice);

                                    Cell cell224x1 = row.createCell(5);
                                    cell224x1.setCellValue(NamePrduct);

                                    Cell cell22411 = row.createCell(6);
                                    cell22411.setCellValue(Proizvoditel);


                                    Cell cell2241111 = row.createCell(7);
                                    cell2241111.setCellValue(BroadLast);

                                    Cell cell224111 = row.createCell(8);
                                    cell224111.setCellValue(Broad);


                                    Cell cell22411111 = row.createCell(9);
                                    cell22411111.setCellValue(CategoryAutoName);

                                    Cell cell224111111 = row.createCell(9);
                                    cell224111111.setCellValue(FullBroad);


//
//
//                            Cell cell2242 = row.createCell(11);
//                            cell2242.setCellValue(Description);


                                    Cell cell224221 = row.createCell(12);
                                    cell224221.setCellValue(MainFoto);


//////////////////////////////////////////


/////////////////////////////////////////////////////////
                                    try {
                                        Elements pictures = doc4.getElementsByClass("gallery").select("a");

                                        int z = 0;
                                        int y3 = 13;
                                        for (Element picture : pictures) {
                                            System.out.println(pictures.get(z).select("a[href]").attr("abs:href"));
                                            String Foto = pictures.get(z).select("a[href]").attr("abs:href");
                                            Cell cell5555 = row.createCell(y3);
                                            cell5555.setCellValue(Foto);
                                            y3++;


                                            z++;
                                        }
                                    } catch (java.lang.NullPointerException e) {
                                        e.printStackTrace();
                                    }
///////////////////////////////////////////////////////////


                                    String Description = doc4.getElementById("descr").html();
                                    System.out.println(Description);

                                    Cell cell2242 = row.createCell(11);
                                    cell2242.setCellValue(Description);

                                } catch (java.lang.IllegalArgumentException e) {
                                    e.printStackTrace();
                                } catch (java.net.SocketTimeoutException e) {
                                    e.printStackTrace();
                                } catch (java.lang.IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                } catch (java.lang.NullPointerException e) {
                                    e.printStackTrace();
                                }
                            } ///if
                            try {
                                FileOutputStream fileOut1 = new FileOutputStream("book_" + CatalogName + ".xls");
                                wb.write(fileOut1);
                                fileOut1.close();

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println();
                            yyy++;

                        }/////////цикл link3
                        System.out.println("===============Страница======== " + Page);
                        Page++;
                                         } catch (java.lang.IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (java.net.SocketTimeoutException e) {
                    e.printStackTrace();
                } catch (java.lang.IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (java.lang.NullPointerException e) {
                    e.printStackTrace();
                }
                }////////////уровень подкатегории Page
                    CountCategory=CountCategory+1;
            }//уровень категории
//        }//уровень сравнения категории

    }///метод Main
} ////класс
