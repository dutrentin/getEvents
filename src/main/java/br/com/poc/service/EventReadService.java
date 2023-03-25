package br.com.poc.service;

import br.com.poc.entity.Event;

import java.util.List;

public interface EventReadService {

    List<Event> readEvents(String entryValue);

}
