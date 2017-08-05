package com.amisoft.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConversionCalculatorService {


    @Value(value = "${fx.calculator.default-decimal-point}")
    String defaultDecimal;

    @Value(value = "${fx.calculator.special-decimal-point}")
    String specialDecimal;


  public String doConversion(String sourceCurrency, String targetCurrency, BigDecimal amount,Double conversionRate){

      return null;
  }


}
