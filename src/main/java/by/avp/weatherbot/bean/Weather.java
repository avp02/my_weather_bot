package by.avp.weatherbot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private String name;
    private Double temp;
    private Double humidity;
    private String icon;
    private String main;
}
