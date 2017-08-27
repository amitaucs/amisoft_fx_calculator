package com.amisoft.processor;

import com.amisoft.cache.ConversionRateCache;
import com.amisoft.cache.ConversionRateCacheFromPropertyFileImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FxProcessorFactory {

    @Autowired
    FxCalculatorConsoleProcessor fxCalculatorConsoleProcessor;

    @Autowired
    FxCalculatorBaseProcessor fxCalculatorBaseProcessor;

    @Autowired
    ConversionRateCacheFromPropertyFileImpl conversionRateCacheFromPropertyFileImpl;


    public FxProcessor getFxCalculatorProcessor(Optional<String> optionalInput) {

        return (optionalInput.isPresent() ? fxCalculatorBaseProcessor : fxCalculatorConsoleProcessor);

    }


    public ConversionRateCache getFxCalculatorConversionRateCache(String cacheSource) {

        switch(cacheSource){

            /**TO DO : If cache loaded from DB. Not considered in the exercise due to small dataset of currency.
            /*case "database":
                return conversionRateCacheFromDataBaseImpl;*/

            default:
                return conversionRateCacheFromPropertyFileImpl;
        }


    }

}
