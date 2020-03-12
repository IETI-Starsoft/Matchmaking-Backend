package edu.escuelaing.ieti.matchmaking.model;

import java.util.ArrayList;
import java.util.Date;

public class Equipo {
	private ArrayList <User> integrantes;
	
	private User capitan; 
	
	private String equipoId; 
	
	public User getCapitan() {
		return capitan;
	}
	public ArrayList<User> getIntegrantes() {
		return integrantes;
	}
	public String getEquipoId() {
		return equipoId;
	}
	public void setCapitan(User capitan) {
		this.capitan = capitan;
	}
	public void setIntegrantes(ArrayList<User> integrantes) {
		this.integrantes = integrantes;
	}
	
	public void setNombreid(String equipoId) {
		this.equipoId = equipoId;
	}
	public String toString() {
        return "Equipo{" +
                "EquipoId='" + equipoId + '\'' +
                ", capitan='" + capitan.toString() + '\'' +
                ", integrantes='" + integrantes.toString() + '\'' +
                '}';
	}
}
