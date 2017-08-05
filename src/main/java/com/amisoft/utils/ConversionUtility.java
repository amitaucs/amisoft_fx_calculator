package com.amisoft.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class ConversionUtility {


    public void loadmainCurrencyConversion(String mainConversionTable,Map<String,Double> targeteMap){

        LoadMapFromProperty(mainConversionTable, targeteMap);
    }



    public void LoadMapFromProperty(String mainConversionTable, Map<String, Double> targeteMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,","));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,"=");
            targeteMap.putIfAbsent(currencyConv[0], Double.valueOf(currencyConv[1]));
        });

        System.out.println(targeteMap.keySet().toString());
    }


    public void LoadMapFromPropertyInt(String mainConversionTable, Map<String, Integer> targeteMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,","));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,"=");
            targeteMap.putIfAbsent(currencyConv[0], Integer.valueOf(currencyConv[1]));
        });

        System.out.println(targeteMap.keySet().toString());
    }


}
