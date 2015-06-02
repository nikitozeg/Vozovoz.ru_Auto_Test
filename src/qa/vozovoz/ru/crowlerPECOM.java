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
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class crowlerPECOM {

    int counter = 0;
    File exlFile = new File("C:\\Users\\n.ivanov\\Dropbox\\crowler.xls");
    Workbook w;
    private Selenium selenium;

    public void waitLoad() throws InterruptedException {
        // Thread.sleep(4000);
        //selenium.click("xpath=(//*[@test-id='order.calc'])"); //new
        //  Thread.sleep(1000);
        for (int second = 0; ; second++) {
            if (second >= 8) {
                // selenium.click("xpath=(//*[@test-id='order.calc'])");
                // Thread.sleep(1000);
                selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\Не прогрузилось слово Перевозка " + System.currentTimeMillis() + ".png", "");
                //fail("timeout");
                break;
            }
            try {
                if //(Pattern.compile("(Перевозка)").matcher(selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div")).find())
                        (Pattern.compile("[П]").matcher(selenium.getText("//div[@id='main']/ng-form/div/div[2]/div/div/div/div")).find())
                    break;
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\не найден локатор ПЕРЕВОЗКИ " + System.currentTimeMillis() + ".png", ""); break;
            }
            Thread.sleep(1000);
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
                selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\Правильная цена " + cost + " " + System.currentTimeMillis() + ".png", "");
                selenium.click("css=div.header-logo.header-logo_img");
                break;
            }
            try {
                if (cost == Integer.parseInt(selenium.getText("xpath=(//*[@test-id='order.cost'])"))) {
                    break;
                }
            } catch (Exception e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\not found order.calc произошло в  " + System.currentTimeMillis() + ".png", "");
                System.out.print(e.toString());
            }
            Thread.sleep(3000);
        }
    }

    public void setCities(String sender, String receiver) throws InterruptedException {
        Thread.sleep(2000);
        try {
            selenium.type("name=town_0", sender);
            Thread.sleep(1000);
            selenium.type("name=town_1", receiver);
            Thread.sleep(1000);
            selenium.click("xpath=(//*[@test-id='minicalculator.order.create'])");
            waitLoad();
        }
        catch (SeleniumException e){
            e.printStackTrace();
            selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\Select Cities is failed" + " " + System.currentTimeMillis() + ".png", "");
            throw new SeleniumException(e);
        }


    }

    public int SetParams(int row) throws IOException {


        int cost = 0;
        try {
            w = Workbook.getWorkbook(exlFile);
            Sheet sheet = w.getSheet(0);

            Cell cell = sheet.getCell(1, row);
            String from = cell.getContents().toString();

            cell = sheet.getCell(2, row);
            String to = cell.getContents().toString();

           //// cell = sheet.getCell(27, row);
          //  cost = Integer.parseInt(cell.getContents());

            //Thread.sleep(1000);
            try {
                selenium.type("name=town_0", from);
                if (from.equals("Воронеж")) selenium.click("link=Воронеж");
                if (from.equals("Новосибирск")) selenium.click("link=Новосибирск");
                if (from.equals("Саратов")) selenium.click("link=Саратов");
                selenium.type("name=town_1", to);
                if (to.equals("Воронеж")) selenium.click("link=Воронеж");
                if (to.equals("Новосибирск")) selenium.click("link=Новосибирск");
                if (to.equals("Саратов")) selenium.click("link=Саратов");
//                waitLoad();
            }
            catch (SeleniumException e){
                e.printStackTrace();
                selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\Select Cities is failed" + " " + System.currentTimeMillis() + ".png", "");
            }

             cell = sheet.getCell(8, row); //ves
             selenium.type("xpath=(//input[@name=''])[5]", cell.getContents().toString().replaceAll(",", ".")); //setted ves


          //  Thread.sleep(2000);
        } catch (BiffException e) {
            e.printStackTrace();
            selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\BiffException" + " " + System.currentTimeMillis() + ".png", "");
        }
        catch (SeleniumException e){
            selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\SeleniumException on row " + row +" "+ System.currentTimeMillis() + ".png", "");
        }
        return cost;
    }


    public void browserReload() throws Exception {
        selenium.close();
        selenium.stop();
        Thread.sleep(5000);
        setUp();
    }

    @Before
    public void setUp() throws Exception {
        crowlerPECOM test = new crowlerPECOM();
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://pecom.ru");
        selenium.start();
    }

    @Test
    public void testVar9SpbMsk() throws Exception {
        selenium.open("/ru/calc/");
        Thread.sleep(2000);
        File crowlerResult = new File("C:\\Users\\n.ivanov\\Dropbox\\crowlerResult.xls");
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(crowlerResult);

        WritableSheet writableSheet = writableWorkbook.createSheet("Sheet2", 0);
       // Label label
      //  Number num
            try {
                System.out.println("start");
                selenium.click("id=strah");
                selenium.type("xpath=(//input[@name=''])[1]", "0.8"); //d
                selenium.type("xpath=(//input[@name=''])[2]", "0.4"); //sh
                selenium.type("xpath=(//input[@name=''])[3]", "0.4"); //v
                selenium.type("xpath=(//input[@name=''])[4]", "0.13"); //o

                for ( int i=1; i<1680; i++){
                    try{

                      SetParams(i);

                       // Thread.sleep(500);
                        selenium.click("id=result");
                        Thread.sleep(1500);

                        for (int second = 0; i<12 ; second++) {
                            if (second >= 10) {
                                selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\цена не загрузилась за 10 сек " + System.currentTimeMillis() + ".png", "");
                                break;
                            }
                            try {
                                selenium.isElementPresent("//div[@id='result-wrap']/div/div/div/table/tbody/tr[5]/th"); break;
                            } catch (Exception e) {
                                selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\глюк " + System.currentTimeMillis() + ".png", "");
                            }
                           // Thread.sleep(1000);
                        }

                        String ok=selenium.getText("//div[@id='result-wrap']/div/div/div/table/tbody/tr[2]/td[2]");
                        String zabor=selenium.getText("//div[@id='result-wrap']/div/div/div/table/tbody/tr[3]/td[3]");
                        String dostavka=selenium.getText("//div[@id='result-wrap']/div/div/div/table/tbody/tr[4]/td[3]");
                        String cena=selenium.getText("css=h5");

                        System.out.println(i);
                       // System.out.print("  Забор " + zabor);
                       // System.out.println(" Доставка " + dostavka);

                        Label label1 = new Label(0, i, ok);
                        Label label2 = new Label(1, i, zabor);
                        Label label3 = new Label(2, i, dostavka);
                        Label label4 = new Label(3, i, cena);

                        writableSheet.addCell(label1);//откуда-куда
                        writableSheet.addCell(label2); //забор
                        writableSheet.addCell(label3); //доставка
                        writableSheet.addCell(label4); //цена

                    }

                    catch(SeleniumException e) {
                        selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\чтото не нашлось  " + i + " " +System.currentTimeMillis() + ".png", "");
                      //  selenium.click("css=div.header-logo.header-logo_img");
                    }finally {

                       // browserReload();
                       // Thread.sleep(5000);


                    }

                }


                System.out.print("is done");

             //   browserReload();
                Thread.sleep(15000);
                System.out.println("Cycled is over");
            } catch (SeleniumException e) {
                selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\Some test FAILed " + System.currentTimeMillis() + ".png", "");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
        finally{
                writableWorkbook.write();
                writableWorkbook.close();
            }
        }


    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
