package com.amisoft.cache;

import java.math.BigDecimal;
import java.util.Map;

public interface ConversionRateCache {

     void loadConversionData();

     Map<String, BigDecimal> getConversionRateMap();

     Map<String, Integer> getDecimalPtMapForSpecialCurrency();
}
