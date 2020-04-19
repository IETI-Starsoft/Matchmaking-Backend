package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.services.impl.Wait;

public interface WaitTestService {
    public Wait newWait(String tipo, Activity act, String id);

}