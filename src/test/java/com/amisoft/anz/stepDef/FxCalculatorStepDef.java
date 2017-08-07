package com.amisoft.anz.stepDef;


import com.amisoft.anz.pojo.FxCalculatorTestInput;
import com.amisoft.services.FxCalculatorService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@ContextConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class FxCalculatorStepDef {

    @Autowired
    FxCalculatorService fxCalculatorService;

    @Given("^John has entered currencies as$")
    public void john_has_entered_currencies_as(List<FxCalculatorTestInput> inputStringList) throws Throwable {

        System.out.println("Hi");
    }

    @Then("^John should get the converted amount <message> as$")
    public void john_should_get_the_converted_amount_message_as(List<FxCalculatorTestInput> outPutStringMsgList) throws Throwable {

        System.out.println("Hi");
    }
}
