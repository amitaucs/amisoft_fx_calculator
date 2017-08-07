package com.amisoft.anz;

import com.amisoft.Constant;
import com.amisoft.dto.CurrencyConversionDetailsDto;
import com.amisoft.services.ConversionCalculatorService;
import com.amisoft.services.DisplayService;
import com.amisoft.services.FxCalculatorService;
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
    FxCalculatorService fxCalculatorService;

    @Autowired
    Constant constant;


    @Override
    public void run(String... strings) {

        if (constant.isConsoleEntry) {

            fxCalculatorService.startFxCalculator(Optional.empty());

        }

    }

}
