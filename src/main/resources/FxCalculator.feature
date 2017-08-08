Feature: FX-Calculator - A currency conversion application

  Background: John is accessing Fx-Calculator application

  Scenario: Input in valid format for which source to target currency are different and direct conversion is available.
    Given   John has provided different currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | AUD            | 100.00 | In    | USD            |
      | JPY            | 100    | In    | USD            |
    Then John should get the converted amount <message> as
      | message                |
      | AUD 100.00 = USD 83.71 |
      | JPY 100 = USD 0.83     |

  Scenario: Input in valid format for which source and target currency is same
    Given John has provided same currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | AUD            | 100.00 | In    | AUD            |
      | EUR            | 100.00 | In    | EUR            |
    Then John should get the converted amount <message> as
      | message                 |
      | AUD 100.00 = AUD 100.00 |
      | EUR 100.00 = EUR 100.00 |

  Scenario: Input in valid format for source and target currency for which no conversion rate available
    Given John has provided invalid currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | KRW            | 100    | In    | FJD            |
    Then John should get the converted amount <message> as
      | Message                         |
      | Unable to find rate for KRW/FJD |

  Scenario: Input in invalid format for source and target currency
    Given John has provided invalid input as
      | sourceCurrency | amount | phase | targetCurrency |
      | invalid        | 445e   | In    | FJDG           |
    Then John should get the converted amount <message> as
      | Message                                              |
      | Sorry .... Invalid input format . Please try again.. |