Feature: FX-Calculator - A currency conversion application

  Background: John is accessing Fx-Calculator application

  Scenario: Input in valid format for which source to target currency are different and direct conversion is available
    Given   John has provided different currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | AUD            | 100.00 | In    | USD            |
      | JPY            | 100    | In    | USD            |
    Then John should get the converted amount <message> as
      | message                |
      | AUD 100.00 = USD 83.71 |
      | JPY 100 = USD 0.83     |

  Scenario: Input in valid format for which source to target currency are different and inverse of direct conversion is needed
    Given   John has provided inverse currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | USD            | 83.71  | In    | AUD            |
    Then John should get the converted amount <message> as
      | message             |
      | USD 83.71 = AUD 100 |

  Scenario: Input in valid format for which source and target currency is same
    Given John has provided same currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | AUD            | 100.00 | In    | AUD            |
      | EUR            | 100.00 | In    | EUR            |
    Then John should get the converted amount <message> as
      | message                 |
      | AUD 100.00 = AUD 100.00 |
      | EUR 100.00 = EUR 100.00 |

  Scenario: Input in valid format for which direct conversion rate of source and target currency unavailable and cross conversion is needed
    Given John has provided cross currencies as
      | sourceCurrency | amount | phase | targetCurrency |
      | AUD            | 1      | In    | JPA            |
    Then John should get the converted amount <message> as
      | message         |
      | AUD 1 = JPY 100 |

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
      | wweerr         | 2we4   | df    | fvg            |
    Then John should get the converted amount <message> as
      | Message                                              |
      | Sorry .... Invalid input format . Please try again.. |
      | Sorry .... Invalid input format . Please try again.. |