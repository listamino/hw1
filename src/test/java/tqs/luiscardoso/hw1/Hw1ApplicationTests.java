package tqs.luiscardoso.hw1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Hw1ApplicationTests {

    @Autowired
    private Cache cache;

    @Mock
    DarkSkyHandler darkSkyHandler;
    Forecast mock_forecast;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Before
    public void resetElements(){
        cache.clear();
        mock_forecast = new Forecast();
    }




    @Test
    public void canAddAndRemoveForecastToCache() {
        int curr_size = cache.size();
        cache.add(mock_forecast);
        assertEquals(cache.size(), curr_size + 1);
        cache.removeElement(mock_forecast);
        assertEquals(0,cache.size());
    }

    @Test
    public void canClearCache() {
        cache.add(mock_forecast);
        cache.add(mock_forecast);
        cache.clear();
        assertEquals(0, cache.size());
    }


    @Test
    public void cantAddExistingElementToCache(){
        mock_forecast.setLatitude(0);
        mock_forecast.setLongitude(0);
        cache.add(mock_forecast);
        cache.add(mock_forecast);
        assertEquals(1,cache.size());
    }

    @Test
    public void canReturnElementInCache(){
        mock_forecast.setLongitude(0);
        mock_forecast.setLatitude(0);
        cache.add(mock_forecast);
        assertEquals(cache.getForecast(0,0), mock_forecast);
    }

    @Test
    public void elementIsDeletedAfterTTL() {
        cache.add(mock_forecast, 12*60 + System.currentTimeMillis() / 1000L);
        cache.getCm().checkElements();
        assertEquals(0, cache.size());
    }




}
