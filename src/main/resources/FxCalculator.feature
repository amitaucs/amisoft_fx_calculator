Feature: FX-Calculator - A currency conversion application

  Background: John is accessing Fx-Calculator application

  Scenario: John provide input in valid format for which source to target currency are different and direct conversion is available.
    Given  he has provided different currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | AUD            | 100.00 | In    | USD            |
      | JPY            | 100    | In    | USD            |
    Then John should get the converted amount <message> as
      | message                |
      | AUD 100.00 = USD 83.71 |
      | JPY 100 = USD 0.83     |

  Scenario: John provide input in valid format for which source and target currency is same
    Given he has provided same currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | AUD            | 100.00 | In    | AUD            |
      | EUR            | 100.00 | In    | EUR            |
    Then John should get the converted amount <message> as
      | message                 |
      | AUD 100.00 = AUD 100.00 |
      | EUR 100.00 = EUR 100.00 |