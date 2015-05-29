package qa.vozovoz.ru;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
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

        String url = "http://api.nrg-tk.ru/api/rest/?callback=jsonp1236078926969&method=nrg.calculate&from=8362&to=495&weight=1&volume=0&place=1";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);

        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        //print result
        System.out.println(response.toString());

        String json = "{\"p_result\":\"ok\",\"p_item\":[ {\"p_id\":132,\"p_name\":\"Николай\"},{\"p_id\":133,\"p_name\":\"Светлана\"}],\"values\":[{\"type\":\"avia\",\"price\":300,\"term\":\"1-2 аДаНаЕаЙ\"},{\"type\":\"rw\",\"price\":200,\"term\":\"5-8 аДаНаЕаЙ\"},{\"type\":\"avto\",\"price\":250,\"term\":\"5-7 аДаНаЕаЙ\"}]}";

        String input = "{\"p_result\":\"ok\",\n" +
                "\n" +
                "\"p_item\":[\n" +
                "\n" +
                " {\"p_id\":132,\"p_name\":\"Николай\"}\n" +
                "\n" +
                ",{\"p_id\":133,\"p_name\":\"Светлана\"}\n" +
                "\n" +
                "               ]\n" +
                "\n" +
                "}";

        JsonParser parser = new JsonParser();
        JsonObject mainObject = parser.parse(json).getAsJsonObject();
        JsonArray pItem = mainObject.getAsJsonArray("values");

        for (JsonElement user : pItem) {
            JsonObject userObject = user.getAsJsonObject();
            if (userObject.get("type").getAsString().equals("avto")) {
                System.out.println(userObject.get("price"));
                return;
            }
        }

        /*Object objj = parser.parse(json);
        JSONObject jsonObj = (JSONObject) objj;
        System.out.println(jsonObj.get("term"));*/
// some string

     /*   JSONObject jo = (JSONObject) jsonObj.get("paramsObj");
        System.out.println(jo.get("three"));
// four

        JSONArray ja = (JSONArray) jsonObj.get("paramsArray");
        System.out.println(ja.get(1));*/
// 100
    }



}