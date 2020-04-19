package edu.escuelaing.ieti.matchmaking.services.impl;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.GroupActivity;
import edu.escuelaing.ieti.matchmaking.model.IndividualActivity;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;

public class Wait implements Runnable {

    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval = 1000 * 60;
    private String tipo;
    private Activity act;
    private String id;
    private ActivityServiceImpl activityService;

    public Wait(String tipo, Activity act, String id, ActivityServiceImpl activityService) {
        this.tipo = tipo;
        this.act = act;
        this.id = id;
        this.activityService = activityService;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    public void run() {
        running.set(true);
        while (running.get()) {
            try {
                Thread.sleep(interval);
                if (running.get()) {
                    if (tipo.equals("winner")) {
                        act = activityService.finishActivityPay(id, act);
                        // act.setWinner(id);
                    } else {
                        String win;
                        if (act.getClass().getSimpleName().equals("IndividualActivity")) {
                            IndividualActivity iAct = (IndividualActivity) act;
                            if (iAct.getIdPlayer1().equals(id)) {
                                win = iAct.getIdPlayer2();
                            } else {
                                win = iAct.getIdPlayer1();
                            }
                        } else {
                            GroupActivity gAct = (GroupActivity) act;
                            if (gAct.getIdTeam1().equals(id)) {
                                win = gAct.getIdTeam2();
                            } else {
                                win = gAct.getIdTeam1();
                            }
                        }
                        act = activityService.finishActivityPay(win, act);
                        // act.setLoser(id);
                    }
                    act.setState(edu.escuelaing.ieti.matchmaking.model.State.Finished);
                    activityService.update(act);
                    stop();
                    activityService.removeWait(act.getId());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted");
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}