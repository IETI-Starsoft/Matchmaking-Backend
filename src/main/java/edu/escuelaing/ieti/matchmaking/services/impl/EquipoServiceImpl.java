package edu.escuelaing.ieti.matchmaking.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Equipo;
import edu.escuelaing.ieti.matchmaking.persistence.EquipoRepository;
import edu.escuelaing.ieti.matchmaking.services.EquipoService;

public class EquipoServiceImpl implements EquipoService {
	@Autowired
	private EquipoRepository inMemoryRepository;

	@Override
	public Equipo create(Equipo equipo) throws EntityExistsException {
		return inMemoryRepository.create(equipo);
	}

	@Override
	public void remove(String equipoId) throws EntityNotFoundException {
		inMemoryRepository.remove(equipoId);
	}

	@Override
	public Equipo update(Equipo equipo) throws EntityNotFoundException {
		return inMemoryRepository.update(equipo);
	}

	@Override
	public Equipo getEquipoBynameId(String equipoId) throws EntityNotFoundException {
		return inMemoryRepository.getEquipoBynameId(equipoId);
	}

	@Override
	public List<Equipo> getAll() throws EntityNotFoundException {
		return inMemoryRepository.getAll();
	}

}
