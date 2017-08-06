package com.amisoft.anz;

import com.amisoft.dto.CurrencyConversionDetailsDto;
import com.amisoft.services.ConversionCalculatorService;
import com.amisoft.services.DisplayService;
import com.amisoft.utils.ConversionUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.amisoft.Constant.*;

@Component
public class FXCalculatorRunner implements CommandLineRunner {


    @Autowired
    ConversionUtility conversionUtility;

    @Autowired
    ConversionCalculatorService conversionCalculatorService;

    @Autowired
    DisplayService displayService;


    private Map<String, BigDecimal> mainConversionRateMap = new TreeMap<>();

    private Map<String, Integer> uptoWhatDecimalPtMap = new TreeMap<>();

    int defaultDecimalPoint;


    @Override
    public void run(String... strings) {

        loadAllData();

        Boolean isDone = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println(welcomeMsg);
        System.out.println(formatMsg);

        processCalculator(isDone, scanner);

        scanner.close();
    }

    private void loadAllData() {

        defaultDecimalPoint = Integer.valueOf(defaultDecimal);

        conversionUtility.loadmainCurrencyConversion(mainConversionTable, mainConversionRateMap);
        conversionUtility.LoadMapFromPropertyInt(specialDecimal, uptoWhatDecimalPtMap);
    }


    private void processCalculator(Boolean isDone, Scanner scanner) {

        do {

            System.out.println(enterInputMsg);
            readFxInput(scanner);
            isDone = exitCheck(isDone, scanner);

        } while (!isDone);
    }


    private void readFxInput(Scanner scanner) {


        System.out.println();
        String currencyInput = scanner.nextLine();

        Pattern pattern = Pattern.compile(regExpression);
        Matcher matcher = pattern.matcher(currencyInput);

        if (matcher.find()) {
            analyzeInput(currencyInput);
        } else {
            System.out.println(invalidInputMsg);
        }


    }

    private void analyzeInput(String inputString) {

        Scanner scanner = new Scanner(inputString).useDelimiter(inputDelimiter);
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
        System.out.println(continueOptionMsg);
        System.out.println();
        String input = scanner.nextLine();
        if (input != null && input.equalsIgnoreCase(no)) {
            isDone = true;
        }
        return isDone;
    }


}
