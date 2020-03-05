package edu.escuelaing.ieti.matchmaking.persistence;

import edu.escuelaing.ieti.matchmaking.model.User;

import java.util.List;

public interface UserRepository {

    User create(User user);

    User update(User user);

    User getById(String userId);

    void remove(String userId);

    List<User> getAll();

}
