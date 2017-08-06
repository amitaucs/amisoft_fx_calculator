package com.amisoft;

import org.springframework.beans.factory.annotation.Value;

public  final class Constant {

    @Value(value = "${fx.calculator.reg-exp.input}")
    public static String regExpression;

    @Value(value = "${fx.calculator.main-conversion-table}")
    public static String mainConversionTable;

    @Value(value = "${fx.calculator.default-decimal-point}")
    public static String defaultDecimal;

    @Value(value = "${fx.calculator.special-decimal-point}")
    public static String specialDecimal;


    //Message

    @Value(value = "${fx.message.welcome}")
    public static String welcomeMsg;

    @Value(value = "${fx.message.format-info}")
    public static String formatMsg;

    @Value(value = "${fx.message.enter-input}")
    public static String enterInputMsg;

    @Value(value = "${fx.message.invalid-input}")
    public static String invalidInputMsg;

    @Value(value = "${fx.message.input-delimiter}")
    public static String inputDelimiter;

    @Value(value = "${fx.message.continue-option-msg}")
    public static String continueOptionMsg;

    @Value(value = "${fx.message.no-msg}")
    public static String no;

}
