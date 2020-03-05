package edu.escuelaing.ieti.matchmaking.services.impl;

import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.persistence.UserRepository;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository inMemoryRepository;

    @Override
    public User create(User user) {
        return inMemoryRepository.create(user);
    }

    @Override
    public void remove(String userId) {
        inMemoryRepository.remove(userId);
    }

    @Override
    public User update(User user) {
        return inMemoryRepository.update(user);
    }

    @Override
    public User getUserById(String userId) {
        return inMemoryRepository.getById(userId);
    }

    @Override
    public List<User> getAll() {
        return inMemoryRepository.getAll();
    }
}