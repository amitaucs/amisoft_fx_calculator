package com.amisoft.anz;

import com.amisoft.services.ConversionCalculatorService;
import com.amisoft.services.ConversionRateLoaderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnzApplication {


	public static void main(String[] args) {
		SpringApplication.run(AnzApplication.class, args);
	}

	@Bean
	public ConversionRateLoaderService conversionRateLoader(){
		return new ConversionRateLoaderService();
	}

	@Bean
	ConversionCalculatorService conversionCalculatorService(){
		return new ConversionCalculatorService();
	}

}
