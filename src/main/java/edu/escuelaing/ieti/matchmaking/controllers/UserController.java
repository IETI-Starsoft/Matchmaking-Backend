package edu.escuelaing.ieti.matchmaking.controllers;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Filter;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) throws EntityNotFoundException {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}/friends")
    public ResponseEntity<?> getUserFriendsById(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserFriendsById(userId), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}/friends/page/{page}")
    public ResponseEntity<?> getUserFriendsById(@PathVariable String userId, @PathVariable int page){
        return new ResponseEntity<>(userService.getUserFriendsById(userId, page), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}/teams/page/{page}")
    public ResponseEntity<?> getUserTeamsById(@PathVariable String userId, @PathVariable int page){
        return new ResponseEntity<>(userService.getUserTeamsById(userId, page), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}/teams")
    public ResponseEntity<?> getUserTeamsById(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserTeamsById(userId), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}/activities")
    public ResponseEntity<?> getUserActivitiesById(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserActivityById(userId), HttpStatus.OK);
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String userEmail) throws EntityNotFoundException {
        return new ResponseEntity<>(userService.getUserByEmail(userEmail), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) throws EntityNotFoundException {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @PostMapping("/id/{userId}/friends/{userEmail}")
    public ResponseEntity<?> addFriendToUser(@PathVariable String userId, @PathVariable String userEmail) throws EntityNotFoundException {
        return new ResponseEntity<>(userService.addFriendToUser(userId, userEmail), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> removeUser(@RequestBody User user) throws EntityNotFoundException {
        userService.remove(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/filters/activity")
	public ResponseEntity<?> getTeamActivitiesByActivity(@RequestBody Filter filter){
		List<Activity> activities=userService.getActivitiesBytActivity(filter); 
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	@PostMapping("/filters/rangeCredrits")
	public ResponseEntity<?> getTeamActivitiesByRangeCredrits(@RequestBody Filter filter){
		List<Activity> activities=userService.getAllActivitiesByRangeCredits(filter); 
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	@PostMapping("/filters/none")
	public ResponseEntity<?> getTeamActivitiesByNoneFilter(@RequestBody Filter filter){
		List<Activity> activities=userService.getAllActivitiesByNoneFilter(filter);
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	@PostMapping("/id/{userId}/ranking/{score}")
	public ResponseEntity<?> setRankingUser(@PathVariable String userId,@PathVariable Double score ){
		try {
			userService.setRanking(userId, score);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	} 
}
