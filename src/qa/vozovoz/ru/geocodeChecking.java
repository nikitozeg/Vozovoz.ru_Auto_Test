package ru.vozovoz;

/**
 * Created by n.ivanov on 25.03.2015.
 */

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;


public class geocodeChecking {
    int counter = 0;
    File inputWorkbook = new File("C:\\Users\\adm\\Dropbox\\test.xls");
    Workbook w;

    public void waitLoad() throws InterruptedException {
        // Thread.sleep(4000);
        //selenium.click("xpath=(//*[@test-id='order.calc'])"); //new
        //  Thread.sleep(1000);
        for (int second = 0; ; second++) {
            if (second >= 8) {
                // selenium.click("xpath=(//*[@test-id='order.calc'])");
                // Thread.sleep(1000);
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Не прогрузилось слово Перевозка " + System.currentTimeMillis() + ".png", "");
                //fail("timeout");
                break;
            }
            try {
                if //(Pattern.compile("(Перевозка)").matcher(selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div")).find())
                        (Pattern.compile("[П]").matcher(selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div")).find())
                    break;
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\не найден локатор ПЕРЕВОЗКИ " + System.currentTimeMillis() + ".png", "");
                break;
            }
            Thread.sleep(1000);
        }
    }
    public void login() throws InterruptedException {

        for (int second = 0; ; second++) {
            if (second >= 10) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\login FAIL " + System.currentTimeMillis() + ".png", "");
                break;
            }
            try {
                if (Pattern.compile("Войти").matcher(selenium.getText("css=button.btn.btn-default")).find()) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        selenium.click("name=login");
        selenium.type("name=login", "9910012655");

        selenium.click("name=password");
        selenium.type("name=password", "72621010a");

        selenium.click("css=button.btn.btn-default");

        Thread.sleep(3000);


        for (int second = 0; ; second++) {
            if (second >= 11) { selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\ loaded ORDERS notFOUND " + System.currentTimeMillis() + ".png", ""); break;}
            try {
                if ("500010118".equals(selenium.getText("//div[@id='main']/div/div/div/div[2]/div/div/div/div/div/div/div/app-orders-orders/div/app-orders-table/div/div/div/div[2]/div/div/div/div"))) break;
                //else selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\ORDER not Asserted " + System.currentTimeMillis() + ".png", ""); break;
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\ORDER locator notFOUND " + System.currentTimeMillis() + ".png", ""); break;
            }
            Thread.sleep(1000);
        }

    }

    public int getCostSetParamsURL(int row) throws IOException {


        int cost = 0;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);
            //   for (int j = 4; j < sheet.getColumns(); j++) {
            //  for (int i = 0; i < sheet.getRows(); i++) {

            Cell cell = sheet.getCell(1, row);
            String from = cell.getContents().toString();
            //  System.out.println(from);

            cell = sheet.getCell(2, row);
            String to = cell.getContents().toString();
            //  System.out.println(to);

            cell = sheet.getCell(27, row);
            cost = Integer.parseInt(cell.getContents());
            //  System.out.println(cost);


            cell = sheet.getCell(0, row);

            if (!(cell.getContents().toString().equals("1"))) {
                //   System.out.println("NUMBER 0 ne raven 1" + cell.getContents().toString());
                selenium.click("xpath=(//*[@test-id='unit.remove'])");
                selenium.click("xpath=(//*[@test-id='correspondence.add'])");
            }

            for (int column = 3; column < 25; column++) {

                cell = sheet.getCell(column, row);
                if (!(cell.getContents() == "")) {
                    // System.out.print(" " + cell.getContents().toString().replaceAll(",", "."));
                    switch (column) {

                        case 3:
                            selenium.click("xpath=(//*[@test-id='from.use.address'])");
                            //        System.out.println("click on FROM USE ADDRESS");
                            Thread.sleep(2000);
                            break;
                        case 4:
                            selenium.click("xpath=(//*[@test-id='to.use.address'])");
                            break;
                        case 5:
                            selenium.type("xpath=(//*[@test-id='unit.length'])[1]", cell.getContents().toString().replaceAll(",", "."));
                            break;
                        case 6:
                            selenium.type("xpath=(//*[@test-id='unit.width'])[1]", cell.getContents().toString().replaceAll(",", "."));
                            break;
                        case 7:
                            selenium.type("xpath=(//*[@test-id='unit.height'])[1]", cell.getContents().toString().replaceAll(",", "."));
                            break;
                        case 8:
                            selenium.type("xpath=(//*[@test-id='unit.weight'])[1]", cell.getContents().toString().replaceAll(",", "."));
                            break;
                        case 9:
                            selenium.type("xpath=(//*[@test-id='unit.amount'])[1]", cell.getContents().toString().replaceAll(",", "."));
                            break;

                        case 12:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.extraPackage'])");
                            break;
                        case 13:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.bubbleFilm'])");
                            break;
                        case 14:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.hardPackage'])");
                            break;
                        case 15:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.box1'])");
                            break;
                        case 16:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.box2'])");
                            break;
                        case 17:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.box3'])");
                            break;
                        case 18:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.box4'])");
                            break;
                        case 19:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.bag1'])");
                            break;
                        case 20:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.bag2'])");
                            break;
                        case 21:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.safePackage'])");
                            break;
                        case 22:
                            selenium.click("xpath=(//*[@test-id='packages.add'])");
                            selenium.click("xpath=(//*[@test-id='packages.sealPackage'])");
                            break;
                        case 23:
                            selenium.click("xpath=(//*[@test-id='from.work.show'])");
                            selenium.click("xpath=(//*[@test-id='from.floor'])");
                            selenium.type("xpath=(//*[@test-id='from.floor.count'])", cell.getContents().toString());
                            break;
                        case 24:
                            selenium.click("xpath=(//*[@test-id='to.work.show'])");
                            selenium.click("xpath=(//*[@test-id='to.floor'])");
                            selenium.type("xpath=(//*[@test-id='to.floor.count'])", cell.getContents().toString());
                            break;

                    }

                }


            }
            Thread.sleep(2000);
        } catch (BiffException e) {
            e.printStackTrace();
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\BiffException" + " " + System.currentTimeMillis() + ".png", "");
        } catch (InterruptedException e) {
            e.printStackTrace();
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\InterruptedException" + " " + System.currentTimeMillis() + ".png", "");
        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\SeleniumException on row " + row + " " + System.currentTimeMillis() + ".png", "");
        }
        return cost;
    }

    public void promoCheck(String url, int cost) throws InterruptedException {
        selenium.open(url);
        Thread.sleep(10000);
        try {
            if (Pattern.compile("[Акция]").matcher(selenium.getText("css=div.order-panel-summary-discount")).find())
            { System.out.println("akcia is OK ");
            } else
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\akcia ne poyavilas " + System.currentTimeMillis() + ".png", "");

            //cost check
            try {
                if (cost == Integer.parseInt(selenium.getText("css=div.order-panel-total-cost.ng-binding"))) {
                } else
                    selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Правильная цена по акции " + cost + " " + System.currentTimeMillis() + ".png", "");
            } catch (Exception e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\not found order.calc произошло в  " + System.currentTimeMillis() + ".png", "");
            }

        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\zabor za 200 FAIL 2   " + System.currentTimeMillis() + ".png", "");
        }

    }

    private Selenium selenium;

    public void browserReload() throws Exception {
        selenium.close();
        selenium.stop();
        Thread.sleep(10000);
        setUp();
    }

    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://vozovoz.ru/");
        selenium.start();
    }

    @Test
    public void testVar9SpbMsk() throws Exception {

        // Thread.sleep(000);
        //   selenium.open("/calculate-the-order");

        while (true) {
            try {
                selenium.open("https://geocode-maps.yandex.ru/1.x/?callback=angular.callbacks._7&format=json&geocode=г+Санкт-Петербург,+ул+Большая+Зеленина,+д+12&sco=latlong");
                Thread.sleep(9000);
                if (selenium.isTextPresent("30.291964 59.960976")) System.out.println("geo found");
                else selenium.captureEntirePageScreenshot("C:\\errorlogGeo\\geo not found " + System.currentTimeMillis() + ".png", "");

                selenium.open("https://geocode-maps.yandex.ru/1.x/?callback=angular.callbacks._7&format=json&geocode=г. Ставрополь, Кулакова пр., д 33");
                Thread.sleep(9000);
                if (selenium.isTextPresent("41.924554 45.063623")) System.out.println("geo found");
                else selenium.captureEntirePageScreenshot("C:\\errorlogGeo\\geo not found " + System.currentTimeMillis() + ".png", "");

                selenium.open("https://geocode-maps.yandex.ru/1.x/?callback=angular.callbacks._7&format=json&geocode=г. Воронеж, Плехановская ул., д 2");
                Thread.sleep(9000);
                if (selenium.isTextPresent("39.205318 51.657652")) System.out.println("geo found");
                else selenium.captureEntirePageScreenshot("C:\\errorlogGeo\\geo not found " + System.currentTimeMillis() + ".png", "");

                selenium.open("https://geocode-maps.yandex.ru/1.x/?callback=angular.callbacks._7&format=json&geocode=г. Ростов-на-Дону, Большая Садовая ул., д 73");
                Thread.sleep(9000);
                if (selenium.isTextPresent("39.719855 47.222984")) System.out.println("geo found");
                else selenium.captureEntirePageScreenshot("C:\\errorlogGeo\\geo not found " + System.currentTimeMillis() + ".png", "");

                selenium.open("https://geocode-maps.yandex.ru/1.x/?callback=angular.callbacks._7&format=json&geocode=г. Самара, Московское ш., д 4");
                Thread.sleep(9000);
                if (selenium.isTextPresent("50.259806 53.272392")) System.out.println("geo found");
                else selenium.captureEntirePageScreenshot("C:\\errorlogGeo\\geo not found " + System.currentTimeMillis() + ".png", "");


            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Some test is FAILed " + System.currentTimeMillis() + ".png", "");
                System.out.println(e.getMessage());
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
