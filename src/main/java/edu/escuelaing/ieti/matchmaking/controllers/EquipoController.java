package edu.escuelaing.ieti.matchmaking.controllers;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Equipo;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.services.EquipoService;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipo")
public class EquipoController {
	@Autowired
    private EquipoService equipoService;
	 @GetMapping
	  public ResponseEntity<?> getAllEquipos() {
	        List<Equipo> equipos = null;
	        try {
	            equipos = equipoService.getAll();
	            return new ResponseEntity<>(equipos, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    @PostMapping
	    public ResponseEntity<?> createEquipo(@RequestBody Equipo equipo) throws EntityExistsException {
	        return new ResponseEntity<>(equipoService.create(equipo), HttpStatus.CREATED);
	    }
	    @GetMapping("/{equipoId}")
	    public ResponseEntity<?> getTeamById(@PathVariable String equipoId) throws EntityNotFoundException {
	        return new ResponseEntity<>(equipoService.getEquipoById(equipoId), HttpStatus.OK);
	    }
	    @PutMapping
	    public ResponseEntity<?> updateUser(@RequestBody Equipo equipo) throws EntityNotFoundException {
	        return new ResponseEntity<>(equipoService.update(equipo), HttpStatus.OK);
	    }

	    @DeleteMapping("/{equipoId}")
	    public ResponseEntity<?> removeUser(@PathVariable String equipoId) throws EntityNotFoundException {
	    	equipoService.remove(equipoId);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
}
