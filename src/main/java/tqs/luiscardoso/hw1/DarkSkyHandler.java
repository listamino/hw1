package tqs.luiscardoso.hw1;

import org.springframework.web.client.RestTemplate;

public class DarkSkyHandler {

    private static RestTemplate restTemplate = new RestTemplate();

    public static Forecast getForecast(double latitude, double longitude) {
        String template = "https://api.darksky.net/forecast/b8734c431dad0952dc6172d47370a235/" + latitude + "," + longitude + "?units=si";
        return restTemplate.getForObject(template, Forecast.class);
    }
}
