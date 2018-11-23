package spartapro;

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
 * Created by SretenskyVD on 23.11.2018.
 */
public class spartapro {
    public static void main(String[] args) throws IOException {

        String Tovar = "соевый";



        String Path = "http://spartapro.ru/category/"+Tovar;

        String CatalogName = Tovar;
        int LastPage = 5;
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


        int Page = 0;
        for (int count = 1; count <= LastPage; count++) {
            String  Path2 = Path+ "?page=" + Page;

   Document doc1 = Jsoup.connect(Path2).get();

            Elements links3 = doc1.getElementsByClass("product_image_with_icon");


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


                    String NameProduct = doc4.getElementsByClass("active").text();
                    System.out.println(NameProduct);

                    String MainPrice = doc4.getElementsByClass("field-item even currencyCodeRUB").first().text();
                    System.out.println(MainPrice);

                    String ID_prod = doc4.getElementsByClass("commerce-product-sku").text();
                    System.out.println(ID_prod);


                    String Proizvoditel55 = doc4.getElementsByClass("field field-name-field-brand field-type-taxonomy-term-reference field-label-hidden").text();
                    System.out.println(Proizvoditel55);


                    String SDescr = doc4.getElementsByClass("field field-name-body field-type-text-long field-label-hidden").get(1).html();
//                    System.out.println(SDescr);

                    String SDescrText = doc4.getElementsByClass("field field-name-body field-type-text-long field-label-hidden").get(1).text();
//                    System.out.println(SDescrText);

                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);


                    try {
                        Elements Options = doc4.getElementsByClass("form-select").select("option");
                        int z = 0;
                        int y3 = 5;

                        for (Element Option : Options) {
                            System.out.println(Options.get(z).text());

                            String MainOption = Options.get(z).text();


                            Cell cell11 = row.createCell(y3);
                            cell11.setCellValue(MainOption);
                            y3++;

                            z++;
                            System.out.println();
                        }
                    }
                    catch (java.lang.NullPointerException e){
                        e.printStackTrace();
                    }
///////////////////////////////////////////////////
                    try {
                        Elements smarts = doc4.getElementsByClass("field field-name-field-goal field-type-taxonomy-term-reference field-label-inline inline");
                        int z = 0;
                        int y4 = 22;

                        for (Element smart : smarts) {
                            System.out.println(smarts.get(z).text());

                            String SMART = smarts.get(z).text();


                            Cell cell4444 = row.createCell(y4);
                            cell4444.setCellValue(SMART);
                            y4++;
                            z++;
                            System.out.println();
                        }
                    }
                    catch (java.lang.NullPointerException e){
                        e.printStackTrace();
                    }

/////////////////////////////////////////////////////////
                    try {
                    Elements pictures = doc4.getElementsByClass("cloud-zoom-container").select("a");

                    int z = 0;
                    int y3 = 35;
                    for (Element picture : pictures) {
                        System.out.println(pictures.get(z).select("a").attr("abs:href"));

                        String Foto = pictures.get(z).select("a").attr("abs:href");
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


                    Cell cell227 = row.createCell(0);
                    cell227.setCellValue(ID_prod);


                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(NameProduct);


                    Cell cell224 = row.createCell(2);
                    cell224.setCellValue(MainPrice);

                    Cell cell2242 = row.createCell(3);
                    cell2242.setCellValue(Tovar);

                    Cell cell2243 = row.createCell(4);
                    cell2243.setCellValue(Proizvoditel55);

                    Cell cell225 = row.createCell(45);
                    cell225.setCellValue(SDescr);

                    Cell cell226 = row.createCell(46);
                    cell226.setCellValue(SDescrText);

                }catch (java.lang.IllegalArgumentException e){
                    e.printStackTrace();}

                catch (java.net.SocketTimeoutException e) {
                    e.printStackTrace();
                }
                catch (java.lang.IndexOutOfBoundsException e) {
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
