import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

public class FiveDaysTempForecast {
    private String apiKey;
    private String cityKeyEndpoint;

    public FiveDaysTempForecast(String apiKey, String cityKeyEndpoint) {
        this.apiKey = apiKey;
        this.cityKeyEndpoint = cityKeyEndpoint;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCityKeyEndpoint() {
        return cityKeyEndpoint;
    }

    public void setCityKeyEndpoint(String cityKeyEndpoint) {
        this.cityKeyEndpoint = cityKeyEndpoint;
    }

    public void getFiveDaysTemperatureForecast(){
        try{
            URL url = new URL("http://dataservice.accuweather.com/forecasts/v1/daily/5day/" + cityKeyEndpoint + "?apikey=" + apiKey);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            System.out.println("Link to the full JSON response: " + url);
            System.out.println("-----------------------");

            con.connect();

            int responseCode = con.getResponseCode();

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
                JSONObject dataObject = (JSONObject) parser.parse(String.valueOf(informationString));

                JSONArray dailyForecasts = (JSONArray) dataObject.get("DailyForecasts");

                for(int dayCount=0; dayCount<dailyForecasts.size(); dayCount++){
                    JSONObject dayData = (JSONObject) dailyForecasts.get(dayCount);

                    LocalDate date = DateConverter.convertDate(dayData);

                    JSONObject dayTempData = (JSONObject) dayData.get("Temperature");
                    float minTemp = TemperatureConverter.convertTemperature(dayTempData, "Minimum");
                    float maxTemp = TemperatureConverter.convertTemperature(dayTempData, "Maximum");

                    DayForecast dayForecast = new DayForecast(date, minTemp, maxTemp);
                    dayForecast.showData();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
