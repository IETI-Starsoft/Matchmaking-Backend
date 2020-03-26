package edu.escuelaing.ieti.matchmaking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.InsufficientFundsException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PutMapping("/user/{userID}/amount/{amount}")
    public ResponseEntity<?> rechargeCredits(@PathVariable String userID, @PathVariable int amount)
            throws MatchmakingException, EntityNotFoundException {
        User user = paymentService.rechargeCredits(userID, amount);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{userID1}/user/{userID2}/amount/{amount}")
    public ResponseEntity<?> addCreditsUserToUser(@PathVariable String userID1, @PathVariable String userID2,
            @PathVariable int amount) throws EntityNotFoundException {

        paymentService.addCreditsUserToUser(userID1, userID2, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user/{userID}/team/{teamID}/amount/{amount}")
    public ResponseEntity<?> addCreditsUserToTeam(@PathVariable String userID, @PathVariable String teamID,
            @PathVariable int amount) throws EntityNotFoundException {
        paymentService.addCreditsUserToTeam(userID, teamID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/team/{teamID}/user/{userID}/amount/{amount}")
    public ResponseEntity<?> addCreditsTeamToUser(@PathVariable String teamID, @PathVariable String userID,
            @PathVariable int amount) throws InsufficientFundsException, EntityNotFoundException {
        paymentService.addCreditsTeamToUser(teamID, userID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user/{userID}/activity/{activityID}/amount/{amount}")
    public ResponseEntity<?> betUserToActivity(@PathVariable String userID, @PathVariable String activityID,
            @PathVariable int amount) throws EntityNotFoundException {
        paymentService.betUserToActivity(userID, activityID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/team/{teamID}/activity/{activityID}/amount/{amount}")
    public ResponseEntity<?> betTeamToActivity(@PathVariable String teamID, @PathVariable String activityID,
            @PathVariable int amount) throws InsufficientFundsException, EntityNotFoundException {
        paymentService.betTeamToActivity(teamID, activityID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/activity/{activityID}/user/{userID}/amount/{amount}")
    public ResponseEntity<?> payActivityToUser(@PathVariable String activityID, @PathVariable String userID,
            @PathVariable int amount) throws EntityNotFoundException {
        paymentService.payActivityToUser(activityID, userID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/activity/{activityID}/team/{teamID}/amount/{amount}")
    public ResponseEntity<?> payActivityToTeam(@PathVariable String activityID, @PathVariable String teamID,
            @PathVariable int amount) throws EntityNotFoundException {
        paymentService.payActivityToTeam(activityID, teamID, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}