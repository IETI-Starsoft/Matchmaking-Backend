package edu.escuelaing.ieti.matchmaking.controllers;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Filter;
import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.services.TeamService;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
public class TeamController {
	@Autowired
    private TeamService teamService;
	 @GetMapping
	  public ResponseEntity<?> getAllTeams() throws EntityNotFoundException {
	        List<Team> teams = null;
	        	teams = teamService.getAll();
	            return new ResponseEntity<>(teams, HttpStatus.OK);
	       
	    }
	    @PostMapping
	    public ResponseEntity<?> createTeam(@RequestBody Team team) throws EntityExistsException,EntityNotFoundException {
	        return new ResponseEntity<>(teamService.create(team), HttpStatus.CREATED);
	    }
	    @GetMapping("/{teamId}")
	    public ResponseEntity<?> getTeamById(@PathVariable String teamId) throws EntityNotFoundException {
	        return new ResponseEntity<>(teamService.getTeamById(teamId), HttpStatus.OK);
	    }
	    @GetMapping("{teamId}/members")
	    public ResponseEntity<?> getTeamMembersById(@PathVariable String teamId) throws EntityNotFoundException {
	        return new ResponseEntity<>(teamService.getMembersByTeam(teamId), HttpStatus.OK);
	    }
	    @PutMapping
	    public ResponseEntity<?> updateTeam(@RequestBody Team team) throws EntityNotFoundException {
	        return new ResponseEntity<>(teamService.update(team), HttpStatus.OK);
	    }

	    @DeleteMapping
	    public ResponseEntity<?> removeTeam(@PathVariable Team team) throws EntityNotFoundException {
	    	teamService.remove(team);
	        return new ResponseEntity<>(HttpStatus.OK);
		}
		
	    @GetMapping("/captain/{captainId}")
		public ResponseEntity<?> getAllTeams(@PathVariable String captainId) throws EntityNotFoundException {
			  List<Team> teams = null;
				  teams = teamService.getTeamsByCaptainId(captainId);
				  return new ResponseEntity<>(teams, HttpStatus.OK);
		}	

		@GetMapping("/{teamId}/activities")
		public ResponseEntity<?> getTeamActivitiesById(@PathVariable String teamId){
			return new ResponseEntity<>(teamService.getTeamActivitiesById(teamId), HttpStatus.OK);
		}
		@PostMapping("/filters/activity")
		public ResponseEntity<?> getTeamActivitiesByActivity(@RequestBody Filter filter){
			List<Activity> activities=teamService.getActivitiesBtActivity(filter); 
			return new ResponseEntity<>(activities,HttpStatus.OK);
		}
		@PostMapping("/filters/rangeCredrits")
		public ResponseEntity<?> getTeamActivitiesByRangeCredrits(@RequestBody Filter filter){
			List<Activity> activities=teamService.getAllActivitiesByRangeCredits(filter); 
			return new ResponseEntity<>(activities,HttpStatus.OK);
		}
		@PostMapping("/filters/none")
		public ResponseEntity<?> getTeamActivitiesByNoneFilter(@RequestBody Filter filter){
			List<Activity> activities=teamService.getAllActivitiesByNoneFilter(filter);
			return new ResponseEntity<>(activities,HttpStatus.OK);
		}
		@PutMapping("/id/{teamId}/ranking/{score}")
		public ResponseEntity<?> setRankingUser(@PathVariable String teamId,@PathVariable Integer score ){
			try {
				teamService.setRanking(teamId, score);
			} catch (EntityNotFoundException e) {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} 
}
