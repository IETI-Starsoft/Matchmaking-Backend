package edu.escuelaing.ieti.matchmaking.persistence;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.model.Equipo;

public interface EquipoRepository {
	
	Equipo create(Equipo equipo);

    void remove(String equipo);

    Equipo update(Equipo equipo);

    Equipo getEquipoBynameId(String equipoNameid);

    List<Equipo> getAll();
}
