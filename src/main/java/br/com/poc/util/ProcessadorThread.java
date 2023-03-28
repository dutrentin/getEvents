package br.com.poc.util;

import br.com.poc.service.EventReadService;
import br.com.poc.service.impl.EventReadServiceImpl;

public class ProcessadorThread extends Thread {
    
    private void delay() {
        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
    	System.out.print("Thread 01 \n");
    	double latitude = -23.70041;
        double longitude = -46.53713;
    	EventReadService eventReadService = new EventReadServiceImpl();
    	eventReadService.readEventsWithDistance(latitude, longitude,50);
    	delay();
    	
    }
}
