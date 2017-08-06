package com.amisoft.anz;

import com.amisoft.Constant;
import com.amisoft.dto.CurrencyConversionDetailsDto;
import com.amisoft.services.ConversionCalculatorService;
import com.amisoft.services.DisplayService;
import com.amisoft.utils.ConversionUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class FXCalculatorRunner implements CommandLineRunner {


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


    @Override
    public void run(String... strings) {

        loadAllData();

        Boolean isDone = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println(constant.welcomeMsg);
        System.out.println(constant.formatMsg);
        System.out.println();

        processCalculator(isDone, scanner);

        scanner.close();
    }

    private void loadAllData() {

        defaultDecimalPoint = Integer.valueOf(constant.defaultDecimal);

        conversionUtility.loadmainCurrencyConversion(constant.mainConversionTable, mainConversionRateMap);
        conversionUtility.LoadMapFromPropertyInt(constant.specialDecimal, uptoWhatDecimalPtMap);
    }


    private void processCalculator(Boolean isDone, Scanner scanner) {

        do {

            System.out.println(constant.enterInputMsg);
            readFxInput(scanner);
            isDone = exitCheck(isDone, scanner);

        } while (!isDone);
    }


    private void readFxInput(Scanner scanner) {


        System.out.println();
        String currencyInput = scanner.nextLine();

        Pattern pattern = Pattern.compile(constant.regExpression);
        Matcher matcher = pattern.matcher(currencyInput);

        if (matcher.find()) {
            analyzeInput(currencyInput);
        } else {
            System.out.println(constant.invalidInputMsg);
        }


    }

    private void analyzeInput(String inputString) {

        Scanner scanner = new Scanner(inputString).useDelimiter(constant.inputDelimiter);
        String sourceCurrency = scanner.next();
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.next();
        String targetCurrency = scanner.next();
        scanner.close();

        int uptoWhatDecimalPt = (uptoWhatDecimalPtMap.getOrDefault(targetCurrency, defaultDecimalPoint));

        if (StringUtils.equalsIgnoreCase(sourceCurrency, targetCurrency)) {

            CurrencyConversionDetailsDto currencyConversionDetailsDto = constructConversionDto(sourceCurrency, targetCurrency, amount, BigDecimal.valueOf(1), uptoWhatDecimalPt, amount);
            displayService.displayConversionOutput(currencyConversionDetailsDto);

        } else {
            sourceAndTargetCurrencyDifferent(sourceCurrency, amount, targetCurrency, uptoWhatDecimalPt);
        }
    }



    private void sourceAndTargetCurrencyDifferent(String sourceCurrency, BigDecimal amount, String targetCurrency, int uptoWhatDecimalPt) {
        Optional<BigDecimal> conversionRate = conversionUtility.conversionRate(sourceCurrency.toUpperCase(), targetCurrency.toUpperCase(), mainConversionRateMap);


        if (conversionRate.isPresent()) {

            CurrencyConversionDetailsDto currencyConversionDetailsDto = constructConversionDto(sourceCurrency, targetCurrency, amount, conversionRate.get(), uptoWhatDecimalPt, null);
            conversionCalculatorService.doConversion(currencyConversionDetailsDto);

        } else {

            CurrencyConversionDetailsDto currencyConversionDetailsDto = constructConversionDto(sourceCurrency, targetCurrency, amount, null, uptoWhatDecimalPt, null);
            displayService.displayConversionOutputError(currencyConversionDetailsDto);
        }
    }



    private CurrencyConversionDetailsDto constructConversionDto(String source, String target, BigDecimal amount, BigDecimal conversionRate, int uptoWhatDecimalPt, BigDecimal convertedAmount) {

        return new CurrencyConversionDetailsDto(source, target, amount, conversionRate, uptoWhatDecimalPt, convertedAmount);

    }

    private Boolean exitCheck(Boolean isDone, Scanner scanner) {
        System.out.println(constant.continueOptionMsg);
        System.out.println();
        String input = scanner.nextLine();
        if (input != null && input.equalsIgnoreCase(constant.no)) {
            isDone = true;
        }
        return isDone;
    }


}
