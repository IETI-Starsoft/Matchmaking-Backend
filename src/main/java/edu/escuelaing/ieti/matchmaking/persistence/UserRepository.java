package edu.escuelaing.ieti.matchmaking.persistence;

import edu.escuelaing.ieti.matchmaking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findByEmailContaining(String searchStr);
}
