package com.amisoft.services;


import com.amisoft.dto.CurrencyConversionDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class DisplayService {

    final String space = "  ";
    final String equalStr = "=";
    final String SLASH = "/";


    public String displayConversionOutput(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

        String stringToDisplay = new StringBuilder()
                .append(currencyConversionDetailsDto.getSourceCurrency())
                .append(space)
                .append(String.valueOf(currencyConversionDetailsDto.getAmount()))
                .append(space)
                .append(equalStr)
                .append(space)
                .append(currencyConversionDetailsDto.getTargetCurrency())
                .append(space)
                .append(String.valueOf(currencyConversionDetailsDto.getConvertedAmount())).toString();

        return display(stringToDisplay);

    }


    public String displayConversionOutputError(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

        String stringToDisplayError = new StringBuilder()
                .append("Unable to find rate for  ")
                .append(currencyConversionDetailsDto.getSourceCurrency())
                .append(SLASH)
                .append(currencyConversionDetailsDto.getTargetCurrency()).toString();

        return display(stringToDisplayError);


    }

    private String display(String stringToDisplay) {
        System.out.println();
        System.out.println(stringToDisplay);
        System.out.println();
        return stringToDisplay;
    }


}
