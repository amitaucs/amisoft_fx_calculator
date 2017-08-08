package com.amisoft.processor;

import com.amisoft.Constant;
import com.amisoft.utils.FxCalculatorValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Scanner;

public class FxCalculatorConsoleProcessor implements FxProcessor {

    @Autowired
    FxCalculatorBaseProcessor fxCalculatorBaseProcessor;

    @Autowired
    Constant constant;

    @Autowired
    FxCalculatorValidator fxCalculatorValidator;

    @Override
    public Optional<String> fxCurrencyProcessor(Optional<String> optionalInputString) {

        showWelcomeMsgInConsole();
        Boolean isDone = false;
        Scanner scanner = new Scanner(System.in);
        processCalculator(isDone, scanner);
        scanner.close();
        return null;
    }


    private void showWelcomeMsgInConsole() {
        System.out.println(constant.welcomeMsg);
        System.out.println(constant.formatMsg);
        System.out.println();
    }


    private void processCalculator(Boolean isDone, Scanner scanner) {

        do {

            System.out.println(constant.enterInputMsg);
            String currencyInput = readInputStringFromConsole(scanner);

            if (fxCalculatorValidator.validateInputStringWithValidFormat(currencyInput)) {

                fxCalculatorBaseProcessor.fxCurrencyProcessor(Optional.of(currencyInput));

            } else {
                System.out.println(constant.invalidInputMsg);
            }
            isDone = showMessageInConsoleWithExitOption(isDone, scanner);

        } while (!isDone);
    }


    private String readInputStringFromConsole(Scanner scanner) {

        System.out.println();
        return scanner.nextLine();
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


}
