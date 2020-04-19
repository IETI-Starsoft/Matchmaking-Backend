package edu.escuelaing.ieti.matchmaking.controllers;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Filter;
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
	public ResponseEntity<?> getAvailableActivities(@PathVariable String userId, @PathVariable State state) {
		List<Activity> activities = null;
		try {
			activities = activityService.getActivities(userId, state);
			return new ResponseEntity<>(activities, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/filters/none")
	public ResponseEntity<?> getAvailableActivitiesFilter(@RequestBody Filter filter) {
		List<Activity> activities = null;
		try {
			activities = activityService.getAvailableActivitiesFilter(filter.getUserConsulting(), filter);
			return new ResponseEntity<>(activities, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/filters/activiti")
	public ResponseEntity<?> getAvailableActivitiesByTypeActiviti(@RequestBody Filter filter) {
		List<Activity> activities = null;
		try {
			activities = activityService.getAvailableActivitiesByTypeActiviti(filter.getUserConsulting(), filter);
			return new ResponseEntity<>(activities, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/filters/participants")
	public ResponseEntity<?> getAvailableActivitiesByParticipants(@RequestBody Filter filter) {
		List<Activity> activities = null;
		try {
			activities = activityService.getAvailableActivitiesByParticipants(filter.getUserConsulting(), filter);
			return new ResponseEntity<>(activities, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/filters/rangeCredrits")
	public ResponseEntity<?> getAvailableActivitiesByRangeCredrits(@RequestBody Filter filter) {
		List<Activity> activities = null;
		try {
			activities = activityService.getAvailableActivitiesByRangeCredrits(filter.getUserConsulting(), filter);
			return new ResponseEntity<>(activities, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{activityId}/winner/{winnerId}")
	public ResponseEntity<?> activityWinner(@PathVariable String activityId, @PathVariable String winnerId) {
		try {
			Activity act = activityService.winnerActivity(activityId, winnerId);
			return new ResponseEntity<>(act, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MatchmakingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{activityId}/loser/{loserId}")
	public ResponseEntity<?> activityLoser(@PathVariable String activityId, @PathVariable String loserId) {
		try {
			Activity act = activityService.loserActivity(activityId, loserId);
			return new ResponseEntity<>(act, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MatchmakingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
