package com.amisoft.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class ConversionRateLoader {


    public void loadmainCurrencyConversion(String mainConversionTable,Map<String,Double> mainConversionRateMap){


        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,","));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,"=");
            mainConversionRateMap.putIfAbsent(currencyConv[0], Double.valueOf(currencyConv[1]));
        });

        System.out.println(mainConversionRateMap.keySet().toString());
    }


}
