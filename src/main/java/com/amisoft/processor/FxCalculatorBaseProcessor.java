package com.amisoft.processor;

import com.amisoft.Constant;
import com.amisoft.dto.CurrencyConversionDetailsDto;
import com.amisoft.services.ConversionCalculatorService;
import com.amisoft.services.DisplayService;
import com.amisoft.utils.ConversionUtility;
import com.amisoft.utils.FxCalculatorValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FxCalculatorBaseProcessor implements FxProcessor {

    @Autowired
    ConversionUtility conversionUtility;

    @Autowired
    ConversionCalculatorService conversionCalculatorService;

    @Autowired
    DisplayService displayService;

    @Autowired
    Constant constant;

    @Autowired
    FxCalculatorValidator fxCalculatorValidator;


    private Map<String, BigDecimal> mainConversionRateMap = new HashMap<>();

    private Map<String, Integer> uptoWhatDecimalPtMap = new HashMap<>();

    int defaultDecimalPoint;

    @Override
    public Optional<String> fxCurrencyProcessor(Optional<String> optionalInputString) {

        if (mainConversionRateMap.isEmpty() && uptoWhatDecimalPtMap.isEmpty()) {
            loadAllData();
        }

        if (constant.isConsoleEntry) {

            return Optional.of(splitInputStringToGetCurrencyAndAmount(optionalInputString.get()));
        } else if (fxCalculatorValidator.validateInputStringWithValidFormat(optionalInputString.get())) {
            return Optional.of(splitInputStringToGetCurrencyAndAmount(optionalInputString.get()));
        } else {

            return (Optional.of(constant.invalidInputMsg));
        }
    }

    private void loadAllData() {

        defaultDecimalPoint = Integer.valueOf(constant.defaultDecimal);
        conversionUtility.loadmainCurrencyConversion(mainConversionRateMap);
        conversionUtility.LoadMapFromPropertyInt(constant.specialDecimal, uptoWhatDecimalPtMap);
    }


    private String splitInputStringToGetCurrencyAndAmount(String inputString) {

        String[] inputStringAfterSplit = StringUtils.split(inputString, constant.spaceSeparator);
        String sourceCurrency = inputStringAfterSplit[0];
        BigDecimal amount = new BigDecimal(inputStringAfterSplit[1]);
        String targetCurrency = inputStringAfterSplit[3];

        return processValidateInputDataForFxConversion(sourceCurrency, amount, targetCurrency);
    }

    private String processValidateInputDataForFxConversion(String sourceCurrency, BigDecimal amount, String targetCurrency) {

        int uptoWhichDecimalPt = (uptoWhatDecimalPtMap.getOrDefault(targetCurrency, defaultDecimalPoint));

        if (StringUtils.equalsIgnoreCase(sourceCurrency, targetCurrency)) {

            CurrencyConversionDetailsDto currencyConversionDetailsDto = constructConversionDto(sourceCurrency, targetCurrency, amount, BigDecimal.valueOf(1), uptoWhichDecimalPt, amount);
            return displayService.displayConversionOutput(currencyConversionDetailsDto);

        } else {
            return processFxConversionWhenSourceAndTargetCurrencyDifferent(sourceCurrency, amount, targetCurrency, uptoWhichDecimalPt);
        }
    }


    private String processFxConversionWhenSourceAndTargetCurrencyDifferent(String sourceCurrency, BigDecimal amount, String targetCurrency, int uptoWhatDecimalPt) {
        Optional<BigDecimal> conversionRate = conversionUtility.conversionRate(sourceCurrency.toUpperCase(), targetCurrency.toUpperCase(), mainConversionRateMap);


        if (conversionRate.isPresent()) {

            CurrencyConversionDetailsDto currencyConversionDetailsDto = constructConversionDto(sourceCurrency, targetCurrency, amount, conversionRate.get(), uptoWhatDecimalPt, null);
            return conversionCalculatorService.doConversion(currencyConversionDetailsDto);

        } else {

            CurrencyConversionDetailsDto currencyConversionDetailsDto = constructConversionDto(sourceCurrency, targetCurrency, amount, null, uptoWhatDecimalPt, null);
            return displayService.displayConversionOutputError(currencyConversionDetailsDto);
        }
    }

    private CurrencyConversionDetailsDto constructConversionDto(String source, String target, BigDecimal amount, BigDecimal conversionRate, int uptoWhatDecimalPt, BigDecimal convertedAmount) {

        return new CurrencyConversionDetailsDto(source, target, amount, conversionRate, uptoWhatDecimalPt, convertedAmount);

    }
}
