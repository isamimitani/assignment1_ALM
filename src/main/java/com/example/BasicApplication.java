package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
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
            System.out.println("2.Göteorg");
            System.out.println("3.Malmö");
        }
        
        public int getUserInput(){
            
            return 0;
        }
        
        public String getWetherForecast(int numberOfTown){
                RestTemplate restTemplate = new RestTemplate();
                String url = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/16.158/lat/58.5812/data.json";
                String res = restTemplate.getForObject(url, String.class);
                System.out.println(res);
                return res;
        }
        
        public void showResult(String res){
            
        }
}
