package by.avp.weatherbot.service;

import by.avp.weatherbot.bean.Weather;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {

    @SneakyThrows
    public static String getWeather(String message, Weather weather) {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=63638f1d983b9303825065c9bb22e4ad");
        Scanner sc = new Scanner((InputStream) url.getContent());
        String result = "";
        while (sc.hasNext()) {
            result +=sc.nextLine();
        }
        JSONObject jsonObject = new JSONObject(result);
        weather.setName(jsonObject.getString("name"));

        JSONObject main = jsonObject.getJSONObject("main");
        weather.setTemp(main.getDouble("temp"));
        weather.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = jsonObject.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            weather.setIcon((String) obj.get("icon"));
            weather.setMain((String) obj.get("main"));
        }
        return "City " + weather.getName() + "\n" +
                "temperature " + weather.getTemp() + "\n" +
                "humidity " + weather.getHumidity() + "\n" +
                "main" + weather.getMain() + "\n" +
                "https://openweathermap.org/img/w/" +
                 weather.getIcon() + ".png";

    }

}
