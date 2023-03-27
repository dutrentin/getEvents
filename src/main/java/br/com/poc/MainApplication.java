package br.com.poc;


import br.com.poc.dto.EventDTO;
import br.com.poc.service.EventReadService;
import br.com.poc.service.impl.EventReadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class MainApplication {

	@Bean(name = "flushBase")
    public String flushBase() {
		return new String();
    }

    private EventReadService eventReadService;

	public static void main(String[] args) {

		SpringApplication.run(MainApplication.class, args);

		Scanner ler = new Scanner(System.in);

		String entryValue01 = "";
		String entryValue02 = "";
		String entryValue03 = "";

		while(1 > 0){

			System.out.printf("Digite:");

			entryValue03 = getValues(ler);

			EventReadService eventReadService = new EventReadServiceImpl();

			List<EventDTO> teste = eventReadService.readEvents(entryValue03);

		}
	}

	private static String getValues(Scanner ler) {
		String entryValue01;
		String entryValue02;
		String entryValue03;
		entryValue01 = ler.next();
		if(entryValue01.equals("exit")){
			System.exit(0);
		}

		entryValue02 = ler.next();
		entryValue03 = ler.next();
		return entryValue03;
	}



}
