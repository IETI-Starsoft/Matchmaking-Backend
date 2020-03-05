package edu.escuelaing.ieti.matchmaking.persistence.impl;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
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
    public User create(User user) throws MatchmakingException {
        String userId = user.getUserId();
        if (userMap.containsKey(userId)) {
            throw new MatchmakingException("User already in memory");
        }
        userMap.put(userId, user);
        return user;
    }

    @Override
    public User update(User user) throws MatchmakingException {
        String userId = user.getUserId();
        if (!userMap.containsKey(userId)){
            throw new MatchmakingException("User not in memory");
        }
        userMap.put(userId, user);
        return user;
    }

    @Override
    public User getById(String userId) throws MatchmakingException {
        User userFound = userMap.get(userId);
        if (userFound == null){
            throw new MatchmakingException("User not in memory");
        }
        return userFound;
    }

    @Override
    public void remove(String userId) throws MatchmakingException {
        if (!userMap.containsKey(userId)){
            throw new MatchmakingException("User not in memory");
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
