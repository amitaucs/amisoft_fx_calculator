package com.amisoft.services;


import com.amisoft.dto.CurrencyConversionDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class ConversionCalculatorService {

    @Autowired
    DisplayService displayService;

    public String doConversion(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

        MathContext mathContext = new MathContext(currencyConversionDetailsDto.getUpToWhichDecimalPt());
        BigDecimal amount = currencyConversionDetailsDto.getAmount();
        BigDecimal conversionRate = currencyConversionDetailsDto.getConversionRate();

        BigDecimal convertedAmount = amount.multiply(conversionRate, mathContext);
        currencyConversionDetailsDto.setConvertedAmount(convertedAmount);
        return displayService.displayConversionOutput(currencyConversionDetailsDto);
    }


}
