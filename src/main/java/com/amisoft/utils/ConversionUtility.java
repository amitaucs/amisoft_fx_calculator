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

    public Optional<BigDecimal> conversionRate(String sourceCurrency , String targetCurrency, Map<String, BigDecimal> conversionRateMap){

        BigDecimal convertionRateOptional = conversionRateMap.getOrDefault(sourceCurrency+constant.keySeparator+targetCurrency,null);

        if(null != convertionRateOptional){
           return Optional.of(convertionRateOptional);
        }else{

            convertionRateOptional = conversionRateMap.get(targetCurrency+constant.keySeparator+sourceCurrency);
            if(null != convertionRateOptional){

                  BigDecimal conversionRate = (BigDecimal.valueOf(1).divide(convertionRateOptional,4,BigDecimal.ROUND_HALF_EVEN));
                  return Optional.of(conversionRate);

            }else{
                return Optional.empty();
            }
        }

    }


}
