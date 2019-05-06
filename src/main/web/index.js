const Http = new XMLHttpRequest();

function getForecast() {
    var latitude = document.getElementById("latitude").value;
    var longitude = document.getElementById("longitude").value;
    if(longitude != "" && latitude!= "") {
        var url = "http://localhost:8080/forecast/" + latitude + "," + longitude;
        Http.open("GET", url);
        Http.send();
    }
}

Http.onreadystatechange=function(){
    if(this.readyState==4 && this.status==200) {
        var json_text = Http.responseText;
        var json_obj = JSON.parse(json_text);
        var forecast_div = document.getElementById("forecast");
        forecast_div.innerHTML = "";
        var counter = 0
        forecast_div.innerHTML += "<h3> Week Summary: " + json_obj.daily.summary + "</h3>";

        json_obj.daily.data.forEach(element => {
            var weather_html = "<div class='weather'><h3>Day " + counter + "</h3>"
            weather_html += "<h4>" + element["summary"] + "</h4>"
            weather_html += "<p>Precipitation probability: " + element["precipProbability"] + "</p>"
            weather_html += "<p>Pressure: " + element["pressure"] + " Pa</p>"
            weather_html += "<p>Humidity: " + element["humidity"] + "</p>"
            weather_html += "<p>Highest Temperature: " + element["temperatureHigh"] + "ºC</p>"
            weather_html += "<p>Lowest Temperature: " + element["temperatureLow"] + "ºC</p></div>"
            forecast_div.innerHTML += weather_html
            counter++;
        });

    }
}