import org.json.simple.JSONObject;

public abstract class TemperatureConverter {
    public static float convertTemperature(JSONObject dayTempData, String value){
        JSONObject dayTempLimit = (JSONObject) dayTempData.get(value);
        String temp = dayTempLimit.get("Value").toString();
        float tempInF = Float.parseFloat(temp);
        float tempInC = convertToCelsius(tempInF);
        tempInC = roundCelsius(tempInC);
        return tempInC;
    }

    public static float convertToCelsius(float fahrenheit){
        float celsius = ((fahrenheit - 32) * 5) / 9;
        return celsius;
    }

    public static float roundCelsius(float celsius){
        String roundedCelsiusStr = String.format("%.1f", celsius);
        roundedCelsiusStr = roundedCelsiusStr.replaceAll(",", ".");
        float roundedCelsius = Float.parseFloat(roundedCelsiusStr);
        return roundedCelsius;
    }
}