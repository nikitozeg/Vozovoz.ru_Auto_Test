package qa.vozovoz.ru;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

public class CalculatorTest {

    int counter = 0;

    public void login() throws InterruptedException {

            for (int second = 0; ; second++) {
                if (second >= 10) {
                    selenium.captureEntirePageScreenshot("C:\\errorlog\\login FAIL " + System.currentTimeMillis() + ".png", "");
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
                    selenium.captureEntirePageScreenshot("C:\\errorlog\\Мои Данные find is FAIL " + System.currentTimeMillis() + ".png", "");
                    break;
                }
                try {
                    if ("Мои данные".equals(selenium.getText("link=Мои данные"))) { selenium.click("link=Оформление заказа"); Thread.sleep(4000); setContacts();  break;}
                } catch (Exception e) {
                }
                Thread.sleep(1000);
            }

    }

    public void waitLoad() throws InterruptedException {
        selenium.click("xpath=(//*[@test-id='order.calc'])"); //new
        for (int second = 0; ; second++) {
            if (second >= 10) {
                selenium.captureEntirePageScreenshot("C:\\errorlog\\Не прогрузилось слово Перевозка " + System.currentTimeMillis() + ".png", "");
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

    public void assertSetCheckOut(String l, String w, String h, String weight, String amount, int cost) throws InterruptedException {
        try {
            selenium.type("xpath=(//*[@test-id='unit.length'])[1]", l);
            selenium.type("xpath=(//*[@test-id='unit.width'])[1]", w);
            selenium.type("xpath=(//*[@test-id='unit.height'])[1]", h);
            selenium.type("xpath=(//*[@test-id='unit.amount'])[1]", amount);
            selenium.type("xpath=(//*[@test-id='unit.weight'])[1]", weight);
            Thread.sleep(500); //Задержка
            selenium.click("xpath=(//*[@test-id='order.calc'])");
            Thread.sleep(500);
        } catch (SeleniumException e) {
            selenium.captureEntirePageScreenshot("C:\\errorlog\\unit filling is fail " + cost  + " "+ System.currentTimeMillis() + ".png", "");
        }

        for (int second = 0; ; second++) {
            if (second >= 4) {
                selenium.captureEntirePageScreenshot("C:\\errorlog\\Правильная цена " + cost + " "+ System.currentTimeMillis() + ".png", "");
                break;
            }
            try {
                if (cost == Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) {
                    checkOut();
                    break;
                }
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlog\\Правильная цена " + cost + " "+ System.currentTimeMillis() + ".png", "");
            }
            catch (NumberFormatException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlog\\Три точки в цене "+ System.currentTimeMillis() + ".png", "");
            }
            Thread.sleep(1000);
        }

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
            selenium.captureEntirePageScreenshot("C:\\errorlog\\click on orderCreate is fail " + " "+ System.currentTimeMillis() + ".png", "");

        } finally {
            Thread.sleep(40000);
         try{   selenium.click("xpath=(//*[@test-id='order.backward'])");

             Thread.sleep(2000);

         }
            catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlog\\click on orderBACKWARD is fail " + " "+ System.currentTimeMillis() + ".png", "");

            }
        }
    }

    public void setContacts() throws InterruptedException {
        selenium.type("name=shipperFizFIO", "иванов никита");
        selenium.type("css=div.col-xs-6.consignee > app-calculator-side-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"shipperFizFIO\"]", "иванов андрей");
        selenium.type("name=shipperFizTel", "+7 (951) 685-32-60");
        selenium.type("css=div.col-xs-6.consignee > app-calculator-side-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"shipperFizTel\"]", "+7 (991) 001-26-55");

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

    public void chooseAllPackage() {
        selenium.click("xpath=(//*[@test-id='packages.add'])");
        selenium.click("xpath=(//*[@test-id='packages.bag1'])");
        selenium.click("xpath=(//*[@test-id='packages.bag2'])");
        selenium.click("xpath=(//*[@test-id='packages.sealPackage'])");
        selenium.click("xpath=(//*[@test-id='packages.safePackage'])");
        selenium.click("xpath=(//*[@test-id='packages.box1'])");//40*20*20
        selenium.click("xpath=(//*[@test-id='packages.box2'])");
        selenium.click("xpath=(//*[@test-id='packages.box3'])");
        selenium.click("xpath=(//*[@test-id='packages.box4'])");
        selenium.click("xpath=(//*[@test-id='packages.hardPackage'])");
        selenium.click("xpath=(//*[@test-id='packages.extraPackage'])");
        selenium.click("xpath=(//*[@test-id='packages.bubbleFilm'])");
    }

    public void assertCheckout(int cost) throws InterruptedException {
        Thread.sleep(500);
        selenium.click("xpath=(//*[@test-id='order.calc'])");
        Thread.sleep(500);
        for (int second = 0; ; second++) {
            if (second >= 5) {
                Thread.sleep(1000);
                selenium.captureEntirePageScreenshot("C:\\errorlog\\Правильная цена " + cost + " "+ System.currentTimeMillis() + ".png", "");
                //fail("timeout");
                break;
            }
            try {
                if (cost == Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) {
                    checkOut();
                    break;
                }
                else selenium.click("xpath=(//*[@test-id='order.calc'])");
            } catch (Exception e) {
                selenium.captureEntirePageScreenshot("C:\\errorlog\\not found order.calc произошло в  "+System.currentTimeMillis()+".png", "");
                System.out.print(e.toString());
            }
            Thread.sleep(1000);
        }
    }

    public void setCities(String sender, String receiver) throws InterruptedException {
        selenium.select("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div/div/div/select", "label="+sender);
        selenium.select("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div[2]/div/div/select", "label=" + receiver);
        selenium.click("xpath=(//*[@test-id='minicalculator.order.create'])");
        waitLoad();
        selenium.click("xpath=(//*[@test-id='to.use.address'])");
        selenium.click("xpath=(//*[@test-id='from.use.address'])");
    }

  /*  public void getOrderNumber() throws InterruptedException {
        Thread.sleep(1000);
        selenium.click("link=Отслеживание груза");
        selenium.type("document.formOrderStatus.elements[0]","500010397");
        selenium.click("css=button.btn.btn-sm");
        selenium.click("link=Дополнительная информация");
        selenium.type("//input[@type='tel']","+7 (964) 641-06-78");
        selenium.click("css=button.btn.btn-default");

        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (Pattern.compile("ООО \"В-СТАР\"").matcher(selenium.getText("css=span.selection.ng-binding")).find()) break;
            } catch (Exception e) {
                selenium.captureEntirePageScreenshot("C:\\errorlog\\traceCargo is fail  "+System.currentTimeMillis()+".png", "");
            }
            Thread.sleep(1000);
        }

        // копируем номер заказа
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

    }
*/
    private Selenium selenium;



    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://vozovoz.ru/");
        selenium.start();
    }

    @Test
    public void testVar9SpbMsk() throws Exception {
       // getOrderNumber();


        while (true) {

         try{   selenium.open("/");
          //  getOrderNumber();

            selenium.click("link=Оформление заказа");
              Thread.sleep(2000);
            //Ожидание загрузки страницы расчета (ждем появления строки ПЕРЕВОЗКА)
            waitLoad();


            // VAR 9 (1-9)


            assertSetCheckOut("0.8", "0.4", "0.4", "2.4", "1", 690);
             setContacts();
            // PARAM2
            assertSetCheckOut("0.8", "0.4", "0.4", "2.5", "1", 800);
            // PARAM4
            assertSetCheckOut("0.8", "0.4", "0.4", "5", "1", 940);
            // PARAM3
            assertSetCheckOut("0.8", "0.4", "0.4", "4.9", "1", 800);
            // PARAM5
            assertSetCheckOut("0.8", "0.4", "0.4", "9.9", "1", 940);
            // PARAM6
            assertSetCheckOut("0.8", "0.4", "0.4", "10", "1", 950);
            // PARAM8
            assertSetCheckOut("0.8", "0.4", "0.4", "20", "1", 960);
            // PARAM7
            assertSetCheckOut("0.8", "0.4", "0.4", "19.9", "1", 950);
            // PARAM8
            assertSetCheckOut("0.8", "0.4", "0.4", "40", "1", 960);

            //CHECK 0.1*0.1*0.1
            assertSetCheckOut("0.1", "0.1", "0.1", "1", "1", 690);

            // VAR 0(correspond check)
            selenium.click("xpath=(//*[@test-id='unit.remove'])");
            selenium.click("xpath=(//*[@test-id='correspondence.add'])");
            selenium.click("xpath=(//*[@test-id='order.calc'])");
            Thread.sleep(1000);
            assertCheckout(550);

            //VAR 1(1)
            selenium.click("xpath=(//*[@test-id='correspondence.remove'])");
            selenium.click("xpath=(//*[@test-id='unit.add'])");
            addFloors();
            chooseAllPackage();
            assertSetCheckOut("0.8", "0.4", "0.4", "10", "1", 2640);


            //VAR 1(2)
            setContacts();
            assertSetCheckOut("0.8", "0.4", "0.4", "39.9", "1", 4060);

            //VAR 2
            assertSetCheckOut("1", "0.4", "0.4", "10", "1", 4230);

            //VAR 8
            assertSetCheckOut("0.8", "0.4", "0.4", "40", "1", 5110);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //CHANGE TO MSK-SPB using URL//
            selenium.open("/calculate-the-order?calculationId=54fff5cdf2f2862e3c88419e"); //RU!
            //       selenium.open("/calculate-the-order?calculationId=550319c5600469746778b9db"); //QA!!!

            // VAR 3
            Thread.sleep(4000);
            selenium.click("xpath=(//*[@test-id='order.create'])");
            setContacts();
            selenium.click("link=Груз");
            assertCheckout(4580);

            // VAR 4
            counter = 0;
            selenium.click("xpath=(//*[@test-id='unit.remove'])");
            selenium.click("xpath=(//*[@test-id='packages.remove'])");
            selenium.click("xpath=(//*[@test-id='packages.add'])");
            selenium.click("xpath=(//*[@test-id='packages.box4'])");
            addMultipleCargo("5", "0.4", "0.4", "10", "2");
            selenium.click("xpath=(//*[@test-id='unit.add'])");
            addMultipleCargo("0.7", "0.6", "0.5", "10", "1");
            selenium.click("xpath=(//*[@test-id='correspondence.add'])");
            selenium.click("xpath=(//*[@test-id='order.cost'])");
            addFloors();
            assertCheckout(10920);


            // VAR 7
            selenium.click("xpath=(//*[@test-id='packages.add'])");
            selenium.click("xpath=(//*[@test-id='packages.box4'])");
            selenium.click("xpath=(//*[@test-id='packages.hardPackage'])");
            selenium.click("xpath=(//*[@test-id='packages.extraPackage'])");
            selenium.click("xpath=(//*[@test-id='packages.bubbleFilm'])");

            selenium.click("xpath=(//*[@test-id='from.work.hide'])");
            selenium.click("xpath=(//*[@test-id='to.work.hide'])");
            selenium.click("xpath=(//*[@test-id='unit.remove'])");
            selenium.click("xpath=(//*[@test-id='correspondence.remove'])");
            assertSetCheckOut("0.4", "0.2", "0.2", "5", "3", 1420);

            // VAR 9 (10-18)
            selenium.click("xpath=(//*[@test-id='packages.remove'])");
            assertSetCheckOut("0.8", "0.4", "0.4", "2.4", "1", 690);
            // PARAM2
            assertSetCheckOut("0.8", "0.4", "0.4", "2.5", "1", 800);
            // PARAM4
            assertSetCheckOut("0.8", "0.4", "0.4", "5", "1", 940);
            // PARAM3
            assertSetCheckOut("0.8", "0.4", "0.4", "4.9", "1", 800);
            // PARAM5
            assertSetCheckOut("0.8", "0.4", "0.4", "9.9", "1", 940);
            // PARAM6
            assertSetCheckOut("0.8", "0.4", "0.4", "10", "1", 950);
            // PARAM8
            assertSetCheckOut("0.8", "0.4", "0.4", "20", "1", 960);
            // PARAM7
            assertSetCheckOut("0.8", "0.4", "0.4", "19.9", "1", 950);
            // PARAM8
            assertSetCheckOut("0.8", "0.4", "0.4", "40", "1", 960);

            // VAR 6
            selenium.open("/");
            Thread.sleep(2000);
            selenium.click("xpath=(//*[@test-id='minicalculator.cities.change'])");
            selenium.click("xpath=(//*[@test-id='minicalculator.order.create'])");
            waitLoad();
            counter = 0;
            addMultipleCargo("0.9", "0.4", "0.4", "35", "4");
            selenium.click("xpath=(//*[@test-id='unit.add'])");
            addMultipleCargo("0.7", "0.5", "0.4", "38", "3");
            selenium.click("xpath=(//*[@test-id='packages.add'])");
            selenium.click("xpath=(//*[@test-id='packages.hardPackage'])");
            selenium.click("xpath=(//*[@test-id='packages.extraPackage'])");
            selenium.click("xpath=(//*[@test-id='packages.bubbleFilm'])");
            selenium.click("xpath=(//*[@test-id='packages.box4'])");

            setContacts();
            assertCheckout(2350);
         } catch (SeleniumException e){
             selenium.captureEntirePageScreenshot("C:\\errorlog\\FAIL OCCURED "+System.currentTimeMillis()+".png", "");
             System.out.println(System.currentTimeMillis());
         }
            try {
                selenium.open("/");
                Thread.sleep(3000);
                setCities("Волгоград", "Воронеж");
                setContacts();
                assertSetCheckOut("0.1", "0.1", "0.1", "1", "1", 580);


                selenium.open("/");
                Thread.sleep(3000);
                setCities("Краснодар", "Ростов-на-Дону");
                setContacts();
                assertSetCheckOut("0.1", "0.1", "0.1", "1", "1", 540);


                selenium.open("/");
                Thread.sleep(3000);
                setCities("Самара", "Саратов");
                setContacts();
                assertSetCheckOut("0.1", "0.1", "0.1", "1", "1", 570);

                selenium.open("/");
                Thread.sleep(3000);
                setCities("Ставрополь", "Волгоград");
                setContacts();
                assertSetCheckOut("0.1", "0.1", "0.1", "1", "1", 610);


            } catch (SeleniumException e){
                selenium.captureEntirePageScreenshot("C:\\errorlog\\Cities checking is FAIL "+System.currentTimeMillis()+".png", "");

            }

           /* Writer output = new BufferedWriter(new FileWriter(file));
            output.write(selenium.getLog());
            output.close();
            System.out.println("Test Complete");*/
        }
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
