package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.payload.ActuationResult;
import au.com.telstra.simcardactivator.payload.SimActivateRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimCardActivationHandler {

    private final RestTemplate restTemplate;
    private final String incentiveApiUrl;


    public SimCardActivationHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.incentiveApiUrl = "http://localhost:8444/actuate";
    }

    public ActuationResult actuate(SimActivateRequest request) {
        return restTemplate.postForObject(incentiveApiUrl, request, ActuationResult.class);
    }
}
