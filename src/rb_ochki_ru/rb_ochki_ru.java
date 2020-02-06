package rb_ochki_ru;

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
 * Created by SretenskyVD on 06.02.2020.
 */
public class rb_ochki_ru {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/rb_ochki_ru/rb.cer.jks");
        String Tovar = "AVIATOR";
        String Manual_category =Tovar;
//        String Manual_Proizvoditel = "Цветомания";

        String Path = "https://rb-ochki.ru/katalog/aviator/?page=";
        //       String Path = "http://www.funkofunatic.ru/";
        //cd C:\Program Files\Java\jdk1.7.0_79\bin
//keytool -import -v -file S:/ProjectJava/Kwork/src/rb_ochki_ru/rb.cer -keystore S:/ProjectJava/Kwork/src/rb_ochki_ru/rb.cer.jks -storepass drowssap

        String CatalogName = Tovar;
        int LastPage = 1; //234  Completed Models
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
        int Page = 1;
        for (int count = 1; count <= LastPage; count++) {
            String  Path2 = Path + Page;
//        String  Path2 = Path;



            Document doc1 = Jsoup.connect(Path2).get();

            Elements links3 = doc1.getElementsByClass("item");
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

//                String NamePrduct = doc1.getElementsByClass("ProductsListName").get(yyy).select("a").text();
//                System.out.println(NamePrduct);
//
//                String MainPrice = doc1.getElementsByClass("ProductsListPrice").get(yyy).text();
//                System.out.println(MainPrice);

                System.out.println();
                String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                System.out.println(addressUrl3);

//                open(addressUrl3);
//                $(By.name("agree"));


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

                    String MainPrice = doc4.getElementsByClass("item_price").text();
                    System.out.println(MainPrice);

//                    String Izmerenie = doc4.getElementsByClass("produnit").text();
//                    System.out.println(Izmerenie);


                    String NamePrduct =   doc4.getElementsByTag("h1").text();
                    System.out.println(NamePrduct);

                    String MainFoto = doc4.getElementsByClass("shk-image").attr("src");
                    System.out.println(MainFoto);

//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                    //     String Proizvoditel = Manual_Proizvoditel;
//                    String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                    System.out.println(Proizvoditel);

//                    String SKU  = doc4.getElementsByClass("product-num").text();
//                    System.out.println(SKU);

                    String SKU  = doc4.getElementsByTag("h5").select("b").text();
                    System.out.println(SKU);

                    //.select("[name=id]").attr("value");

//                     String Description  = doc4.getElementsByTag("div").select("[itemprop=description]").select("p").text();
                    String Description  = doc4.getElementsByClass("zxcm").select("p").attr("itemprop[description]");
                    System.out.println(Description);

//                    String Description_text  = doc4.getElementsByClass("tab-content").text();
//                    System.out.println(Description_text);
////
//                    String breadcrumbs_last  = doc4.getElementsByClass("posted_in").text();
//                    System.out.println(breadcrumbs_last);



                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);

//
                    Cell cell227p = row.createCell(0);
                    cell227p.setCellValue("hob-"+ SKU);

                    Cell cell227 = row.createCell(1);
                    cell227.setCellValue(NamePrduct);


                    Cell cell1 = row.createCell(2);
                    cell1.setCellValue(Tovar);

////
                    Cell cell224 = row.createCell(3);
                    cell224.setCellValue(MainPrice);



                    Cell cell224221 = row.createCell(4);
                    cell224221.setCellValue("https://rb-ochki.ru"+MainFoto);



                    Cell cell2242 = row.createCell(19);
                    cell2242.setCellValue(Description);


//
                    Elements links_attr = doc4.getElementsByClass("product-properties").select("label");
                    int attr= 0;
                    int attr_cell= 20;
                    for (Element links_attrs : links_attr) {
//                        String atr = links_attr.get(attr).select("span").text();
                        String atr_znachenie = links_attr.get(attr).select("label").text();
//                        System.out.println(atr);
                        System.out.println(atr_znachenie);
                        attr++;
                        Cell cell5555atr = row.createCell(attr_cell);
                        cell5555atr.setCellValue(atr_znachenie);
                        attr_cell=attr_cell+2;


                    }
/////////////////////////////////////////////

                    Elements links_opc = doc4.getElementsByClass("choose").select("span");
                    int opc= 0;
                    int opc_cell=50;
                    for (Element links_opcs : links_opc) {
                        String opc_znachenie = links_opc.get(opc).select("span").first().text();
                        System.out.println(opc_znachenie);
                        opc++;
                        Cell cell5555opc = row.createCell(opc_cell);
                        cell5555opc.setCellValue(opc_znachenie);
                        opc_cell++;


                    }




/////////////////////////////////////////////////////////
                    try {
                        Elements pictures = doc4.getElementsByClass("thumbs").select("a");

                        int z = 0;
                        //                      int y3 = 6;
                        int y3 = 5;
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
