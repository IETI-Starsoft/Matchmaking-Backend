package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    void remove(String userId);

    User update(User user);

    User getUserById(String userId);

    List<User> getAll();
}
