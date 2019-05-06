package tqs.luiscardoso.hw1;

import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class Cache {

    private Map<Forecast, Long> forecasts;

    public Cache() {
        forecasts = new HashMap<>();
        CacheManager cm = new CacheManager(forecasts, this);
        cm.start();
    }

    public Forecast getForecast(double latitude, double longitude) {
        Optional<Forecast> match = forecasts.keySet().stream()
                .filter(forecast -> forecast.getLatitude() == latitude && forecast.getLongitude() == longitude)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        }
        Forecast forecast = DarkSkyHandler.getForecast(latitude, longitude);
        forecasts.put(forecast, System.currentTimeMillis() / 1000L);
        return forecast;
    }

    public void removeElement(Forecast forecast) {
        forecasts.remove(forecast);
    }
}
