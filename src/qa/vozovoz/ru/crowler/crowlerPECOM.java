package qa.vozovoz.ru.crowler;

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
import java.util.regex.Pattern;

public class crowlerPECOM {

    int counter,sum = 0, number=0;
    File exlFile = new File("C:\\Users\\n.ivanov\\Dropbox\\request\\PecPrice12\\input2.xls");
    Workbook w;
    private Selenium selenium;
    //  Double volume;
    String weight, volume, length, height, width;

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


    public int SetParams(int row) throws Exception {


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
                selenium.type("name=town_0", "Уфа");
              /*  if (from.equals("Воронеж")) selenium.click("link=Воронеж");
                if (from.equals("Новосибирск")) selenium.click("link=Новосибирск");
                if (from.equals("Саратов")) selenium.click("link=Саратов");
                if (from.equals("Архангельск")) selenium.click("link=Архангельск");
                if (from.equals("Вельск")) selenium.click("link=Вельск (Вельский р-н)");
                if (from.equals("Кандалакша")) selenium.click("link=Кандалакша (Кандалакшский р-н)");
                if (from.equals("Киров")) selenium.click("link=Киров");
                if (from.equals("Благовещенск")) selenium.click("link=Благовещенск");
                if (from.equals("Большое Козино")) selenium.click("link=Большое Козино (Балахнинский р-н)");
                if (from.equals("Волжск")) selenium.click("link=Волжск");
                if (from.equals("Жуково")) selenium.click("link=Жуково");
                if (from.equals("Зубово")) selenium.click("link=Зубово");
                if (from.equals("Ковров")) selenium.click("link=Ковров");
                if (from.equals("Кольцово")) selenium.click("link=Кольцово");
                if (from.equals("Кузнецк")) selenium.click("link=Кузнецк");
                if (from.equals("Муром")) selenium.click("link=Муром");
                if (from.equals("Радужный")) selenium.click("link=Радужный (Московская обл.) (Коломна)");
                if (from.equals("Тара")) selenium.click("link=Тара");
                if (from.equals("Толмачево")) selenium.click("link=Толмачево");
*/

                selenium.type("name=town_1", to);
                if (to.equals("Воронеж")) selenium.click("link=Воронеж");
                if (to.equals("Новосибирск")) selenium.click("link=Новосибирск");
                if (to.equals("Саратов")) selenium.click("link=Саратов");
                if (to.equals("Архангельск")) selenium.click("link=Архангельск");
                if (to.equals("Вельск")) selenium.click("link=Вельск (Вельский р-н)");
                if (to.equals("Кандалакша")) selenium.click("link=Кандалакша (Кандалакшский р-н)");
                if (to.equals("Киров")) selenium.click("link=Киров");
                if (to.equals("Благовещенск")) selenium.click("link=Благовещенск");
                if (to.equals("Большое Козино")) selenium.click("link=Большое Козино (Балахнинский р-н)");
                if (to.equals("Волжск")) selenium.click("link=Волжск");
                if (to.equals("Жуково")) selenium.click("link=Жуково");
                if (to.equals("Зубово")) selenium.click("link=Зубово");
                if (to.equals("Ковров")) selenium.click("link=Ковров");
                if (to.equals("Кольцово")) selenium.click("link=Кольцово");
                if (to.equals("Кузнецк")) selenium.click("link=Кузнецк");
                if (to.equals("Муром")) selenium.click("link=Муром");
                if (to.equals("Радужный")) selenium.click("link=Радужный (Московская обл.) (Коломна)");
                if (to.equals("Тара")) selenium.click("link=Тара");
                if (to.equals("Толмачево")) selenium.click("link=Толмачево");
                if (to.equals("Мармылево")) throw new Exception();
                if (to.equals("Вавилово")) throw new Exception();
                if (to.equals("Возрождение")) throw new Exception();
                if (to.equals("Габишево")) throw new Exception();
                if (to.equals("Амерево")) throw new Exception();
                if (to.equals("Миловка")) throw new Exception();
                if (to.equals("Новое Бобренево")) throw new Exception();
                if (to.equals("Одесское")) throw new Exception();
                if (to.equals("Пестриково")) throw new Exception();
                if (to.equals("Санино")) throw new Exception();
                if (to.equals("Сергиевский")) throw new Exception();
                if (to.equals("Старое Бобренево")) throw new Exception();
                if (to.equals("Степанщино")) throw new Exception();
                if (to.equals("Щербакуль")) throw new Exception();
                if (to.equals("Нагаево")) throw new Exception();
                if (to.equals("Барышево")) throw new Exception();
                if (to.equals("Усинск")) throw new Exception();
                if (to.equals("Ревда")) selenium.click("link=Ревда");
//                waitLoad();
            }
            catch (SeleniumException e){
                //  e.printStackTrace();
                // selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\Select Cities is failed" + " " + System.currentTimeMillis() + ".png", "");
            }

            cell = sheet.getCell(5, row); //dlina
            length=cell.getContents().toString().replaceAll(",", ".");
            selenium.type("xpath=(//input[@name=''])[1]", cell.getContents().toString().replaceAll(",", ".")); //setted ves

            cell = sheet.getCell(6, row); //ves
            width=cell.getContents().toString().replaceAll(",", ".");
            selenium.type("xpath=(//input[@name=''])[2]", cell.getContents().toString().replaceAll(",", ".")); //setted ves

            cell = sheet.getCell(7, row); //ves
            height=cell.getContents().toString().replaceAll(",", ".");
            selenium.type("xpath=(//input[@name=''])[3]", cell.getContents().toString().replaceAll(",", ".")); //setted ves

            cell = sheet.getCell(10, row); //ves
            weight=cell.getContents().toString().replaceAll(",", ".");
            selenium.type("xpath=(//input[@name=''])[5]", cell.getContents().toString().replaceAll(",", ".")); //setted ves

            cell = sheet.getCell(11, row); //volume
            //  System.out.print(cell.getContents().toString().replaceAll(",", "."));
            volume = cell.getContents().toString().replaceAll(",", ".");
            selenium.type("xpath=(//input[@name=''])[4]", volume.toString()); //o
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

    public String setValues(String s) throws Exception {
        String cost="";

        for(int i =2;i<5;i++){
            String valueOfTextTable = selenium.getText("//div[@id='result-wrap']/div/div/div/table/tbody/tr["+i+"]/td");
          //  System.out.println(valueOfTextTable);
            if (valueOfTextTable.contains(s)) {cost= selenium.getText("//div[@id='result-wrap']/div/div/div/table/tbody/tr["+i+"]/td[3]"); break;}

            if (i==4) {//selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\нчиего не определено " + System.currentTimeMillis() + ".png", "");
                cost="0";
        }}


        return cost;
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
        File crowlerResult = new File("C:\\Users\\n.ivanov\\Dropbox\\request\\PecPrice12\\PECOM.xls");
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(crowlerResult);

        WritableSheet writableSheet = writableWorkbook.createSheet("Sheet2", 0);
        // Label label
        //  Number num
        try {
            Label label01 = new Label(1, 0, "Забор");
            Label label02 = new Label(2, 0, "Доставка");
            Label label03 = new Label(3, 0, "Сумма");
            Label label04 = new Label(4, 0, "Объем");
            Label label05 = new Label(5, 0, "Вес");

            writableSheet.addCell(label01);
            writableSheet.addCell(label02);
            writableSheet.addCell(label03);
            writableSheet.addCell(label04);
            writableSheet.addCell(label05);

            System.out.println("start");
            selenium.click("id=strah");



            for ( int i=1; i<462; i++){//3650
                try{
                    SetParams(i);

                    selenium.click("id=result");

                    for (int second = 0; second<10 ; second++) {
                        if (second >= 10) {
                            selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\цена не загрузилась за 10 сек " + System.currentTimeMillis() + ".png", "");
                            break;
                        }
                        try {
                            if(selenium.isTextPresent("ИТОГО"))  break;
                        } catch (Exception e) {
                            selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\глюк " + System.currentTimeMillis() + ".png", "");
                        }
                        Thread.sleep(500);
                    }
                    //


                    String ok=setValues("перевозк");
                    String zabor=setValues("доставк");
                    String dostavka=setValues("забор");

                    //System.out.print("  перевозка " + ok);

                    // System.out.print("  Забор " + zabor);
                    // System.out.println(" Доставка " + dostavka);

                    Label label1 = new Label(0, i, ok);
                    Label label2 = new Label(1, i, zabor);
                    Label label3 = new Label(2, i, dostavka);
                    Label label4 = new Label(3, i, selenium.getText("css=h5"));
                    Label label5 = new Label(4, i, volume.toString());
                    Label label6 = new Label(5, i, weight);
                    Label label7 = new Label(6, i, length);
                    Label label8 = new Label(7, i, width);
                    Label label9 = new Label(8, i, height);
                    Label label10 = new Label(9, i,  selenium.getValue("name=town_0"));
                    Label label11 = new Label(10, i, selenium.getValue("name=town_1"));
                    Label label12 = new Label(12, i, selenium.getText("//div[@id='page-wrapper']/div/div[2]/div/div/h3[3]/div/div/div/table/tbody/tr[3]/td[2]"));
                    Label label13 = new Label(13, i, selenium.getText("//div[@id='page-wrapper']/div/div[2]/div/div/h3[3]/div/div/div/table/tbody/tr[7]/td[2]"));

                    writableSheet.addCell(label1);//откуда-куда
                    writableSheet.addCell(label2); //забор
                    writableSheet.addCell(label3); //доставка
                    writableSheet.addCell(label4); //цена
                    writableSheet.addCell(label5); //объем
                    writableSheet.addCell(label6); //ves
                    writableSheet.addCell(label7); //цена
                    writableSheet.addCell(label8); //объем
                    writableSheet.addCell(label9); //ves
                    writableSheet.addCell(label10);
                    writableSheet.addCell(label11);
                    writableSheet.addCell(label12);
                    writableSheet.addCell(label13);
                    System.out.println(i);
                }
                catch(Exception e) { System.out.println(i);
                   // selenium.captureEntirePageScreenshot("C:\\errorlogCrowler\\чтото не нашлось  " + i + " " +System.currentTimeMillis() + ".png", "");
                    //  selenium.click("css=div.header-logo.header-logo_img");
                } finally {

                    // browserReload();
                    // Thread.sleep(5000);


                }

            }


            System.out.print("is done");

            //   browserReload();
            Thread.sleep(5000);
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
