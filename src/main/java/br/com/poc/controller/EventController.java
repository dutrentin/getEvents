package br.com.poc.controller;


import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class EventController {

    private static final Logger log = Logger.getLogger(EventController.class);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getEvents() throws IOException, BiffException {


        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
