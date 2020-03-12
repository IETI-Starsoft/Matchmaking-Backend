package edu.escuelaing.ieti.matchmaking.persistence;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.User;

import java.util.List;

public interface UserRepository {

    User create(User user) throws EntityExistsException;

    User update(User user) throws EntityNotFoundException;

    User getById(String userId) throws EntityNotFoundException;

    void remove(String userId) throws EntityNotFoundException;

    List<User> getAll();

}
