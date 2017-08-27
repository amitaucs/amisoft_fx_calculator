package com.amisoft.cache;

import com.amisoft.Constant;
import com.amisoft.utils.ConversionUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

public class ConversionRateCacheFromPropertyFileImpl implements ConversionRateCache {

    @Autowired
    Constant constant;

    @Autowired
    ConversionUtility conversionUtility;

    private Map<String, BigDecimal> conversionRateMap = new HashMap<>();

    private Map<String, Integer> decimalPtMapForSpecialCurrency = new HashMap<>();


    public Map<String, BigDecimal> getConversionRateMap() {
        return Collections.unmodifiableMap(conversionRateMap);
    }

    public Map<String, Integer> getDecimalPtMapForSpecialCurrency() {
        return Collections.unmodifiableMap(decimalPtMapForSpecialCurrency);
    }


    @Override
    public void loadConversionData() {

        if (conversionRateMap.isEmpty()) {
            loadMainCurrencyConversion(conversionRateMap);
            loadCurrencyCrossConversion(conversionRateMap);
        }
        if (decimalPtMapForSpecialCurrency.isEmpty()) {
            loadMapFromPropertySpecialCurrencyAndDecimalPoints(constant.specialDecimalCurrency, decimalPtMapForSpecialCurrency);
        }
    }


    private void loadMainCurrencyConversion(Map<String, BigDecimal> targetMap) {

        loadMapFromPropertyMainCurrencyPairAndConversionRate(constant.mainConversionTable, targetMap);

    }

    private void loadCurrencyCrossConversion(Map<String, BigDecimal> targetMap) {

        // Helper

        String[] crossCurrencyTableArray = {constant.audCrossConversion, constant.cadCrossConversion, constant.cnyCrossConversion,
                constant.czkCrossConversion, constant.dkkCrossConversion, constant.eurCrossConversion,constant.gbpCrossConversion,
                constant.jpyCrossConversion, constant.nokCrossConversion, constant.nzdCrossConversion, constant.usdCrossConversion};

        List<String> crossCurrencyTableAsList = Arrays.asList(crossCurrencyTableArray);

        crossCurrencyTableAsList.forEach(

                crossCurrencyRow -> calculateCrossConvRate(targetMap, crossCurrencyRow)
        );

    }

    private void calculateCrossConvRate(Map<String, BigDecimal> conversionRateMap, String crossCurrencyRow) {

        Map<String, String> mapCrossCurrencyPairAndRefCurrency = new HashMap<>();
        loadMapFromPropertyCrossCurrencyPairAndRefCurrency(mapCrossCurrencyPairAndRefCurrency,crossCurrencyRow);


        mapCrossCurrencyPairAndRefCurrency.keySet().forEach(key -> {

            String refCurrency = mapCrossCurrencyPairAndRefCurrency.get(key).toUpperCase();
            String[] splitedKey = StringUtils.split(key, constant.keySeparator);

            Optional<BigDecimal> sourceCurrencyToRefCurrencyConvRate = conversionUtility.conversionRate(splitedKey[0], refCurrency, Collections.unmodifiableMap(conversionRateMap));

            if (sourceCurrencyToRefCurrencyConvRate.isPresent()) {

                BigDecimal amountInRefCurrency = sourceCurrencyToRefCurrencyConvRate.get();
                conversionRateMap.putIfAbsent(splitedKey[0]+constant.keySeparator+refCurrency, amountInRefCurrency);


                Optional<BigDecimal> refCurrencyToTargetCurrencyConvRate = conversionUtility.conversionRate(refCurrency, splitedKey[1], Collections.unmodifiableMap(conversionRateMap));
                conversionRateMap.putIfAbsent(refCurrency+constant.keySeparator+splitedKey[1], amountInRefCurrency);

                if (refCurrencyToTargetCurrencyConvRate.isPresent()) {
                    BigDecimal crossCalculatedConvRate = amountInRefCurrency.multiply(refCurrencyToTargetCurrencyConvRate.get());
                    crossCalculatedConvRate = crossCalculatedConvRate.setScale(Integer.valueOf(constant.crossConvDecimalPoint), BigDecimal.ROUND_HALF_EVEN);
                    conversionRateMap.putIfAbsent(key, crossCalculatedConvRate);
                }
            }
        });
    }


    private void loadMapFromPropertyCrossCurrencyPairAndRefCurrency(Map<String, String> targetMap, String crossCurrencyRow) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(crossCurrencyRow,constant.currencyPairSeparator));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,constant.keyValueSeparator);
            targetMap.putIfAbsent(currencyConv[0], currencyConv[1]);
        });

    }


    private void loadMapFromPropertyMainCurrencyPairAndConversionRate(String mainConversionTable, Map<String, BigDecimal> targetMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(mainConversionTable,constant.currencyPairSeparator));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,constant.keyValueSeparator);
            targetMap.putIfAbsent(currencyConv[0], BigDecimal.valueOf(Double.valueOf(currencyConv[1])));
        });

    }


    private void loadMapFromPropertySpecialCurrencyAndDecimalPoints(String specialCurrencyAndDecimalPoint, Map<String, Integer> targeteMap) {

        List<String> mainConversionRateList = Arrays.asList(StringUtils.split(specialCurrencyAndDecimalPoint,constant.currencyPairSeparator));

        mainConversionRateList.forEach(convRatePair -> {

            String[] currencyConv = StringUtils.split(convRatePair,constant.keyValueSeparator);
            targeteMap.putIfAbsent(currencyConv[0], Integer.valueOf(currencyConv[1]));
        });

    }

}
