package com.amisoft.services;


import com.amisoft.Constant;
import com.amisoft.dto.CurrencyConversionDetailsDto;
import com.amisoft.utils.ConversionUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FxCalculatorService {

    @Autowired
    ConversionUtility conversionUtility;

    @Autowired
    ConversionCalculatorService conversionCalculatorService;

    @Autowired
    DisplayService displayService;

    @Autowired
    Constant constant;


    private Map<String, BigDecimal> mainConversionRateMap = new HashMap<>();

    private Map<String, Integer> uptoWhatDecimalPtMap = new HashMap<>();

    int defaultDecimalPoint;


    public Optional<String> startFxCalculator(Optional<String> optionalInputString) {

        loadAllData();

        if (!optionalInputString.isPresent()) {

            showWelcomeMsgInConsole();

            Boolean isDone = false;
            Scanner scanner = new Scanner(System.in);

            processCalculator(isDone, scanner);
            scanner.close();

            return Optional.empty();
        } else {
            return Optional.of(validateInputStringWithValidFormat(optionalInputString.get()));
        }
    }

    private void loadAllData() {

        defaultDecimalPoint = Integer.valueOf(constant.defaultDecimal);
        conversionUtility.loadmainCurrencyConversion(mainConversionRateMap);
        conversionUtility.LoadMapFromPropertyInt(constant.specialDecimal, uptoWhatDecimalPtMap);
    }

    private void showWelcomeMsgInConsole() {
        System.out.println(constant.welcomeMsg);
        System.out.println(constant.formatMsg);
        System.out.println();
    }


    private void processCalculator(Boolean isDone, Scanner scanner) {

        do {

            System.out.println(constant.enterInputMsg);
            readInputStringFromConsole(scanner);
            isDone = showMessageInConsoleWithExitOption(isDone, scanner);

        } while (!isDone);
    }


    private void readInputStringFromConsole(Scanner scanner) {

        System.out.println();
        String currencyInput = scanner.nextLine();

        validateInputStringWithValidFormat(currencyInput);

    }


    private String validateInputStringWithValidFormat(String currencyInput) {

        Pattern pattern = Pattern.compile(constant.regExpression);
        Matcher matcher = pattern.matcher(currencyInput);

        if (matcher.find()) {
            return splitInputStringToGetCurrencyAndAmount(currencyInput);
        } else {

            System.out.println(constant.invalidInputMsg);
            return constant.invalidInputMsg;
        }
    }

    private String splitInputStringToGetCurrencyAndAmount(String inputString) {

        String[] inputStringAfterSplit = StringUtils.split(inputString,constant.spaceSeparator);
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
            return processFxConversionEhwnSourceAndTargetCurrencyDifferent(sourceCurrency, amount, targetCurrency, uptoWhichDecimalPt);
        }
    }


    private String processFxConversionEhwnSourceAndTargetCurrencyDifferent(String sourceCurrency, BigDecimal amount, String targetCurrency, int uptoWhatDecimalPt) {
        Optional<BigDecimal> conversionRate = conversionUtility.conversionRate(sourceCurrency.toUpperCase(), targetCurrency.toUpperCase(), mainConversionRateMap);


        if (conversionRate.isPresent()) {

            CurrencyConversionDetailsDto currencyConversionDetailsDto = constructConversionDto(sourceCurrency, targetCurrency, amount, conversionRate.get(), uptoWhatDecimalPt, null);
            return conversionCalculatorService.doConversion(currencyConversionDetailsDto);

        } else {

            CurrencyConversionDetailsDto currencyConversionDetailsDto = constructConversionDto(sourceCurrency, targetCurrency, amount, null, uptoWhatDecimalPt, null);
            return displayService.displayConversionOutputError(currencyConversionDetailsDto);
        }
    }


    private Boolean showMessageInConsoleWithExitOption(Boolean isDone, Scanner scanner) {

        System.out.println(constant.continueOptionMsg);
        System.out.println();
        String input = scanner.nextLine();
        if (input != null && input.equalsIgnoreCase(constant.no)) {
            isDone = true;
        }
        return isDone;
    }


    private CurrencyConversionDetailsDto constructConversionDto(String source, String target, BigDecimal amount, BigDecimal conversionRate, int uptoWhatDecimalPt, BigDecimal convertedAmount) {

        return new CurrencyConversionDetailsDto(source, target, amount, conversionRate, uptoWhatDecimalPt, convertedAmount);

    }
}
