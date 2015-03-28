package qa.vozovoz.ru;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;

public class AllCitiesCheckOut  {

    String timeStamp = new SimpleDateFormat("HH.mm дата dd.MM").format(Calendar.getInstance().getTime());
    int counter = 0;

    public void login() throws InterruptedException {
        // ОФОРМЛЕНИЕ
        selenium.open("/");
        for (int second = 0; ; second++) {
            if (second >= 10) fail("timeout");
            try {
                if (selenium.isElementPresent("css=button.btn")) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        selenium.click("link=Личный кабинет");
        for (int second = 0; ; second++) {
            if (second >= 10) fail("timeout");
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
            if (second >= 10) fail("timeout");
            try {
                if ("Мои данные".equals(selenium.getText("link=Мои данные"))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

    }
    public void waitLoad() throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 10) {
                selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\waitload fail " + timeStamp + ".png", "");
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
    public void assertAndCheckOut(String l, String w, String h, String weight, String amount, int cost) throws InterruptedException {
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
            selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\unit filling is fail " + cost + "руб, произошло в " + timeStamp + ".png", "");
        }

        for (int second = 0; ; second++) {
            if (second >= 4) {
                selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\Правильная цена " + cost + "руб, произошло в " + timeStamp + ".png", "");
                break;
            }
            try {
                if (cost == Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) {
                    checkOut();
                    break;
                }
            } catch (Exception e) {
                selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\Правильная цена " + cost + "руб, произошло в " + timeStamp + ".png", "");
            }
            Thread.sleep(6000);
        }

    }

    public void checkOut() throws InterruptedException {
        // try {selenium.click("link=Личный кабинет"); login();}
        //    catch(SeleniumException e) {}
        // ОФОРМЛЕНИЕ
        try {
            selenium.click("xpath=(//*[@test-id='order.create'])");
        } catch (SeleniumException e) {
            Thread.sleep(1000);
            selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\click on orderCreate is fail " + timeStamp + ".png", "");
            System.out.print(e.toString());
        } finally {
            Thread.sleep(5000);
            selenium.click("xpath=(//*[@test-id='order.backward'])");
        }
    }

    public void setContacts() throws InterruptedException {
        selenium.type("name=shipperFizFIO", "иванов никита");
        selenium.type("css=div.col-xs-6.consignee > app-calculator-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"shipperFizFIO\"]", "иванов андрей");
        selenium.type("name=shipperFizTel", "+7 (951) 685-32-60");
        selenium.type("css=div.col-xs-6.consignee > app-calculator-profile.ng-isolate-scope > div.calculator-profile > div.fzFields.ng-scope > div.form-group > input[name=\"shipperFizTel\"]", "+7 (991) 001-26-55");

    }

    public void setCities(String sender, String receiver) throws InterruptedException {
        selenium.select("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div/div/div/select", "label="+sender);
        selenium.select("//div[@id='main']/div/section/div/div/div/div[2]/div/div/div/div/app-homepage-calculator/div/form/div[2]/div/div/select", "label=" + receiver);
        selenium.click("css=div.count > button.btn");
        waitLoad();
        selenium.click("xpath=(//*[@test-id='to.use.address'])");
        selenium.click("xpath=(//*[@test-id='from.use.address'])");
    }


    private Selenium selenium;

    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://vozovoz.ru/");
        selenium.start();
    }

    @Test
    public void testVar9SpbMsk() throws Exception {
        // getOrderNumber();
        login();
        ArrayList<String> listOfCities = new ArrayList<String>();
        listOfCities.add("Москва");
        listOfCities.add("Санкт-Петербург");
        listOfCities.add("Волгоград");
        listOfCities.add("Воронеж");
        listOfCities.add("Краснодар");
        listOfCities.add("Ростов-на-Дону");
        listOfCities.add("Самара");
        listOfCities.add("Ставрополь");

        while (true) {
          try {
              selenium.open("/");


              for(int i=0; i<=8; i++) {
                  for (int j = 0; j <= 8; j++) {
                      if (!(i == j)){
                      setCities(listOfCities.get(i), listOfCities.get(j));
                          selenium.click("xpath=(//*[@test-id='unit.remove'])");
                          selenium.click("xpath=(//*[@test-id='correspondence.add'])");
                          selenium.click("xpath=(//*[@test-id='order.calc'])");

                      }
                  }
              }





          } catch (SeleniumException e){
              selenium.captureEntirePageScreenshot("Y:\\Отдел IT\\Тестирование\\Лог ошибок ежечасного тестирования калькулятора\\Cities checking is FAIL.png", "");
          }
        }
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
