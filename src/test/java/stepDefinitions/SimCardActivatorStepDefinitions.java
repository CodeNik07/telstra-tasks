package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.payload.SimActivateRequest;
import au.com.telstra.simcardactivator.payload.SimCardResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private String iccid;
    private static int activationCounter = 0; // To track the auto-incremented ID
    private ResponseEntity<SimCardResponse> queryResponse;

    @Given("I have a SIM card with ICCID {string}")
    public void i_have_a_sim_card_with_iccid(String iccid) {
        this.iccid = iccid;
    }

    @When("I submit an activation request for the SIM card")
    public void i_submit_an_activation_request_for_the_sim_card() {
        SimActivateRequest request = new SimActivateRequest();
        request.setIccid(iccid);
        activationCounter++; // Increment counter for each activation
        restTemplate.postForEntity("http://localhost:8080/activate", request, Void.class);
    }

    @Then("the activation should be successful when queried")
    public void the_activation_should_be_successful_when_queried() {
        queryResponse = restTemplate.getForEntity(
                "http://localhost:8080/query?id=" + activationCounter, SimCardResponse.class);
        assertTrue(queryResponse.getBody().active(), "Activation should be successful");
    }

    @Then("the activation should fail when queried")
    public void the_activation_should_fail_when_queried() {
        queryResponse = restTemplate.getForEntity(
                "http://localhost:8080/query?id=" + activationCounter, SimCardResponse.class);
        assertFalse(queryResponse.getBody().active(), "Activation should fail");
    }


}