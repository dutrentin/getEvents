package br.com.poc.util;

import br.com.poc.dto.EventDTO;

public class ProcessorThread extends Thread {
    private int idThread;
    private EventDTO eventDTO;
 
    public ProcessorThread(int id, EventDTO eventDTO) {
        this.idThread = id;
        this.eventDTO = eventDTO;
    }
    
    
    private void delay() {
        try {
            Thread.sleep((long) (Math.random() * 10000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}

