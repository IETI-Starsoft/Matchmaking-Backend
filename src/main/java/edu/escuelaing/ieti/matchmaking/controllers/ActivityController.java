package edu.escuelaing.ieti.matchmaking.controllers;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	    public ResponseEntity<?> createActivity(@RequestBody Activity activity) throws EntityExistsException {
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
}
