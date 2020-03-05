package edu.escuelaing.ieti.matchmaking.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.ieti.matchmaking.model.Equipo;
import edu.escuelaing.ieti.matchmaking.persistence.EquipoRepository;
import edu.escuelaing.ieti.matchmaking.services.EquipoService;

public class EquipoServiceImpl implements EquipoService {
	@Autowired
    private EquipoRepository inMemoryRepository;

	@Override
	public Equipo create(Equipo Equipo) {
		return inMemoryRepository.create(Equipo);
	}

	@Override
	public void remove(String EquipoNameid) {
		inMemoryRepository.remove(EquipoNameid);
	}

	@Override
	public Equipo update(Equipo Equipo) {
		return inMemoryRepository.update(Equipo);
	}

	@Override
	public Equipo getEquipoBynameId(String EquipoNameid) {
		return inMemoryRepository.getEquipoBynameId(EquipoNameid);
	}

	@Override
	public List<Equipo> getAll() {
		return  inMemoryRepository.getAll();
	}

}
