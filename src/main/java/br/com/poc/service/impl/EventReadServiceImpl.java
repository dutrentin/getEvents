package br.com.poc.service.impl;

import br.com.poc.dto.EventDTO;
import br.com.poc.entity.Event;
import br.com.poc.enuns.TypeDistanceEnum;
import br.com.poc.service.EventReadService;
import jxl.read.biff.BiffException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventReadServiceImpl implements EventReadService {


    private String arquivoCSV = "C://eventlog.csv";
    BufferedReader br = null;
    String line = "";
    String csvDivisor = ",";

    private List<Event> events = new ArrayList<>();

    @Override
    public List<Event> readEvents(String entryValue){
        return readCSV(entryValue);
    }

    public List<Event> readCSV(String entryValue){
        String[] columnsEntryValues = entryValue.split(csvDivisor);

        double latitude = Double.parseDouble(columnsEntryValues[TypeDistanceEnum.LATITUDE.getValueType()]);
        double longitude = Double.parseDouble(columnsEntryValues[TypeDistanceEnum.LONGITUDE.getValueType()]);


        try {

            br = new BufferedReader(new FileReader(arquivoCSV));

            while ((line = br.readLine()) != null) {
                if(!line.contains("device")){
                    String[] columns = line.split(csvDivisor);

                    EventDTO event = new EventDTO();

                    String latitudeColumn = columns[5];
                    String longitudeColumn = columns[6];
                    //longitudeColumn = "-" + longitudeColumn.substring(2, longitudeColumn.length());
                    longitudeColumn = longitudeColumn.replaceAll("<","");
                    longitudeColumn = longitudeColumn.replaceAll("\"", "");
                    //longitudeColumn = longitudeColumn.substring(0, longitudeColumn.length() - 1);

                    event.setLatitude(Double.parseDouble(latitudeColumn));
                    event.setLongitude(Double.parseDouble(longitudeColumn));

                    distanceCalculate(latitude, longitude, event);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return events;

    }

    private double distanceCalculate(double latitudeParam, double longitudeParam, EventDTO event){

        double earthRadius = 6371;//kilometers
        double dLat = Math.toRadians(event.getLatitude() - latitudeParam);
        double dLng = Math.toRadians(event.getLongitude() - longitudeParam);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latitudeParam))
                * Math.cos(Math.toRadians(event.getLatitude()));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;
        double distInMeters =  dist * 1000;

        if(distInMeters < 50){
            System.out.print("Menor que 50m \n");
            System.out.printf("Distância de: %.2f metros \n", distInMeters);
        }

        return distInMeters;
    }

    public List<Event> readMysql(){


        return events;
    }

}
