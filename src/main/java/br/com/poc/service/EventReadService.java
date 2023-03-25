package br.com.poc.service;

import br.com.poc.dto.EventDTO;

import java.util.List;

public interface EventReadService {

    List<EventDTO> readEvents(String entryValue);

}
