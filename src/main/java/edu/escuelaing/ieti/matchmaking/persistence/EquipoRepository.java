package edu.escuelaing.ieti.matchmaking.persistence;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Equipo;

public interface EquipoRepository {
	
	Equipo create(Equipo equipo) throws MatchmakingException;

    void remove(String equipo) throws MatchmakingException;

    Equipo update(Equipo equipo)throws MatchmakingException;

    Equipo getEquipoBynameId(String equipoNameid)throws MatchmakingException;

    List<Equipo> getAll() throws MatchmakingException;
}
