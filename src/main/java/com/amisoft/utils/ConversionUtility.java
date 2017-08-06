package com.amisoft.utils;

import com.amisoft.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class ConversionUtility {


    @Autowired
    Constant constant;


    public void loadmainCurrencyConversion(Map<String,BigDecimal> targetMap){

        loadMapFromPropertyBigDecimal(constant.mainConversionTable, targetMap);
        loadMapFromCrossConversion(targetMap);
    }

    private void loadMapFromCrossConversion(Map<String, BigDecimal> targetMap) {

        calculateCrossConvRate(targetMap,constant.audCrossConversion);
        calculateCrossConvRate(targetMap,constant.cadCrossConversion);

    }

    private void calculateCrossConvRate(Map<String, BigDecimal> targetMap,String crossCurrencyProperty) {

        Map<String,String> mapCrossCurrency = new HashMap<>();
        loadMapFromPropertyString(crossCurrencyProperty,mapCrossCurrency);

        mapCrossCurrency.keySet().forEach(key -> {

            String refCurrency = mapCrossCurrency.get(key).toUpperCase();
            String[] splitedKey = StringUtils.split(key,constant.keySeparator);

            Optional<BigDecimal> directCurrencyConvRate = conversionRate(splitedKey[0],refCurrency,targetMap);

            if(directCurrencyConvRate.isPresent()){

                BigDecimal amountInRefCurrency = directCurrencyConvRate.get();
                Optional<BigDecimal> refCurrencyToTargetCurrencyConvRate = conversionRate(refCurrency,splitedKey[1],targetMap);

                if(refCurrencyToTargetCurrencyConvRate.isPresent()){
                    BigDecimal crossCalculatedConvRate = amountInRefCurrency.multiply(refCurrencyToTargetCurrencyConvRate.get());
                    crossCalculatedConvRate = crossCalculatedConvRate.setScale(Integer.valueOf(constant.crossConvDecimalPoint),BigDecimal.ROUND_HALF_EVEN);
                    targetMap.putIfAbsent(key,crossCalculatedConvRate);
                }
            }
        });
    }

    public void loadMapFromPropertyString(String mainConversionTable, Map<String, String> targetMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,constant.commaSeparator));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,constant.keyValueSeparator);
            targetMap.putIfAbsent(currencyConv[0], currencyConv[1]);
        });

    }


    public void loadMapFromPropertyBigDecimal(String mainConversionTable, Map<String, BigDecimal> targetMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,constant.commaSeparator));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,constant.keyValueSeparator);
            targetMap.putIfAbsent(currencyConv[0], BigDecimal.valueOf(Double.valueOf(currencyConv[1])));
        });

    }


    public void LoadMapFromPropertyInt(String mainConversionTable, Map<String, Integer> targeteMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,constant.commaSeparator));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,constant.keyValueSeparator);
            targeteMap.putIfAbsent(currencyConv[0], Integer.valueOf(currencyConv[1]));
        });

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

                  targeteMap.putIfAbsent(targetCurrency+constant.keySeparator+sourceCurrency,conversionRate);
                  return Optional.of(conversionRate);

            }else{
                return Optional.empty();
            }
        }

    }


}
