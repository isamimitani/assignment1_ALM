package com.example;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BasicApplication.class, args);
                BasicApplication app = new BasicApplication();
                app.showMessage();
                int num = app.getUserInput();
                String res = app.getWetherForecast(num);
                app.showResult(res);
	}
        
        public static void showMessage(){
            System.out.println("Choose a number to show weather condition.");
            System.out.println("1.Stockholm");
            System.out.println("2.Göteborg");
            System.out.println("3.Malmö");
        }
        
        public int getUserInput(){
            Scanner myscan = new Scanner(System.in);
            System.out.println("Enter the number of your city: ");
            while (!myscan.hasNextInt()) {
                System.out.println("Invalid number, input again.");
                myscan.next(); 
            }
            int citynumber = myscan.nextInt();                    
            return citynumber;
        }
        
        public String getWetherForecast(int numberOfTown){
            double latitude = 59.334591;
            double longitude = 18.063240;
            switch(numberOfTown){
                case 1:
                    System.out.print("One hour later, Stockholm's temperature will be ");
                    latitude = Town.STOCKHOLM.latitude();
                    longitude = Town.STOCKHOLM.longitude();
                    break;
                case 2:
                    System.out.print("One hour later, Göteborg's temperature will be ");
                    latitude = Town.GOTHENBURG.latitude();
                    longitude = Town.GOTHENBURG.longitude();
                    break;
                case 3:
                    System.out.print("One hour later, Malmö's temperature will be ");
                    latitude = Town.MALMO.latitude();
                    longitude = Town.MALMO.longitude();
                    break;
            }
                RestTemplate restTemplate = new RestTemplate();
                String url = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/" +  longitude + "/lat/" + latitude + "/data.json";
                String res = restTemplate.getForObject(url, String.class);
//                System.out.println(res);
                return res;
        }
        
        public void showResult(String res) throws JSONException{
            JSONObject obj = new JSONObject(res);
            JSONArray oneHourLater = obj.getJSONArray("timeSeries");
            JSONArray parameters = oneHourLater.getJSONObject(0).getJSONArray("parameters");
            for(int i=0; i<parameters.length(); i++){
                String getAllNames = parameters.getJSONObject(i).getString("name");
                if(getAllNames.equals("t")){
//                    System.out.println("find");
                    JSONArray temperature = parameters.getJSONObject(i).getJSONArray("values");                    
                    System.out.println(temperature.get(0) + "C");
                }               
            }
        }
}
