package br.com.poc.service.impl;

import br.com.poc.dto.EventDTO;
import br.com.poc.entity.Event;
import br.com.poc.enuns.TypeDistanceEnum;
import br.com.poc.service.EventReadService;
import br.com.poc.util.DistanceCalculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("eventReadServiceImpl")
public class EventReadServiceImpl implements EventReadService {

    @Autowired
    private Environment env;


    private String arquivoCSV = "C://eventlog6.csv";
    BufferedReader br = null;
    String line = "";
    String csvDivisor = ",";
    String payloadDivisor = ">";
    String payloadDivisorEnd = "<";

    private List<EventDTO> events;

    @Override
    public List<EventDTO> readEvents(String entryValue){
        double distanceLimitDefault = Double.parseDouble(env.getProperty("proximity.limit.distance"));
        return readCSV(entryValue, distanceLimitDefault);
    }

    @Override
    public List<EventDTO> readEventsWithDistance(String entryValue,double distanceLimit){
        return readCSV(entryValue, distanceLimit);
    }

    public List<EventDTO> readCSV(String entryValue, double distanceLimit){
        events = new ArrayList<>();

        String[] columnsEntryValues = entryValue.split(csvDivisor);

        double latitude = Double.parseDouble(columnsEntryValues[TypeDistanceEnum.LATITUDE.getValueType()]);
        double longitude = Double.parseDouble(columnsEntryValues[TypeDistanceEnum.LONGITUDE.getValueType()]);


        try {

            br = new BufferedReader(new FileReader(arquivoCSV));

            while ((line = br.readLine()) != null) {
                if(!line.contains("device")){
                    String[] columns = line.split(csvDivisor);
                    if(columns.length >= 7){
                        String[] payloadColumns = line.split(payloadDivisor);
                        String getPayload = payloadColumns[1].split("\\<")[0];


                        EventDTO event = new EventDTO();
                        event.setDevice(columns[0]);
                        event.setPrefix(columns[1]);

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        format.setTimeZone(TimeZone.getTimeZone("GMT"));

                        Date instantCreateEvent = format.parse(columns[2]);

                        event.setInstantCreateEvent(instantCreateEvent);
                        event.setPayload(getPayload);

                        String latitudeColumn = columns[5];
                        String longitudeColumn = columns[6];

                        longitudeColumn = longitudeColumn.replaceAll("<","");
                        longitudeColumn = longitudeColumn.replaceAll("\"", "");

                        event.setLatitude(Double.parseDouble(latitudeColumn));
                        event.setLongitude(Double.parseDouble(longitudeColumn));
                        event.setCompany(columns[7]);

                        boolean nearByArea =  distanceCalculateInMeters(latitude, longitude, event, distanceLimit);
                        if(nearByArea){
                            events.add(event);
                        }
                    }else{
                        System.out.println("A planilha csv não está no formato correto. O número de colunas está menor que o padrão.");
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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

        events.sort(Comparator.comparing(EventDTO::getInstantCreateEvent).reversed());

        return events;

    }

    private boolean distanceCalculateInMeters(double latitudeParam, double longitudeParam, EventDTO event, double distanceLimit){
        if(DistanceCalculate.calculateInMeters(latitudeParam, longitudeParam, event) > distanceLimit){
            return false;
        }
        return true;
    }

    public List<EventDTO> readMysql(){


        return events;
    }

}
