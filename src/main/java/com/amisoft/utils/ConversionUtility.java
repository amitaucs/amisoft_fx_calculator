package com.amisoft.utils;

import com.amisoft.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ConversionUtility {


    @Autowired
    Constant constant;

    public void loadmainCurrencyConversion(Map<String,BigDecimal> targeteMap){

        loadMapFromMainConversion(constant.mainConversionTable, targeteMap);
        loadMapFromCrossConversion(targeteMap);
    }

    private void loadMapFromCrossConversion(Map<String, BigDecimal> targeteMap) {




    }


    public void loadMapFromMainConversion(String mainConversionTable, Map<String, BigDecimal> targetMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,constant.commaSeparator));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,constant.keyValueSeparator);
            targetMap.putIfAbsent(currencyConv[0], BigDecimal.valueOf(Double.valueOf(currencyConv[1])));
        });
        System.out.println(targetMap.toString());

    }


    public void LoadMapFromPropertyInt(String mainConversionTable, Map<String, Integer> targeteMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,constant.commaSeparator));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,constant.keyValueSeparator);
            targeteMap.putIfAbsent(currencyConv[0], Integer.valueOf(currencyConv[1]));
        });

        System.out.println(targeteMap.keySet().toString());
    }


    public Optional<BigDecimal> conversionRate(String sourceCurrency , String targetCurrency, Map<String, BigDecimal> targeteMap){

        BigDecimal convertionRateOptional = targeteMap.getOrDefault(sourceCurrency+constant.keySeparator+targetCurrency,null);

        if(null != convertionRateOptional){
           return Optional.of(convertionRateOptional);
        }else{

            convertionRateOptional = targeteMap.get(targetCurrency+constant.keySeparator+sourceCurrency);
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
