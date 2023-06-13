package api.test;
import api.endpoints.EndPoints;
import api.payload.WeatherForecast;
import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Random;

public class Tests {

    Faker faker;
    WeatherForecast weatherPayload;
    Random random;

    @BeforeClass
    public void setupData(){
        faker = new Faker();
        random = new Random();
        weatherPayload = new WeatherForecast();
        weatherPayload.setSummary(faker.toString());
        weatherPayload.setTemperatureC(random.nextInt((45 - -20) + 1) -20);
        weatherPayload.setDate(new Date());
    }
    @Test(priority = 1)
    public void testPostWeather(){

        Response response = EndPoints.createWeather(weatherPayload.getSummary(), weatherPayload.getTemperatureC());
        response.then().log().all();

        System.out.println("   ");
        System.out.println("Summary: "+weatherPayload.getSummary());
        System.out.println("TemperaturaC: "+weatherPayload.getTemperatureC());

        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2)
    public void testGetWeather()
    {
        Response response = EndPoints.getWeather(weatherPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateWeather()
    {
        Response response = EndPoints.updateWeather(weatherPayload.getSummary(),weatherPayload.getTemperatureC(),weatherPayload.getDate(),"string");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 4)
    public void testRemoveWeather()
    {
        Response response = EndPoints.removeWeather(weatherPayload.getSummary(), weatherPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
