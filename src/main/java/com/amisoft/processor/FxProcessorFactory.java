package com.amisoft.processor;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FxProcessorFactory {

    @Autowired
    FxCalculatorConsoleProcessor fxCalculatorConsoleProcessor;

    @Autowired
    FxCalculatorBaseProcessor fxCalculatorBaseProcessor;

    public FxProcessor getFxCalculatorProcessor(Optional<String> optionalInput) {

        return (optionalInput.isPresent() ? fxCalculatorBaseProcessor : fxCalculatorConsoleProcessor);

    }

}
