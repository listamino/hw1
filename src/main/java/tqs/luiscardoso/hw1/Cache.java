package tqs.luiscardoso.hw1;

import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class Cache {

    private Map<Forecast, Long> forecasts;
    private CacheManager cm;

    public Cache() {
        forecasts = new HashMap<>();
        cm = new CacheManager(forecasts, this, 10*60L);
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
        this.add(forecast);
        return forecast;
    }

    public void removeElement(Forecast forecast) {
        forecasts.remove(forecast);
    }

    public CacheManager getCm() {
        return cm;
    }

    public void add(Forecast forecast) {
        if (!forecasts.keySet().contains(forecast)) {
            forecasts.put(forecast, System.currentTimeMillis() / 1000L);
        }
    }

    public void add(Forecast forecast, long time_since_insertion) {
        if (!forecasts.keySet().contains(forecast)) {
            forecasts.put(forecast, time_since_insertion);
        }
    }

    public int size() {
        return forecasts.size();
    }

    public void clear() {
        forecasts.clear();
    }
}
