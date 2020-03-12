package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.User;

import java.util.List;

public interface UserService {

    User create(User user) throws EntityExistsException;

    void remove(String userId) throws EntityNotFoundException;

    User update(User user) throws EntityNotFoundException;

    User getUserById(String userId) throws EntityNotFoundException;

    List<User> getAll();
}
