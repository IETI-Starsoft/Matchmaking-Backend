package edu.escuelaing.ieti.matchmaking.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;

public interface TeamRepository extends MongoRepository<Team, String>{

    
    boolean existsById (String teamId);
}
