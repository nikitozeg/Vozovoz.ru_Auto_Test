package ru.vozovoz;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class MTSChecker {

    private Selenium selenium;

    public void browserReload() throws Exception {
        selenium.close();
        selenium.stop();
        Thread.sleep(5000);
        setUp();
        selenium.open("http://www.mcommunicator.ru/");
        Thread.sleep(5000);
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
        selenium.open("http://www.mcommunicator.ru/");
        Thread.sleep(3000);
        int i = 0;
        int ochered = 0;

        selenium.type("name=login", "79516853260");
        selenium.type("name=password", "461189");
        selenium.click("css=input[type=\"image\"]}");
        Thread.sleep(5000);

        while (true) {
            try {
                i++;
                selenium.refresh();
                Thread.sleep(7000);
                selenium.click("link=Показать ещё");
                Thread.sleep(15000);

                if (selenium.isTextPresent("очереди") || selenium.isTextPresent("Ошибка") || selenium.isTextPresent("шибка") ) {
                    ochered ++;
                } else ochered = 0;

                if (ochered>1) {
                    selenium.captureEntirePageScreenshot("C:\\Users\\adm\\Dropbox\\MTS\\Статус очередь или ошибка дольше 35 секунд " + System.currentTimeMillis() + ".png", "");
                }

                Thread.sleep(6000);

                if (i == 10) {
                    browserReload();
                    Thread.sleep(3000);
                    selenium.type("name=login", "79516853260");
                    selenium.type("name=password", "461189");
                    selenium.click("css=input[type=\"image\"]}");
                    Thread.sleep(5000);
                    System.out.print("reload done");
                    i = 0;
                }

            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogMTS\\Some test SITECHECKER FAILed " + System.currentTimeMillis() + ".png", "");
                System.out.println(e.getMessage());
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
