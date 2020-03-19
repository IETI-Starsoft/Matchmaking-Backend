package edu.escuelaing.ieti.matchmaking.controllers;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
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
	    public ResponseEntity<?> createTeam(@RequestBody Team team) throws EntityExistsException {
	        return new ResponseEntity<>(teamService.create(team), HttpStatus.CREATED);
	    }
	    @GetMapping("/{teamId}")
	    public ResponseEntity<?> getTeamById(@PathVariable String teamId) throws EntityNotFoundException {
	        return new ResponseEntity<>(teamService.getTeamById(teamId), HttpStatus.OK);
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
}
