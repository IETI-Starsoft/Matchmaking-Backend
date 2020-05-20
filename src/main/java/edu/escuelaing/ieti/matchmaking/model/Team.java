package edu.escuelaing.ieti.matchmaking.model;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	private List<Double> ranking=Arrays.asList(0.0,0.0);
	
	public List<Double> getRanking() {
		return ranking;
	}

	public void setRanking(List<Double> ranking) {
		this.ranking = ranking;
	}
	
	public void setRanking(Double score) {
		Double n=ranking.get(0);
		Double nScore=((ranking.get(1)*n)+score)/(n+1);
		ranking.set(0, n+1);
		ranking.set(1, nScore);
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
