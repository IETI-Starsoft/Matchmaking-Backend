package edu.escuelaing.ieti.matchmaking.controllers;

import edu.escuelaing.ieti.matchmaking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autocomplete")
public class AutoCompleteController {

    private static final int SUGGESTION_LIMIT = 10;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> autocompleteUserSuggestions(@RequestParam String searchstr){
        return new ResponseEntity<>(userService.getUsersEmailContaining(searchstr, SUGGESTION_LIMIT), HttpStatus.OK);
    }

}
