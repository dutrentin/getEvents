package br.com.controller;


import br.com.poc.service.impl.EventReadServiceImpl;

//import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {br.com.poc.service.impl.EventReadServiceImpl.class})
@WebMvcTest(EventReadServiceImpl.class)
@Import(EventReadServiceImpl.class)
public class EventReadControllerTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventReadServiceImpl eventReadServiceImpl;


    @Test
    public void shouldSuccess() throws Exception{
        //EasyRandomParameters parameters = new EasyRandomParameters();
        System.out.println();

    }


}
