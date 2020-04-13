package edu.escuelaing.ieti.matchmaking.model;

import java.util.List;

public class Filter {
	private String name; 
	private List<String> labels;
	private List<Integer> rangeCredrits;
	private String participants;
	private String userConsulting;
	private Integer pag;
	private State stateActiviti;
	
	
	public State getStateActiviti() {
		return stateActiviti;
	}
	public void setStateActiviti(State stateActiviti) {
		this.stateActiviti = stateActiviti;
	}
	public Integer getPag() {
		return pag;
	}
	public void setPag(Integer pag) {
		this.pag = pag;
	}
	@Override
	public String toString() {
		return "Filter [name=" + name + ", labels=" + labels + ", rangeCredrits=" + rangeCredrits + ", participants="
				+ participants + ", userConsulting=" + userConsulting + ", pag=" + pag + ", stateActiviti="
				+ stateActiviti + "]";
	}
	public String getUserConsulting() {
		return userConsulting;
	}
	public void setUserConsulting(String userConsulting) {
		this.userConsulting = userConsulting;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public List<Integer> getRangeCredrits() {
		return rangeCredrits;
	}
	public void setRangeCredrits(List<Integer> rangeCredrits) {
		this.rangeCredrits = rangeCredrits;
	}
	public String getParticipants() {
		return participants;
	}
	public void setParticipants(String participants) {
		this.participants = participants;
	} 

}
