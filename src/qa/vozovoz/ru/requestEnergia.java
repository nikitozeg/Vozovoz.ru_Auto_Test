package qa.vozovoz.ru;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class requestEnergia {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {


        requestEnergia http = new requestEnergia();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();


    }

    // HTTP GET request
    private void sendGet() throws Exception {
        File crowlerResult = new File("C:\\Users\\n.ivanov\\Dropbox\\crowlerResult.xls");
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(crowlerResult);
        String s = "null";
        WritableSheet writableSheet = writableWorkbook.createSheet("Sheet2", 0);
        String url = "http://api.nrg-tk.ru/api/rest/?method=nrg.calculate&from=383&to=495&weight=1&volume=0&place=1";

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

//        String json = "{\"p_result\":\"ok\",\"p_item\":[ {\"p_id\":132,\"p_name\":\"Николай\"},{\"p_id\":133,\"p_name\":\"Светлана\"}],\"values\":[{\"type\":\"avto\",\"price\":230,\"term\":\"2-3 дня\"}]}";
//        String input = "{\"p_result\":\"ok\",\n" +
//                "\n" +
//                "\"p_item\":[\n" +
//                "\n" +
//                " {\"p_id\":132,\"p_name\":\"Николай\"}\n" +
//                "\n" +
//                ",{\"p_id\":133,\"p_name\":\"Светлана\"}\n" +
//                "\n" +
//                "               ]\n" +
//                "\n" +
//                "}";

        JsonParser parser = new JsonParser();//response.toString()
        JsonObject mainObject = parser.parse(response.toString()).getAsJsonObject().getAsJsonObject("rsp");
        System.out.println(response.toString());
        JsonArray pItem = mainObject.getAsJsonArray("values");

        try {
            for (JsonElement user : pItem) {
                JsonObject userObject = user.getAsJsonObject();
                if (userObject.get("type").getAsString().equals("avto")) {
                    System.out.print(userObject.get("price"));
                    s = String.valueOf(userObject.get("price"));
                    Label label5 = new Label(4, 1, s);
                    writableSheet.addCell(label5);
                    writableWorkbook.write();


                    //return;
                }
            }
        } catch (IOException e) {
        } finally {
            writableWorkbook.close();
        }

    }


}