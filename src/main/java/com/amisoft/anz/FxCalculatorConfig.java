package com.amisoft.anz;

import com.amisoft.Constant;
import com.amisoft.services.ConversionCalculatorService;
import com.amisoft.services.DisplayService;
import com.amisoft.services.FxCalculatorService;
import com.amisoft.utils.ConversionUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FxCalculatorConfig {

    @Bean
    public ConversionUtility conversionUtility(){
        return new ConversionUtility();
    }

    @Bean
    ConversionCalculatorService conversionCalculatorService(){
        return new ConversionCalculatorService();
    }

    @Bean
    DisplayService displayService(){
        return new DisplayService();
    }

    @Bean
    Constant constant(){
        return new Constant();
    }

    @Bean
    FxCalculatorService fxCalculatorService(){
        return new FxCalculatorService();
    }
}
