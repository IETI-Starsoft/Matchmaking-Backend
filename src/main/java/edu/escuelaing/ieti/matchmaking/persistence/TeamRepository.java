package edu.escuelaing.ieti.matchmaking.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
@Repository
public interface TeamRepository extends MongoRepository<Team, String>{
    boolean existsById (String teamId);
    Iterable<Team> findAllByCaptain(User captain);
}
