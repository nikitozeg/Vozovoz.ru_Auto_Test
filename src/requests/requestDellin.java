package requests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;

public class requestDellin {
    File exlFile = new File("C:\\Users\\n.ivanov\\Dropbox\\request\\78109\\input220volt2.xls");
    Workbook w;
    String fromCode, toCode, insuranceResponse, intercity;
    Double weight,volume, insurance;
    int count;
    private final String USER_AGENT = "Mozilla/5.0";
  /*  public  SetParams(int row) throws IOException {


      return;
    }
*/

    public static void main(String[] args) throws Exception {
        requestDellin http = new requestDellin();
        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();

    }

    private void sendGet() throws Exception {
        String summa, derival, priceFrom, fromm, priceTO = "";
        File crowlerResult = new File("C:\\Users\\n.ivanov\\Dropbox\\request\\78109\\output220volt.xls");
        w = Workbook.getWorkbook(exlFile);
        Sheet sheet = w.getSheet(0);
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(crowlerResult);
        WritableSheet writableSheet = writableWorkbook.createSheet("Sheet2", 0);
        Label label00 = new Label(2, 0, "МТ+Забор+Отвоз");
        Label label01 = new Label(3, 0, "Забор");
        Label label02 = new Label(4, 0, "Отвоз");
        Label label03 = new Label(4, 0, "Отвоз");
        Label label04 = new Label(6, 0, "Вес");
        writableSheet.addCell(label01);
        writableSheet.addCell(label02);
        writableSheet.addCell(label00);
        writableSheet.addCell(label03);
        writableSheet.addCell(label04);

        try {

            String to = ""; String from = "";

            for (int i = 1; i < 2585; i++) {//2585
                try {
                    weight=0.0;
                    volume=0.0;
                    insurance=0.0;
                    insuranceResponse="";
                    intercity="";
                    System.out.println(i);
                    Cell cell = sheet.getCell(1, i);
                     from = cell.getContents();
                    if (from.equalsIgnoreCase("МОСКВА")) fromCode = "7700000000000000000000000";
                    if (from.equalsIgnoreCase("Москва")) fromCode = "7700000000000000000000000";
                    if (from.equalsIgnoreCase("Волгоград")) fromCode = "3400000100000000000000000";
                    if (from.equalsIgnoreCase("Воронеж")) fromCode = "3600000100000000000000000";
                    if (from.equalsIgnoreCase("Екатеринбург")) fromCode = "6600000100000000000000000";
                    if (from.equalsIgnoreCase("Казань")) fromCode = "1600000100000000000000000";
                    if (from.equalsIgnoreCase("Краснодар")) fromCode = "2300000100000000000000000";
                    if (from.equalsIgnoreCase("Нижний Новгород")) fromCode = "5200000100000000000000000";
                    if (from.equalsIgnoreCase("Н.Новгород")) fromCode = "5200000100000000000000000";
                    if (from.equalsIgnoreCase("НОВОСИБИРСК")) fromCode = "5400000100000000000000000";
                    if (from.equalsIgnoreCase("Новосибирск")) fromCode = "5400000100000000000000000";
                    if (from.equalsIgnoreCase("Омск")) fromCode = "5500000100000000000000000";
                    if (from.equalsIgnoreCase("Ростов-на-Дону")) fromCode = "6100000100000000000000000";
                    if (from.equalsIgnoreCase("Самара")) fromCode = "6300000100000000000000000";
                    if (from.equalsIgnoreCase("Санкт-Петербург")) fromCode = "7800000000000000000000000";
                    if (from.equalsIgnoreCase("Саратов")) fromCode = "6400000100000000000000000";
                    if (from.equalsIgnoreCase("Ставрополь")) fromCode = "2600000100000000000000000";
                    if (from.equalsIgnoreCase("Уфа")) fromCode = "200000100000000000000000";
                    if (from.equalsIgnoreCase("Челябинск")) fromCode = "7400000100000000000000000";
                    if (from.equalsIgnoreCase("Апатиты")) fromCode = "5100000200000000000000000";
                    if (from.equalsIgnoreCase("Архангельск")) fromCode = "2900000100000000000000000";
                    if (from.equalsIgnoreCase("Вельск")) fromCode = "2900200100000000000000000";
                    if (from.equalsIgnoreCase("Вологда")) fromCode = "3500000100000000000000000";
                    if (from.equalsIgnoreCase("Евпатория")) fromCode = "9100000900000000000000000";
                    if (from.equalsIgnoreCase("Калининград")) fromCode = "3900000100000000000000000";
                    if (from.equalsIgnoreCase("Кандалакша")) fromCode = "5100000400000000000000000";
                    if (from.equalsIgnoreCase("Керчь")) fromCode = "9100000100000000000000000";
                    if (from.equalsIgnoreCase("Киров")) fromCode = "4300000100000000000000000";
                    if (from.equalsIgnoreCase("Котлас")) fromCode = "2900800100000000000000000";
                    if (from.equalsIgnoreCase("Мончегорск")) fromCode = "5100000600000000000000000";
                    if (from.equalsIgnoreCase("Мурманск")) fromCode = "5100000100000000000000000";
                    if (from.equalsIgnoreCase("Набережные Челны")) fromCode = "1600000200000000000000000";
                    if (from.equalsIgnoreCase("Пенза")) fromCode = "5800000100000000000000000";
                    if (from.equalsIgnoreCase("Пермь")) fromCode = "5900000100000000000000000";
                    if (from.equalsIgnoreCase("Петрозаводск")) fromCode = "1000000100000000000000000";
                    if (from.equalsIgnoreCase("Севастополь")) fromCode = "9200000000000000000000000";
                    if (from.equalsIgnoreCase("Симферополь")) fromCode = "9100000700000000000000000";
                    if (from.equalsIgnoreCase("Сыктывкар")) fromCode = "1100000100000000000000000";
                    if (from.equalsIgnoreCase("Ухта")) fromCode = "1100000800000000000000000";
                    if (from.equalsIgnoreCase("Ялта")) fromCode = "9100000800000000000000000";
                    if (from.equalsIgnoreCase("Череповец")) fromCode = "3500000200000000000000000";
                    if (from.equalsIgnoreCase("Феодосия")) fromCode = "9100001000000000000000000";

                    if (from.equalsIgnoreCase("Белгород")) fromCode = "3100000100000000000000000";
                    if (from.equalsIgnoreCase("Великий Новгород")) fromCode = "5300000100000000000000000";
                    if (from.equalsIgnoreCase("Смоленск")) fromCode = "6700000300000000000000000";
                    if (from.equalsIgnoreCase("Тольятти")) fromCode = "6300000700000000000000000";
                    if (from.equalsIgnoreCase("Тула")) fromCode = "7100000100000000000000000";
                    if (from.equalsIgnoreCase("Ярославль")) fromCode = "7600000100000000000000000";
                    if (from.equalsIgnoreCase("Анжеро-Судженск")) fromCode = "4200000200000000000000000";
                    if (from.equalsIgnoreCase("Астрахань")) fromCode = "3000000100000000000000000";
                    if (from.equalsIgnoreCase("Барнаул")) fromCode = "2200000100000000000000000";
                    if (from.equalsIgnoreCase("Батайск")) fromCode = "6100000300000000000000000";
                    if (from.equalsIgnoreCase("Бердск")) fromCode = "5400000200000000000000000";
                    if (from.equalsIgnoreCase("Бийск")) fromCode = "2200000400000000000000000";
                    if (from.equalsIgnoreCase("Виллози")) fromCode = "4701200000400000000000000";
                    if (from.equalsIgnoreCase("Кемерово")) fromCode = "4200000900000000000000000";
                    if (from.equalsIgnoreCase("Красноярск")) fromCode = "2400000100000000000000000";
                    if (from.equalsIgnoreCase("Ленинск-Кузнецкий")) fromCode = "4200001000000000000000000";
                    if (from.equalsIgnoreCase("МО")) fromCode = "5000000000000000000000000";
                    if (from.equalsIgnoreCase("Красноярск")) fromCode = "2400000100000000000000000";
                    if (from.equalsIgnoreCase("Новокузнецк")) fromCode = "4200001200000000000000000";
                    if (from.equalsIgnoreCase("Томск")) fromCode = "7000000100000000000000000";
                    if (from.equalsIgnoreCase("Ульяновск")) fromCode = "7300000100000000000000000";
                    if (from.equalsIgnoreCase("Красноярск")) fromCode = "2400000100000000000000000";
                    if (from.equalsIgnoreCase("Залесье")) fromCode =    "5002800007500000000000000";

                   // System.out.print(from);
                    cell = sheet.getCell(2, i);
                     to = cell.getContents();
                  //  System.out.print(toCode);
                    if (to.equalsIgnoreCase("МОСКВА")) toCode = "7700000000000000000000000";
                    if (to.equalsIgnoreCase("Москва")) toCode = "7700000000000000000000000";
                    if (to.equalsIgnoreCase("Волгоград")) toCode = "3400000100000000000000000";
                    if (to.equalsIgnoreCase("Воронеж")) toCode = "3600000100000000000000000";
                    if (to.equalsIgnoreCase("Екатеринбург")) toCode = "6600000100000000000000000";
                    if (to.equalsIgnoreCase("Казань")) toCode = "1600000100000000000000000";
                    if (to.equalsIgnoreCase("Краснодар")) toCode = "2300000100000000000000000";
                    if (to.equalsIgnoreCase("Нижний Новгород")) toCode = "5200000100000000000000000";
                    if (to.equalsIgnoreCase("Н.Новгород")) toCode = "5200000100000000000000000";
                    if (to.equalsIgnoreCase("НОВОСИБИРСК")) toCode = "5400000100000000000000000";
                    if (to.equalsIgnoreCase("Новосибирск")) toCode = "5400000100000000000000000";
                    if (to.equalsIgnoreCase("Омск")) toCode = "5500000100000000000000000";
                    if (to.equalsIgnoreCase("Ростов-на-Дону")) toCode = "6100000100000000000000000";
                    if (to.equalsIgnoreCase("Самара")) toCode = "6300000100000000000000000";
                    if (to.equalsIgnoreCase("Санкт-Петербург")) toCode = "7800000000000000000000000";
                    if (to.equalsIgnoreCase("Саратов")) toCode = "6400000100000000000000000";
                    if (to.equalsIgnoreCase("Ставрополь")) toCode = "2600000100000000000000000";
                    if (to.equalsIgnoreCase("Уфа")) toCode = "200000100000000000000000";
                    if (to.equalsIgnoreCase("Челябинск")) toCode = "7400000100000000000000000";
                    if (to.equalsIgnoreCase("Апатиты")) toCode = "5100000200000000000000000";
                    if (to.equalsIgnoreCase("Архангельск")) toCode = "2900000100000000000000000";
                    if (to.equalsIgnoreCase("Вельск")) toCode = "2900200100000000000000000";
                    if (to.equalsIgnoreCase("Вологда")) toCode = "3500000100000000000000000";
                    if (to.equalsIgnoreCase("Евпатория")) toCode = "9100000900000000000000000";
                    if (to.equalsIgnoreCase("Калининград")) toCode = "3900000100000000000000000";
                    if (to.equalsIgnoreCase("Кандалакша")) toCode = "5100000400000000000000000";
                    if (to.equalsIgnoreCase("Керчь")) toCode = "9100000100000000000000000";
                    if (to.equalsIgnoreCase("Киров")) toCode = "4300000100000000000000000";
                    if (to.equalsIgnoreCase("Котлас")) toCode = "2900800100000000000000000";
                    if (to.equalsIgnoreCase("Мончегорск")) toCode = "5100000600000000000000000";
                    if (to.equalsIgnoreCase("Мурманск")) toCode = "5100000100000000000000000";
                    if (to.equalsIgnoreCase("Набережные Челны")) toCode = "1600000200000000000000000";
                    if (to.equalsIgnoreCase("Пенза")) toCode = "5800000100000000000000000";
                    if (to.equalsIgnoreCase("Пермь")) toCode = "5900000100000000000000000";
                    if (to.equalsIgnoreCase("Петрозаводск")) toCode = "1000000100000000000000000";
                    if (to.equalsIgnoreCase("Севастополь")) toCode = "9200000000000000000000000";
                    if (to.equalsIgnoreCase("Симферополь")) toCode = "9100000700000000000000000";
                    if (to.equalsIgnoreCase("Сыктывкар")) toCode = "1100000100000000000000000";
                    if (to.equalsIgnoreCase("Ухта")) toCode = "1100000800000000000000000";
                    if (to.equalsIgnoreCase("Ялта")) toCode = "9100000800000000000000000";
                    if (to.equalsIgnoreCase("Череповец")) toCode = "3500000200000000000000000";
                    if (to.equalsIgnoreCase("Феодосия")) toCode = "9100001000000000000000000";

                    if (to.equalsIgnoreCase("Белгород")) toCode = "3100000100000000000000000";
                    if (to.equalsIgnoreCase("Великий Новгород")) toCode = "5300000100000000000000000";
                    if (to.equalsIgnoreCase("Смоленск")) toCode = "6700000300000000000000000";
                    if (to.equalsIgnoreCase("Тольятти")) toCode = "6300000700000000000000000";
                    if (to.equalsIgnoreCase("Тула")) toCode = "7100000100000000000000000";
                    if (to.equalsIgnoreCase("Ярославль")) toCode = "7600000100000000000000000";
                    if (to.equalsIgnoreCase("Анжеро-Судженск")) toCode = "4200000200000000000000000";
                    if (to.equalsIgnoreCase("Астрахань")) toCode = "3000000100000000000000000";
                    if (to.equalsIgnoreCase("Барнаул")) toCode = "2200000100000000000000000";
                    if (to.equalsIgnoreCase("Батайск")) toCode = "6100000300000000000000000";
                    if (to.equalsIgnoreCase("Бердск")) toCode = "5400000200000000000000000";
                    if (to.equalsIgnoreCase("Бийск")) toCode = "2200000400000000000000000";
                    if (to.equalsIgnoreCase("Виллози")) toCode = "4701200000400000000000000";
                    if (to.equalsIgnoreCase("Кемерово")) toCode = "4200000900000000000000000";
                    if (to.equalsIgnoreCase("Красноярск")) toCode = "2400000100000000000000000";
                    if (to.equalsIgnoreCase("Ленинск-Кузнецкий")) toCode = "4200001000000000000000000";
                    if (to.equalsIgnoreCase("МО")) throw new Exception();
                    if (to.equalsIgnoreCase("Красноярск")) toCode = "2400000100000000000000000";
                    if (to.equalsIgnoreCase("Новокузнецк")) toCode = "4200001200000000000000000";
                    if (to.equalsIgnoreCase("Томск")) toCode = "7000000100000000000000000";
                    if (to.equalsIgnoreCase("Ульяновск")) toCode = "7300000100000000000000000";
                    if (to.equalsIgnoreCase("Красноярск")) toCode = "2400000100000000000000000";
                    if (to.equalsIgnoreCase("Залесье")) fromCode =  "5002800007500000000000000";
                   // System.out.println("tocode= " + toCode);

                    //// cell = sheet.getCell(27, row);
                    //  cost = Integer.parseInt(cell.getContents());

                    //Thread.sleep(1000);

                    cell = sheet.getCell(10, i); //ves
                    //    System.out.println("is= " + cell.getContents().toString().replaceAll(",", "."));
                    weight = Double.parseDouble(cell.getContents().replaceAll(",", "."));

                    cell = sheet.getCell(11, i); //volume
                   //   System.out.print(cell.getContents().toString().replaceAll(",", "."));
                    volume = Double.parseDouble(cell.getContents().replaceAll(",", "."));

                    cell = sheet.getCell(32, i); //insurance
                   //   System.out.print(cell.getContents().toString().replaceAll(",", "."));
                    insurance = Double.parseDouble(cell.getContents().replaceAll(",", "."));





                HttpClient httpClient = HttpClientBuilder.create().build();

                HttpPost request = new HttpPost("https://api.dellin.ru/v1/public/calculator.json");
                StringEntity params = new StringEntity("{\"appKey\":\"8E6F26C2-043D-11E5-8F8A-00505683A6D3\",    \"derivalPoint\":\"" + fromCode + "\",\"derivalDoor\":true,\"arrivalPoint\":\"" + toCode + "\"," +
                        "\"arrivalDoor\":true,\"sizedVolume\":\""+volume + "\",\"sizedWeight\":\"" + weight + "\",\"statedValue\":\"" + insurance+ "\"}");

              /*  String inputLine ;
                BufferedReader br = new BufferedReader(new InputStreamReader(params.getContent()));
                try {
                    while ((inputLine = br.readLine()) != null) {
                        System.out.println(inputLine);
                    }
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/

                request.addHeader("content-type", "application/javascript");
                request.setEntity(params);

                HttpResponse response = httpClient.execute(request);
                 //   System.out.println(response);

                HttpEntity entity = response.getEntity();
                InputStream instream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                StringBuilder sb = new StringBuilder();

                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                String ss = sb.toString();
                // now you have the string representation of the HTML request
               // System.out.println("RESPONSE: " + ss);
                instream.close();

                // Thread.sleep(90000);

                JsonParser parser = new JsonParser();//response.toString()

                JsonObject mainObject = parser.parse(ss).getAsJsonObject();
                summa = mainObject.getAsJsonPrimitive("price").getAsString();
                //   System.out.println("intercity= " + summa);

                JsonObject mainObject2 = parser.parse(ss).getAsJsonObject().getAsJsonObject("derival");
                priceFrom = mainObject2.getAsJsonPrimitive("price").getAsString();

             try {
                 JsonObject mainObject8 = parser.parse(ss).getAsJsonObject().getAsJsonObject("intercity");
                 intercity = mainObject8.getAsJsonPrimitive("price").getAsString();
             }catch (Exception e) { intercity = "-";}

                try {
                JsonObject mainObject9 = parser.parse(ss).getAsJsonObject();
                insuranceResponse = mainObject9.getAsJsonPrimitive("insurance").getAsString();
                }catch (Exception e) { insuranceResponse = "-";}
                //   System.out.println("priceFrom= " + priceFrom);

                fromm = mainObject2.get("terminal").getAsString();
                //   System.out.println("from= " + fromm);

                JsonObject mainObject3 = parser.parse(ss).getAsJsonObject().getAsJsonObject("arrival");
                derival = mainObject3.get("terminal").getAsString();
                 //   System.out.println("to= " + derival.toString());

                priceTO = mainObject3.get("price").getAsString();
                //     System.out.println("priceTO= " + priceTO);

                //     System.out.println("weight= " + weight);

                try {
                    // for (JsonElement user : pItem) {
                    //   JsonObject userObject = user.getAsJsonObject();
                    //if (userObject.get("type").getAsString().equals("avto")) {
                    //        System.out.print(userObject.get("price"));

                    Label label0 = new Label(0, i, from);
                    Label label1 = new Label(1, i, to);
                    Label label2 = new Label(2, i, summa);
                    Label label3 = new Label(3, i, priceFrom);
                    Label label4 = new Label(4, i, priceTO);
                    Label label5 = new Label(6, i, weight.toString());
                    Label label6 = new Label(7, i, volume.toString());
                    Label label7 = new Label(8, i, insuranceResponse);
                    Label label8 = new Label(9, i, intercity);

                    writableSheet.addCell(label0);
                    writableSheet.addCell(label1);
                    writableSheet.addCell(label2);
                    writableSheet.addCell(label3);
                    writableSheet.addCell(label4);
                    writableSheet.addCell(label5);
                    writableSheet.addCell(label6);
                    writableSheet.addCell(label7);
                    writableSheet.addCell(label8);
                    if (count==10){
                        System.out.println(i);count=0;}
                    else count++;

                    //return;
                } catch (Exception e) {System.out.print("exc");
                }


                } catch (Exception e) {
                    Label label0 = new Label(0, i, "Моск Обл");
                    writableSheet.addCell(label0);
                    System.out.print("DoesntRecognized");
                }
                }

        } catch (Exception e) {System.out.print("exc2");
        } finally {
            writableWorkbook.write();
            writableWorkbook.close();
        }


    }

}
