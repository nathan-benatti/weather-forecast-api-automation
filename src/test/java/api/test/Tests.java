package api.test;

import api.endpoints.EndPoints;
import api.payload.WeatherForecast;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Random;

public class Tests {

    Faker faker;
    Random random;
    private String testSummary;

    @Test(priority = 1)
    public void testPostWeather() {
        WeatherForecast weatherPayload = new WeatherForecast();
        faker = new Faker();
        random = new Random();
        weatherPayload.setSummary(faker.toString());
        weatherPayload.setTemperatureC(random.nextInt((45 - -20) + 1) - 20);

        Response response = EndPoints.createWeather(weatherPayload.getSummary(), weatherPayload.getTemperatureC());
        response.then().log().all();

        System.out.println("   ");
        System.out.println("Summary: " + weatherPayload.getSummary());
        testSummary = weatherPayload.getSummary();
        System.out.println(testSummary);
        System.out.println("TemperatureC: " + weatherPayload.getTemperatureC());
    }

    @Test(priority = 2, dependsOnMethods = "testPostWeather")
    public void testGetWeather() {
        WeatherForecast weatherPayload = new WeatherForecast();
        weatherPayload.setSummary(testSummary);

        System.out.println("testSummary: " + testSummary);
        Response response = EndPoints.getWeather(weatherPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dependsOnMethods = "testPostWeather")
    public void testUpdateWeather() {
        WeatherForecast weatherPayload = new WeatherForecast();
        faker = new Faker();
        random = new Random();
        weatherPayload.setSummary(faker.toString());
        weatherPayload.setTemperatureC(random.nextInt((45 - -20) + 1) - 20);

        Response response = EndPoints.updateWeather(weatherPayload.getSummary(), weatherPayload.getTemperatureC(),new Date(),testSummary);
        response.then().log().all();

        testSummary = weatherPayload.getSummary();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4, dependsOnMethods = "testPostWeather")
    public void testRemoveWeather() {
        WeatherForecast weatherPayload = new WeatherForecast();
        weatherPayload.setSummary(testSummary);

        Response response = EndPoints.removeWeather(testSummary, weatherPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
