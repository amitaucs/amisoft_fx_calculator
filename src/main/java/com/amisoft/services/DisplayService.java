package com.amisoft.services;


import com.amisoft.dto.CurrencyConversionDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class DisplayService {

    public String displayConversionOutput(CurrencyConversionDetailsDto currencyConversionDetailsDto) {


        final String space = "  ";
        final String equalStr = "=";


        StringBuilder displayString = new StringBuilder()
                .append(currencyConversionDetailsDto.getSourceCurrency())
                .append(space)
                .append(currencyConversionDetailsDto.getAmount())
                .append(space)
                .append(equalStr)
                .append(space)
                .append(currencyConversionDetailsDto.getTargetCurrency());

        return displayString.toString();
    }

}
