package api.payload;

import java.util.Date;


public class WeatherForecast {

//    Date date;
    String dateF;
    int temperatureC;
    String summary;

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public int getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(int temperatureC) {
        this.temperatureC = temperatureC;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}

