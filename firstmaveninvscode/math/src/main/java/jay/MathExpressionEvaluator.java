package jay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class MathExpressionEvaluator 
{// this is tthe math.js api we are going to use
    private static final String API_URL = "http://api.mathjs.org/v4/";

    // this varibale defines maximum concurrent request which we need to  handle, we can change this as
    // as per our requirement
    private static final int MAX_CONCURRENT_REQUESTS = 50; 

        // asmentioned in problem we need solution to handle more request per second this can be done using
        //java.util.concurrent class and its features like  executor and executor service 
        //so this variable defines the rate of requat per second we can chnage it as per our need,as 
        // mentioned in problem statement.
    private static final int REQUESTS_PER_SECOND = 500; 


    // So to handel concurrent request 
    // we need to create a thread pool of maximum concurrent request we need to process
    //this means if we  create thread pool f 50 concurrent request 
    // Now using for loop  we can give each expression to each thread to evaluate  calling the function evaluate expression
    

    public static void main(String[] args) {
        List<String> expressions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (!(input = reader.readLine()).equals("end")) {
                expressions.add(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONCURRENT_REQUESTS);

        for (String expression : expressions) {
            executorService.execute(() -> {
                try {
                    evaluateExpression(expression);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            try {
                // this will make make the code to sleep for (1000 / REQUESTS_PER_SECOND) this much time
                // to evaluate the expression submitted.  and also it will prevent rate limitations of math.js
                Thread.sleep(1000 / REQUESTS_PER_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

    static String evaluateExpression(String expression) throws IOException {
        //create a string whcih includes api url and rexprsssion which will be used  to sent to api
        String apiUrl = API_URL + "?expr=" + expression;
        // creatinfg url for connection
        URL url = new URL(apiUrl);

        // establish connection with api using hhtpconnection class
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //stteing request type as get
        connection.setRequestMethod("GET");
         // getting response from math.js api
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            // reading response and showing output
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = in.readLine();
            System.out.println(expression + " => " + response);
            in.close();
        } else {
            System.err.println("Failed to evaluate expression: " + expression);
        }
        return apiUrl;
    }
}
