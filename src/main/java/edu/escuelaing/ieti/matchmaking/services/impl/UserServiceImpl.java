package edu.escuelaing.ieti.matchmaking.services.impl;

import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void remove(String userId) {

    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User getUserById(String userId) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
