package com.amisoft.anz;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources", glue="com.amisoft.anz.stepDef")
public class CucumberFxCalculatorTest {
}
