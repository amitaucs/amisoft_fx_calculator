package com.amisoft.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ConversionUtility {


    public void loadmainCurrencyConversion(String mainConversionTable,Map<String,BigDecimal> targeteMap){

        LoadMapFromProperty(mainConversionTable, targeteMap);
    }



    public void LoadMapFromProperty(String mainConversionTable, Map<String, BigDecimal> targeteMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,","));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,"=");
            targeteMap.putIfAbsent(currencyConv[0], BigDecimal.valueOf(Double.valueOf(currencyConv[1])));
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


    public Optional<BigDecimal> conversionRate(String sourceCurrency , String targetCurrency, Map<String, BigDecimal> targeteMap){

        Optional<BigDecimal> converrateOptional = Optional.of(targeteMap.get(sourceCurrency+targetCurrency));
        if(converrateOptional.isPresent()){
           return converrateOptional;
        }else{

            converrateOptional = Optional.of(targeteMap.get(targetCurrency+sourceCurrency));
            if(converrateOptional.isPresent()){

                  BigDecimal conversionRate = BigDecimal.valueOf(1).divide(converrateOptional.get()).setScale(4);
                  targeteMap.putIfAbsent(targetCurrency+sourceCurrency,conversionRate);
                  return Optional.of(conversionRate);

            }else{
                return Optional.empty();
            }
        }

    }


}
