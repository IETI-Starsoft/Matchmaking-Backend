package edu.escuelaing.ieti.matchmaking.persistence.impl;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryUserRepository implements UserRepository {

    private static Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public User create(User user) throws EntityExistsException {
        String userId = user.getUserId();
        if (userMap.containsKey(userId)) {
            throw new EntityExistsException(User.class, "user", user.toString());
        }
        userMap.put(userId, user);
        return user;
    }

    @Override
    public User update(User user) throws EntityNotFoundException {
        String userId = user.getUserId();
        if (!userMap.containsKey(userId)){
            throw new EntityNotFoundException(User.class, "user", user.toString());
        }
        userMap.put(userId, user);
        return user;
    }

    @Override
    public User getById(String userId) throws EntityNotFoundException {
        User userFound = userMap.get(userId);
        if (userFound == null){
            throw new EntityNotFoundException(User.class, "id" , userId);
        }
        return userFound;
    }

    @Override
    public void remove(String userId) throws EntityNotFoundException {
        if (!userMap.containsKey(userId)){
            throw new EntityNotFoundException(User.class, "id", userId);
        }
        userMap.remove(userId);
    }

    @Override
    public List<User> getAll() {
        Set<Map.Entry<String, User>> entrySet = userMap.entrySet();
        List<User> users = new ArrayList<>();
        for (Map.Entry<String, User> entry : entrySet) {
            users.add(entry.getValue());
        }
        return users;
    }
}
