package edu.escuelaing.ieti.matchmaking.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import edu.escuelaing.ieti.matchmaking.model.Equipo;
import edu.escuelaing.ieti.matchmaking.persistence.EquipoRepository;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
public class InMemoryEquipoRepository implements EquipoRepository {
	
	private static Map<String, Equipo> EquipoMap = new ConcurrentHashMap<>();

	@Override
	public Equipo create(Equipo equipo)  throws MatchmakingException {
		String nombreId=equipo.getNombreid();
		if(!EquipoMap.containsKey(nombreId)) {
			EquipoMap.put(nombreId, equipo);
			return equipo;
		}
		throw new MatchmakingException("Team already in memory");
	}

	@Override
	public void remove(String nombreId) throws MatchmakingException  {
		if(EquipoMap.containsKey(nombreId)) {
			EquipoMap.remove(nombreId);
		}else{
			throw new MatchmakingException("Team to remove not found");
		}
		
	}

	@Override
	public Equipo update(Equipo equipo) throws MatchmakingException {
		String nombreId=equipo.getNombreid();
		if(EquipoMap.containsKey(nombreId)) {
			EquipoMap.put(nombreId, equipo);
			return equipo;
		}
		 throw new MatchmakingException("Team to update not found");
	}

	@Override
	public Equipo getEquipoBynameId(String EquipoNameid) throws MatchmakingException  {
		try {
			return EquipoMap.get(EquipoNameid);	
		} catch (Exception e) {
			throw new MatchmakingException("Team to get not found");
		}
		
	}

	@Override
	public List<Equipo> getAll()  {
		Set<Map.Entry<String, Equipo>> entrySet = EquipoMap.entrySet();
        List<Equipo> equipos = new ArrayList<>();
        for (Map.Entry<String, Equipo> entry : entrySet) {
        	equipos.add(entry.getValue());
        }
        return equipos;
	}

}
