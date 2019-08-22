package com.example;

import java.util.Scanner;
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
                RestTemplate restTemplate = new RestTemplate();
                String url = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/16.158/lat/58.5812/data.json";
                String res = restTemplate.getForObject(url, String.class);
                System.out.println(res);
                return res;
        }
        
        public void showResult(String res){
            
        }
}
