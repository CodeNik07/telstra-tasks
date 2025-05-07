package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.entities.SimCard;
import au.com.telstra.simcardactivator.payload.ActuationResult;
import au.com.telstra.simcardactivator.payload.SimActivateRequest;
import au.com.telstra.simcardactivator.payload.SimCardResponse;
import au.com.telstra.simcardactivator.repositories.SimCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class SimCardActivationRestController {

    private final SimCardActivationHandler simCardActivationHandler;
    private final SimCardRepository simCardRepo;


    public SimCardActivationRestController(SimCardActivationHandler simCardActivationHandler, SimCardRepository simCardRepo) {
        this.simCardActivationHandler = simCardActivationHandler;
        this.simCardRepo = simCardRepo;
    }

    @PostMapping("/activate-sim")
    public ResponseEntity<?> activateSIM(@RequestBody SimActivateRequest request) {
        if (request.getIccid() != null && request.getCustomerEmail() != null) {
            ActuationResult response = simCardActivationHandler.actuate(request);
            SimCard sim = new SimCard();
            sim.setIcicid(request.getIccid());
            sim.setCustomerEmail(request.getCustomerEmail());
            sim.setActive(response.success());
            SimCard savedSim = simCardRepo.save(sim);
            System.out.println("Responce :- " + response.success());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSim);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Iccid and CustomerEmail is nacessary");
    }

    @GetMapping("/sim")
    public ResponseEntity<?> getSimCard(@RequestParam Long id) {
        SimCard sim = simCardRepo.findById(id).orElse(null);
        if (sim == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sim card not found");
        }

        SimCardResponse simResponse = new SimCardResponse(sim.getIcicid(), sim.getCustomerEmail(), sim.isActive());

        return ResponseEntity.status(HttpStatus.OK).body(simResponse);
    }



}
