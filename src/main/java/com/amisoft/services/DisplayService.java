package com.amisoft.services;


import com.amisoft.Constant;
import com.amisoft.dto.CurrencyConversionDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisplayService {

    @Autowired
    Constant constant;


    public String displayConversionOutput(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

        String stringToDisplay = new StringBuilder()
                .append(currencyConversionDetailsDto.getSourceCurrency())
                .append(constant.spaceSeparator)
                .append(String.valueOf(currencyConversionDetailsDto.getAmount()))
                .append(constant.spaceSeparator)
                .append(constant.displayEqual)
                .append(constant.spaceSeparator)
                .append(currencyConversionDetailsDto.getTargetCurrency())
                .append(constant.spaceSeparator)
                .append(String.valueOf(currencyConversionDetailsDto.getConvertedAmount())).toString();

        return display(stringToDisplay);

    }


    public String displayConversionOutputError(CurrencyConversionDetailsDto currencyConversionDetailsDto) {

        String stringToDisplayError = new StringBuilder()
                .append(constant.displayUnableToFindConversion)
                .append(constant.spaceSeparator)
                .append(currencyConversionDetailsDto.getSourceCurrency())
                .append(constant.displaySlash)
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
