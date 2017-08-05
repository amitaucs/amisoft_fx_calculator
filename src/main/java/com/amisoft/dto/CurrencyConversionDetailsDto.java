package com.amisoft.dto;

import java.math.BigDecimal;

public class CurrencyConversionDetailsDto {

    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal amount;
    private Double conversionRate;
    private int upToWhichDecimalPt;

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

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public int getUpToWhichDecimalPt() {
        return upToWhichDecimalPt;
    }

    public void setUpToWhichDecimalPt(int upToWhichDecimalPt) {
        this.upToWhichDecimalPt = upToWhichDecimalPt;
    }
}
