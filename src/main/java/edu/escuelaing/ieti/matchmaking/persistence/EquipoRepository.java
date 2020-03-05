package edu.escuelaing.ieti.matchmaking.persistence;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.model.Equipo;

public interface EquipoRepository {
	Equipo create(Equipo Equipo);

    void remove(String Equipo);

    Equipo update(Equipo Equipo);

    Equipo getEquipoBynameId(String EquipoNameid);

    List<Equipo> getAll();
}
