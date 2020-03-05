package edu.escuelaing.ieti.matchmaking.exception;

public class MatchmakingException extends Exception {


    public static String ActivityNotFound = "Activity not found in the register";
    public static String ActivityAlreadyExist = "Activity already in the register";
    
    public MatchmakingException(String msg) {
        super(msg);
    }

}
