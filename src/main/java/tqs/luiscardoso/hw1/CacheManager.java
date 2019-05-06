package tqs.luiscardoso.hw1;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CacheManager extends Thread {

    Map<Forecast, Long> forecasts;
    double time_to_live;
    Cache cache;

    public CacheManager(Map<Forecast, Long> forecasts, Cache cache, Long time_to_live) {
        this.forecasts = forecasts;
        this.cache = cache;
        this.time_to_live = time_to_live;
    }

    @Override
    public void run() {
        while (true) {
            checkElements();
            try {
                Thread.sleep(60*1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void checkElements() {
        List<Forecast> removeList = forecasts.keySet().stream()
                .filter(forecast -> forecasts.get(forecast) + time_to_live > System.currentTimeMillis() / 1000L )
                .collect(Collectors.toList());
        removeList.stream().forEach(n -> cache.removeElement(n));
    }
}
