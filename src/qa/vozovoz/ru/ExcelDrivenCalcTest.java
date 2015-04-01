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

import static org.junit.Assert.fail;


public class ExcelDrivenCalcTest {

    int counter = 0;

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
        for (int second = 0; ; second++) {
            if (second >= 10) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Мои Данные find is FAIL " + System.currentTimeMillis() + ".png", "");
                break;
            }
            try {
                if ("Мои данные".equals(selenium.getText("link=Мои данные"))) { selenium.open("/");Thread.sleep(2000); break;}
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

    }

    public void waitLoad() throws InterruptedException {
       // Thread.sleep(4000);
        //selenium.click("xpath=(//*[@test-id='order.calc'])"); //new
      //  Thread.sleep(1000);
        for (int second = 0; ; second++) {
            if (second >= 10) {
                selenium.click("xpath=(//*[@test-id='order.calc'])");
                Thread.sleep(1000);
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Не прогрузилось слово Перевозка " + System.currentTimeMillis() + ".png", "");
                //fail("timeout");
                break;
            }
            try {
                if (Pattern.compile("(Перевозка)").matcher(selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div")).find())
                    break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }}

    public void addFloors() {
        selenium.click("xpath=(//*[@test-id='from.work.show'])");
        selenium.click("//input[@type='checkbox']");
        selenium.type("//input[@type='number']", "3");
        selenium.click("xpath=(//*[@test-id='to.work.show'])");
        selenium.click("xpath=(//input[@type='checkbox'])[3]");
        selenium.type("xpath=(//input[@type='number'])[2]", "3");
    }

    public void checkOut() throws InterruptedException {
        try {selenium.click("link=Личный кабинет");
            login();
        }
        catch(SeleniumException e) {}
        // ОФОРМЛЕНИЕ
        try {
            selenium.click("xpath=(//*[@test-id='order.create'])");
        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\click on orderCreate is fail " + " "+ System.currentTimeMillis() + ".png", "");

        } finally {
            Thread.sleep(5000);
            try{   selenium.click("css=div.header-logo.header-logo_img");
                Thread.sleep(2000);
            }
            catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\click on orderBACKWARD is fail " + " "+ System.currentTimeMillis() + ".png", "");

            }
        }
    }

    public void setContacts() throws InterruptedException {
        selenium.type("name=shipperFizFIO", "иванов никита");
        selenium.type("css=div.col-xs-6.consignee > app-calculator-side-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"shipperFizFIO\"]", "иванов андрей");
        selenium.type("name=shipperFizTel", "+7 (951) 685-32-60");
        selenium.type("css=div.col-xs-6.consignee > app-calculator-side-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"shipperFizTel\"]", "+7 (991) 001-26-55");
        //
    }

    public void addMultipleCargo(String l, String w, String h, String weight, String amount) {
        counter++;

        selenium.type("xpath=(//*[@test-id='unit.length'])[" + counter + "]", l);
        selenium.type("xpath=(//*[@test-id='unit.width'])[" + counter + "]", w);
        selenium.type("xpath=(//*[@test-id='unit.height'])[" + counter + "]", h);
        selenium.type("xpath=(//*[@test-id='unit.amount'])[" + counter + "]", amount);
        selenium.type("xpath=(//*[@test-id='unit.weight'])[" + counter + "]", weight);
        selenium.click("xpath=(//*[@test-id='order.calc'])");
    }

    public void assertCheckout(int cost) throws InterruptedException {
       // Thread.sleep(500);
        selenium.click("xpath=(//*[@test-id='order.calc'])");
        Thread.sleep(3000);
        for (int second = 0; ; second++) {
            if (second >= 6) {
                selenium.click("xpath=(//*[@test-id='order.calc'])");
                Thread.sleep(1000);
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Правильная цена " + cost + " "+ System.currentTimeMillis() + ".png", "");
                break;
            }
            try {
                if (cost == Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) {
                    setContacts();
                    checkOut();
                    break;
                }
                else selenium.click("xpath=(//*[@test-id='order.calc'])");
            } catch (Exception e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\not found order.calc произошло в  "+System.currentTimeMillis()+".png", "");
                System.out.print(e.toString());
            }
            Thread.sleep(3000);
        }
    }

    public void setCities(String sender, String receiver) throws InterruptedException {
        selenium.select("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div/div/div/select", "label=" + sender);
        selenium.select("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div[2]/div/div/select", "label=" + receiver);

        selenium.click("xpath=(//*[@test-id='minicalculator.order.create'])");
        waitLoad();

       // selenium.click("xpath=(//*[@test-id='to.use.address'])");
       // selenium.click("xpath=(//*[@test-id='from.use.address'])");


    }

    public void getOrderNumber() throws InterruptedException {
        Thread.sleep(1000);
        selenium.click("link=Отслеживание груза");
        selenium.type("document.formOrderStatus.elements[0]", "500010397");
        selenium.click("css=button.btn.btn-sm");
        selenium.click("link=Дополнительная информация");
        selenium.type("//input[@type='tel']", "+7 (964) 641-06-78");
        selenium.click("css=button.btn.btn-default");

        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (Pattern.compile("ООО \"В-СТАР\"").matcher(selenium.getText("css=span.selection.ng-binding")).find())
                    break;
            } catch (Exception e) {
                selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\traceCargo is fail  " + System.currentTimeMillis() + ".png", "");
            }
            Thread.sleep(1000);
        }

        /*// копируем номер заказа
        String zzzz = selenium.getText("css=span.selection.ng-binding");
        // вставить номер заказа
        selenium.type("//div[@id='body']/div[2]/app-find-order/section/div/div/div/form/div/div/input", zzzz);
        selenium.click("css=div.col-md-4 > button.btn");
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (Pattern.compile("[0]").matcher(selenium.getText("css=h3.ng-binding")).find()) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        // Проверка ОТСЛЕЖИВАНИЯ

        Assert.assertTrue(selenium.isElementPresent("css=h4.list-group-item-heading"));
        String summa = selenium.getText("css=p.list-group-item-text.ng-binding");
        Assert.assertTrue(selenium.isElementPresent("//div[@id='body']/div[2]/app-find-order/section[2]/div/div[2]/div[2]/h4"));
        Assert.assertTrue(selenium.isElementPresent("//div[@id='body']/div[2]/app-find-order/section[2]/div/div[2]/div[3]/h4"));
*/
    }

    private Selenium selenium;

    public int getCitiesGetCost(int row) throws IOException {
        File inputWorkbook = new File("C:\\Users\\n.ivanov\\Desktop\\SeleniumSuite\\Сценарии18.xls");
        Workbook w;
        int cost = 0;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);
         //   for (int j = 4; j < sheet.getColumns(); j++) {
                //  for (int i = 0; i < sheet.getRows(); i++) {

                Cell cell = sheet.getCell(1, row);
                String from = cell.getContents().toString();
                System.out.println(from);

                cell = sheet.getCell(2, row);
                String to = cell.getContents().toString();
                System.out.println(to);

                cell = sheet.getCell(27, row);
                cost = Integer.parseInt(cell.getContents());
                System.out.println(cost);

                setCities(from, to);


            cell = sheet.getCell(0, row);
            System.out.println("NUMBER 0 IS------"+cell.getContents().toString());

            if (!(cell.getContents().toString().equals("1"))) {
                System.out.println("NUMBER 0 ne raven 1"+cell.getContents().toString());
                selenium.click("xpath=(//*[@test-id='unit.remove'])");
                selenium.click("xpath=(//*[@test-id='correspondence.add'])");
            }
            for (int cc=3; cc<25;cc++) {

                cell = sheet.getCell(cc, row);
                if (!(cell.getContents() == "")) {
                    System.out.println("finded= "+cell.getContents().toString().replaceAll(",", "."));
                    switch (cc){

                        case 3: selenium.click("xpath=(//*[@test-id='from.use.address'])"); System.out.println("click on FROM USE ADDRESS"); Thread.sleep(2000); break;
                        case 4: selenium.click("xpath=(//*[@test-id='to.use.address'])"); break;
                        case 5: selenium.type("xpath=(//*[@test-id='unit.length'])[1]", cell.getContents().toString().replaceAll(",", ".")); break;
                        case 6: selenium.type("xpath=(//*[@test-id='unit.width'])[1]",  cell.getContents().toString().replaceAll(",", ".")); break;
                        case 7: selenium.type("xpath=(//*[@test-id='unit.height'])[1]", cell.getContents().toString().replaceAll(",", ".")); break;
                        case 8: selenium.type("xpath=(//*[@test-id='unit.weight'])[1]", cell.getContents().toString().replaceAll(",", ".")); break;
                        case 9: selenium.type("xpath=(//*[@test-id='unit.amount'])[1]", cell.getContents().toString().replaceAll(",", ".")); break;

                        case 12:selenium.click("xpath=(//*[@test-id='packages.add'])");
                                  selenium.click("xpath=(//*[@test-id='packages.extraPackage'])");
                                    break;
                        case 13:
                            selenium.click("xpath=(//*[@test-id='packages.bubbleFilm'])");
                            break;
                        case 14:
                            selenium.click("xpath=(//*[@test-id='packages.hardPackage'])");
                            break;
                        case 15:
                            selenium.click("xpath=(//*[@test-id='packages.box1'])");
                            break;
                        case 16:
                            selenium.click("xpath=(//*[@test-id='packages.box2'])");
                            break;
                        case 17:
                            selenium.click("xpath=(//*[@test-id='packages.box3'])");
                            break;
                        case 18:
                            selenium.click("xpath=(//*[@test-id='packages.box4'])");
                            break;
                        case 19:
                            selenium.click("xpath=(//*[@test-id='packages.bag1'])");
                            break;
                        case 20:
                            selenium.click("xpath=(//*[@test-id='packages.bag2'])");
                            break;
                        case 21:
                            selenium.click("xpath=(//*[@test-id='packages.safePackage'])");
                            break;
                        case 22:
                            selenium.click("xpath=(//*[@test-id='packages.sealPackage'])");
                            break;
                        case 23:
                            selenium.click("xpath=(//*[@test-id='from.work.show'])");
                            selenium.click("//input[@type='checkbox']");
                            selenium.type("//input[@type='number']", "3");
                            break;
                        case 24:
                            selenium.click("xpath=(//*[@test-id='to.work.show'])");
                            selenium.click("xpath=(//input[@type='checkbox'])[3]");
                            selenium.type("xpath=(//input[@type='number'])[2]", "3");
                            break;

                    }

                }



            }



            Thread.sleep(2000);
           // selenium.click("xpath=(//*[@test-id='order.calc'])");

                    /*CellType type = cell.getType();

                    if (type == CellType.LABEL) {
                        System.out.println("I got a label "   + cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
                        System.out.println("I got a number "   + cell.getContents());
                    }*/


                //  }
        //    }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      return cost;
    }


    @Before
    public void setUp() throws Exception {
        ExcelDrivenCalcTest test = new ExcelDrivenCalcTest();

        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://vozovoz.ru/");
        selenium.start();
    }

    @Test
    public void testVar9SpbMsk() throws Exception {
        // getOrderNumber();
        selenium.open("/");

       try {selenium.click("link=Личный кабинет");
            login();
        }

        catch(SeleniumException e) {}

        while (true) {
            try {
                Thread.sleep(1000);
//                // VAR 9 (1-9)
//                assertSetCheckOut("0.8", "0.4", "0.4", "2.4", "1", 690);
//                // PARAM2
//                assertSetCheckOut("0.8", "0.4", "0.4", "2.5", "1", 800);
//                // PARAM4
//                assertSetCheckOut("0.8", "0.4", "0.4", "5", "1", 940);
//                // PARAM3
//                assertSetCheckOut("0.8", "0.4", "0.4", "4.9", "1", 800);
//                // PARAM5
//                assertSetCheckOut("0.8", "0.4", "0.4", "9.9", "1", 940);
//                // PARAM6
//                assertSetCheckOut("0.8", "0.4", "0.4", "10", "1", 950);
//                // PARAM8
//                assertSetCheckOut("0.8", "0.4", "0.4", "20", "1", 960);
//                // PARAM7
//                assertSetCheckOut("0.8", "0.4", "0.4", "19.9", "1", 950);
//                // PARAM8
//                assertSetCheckOut("0.8", "0.4", "0.4", "40", "1", 960);
                //Вариант 0 - Корреспонденция
                for (int i=6; i<76; i++){ //4
                    try{assertCheckout(getCitiesGetCost(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }
                }

                //Вариант 0* - Корреспонденция от терминала к терминалу
                for ( int i=82; i<152; i++){ //80
                    try{assertCheckout(getCitiesGetCost(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " + System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }
                }

               // selenium.click("xpath=(//*[@test-id='unit.remove'])");
                //Вариант 1 - Фиксированная с льготными ПРР
                for ( int i=157; i<227; i++){ //80
                    try{assertCheckout(getCitiesGetCost(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }
                }

                //Вариант 1 - Фикс с Полным ПРР
                for ( int i=232; i<302; i++){ //80
                    try{assertCheckout(getCitiesGetCost(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }
                }

                //Вариант 2 - Минимум (Объемный)
                for ( int i=307; i<377; i++){ //80
                    try{assertCheckout(getCitiesGetCost(i));}
                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Assert fail row  " + i + " " +System.currentTimeMillis() + ".png", "");
                        selenium.click("css=div.header-logo.header-logo_img");
                    }
                }




             /*   //CHECK 0.1*0.1*0.1
                assertSetCheckOut("0.1", "0.1", "0.1", "1", "1", 690);

                // VAR 0(correspond check)
                selenium.click("xpath=(//*[@test-id='unit.remove'])");
                selenium.click("xpath=(//*[@test-id='correspondence.add'])");
                selenium.click("xpath=(//*[@test-id='order.calc'])");
                Thread.sleep(1000);
                assertCheckout(550);
*/

            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogExcel\\Cities checking is FAIL " + System.currentTimeMillis() + ".png", "");
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