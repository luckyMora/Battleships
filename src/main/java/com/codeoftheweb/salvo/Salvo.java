package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long SalvoID;
    private  int turnNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePLayer_id")
    private GamePlayer gamePlayer;

//    @ElementCollection
//    @Column(name="locations")
    private String salvolocations;

    public Salvo (){}

    public Salvo(int tn, String local ){
        turnNumber = tn;
        this.salvolocations = local;
    }

    public long getSalvoID() {
        return SalvoID;
    }

    public void setSalvoID(long salvoID) {
        SalvoID = salvoID;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public String getSalvolocations() {
        return salvolocations;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
