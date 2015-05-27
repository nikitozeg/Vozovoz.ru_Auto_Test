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


public class devTest {
    int counter = 0;
    File inputWorkbook = new File("C:\\Users\\adm\\Dropbox\\test.xls");
    Workbook w;
    boolean nextIsUrLico=false;
    public void waitLoad() throws InterruptedException {
        Thread.sleep(8000);
        if (selenium.isTextPresent("Перевозка"));
        else   selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Не прогрузился калькулятор за 8 сек " + System.currentTimeMillis() + ".png", "");
        Thread.sleep(1000);
    }

    public void login() throws InterruptedException {
        selenium.click("link=Личный кабинет");
        Thread.sleep(2000);
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
        selenium.type("name=password", "2a320d2f");

        selenium.click("css=button.btn.btn-default");

        Thread.sleep(5000);


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
        selenium.click("xpath=(//*[@test-id='order.calc'])");
        Thread.sleep(3000);
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

    public void assertCheckout(int cost) throws InterruptedException {
        // Thread.sleep(500);
        selenium.click("xpath=(//*[@test-id='order.calc'])");
        Thread.sleep(3000);
        for (int second = 0; ; second++) {
            if (second == 3) {
                selenium.click("xpath=(//*[@test-id='order.calc'])");
                Thread.sleep(3000);
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Правильная цена " + cost + " " + System.currentTimeMillis() + ".png", "");
                selenium.click("css=div.header-logo.header-logo_img");
                break;
            }
            try {
                if (cost == Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) {
                    setContacts();
                    Thread.sleep(1000);//new
                    checkOut();
                    break;
                }
            } catch (Exception e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\not found order.calc произошло в  " + System.currentTimeMillis() + ".png", "");
                System.out.print(e.toString());
            }
            Thread.sleep(3000);
        }
    }

    public int getCostSetParams(int row) throws IOException {


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

            setCities(from, to);


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
        }
        catch (SeleniumException e){
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\SeleniumException on row " + row +" "+ System.currentTimeMillis() + ".png", "");
        }
        return cost;
    }
    public void checkOut() throws InterruptedException {
        try {
            selenium.click("link=Личный кабинет");
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\UNLOGIN detected " + " " + System.currentTimeMillis() + ".png", "");
            login();
            selenium.click("link=Оформление заказа");
            waitLoad();
            setContacts();
        } catch (SeleniumException e) {

        }
        // ОФОРМЛЕНИЕ
        try {Thread.sleep(1000);
            //  selenium.click("xpath=(//*[@test-id='order.create'])");
            selenium.click("//div[@id='main']/ng-form/div/div[2]/div/div[3]/div/button");
            Thread.sleep(2000);
        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\click on orderCreate is fail " + " " + System.currentTimeMillis() + ".png", "");
            Thread.sleep(2000);
            try {selenium.click("//div[@id='main']/ng-form/div/div[2]/div/div[3]/div/button");}
            catch (SeleniumException ee) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\REPEAT click on orderCreate is fail " + " " + System.currentTimeMillis() + ".png", "");
                Thread.sleep(2000);
            }
        }

        Thread.sleep(2000);

        try { selenium.click("//button[@type='button']");}
        catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\click on button is fail " + " " + System.currentTimeMillis() + ".png", "");
            Thread.sleep(2000);
            try {selenium.click("//button[@type='button']");}
            catch (SeleniumException ee) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\REPEAT click on button is fail " + " " + System.currentTimeMillis() + ".png", "");
                Thread.sleep(2000);
            }
        }

        Thread.sleep(1000);
        for (int second = 0; ; second++) {
            if (second >= 5) { selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\OrderNumber is fail " + " " + System.currentTimeMillis() + ".png", ""); break;}
            try {
                if ("000008116".equals(selenium.getText("css=span.selection.ng-binding"))){Thread.sleep(1000); break;}
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\ng-binding not found " + " " + System.currentTimeMillis() + ".png", "");break;
            }
            Thread.sleep(1000);
        }


        /*finally {
           // Thread.sleep(30000);
            try {
                selenium.click("css=div.header-logo.header-logo_img");
                Thread.sleep(2000);
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\click on orderBACKWARD is fail " + " " + System.currentTimeMillis() + ".png", "");

            }
        }*/
    }

    public void setContacts() throws InterruptedException {
        try {
            if (nextIsUrLico == false) {
                selenium.click("//div[@id='main']/ng-form/div/div[2]/div/div[3]/div/button");
                Thread.sleep(1000);
                selenium.type("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div[2]/ng-form/div/div/app-calculator-side-profile/div/div[2]/div/app-calculator-counteragents/div/oi-multiselect/div/ul/li/input", "иванов никита");
                selenium.type("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div[2]/ng-form/div/div[2]/app-calculator-side-profile/div/div[2]/div/app-calculator-counteragents/div/oi-multiselect/div/ul/li/input", "иванов андрей");
                selenium.type("name=extraPhoneNumberFz", "+7 (951) 685-32-60");
                selenium.type("css=div.col-xs-6.consignee > app-calculator-side-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"extraPhoneNumberFz\"]", "+7 (991) 001-26-55");
                nextIsUrLico = true;
            } else {
                selenium.click("//div[@id='main']/ng-form/div/div[2]/div/div[3]/div/button");
                Thread.sleep(1000);
                selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div[2]/ng-form/div/div/app-calculator-side-profile/div/div/div/label[2]");
                selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div[2]/ng-form/div/div[2]/app-calculator-side-profile/div/div/div/label[2]");
                selenium.click("css=oi-multiselect[name=\"shipperUrNameSelect\"] > div.multiselect-search > ul.multiselect-search-list");
                selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div[2]/ng-form/div/div/app-calculator-side-profile/div/div[2]/div/app-calculator-counteragents/div/oi-multiselect/div[2]/ul/li[2]");
                selenium.type("name=shipperUrAddress", "a. , #№;v`:'/-");
                selenium.type("name=shipperUrFIO", "a. , #№;v`:'/-");
                selenium.type("name=extraPhoneNumberUr", "+7 (951) 685-32-60");
                selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div[2]/ng-form/div/div[2]/app-calculator-side-profile/div/div[2]/div/app-calculator-counteragents/div/oi-multiselect/div/ul");
                selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div[2]/ng-form/div/div[2]/app-calculator-side-profile/div/div[2]/div/app-calculator-counteragents/div/oi-multiselect/div[2]/ul/li[3]");
                selenium.type("css=div.col-xs-6.consignee > app-calculator-side-profile.ng-isolate-scope > div.calculator-profile > div.urFields.ng-scope > div.form-group > input[name=\"shipperUrAddress\"]", "a. , #№;v`:'/-");
                selenium.type("css=div.col-xs-6.consignee > app-calculator-side-profile.ng-isolate-scope > div.calculator-profile > div.urFields.ng-scope > div.form-group > input[name=\"shipperUrFIO\"]", "a. , #№;v`:'/-");
                selenium.type("css=div.col-xs-6.consignee > app-calculator-side-profile.ng-isolate-scope > div.calculator-profile > div.urFields.ng-scope > div.form-group > input[name=\"extraPhoneNumberUr\"]", "+7 (951) 685-32-60");
                nextIsUrLico = false;
            }
        }catch (Exception e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\setcontacts fail  " + System.currentTimeMillis() + ".png", "");

        }

    }

    public void setCities(String sender, String receiver) throws InterruptedException {
        Thread.sleep(2000);
        try {
            //  selenium.click("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div/div/div/oi-multiselect/div/ul/li");
            // selenium.type("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div/div/div/oi-multiselect/div/ul/li/input", "Самара");
            //selenium.click("css=strong");
            //selenium.click("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div[2]/div/div/oi-multiselect/div/ul/li");
            //selenium.type("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div[2]/div/div/oi-multiselect/div/ul/li/input", "Ставрополь");
            //selenium.click("css=strong");

            selenium.click("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div/div/div/oi-multiselect/div/ul/li");
            Thread.sleep(1000);
            selenium.type("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div/div/div/oi-multiselect/div/ul/li/input", sender);
            Thread.sleep(1000);
            selenium.click("css=strong");
            Thread.sleep(1000);
            selenium.click("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div[2]/div/div/oi-multiselect/div/ul/li");
            Thread.sleep(1000);
            selenium.type("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div[2]/div/div/oi-multiselect/div/ul/li/input", receiver);
            Thread.sleep(1000);
            selenium.click("css=strong");
            Thread.sleep(1000);
            //selenium.click("xpath=(//*[@test-id='minicalculator.order.create'])");
            selenium.click("css=div.homepage-calculator-calcButton > button.btn");
            waitLoad();
        }
        catch (SeleniumException e){
            e.printStackTrace();
            selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Select Cities is failed" + " " + System.currentTimeMillis() + ".png", "");
            throw new SeleniumException(e);
        }
        // selenium.click("xpath=(//*[@test-id='to.use.address'])");
        // selenium.click("xpath=(//*[@test-id='from.use.address'])");


    }

    private Selenium selenium;

    public void browserReload() throws Exception {
        selenium.close();
        selenium.stop();
        Thread.sleep(4000);
        setUp();
    }

    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://qa.vozovoz.ru/");
        selenium.start();

    }

    @Test
    public void testVar9SpbMsk() throws Exception {

        // Thread.sleep(000);
        //   selenium.open("/calculate-the-order");
        while (true) {
            try {
                selenium.open("/");
                Thread.sleep(2000);

//                /*      Thread.sleep(2000);
//                           try {
//                                selenium.click("link=Личный кабинет");
//                                login();
//                            } catch (SeleniumException e) {
//                            }
//
//
//                            selenium.click("css=i.vzi-arrows-cw-outline");
//                            selenium.
//                            selenium.selectWindow("15555");
//                            Thread.sleep(8000);
//                            waitLoad();
//                            try {
//                                if ("690".equals((selenium.getText("css=div.order-panel-total-cost.ng-binding")))) {
//                                    System.out.println("LK repeat ok ");
//                                } else
//                                    selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\цена изменилась nom1256 " + System.currentTimeMillis() + ".png", "");
//
//                            } catch (SeleniumException e) {
//                                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Ссылка на повтор фейл  " + System.currentTimeMillis() + ".png", "");
//                                System.out.print(e.toString());
//                            }
//                            System.out.print("repeat order cost check is done ");
//            */

                //Авторизация




                        try {
                            selenium.click("link=Личный кабинет");
                            login();
                            selenium.click("css=div.header-logo.header-logo_img");
                            Thread.sleep(1000);
                        } catch (SeleniumException e) {
                        }


                //Проверка оформления

                //CHECK 0.1*0.1*0.1
    /*            try {
                    assertCheckout(getCostSetParams(572));
                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\01 01 01is fail  " + System.currentTimeMillis() + ".png", "");
                    selenium.click("css=div.header-logo.header-logo_img");
                } finally {
                    browserReload();
                    Thread.sleep(3000);
                    try {
                        selenium.open("/user/login");
                        login();
                        selenium.click("css=div.header-logo.header-logo_img");
                        Thread.sleep(1000);
                    } catch (SeleniumException e) {
                    }
                }
                System.out.print("0.1  is done ");
*/
                //Регресс бага по нерабочей кнопке оформить
         /*       try {
                    selenium.open("/calculate-the-order");
                    Thread.sleep(3000);
                    waitLoad();
                    selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div/app-select-address/oi-multiselect/div/ul/li");
                    selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div[2]/app-calculator-side-to/form/app-calculator-side-location/div/ng-form/div[2]/div/app-select-address/oi-multiselect/div/ul/li");
                    Thread.sleep(1000);
                    selenium.click("xpath=(//*[@test-id='from.use.ppv'])");
                    selenium.click("xpath=(//*[@test-id='to.use.ppv'])");
                    Thread.sleep(3000);
                    assertCheckout(240);
                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Регресс бага FAIL " + System.currentTimeMillis() + ".png", "");
                    System.out.print(e.toString());
                } finally {
                    browserReload();
                    Thread.sleep(3000);
                    try {
                        selenium.open("/user/login");
                        login();
                        selenium.click("css=div.header-logo.header-logo_img");
                        Thread.sleep(1000);
                    } catch (SeleniumException e) {
                    }
                }
                System.out.print("regress  is done ");
*/
                //Проверка авторизации

              //  login();
                //Проверка отслеживания груза

                selenium.click("link=Отслеживание груза");
                selenium.type("//div[@id='body']/app-header/div/div/div[4]/div/app-current-user/div/app-find-order/div/form/div/input", "500010397");
                selenium.click("css=button.btn.btn-sm");
                Thread.sleep(5000);
                if (selenium.isTextPresent("16 марта 2015 г.")) System.out.println("16 marta found");
                else selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\TrackCargoDate not found " + System.currentTimeMillis() + ".png", "");
                selenium.click("link=Дополнительная информация");
                selenium.type("//input[@type='tel']", "9646410678");
                selenium.click("css=button.btn.btn-default");
                Thread.sleep(5000);
                if (selenium.isTextPresent("Получение по адресу: г Москва, ул Люблинская, д 42")) System.out.println("TrackCargoStatus found");
                else selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\TrackCargoAddress not found " + System.currentTimeMillis() + ".png", "");
                Thread.sleep(3000);

                //Проверка появления терминалов
                selenium.open("/calculate-the-order");
                waitLoad();
                selenium.click("xpath=(//*[@test-id='from.use.ppv'])");
                Thread.sleep(2000);
                if ("Кубинская ул. 80, м. Ленинский пр.".equals((selenium.getText("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div[2]/oi-multiselect/div/ul/li")))) {
                    System.out.println("terminal1 is ok ");
                }
                else  selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\terminal1 check check is fail  " + System.currentTimeMillis() + ".png", "");

                selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div/oi-multiselect/div/ul/li");
                selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div/oi-multiselect/div[2]/ul/li");

                Thread.sleep(2000);
                if ("Востряковский проезд 10".equals((selenium.getText("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div[2]/oi-multiselect/div/ul/li")))) {
                    System.out.println("terminal2 is ok ");
                }
                else  selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\terminal2 check check is fail  " + System.currentTimeMillis() + ".png", "");

                selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div/oi-multiselect/div/ul/li");
                selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div/oi-multiselect/div[2]/ul/li[5]");

                Thread.sleep(2000);
                if ("Стартовая ул.3/11".equals((selenium.getText("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div[2]/oi-multiselect/div/ul/li")))) {
                    System.out.println("terminal2 is ok ");
                }
                else  selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\terminal2 check check is fail  " + System.currentTimeMillis() + ".png", "");
                //Адреса терминалов
                selenium.click("link=Адреса терминалов");
                Thread.sleep(10000);
                selenium.click("xpath=(//a[contains(text(),'Латышских стрелков ул. 23')])[2]");
                Thread.sleep(4000);
                if ("Санкт-Петербург, ул. Латышских Стрелков, д. 23, лит. О".equals((selenium.getText("css=p.list-group-item-text.ng-binding")))) {
                    System.out.println("ADRESA is ok ");
                } else
                    selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\adresa check is fail  " + System.currentTimeMillis() + ".png", "");

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
                    System.out.println("gettedcost AFTER url= " + (selenium.getText("css=div.order-panel-total-cost.ng-binding")));
                    if (savedCost.equals((selenium.getText("css=div.order-panel-total-cost.ng-binding")))) {
                        System.out.println("URL OK ");
                    } else
                        selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\цена изменилась  " + System.currentTimeMillis() + ".png", "");

                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Ссылка на расчет FAIL  " + System.currentTimeMillis() + ".png", "");
                    System.out.print(e.toString());
                }
                System.out.println("SAVED URL done ");
                //Проверка акции забор за 200 рублей
                try {
                    promoCheck("/calculate-the-order?calculationId=555dd56fe5b6a41bc7e97c0f", 850); //спб мск
                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\promocheck fail  " + System.currentTimeMillis() + ".png", "");
                    System.out.print(e.toString());
                }


                //Проверка акций
                try {
                    selenium.click("link=Акции");

                    Thread.sleep(2000);

                    if (selenium.isTextPresent("Забор груза от клиента – за 200 рублей")) System.out.println("akFOUND!!!!!");
                    else selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\akcii not found " + System.currentTimeMillis() + ".png", "");

                 /*   selenium.open("/promotions/5");
                    Thread.sleep(2000);
                    if (selenium.isTextPresent("территориальных границ")) System.out.println("akFOUND!!!!!");
                    else selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\akcii2 not found " + System.currentTimeMillis() + ".png", "");*/


                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\go to promo failed " + System.currentTimeMillis() + ".png", "");

                }
                //Проверка novostei
                try {
                    selenium.click("link=Новости");
                    Thread.sleep(3000);

                    if (selenium.isTextPresent("Открытие нового терминала")) System.out.println("FOUND!!!!!");
                    else selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\novosti not found " + System.currentTimeMillis() + ".png", "");

                 /*   selenium.open("/news/51");
                    Thread.sleep(2000);
                    if (selenium.isTextPresent("Скидка предоставляется")) System.out.println("FOUND!!!!!");
                    else selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\novosti2 not found " + System.currentTimeMillis() + ".png", "");*/

                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\go to novosti failed " + System.currentTimeMillis() + ".png", "");

                }

                System.out.println("Cycled is over");
                browserReload();
                Thread.sleep(3000);

            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogFunc\\Some test SITECHECKER FAILed " + System.currentTimeMillis() + ".png", "");
                System.out.println(e.getMessage());
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
