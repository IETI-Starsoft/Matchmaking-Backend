package edu.escuelaing.ieti.matchmaking.exception;

public class UserMatchmakingException extends MatchmakingException{

    public static final String USER_NOT_FOUND = "User not found";

    public static final String USER_ALREADY_EXISTS = "User already exists";

    public UserMatchmakingException(String msg) {
        super(msg);
    }

}
