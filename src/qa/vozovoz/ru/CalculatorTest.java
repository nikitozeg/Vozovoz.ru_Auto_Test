package qa.vozovoz.ru;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;

public class CalculatorTest {

    public static void main(String[] args) throws Exception {
        JUnitCore.main("qa.vozovoz.ru.CalculatorTest");
    }

    String timeStamp = new SimpleDateFormat("HH.mm число dd месяц MM").format(Calendar.getInstance().getTime());
    int counter=0;

    public void waitLoad() throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 10) { selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\Не прогрузилось слово Перевозка, произошло в " + timeStamp + ".png", ""); fail("timeout"); }
            try {
                if (Pattern.compile("(Перевозка)").matcher(selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div")).find())
                    break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
    }

    public void addFloors() throws InterruptedException {
        selenium.click("xpath=(//*[@test-id='from.work.show'])");
        selenium.click("//input[@type='checkbox']");
        selenium.type("//input[@type='number']", "3");
        selenium.click("xpath=(//*[@test-id='to.work.show'])");
        selenium.click("xpath=(//input[@type='checkbox'])[3]");
        selenium.type("xpath=(//input[@type='number'])[2]", "3");
    }

    public void calculateTest(String l, String w, String h, String weight, String amount, int cost) throws InterruptedException {
        selenium.type("xpath=(//*[@test-id='unit.length'])[1]", l);
        selenium.type("xpath=(//*[@test-id='unit.width'])[1]", w);
        selenium.type("xpath=(//*[@test-id='unit.height'])[1]", h);
        selenium.type("xpath=(//*[@test-id='unit.amount'])[1]", amount);
        selenium.type("xpath=(//*[@test-id='unit.weight'])[1]", weight);
        Thread.sleep(500); //Задержка
        selenium.click("xpath=(//*[@test-id='order.calc'])");
        Thread.sleep(500);
        for (int second = 0; ; second++) {
            if (second >= 4) {Thread.sleep(3000); selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\Правильная цена "+cost+"руб, произошло в "+timeStamp+".png",""); fail("timeout"); }
            try {
                if (cost==Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) break;
            } catch (Exception e) {

            }
           Thread.sleep(1000);
        }

    }

    public void addMultipleCargo(String l, String w, String h, String weight, String amount) throws InterruptedException {
        counter++;

        selenium.type("xpath=(//*[@test-id='unit.length'])["+counter+"]", l);
        selenium.type("xpath=(//*[@test-id='unit.width'])["+counter+"]", w);
        selenium.type("xpath=(//*[@test-id='unit.height'])["+counter+"]", h);
        selenium.type("xpath=(//*[@test-id='unit.amount'])["+counter+"]", amount);
        selenium.type("xpath=(//*[@test-id='unit.weight'])["+counter+"]", weight);
        selenium.click("xpath=(//*[@test-id='order.calc'])");
        }

    public void assertCost(int cost) throws InterruptedException {
        Thread.sleep(500);
        selenium.click("xpath=(//*[@test-id='order.calc'])");
        Thread.sleep(500);
        for (int second = 0; ; second++) {
            if (second >= 5) { Thread.sleep(3000); selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\Правильная цена " + cost + "руб, произошло в " + timeStamp + ".png", ""); fail("timeout"); }
            try {
                if (cost==Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) break;
            } catch (Exception e) {

            }
            Thread.sleep(1000);
        }

       }

    public void chooseAllPackage(){
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



    private Selenium selenium;
    Date date = new Date();

    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://vozovoz.ru/");
        selenium.start();
    }

    @Test
    public void testVar9SpbMsk() throws Exception {
        selenium.open("/");
        selenium.click("link=Оформление заказа");

        // Ожидание загрузки страницы расчета (ждем появления строки ПЕРЕВОЗКА)
         waitLoad();

        // VAR9 (1-9)
        calculateTest("0.8","0.4","0.4","2.4","1",690);
        // PARAM2
        calculateTest("0.8","0.4","0.4","2.5","1",800);
        // PARAM4
        calculateTest("0.8","0.4","0.4","5","1",940);
        // PARAM3
        calculateTest("0.8","0.4","0.4","4.9","1",800);
        // PARAM5
        calculateTest("0.8","0.4","0.4","9.9","1",940);
        // PARAM6
        calculateTest("0.8","0.4","0.4","10","1",950);
        // PARAM8
        calculateTest("0.8","0.4","0.4","20","1",960);
        // PARAM7
        calculateTest("0.8","0.4","0.4","19.9","1",950);
        // PARAM8
        calculateTest("0.8","0.4","0.4","40","1",960);


        //VAR 0(correspond check)
        selenium.click("xpath=(//*[@test-id='unit.remove'])");
        selenium.click("xpath=(//*[@test-id='correspondence.add'])");
        selenium.click("xpath=(//*[@test-id='order.calc'])");
        Thread.sleep(1000);
        assertCost(550);

        //VAR 1(1)
        selenium.click("xpath=(//*[@test-id='correspondence.remove'])");
        selenium.click("xpath=(//*[@test-id='unit.add'])");
        addFloors();
        chooseAllPackage();
        calculateTest("0.8", "0.4", "0.4", "10", "1", 2640);

        //VAR 1(2)
        calculateTest("0.8","0.4","0.4","39.9","1",4060);

        //VAR 2
               calculateTest("1","0.4","0.4","10","1",4230);

        //VAR 8
        calculateTest("0.8","0.4","0.4","40","1",5110);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //CHANGE TO MSK-SPB using URL//
        selenium.open("/calculate-the-order?calculationId=54fff5cdf2f2862e3c88419e");
        // VAR 3
        Thread.sleep(5000);
        assertCost(4580);


        // VAR 4
        counter=0;
        selenium.click("xpath=(//*[@test-id='packages.remove'])");
        selenium.click("xpath=(//*[@test-id='packages.add'])");
        selenium.click("xpath=(//*[@test-id='packages.box4'])");
        addMultipleCargo("5", "0.4", "0.4", "10","2");
        addMultipleCargo("0.7", "0.6", "0.5", "10","1");
        selenium.click("xpath=(//*[@test-id='order.cost'])");
        assertCost(10920);

        // VAR 7
        selenium.click("xpath=(//*[@test-id='packages.hardPackage'])");
        selenium.click("xpath=(//*[@test-id='packages.extraPackage'])");
        selenium.click("xpath=(//*[@test-id='packages.bubbleFilm'])");
        selenium.click("xpath=(//*[@test-id='packages.box4'])");
        selenium.click("xpath=(//*[@test-id='from.work.hide'])");
        selenium.click("xpath=(//*[@test-id='to.work.hide'])");
        selenium.click("xpath=(//*[@test-id='unit.remove'])");
        selenium.click("xpath=(//*[@test-id='correspondence.remove'])");
        calculateTest("0.4", "0.2", "0.2", "5","3",1420);

        // VAR 9 (10-18)
        selenium.click("xpath=(//*[@test-id='packages.remove'])");
        calculateTest("0.8","0.4","0.4","2.4","1",690);
        // PARAM2
        calculateTest("0.8","0.4","0.4","2.5","1",800);
        // PARAM4
        calculateTest("0.8","0.4","0.4","5","1",940);
        // PARAM3
        calculateTest("0.8","0.4","0.4","4.9","1",800);
        // PARAM5
        calculateTest("0.8","0.4","0.4","9.9","1",940);
        // PARAM6
        calculateTest("0.8","0.4","0.4","10","1",950);
        // PARAM8
        calculateTest("0.8","0.4","0.4","20","1",960);
        // PARAM7
        calculateTest("0.8","0.4","0.4","19.9","1",950);
        // PARAM8
        calculateTest("0.8","0.4","0.4","40","1",960);

        // VAR 6
        selenium.open("/");
        Thread.sleep(2000);
        selenium.click("xpath=(//*[@test-id='minicalculator.cities.change'])");
        selenium.click("xpath=(//*[@test-id='minicalculator.order.create'])");
        waitLoad();
        counter=0;
        addMultipleCargo("0.9", "0.4", "0.4", "35", "4");
        selenium.click("xpath=(//*[@test-id='unit.add'])");
        addMultipleCargo("0.7", "0.5", "0.4", "38","3");
        selenium.click("xpath=(//*[@test-id='packages.add'])");
        selenium.click("xpath=(//*[@test-id='packages.hardPackage'])");
        selenium.click("xpath=(//*[@test-id='packages.extraPackage'])");
        selenium.click("xpath=(//*[@test-id='packages.bubbleFilm'])");
        selenium.click("xpath=(//*[@test-id='packages.box4'])");
        assertCost(2350);

        // ОФОРМЛЕНИЕ
        selenium.click("xpath=(//*[@test-id='order.create'])");
        selenium.type("name=shipperFizFIO", "иванов никита");
        selenium.type("css=div.col-xs-6.consignee > app-calculator-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"shipperFizFIO\"]", "иванов андрей");
        selenium.type("name=shipperFizTel", "+7 (951) 685-32-60");
        selenium.type("css=div.col-xs-6.consignee > app-calculator-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"shipperFizTel\"]", "+7 (951) 685-32-60");

        // ОФОРМИТЬ
        selenium.click("xpath=(//*[@test-id='order.create'])");
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
