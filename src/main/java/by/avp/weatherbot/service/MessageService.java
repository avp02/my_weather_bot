package by.avp.weatherbot.service;

import by.avp.weatherbot.bean.Weather;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MessageService {

    private final Weather weather;

    public MessageService(Weather weather) {
        this.weather = weather;
    }

    public SendMessage getMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        Message message = update.getMessage();
        if (update != null) {
            sendMessage.setChatId(message.getChatId());
            if (message != null && message.hasText()) {
                String answer = message.getText();
                if (answer.equals("/start")) {
                    return sendMessage.setText("Hello!");
                } else if (answer.equals("/help")) {
                    return sendMessage.setText("Здесь будут настройки");
                } else if (answer.equals("/settings")) {
                    return sendMessage.setText("Здесь будут настройки");
                }
            }
        }
        try {
            return sendMessage.setText(WeatherService.getWeather(message.getText(), weather));
        } catch (Exception e){
            return sendMessage.setText("Еще чего!");
        }
    }
}
