package edu.escuelaing.ieti.matchmaking.persistence;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.model.Equipo;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;

public interface EquipoRepository {

    Equipo create(Equipo equipo) throws EntityExistsException;

    void remove(String equipo) throws EntityNotFoundException;

    Equipo update(Equipo equipo) throws EntityNotFoundException;

    Equipo getEquipoBynameId(String equipoNameid) throws EntityNotFoundException;

    List<Equipo> getAll() throws EntityNotFoundException;
}
