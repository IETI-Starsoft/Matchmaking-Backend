package edu.escuelaing.ieti.matchmaking.services.impl;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.persistence.UserRepository;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Override
    public User create(User user) throws EntityExistsException {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new EntityExistsException(User.class, "User email", user.getEmail());
        }
        user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(User user) throws EntityNotFoundException {
        if (!userRepository.existsByEmail(user.getEmail())){
            throw new EntityNotFoundException(User.class, "User email", user.getEmail());
        }
        userRepository.delete(user);
    }

    @Override
    public User update(User user) throws EntityNotFoundException {
        if (!userRepository.existsByEmail(user.getEmail())){
            throw new EntityNotFoundException(User.class, "User email", user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) throws EntityNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()){
            throw new EntityNotFoundException(User.class, "User id", userId);
        }
        return optionalUser.get();
    }

    @Override
    public User getUserByEmail(String email) throws EntityNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()){
            throw new EntityNotFoundException(User.class, "User email", email);
        }
        return optionalUser.get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
