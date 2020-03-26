package edu.escuelaing.ieti.matchmaking.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonTypeName("IndividualActivity")
public class IndividualActivity extends Activity{

     
    private String idPlayer1;
    private String idPlayer2;


    public IndividualActivity(){}

    public String getIdPlayer1() {
        return idPlayer1;
    }

    public void setIdPlayer1(String idPlayer1) {
        this.idPlayer1 = idPlayer1;
    }

    public String getIdPlayer2() {
        return idPlayer2;
    }

    public void setIdPlayer2(String idPlayer2) {
        this.idPlayer2 = idPlayer2;
    }
}