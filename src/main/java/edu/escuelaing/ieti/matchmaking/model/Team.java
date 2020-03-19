package edu.escuelaing.ieti.matchmaking.model;

import java.util.ArrayList;
import java.util.Date;

public class Team {
	private ArrayList<User> members;

	private User captain;

	private String teamId;

	private Integer credits;

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

	public void setNombreid(String teamId) {
		this.teamId = teamId;
	}

	public void addCredits(Integer credits) {
		this.credits += credits;
	}

	public void subCredits(Integer credits) {
		this.credits -= credits;
	}

	public String toString() {
		return "Team{" + "teamId='" + teamId + '\'' + ", captain='" + captain.toString() + '\'' + ", members='"
				+ members.toString() + '\'' + '}';
	}
}
