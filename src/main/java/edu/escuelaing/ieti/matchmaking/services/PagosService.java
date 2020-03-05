package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.model.User;

public interface PagosService {

    void recargaCreditos(User user, int saldo);

    void apostar(User user, int saldo);

}