package br.com;


import br.com.poc.dto.EventDTO;
import br.com.poc.service.EventReadService;
import br.com.poc.service.impl.EventReadServiceImpl;

//import org.jeasy.random.EasyRandomParameters;
import  static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    //@Autowired
    //private EventReadServiceImpl eventReadServiceImpl;

    @Autowired
    private EventReadServiceImpl eventReadService;


    @BeforeEach
    public void setup(){
        //Carregar apenas as classes selecionados no contexto
        standaloneSetup(this.eventReadService);
    }


    @Test
    public void readCsvSucessWithDistance() throws Exception{
        double latitude = -23.70041;
        double longitude = -46.53713;
        double limitDistance = 50;


        EventDTO event = Mockito.mock(EventDTO.class);
        EventReadServiceImpl mock = org.mockito.Mockito.mock(EventReadServiceImpl.class);
        
        //Mockito.when(mock.readEventsWithDistance(latitude, longitude, limitDistance)).thenReturn(List.of(event));
        
        List<EventDTO> events = mock.readEventsWithDistance(latitude, longitude, limitDistance);
        
        assertEquals(1, events.size());
        assertTrue(!events.isEmpty());

    }
    
    @Test
    public void readCsvSucessWithDistanceDefault() throws Exception{
    	double latitude = -23.70041;
        double longitude = -46.53713;
        double limitDistance = 50;


        EventDTO event = Mockito.mock(EventDTO.class);
        EventReadServiceImpl mock = org.mockito.Mockito.mock(EventReadServiceImpl.class);
        
        //Mockito.when(mock.readEvents(latitude, longitude)).thenReturn(List.of(event));
        
        List<EventDTO> events = mock.readEvents(latitude, longitude);
        
        assertEquals(1, events.size());
        assertTrue(!events.isEmpty());
    }
    

}
