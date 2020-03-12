package edu.escuelaing.ieti.matchmaking.services;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Equipo;
public interface EquipoService {
	
	Equipo create(Equipo equipo)  throws EntityExistsException;

    void remove(String equipo)  throws EntityNotFoundException;

    Equipo update(Equipo equipo)  throws EntityNotFoundException;

    Equipo getEquipoBynameId(String equipoId)  throws EntityNotFoundException;

    List<Equipo> getAll()  throws EntityNotFoundException;
}
