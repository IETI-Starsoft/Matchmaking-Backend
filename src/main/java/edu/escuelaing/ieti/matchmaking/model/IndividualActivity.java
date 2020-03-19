package edu.escuelaing.ieti.matchmaking.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonTypeName("IndividualActivity")
public class IndividualActivity extends Activity{

     
    private User player1;
    private User player2;


    public IndividualActivity(){}

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

  
}