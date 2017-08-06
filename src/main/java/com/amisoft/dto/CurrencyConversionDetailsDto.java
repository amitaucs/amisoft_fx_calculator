package com.amisoft.dto;

import java.math.BigDecimal;

public class CurrencyConversionDetailsDto {

    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal amount;
    private BigDecimal conversionRate;
    private int upToWhichDecimalPt;
    private BigDecimal convertedAmount;


    public CurrencyConversionDetailsDto(String sourceCurrency, String targetCurrency, BigDecimal amount, BigDecimal conversionRate, int upToWhichDecimalPt, BigDecimal convertedAmount) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.amount = amount;
        this.conversionRate = conversionRate;
        this.upToWhichDecimalPt = upToWhichDecimalPt;
        this.convertedAmount = convertedAmount;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public int getUpToWhichDecimalPt() {
        return upToWhichDecimalPt;
    }

    public void setUpToWhichDecimalPt(int upToWhichDecimalPt) {
        this.upToWhichDecimalPt = upToWhichDecimalPt;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
