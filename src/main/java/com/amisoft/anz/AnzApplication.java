package com.amisoft.anz;

import com.amisoft.services.ConversionRateLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnzApplication {


	public static void main(String[] args) {
		SpringApplication.run(AnzApplication.class, args);
	}

	@Bean
	public ConversionRateLoader conversionRateLoader(){
		return new ConversionRateLoader();
	}

}
