package com.example.weatherapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CurrentWeather {
    private String locationLabel;
    private String icon;
    private String summary;
    private long time;
    private double temperature;
    private double humidity;
    private double prepChance;

    public String getTimezone() {
        return timezone;
    }

    private String timezone;

    public CurrentWeather() {
    }

    public CurrentWeather(String locationLabel, String icon, String summary, long time, double temperature, double humidity, double prepChance, String timezone) {
        this.locationLabel = locationLabel;
        this.icon = icon;
        this.summary = summary;
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.prepChance = prepChance;
        this.timezone = timezone;
    }

    public String getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel;
    }

    public String getIcon() {
        return icon;
    }

    public int getIconID(){
        int iconId = R.drawable.clear_day;

        switch (icon){
            case "clear-day":
                iconId = R.drawable.clear_day;
                break;
            case "clear-night":
                iconId = R.drawable.clear_night;
                break;
            case "rain":
                iconId = R.drawable.rain;
                break;
            case "snow":
                iconId = R.drawable.snow;
                break;
            case "sleet":
                iconId = R.drawable.sleet;
                break;
            case "wind":
                iconId = R.drawable.wind;
                break;
            case "fog":
                iconId = R.drawable.fog;
                break;
            case "cloudy":
                iconId = R.drawable.cloudy;
                break;
            case "partly-cloudy-day":
                iconId = R.drawable.partly_cloudy;
                break;
            case "partly-cloudy-night":
                iconId = R.drawable.cloudy_night;
                break;

        }
        return iconId;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public long getTime() {
        return time;
    }

    public String getFormattedTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));

        Date dateTime = new Date(time *1000);
        return formatter.format(dateTime);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrepChance() {
        return prepChance;
    }

    public void setPrepChance(double prepChance) {
        this.prepChance = prepChance;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
