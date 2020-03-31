package edu.escuelaing.ieti.matchmaking.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonTypeName("GroupActivity")
public class GroupActivity extends Activity {

     
    private String idTeam1;
    private String idTeam2;

    public GroupActivity(){}

    public String getIdTeam1() {
        return idTeam1;
    }

    public void setIdTeam1(String idTeam1) {
        this.idTeam1 = idTeam1;
    }

    public String getIdTeam2() {
        return idTeam2;
    }

    public void setIdTeam2(String idTeam2) {
        this.idTeam2 = idTeam2;
    }

}