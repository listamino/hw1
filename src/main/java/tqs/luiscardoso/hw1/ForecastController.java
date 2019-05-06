package tqs.luiscardoso.hw1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ForecastController {

    @Autowired
    private Cache cache;

    //TODO: add regex
    @GetMapping("/forecast/{latitude},{longitude}")
    public Forecast forecast(@PathVariable double latitude, @PathVariable double longitude) {
        return cache.getForecast(latitude, longitude);
    }
}
