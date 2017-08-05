package com.amisoft.services;


import com.amisoft.dto.CurrencyConversionDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class DisplayService {

    public String displayConversionOutput(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

        String space = "  ";

        StringBuilder displayString = new StringBuilder()
                .append(currencyConversionDetailsDto.getSourceCurrency())
                .append(space)
                .append(currencyConversionDetailsDto.getConvertedAmount())
                .append(space)
                .append(currencyConversionDetailsDto.getTargetCurrency());

        return displayString.toString();
    }

}
