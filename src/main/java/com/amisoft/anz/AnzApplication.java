package com.amisoft.anz;

import com.amisoft.Constant;
import com.amisoft.processor.FxProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AnzApplication {

    @Autowired
    FxProcessorFactory fxProcessorFactory;
    @Autowired
    Constant constant;

    public static void main(String[] args) {
        SpringApplication.run(AnzApplication.class, args);
    }


    @PostConstruct
    private void loadCacheForCurrencyConversion() {

        fxProcessorFactory.getFxCalculatorConversionRateCache(constant.cacheLoadSource).loadConversionData();

    }

}
