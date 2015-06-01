package requests;

import com.thoughtworks.selenium.SeleniumException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class requestDellin {
    File exlFile = new File("C:\\Users\\n.ivanov\\Dropbox\\crowler.xls");
    Workbook w;
    int fromCode, toCode;
    Double weight;
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

    // HTTP GET request
    private void sendPost() throws Exception {

        String url = "https://api.dellin.ru/v1/public/calculator.json";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        String urlParameters = "{\n" +
                "    \"appKey\":\"8E6F26C2-043D-11E5-8F8A-00505683A6D3\",\n" +
                "    \"derivalPoint\":     \"7800000000000000000000000\", \n" +
                "    \"derivalDoor\":      true, \n" +
                "    \"arrivalPoint\":     \"5200000100000000000000000\",\n" +
                "    \"arrivalDoor\":      true, \n" +
                "    \"sizedVolume\":      \"1\", \n" +
                "    \"sizedWeight\":      \"1\"}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    private void sendGet() throws Exception {


        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {
            HttpPost request = new HttpPost("https://api.dellin.ru/v1/public/calculator.json");
            StringEntity params = new StringEntity("{\"appKey\":\"8E6F26C2-043D-11E5-8F8A-00505683A6D3\",\"derivalPoint\":\"7800000000000000000000000\",\"derivalDoor\":true,\"arrivalPoint\":\"5200000100000000000000000\",\"arrivalDoor\":true,\"sizedVolume\":\"1\",\"sizedWeight\":\"1\"}");
            request.addHeader("content-type", "application/javascript");
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);
            response.getEntity().toString();
            // String responseString = EntityUtils.toString(request, "UTF-8");
            // System.out.println(responseString);
            System.out.println(response);
            // handle response here...


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
            String s = sb.toString();
            // now you have the string representation of the HTML request
            System.out.println("RESPONSE: " + s);
            instream.close();


            Thread.sleep(99000);


            File crowlerResult = new File("C:\\Users\\n.ivanov\\Dropbox\\crowlerResult.xls");
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(crowlerResult);
            w = Workbook.getWorkbook(exlFile);
            Sheet sheet = w.getSheet(0);


            // String s = "null";
            String to = "", from = "";
            WritableSheet writableSheet = writableWorkbook.createSheet("Sheet2", 0);
            Double volume = 0.13;
            for (int i = 1; i < 7; i++) {
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

                } catch (SeleniumException e) {
                }


                String url = "http://api.nrg-tk.ru/api/rest/?method=nrg.calculate&from=" + fromCode + "&to=" + toCode + "&weight=" + weight + "&volume=0.13&place=1";
                // System.out.println(url);
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                //add request header
                con.setRequestProperty("User-Agent", USER_AGENT);

            }
        } catch (
                Exception e
                )

        {
        }
    }
}