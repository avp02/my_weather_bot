package by.avp.weatherbot;

import by.avp.weatherbot.button.reply.ReplyButtons;
import by.avp.weatherbot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("application.properties")
public class WeatherBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String userName;
    @Value("${bot.token}")
    private String token;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ReplyButtons replyButtons;


    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = messageService.getMessage(update);
        try {
            replyButtons.setButton(sendMessage);
            execute(sendMessage);
        } catch(TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
