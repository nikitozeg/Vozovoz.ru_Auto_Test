package qa.vozovoz.ru;
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


public class AllSiteChecker {
    int counter = 0;
    File inputWorkbook = new File("C:\\Users\\n.ivanov\\Dropbox\\test.xls");
    Workbook w;

    public void login() throws InterruptedException {

        for (int second = 0; ; second++) {
            if (second >= 10) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\login FAIL " + System.currentTimeMillis() + ".png", "");
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
            if (second >= 6) { selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\ loaded ORDERS notFOUND " + System.currentTimeMillis() + ".png", ""); break;}
            try {
                if ("500010118".equals(selenium.getText("//div[@id='main']/div/div/div/div[2]/div/div/div/div/div/div/div/app-orders-orders/div/app-orders-table/div/div/div/div[2]/div/div/div/div"))) break;
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\ORDER locator notFOUND " + System.currentTimeMillis() + ".png", ""); break;
            }
            Thread.sleep(1000);
        }
        selenium.click("css=div.header-logo.header-logo_img");
        Thread.sleep(1000);

    }
    public void waitLoad() throws InterruptedException {
        // Thread.sleep(4000);
        //selenium.click("xpath=(//*[@test-id='order.calc'])"); //new
        //  Thread.sleep(1000);
        for (int second = 0; ; second++) {
            if (second >= 8) {
                // selenium.click("xpath=(//*[@test-id='order.calc'])");
                // Thread.sleep(1000);
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Не прогрузилось слово Перевозка " + System.currentTimeMillis() + ".png", "");
                //fail("timeout");
                break;
            }
            try {
                if //(Pattern.compile("(Перевозка)").matcher(selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div")).find())
                        (Pattern.compile("[П]").matcher(selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div")).find())
                    break;
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\не найден локатор ПЕРЕВОЗКИ " + System.currentTimeMillis() + ".png", ""); break;
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
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\BiffException" + " " + System.currentTimeMillis() + ".png", "");
        } catch (InterruptedException e) {
            e.printStackTrace();
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\InterruptedException" + " " + System.currentTimeMillis() + ".png", "");
        }
        catch (SeleniumException e){
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\SeleniumException on row " + row +" "+ System.currentTimeMillis() + ".png", "");
        }
        return cost;
    }
    public void setContacts() throws InterruptedException {
        selenium.type("name=shipperFizFIO", "иванов никита");
        selenium.type("xpath=(//input[@name='shipperFizFIO'])[2]", "иванов андрей");
        selenium.type("name=extraPhoneNumberFz", "+7 (951) 685-32-60");
        selenium.type("css=div.col-xs-6.consignee > app-calculator-side-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"extraPhoneNumberFz\"]", "+7 (991) 001-26-55");

    }

    public void promoCheck(String url, int cost) throws InterruptedException {
        selenium.open(url);
        Thread.sleep(10000);
        try {
            if ("Забор груза от клиента: 200,00".equals((selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div[2]")))) {
                System.out.println("zabor za 200 is OK ");
            }
            else  selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\zabor za 200 fail 1  " + System.currentTimeMillis() + ".png", "");

            //cost check
            try {
                if (cost == Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) {    System.out.println("cost is OK ");       }
                else  selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Правильная цена " + cost + " " + System.currentTimeMillis() + ".png", "");
            } catch (Exception e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\not found order.calc произошло в  " + System.currentTimeMillis() + ".png", "");
            }

        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\zabor za 200 FAIL 2   " + System.currentTimeMillis() + ".png", "");
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
                selenium.open("/");

                //Адреса терминалов
       /*         selenium.click("link=Адреса терминалов");
                Thread.sleep(10000);
                selenium.click("xpath=(//a[contains(text(),'Латышских стрелков ул. 23')])[2]");
                Thread.sleep(4000);
                if ("Санкт-Петербург, ул. Латышских Стрелков, д. 23, лит. О".equals((selenium.getText("css=p.list-group-item-text.ng-binding")))) {
                    System.out.println("ADRESA is ok ");
                }
                else  selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\adresa check is fail  " + System.currentTimeMillis() + ".png", "");

                selenium.open("/calculate-the-order");
                //ССЫЛКА НА РАСЧЕТ
                int rr = 155;
                Thread.sleep(3000);
                waitLoad();
                getCostSetParamsURL(rr);
                rr++;
                // Thread.sleep(1000);
                   selenium.click("xpath=(//*[@test-id='order-get-link'])");
                   Thread.sleep(5000);
                   String savedCost = selenium.getText("css=div.order-panel-total-cost.ng-binding");
                   String url = selenium.getValue("xpath=(//*[@test-id='order-link'])");
                   selenium.open(url);
                   Thread.sleep(10000);

                try {
                       System.out.println("gettedcost AFTER url= "+(selenium.getText("css=div.order-panel-total-cost.ng-binding")));
                       if (savedCost.equals((selenium.getText("css=div.order-panel-total-cost.ng-binding")))) {
                           System.out.println("URL OK ");
                        }
                    else  selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\цена изменилась  " + System.currentTimeMillis() + ".png", "");

                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Ссылка на расчет FAIL  " + System.currentTimeMillis() + ".png", "");
                    System.out.print(e.toString());
                }
                System.out.println("SAVED URL done ");

                //Проверка акции забор за 200 рублей
                promoCheck("/calculate-the-order?calculationId=554a18e0a41984e4fa3de269", 690);
                 selenium.open("/calculate-the-order?calculationId=554a2164fac74ec6fa19515b");
                Thread.sleep(10000);
                try {
                    if ("Акция: -50,00".equals((selenium.getText("css=div.order-panel-summary-discount")))) {
                        System.out.println("Akcia minus 50 is OK ");
                    }
                    else  selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\zabor za 200 fail 1  " + System.currentTimeMillis() + ".png", "");

                    //cost check
                    try {
                        if (690 == Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) {    System.out.println("cost is OK ");       }
                        else  selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Правильная цена " + 690 + " " + System.currentTimeMillis() + ".png", "");
                    } catch (Exception e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\not found order.calc произошло в  " + System.currentTimeMillis() + ".png", "");
                    }

                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\zabor za 200 FAIL 2   " + System.currentTimeMillis() + ".png", "");
                }
                promoCheck("/calculate-the-order?calculationId=554a21c7fac74ec6fa19515d", 640);
                promoCheck("/calculate-the-order?calculationId=554a21e1fac74ec6fa19515e", 630);
                promoCheck("/calculate-the-order?calculationId=554a21f7fac74ec6fa19515f", 640);
                promoCheck("/calculate-the-order?calculationId=554a2208a130d5d5fa341fe1", 600);
                promoCheck("/calculate-the-order?calculationId=554a221dc3a5dbf3fa4142a9", 620);
                promoCheck("/calculate-the-order?calculationId=554a222ec3a5dbf3fa4142aa", 630);
                promoCheck("/calculate-the-order?calculationId=554a223fa41984e4fa3de29b", 650);
*/
                //Проверка акций
                try {
                    selenium.click("link=Акции");
                    Thread.sleep(3000);

                    for (int second = 0; ; second++) {
                        if (second >= 4) { selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\promo FAILED  " + System.currentTimeMillis() + ".png", ""); break;}
                        try {
                            if ("Акции".equals(selenium.getText("css=h1"))) break;
                        } catch (SeleniumException e) {
                            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\akcii gettext FAIL " + System.currentTimeMillis() + ".png", ""); break;
                        }
                        Thread.sleep(1000);
                    }

                    Thread.sleep(2000);

                    selenium.click("//div[@id='main']/div/div/article[3]/div[2]/a");
                    Thread.sleep(2000);

                    for (int second = 0; ; second++) {
                        if (second >= 3) { selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\promo FAILED  " + System.currentTimeMillis() + ".png", ""); break;}
                        try {
                            if  (Pattern.compile("[VOZOVOZ]").matcher(selenium.getText("css=p")).find())   break;
                        } catch (SeleniumException e) {
                            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\akcii gettext FAIL " + System.currentTimeMillis() + ".png", ""); break;
                        }
                        Thread.sleep(1000);
                    }

                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\go to promo failed " + System.currentTimeMillis() + ".png", "");

                }

                System.out.print("AKcii check is done ");
                System.out.println("Cycled is over");
                browserReload();
                Thread.sleep(3000);

            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Some test SITECHECKER FAILed " + System.currentTimeMillis() + ".png", "");
                System.out.println(e.getMessage());
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
