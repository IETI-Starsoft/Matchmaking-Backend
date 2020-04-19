package edu.escuelaing.ieti.matchmaking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.services.WaitTestService;

@Service
public class WaitTest implements WaitTestService {

    @Autowired
    private ActivityServiceImpl activityService;

    public Wait newWait(String tipo, Activity act, String id) {
        return new Wait(tipo, act, id, activityService);
    }

}