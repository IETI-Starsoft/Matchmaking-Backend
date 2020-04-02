package edu.escuelaing.ieti.matchmaking.controllers;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.State;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
	@Autowired
	private ActivityService activityService;

	@GetMapping
	public ResponseEntity<?> getAllActivities() {
		List<Activity> activities = null;
		try {
			activities = activityService.getAll();
			return new ResponseEntity<>(activities, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) throws EntityExistsException {
		return new ResponseEntity<>(activityService.create(activity), HttpStatus.CREATED);
	}

	@GetMapping("/{activityId}")
	public ResponseEntity<?> getActivityById(@PathVariable String activityId) throws EntityNotFoundException {
		return new ResponseEntity<>(activityService.getActivityById(activityId), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> updateActivity(@RequestBody Activity activity) throws EntityNotFoundException {
		return new ResponseEntity<>(activityService.update(activity), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> removeActivity(@RequestBody Activity activity) throws EntityNotFoundException {
		activityService.remove(activity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/available/{userId}/{state}")
	public ResponseEntity<?> getAvailableActivities(@PathVariable String userId,@PathVariable State state) {
		List<Activity> activities = null;
		try {
			activities = activityService.getActivities(userId,state);
			return new ResponseEntity<>(activities, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
