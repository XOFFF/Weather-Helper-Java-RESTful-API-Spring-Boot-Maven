public class RequestsCounter {
    private static int requestsCounter = 0;

    public RequestsCounter(){}

    public static void addRequestToCounter(){
        requestsCounter++;
    }
    public void showHowManyRequestsProgramUsed(){
        System.out.println("The program used " + requestsCounter + " requests to the weather service during this session.");
    }
}