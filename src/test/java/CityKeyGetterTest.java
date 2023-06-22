import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CityKeyGetterTest{
    private String apiKey = "hHQoU0Adpo6wzZi9PK198lzBPK8vA75z";
    private String cityNameKeyword = "cities";
    private String cityName = "Lublin";
    private String postcodeKeyword = "postalcodes";
    private String postcodeValue = "20-092";
    @Test
    public void getCityKeyGetterResponseByCityNameIsNotNull(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        assertNotNull(cityKeyGetter.getResponse(cityNameKeyword, cityName));
    }

    @Test
    public void getCityKeyGetterResponseByCityPostcodeIsNotNull(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        assertNotNull(cityKeyGetter.getResponse(postcodeKeyword, postcodeValue));
    }

    @Test
    public void getCityKeyGetterResponseCodeByCityNameIs200(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        cityKeyGetter.getResponse(cityNameKeyword, cityName);
        assertEquals(200, cityKeyGetter.getResponseCode());
    }

    @Test
    public void getCityKeyGetterResponseCodeByCityPostcodeIs200(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        cityKeyGetter.getResponse(postcodeKeyword, postcodeValue);
        assertEquals(200, cityKeyGetter.getResponseCode());
    }

    @Test
    public void getCityKeyGetterResponseCodeByCityNameIs503WhenAllowedNumberOfRequestsHasBeenExceeded(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        cityKeyGetter.getResponse(cityNameKeyword, cityName);
        assertEquals(503, cityKeyGetter.getResponseCode());
    }

    @Test
    public void getCityKeyGetterResponseCodeByCityPostcodeIs503WhenAllowedNumberOfRequestsHasBeenExceeded(){
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);
        cityKeyGetter.getResponse(postcodeKeyword, postcodeValue);
        assertEquals(503, cityKeyGetter.getResponseCode());
    }
}