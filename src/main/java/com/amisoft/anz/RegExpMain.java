package com.amisoft.anz;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpMain {

    public static void main(String[] args){


        BigDecimal conversionRate = (BigDecimal.valueOf(1).divide(BigDecimal.valueOf(0.8371),4, RoundingMode.HALF_EVEN));
        System.out.println(conversionRate);


        /*String input  ="AUD 456.67 IN USD";

        //Pattern pattern = Pattern.compile("([A-Z]{3}[\\s]{1,4}[0-9]{1,10}[\\s]{1,4}[IN]{2}[\\s]{1,4}[A-Z]{3})");
        Pattern pattern = Pattern.compile("(^[A-Z]{3}[\\s]{1,4}[0-9]+(\\.[0-9]{1,2})*[\\s]{1,4}[IN]{2}[\\s]{1,4}([A-Z]{3}$))");

        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }else{
            System.out.println("No match");
        }*/
    }
}
