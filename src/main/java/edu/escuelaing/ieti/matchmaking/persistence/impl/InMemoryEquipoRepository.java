package edu.escuelaing.ieti.matchmaking.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import edu.escuelaing.ieti.matchmaking.model.Equipo;
import edu.escuelaing.ieti.matchmaking.persistence.EquipoRepository;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;

public class InMemoryEquipoRepository implements EquipoRepository {

	private static Map<String, Equipo> equipoMap = new ConcurrentHashMap<>();

	@Override
	public Equipo create(Equipo equipo) throws EntityExistsException {
		String equipoId = equipo.getEquipoId();
		if (!equipoMap.containsKey(equipoId)) {
			equipoMap.put(equipoId, equipo);
			return equipo;
		}
		throw new EntityExistsException(Equipo.class, "Equipo", equipo.toString());
	}

	@Override
	public void remove(String equipoId) throws EntityNotFoundException {
		if (equipoMap.containsKey(equipoId)) {
			equipoMap.remove(equipoId);
		} else {
			throw new EntityNotFoundException(Equipo.class, "id Equipo", equipoId);
		}

	}

	@Override
	public Equipo update(Equipo equipo) throws EntityNotFoundException {
		String equipoId = equipo.getEquipoId();
		if (equipoMap.containsKey(equipoId)) {
			equipoMap.put(equipoId, equipo);
			return equipo;
		}
		throw new EntityNotFoundException(Equipo.class, "Equipo", equipo.toString());
	}

	@Override
	public Equipo getEquipoBynameId(String equipoId) throws EntityNotFoundException {
		if (equipoMap.get(equipoId) == null) {
			throw new EntityNotFoundException(Equipo.class, "Id Equipo", equipoId);
		} else {
			return equipoMap.get(equipoId);
		}
	}

	@Override
	public List<Equipo> getAll() throws EntityNotFoundException {
		Set<Map.Entry<String, Equipo>> entrySet = equipoMap.entrySet();
		List<Equipo> equipos = new ArrayList<>();
		for (Map.Entry<String, Equipo> entry : entrySet) {
			equipos.add(entry.getValue());
		}
		return equipos;
	}

}
