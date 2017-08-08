package com.amisoft.processor;

import java.util.Optional;

public interface FxProcessor {

    Optional<String> fxCurrencyProcessor(Optional<String> inputStringOptional);
}
