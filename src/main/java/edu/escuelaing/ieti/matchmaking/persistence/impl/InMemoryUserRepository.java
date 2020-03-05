package edu.escuelaing.ieti.matchmaking.persistence.impl;

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
    public User create(User user) {
        String userId = user.getUserId();
        if (!userMap.containsKey(userId)){
            userMap.put(userId, user);
            return user;
        }
        return null;
    }

    @Override
    public User update(User user) {
        String userId = user.getUserId();
        if (userMap.containsKey(userId)){
            userMap.put(userId, user);
            return user;
        }
        return null;
    }

    @Override
    public User getById(String userId) {
        return userMap.get(userId);
    }

    @Override
    public void remove(String userId) {
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
