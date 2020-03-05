package edu.escuelaing.ieti.matchmaking.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import edu.escuelaing.ieti.matchmaking.model.Equipo;
import edu.escuelaing.ieti.matchmaking.persistence.EquipoRepository;

public class InMemoryEquipoRepository implements EquipoRepository {
	
	private static Map<String, Equipo> EquipoMap = new ConcurrentHashMap<>();

	@Override
	public Equipo create(Equipo equipo) {
		String nombreId=equipo.getNombreid();
		if(!EquipoMap.containsKey(nombreId)) {
			EquipoMap.put(nombreId, equipo);
			return equipo;
		}
		return null;
	}

	@Override
	public void remove(String nombreId) {
		if(EquipoMap.containsKey(nombreId)) {
			EquipoMap.remove(nombreId);
		}
	}

	@Override
	public Equipo update(Equipo equipo) {
		String nombreId=equipo.getNombreid();
		if(EquipoMap.containsKey(nombreId)) {
			EquipoMap.put(nombreId, equipo);
			return equipo;
		}
		return null;
	}

	@Override
	public Equipo getEquipoBynameId(String EquipoNameid) {
		return EquipoMap.get(EquipoNameid);
	}

	@Override
	public List<Equipo> getAll() {
		Set<Map.Entry<String, Equipo>> entrySet = EquipoMap.entrySet();
        List<Equipo> equipos = new ArrayList<>();
        for (Map.Entry<String, Equipo> entry : entrySet) {
        	equipos.add(entry.getValue());
        }
        return equipos;
	}

}
