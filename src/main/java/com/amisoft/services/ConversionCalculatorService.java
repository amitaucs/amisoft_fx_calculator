package com.amisoft.services;


import com.amisoft.dto.CurrencyConversionDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConversionCalculatorService {

    @Autowired
    DisplayService displayService;

    public String  doConversion(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

        BigDecimal amount = currencyConversionDetailsDto.getAmount();
        BigDecimal conversionRate = currencyConversionDetailsDto.getConversionRate();

        BigDecimal convertedAmount = amount.multiply(conversionRate);
        convertedAmount = convertedAmount.setScale(currencyConversionDetailsDto.getUpToWhichDecimalPt(),BigDecimal.ROUND_HALF_UP);
        currencyConversionDetailsDto.setConvertedAmount(convertedAmount);
        return displayService.displayConversionOutput(currencyConversionDetailsDto);
    }


}
