package edu.escuelaing.ieti.matchmaking.controllers;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.services.UserService;
import edu.escuelaing.ieti.matchmaking.util.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("users")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userLogin) throws ServletException, EntityNotFoundException {
        String jwtToken = "";

        if (userLogin.getEmail() == null || userLogin.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        String email = userLogin.getEmail();
        String password = userLogin.getPassword();

        User user = userService.getUserByEmail(email);

        String pwd = user.getPassword();

        if (!passwordEncryptor.checkPassword(password, pwd)) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }

        jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date()).signWith(
                SignatureAlgorithm.HS256, "secretkey").compact();

        return new ResponseEntity<>(new Token(jwtToken, user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User registerUser) throws EntityExistsException {
        return new ResponseEntity<>(userService.create(registerUser), HttpStatus.CREATED);
    }

}
