package br.com.poc.service;

import br.com.poc.dto.EventDTO;

import java.util.List;

public interface EventReadService {

    List<EventDTO> readEvents(double entryValueLatitude, double entryValueLongitude);

    List<EventDTO> readEventsWithDistance(double entryValueLatitude, double entryValueLongitude, double proximityLimitDistance);

}
