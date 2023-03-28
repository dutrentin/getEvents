package br.com.poc.util;

public class ConcurrentGerenate {
	
	public static void main(String[] args) {
		
	    for (int i = 0; i < 10; i++) {
	    	ProcessadorThread thread01 = new ProcessadorThread();
	    	ProcessadorThread02 thread02 = new ProcessadorThread02();
	    	thread01.setName("thread01");
	    	thread02.setName("thread02");
	    	
	    	thread01.start();
	    	thread02.start();
	    	
	    	//aguarda a finalização das tarefas
	        try {
	        	thread01.join();
	        	thread02.join();
	        } catch (InterruptedException ex) {
	            ex.printStackTrace();
	        }
	    	
	    }
		
	}

}
