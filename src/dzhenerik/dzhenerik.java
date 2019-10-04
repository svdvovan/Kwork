package dzhenerik;

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

public class dzhenerik {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "F:/Projects/Kwork2/src/dzhenerik/dzheneriki.crt.jks");
//keytool -import -v -file F:/Projects/Kwork2/src/dzhenerik/dzheneriki.crt -keystore F:/Projects/Kwork2/src/dzhenerik/dzheneriki.crt.jks -storepass drowssap
//        String Tovar = "Виагра";
        String Tovar = "Сиалиса";
        String Manual_category =Tovar;
        String Manual_Proizvoditel = "Orac Decor";

//        String Path = "https://xn--d1acacmanv2a.xn--p1ai/dzheneriki-viagra/";
        String Path = "https://xn--d1acacmanv2a.xn--p1ai/dzheneriki-cialis/";


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

            Elements links3 = doc1.getElementsByClass("ccItemPhoto");
            int yyy = 0;
            int yyyy=1;
            for (Element link3 : links3) {
//
//                String ID = doc1.getElementsByClass("articul").get(yyy).text();
//                System.out.println(ID);
//
                String MainPrice = doc1.getElementsByClass("ccItemPriceNew").get(yyy).text();
                System.out.println(MainPrice);

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


                    String NamePrduct =   doc4.getElementsByTag("h1").text();
                    System.out.println(NamePrduct);



//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                    //     String Proizvoditel = Manual_Proizvoditel;
//                    String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                    System.out.println(Proizvoditel);

                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);


//                    Elements table = doc4.getElementsByClass("table table-bordered");
//                    Iterator<Element> ite = table.select("td").iterator();
//
//                    Elements row2 = table.select("td");
//
//                    int y2 = 15;
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
//                    String MainFoto = doc4.getElementsByClass("det_slider").select("a").first().attr("abs:href");
//                    String MainFoto = MainFoto1.toString();

//
//                    String Model = doc4.getElementsByClass("list-unstyled").first().select("li").get(1).text();
//                    System.out.println(Model);


//////////////////////////////////////////////////////////



//
//                    String Model1 = doc4.getElementsByClass("BREND").select("p").first().text();
//                    String Model = doc4.getElementsByClass("BREND").select("p").get(1).text();
//                    System.out.println(Model1);
//                    System.out.println(Model);

//                String MainPrice = doc1.getElementsByClass("price").get(yyyy).text();
//                System.out.println(MainPrice);



                    try {
                        Elements table = doc4.getElementsByClass("prodInstructions");
                        Iterator<Element> ite = table.select("th").iterator();

                        Elements row2 = table.select("th");

                        int y2 = 25;

                        for (Element rows : row2) {

                            String Har = ite.next().text();

                            System.out.print(Har);


                            Cell cell1000 = row.createCell(y2);
                            cell1000.setCellValue(Har);



                            y2=y2+2;





                            }

                        Elements table3 = doc4.getElementsByClass("prodInstructions");
                        Iterator<Element> ite3 = table3.select("td").iterator();

                        Elements row3 = table3.select("td");

                        int y3 = 26;

                        for (Element rows3 : row3) {

                            String Har3 = ite3.next().text();

                            System.out.print(Har3);


                            Cell cell10003 = row.createCell(y3);
                            cell10003.setCellValue(Har3);


                            y3=y3+2;
                        }


                        Elements table4 = doc4.getElementsByClass("prodPrice");
                        Iterator<Element> ite4 = table4.select("th").iterator();

                        Elements row4 = table4.select("th");

                        int y4 = 45;

                        for (Element rows4 : row4) {
                            String Har4 = ite4.next().text();
                            System.out.print(Har4);
                            Cell cell10004 = row.createCell(y4);
                            cell10004.setCellValue(Har4);
                            y4=y4+2;

                        }

                        Elements table5 = doc4.getElementsByClass("prodPrice");
                        Iterator<Element> ite5 = table5.select("td").iterator();

                        Elements row5 = table5.select("td");

                        int y5 = 46;

                        for (Element rows5 : row5) {
                            String Har5 = ite5.next().text();
                            System.out.print(Har5);
                            Cell cell10005 = row.createCell(y5);
                            cell10005.setCellValue(Har5);
                            y5=y5+2;

                        }










//                        Elements chars = doc4.getElementsByClass("chars").select("li");
//
//                        int z = 0;
//                        int y3 = 16;
//                        int y4 =17;
//                        for (Element charses : chars) {
//
//
//                            String ATTRIBUTE = chars.get(z).select("p").first().text();
//                            String ATTRIBUTE2 = chars.get(z).select("p").get(1).text();
//
//                            System.out.println(ATTRIBUTE + " " +ATTRIBUTE2);
//
//
//                            Cell cell5555 = row.createCell(y3);
//                            Cell cell6666 = row.createCell(y4);
//
//
//                            cell5555.setCellValue(ATTRIBUTE);
//                            cell6666.setCellValue(ATTRIBUTE2);
//                            y3=y3+2;
//                            y4=y4+2;
//
//                            z++;
//                        }
                    }
                    catch (java.lang.NullPointerException e){
                        e.printStackTrace();
                    }













/////////////////////////////////////////////////////////
                    try {
                        Elements pictures = doc4.getElementsByClass("prodThumbs").select("a");

                        int z = 0;
                        int y33 = 6;
                        for (Element picture : pictures) {
                            System.out.println(pictures.get(z).select("a").attr("abs:href"));

                            String Foto = pictures.get(z).select("a").attr("abs:href");
                            Cell cell5555 = row.createCell(y33);
                            cell5555.setCellValue(Foto);
                            y33++;
                            z++;
                        }
                    }
                    catch (java.lang.NullPointerException e){
                        e.printStackTrace();
                    }
///////////////////////////////////////////////////////////


//                    Cell cell227 = row.createCell(0);
//                    cell227.setCellValue(ID);


                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(Category);

//
                    Cell cell224 = row.createCell(44);
                    cell224.setCellValue(MainPrice);

                    Cell cell2242 = row.createCell(3);
                    cell2242.setCellValue(NamePrduct);

//                    Cell cell22422 = row.createCell(4);
//                    cell22422.setCellValue(Proizvoditel);

//                    Cell cell224221 = row.createCell(5);
//                    cell224221.setCellValue(MainFoto);

//                    Cell cell22422112 = row.createCell(15);
//                    cell22422112.setCellValue(Description);

//                    Cell cell2242211 = row.createCell(14);
//                    cell2242211.setCellValue(MainFoto);

                    try {
                        String Description = doc4.getElementsByClass("col-xs-12 col-sm-12").html();
//                        String Description = doc4.getElementsByClass("row").select("[class=col-xs-12 col-sm-12]").not("div").html();
//                        String Description = doc4.select("h2[id=pdesk]").next().text();
                        System.out.println(Description);
                        Cell cell22422112 = row.createCell(15);
                        cell22422112.setCellValue(Description);
                    }
                    catch (java.lang.NullPointerException e){
                        e.printStackTrace();
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
