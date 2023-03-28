package br.com.poc.dto;


import java.util.Comparator;
import java.util.Date;

public class EventDTO implements Comparator<EventDTO> {

    private String device;
    private String prefix;
    private Date instantCreateEvent;
    private String payload;
    private String company;
    private double latitude;
    private double longitude;
    private double distanceInMeters;

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

    public double getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(double distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    @Override
    public int compare(EventDTO o1, EventDTO o2) {
        if(o1.getInstantCreateEvent().after(o2.getInstantCreateEvent())){
            return -1;
        }
        if(o1.getInstantCreateEvent().before(o2.getInstantCreateEvent())){
            return 1;
        }
        return 0;
    }
}
