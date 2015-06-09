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
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.regex.Pattern;


public class srk78109 {

    boolean nextIsUrLico = false;
    int browser = 0;

    String from="&";
    String to="&";

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

        if (selenium.isTextPresent("500010118")) ;
        else
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Order number is notFOUND " + System.currentTimeMillis() + ".png", "");

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
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\не найден локатор ПЕРЕВОЗКИ " + System.currentTimeMillis() + ".png", "");
                break;
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
        try {
            Thread.sleep(1000);
            //  selenium.click("xpath=(//*[@test-id='order.create'])");
            selenium.click("//div[@id='main']/ng-form/div/div[2]/div/div[3]/div/button");
            Thread.sleep(2000);
        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\click on orderCreate is fail " + " " + System.currentTimeMillis() + ".png", "");
            Thread.sleep(2000);
            try {
                selenium.click("//div[@id='main']/ng-form/div/div[2]/div/div[3]/div/button");
            } catch (SeleniumException ee) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\REPEAT click on orderCreate is fail " + " " + System.currentTimeMillis() + ".png", "");
                Thread.sleep(2000);
            }
        }

        Thread.sleep(2000);

        try {
            selenium.click("//button[@type='button']");
        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\click on button is fail " + " " + System.currentTimeMillis() + ".png", "");
            Thread.sleep(2000);
            try {
                selenium.click("//button[@type='button']");
            } catch (SeleniumException ee) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\REPEAT click on button is fail " + " " + System.currentTimeMillis() + ".png", "");
                Thread.sleep(2000);
            }
        }

        Thread.sleep(1000);
        for (int second = 0; ; second++) {
            if (second >= 5) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\OrderNumber is fail " + " " + System.currentTimeMillis() + ".png", "");
                break;
            }
            try {
                if ("000008116".equals(selenium.getText("css=span.selection.ng-binding"))) {
                    Thread.sleep(1000);
                    break;
                }
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\ng-binding not found " + " " + System.currentTimeMillis() + ".png", "");
                break;
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
        } catch (Exception e) {
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

            selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div/app-select-address/oi-multiselect/div/ul/li");
            Thread.sleep(1000);
            selenium.type("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div/app-select-address/oi-multiselect/div/ul/li/input", sender);
            Thread.sleep(1000);
            selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div/app-calculator-side-from/form/app-calculator-side-location/div/ng-form/div[2]/div/app-select-address/oi-multiselect/div[2]/ul/li");

            Thread.sleep(1000);
            selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div[2]/app-calculator-side-to/form/app-calculator-side-location/div/ng-form/div[2]/div/app-select-address/oi-multiselect/div/ul/li");
            Thread.sleep(1000);
            selenium.type("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div[2]/app-calculator-side-to/form/app-calculator-side-location/div/ng-form/div[2]/div/app-select-address/oi-multiselect/div/ul/li/input", receiver);
            Thread.sleep(1000);
            selenium.click("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div[2]/div[2]/app-calculator-side-to/form/app-calculator-side-location/div/ng-form/div[2]/div/app-select-address/oi-multiselect/div[2]/ul/li");

        } catch (SeleniumException e) {
            e.printStackTrace();
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Select Cities is failed" + " " + System.currentTimeMillis() + ".png", "");
            throw new SeleniumException(e);
        }



    }



    private Selenium selenium;

  /*  public int getCostSetParams(int row) throws IOException {


        int cost = 0;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);
            //  for (int j = 4; j < sheet.getColumns(); j++) {
            //  for (int i = 0; i < sheet.getRows(); i++) {

            Cell cell = sheet.getCell(1, row);
            from = cell.getContents().toString();
             System.out.print(from + " ");

            cell = sheet.getCell(2, row);
            to = cell.getContents().toString();
             System.out.println(to);

           // cell = sheet.getCell(27, row);
           // cost = Integer.parseInt(cell.getContents());
            //   System.out.println(cost);

              setCities(from, to);


            cell = sheet.getCell(0, row);

            if (!(cell.getContents().toString().equals("1"))) {
                if (cell.getContents().toString().equals("1")) {
                    selenium.click("xpath=(//*[@test-id='from.use.ppv'])");
                } //Терминал - дверь 1
                else if (cell.getContents().toString().equals("2")) {
                    selenium.click("xpath=(//*[@test-id='from.use.ppv'])");
                    selenium.click("xpath=(//*[@test-id='to.use.ppv'])");
                }//терм-терм
                else if (cell.getContents().toString().equals("3")) {
                    selenium.click("xpath=(//*[@test-id='to.use.ppv'])");
                } //дверь-терм
                else if (cell.getContents().toString().equals("4")) {
                } //дверь-дверь
                else
                    selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\smthgoeswrong" + " " + System.currentTimeMillis() + ".png", "");
                Thread.sleep(1000);

            }

            for (int column = 3; column < 7; column++) {

                cell = sheet.getCell(column, row);
                if (!(cell.getContents() == "")) {
                    // System.out.print(" " + cell.getContents().toString().replaceAll(",", "."));
                    switch (column) {

                        case 3://mesta
                            selenium.type("xpath=(//input[@type='number'])[5]", cell.getContents().toString().replaceAll(",", "."));
                            break;
                        case 4:
                            selenium.type("xpath=(//*[@test-id='unit.weight'])[1]", cell.getContents().toString().replaceAll(",", "."));
                            break;
                        case 5:
                            selenium.click("//input[@type='checkbox']");
                            Thread.sleep(1000);
                            selenium.type("xpath=(//input[@type='number'])[6]", cell.getContents().toString().replaceAll(",", "."));
                            break;
                        case 6:
                            selenium.type("xpath=(//*[@test-id='unit.height'])[1]", cell.getContents().toString().replaceAll(",", "."));
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
        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\SeleniumException on row " + row + " " + System.currentTimeMillis() + ".png", "");
        }





        return cost;

    }*/

    public void browserReload() throws Exception {
        selenium.close();
        selenium.stop();
        //Runtime.getRuntime().exec("TASKKILL /F /IM crashreporter.exe");
        Thread.sleep(5000);
        setUp();

    }

    @Before
    public void setUp() throws Exception {

        srk78109 test = new srk78109();
        if (browser == 0) selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://dev.vozovoz.ru/");
        else selenium = new DefaultSelenium("localhost", 4444, "*googlechrome", "http://vozovoz.ru/");
        selenium.start();
        selenium.windowMaximize();
    }

    @Test
    public void testVar9SpbMsk() throws Exception {

        selenium.open("/calculate-the-order");
        Thread.sleep(2000);

        File inputWorkbook = new File("C:\\Users\\n.ivanov\\Dropbox\\SRK\\test2.xls");
        File crowlerResult = new File("C:\\Users\\n.ivanov\\Dropbox\\crowlerResult.xls");

        WritableWorkbook writableWorkbook = Workbook.createWorkbook(crowlerResult);
        WritableSheet writableSheet = writableWorkbook.createSheet("Sheet2", 0);

        Workbook w = Workbook.getWorkbook(inputWorkbook);
        Sheet sheet = w.getSheet(0);


        try {

            for (int i = 149; i < 159; i++) {
                try {


                    try {

                        Cell cell = sheet.getCell(1, i);
                        from = cell.getContents().toString();
                        System.out.print(from + " ");

                        cell = sheet.getCell(2, i);
                        to = cell.getContents().toString();
                        System.out.println(to);

                        setCities(from, to);

                        System.out.println("will getcellTYPE!!!!");
                        cell = sheet.getCell(0, i);


                        if (cell.getContents().toString().equals("1")) {
                                selenium.click("xpath=(//*[@test-id='from.use.ppv'])");
                                System.out.println("t-d!!!!");
                                //Терминал - дверь 1
                        }
                            else if (cell.getContents().toString().equals("2")) {
                                selenium.click("xpath=(//*[@test-id='from.use.ppv'])");
                                selenium.click("xpath=(//*[@test-id='to.use.ppv'])");
                                System.out.println("t-t!!!!");
                            }//терм-терм
                                else if (cell.getContents().toString().equals("3")) {
                                                          selenium.click("xpath=(//*[@test-id='to.use.ppv'])");
                                                          System.out.println("d-t!!!!");
                                                    } //дверь-терм
                                       else if (cell.getContents().toString().equals("4")) {
                                                System.out.println("d-d!!!!");
                                                     } //дверь-дверь
                                              else
                                                   selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\smthgoeswrong" + " " + System.currentTimeMillis() + ".png", "");
                                                     Thread.sleep(1000);




                        for (int column = 3; column < 7; column++) {

                            cell = sheet.getCell(column, i);
                            if (!(cell.getContents() == "")) {
                                switch (column) {

                                    case 3://mesta
                                        selenium.type("xpath=(//input[@type='number'])[5]", cell.getContents().toString().replaceAll(",", "."));
                                        break;
                                    case 4:
                                        selenium.type("xpath=(//*[@test-id='unit.weight'])[1]", cell.getContents().toString().replaceAll(",", "."));
                                        break;
                                    case 5:
                                        selenium.click("//input[@type='checkbox']");
                                        Thread.sleep(1000);
                                        selenium.type("xpath=(//input[@type='number'])[6]", cell.getContents().toString().replaceAll(",", "."));
                                        break;
                                    case 6:
                                        selenium.type("xpath=(//*[@test-id='unit.height'])[1]", cell.getContents().toString().replaceAll(",", "."));
                                        break;

                                }

                            }


                        }
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\InterruptedException" + " " + System.currentTimeMillis() + ".png", "");
                    } catch (SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\SeleniumException on row " + i + " " + System.currentTimeMillis() + ".png", "");
                    }



                    selenium.type("xpath=(//*[@test-id='unit.length'])[1]", "1");
                    selenium.type("xpath=(//*[@test-id='unit.width'])[1]", "1");


                    selenium.click("xpath=(//*[@test-id='order.calc'])");
                    Thread.sleep(1500);

                    for(int ii=0;ii<7;ii++){
                      try{  if (selenium.isTextPresent("оплате")) {

                                System.out.println("i= " + i +" cost= "+ selenium.getText("css=div.order-panel-total-cost.ng-binding"));
                                System.out.println("объем=" + selenium.getText("css=i.calculator-cargo-list-total-text.ng-binding"));
                                System.out.println("вес=" + selenium.getValue("xpath=(//input[@type='number'])[4]"));
                                System.out.println("штук=" + selenium.getValue("xpath=(//input[@type='number'])[5]"));
                                  if (selenium.isTextPresent("Забор груза")) {
                                      Label label8 = new Label(8, i, selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div[2]") );
                                      writableSheet.addCell(label8);
                                  }
                                Label label0 = new Label(0, i, sheet.getCell(0, i).getContents().toString() );
                                Label label1 = new Label(1, i, from);
                                Label label2 = new Label(2, i, to);
                                Label label3 = new Label(3, i, selenium.getText("css=i.calculator-cargo-list-total-text.ng-binding"));//volume
                                Label label4 = new Label(4, i, selenium.getValue("xpath=(//input[@type='number'])[4]"));//ves
                                Label label5 = new Label(5, i, selenium.getValue("xpath=(//input[@type='number'])[5]"));//kol
                                Label label6 = new Label(6, i, selenium.getText("css=div.order-panel-total-cost.ng-binding"));//cost
                                Label label7 = new Label(7, i, Integer.toString(i) );

                                writableSheet.addCell(label0);
                                writableSheet.addCell(label1);
                                writableSheet.addCell(label2);
                                writableSheet.addCell(label3);
                                writableSheet.addCell(label4);
                                writableSheet.addCell(label5);
                                writableSheet.addCell(label6);
                                writableSheet.addCell(label7);

                                selenium.click("xpath=(//*[@test-id='from.use.address'])");
                                selenium.click("xpath=(//*[@test-id='to.use.address'])");

                                break;

                        }
                        else {
                            Thread.sleep(1000);

                        }}catch (SeleniumException e){System.out.println("fail on "+i);
                          selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\ sshouldntbe " + System.currentTimeMillis() + ".png", "");}


                    }




                } catch (SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " + System.currentTimeMillis() + ".png", "");
                }


                // finally {
                 //   browserReload();
                  //  Thread.sleep(3000);
                  //  try {
                  //      selenium.open("/user/login");
                 //       login();
                 //   } catch (SeleniumException e) {
                 //   }
               // }
            }

        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Some test FAILed " + System.currentTimeMillis() + ".png", "");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        finally {
            writableWorkbook.write();
            writableWorkbook.close();
        }

    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
