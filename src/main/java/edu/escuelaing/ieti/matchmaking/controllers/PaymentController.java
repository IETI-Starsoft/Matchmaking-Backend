package edu.escuelaing.ieti.matchmaking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.InsufficientFundsException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PutMapping
    public ResponseEntity<?> rechargeCredits(@RequestParam(value = "user") String userID,
            @RequestParam(value = "amount") int amount) throws MatchmakingException, EntityNotFoundException {

        paymentService.rechargeCredits(userID, amount);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<?> addCreditsUserToUser(@RequestParam(value = "user1") String userID1,
            @RequestParam(value = "user2") String userID2, @RequestParam(value = "amount") int amount)
            throws EntityNotFoundException {

        paymentService.addCreditsUserToUser(userID1, userID2, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> addCreditsUserToTeam(@RequestParam(value = "user") String userID,
            @RequestParam(value = "team") String teamID, @RequestParam(value = "amount") int amount)
            throws EntityNotFoundException {
        paymentService.addCreditsUserToTeam(userID, teamID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> addCreditsTeamToUser(@RequestParam(value = "team") String teamID,
            @RequestParam(value = "user") String userID, @RequestParam(value = "amount") int amount)
            throws InsufficientFundsException, EntityNotFoundException {
        paymentService.addCreditsTeamToUser(teamID, userID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> betUserToActivity(@RequestParam(value = "user") String userID,
            @RequestParam(value = "activity") String activityID, @RequestParam(value = "amount") int amount)
            throws EntityNotFoundException {
        paymentService.betUserToActivity(userID, activityID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> betTeamToActivity(@RequestParam(value = "team") String teamID,
            @RequestParam(value = "activity") String activityID, @RequestParam(value = "amount") int amount)
            throws InsufficientFundsException, EntityNotFoundException {
        paymentService.betTeamToActivity(teamID, activityID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> payActivityToUser(@RequestParam(value = "activity") String activityID,
            @RequestParam(value = "user") String userID, @RequestParam(value = "amount") int amount)
            throws EntityNotFoundException {
        paymentService.payActivityToUser(activityID, userID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> payActivityToTeam(@RequestParam(value = "activity") String activityID,
            @RequestParam(value = "team") String teamID, @RequestParam(value = "amount") int amount)
            throws EntityNotFoundException {
        paymentService.payActivityToTeam(activityID, teamID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}