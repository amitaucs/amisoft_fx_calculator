package com.amisoft.anz;

import com.amisoft.Constant;
import com.amisoft.cache.ConversionRateCache;
import com.amisoft.cache.ConversionRateCacheFromPropertyFileImpl;
import com.amisoft.processor.FxCalculatorBaseProcessor;
import com.amisoft.processor.FxCalculatorConsoleProcessor;
import com.amisoft.processor.FxProcessorFactory;
import com.amisoft.services.ConversionCalculatorService;
import com.amisoft.services.DisplayService;
import com.amisoft.services.FxCalculatorService;
import com.amisoft.utils.ConversionUtility;
import com.amisoft.utils.FxCalculatorValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FxCalculatorConfig {

    @Bean
    public ConversionUtility conversionUtility() {
        return new ConversionUtility();
    }

    @Bean
    ConversionCalculatorService conversionCalculatorService() {
        return new ConversionCalculatorService();
    }

    @Bean
    DisplayService displayService() {
        return new DisplayService();
    }

    @Bean
    Constant constant() {
        return new Constant();
    }

    @Bean
    FxCalculatorService fxCalculatorService() {
        return new FxCalculatorService();
    }

    @Bean
    FxProcessorFactory fxProcessorFactory() {
        return new FxProcessorFactory();
    }

    @Bean
    FxCalculatorConsoleProcessor fxCalculatorConsoleProcessor() {
        return new FxCalculatorConsoleProcessor();
    }

    @Bean
    FxCalculatorBaseProcessor fxCalculatorBaseProcessor() {
        return new FxCalculatorBaseProcessor();
    }

    @Bean
    FxCalculatorValidator fxCalculatorValidator() {
        return new FxCalculatorValidator();
    }

    @Bean
    ConversionRateCacheFromPropertyFileImpl conversionRateCache(){return new ConversionRateCacheFromPropertyFileImpl();}


}
