package com.amisoft.services;


import com.amisoft.dto.CurrencyConversionDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class DisplayService {

    final String space = "  ";
    final String equalStr = "=";
    final String SLASH = "/";


    public String displayConversionOutput(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

        return new StringBuilder()
                .append(currencyConversionDetailsDto.getSourceCurrency())
                .append(space)
                .append(String.valueOf(currencyConversionDetailsDto.getAmount()))
                .append(space)
                .append(equalStr)
                .append(space)
                .append(currencyConversionDetailsDto.getTargetCurrency())
                .append(space)
                .append(String.valueOf(currencyConversionDetailsDto.getConvertedAmount())).toString();

    }


    public String displayConversionOutputError(CurrencyConversionDetailsDto currencyConversionDetailsDto) {


        final String space = "  ";
        final String equalStr = "=";


        return new StringBuilder()
                .append("Unable to find rate for  ")
                .append(currencyConversionDetailsDto.getSourceCurrency())
                .append(SLASH)
                .append(currencyConversionDetailsDto.getTargetCurrency()).toString();


    }

}
