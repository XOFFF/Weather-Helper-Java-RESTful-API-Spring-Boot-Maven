import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FiveDaysTempForecastTest{
    private String apiKey = "hHQoU0Adpo6wzZi9PK198lzBPK8vA75z";
    private String cityNameKeyword = "cities";
    private String cityName = "Lublin";
    private String postcodeKeyword = "postalcodes";
    private String postcodeValue = "20-092";

    @Test
    public void getFiveDaysTempForecastResponseByCityNameIsNotNull(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        assertNotNull(cityKeyGetter.getResponse(cityNameKeyword, cityName));
    }

    @Test
    public void getFiveDaysTempForecastResponseByCityPostcodeIsNotNull(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        assertNotNull(cityKeyGetter.getResponse(postcodeKeyword, postcodeValue));
    }

    @Test
    public void getFiveDaysTempForecastResponseCodeByCityNameIs200(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        cityKeyGetter.getResponse(cityNameKeyword, cityName);
        assertEquals(200, cityKeyGetter.getResponseCode());
    }

    @Test
    public void getFiveDaysTempForecastResponseCodeByCityPostcodeIs200(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        cityKeyGetter.getResponse(postcodeKeyword, postcodeValue);
        assertEquals(200, cityKeyGetter.getResponseCode());
    }

    @Test
    public void getFiveDaysTempForecastResponseCodeByCityNameIs503WhenAllowedNumberOfRequestsHasBeenExceeded(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        cityKeyGetter.getResponse(cityNameKeyword, cityName);
        assertEquals(503, cityKeyGetter.getResponseCode());
    }

    @Test
    public void getFiveDaysTempForecastResponseCodeByCityPostcodeIs503WhenAllowedNumberOfRequestsHasBeenExceeded(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        cityKeyGetter.getResponse(postcodeKeyword, postcodeValue);
        assertEquals(503, cityKeyGetter.getResponseCode());
    }

    @Test
    public void getFiveDaysTempForecastSizeOf5DaysInResponseByCityNameOfDailyForecasts(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        String cityKey = cityKeyGetter.getResponse(cityNameKeyword, cityName);
        FiveDaysTempForecast fiveDaysTempForecast = new FiveDaysTempForecast(apiKey, cityKey);
        fiveDaysTempForecast.getFiveDaysTemperatureForecast();
        assertEquals(5, fiveDaysTempForecast.getDailyForecastsSize());
    }

    @Test
    public void getFiveDaysTempForecastSizeOf5DaysInResponseByCityPostcodeOfDailyForecasts(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        String cityKey = cityKeyGetter.getResponse(postcodeKeyword, postcodeValue);
        FiveDaysTempForecast fiveDaysTempForecast = new FiveDaysTempForecast(apiKey, cityKey);
        fiveDaysTempForecast.getFiveDaysTemperatureForecast();
        assertEquals(5, fiveDaysTempForecast.getDailyForecastsSize());
    }
}