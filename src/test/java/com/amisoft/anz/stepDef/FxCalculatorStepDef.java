package com.amisoft.anz.stepDef;


import com.amisoft.Constant;
import com.amisoft.anz.pojo.FxCalculatorTestInput;
import com.amisoft.services.FxCalculatorService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ContextConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class FxCalculatorStepDef {

    @Autowired
    FxCalculatorService fxCalculatorService;

    @Autowired
    Constant constant;

    List<String> actualOutputMsgFromFxCalculatorList = new ArrayList<>();


    private int count = 0;

    @Given("^John has provided currencies as$")
    public void john_has_provided_different_currencies_as(List<FxCalculatorTestInput> inputStringList) throws Throwable {

        ProcessTestData(inputStringList);

    }


    @Given("^he has provided currencies as$")
    public void he_has_provided_same_currencies_as(List<FxCalculatorTestInput> inputStringList) throws Throwable {

        ProcessTestData(inputStringList);
    }


    private void ProcessTestData(List<FxCalculatorTestInput> inputStringList) {
        inputStringList.forEach(testInputStringPojo -> {

            String inputString = new StringBuilder().append(testInputStringPojo.getSourceCurrency())
                    .append(constant.spaceSeparator)
                    .append(testInputStringPojo.getAmount())
                    .append(constant.spaceSeparator)
                    .append(testInputStringPojo.getPhase())
                    .append(constant.spaceSeparator)
                    .append(testInputStringPojo.getTargetCurrency())
                    .toString();

            Optional<String> optionalInput = Optional.of(inputString);
            String outputMsgAfterConversion = (fxCalculatorService.startFxCalculator(optionalInput)).get();
            actualOutputMsgFromFxCalculatorList.add(outputMsgAfterConversion);

        });
    }


    @Then("^John should get the converted amount <message> as$")
    public void john_should_get_the_converted_amount_message_as(List<FxCalculatorTestInput> inputStringList) throws Throwable {

        actualOutputMsgFromFxCalculatorList.forEach(actualOutputMsg -> {

            Assert.assertEquals(true, StringUtils.equalsIgnoreCase(actualOutputMsg, inputStringList.get(count).getMessage()));
            count++;

        });

        actualOutputMsgFromFxCalculatorList.clear();
    }
}
