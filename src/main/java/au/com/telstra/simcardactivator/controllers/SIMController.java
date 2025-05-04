package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.payload.ActivateResponse;
import au.com.telstra.simcardactivator.payload.SIMActivateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class SIMController {

    private final RestTemplate restTemplate;


    public SIMController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/activate-sim")
    public void activateSIM(@RequestBody SIMActivateRequest request) {
        if (request.iccid() != null && request.customerEmail() != null) {
            ActivateResponse response = restTemplate.postForObject("http://localhost:8444/actuate", request, ActivateResponse.class);
            System.out.println("Responce :- " + response.success());
        }
    }



}
