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

        BigDecimal convertionRateOptional = targeteMap.getOrDefault(sourceCurrency+targetCurrency,null);

        if(null != convertionRateOptional){
           return Optional.of(convertionRateOptional);
        }else{

            convertionRateOptional = targeteMap.get(targetCurrency+sourceCurrency);
            if(null != convertionRateOptional){

                  BigDecimal conversionRate = (BigDecimal.valueOf(1).divide(convertionRateOptional,4,BigDecimal.ROUND_HALF_EVEN));

                  //adding missing value in map for faster processing

                  targeteMap.putIfAbsent(targetCurrency+sourceCurrency,conversionRate);
                  return Optional.of(conversionRate);

            }else{
                return Optional.empty();
            }
        }

    }


}
