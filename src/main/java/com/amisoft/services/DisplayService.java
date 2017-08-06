package com.amisoft.services;


import com.amisoft.dto.CurrencyConversionDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class DisplayService {

    final String space = "  ";
    final String equalStr = "=";
    final String SLASH = "/";


    public void displayConversionOutput(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

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

        display(stringToDisplay);

    }



    public void displayConversionOutputError(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

        String sitringToDisplayError = new StringBuilder()
                .append("Unable to find rate for  ")
                .append(currencyConversionDetailsDto.getSourceCurrency())
                .append(SLASH)
                .append(currencyConversionDetailsDto.getTargetCurrency()).toString();

        display(sitringToDisplayError);


    }

    private void display(String stringToDisplay) {
        System.out.println();
        System.out.println(stringToDisplay);
        System.out.println();
    }


}
