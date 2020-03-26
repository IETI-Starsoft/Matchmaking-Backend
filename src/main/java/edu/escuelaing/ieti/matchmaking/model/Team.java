package edu.escuelaing.ieti.matchmaking.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Team {
	
	@Id
	private String teamId;
	
	private ArrayList <String> members;
	
	private User captain; 
	
	private Integer credits;
	 
	
	private String name; 
	
	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}

	public User getCaptain() {
		return captain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
                "name='" + name + '\'' +
                ", captain='" + captain.toString() + '\'' +
                ", members='" + members.toString() + '\'' +
                '}';
	}
}
