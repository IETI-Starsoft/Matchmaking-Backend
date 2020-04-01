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

}