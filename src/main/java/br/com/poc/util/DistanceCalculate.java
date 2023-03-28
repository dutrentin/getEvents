package br.com.poc.util;

import br.com.poc.dto.EventDTO;

public class DistanceCalculate {

    public static double calculateInMeters(double latitudeParam, double longitudeParam, EventDTO event){

        double distKm = distanceInKilometers(latitudeParam, longitudeParam, event);
        event.setDistanceInMeters(distKm * 1000);

        return distKm * 1000;
    }

    public static double calculateToKilometers(double latitudeParam, double longitudeParam, EventDTO event){
            return distanceInKilometers(latitudeParam, longitudeParam, event);
    }


    private static double distanceInKilometers(double latitudeParam, double longitudeParam, EventDTO event) {
        double earthRadius = 6371;//kilometers
        double dLat = Math.toRadians(event.getLatitude() - latitudeParam);
        double dLng = Math.toRadians(event.getLongitude() - longitudeParam);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latitudeParam))
                * Math.cos(Math.toRadians(event.getLatitude()));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
}
