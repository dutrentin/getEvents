package br.com.poc.dto;


import java.util.Date;

public class EventDTO {

    private String device;
    private String prefix;
    private Date instantCreateEvent;
    private String payload;
    private String company;
    private double latitude;
    private double longitude;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Date getInstantCreateEvent() {
        return instantCreateEvent;
    }

    public void setInstantCreateEvent(Date instantCreateEvent) {
        this.instantCreateEvent = instantCreateEvent;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
