package com.amisoft.utils;

import com.amisoft.Constant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FxCalculatorValidator {

    @Autowired
    Constant constant;

    public boolean validateInputStringWithValidFormat(String currencyInput) {

        Pattern pattern = Pattern.compile(constant.regExpression);
        Matcher matcher = pattern.matcher(currencyInput);

        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }
}
