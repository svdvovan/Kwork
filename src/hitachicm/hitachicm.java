package hitachicm;

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
 * Created by SretenskyVD on 16.11.2020.
 */
public class hitachicm {
    public static void main(String[] args) throws IOException {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/hitachicm/hitachicm.cer.jks");
        String Tovar = "Экскаваторы cо сверхдлинным оборудованием";
        String Manual_category =Tovar;
//        String Suff ="o3221e11о1-19";
//        String Artikul_text="Код";
//        String Manual_Proizvoditel = "Цветомания";

        String Path = "https://www.hitachicm.ru/produkciya/ekskavatory/mini-ekskavatory/";
//keytool -import -v -file S:/ProjectJava/Kwork/src/hitachicm/hitachicm.cer -keystore S:/ProjectJava/Kwork/src/hitachicm/hitachicm.cer.jks -storepass drowssap

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
//Path+"?PAGEN_1="+ Page;
        //  int Page = 26;
        int Page = 1;
        for (int count = 1; count <= LastPage; count++) {
            if(Page==1) {
                String Path2 = Path;
//            }
//            String  Path2 = Path+"?PAGEN_1="+ Page;
//            String  Path2 = Path+"/page_"+ Page;
//            if(Page==1) {
//                Page = Page - 1;
//                String Path2 = Path + "/p/" + Page;
//            }
            }
            String  Path2 = Path+"?page="+ Page;
//            String  Path2 = Path;



            Document doc1 = Jsoup.connect(Path2).get();


//            String[] links3 = {
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/mini-ekskavatory/zx26u-5a/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/mini-ekskavatory/zx33u-5a/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/mini-ekskavatory/zx38u-5a/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/mini-ekskavatory/zx48u-5a/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/mini-ekskavatory/zx55u-5a/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/mini-ekskavatory/zx65usb-5a/"
//            };

//            String[] links3 = {
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx70-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx130-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx160lc-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx180lcn-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx200-5g-zx200lc-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx210lcn-5a/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx210h-5g-zx210lch-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx210k-5g-zx210lck-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx240-5g-zx240lc-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx250h-5g-zx250lch-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx250k-5g-zx250lck-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx300-5a-zx300lc-5a/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx330-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx350h-5g-zx350lch-5g-2/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx350k-5g-zx350lck-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx380lc-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-srednego-klassa/zx400lch-5g/"
//            };
//            String[] links3 = { "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx470-5g-zx470lc-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx470h-5g-zx470lch-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx470r-5g-zx470lcr-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx470-5g-ld/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx670-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx670lcr-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx870-5g-zx870lc-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx870h-5gzx870lch-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx870lcr-5g/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/ekskavatory-krupnogo-klassa/zx850870h-3-ld/"
//            };
//            String[] links3 = {"https://www.hitachicm.ru/produkciya/ekskavatory/gornye-ekskavatory/ex1200-7/",
//            "https://www.hitachicm.ru/produkciya/ekskavatory/gornye-ekskavatory/ex1900-6/",
//            "https://www.hitachicm.ru/produkciya/ekskavatory/gornye-ekskavatory/ex2600-7/",
//            "https://www.hitachicm.ru/produkciya/ekskavatory/gornye-ekskavatory/ex3600-6/",
//            "https://www.hitachicm.ru/produkciya/ekskavatory/gornye-ekskavatory/ex3600-7/",
//            "https://www.hitachicm.ru/produkciya/ekskavatory/gornye-ekskavatory/ex5600-6/",
//            "https://www.hitachicm.ru/produkciya/ekskavatory/gornye-ekskavatory/ex8000-6/"};

//            String[] links3 = {"https://www.hitachicm.ru/produkciya/ekskavatory/kolesnye-ekskavatory/zx170w-5a/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/kolesnye-ekskavatory/zx190w-5a/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/kolesnye-ekskavatory/zx210w-5a/"
//            };
//            String[] links3 = {
//                    "https://www.hitachicm.ru/produkciya/kolesnye-pogruzchiki/zw100/",
//                    "https://www.hitachicm.ru/produkciya/kolesnye-pogruzchiki/zw120/",
//                    "https://www.hitachicm.ru/produkciya/kolesnye-pogruzchiki/zw140/",
//                    "https://www.hitachicm.ru/produkciya/kolesnye-pogruzchiki/zw180-5a/",
//                    "https://www.hitachicm.ru/produkciya/kolesnye-pogruzchiki/zw220-5a/",
//                    "https://www.hitachicm.ru/produkciya/kolesnye-pogruzchiki/zw250/",
//                    "https://www.hitachicm.ru/produkciya/kolesnye-pogruzchiki/zw310-5a/",
//                    "https://www.hitachicm.ru/produkciya/kolesnye-pogruzchiki/zw370g/",
//                    "https://www.hitachicm.ru/produkciya/kolesnye-pogruzchiki/zw550/",
//            };
//            String[] links3 = {"https://www.hitachicm.ru/produkciya/samosvaly-s-zhestkoj-ramoj/eh3500ac-3/",
//                    "https://www.hitachicm.ru/produkciya/samosvaly-s-zhestkoj-ramoj/eh4000ac-3/",
//                    "https://www.hitachicm.ru/produkciya/samosvaly-s-zhestkoj-ramoj/eh5000ac-3/"
//            };
//            String[] links3 = {"https://www.hitachicm.ru/produkciya/krany/scx550e/",
//                    "https://www.hitachicm.ru/produkciya/krany/scx700e/",
//                    "https://www.hitachicm.ru/produkciya/krany/scx1000a-3/",
//                    "https://www.hitachicm.ru/produkciya/krany/scx1200-3/",
//                    "https://www.hitachicm.ru/produkciya/krany/scx1500a-3/"
//            };

//            String[] links3 = {
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/demolition-excavators/zx350lck-3-hrd/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/demolition-excavators/zx480lck-3-hrd/"
//            };

//            String[] links3 = {
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/ekskavatory-s-teleskopicheskoy-rukoyatyu/zx330lc-5g-cta-25m/",
//                    "https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/ekskavatory-s-teleskopicheskoy-rukoyatyu/zx330lc-5a-cta-30m/"
//            };

            String[] links3 = {"https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/ekskavatory-sverhdlinnoe-oborudovanie/zx200lc-5g-slf-h15/",
                    "https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/ekskavatory-sverhdlinnoe-oborudovanie/zx200lc-5g-slf-h18/",
                    "https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/ekskavatory-sverhdlinnoe-oborudovanie/zx240lc-5g-slf-h18/",
                    "https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/ekskavatory-sverhdlinnoe-oborudovanie/zx240lc-5g-slf-h20/",
                    "https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/ekskavatory-sverhdlinnoe-oborudovanie/zx330lc-5g-slf-h20/",
                    "https://www.hitachicm.ru/produkciya/ekskavatory/special-naya-tehnika/ekskavatory-sverhdlinnoe-oborudovanie/zx330lc-5g-slf-h22/"

            };


            int yyy = 0;
            int yyyy =1;
            for (String link3 : links3) {

//                String dataID = doc1.getElementsByClass("button-text catalog__button\n" +
//                        "\t\t\t\t\tcatalog__button-price addToBasket").get(yyy).attr("data-id");
//                System.out.println(dataID);


//                String dataID = doc1.getElementsByClass("cs-product-gallery__sku cs-goods-sku").get(yyy).select("span").attr("title");
//                System.out.println(dataID);

//                String NameProduct = doc1.getElementsByClass("noo-product-title").select("h3").get(yyy).text();
//                System.out.println(NameProduct);
//                String Description_main = doc1.getElementsByClass("noo-product-excerpt").get(yyy).text();
//                System.out.println(Description_main);
////
//                String MainPrice = doc1.getElementsByClass("quantity").get(yyy).select("input").attr("data-price");
//                System.out.println(MainPrice);
//                String MainPrice = doc1.getElementsByClass("cs-custom-button cs-custom-button_color_main cs-product-gallery__btn cs-product-gallery__btn_buy csjs-button-buy js-product-buy-button js-buy-button__text").get(yyy).attr("data-product-price");
//                System.out.println(MainPrice);
//                String MainPrice = doc1.getElementsByClass("price-current").get(yyy).text();
//                System.out.println(MainPrice);
//                String MainPrice = doc1.getElementsByClass("cs-goods-price").get(yyy).select("span").first().text();
//                System.out.println(MainPrice);
//cs-product-gallery__image-link
//cs-custom-button cs-custom-button_color_main cs-product-gallery__btn cs-product-gallery__btn_buy csjs-button-buy js-product-buy-button js-buy-button__text
                System.out.println();
//                String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                String addressUrl3 = (link3);
                System.out.println(addressUrl3);

//                int x = (int)(Math.random() * ((100000 - 100) + 1)) + 100;
//                String ID_random = Suff +"_"+ x;





                try {
                    Document doc4 = Jsoup.connect(addressUrl3)
//                            .proxy("201.174.52.27", 49229)
//                            .timeout(50000)
//                            .ignoreHttpErrors(true)
//                            .ignoreContentType(true)
//                            .followRedirects(true)
//                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                            .get();




//                    String Category = doc4.getElementsByClass("active").select("span").first().text();
//);
                    String Category = Manual_category;
                    System.out.println(Category);

//                    String SubCategory = doc4.getElementsByTag("h1").select("span").text();
//                    System.out.println(SubCategory);
//
//                    String AutoCategory = doc4.getElementsByClass("b-path__item b-breadcrumb__current").prev().text();
//                    System.out.println(AutoCategory);
//
//                    String MainPrice = doc4.getElementsByClass("product-card-top-info__price").text();
//                    System.out.println(MainPrice);


                    String NameProduct = doc4.getElementsByTag("h1").text();
//                    String NameProduct = doc4.getElementsByClass("modal-header-content").select("span[class=h1]").text();

                    System.out.println(NameProduct);
//
//                    String NameProduct = doc4.getElementsByClass("site-path").select("span").text();
//                    System.out.println(NameProduct);
//                    String NamePrduct =   doc4.getElementsByClass("al-title title").text();
//                    System.out.println(NamePrduct);

//                    String sku =   doc4.getElementsByClass("goodsDataMainModificationsList").select("[name=id]").attr("value");
//                    System.out.println(sku);

//                    String sku =   doc4.getElementsByClass("shop2-product-article").text();
//                    System.out.println(sku);
//
//                    String Price =   doc4.getElementsByClass("prices-current js-prices-current").text();
//                    System.out.println(Price);
//
//                    String  Pictures =   doc4.getElementsByClass("product-simple-image").select("a").attr("abs:href");
//                    System.out.println(Pictures);

//                    String smallCategory =   doc4.getElementsByClass("posted_in").select("a").text();
//                    System.out.println(smallCategory);



//                    String Proizvoditel =   doc4.getElementsByTag("a").select("[target=_blank]").first().text();
                    //     String Proizvoditel = Manual_Proizvoditel;
//                    String Proizvoditel  = doc4.getElementsByClass("list-unstyled").first().select("li").get(0).text();
//                    System.out.println(Proizvoditel);

//                    String Description  = doc4.getElementsByClass("col-sm-6 overview-content").first().html();
//                    System.out.println(Description);
//вот здесь решение https://stackru.com/questions/35302164/kak-vyibrat-vse-dochernie-elementyi-s-odnim-i-tem-zhe-tegom-extable-krome-pervogo-i-poslednego-s-pomoschyu-jsoup
                    String Description  = doc4.getElementsByClass("wpb_text_plain_column wpb_content_element ").html();
//                    System.out.println(Description);


//
//                    String Description2  = doc4.getElementsByClass("app-article").get(1).select("li").html();
//                    System.out.println(Description2);

//                    String material  = doc4.getElementsByClass("prod-info-material").text();
//                    System.out.println(material);



                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(++rowCount);
//

//                    Elements table = doc4.getElementsByClass("table specifications-table").select("tr");
//                    Iterator<Element> ite = table.select("th").iterator();
//
//                    Elements row2 = table.select("th");
//
//                    int y2 = 17;
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
//                        y2=y2+2;
//
//                    }
//
//
//                    Elements table2 = doc4.getElementsByClass("table specifications-table").select("tr");
//                    Iterator<Element> ite22 = table2.select("td").iterator();
//
//                    Elements row22 = table2.select("td");
//
//                    int y22 = 18;
//
//                    for (Element rows2 : row22) {
//
//                        String Har2 = ite22.next().text();
//
//                        System.out.print(Har2);
//
//
//                        Cell cell10002 = row.createCell(y22);
//                        cell10002.setCellValue(Har2);
//
//
//
//                        y22=y22+2;
//
//                    }
//
//





///////////////////////////////////

///////////////////////////////////
//                    String ATTRIBUTE111 = doc4.getElementsByClass("param-item-flex").text();
//                    System.out.println(ATTRIBUTE111);
/////////////////////////////////

//                    Elements table = doc4.getElementsByClass("product-properties");
//                    Iterator<Element> ite = table.select("td").iterator();
//                    Elements row2 = table.select("td");
//                    int y2 = 10;
//                    for (Element rows : row2) {
//                        String Har = ite.next().text();
//                        System.out.print(Har);
//                        Cell cell1000 = row.createCell(y2);
//                        cell1000.setCellValue(Har);
//                        y2++;
//                    }

//////////////////////////////////
//
//                    try {
//                        Elements features = doc4.getElementsByClass("param-left-main");
//
//                        int z11f = 0;
//                        int z11ff = 1;
//                        int y311f = 24;
//                        int y411f =25;
//
//                        for (Element featureses : features) {
//
//                            String ATTRIBUTEf = doc4.getElementsByClass("param-item-flex").get(z11f).select("span").text();
//                            String ATTRIBUTE2f =doc4.getElementsByClass("param-item-flex").get(z11ff).select("span").text();
//                            Cell cell5555 = row.createCell(y311f);
//                            Cell cell6666 = row.createCell(y411f);
//                            System.out.println(ATTRIBUTEf + " " +ATTRIBUTE2f);
//                            cell5555.setCellValue(ATTRIBUTEf);
//                            cell6666.setCellValue(ATTRIBUTE2f);
//                            y311f=y311f+2;
//                            y411f=y411f+2;
//                            z11f++;
//                            z11ff++;
//                        }
//
//                    }
//                    catch (java.lang.NullPointerException e){
//                        e.printStackTrace();
//                    }
//



///////////////////////////////////
//                    String MainFoto = doc4.getElementsByClass("product-card-gallery-view__wrap").select("img").attr("src");
//                    System.out.println(MainFoto);
//
//                    String MainFoto2= "https://evroplast.ru/"+ doc4.getElementsByClass("param-section-block").select("img").attr("src");
//                    System.out.println(MainFoto2);
//
//                    String Model = doc4.getElementsByClass("list-unstyled").first().select("li").get(1).text();
//                    System.out.println(Model);
//                    String pictures1 = doc4.getElementsByClass("grid").html();
//                    System.out.println(pictures1);
/////////////////////////////////////////////////////////
                    try {
//                        Elements pictures = doc4.getElementsByClass("product-card-gallery-list-item product-card-gallery-list-item--active").select("img");
                        Elements pictures = doc4.getElementById("related_media_slider").select("a");

                        int z = 0;
                        //                      int y3 = 6;
                        int y3 = 5;
                        for (Element picture : pictures) {
                            System.out.println(pictures.get(z).attr("href"));
//                            System.out.println(pictures);
                            String Foto =  pictures.get(z).attr("href");
                            Cell cell5555 = row.createCell(y3);
                            cell5555.setCellValue(Foto);
                            y3++;
                            z++;
                        }
                    }
                    catch (java.lang.NullPointerException e){
                        e.printStackTrace();
                    }
/////////////////////////////////////////////////////////// обработка
//
//                    try {
//                        Elements pictures = doc4.getElementsByClass("cs-images__img");
//
//                        int z = 0;
//                        //                      int y3 = 6;
//                        int y3 = 37;
//                        for (Element picture : pictures) {
//                            System.out.println(pictures.get(z).attr("src"));
//
//                            String FotoOBR =  pictures.get(z).attr("src");
//                            String RazmerFoto ="_w640_h640_";
//                            int Start=26;
//                            int End =36;
//
//                            String   FotoOBR_result = FotoOBR.substring(Start, End);
//                            String dst ="https://images.ua.prom.st/"+FotoOBR_result+RazmerFoto+ FotoOBR_result;
//
//                            Cell cell5555 = row.createCell(y3);
//                            cell5555.setCellValue(dst);
//                            y3++;
//                            z++;
//                        }
//                    }
//                    catch (java.lang.NullPointerException e){
//                        e.printStackTrace();
//                    }
//                    Cell cell2279rand = row.createCell(0);
//                    cell2279rand.setCellValue(sku);

                    Cell cell2279rand = row.createCell(0);
                    cell2279rand.setCellValue(NameProduct);
//                    Cell cell2279rand = row.createCell(0);
//                    cell2279rand.setCellValue(ID_random);

                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(Manual_category);

//                    Cell cell227 = row.createCell(2);
//                    cell227.setCellValue(NameProduct.substring(0, 1).toUpperCase()+NameProduct.substring(1) );

//                    Cell cellsub1 = row.createCell(2);
//                    cellsub1.setCellValue(SubCategory);
//
//
//                    Cell cell224 = row.createCell(3);
//                    cell224.setCellValue(MainPrice);

                    Cell cell2242 = row.createCell(4);
                    cell2242.setCellValue(Description);

//                    Cell cell2242211 = row.createCell(26);
//                    cell2242211.setCellValue(MainFoto);

//                    Cell cell227AutoCategory = row.createCell(27);
//                    cell227AutoCategory.setCellValue(Description2);

//
//                    Cell cell2279 = row.createCell(0);
//                    cell2279.setCellValue(sku);
//                    Cell cell2279artikul = row.createCell(6);
//                    cell2279artikul.setCellValue(Artikul_text);


//                    Cell cell2279 = row.createCell(7);
//                    cell2279.setCellValue(dataID);



//






//                    Cell cell2242flex = row.createCell(24);
//                    cell2242flex.setCellValue(ATTRIBUTE111);

//                    Cell cell22422 = row.createCell(5);
//                    cell22422.setCellValue(Proizvoditel);
//
//                    Cell cell224221 = row.createCell(6);
//                    cell224221.setCellValue(Pictures);
//

//
//
//                    Cell cell224foto2 = row.createCell(30);
//                    cell224foto2.setCellValue(MainFoto2);

//                    Cell cell224mat = row.createCell(8);
//                    cell224mat.setCellValue(material);

                    try {
                        Elements features = doc4.getElementsByTag("li");
//                        Elements features = doc4.getElementById("evc_specifications_table").select("li");

                        int z11 = 0;
                        int y311 = 28;
                        int y411 =29;

                        for (Element featureses : features) {

                            String ATTRIBUTE = doc4.getElementsByTag("span").select("[class=left]").get(z11).text();
                            String ATTRIBUTE2 =doc4.getElementsByTag("span").select("[class=right]").get(z11).text();
                            Cell cell5555 = row.createCell(y311);
                            Cell cell6666 = row.createCell(y411);
                            System.out.println(ATTRIBUTE + " " +ATTRIBUTE2);
                            cell5555.setCellValue(ATTRIBUTE);
                            cell6666.setCellValue(ATTRIBUTE2);
                            y311=y311+2;
                            y411=y411+2;
                            z11++;
                        }

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
                System.out.println();
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
