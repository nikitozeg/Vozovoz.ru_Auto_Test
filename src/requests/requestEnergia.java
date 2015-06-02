package requests;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.selenium.SeleniumException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class requestEnergia {
    File exlFile = new File("C:\\Users\\n.ivanov\\Dropbox\\crowler.xls");
    Workbook w;
    int fromCode,toCode;
Double weight;
    private final String USER_AGENT = "Mozilla/5.0";
  /*  public  SetParams(int row) throws IOException {


      return;
    }
*/

    public static void main(String[] args) throws Exception {
        requestEnergia http = new requestEnergia();
        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();

    }

    // HTTP GET request
    private void sendGet() throws Exception {
        File crowlerResult = new File("C:\\Users\\n.ivanov\\Dropbox\\crowlerResult.xls");
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(crowlerResult);
        w = Workbook.getWorkbook(exlFile);
        Sheet sheet = w.getSheet(0);
    try {

        String s = "null";
        String to = "", from = "";
        WritableSheet writableSheet = writableWorkbook.createSheet("Sheet2", 0);

        for (int i = 1; i <2; i++) {
            try {


                Cell cell = sheet.getCell(1, i);
                from = cell.getContents().toString();

                if (from.equals("МОСКВА")) fromCode = 495;
                if (from.equals("Волгоград")) fromCode = 8442;
                if (from.equals("Воронеж")) fromCode = 4732;
                if (from.equals("Екатеринбург")) fromCode = 343;
                if (from.equals("Казань")) fromCode = 84321;
                if (from.equals("Краснодар")) fromCode = 8612;
                if (from.equals("Нижний Новгород")) fromCode = 8312;
                if (from.equals("НОВОСИБИРСК")) fromCode = 383;
                if (from.equals("Омск")) fromCode = 3812;
                if (from.equals("Ростов-на-Дону")) fromCode = 863;
                if (from.equals("Самара")) fromCode = 864;
                if (from.equals("С.Петербург")) fromCode = 812;
                if (from.equals("Саратов")) fromCode = 8452;
                if (from.equals("Ставрополь")) fromCode = 8652;
                if (from.equals("Уфа")) fromCode = 3472;
                if (from.equals("Челябинск")) fromCode = 3512;


                cell = sheet.getCell(2, i);
                to = cell.getContents().toString();

                if (to.equals("МОСКВА")) toCode = 495;
                if (to.equals("Волгоград")) toCode = 8442;
                if (to.equals("Воронеж")) toCode = 4732;
                if (to.equals("Екатеринбург")) toCode = 343;
                if (to.equals("Казань")) toCode = 84321;
                if (to.equals("Краснодар")) toCode = 8612;
                if (to.equals("Нижний Новгород")) toCode = 8312;
                if (to.equals("НОВОСИБИРСК")) toCode = 383;
                if (to.equals("Омск")) toCode = 3812;
                if (to.equals("Ростов-на-Дону")) toCode = 863;
                if (to.equals("Самара")) toCode = 864;
                if (to.equals("С.Петербург")) toCode = 812;
                if (to.equals("Саратов")) toCode = 8452;
                if (to.equals("Ставрополь")) toCode = 8652;
                if (to.equals("Уфа")) toCode = 3472;
                if (to.equals("Челябинск")) toCode = 3512;

                //// cell = sheet.getCell(27, row);
                //  cost = Integer.parseInt(cell.getContents());

                //Thread.sleep(1000);

                cell = sheet.getCell(8, i); //ves
                //    System.out.println("is= " + cell.getContents().toString().replaceAll(",", "."));
                weight = Double.parseDouble(cell.getContents().toString().replaceAll(",", "."));

            }  catch (SeleniumException e) {
            }


            String url = "http://api.nrg-tk.ru/api/rest/?method=nrg.calculate&from=" + fromCode + "&to=" + toCode + "&weight=" + weight + "&volume=0.13&place=1";
           // System.out.println(url);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            //int responseCode = con.getResponseCode();
            // System.out.println("\nSending 'GET' request to URL : " + url);

            //System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            JsonParser parser = new JsonParser();//response.toString()
            JsonObject mainObject = parser.parse(response.toString()).getAsJsonObject().getAsJsonObject("rsp");
          //  System.out.println(response.toString());
            JsonArray pItem = mainObject.getAsJsonArray("values");

            try {
                for (JsonElement user : pItem) {
                    JsonObject userObject = user.getAsJsonObject();
                    if (userObject.get("type").getAsString().equals("avto")) {
                     //   System.out.print(userObject.get("price"));

                        s = String.valueOf(userObject.get("price"));
                        Label label5 = new Label(3, i, s);
                        Label from1 = new Label(0, i, from);
                        Label to1 = new Label(1, i, to);
                        Label ves = new Label(2, i, weight.toString());
                        writableSheet.addCell(label5);
                        writableSheet.addCell(from1);
                        writableSheet.addCell(to1);
                        writableSheet.addCell(ves);
                        System.out.println(i);
                        //return;
                    }
                }
            } catch (Exception e) {
            }
        }
    }catch (Exception e){}
      finally {
        writableWorkbook.write();
            writableWorkbook.close();}

    }


}