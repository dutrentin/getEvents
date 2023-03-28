package br.com.controller;


import br.com.poc.dto.EventDTO;
import br.com.poc.service.EventReadService;
import br.com.poc.service.impl.EventReadServiceImpl;

//import org.jeasy.random.EasyRandomParameters;
import  static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {br.com.poc.service.impl.EventReadServiceImpl.class})
@WebMvcTest(EventReadServiceImpl.class)
@Import(EventReadServiceImpl.class)
public class EventReadServiceImplTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventReadServiceImpl eventReadServiceImpl;
    
    @Autowired
    private EventReadService eventReadService;


    @BeforeEach
    public void setup(){
        //Carregar apenas as classes selecionados no contexto
        standaloneSetup(this.eventReadServiceImpl);
    }
    

    @Test
    public void readCsv() throws Exception{
        double latitude = -23.70041;
        double longitude = -46.53713;
        double limitDistance = 50;

        
        
        //List<EventDTO> events = eventReadService.readEventsWithDistance(latitude, longitude, limitDistance);
        
        EventDTO event = Mockito.mock(EventDTO.class);
        List<EventDTO> events = Collections.singletonList(event);
        
        Mockito.when(eventReadService.readEventsWithDistance(latitude, longitude, limitDistance)).thenReturn(events);
        
        //Assertions.assert
        
        //given().accept(this.eventReadServiceImpl.readEvents(latitude,longitude ));

        //EasyRandomParameters parameters = new EasyRandomParameters();
        System.out.println();

    }


}
