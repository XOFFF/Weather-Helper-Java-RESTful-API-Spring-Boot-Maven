import java.time.LocalDate;

public class DayForecast {
    private LocalDate date;
    private float minTemp;
    private float maxTemp;

    public DayForecast(LocalDate date, float minTemp, float maxTemp){
        this.date = date;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void showData(){
        System.out.println("Date: " + date);
        System.out.println("Min temp: " + minTemp + " C");
        System.out.println("Max temp: " + maxTemp + " C");
        System.out.println("-----------------------");
    }
}
