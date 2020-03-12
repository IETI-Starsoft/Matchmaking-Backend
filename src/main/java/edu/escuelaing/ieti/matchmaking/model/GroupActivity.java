package edu.escuelaing.ieti.matchmaking.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("GroupActivity")
public class GroupActivity extends Activity {

     
    private Team team1;
    private Team team2;

    public GroupActivity(){}

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
  
}