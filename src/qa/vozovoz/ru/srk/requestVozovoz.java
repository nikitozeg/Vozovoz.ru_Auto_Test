package qa.vozovoz.ru.srk;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.selenium.SeleniumException;
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

public class requestVozovoz {
    File exlFile = new File("C:\\Users\\n.ivanov\\Dropbox\\crowler.xls");
    Workbook w;
    String fromCode, toCode;
    Double weight;
    private final String USER_AGENT = "Mozilla/5.0";
  /*  public  SetParams(int row) throws IOException {


      return;
    }
*/

    public static void main(String[] args) throws Exception {
        requestVozovoz http = new requestVozovoz();
        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();

    }





    private void sendGet() throws Exception {
        String MTcost, derival, priceFrom, fromm, priceTO = "";
        File crowlerResult = new File("C:\\Users\\n.ivanov\\Dropbox\\crowlerResult.xls");
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(crowlerResult);
        w = Workbook.getWorkbook(exlFile);
        Sheet sheet = w.getSheet(0);
        WritableSheet writableSheet = writableWorkbook.createSheet("Sheet2", 0);
        Label label00 = new Label(2, 0, "МТ");
        Label label01 = new Label(3, 0, "Забор");
        Label label02 = new Label(4, 0, "Отвоз");
        Label label03 = new Label(4, 0, "Отвоз");
        Label label04 = new Label(6, 0, "Вес");
        writableSheet.addCell(label01);
        writableSheet.addCell(label02);
        writableSheet.addCell(label00);
        writableSheet.addCell(label03);
        writableSheet.addCell(label04);

        //YANDEX GETGEO





        try {

            String to = "", from = "";

            for (int i = 1236; i < 1682; i++) {
                try {

                    Cell cell = sheet.getCell(1, i);
                    from = cell.getContents().toString();

                    if (from.equals("МОСКВА")) fromCode = "7700000000000000000000000";
                    if (from.equals("Волгоград")) fromCode = "3400000100000000000000000";
                    if (from.equals("Воронеж")) fromCode = "3600000100000000000000000";
                    if (from.equals("Екатеринбург")) fromCode = "6600000100000000000000000";
                    if (from.equals("Казань")) fromCode = "1600000100000000000000000";
                    if (from.equals("Краснодар")) fromCode = "2300000100000000000000000";
                    if (from.equals("Нижний Новгород")) fromCode = "5200000100000000000000000";
                    if (from.equals("НОВОСИБИРСК")) fromCode = "5400000100000000000000000";
                    if (from.equals("Омск")) fromCode = "5500000100000000000000000";
                    if (from.equals("Ростов-на-Дону")) fromCode = "6100000100000000000000000";
                    if (from.equals("Самара")) fromCode = "6300000100000000000000000";
                    if (from.equals("С.Петербург")) fromCode = "7800000000000000000000000";
                    if (from.equals("Саратов")) fromCode = "6400000100000000000000000";
                    if (from.equals("Ставрополь")) fromCode = "2600000100000000000000000";
                    if (from.equals("Уфа")) fromCode = "200000100000000000000000";
                    if (from.equals("Челябинск")) fromCode = "7400000100000000000000000";


                    cell = sheet.getCell(2, i);
                    to = cell.getContents().toString();

                    if (to.equals("МОСКВА")) toCode = "7700000000000000000000000";
                    if (to.equals("Волгоград")) toCode = "3400000100000000000000000";
                    if (to.equals("Воронеж")) toCode = "3600000100000000000000000";
                    if (to.equals("Екатеринбург")) toCode = "6600000100000000000000000";
                    if (to.equals("Казань")) toCode = "1600000100000000000000000";
                    if (to.equals("Краснодар")) toCode = "2300000100000000000000000";
                    if (to.equals("Нижний Новгород")) toCode = "5200000100000000000000000";
                    if (to.equals("НОВОСИБИРСК")) toCode = "5400000100000000000000000";
                    if (to.equals("Омск")) toCode = "5500000100000000000000000";
                    if (to.equals("Ростов-на-Дону")) toCode = "6100000100000000000000000";
                    if (to.equals("Самара")) toCode = "6300000100000000000000000";
                    if (to.equals("С.Петербург")) toCode = "7800000000000000000000000";
                    if (to.equals("Саратов")) toCode = "6400000100000000000000000";
                    if (to.equals("Ставрополь")) toCode = "2600000100000000000000000";
                    if (to.equals("Уфа")) toCode = "200000100000000000000000";
                    if (to.equals("Челябинск")) toCode = "7400000100000000000000000";


                    //// cell = sheet.getCell(27, row);
                    //  cost = Integer.parseInt(cell.getContents());

                    //Thread.sleep(1000);

                    cell = sheet.getCell(8, i); //ves
                    //    System.out.println("is= " + cell.getContents().toString().replaceAll(",", "."));
                    weight = Double.parseDouble(cell.getContents().toString().replaceAll(",", "."));

                } catch (SeleniumException e) {
                }


                HttpClient httpClient = HttpClientBuilder.create().build();

                HttpPost request = new HttpPost("https://api.dellin.ru/v1/public/calculator.json");
                StringEntity params = new StringEntity("{\"appKey\":\"8E6F26C2-043D-11E5-8F8A-00505683A6D3\",\"derivalPoint\":\"" + fromCode + "\",\"derivalDoor\":true,\"arrivalPoint\":\"" + toCode + "\",\"arrivalDoor\":true,\"sizedVolume\":\"0.13\",\"sizedWeight\":\"" + weight + "\"}");
                request.addHeader("content-type", "application/javascript");
                request.setEntity(params);

                HttpResponse response = httpClient.execute(request);
                System.out.println(response);

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

                JsonObject mainObject = parser.parse(ss.toString()).getAsJsonObject().getAsJsonObject("intercity");
                MTcost = mainObject.getAsJsonPrimitive("price").getAsString();
                System.out.println("intercity= " + MTcost);

                JsonObject mainObject2 = parser.parse(ss.toString()).getAsJsonObject().getAsJsonObject("derival");
                priceFrom = mainObject2.getAsJsonPrimitive("price").getAsString();
                System.out.println("priceFrom= " + priceFrom);

                fromm = mainObject2.get("terminal").getAsString();
                System.out.println("from= " + fromm);

                JsonObject mainObject3 = parser.parse(ss.toString()).getAsJsonObject().getAsJsonObject("arrival");
                derival = mainObject3.get("terminal").getAsString();
                System.out.println("to= " + derival);
                String arrival = mainObject3.get("price").getAsString();
                priceTO = mainObject3.get("price").getAsString();
                System.out.println("priceTO= " + priceTO);

                //     System.out.println("weight= " + weight);

                try {
                    // for (JsonElement user : pItem) {
                    //   JsonObject userObject = user.getAsJsonObject();
                    //if (userObject.get("type").getAsString().equals("avto")) {
                    //        System.out.print(userObject.get("price"));

                    Label label0 = new Label(0, i, fromm);
                    Label label1 = new Label(1, i, to);
                    Label label2 = new Label(2, i, MTcost);
                    Label label3 = new Label(3, i, priceFrom);
                    Label label4 = new Label(4, i, priceTO);
                    Label label5 = new Label(6, i, weight.toString());


                    writableSheet.addCell(label0);
                    writableSheet.addCell(label1);
                    writableSheet.addCell(label2);
                    writableSheet.addCell(label3);
                    writableSheet.addCell(label4);
                    writableSheet.addCell(label5);
                    System.out.println(i);

                    //return;
                } catch (Exception e) {
                }

            }

        } catch (Exception e) {
        } finally {
            writableWorkbook.write();
            writableWorkbook.close();
        }


    }

}
