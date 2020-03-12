package edu.escuelaing.ieti.matchmaking.controllers;

import edu.escuelaing.ieti.matchmaking.exception.UserMatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = null;
        try {
            users = userService.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        User user = null;
        try {
            user = userService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserMatchmakingException e) {
            e.printStackTrace();
            logger.log(Level.INFO, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User createdUser = null;
        try {
            createdUser = userService.create(user);
            return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
        } catch (UserMatchmakingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updatedUser = null;
        try {
            updatedUser = userService.update(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (UserMatchmakingException e) {
            e.printStackTrace();
            logger.log(Level.INFO, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable String userId) {
        try {
            userService.remove(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserMatchmakingException e) {
            e.printStackTrace();
            logger.log(Level.INFO, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
