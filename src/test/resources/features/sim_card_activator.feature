Feature: SIM Card Activation As a user of the SIM Card Activator service I want to activate a SIM card using its ICCID So that I can confirm whether the activation was successful or not

Scenario: Successful SIM card activation Given I have a SIM card with ICCID "1255789453849037777" When I submit an activation request for the SIM card Then the activation should be successful when queried

Scenario: Failed SIM card activation Given I have a SIM card with ICCID "8944500102198304826" When I submit an activation request for the SIM card Then the activation should fail when queried