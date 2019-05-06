package tqs.luiscardoso.hw1;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CacheManager extends Thread {

    Map<Forecast, Long> forecasts;
    Cache cache;

    public CacheManager(Map<Forecast, Long> forecasts, Cache cache) {
        this.forecasts = forecasts;
        this.cache = cache;
    }

    public void run() {
        while (true) {
            List<Forecast> removeList = forecasts.keySet().stream()
                    .filter(forecast -> forecasts.get(forecast) + 10*60 > System.currentTimeMillis() / 1000L )
                    .collect(Collectors.toList());
            removeList.stream().forEach(n -> cache.removeElement(n));
            try {
                Thread.sleep(60*1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
