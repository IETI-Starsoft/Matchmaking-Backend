package edu.escuelaing.ieti.matchmaking.services.impl;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.persistence.UserRepository;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;
import edu.escuelaing.ieti.matchmaking.services.TeamService;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Override
    public User create(User user) throws EntityExistsException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException(User.class, "User email", user.getEmail());
        }
        user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(User user) throws EntityNotFoundException {
        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new EntityNotFoundException(User.class, "User email", user.getEmail());
        }
        userRepository.delete(user);
    }

    @Override
    public User update(User user) throws EntityNotFoundException {
        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new EntityNotFoundException(User.class, "User email", user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) throws EntityNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException(User.class, "User id", userId));
    }

    @Override
    public User getUserByEmail(String email) throws EntityNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException(User.class, "User email", email));
    }

    @Override
    public List<User> getUserFriendsById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<User> friends = new ArrayList<>();
        optionalUser.ifPresent(user -> {
            user.getFriends().forEach(friendId -> {
                        userRepository.findById(friendId).ifPresent(friends::add);
                    }
            );
        });
        return friends;
    }

    @Override
    public List<Team> getUserTeamsById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<Team> teams = new ArrayList<>();
        optionalUser.ifPresent(user -> {
            user.getTeams().forEach(teamId -> {
                        try {
                            teams.add(teamService.getTeamById(teamId));
                        } catch (EntityNotFoundException ignore) {
                        }
                    }
            );
        });
        return teams;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Activity> getUserActivityById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<Activity> activities = new ArrayList<>();
        optionalUser.ifPresent(user -> {
            user.getTeams().forEach(activityId -> {
                        try {
                            activities.add(activityService.getActivityById(activityId));
                        } catch (EntityNotFoundException ignore) {
                        }
                    }
            );
        });
        return activities;
    }
}
