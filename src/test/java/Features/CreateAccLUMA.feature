Feature: Create Account in LUMA Applications
  Scenario Outline: Validate Error Messages in Create Account
    Given Launch Application
    Then Create an Account with "<testCase_ID>", "<sheetName>" and "<fileName>"
    Then Validate the Message
		And Close Browser

    Examples: 
      | testCase_ID | sheetName |    fileName   |
      |     TC_01   | CreateAcc | CreateAccLUMA |
      |     TC_02   | CreateAcc | CreateAccLUMA |
      |     TC_03   | CreateAcc | CreateAccLUMA |
      |     TC_04   | CreateAcc | CreateAccLUMA |
