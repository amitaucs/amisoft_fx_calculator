Feature: FX-Calculator - A currency conversion application

  Background: John is accessing Fx-Calculator application

  Scenario: John entered the valid currency for which direct conversion for source to target currency is available.
    Given  John has entered currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | AUD            | 100.00 | In    | USD            |
      | JPY            | 100    | In    | USD            |
    Then John should get the converted amount <message> as
      | message                |
      | AUD 100.00 = USD 83.71 |
      | JPY 100 = USD 0.83     |