package qa.vozovoz.ru.srk;
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


public class ExcelCalculatorTest {

    int counter = 0;
    boolean nextIsUrLico=false;
    int browser=0;
    File inputWorkbook = new File("C:\\Users\\adm\\Dropbox\\test.xls");
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

        Thread.sleep(4000);

        if (selenium.isTextPresent("500010118"));
        else selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Order number is notFOUND " + System.currentTimeMillis() + ".png", "");

     /*   for (int second = 0; ; second++) {
            if (second >= 11) { selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\ loaded ORDERS notFOUND " + System.currentTimeMillis() + ".png", ""); break;}
            try {
                if ("500010118".equals(selenium.getText("//div[@id='main']/div/div/div/div[2]/div/div/div/div/div/div/div/app-orders-orders/div/app-orders-table/div/div/div/div[2]/div/div/div/div"))) break;
              //  else selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\ORDER not Asserted " + System.currentTimeMillis() + ".png", ""); break;
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\ORDER locator notFOUND " + System.currentTimeMillis() + ".png", ""); break;
            }
             Thread.sleep(1000);
        }*/

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

    public void checkOut() throws InterruptedException {
        try {
            selenium.click("link=Личный кабинет");
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\UNLOGIN detected " + " " + System.currentTimeMillis() + ".png", "");
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
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\click on orderCreate is fail " + " " + System.currentTimeMillis() + ".png", "");
            Thread.sleep(2000);
            try {selenium.click("//div[@id='main']/ng-form/div/div[2]/div/div[3]/div/button");}
            catch (SeleniumException ee) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\REPEAT click on orderCreate is fail " + " " + System.currentTimeMillis() + ".png", "");
                Thread.sleep(2000);
            }
        }

        Thread.sleep(2000);

        try { selenium.click("//button[@type='button']");}
        catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\click on button is fail " + " " + System.currentTimeMillis() + ".png", "");
            Thread.sleep(2000);
            try {selenium.click("//button[@type='button']");}
            catch (SeleniumException ee) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\REPEAT click on button is fail " + " " + System.currentTimeMillis() + ".png", "");
                Thread.sleep(2000);
            }
        }

        Thread.sleep(1000);
        for (int second = 0; ; second++) {
            if (second >= 5) { selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\OrderNumber is fail " + " " + System.currentTimeMillis() + ".png", ""); break;}
            try {
                if ("000008116".equals(selenium.getText("css=span.selection.ng-binding"))){Thread.sleep(1000); break;}
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\ng-binding not found " + " " + System.currentTimeMillis() + ".png", "");break;
            }
            Thread.sleep(1000);
        }


        /*finally {
           // Thread.sleep(30000);
            try {
                selenium.click("css=div.header-logo.header-logo_img");
                Thread.sleep(2000);
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\click on orderBACKWARD is fail " + " " + System.currentTimeMillis() + ".png", "");

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
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\setcontacts fail  " + System.currentTimeMillis() + ".png", "");

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
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Правильная цена " + cost + " " + System.currentTimeMillis() + ".png", "");
                //selenium.click("css=div.header-logo.header-logo_img");
                setContacts();
                Thread.sleep(1000);//new
                checkOut();
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
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\not found order.calc произошло в  " + System.currentTimeMillis() + ".png", "");
                System.out.print(e.toString());
            }
            Thread.sleep(3000);
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
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Select Cities is failed" + " " + System.currentTimeMillis() + ".png", "");
            throw new SeleniumException(e);
        }
        // selenium.click("xpath=(//*[@test-id='to.use.address'])");
        // selenium.click("xpath=(//*[@test-id='from.use.address'])");


    }

    private Selenium selenium;

 /*   public int getCostSetParams(int row) throws IOException {


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
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\BiffException" + " " + System.currentTimeMillis() + ".png", "");
        } catch (InterruptedException e) {
            e.printStackTrace();
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\InterruptedException" + " " + System.currentTimeMillis() + ".png", "");
        }
        catch (SeleniumException e){
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\SeleniumException on row " + row +" "+ System.currentTimeMillis() + ".png", "");
        }
        return cost;
    }*/

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
            //   System.out.println(cost);

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
                            if (cell.getContents().toString().equals("терм")) {}
                            else   selenium.click("xpath=(//*[@test-id='from.use.address'])");
                            Thread.sleep(2000);
                            break;
                        case 4:
                            if (cell.getContents().toString().equals("терм")) {}
                            else   selenium.click("xpath=(//*[@test-id='to.use.address'])");
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

    public int getCostSetMultiParams(int row, int amount, String corresp) throws IOException {

        int cost = 0;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);

            Cell cell = sheet.getCell(1, row);
            String from = cell.getContents().toString();

            cell = sheet.getCell(2, row);
            String to = cell.getContents().toString();

            cell = sheet.getCell(27, row);
            cost = Integer.parseInt(cell.getContents());
            setCities(from, to);
            cell = sheet.getCell(0, row);

            if (corresp.equalsIgnoreCase("+corresp"))
                selenium.click("xpath=(//*[@test-id='correspondence.add'])");

            for (int items = 1; items <= amount; items++) {
                for (int column = 3; column < 25; column++) {
                    cell = sheet.getCell(column, row);
                    if (!(cell.getContents() == "")) {
                        switch (column) {

                            case 3:
                                selenium.click("xpath=(//*[@test-id='from.use.address'])");
                                Thread.sleep(2000);
                                break;
                            case 4:
                                selenium.click("xpath=(//*[@test-id='to.use.address'])");
                                break;
                            case 5:
                                selenium.type("xpath=(//*[@test-id='unit.length'])[" + items + "]", cell.getContents().toString().replaceAll(",", "."));
                                break;
                            case 6:
                                selenium.type("xpath=(//*[@test-id='unit.width'])[" + items + "]", cell.getContents().toString().replaceAll(",", "."));
                                break;
                            case 7:
                                selenium.type("xpath=(//*[@test-id='unit.height'])[" + items + "]", cell.getContents().toString().replaceAll(",", "."));
                                break;
                            case 8:
                                selenium.type("xpath=(//*[@test-id='unit.weight'])[" + items + "]", cell.getContents().toString().replaceAll(",", "."));
                                break;
                            case 9:
                                selenium.type("xpath=(//*[@test-id='unit.amount'])[" + items + "]", cell.getContents().toString().replaceAll(",", "."));
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
                if (items < amount) {
                    selenium.click("xpath=(//*[@test-id='unit.add'])");
                }
                row++;
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

    public void browserReload() throws Exception {
        selenium.close();
        selenium.stop();
        //Runtime.getRuntime().exec("TASKKILL /F /IM crashreporter.exe");
        Thread.sleep(5000);
        setUp();

    }

    @Before
    public void setUp() throws Exception {


        ExcelCalculatorTest test = new ExcelCalculatorTest();
        if (browser==0) selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://vozovoz.ru/");
        else selenium = new DefaultSelenium("localhost", 4444, "*googlechrome", "http://vozovoz.ru/");
        selenium.start();
        selenium.windowMaximize();
    }

    @Test
    public void testVar9SpbMsk() throws Exception {
        // getOrderNumber();

        // Random rand = new Random();

        // double randomNum = 0.1 + (2.4 - 0.1) * rand.nextDouble();

        while (true) {
            try {

                selenium.open("/");
                Thread.sleep(3000);

                try {
                    selenium.click("link=Личный кабинет");
                    login();
                } catch (SeleniumException e) {
                }


                //Новый пул
                for (int i=574; i<581; i++){//4
                    try{assertCheckout(getCostSetParams(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }finally {
                        browserReload();
                        Thread.sleep(3000);
                        try {
                            selenium.open("/user/login");
                            login();
                        }
                        catch(SeleniumException e) {}
                    }
                }


                //Новый пул терминалы
                for (int i=583; i<590; i++){//4
                    try{assertCheckout(getCostSetParams(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }finally {
                        browserReload();
                        Thread.sleep(3000);
                        try {
                            selenium.open("/user/login");
                            login();
                        }
                        catch(SeleniumException e) {}
                    }
                }


                //Старый пул терминалы

                for (int i=592; i<599; i++){//4
                    try{assertCheckout(getCostSetParams(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }finally {
                        browserReload();
                        Thread.sleep(3000);
                        try {
                            selenium.open("/user/login");
                            login();
                        }
                        catch(SeleniumException e) {}
                    }
                }
                System.out.print("new pool is done");

                selenium.open("/");
                Thread.sleep(3000);

                //CHECK 0.1*0.1*0.1
                try {
                    assertCheckout(getCostSetParams(572));
                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\01 01 01is fail  " + System.currentTimeMillis() + ".png", "");
                    selenium.click("css=div.header-logo.header-logo_img");
                } finally {
                    browserReload();
                    Thread.sleep(3000);
                    try {
                        selenium.open("/user/login");
                        login();
                    } catch (SeleniumException e) {
                    }
                }
                System.out.print("0.1  is done ");

                //Регресс бага по нерабочей кнопке оформить
                try {
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
                    selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Регресс бага FAIL " + System.currentTimeMillis() + ".png", "");
                    System.out.print(e.toString());
                } finally {
                    browserReload();
                    Thread.sleep(3000);
                    try {
                        selenium.open("/user/login");
                        login();
                    } catch (SeleniumException e) {
                    }
                }
                System.out.print("regress  is done ");

                //Вариант 0 - Корреспонденция
                for (int i=4; i<76; i++){//4
                    try{assertCheckout(getCostSetParams(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }finally {
                        browserReload();
                        Thread.sleep(3000);
                        try {
                            selenium.open("/user/login");
                            login();
                        }

                        catch(SeleniumException e) {}

                    }
                }
                System.out.print("row 76 is done");

                //Вариант 0* - Корреспонденция от терминала к терминалу
                for ( int i=80; i<152; i++){//80
                    try{assertCheckout(getCostSetParams(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " + System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }finally {
                        browserReload();
                        Thread.sleep(3000);
                        try {
                            selenium.open("/user/login");
                            login();
                        }

                        catch(SeleniumException e) {}

                    }
                }
                System.out.print("row 152 is done");

                //Вариант 1 - Фиксированная с льготными ПРР
                for ( int i=155; i<227; i++){//155
                    try{assertCheckout(getCostSetParams(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }finally {
                        browserReload();
                        Thread.sleep(3000);
                        try {
                            selenium.open("/user/login");
                            login();
                        }

                        catch(SeleniumException e) {}

                    }
                }
                System.out.print("row 227 is done");

                //Вариант 1 - Фикс с Полным ПРР
                for ( int i=230; i<302; i++){//230
                    try{assertCheckout(getCostSetParams(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }finally {
                        browserReload();
                        Thread.sleep(3000);
                        try {
                            selenium.open("/user/login");
                            login();
                        }

                        catch(SeleniumException e) {}

                    }
                }
                System.out.print("row 302 is done");

                //Вариант 2 - Минимум (Объемный)
                for ( int i=305; i<377; i++){//305
                    try{assertCheckout(getCostSetParams(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }finally {
                        browserReload();
                        Thread.sleep(3000);
                        try {
                            selenium.open("/user/login");
                            login();
                        }

                        catch(SeleniumException e) {}

                    }
                }
                System.out.print("row 377 is done");

                //Вариант 3 - 2 груза + Корреспонденция
                assertCheckout(getCostSetMultiParams(380, 2, "+corresp"));
                System.out.print("row 380 is done");
                browserReload();
                Thread.sleep(3000);
                try {
                    selenium.open("/user/login");
                    login();
                } catch (SeleniumException e) {
                }

                //Вариант 4 - 2 груза + Корреспонденция (1 негабарит) без ЖУ
                assertCheckout(getCostSetMultiParams(387, 2, "+corresp"));
                System.out.print("row 387 is done");
                browserReload();
                Thread.sleep(3000);
                try {
                    selenium.open("/user/login");
                    login();
                } catch (SeleniumException e) {
                }

                //Вариант 6 - 2 груза (Тяжелый) Терминалы
                assertCheckout(getCostSetMultiParams(410, 2, ""));
                System.out.print("row 410 is done");
                browserReload();
                Thread.sleep(3000);
                try {
                    selenium.open("/user/login");
                    login();
                } catch (SeleniumException e) {
                }

                //Вариант 8 - Фикс с Полным ПРР*1,5
                for (int i = 421; i < 493; i++) {//421
                    try {
                        assertCheckout(getCostSetParams(i));
                    } catch (SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " + System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    } finally {
                        browserReload();
                        Thread.sleep(3000);
                        try {
                            selenium.open("/user/login");
                            login();
                        } catch (SeleniumException e) {
                        }

                    }

                }
                System.out.print("row 493 is done");

                //Вариант 9 -Интервалы у Фикса
                for (int i = 569; i < 570; i++) {//498
                    try {
                        assertCheckout(getCostSetParams(i));
                    } catch (SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " + System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    } finally {
                        browserReload();
                        Thread.sleep(3000);
                    }
                }
                System.out.print("row 570 is done");

           /*     if (browser==1) browser=0;
                else browser=1;
                System.out.println("Cycled is over");*/


            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Some test FAILed " + System.currentTimeMillis() + ".png", "");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
