package br.com.poc;


import br.com.poc.enuns.TypeDistanceEnum;
import br.com.poc.service.EventReadService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class MainApplication {

	public static final String csvDivisor = ",";

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(MainApplication.class, args);
		
		Scanner ler = new Scanner(System.in);

		String value = "";

		while(1 > 0){

			System.out.printf("Digite:");

			value = getValues(ler);

			EventReadService eventReadService = context.getBean(EventReadService.class);
			//EventReadService eventReadService = new EventReadServiceImpl();

			String[] columnsEntryValues = value.split(csvDivisor);

			double latitude = Double.parseDouble(columnsEntryValues[TypeDistanceEnum.LATITUDE.getValueType()]);
			double longitude = Double.parseDouble(columnsEntryValues[TypeDistanceEnum.LONGITUDE.getValueType()]);

			eventReadService.readEvents(latitude, longitude);

		}
	}

	private static String getValues(Scanner ler) {
		/* Como são passadas 3 propriedades via linha de comando
		  os dois primeiros paramtros são ignorados e  apenas o terceiro, que é esperado o valor de latitude e longitude
		   é utilizado. Os demais são usados apenas para avançar o método ler.next() */
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
