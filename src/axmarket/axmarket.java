package axmarket;

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
 * Created by SretenskyVD on 19.11.2018.
 */
public class axmarket {
    public static void main(String[] args) throws IOException {
        String Path = "http://exmark.ru/";
        String CatalogName = "kepki";
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet1 = wb.createSheet(CatalogName);
        FileOutputStream fileOut = new FileOutputStream("book_" + CatalogName + ".xls");
        int yyy = 0;
        int yyyy = 0;

        try {
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);

        Document doc1 = Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("product-block product-block-detailed");

        for (Element link1 : links1) {
            System.out.println();
            String addressUrl3 = (links1.get(yyy).select("a[href]").attr("abs:href"));
            System.out.println(addressUrl3);


            Document doc2 = Jsoup.connect(addressUrl3).get();


                Document doc5 = Jsoup.connect(addressUrl3).get();

                String NameProduct = doc5.getElementsByTag("h1").text();
                System.out.println(NameProduct);

                String MainPrice = doc5.getElementsByClass("price-new").text();
                System.out.println(MainPrice);

                String Description = doc5.getElementsByClass("tab-pane active").html();
                System.out.println(Description);


//                  <input type="hidden" name="product_id" value="121" />
                String ID_product1 = doc5.getElementsByClass("stock-price-buttons").select("input[type=hidden]").attr("value");
                System.out.println(ID_product1);


                String MainPictures = doc5.getElementsByClass("thumbnails text-center").select("a").attr("abs:href");
                System.out.println(MainPictures);


                int rowCount = sheet.getLastRowNum();
                Row row = sheet.createRow(++rowCount);

                Elements pictures = doc5.getElementsByClass(" image-additional img-thumbnail-transparent colorbox").select("a");
                int z = 0;
                int y3 = 25;

                for (Element picture : pictures) {
                    System.out.println(pictures.get(z).select("a").attr("abs:href"));

                    String Foto = pictures.get(z).select("a").attr("abs:href");


                    Cell cell11 = row.createCell(y3);
                    cell11.setCellValue(Foto);
                    y3++;

                    z++;

                }


                Elements table = doc5.select("table");
                Iterator<Element> ite = table.select("td").iterator();
                Elements row2 = table.select("td");

                int y2 = 10;
                for (Element rows : row2) {

                    String Har = ite.next().text();
                    System.out.print(Har);
                    Cell cell1000 = row.createCell(y2);
                    cell1000.setCellValue(Har);
                    y2++;
                }

                System.out.println();

                Cell cell425 = row.createCell(0);
                cell425.setCellValue(ID_product1);


                Cell cell1 = row.createCell(1);
                cell1.setCellValue(NameProduct);

                Cell cell228 = row.createCell(2);
                cell228.setCellValue(MainPictures);

                Cell cell223 = row.createCell(3);
                cell223.setCellValue(Description);

                Cell cell224 = row.createCell(5);
                cell224.setCellValue(MainPrice);

                System.out.println();



                try {
                    FileOutputStream fileOut1 = new FileOutputStream("book_" + CatalogName + ".xls");
                    wb.write(fileOut1);
                    fileOut1.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();


                }

yyy++;
            }

        }

    }












