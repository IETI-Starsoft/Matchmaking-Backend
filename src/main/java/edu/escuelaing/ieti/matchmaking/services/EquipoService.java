package edu.escuelaing.ieti.matchmaking.services;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Equipo;
public interface EquipoService {
	Equipo create(Equipo Equipo)  throws MatchmakingException;

    void remove(String Equipo)  throws MatchmakingException;

    Equipo update(Equipo Equipo)  throws MatchmakingException;

    Equipo getEquipoBynameId(String EquipoNameid)  throws MatchmakingException;

    List<Equipo> getAll()  throws MatchmakingException;
}
