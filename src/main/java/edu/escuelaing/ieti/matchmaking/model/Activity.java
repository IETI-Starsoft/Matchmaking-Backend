package edu.escuelaing.ieti.matchmaking.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.springframework.data.annotation.Id;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typ")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GroupActivity.class, name = "GroupActivity"),
        @JsonSubTypes.Type(value = IndividualActivity.class, name = "IndividualActivity")})
public abstract class Activity  {

    @Id
    private String id;
    
    private Date date;
    private Date publicationDate;  
    private int bet; 
    private String description; 
    private String type; 
    private String location;
    private Integer credits;
    private String winner; 
    private State state;
    private String owner;
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
            this.bet = bet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public void addCredits(Integer credits){
        this.credits += credits; 
    }

    public void subCredits(Integer credits){
        this.credits -= credits;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Activity [bet=" + bet + ", credits=" + credits + ", date=" + date + ", description=" + description
                + ", id=" + id + ", location=" + location + ", owner=" + owner + ", publicationDate=" + publicationDate
                + ", state=" + state + ", type=" + type + ", winner=" + winner + "]";
    }

  

   


}