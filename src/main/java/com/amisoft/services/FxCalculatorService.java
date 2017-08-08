package com.amisoft.services;


        import com.amisoft.processor.FxProcessorFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.Optional;

@Service
public class FxCalculatorService {

    @Autowired
    FxProcessorFactory fxProcessorFactory;

    public Optional<String> startFxCalculator(Optional<String> optionalInputString) {

        return fxProcessorFactory.getFxCalculatorProcessor(optionalInputString)
                .fxCurrencyProcessor(optionalInputString);

    }
}
