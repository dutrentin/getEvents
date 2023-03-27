package br.com.controller;


import br.com.poc.service.impl.EventReadServiceImpl;

//import org.jeasy.random.EasyRandomParameters;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
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


    @BeforeEach
    public void setup(){
        //Carregar apenas as classes selecionados no contexto
        RestAssuredMockMvc.standaloneSetup(this.eventReadServiceImpl);
    }

    @Test
    public void readCsv() throws Exception{
        //EasyRandomParameters parameters = new EasyRandomParameters();
        System.out.println();

    }


}
