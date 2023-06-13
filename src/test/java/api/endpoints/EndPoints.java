package api.endpoints;

import static api.endpoints.Routes.put_url;
import static io.restassured.RestAssured.given;

import api.payload.WeatherForecast;
import com.github.javafaker.Weather;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EndPoints {
    public static Response createWeather(String summary,int temperatureC){
        // Request URL: http://localhost:5179/WeatherForecast?temperatureC=20&summary=sumeria
        // Header: -H 'accept: */*'

        Response response = given()
                .header("accept","*/*")
                .queryParam("temperatureC",temperatureC)
                .queryParam("summary",summary)
                .when()
                .post(Routes.post_url);
        System.out.println("Payload Summary: "+ summary);
        System.out.println("Payload TemperatureC: "+ temperatureC);
        return response;
    }

    public static Response getWeather(WeatherForecast payload){
        // Request URL: http://localhost:5179/WeatherForecast
        // Header: -H 'accept: text/plain'
        Response response = given()
                .header("accept","text/plain")
                .when()
                .get(Routes.get_url);
        return response;
    }

    public static Response updateWeather(String summary, int temperatureC, Date date, String keySummary) {
        WeatherForecast payload = new WeatherForecast();
        payload.setSummary(summary);
        payload.setTemperatureC(temperatureC);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String formattedDate = dateFormat.format(date);
        payload.setDateF(formattedDate);
        String url = put_url.replace("{summary}", keySummary);

        Response response = given()
                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .and()
                .body(payload)
                .when()
                .put(url)
                .then()
                .extract().response();
        return response;
    }

    public static Response removeWeather(String summary,WeatherForecast payload){
        // Request URL: http://localhost:5179/WeatherForecast/teste
        // Header -H 'accept: */*'
        Response response = given()
                .pathParam("summary",summary)
                .header("accept","*/*")
                .when()
                .delete(Routes.delete_url);
        return response;
    }
}
