package com.amisoft;

import org.springframework.beans.factory.annotation.Value;

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

}
