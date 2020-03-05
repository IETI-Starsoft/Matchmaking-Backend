package edu.escuelaing.ieti.matchmaking.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Equipo;
import edu.escuelaing.ieti.matchmaking.persistence.EquipoRepository;
import edu.escuelaing.ieti.matchmaking.services.EquipoService;

public class EquipoServiceImpl implements EquipoService {
	@Autowired
    private EquipoRepository inMemoryRepository;

	@Override
	public Equipo create(Equipo Equipo) throws MatchmakingException {
		return inMemoryRepository.create(Equipo);
	}

	@Override
	public void remove(String EquipoNameid) throws MatchmakingException {
		inMemoryRepository.remove(EquipoNameid);
	}

	@Override
	public Equipo update(Equipo Equipo)  throws MatchmakingException{
		return inMemoryRepository.update(Equipo);
	}

	@Override
	public Equipo getEquipoBynameId(String EquipoNameid)  throws MatchmakingException{
		return inMemoryRepository.getEquipoBynameId(EquipoNameid);
	}

	@Override
	public List<Equipo> getAll() throws MatchmakingException {
		return  inMemoryRepository.getAll();
	}

}
