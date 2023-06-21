import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String apiKey = "hHQoU0Adpo6wzZi9PK198lzBPK8vA75z";

        while(true) {
            String choice = AskForChoice();
            GetForecastData(apiKey, choice);
        }
    }

    public static String AskForChoice(){
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("Hello! Do you want to write down city NAME or POSTCODE?");
        while (true) {
            System.out.println("Please, type 1 for NAME, or type 2 for POSTCODE.");
            choice = scanner.next();
            if(choice.equals("1") || choice.equals("2")){
                break;
            }
        }
        return choice;
    }

    public static void GetForecastData(String apiKey, String choice){
        String cityKey = "";
        CityKeyGetter cityKeyGetter = new CityKeyGetter(apiKey);

        if (choice.equals("1")) { // user wants to write city name
            cityKey = cityKeyGetter.GetCityKeyByName();
        }
        else if (choice.equals("2")) { // user wants to write city postCode
            cityKey = cityKeyGetter.GetCityKeyByPostCode();
        }

        FiveDaysTempForecast fiveDaysTempForecast = new FiveDaysTempForecast(apiKey, cityKey);
        fiveDaysTempForecast.GetFiveDaysTemperatureForecast();
    }
}