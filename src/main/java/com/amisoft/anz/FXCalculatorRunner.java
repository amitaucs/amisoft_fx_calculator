package com.amisoft.anz;

import com.amisoft.services.ConversionRateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FXCalculatorRunner implements CommandLineRunner {

    @Value(value = "${fx.calculator.reg-exp.input}")
    String regExpression;

    @Value(value = "${fx.calculator.main-conversion-table}")
    String mainConversionTable;

    public Map<String, Double> mainConversionRateMap = new TreeMap<>();

    @Autowired
    ConversionRateLoader conversionRateLoader;

    @Override
    public void run(String... strings) {

        loadAllConversionRates();

        Boolean isDone = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to FX Calculator");
        System.out.println("HELP : **** Enter the currencies you want to convert in  .... <Source Currency> <amount>  <In> <Target Currency> format e.g. AUD 100.00 in USD");

        processCalculator(isDone, scanner);

        scanner.close();
    }


    private void processCalculator(Boolean isDone, Scanner scanner) {

        System.out.println(mainConversionRateMap.get("AUDUSD"));
        do {

            System.out.println("Please enter your input now :");
            System.out.println();
            readFxInput(scanner);
            isDone = exitCheck(isDone, scanner);

        } while (!isDone);
    }


    private void loadAllConversionRates() {

        conversionRateLoader.loadmainCurrencyConversion(mainConversionTable, mainConversionRateMap);
    }

    private void readFxInput(Scanner scanner) {


        System.out.println();
        String currencyInput = scanner.nextLine();

        System.out.println("Input is :" + currencyInput);

        Pattern pattern = Pattern.compile(regExpression);
        Matcher matcher = pattern.matcher(currencyInput);

        if (matcher.find()) {
            System.out.println(matcher.group());
            analyzeInput(currencyInput);
        } else {
            System.out.println("Sorry .... Invalid input format . Please try again..");
        }


    }

    private void analyzeInput(String inputString) {

        Scanner scanner = new Scanner(inputString).useDelimiter("\\s");
        String sourceCurrency = scanner.next();
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.next();
        String targetCurrency = scanner.next();
        scanner.close();
        scanner = null;

        System.out.println("Source Currency :" + sourceCurrency);
        System.out.println("Amount :" + amount);
        System.out.println("Target Currency :" + targetCurrency);
    }

    private Boolean exitCheck(Boolean isDone, Scanner scanner) {
        System.out.println("Do you want to continue ? (Y/N)");
        System.out.println();
        String input = scanner.nextLine();
        if (input != null && input.equalsIgnoreCase("N")) {
            isDone = true;
        }
        return isDone;
    }


}
