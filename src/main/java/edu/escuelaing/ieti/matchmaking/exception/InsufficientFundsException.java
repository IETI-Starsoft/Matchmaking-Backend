package edu.escuelaing.ieti.matchmaking.exception;

import org.springframework.util.StringUtils;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(Class clazz, String... searchParamsMap) {
        super(InsufficientFundsException.generateMessage(clazz.getSimpleName()));
    }

    private static String generateMessage(String entity) {
        return StringUtils.capitalize(entity) + " insufficient funds to bet";
    }
}
