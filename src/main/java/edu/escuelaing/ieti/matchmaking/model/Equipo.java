package edu.escuelaing.ieti.matchmaking.model;

import java.util.ArrayList;
import java.util.Date;

public class Equipo {
	private ArrayList <User> integrantes;
	
	private User capitan; 
	
	private String equipoId; 
	
	private Date fechaCreacion;
	
	public User getCapitan() {
		return capitan;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
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
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public void setIntegrantes(ArrayList<User> integrantes) {
		this.integrantes = integrantes;
	}
	
	public void setNombreid(String equipoId) {
		this.equipoId = equipoId;
	}
}
