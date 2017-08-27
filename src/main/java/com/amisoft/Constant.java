package com.amisoft;

import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  final class Constant {


    @Value(value = "${fx.calculator.reg-exp.input}")
    public  String regExpression;

    @Value(value = "${fx.calculator.main-conversion-table}")
    public  String mainConversionTable;

    @Value(value = "${fx.calculator.default-decimal-point}")
    public  int defaultDecimal;

    @Value(value = "${fx.calculator.special-decimal-point-currency}")
    public  String specialDecimalCurrency;

    @Value(value = "${fx.calculator.console}")
    public  boolean isConsoleEntry;




    //Message

    @Value(value = "${fx.message.welcome}")
    public  String welcomeMsg;

    @Value(value = "${fx.message.format-info}")
    public  String formatMsg;

    @Value(value = "${fx.message.enter-input}")
    public  String enterInputMsg;

    @Value(value = "${fx.message.invalid-input}")
    public  String invalidInputMsg;

    @Value(value = "${fx.message.input-delimiter}")
    public  String inputDelimiter;

    @Value(value = "${fx.message.continue-option-msg}")
    public  String continueOptionMsg;

    @Value(value = "${fx.message.no-msg}")
    public  String no;

    @Value(value = "${fx.message.key-separator}")
    public  String keySeparator;

    @Value(value = "${fx.message.key-value-seprator}")
    public  String keyValueSeparator;

    @Value(value = "${fx.message.currency-pair-separator}")
    public  String currencyPairSeparator;


    @Value(value = "${fx.calculator.cad-cross-conversion-table}")
    public  String cadCrossConversion;

    @Value(value = "${fx.calculator.aud-cross-conversion-table}")
    public  String audCrossConversion;

    @Value(value = "${fx.calculator.cny-cross-conversion-table}")
    public  String cnyCrossConversion;

    @Value(value = "${fx.calculator.czk-cross-conversion-table}")
    public  String czkCrossConversion;

    @Value(value = "${fx.calculator.dkk-cross-conversion-table}")
    public  String dkkCrossConversion;

    @Value(value = "${fx.calculator.eur-cross-conversion-table}")
    public  String eurCrossConversion;

    @Value(value = "${fx.calculator.gbp-cross-conversion-table}")
    public  String gbpCrossConversion;

    @Value(value = "${fx.calculator.jpy-cross-conversion-table}")
    public  String jpyCrossConversion;

    @Value(value = "${fx.calculator.nok-cross-conversion-table}")
    public  String nokCrossConversion;

    @Value(value = "${fx.calculator.nzd-cross-conversion-table}")
    public  String nzdCrossConversion;

    @Value(value = "${fx.calculator.usd-cross-conversion-table}")
    public  String usdCrossConversion;

    @Value(value = "${fx.calculator.conversion-rate-decimal-point}")
    public  String crossConvDecimalPoint;


    // Display

    public  String spaceSeparator = " ";

    @Value(value = "${fx.display.unable-to-find-conversion}")
    public  String displayUnableToFindConversion;

    @Value(value = "${fx.display.slash}")
    public  String displaySlash;

    @Value(value = "${fx.display.equal}")
    public  String displayEqual;


    //Cache load

    @Value(value = "${fx.calculator.cache-source}")
    public  String cacheLoadSource;


}
