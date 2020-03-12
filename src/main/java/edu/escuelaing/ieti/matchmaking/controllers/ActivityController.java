package edu.escuelaing.ieti.matchmaking.controllers;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
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
	    public ResponseEntity<?> createActivity(@RequestBody Activity activity) throws MatchmakingException {
	        return new ResponseEntity<>(activityService.create(activity), HttpStatus.CREATED);
	    }
	    @GetMapping("/{activityId}")
	    public ResponseEntity<?> getActivityById(@PathVariable String activityId) throws MatchmakingException {
	        return new ResponseEntity<>(activityService.getActivityById(activityId), HttpStatus.OK);
	    }
	    @PutMapping
	    public ResponseEntity<?> updateActivity(@RequestBody Activity activity) throws MatchmakingException {
	        return new ResponseEntity<>(activityService.update(activity), HttpStatus.OK);
	    }

	    @DeleteMapping("/{activityId}")
	    public ResponseEntity<?> removeActivity(@PathVariable String activityId) throws MatchmakingException {
	    	activityService.remove(activityId);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
}
