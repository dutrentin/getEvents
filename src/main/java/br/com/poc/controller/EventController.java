package br.com.poc.controller;


import br.com.poc.dto.EventDTO;
import br.com.poc.service.EventReadService;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class EventController {

    private static final Logger log = Logger.getLogger(EventController.class);

    @Autowired
    private EventReadService eventReadService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<EventDTO>> getEvents(@RequestParam("limitDistance") Integer limitDistance, @RequestParam("param") String param) throws IOException, BiffException {

        return ResponseEntity.status(HttpStatus.OK).body(eventReadService.readEventsWithDistance(param, limitDistance.doubleValue()));
    }

}
