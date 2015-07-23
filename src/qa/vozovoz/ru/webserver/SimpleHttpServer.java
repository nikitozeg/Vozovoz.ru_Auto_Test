package qa.vozovoz.ru.webserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/*
 * a simple static http server
*/
public class SimpleHttpServer {

    public static void main(String[] args) throws Exception {
        //  SimpleHttpServer.setMapOfUidTerminals();
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    public static Map setMapOfUidTerminals() throws Exception {
        Map mapobj = new HashMap<>();

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://vozovoz.ru/api/v1/terminals?limit=10");

        request.addHeader("content-type", "application/json; charset=utf-8");
        //request.addHeader("Accept-Language","ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");

        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        // EntityUtils.toString(sb, "UTF-8");
        InputStream instream = entity.getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
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

        System.out.println("RESPONSE: " + sb);
        String ss = sb.toString();

        return mapobj;
    }

    public static String getKladr(String address) throws Exception {
        String kladr = "";
        HttpClient httpClient1 = HttpClientBuilder.create().build();
        HttpPost request1 = new HttpPost("https://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/address");
        StringEntity params1 = new StringEntity("{\"count\":2,\"query\":\"" + address + "\"}", "utf-8");
        request1.addHeader("content-type", "application/json");
        request1.addHeader("Authorization", "Token 84beb76a98914195f374779f2f313d31efca3c5d");
        request1.addHeader("X-Secret", "cb82deee2d367b967ba569b5fc11b9e21a8c4832");
        request1.setEntity(params1);

        HttpResponse response1 = httpClient1.execute(request1);
        HttpEntity entity1 = response1.getEntity();
        InputStream instream1 = entity1.getContent();
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(instream1));


        StringBuilder sb1 = new StringBuilder();

        String line1 = null;
        try {
            while ((line1 = reader1.readLine()) != null) {
                sb1.append(line1 + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                instream1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String ss1 = sb1.toString();
        // now you have the string representation of the HTML request
        //  System.out.println("RESPONSE: " + ss1);

        JsonParser parser = new JsonParser();//response.toString()

        JsonArray mainObject = parser.parse(sb1.toString()).getAsJsonObject().getAsJsonArray("suggestions");
        //System.out.println(mainObject.get(0).getAsJsonObject().getAsJsonObject("data").get("kladr_id").getAsString());
        kladr = mainObject.get(0).getAsJsonObject().getAsJsonObject("data").get("kladr_id").getAsString();

        return kladr + "000000000000";

    }

    public static String getDlResponseInJson(String fromAddress, String toAddress, String volume, String weight, String insuranceCost, Boolean derivalDoor, Boolean arrivalDoor) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

//        JsonParser parser = new JsonParser();
        String kladrFrom = getKladr(fromAddress);
        String kladrTo = getKladr(toAddress);
        if (insuranceCost == null) insuranceCost = "0";
      /*  System.out.println(fromAddress);
        System.out.println(toAddress);
        System.out.println(volume);
        System.out.println(weight);
        System.out.println(insuranceCost);
*/
        HttpPost request = new HttpPost("https://api.dellin.ru/v1/public/calculator.json");
        StringEntity params = new StringEntity("{\"appKey\":\"8E6F26C2-043D-11E5-8F8A-00505683A6D3\",    \"derivalPoint\":\"" + kladrFrom + "\",\"derivalDoor\":" + derivalDoor + ",\"arrivalPoint\":\"" + kladrTo + "\"," +
                "\"arrivalDoor\":" + arrivalDoor + ",\"sizedVolume\":\"" + volume + "\",\"sizedWeight\":\"" + weight + "\",\"statedValue\":\"" + insuranceCost + "\"}", "UTF-8");

        request.addHeader("content-type", "application/json; charset=utf-8");
        //request.addHeader("Accept-Language","ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        request.setEntity(params);

        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        // EntityUtils.toString(sb, "UTF-8");
        InputStream instream = entity.getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
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

        // System.out.println("RESPONSE: " + sb);
        String ss = sb.toString();
        instream.close();

        return ss;
        // Thread.sleep(90000);


    }

    public static class MyHandler implements HttpHandler {

        private String fromAddress, toAddress, volume, weight, insuranceCost;
        Double priceFrom, priceTo, summa, intercity, insuranceResponse;
        Boolean derivalDoor, arrivalDoor;

        public void handle(HttpExchange t) throws IOException {
            JsonParser parser = new JsonParser();

            InputStream instreamVoz = t.getRequestBody();

            BufferedReader readerVoz = new BufferedReader(new InputStreamReader(instreamVoz, "UTF-8"));
            StringBuilder sbVoz = new StringBuilder();

            String lineVoz = "";
            try {
                while ((lineVoz = readerVoz.readLine()) != null) {
                    sbVoz.append(lineVoz + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    instreamVoz.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // String ssDL = sbVoz.toString();
            // System.out.println("RESPONSE: " + ssDL);


            Headers headers = t.getResponseHeaders();
            // headers.add("Content-Type", "application/atom+xml");
            //     headers.add("Access-Control-Allow-Origin:", "*");
            //   headers.add("Access-Control-Allow-Methods:", "GET, POST, OPTIONS, PUT, DELETE");
            headers.add("text/html", "charset=utf-8");
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "POST, OPTIONS");
            headers.add("Access-Control-Max-Age", "3600");
            headers.add("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type");

            try {
                JsonObject useppv = parser.parse(sbVoz.toString()).getAsJsonObject();

                if (useppv.getAsJsonObject("from").get("useppv").getAsString().equalsIgnoreCase("false")) {
                    fromAddress = useppv.getAsJsonObject("from").getAsJsonObject("address").get("value").getAsString();
                    derivalDoor = true;
                } else {
                    fromAddress = useppv.getAsJsonObject("from").getAsJsonObject("terminal").get("address").getAsString();
                    derivalDoor = false;
                }

                if (useppv.getAsJsonObject("to").get("useppv").getAsString().equalsIgnoreCase("false")) {
                    toAddress = useppv.getAsJsonObject("to").getAsJsonObject("address").get("value").getAsString();
                    arrivalDoor = true;
                } else {
                    toAddress = useppv.getAsJsonObject("to").getAsJsonObject("terminal").get("address").getAsString();
                    arrivalDoor = false;
                }

                volume = useppv.getAsJsonObject("cargo").getAsJsonObject("total").getAsJsonObject("all").get("volume").getAsString();
                weight = useppv.getAsJsonObject("cargo").getAsJsonObject("total").getAsJsonObject("all").get("weight").getAsString();

                if (useppv.getAsJsonObject("cargo").get("insurance").getAsString().equalsIgnoreCase("true"))
                    insuranceCost = useppv.getAsJsonObject("cargo").get("insuranceCost").getAsString();
                else insuranceCost = "0";
            } catch (Exception e) {
                t.sendResponseHeaders(200, 0);
                OutputStream os = t.getResponseBody();
                os.write("Can't parse body request 1".getBytes());
                os.close();
            }

            try {
                String ss = getDlResponseInJson(fromAddress, toAddress, volume, weight, insuranceCost, derivalDoor, arrivalDoor);
                JsonObject mainObject = parser.parse(ss).getAsJsonObject();
                summa = mainObject.getAsJsonPrimitive("price").getAsDouble();

                JsonObject mainObject2 = parser.parse(ss).getAsJsonObject().getAsJsonObject("derival");
                priceFrom = mainObject2.getAsJsonPrimitive("price").getAsDouble();
                //  recognizedFrom=mainObject2.getAsJsonPrimitive("terminal").getAsString();

                try {
                    JsonObject mainObject8 = parser.parse(ss).getAsJsonObject().getAsJsonObject("intercity");
                    intercity = mainObject8.getAsJsonPrimitive("price").getAsDouble();
                } catch (Exception e) {
                    intercity = 0.0;
                }

                try {
                    JsonObject mainObject9 = parser.parse(ss).getAsJsonObject();
                    insuranceResponse = mainObject9.getAsJsonPrimitive("insurance").getAsDouble();
                } catch (Exception e) {
                    insuranceResponse = 0.0;
                }

                JsonObject mainObject3 = parser.parse(ss).getAsJsonObject().getAsJsonObject("arrival");
                //  recognizedTo=mainObject2.getAsJsonPrimitive("terminal").getAsString();

                priceTo = mainObject3.get("price").getAsDouble();

                System.out.println("============================");
                System.out.println(priceFrom);
                System.out.println(intercity);
                System.out.println(priceTo);
                System.out.println(insuranceResponse);
                System.out.println(summa);

            } catch (Exception e) {
                t.sendResponseHeaders(200, 0);
                OutputStream os = t.getResponseBody();
                os.write("Can't parse body request 2".getBytes());
                os.close();
            }


            String response = "{\"data\":{\"cost\":" + summa + ",\"price\":[{\"ID\":\"06\",\"Name\":\"Перевозка между городами\",\"Cost\":" + intercity + "},{\"ID\":\"01\",\"Name\":\"Забор груза от клиента\",\"Cost\":" + priceFrom + "}," +
                    "{\"ID\":\"04\",\"Name\":\"Отвоз груза клиенту\",\"Cost\":" + priceTo + "},{\"ID\":\"10\",\"Name\":\"Страхование грузов\",\"Cost\":7.1,\"ActionCost\":7.1}],\"calculationId\":\"55b0adaacfe0ccfb77d7a955\"}}";
            // priceFrom, priceTo, summa, intercity, insuranceResponse;
            t.sendResponseHeaders(200, 0);


            // t.sendResponseHeaders(200, response.length());

            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}