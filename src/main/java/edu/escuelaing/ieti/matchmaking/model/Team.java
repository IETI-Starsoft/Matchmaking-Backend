package edu.escuelaing.ieti.matchmaking.model;

import java.util.ArrayList;
import java.util.Date;

public class Team {
	private ArrayList <User> members;
	
	private User captain; 
	
	private String teamId; 
	
	public User getCapitan() {
		return captain;
	}
	public ArrayList<User> getIntegrantes() {
		return members;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setCapitan(User captain) {
		this.captain = captain;
	}
	public void setIntegrantes(ArrayList<User> members) {
		this.members = members;
	}
	
	public void setNombreid(String equipoId) {
		this.teamId = equipoId;
	}
	public String toString() {
        return "Equipo{" +
                "EquipoId='" + teamId + '\'' +
                ", capitan='" + captain.toString() + '\'' +
                ", integrantes='" + members.toString() + '\'' +
                '}';
	}
}
