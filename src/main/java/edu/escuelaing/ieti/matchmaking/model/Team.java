package edu.escuelaing.ieti.matchmaking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Team {
	
	@Id
	private String teamId;
	
	private ArrayList <String> members;
	
	private String captainId; 
	
	private Integer credits;
	 
	private List<String> activities;

	private String name; 
	
	private Integer nRating; 
	
	private Double rating;
	
	public void setCalculateRating(Integer score) {
		Integer n=nRating;
		Double nScore=((rating*n)+score)/(n+1);
		setnRating(n+1);
		setRating(nScore);
	}

	public Integer getnRating() {
		return nRating;
	}

	public void setnRating(Integer nRating) {
		this.nRating = nRating;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
                ", captain='" + captainId + '\'' +
                ", members='" + members.toString() + '\'' +
                '}';
	}

	public String getCaptainId() {
		return captainId;
	}

	public void setCaptainId(String captainId) {
		this.captainId = captainId;
	}

	public List<String> getActivities() {
		return activities;
	}

	public void setActivities(List<String> activities) {
		this.activities = activities;
	}
}
