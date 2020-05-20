package edu.escuelaing.ieti.matchmaking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Document
public class User {

    @Id
    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String imageFileURL;

    private Integer rating;

    private Integer credits;

    private List<String> friends;

    private List<String> teams;

    private List<String> activities;
    
    private List<Double> ranking; 

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

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void addCredits(Integer credits) {
        this.credits += credits;
    }

    public void subCredits(Integer credits) {
        this.credits -= credits;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                ", credits=" + credits +
                '}';
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

    public String getImageFileURL() {
        return imageFileURL;
    }

    public void setImageFileURL(String imageFileURL) {
        this.imageFileURL = imageFileURL;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }
}
