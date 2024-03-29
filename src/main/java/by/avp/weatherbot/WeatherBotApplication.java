package by.avp.weatherbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class WeatherBotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(WeatherBotApplication.class, args);
	}

}
