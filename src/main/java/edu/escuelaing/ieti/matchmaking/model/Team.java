package edu.escuelaing.ieti.matchmaking.model;

import java.util.ArrayList;
import java.util.Date;

public class Team {
	private ArrayList <User> members;
	
	private User captain; 
	private Integer credits;
	private String teamId; 
	
	
	public ArrayList<User> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

	public User getCaptain() {
		return captain;
	}

	public void setCaptain(User captain) {
		this.captain = captain;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public void addCredits(Integer credits) {
		this.credits += credits;
	}

	public void subCredits(Integer credits) {
		this.credits -= credits;
	}
	public String toString() {
        return "Team{" +
                "teamId='" + teamId + '\'' +
                ", captain='" + captain.toString() + '\'' +
                ", members='" + members.toString() + '\'' +
                '}';
	}
}
