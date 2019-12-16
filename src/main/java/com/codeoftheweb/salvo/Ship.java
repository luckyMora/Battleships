package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Ship {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long ShipID;
    private String Type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePLayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="locations")
    private List<String> Locations = new ArrayList<>();



    public Ship (){ }

    public Ship (String type, List loc ){
        Type = type;
        this.Locations = loc;

    }
    public long getShipID() {
        return ShipID;
    }

    public void setShipID(long shipID) {
        ShipID = shipID;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public List<String> getLocations() {
        return Locations;
    }

    public void setLocations(List<String> locations) {
        Locations = locations;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

}
