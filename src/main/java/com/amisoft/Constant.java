package com.amisoft;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public  final class Constant {

    @Value(value = "${fx.calculator.reg-exp.input}")
    public  String regExpression;

    @Value(value = "${fx.calculator.main-conversion-table}")
    public  String mainConversionTable;

    @Value(value = "${fx.calculator.default-decimal-point}")
    public  String defaultDecimal;

    @Value(value = "${fx.calculator.special-decimal-point}")
    public  String specialDecimal;


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

    @Value(value = "${fx.message.comma-separator}")
    public  String commaSeparator;

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




}
