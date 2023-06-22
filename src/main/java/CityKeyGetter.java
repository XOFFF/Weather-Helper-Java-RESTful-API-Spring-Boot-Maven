import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CityKeyGetter {
    private String apiKey;
    private int responseCode;

    public CityKeyGetter(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getCityKeyByName(){
        Scanner nameScanner = new Scanner(System.in);
        String urlKeyword = "cities";

        System.out.print("Write city NAME: ");
        String cityNameEndpoint = nameScanner.next();

        return getResponse(urlKeyword, cityNameEndpoint);
    }

    public String getCityKeyByPostCode(){
        Scanner postCodeScanner = new Scanner(System.in);
        String urlKeyword = "postalcodes";

        System.out.print("Write city POSTCODE: ");
        String cityPostCodeEndpoint = postCodeScanner.next();

        return getResponse(urlKeyword, cityPostCodeEndpoint);
    }

    protected String getResponse(String urlKeyword, String endpoint){
        String cityKey = "";
        RequestsCounter.addRequestToCounter();
        try{
            URL url = new URL("http://dataservice.accuweather.com/locations/v1/" + urlKeyword + "/search?apikey=" + apiKey + "&q=" + endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept-Encoding", "gzip");
            con.connect();

            responseCode = con.getResponseCode();

            if(responseCode != 200){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(informationString));

                JSONObject cityData = (JSONObject) dataObject.get(0);
                cityKey = cityData.get("Key").toString();
            }

        } catch (Exception e){
            System.out.println("System cannot find that :(");
            e.printStackTrace();
        }
        return cityKey;
    }
}