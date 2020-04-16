package edu.escuelaing.ieti.matchmaking.controllers;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autocomplete")
public class AutoCompleteController {

    private static final int SUGGESTION_LIMIT = 10;

    @Autowired
    private UserService userService;

    @GetMapping("/users/id/{userId}")
    public ResponseEntity<?> autocompleteUserSuggestions(@PathVariable String userId, @RequestParam String searchstr) throws EntityNotFoundException {
        return new ResponseEntity<>(userService.getUsersNotFriendsWithEmailContaining(userId, searchstr, SUGGESTION_LIMIT), HttpStatus.OK);
    }

}
