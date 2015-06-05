package qa.vozovoz.ru.crowler;
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

public class crowlerEnergia {

    int counter = 0;
    File exlFile = new File("C:\\Users\\n.ivanov\\Dropbox\\crowler.xls");
    Workbook w;
    private Selenium selenium;
    String type;
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
                selenium.select("id=cityFrom", "label="+from);
                selenium.select("id=cityTo", "label="+to);
            }
            catch (SeleniumException e){
                e.printStackTrace();
                selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\Select Cities is failed" + " " + System.currentTimeMillis() + ".png", "");
            }

            cell = sheet.getCell(8, row); //ves

            selenium.type("id=weight", cell.getContents().toString().replaceAll(",", ".")); //setted ves


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
       // Thread.sleep(5000);
        setUp();

    }
    @Before
    public void setUp() throws Exception {
        crowlerEnergia test = new crowlerEnergia();
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://nrg-tk.ru/client");
        selenium.start();
    }

    @Test
    public void testVar9SpbMsk() throws Exception {
        selenium.open("/calculator.html");
        Thread.sleep(3000);
        File crowlerResult = new File("C:\\Users\\n.ivanov\\Dropbox\\crowlerResult.xls");
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(crowlerResult);

        WritableSheet writableSheet = writableWorkbook.createSheet("Sheet2", 0);
        String cena="";
        try {
            System.out.println("start");
            selenium.type("id=size-length", "0.8"); //d
            selenium.type("id=size-width", "0.4"); //sh
            selenium.type("id=size-height", "0.4"); //v
            selenium.type("id=volume", "0.13"); //o
            String ves="";
            int count=0;
            Thread.sleep(1000);
            for ( int i=1081; i<1095; i++){
                try{
                    if (count==3) {browserReload(); /*Thread.sleep(6000);*/selenium.open("/calculator.html");  /* Thread.sleep(3000);*/ selenium.type("id=size-length", "0.8"); //d
                        selenium.type("id=size-width", "0.4"); //sh
                        selenium.type("id=size-height", "0.4"); //v
                        selenium.type("id=volume", "0.13"); count=0;};
                    count++;
                    SetParams(i);

                    selenium.click("css=button.btn.btn-primary");
                    selenium.waitForPageToLoad("20000");

                    if (selenium.isTextPresent("Авиа")||selenium.isTextPresent("ЖД")) {

                        if(selenium.getText( "//div[@id='all']/div/div[2]/aside/section/table/thead/tr/th[2]").equals("Авто")){cena =selenium.getText( "//div[@id='all']/div/div[2]/aside/section/table/tbody/tr/td[2]");type=selenium.getText("//div[@id='all']/div/div[2]/aside/section/table/thead/tr/th[2]}");}
                        else  if(selenium.getText( "//div[@id='all']/div/div[2]/aside/section/table/thead/tr/th[3]").equals("Авто")){cena =selenium.getText( "//div[@id='all']/div/div[2]/aside/section/table/tbody/tr/td[3]");type=selenium.getText("//div[@id='all']/div/div[2]/aside/section/table/thead/tr/th[3]");}
                                else {cena =selenium.getText("//div[@id='all']/div/div[2]/aside/section/table/tbody/tr/td[4]");type=selenium.getText("//div[@id='all']/div/div[2]/aside/section/table/thead/tr/th[4]");}
                    }
                    else {
                        cena =selenium.getText("//div[@id='all']/div/div[2]/aside/section/table/tbody/tr/td[2]");
                        type=selenium.getText("//div[@id='all']/div/div[2]/aside/section/table/thead/tr/th[2]");
                    }


                    String ok=selenium.getText("css=td");
                    String zabor=selenium.getText("//div[@id='all']/div/div[2]/aside/section/table/tbody/tr[2]/td[2]");
                    String dostavka=selenium.getText("//div[@id='all']/div/div[2]/aside/section/table/tbody/tr[3]/td[2]");

                    ves=selenium.getValue("id=weight");

                    System.out.println(i);
                    // System.out.print("  Забор " + zabor);
                    // System.out.println(" Доставка " + dostavka);

                    Label label1 = new Label(0, i, ok);
                    Label label2 = new Label(1, i, zabor);
                    Label label3 = new Label(2, i, dostavka);
                    Label label4 = new Label(3, i, cena);
                    Label label5 = new Label(4, i, ves);
                    Label label6 = new Label(5, i, type);
                    writableSheet.addCell(label1);//откуда-куда
                    writableSheet.addCell(label2); //забор
                    writableSheet.addCell(label3); //доставка
                    writableSheet.addCell(label4); //цена
                    writableSheet.addCell(label5); //ves
                    writableSheet.addCell(label6);
                    Thread.sleep(1000);
                }

                catch(SeleniumException e) {
                    selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\чтото не нашлось  " + i + " " +System.currentTimeMillis() + ".png", "");
                }
            }

            System.out.print("is done");
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
