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
                    System.out.print(temperature.get(0) + "C. Weather will be ");
                }
                if(getAllNames.equals("Wsymb2")){
                    JSONArray symble = parameters.getJSONObject(i).getJSONArray("values");  
                    int symbleNum = (Integer) symble.get(0);
                    showSymble(symbleNum);
                }
            }
        }
        
        private void showSymble(int num){
            switch(num){
                case 1:
                    System.out.println("Clear sky.");
                    break;
                case 2:
                    System.out.println("Nearly clear sky.");
                    break;
                case 3:
                    System.out.println("Variable cloudiness.");
                    break;
                case 4:
                    System.out.println("Variable cloudiness.");
                    break;
                case 5:
                    System.out.println("Cloudy sky.");
                    break;
                case 6:
                    System.out.println("Overcast.");
                    break;
                case 7:
                    System.out.println("Fog.");
                    break;
                case 8:
                    System.out.println("Light rain showers.");
                    break;
                case 9:
                    System.out.println("Moderate rain showers.");
                    break;
                case 10:
                    System.out.println("Heavy rain showers.");
                    break;
                case 11:
                    System.out.println("Thunderstorm.");
                    break;
                case 12:
                    System.out.println("Light sleet showers.");
                    break;
                case 13:
                    System.out.println("Moderate sleet showers.");
                    break;
                case 14:
                    System.out.println("Heavy sleet showers.");
                    break;
                case 15:
                    System.out.println("Light snow showers.");
                    break;
                case 16:
                    System.out.println("Moderate snow showers.");
                    break;
                case 17:
                    System.out.println("Heavy snow showers.");
                    break;
                case 18:
                    System.out.println("Light rain.");
                    break;
                case 19:
                    System.out.println("Moderate rain.");
                    break;
                case 20:
                    System.out.println("Heavy rain.");
                    break;
                case 21:
                    System.out.println("Thunder.");
                    break;
                case 22:
                    System.out.println("Thunder.");
                    break;
                case 23:
                    System.out.println("Moderate sleet.");
                    break;
                case 24:
                    System.out.println("Heavy sleet.");
                    break;
                case 25:
                    System.out.println("Light snowfall.");
                    break;
                case 26:
                    System.out.println("Moderate snowfall.");
                    break;
                case 27:
                    System.out.println("Heavy snowfall.");
                    break;                    
            }
        }
}
