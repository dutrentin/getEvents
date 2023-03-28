package br.com.poc.service.impl;

import br.com.poc.dto.EventDTO;
import br.com.poc.service.EventReadService;
import br.com.poc.util.DistanceCalculate;
import br.com.poc.util.PropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(EventReadServiceImpl.class);

    //private String arquivoCSV = "C://eventlog.csv";
    private String arquivoCSV = EventReadServiceImpl.class.getClassLoader().getResource("eventlog.csv").getFile() ;

    
    public static final String CSV_DIVISOR = ",";
    public static final String PAYLOAD_DIVISOR = ">";
    public static final String PAYLOAD_DIVISOR_END = "<";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_OUT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private List<EventDTO> events;
    
    @Value("${proximity.limit.distance}")
    private Double distanceLimitDefault;

    @Override
    public List<EventDTO> readEvents(double entryValueLatitude,double entryValueLongitude ){
        return filterEvents(entryValueLatitude, entryValueLongitude, distanceLimitDefault);
    }

    @Override
    public List<EventDTO> readEventsWithDistance(double entryValueLatitude, double entryValueLongitude, double proximityLimitDistance){
        return filterEvents(entryValueLatitude, entryValueLongitude, proximityLimitDistance);
    }

    public List<EventDTO> filterEvents(double entryValueLatitude,double entryValueLongitude, double distanceLimit){
        BufferedReader br = null;
        String line = "";

        events = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(arquivoCSV));

            while ((line = br.readLine()) != null) {
                if(!line.contains("device")){
                    String[] columns = line.split(CSV_DIVISOR);

                    if(columns.length >= 7){
                        String[] payloadColumns = line.split(PAYLOAD_DIVISOR);
                        String getPayload = payloadColumns[1].split("\\<")[0];

                        EventDTO event = setValuesEvent(columns, getPayload);

                        boolean nearByArea =  distanceCalculateInMeters(entryValueLatitude, entryValueLongitude, event, distanceLimit);

                        if(nearByArea){
                            events.add(event);
                        }

                    }else{
                        LOGGER.error("A planilha csv não está no formato correto. O número de colunas está menor que o padrão.");
                        System.out.println("A planilha csv não está no formato correto. O número de colunas está menor que o padrão.");
                    }
                }
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("Arquivo não encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("Arquivo não encontrado.");
            e.printStackTrace();
        } catch (ParseException e) {
            LOGGER.error("Erro na formatação do arquivo.");
            e.printStackTrace();
        } finally {
            finalizarArquivo(br);
        }

        events.sort(Comparator.comparing(EventDTO::getInstantCreateEvent).reversed());
        printValuesInConsole();
        return events;

    }

    private void printValuesInConsole(){
        for(EventDTO event : events){
            StringBuilder stringToOut = new StringBuilder();
            stringToOut.append(event.getDevice()).append(CSV_DIVISOR)
                    .append(event.getDistanceInMeters()).append(CSV_DIVISOR)
                    .append(DATE_FORMAT_OUT.format(event.getInstantCreateEvent())).append(CSV_DIVISOR)
                    .append("\">"+event.getPayload()+"<\"");

            System.out.println(stringToOut.toString().substring(1,stringToOut.length()));
        }
    }

    private void finalizarArquivo(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private EventDTO setValuesEvent(String[] columns, String getPayload) throws ParseException {
        EventDTO event = new EventDTO();
        event.setDevice(columns[0]);
        event.setPrefix(columns[1]);


        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date instantCreateEvent = DATE_FORMAT.parse(columns[2]);

        event.setInstantCreateEvent(instantCreateEvent);
        event.setPayload(getPayload);

        String latitudeColumn = columns[5];
        String longitudeColumn = columns[6];

        longitudeColumn = longitudeColumn.replaceAll("<","");
        longitudeColumn = longitudeColumn.replaceAll("\"", "");

        event.setLatitude(Double.parseDouble(latitudeColumn));
        event.setLongitude(Double.parseDouble(longitudeColumn));
        event.setCompany(columns[7]);

        return event;
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
