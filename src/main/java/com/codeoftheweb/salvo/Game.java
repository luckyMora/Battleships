package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long gameId;
    private String gameName;
    Date date;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    public Game (){ }
    public Game(String gName, Date date) {
        this.gameName = gName;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public long getGameId(){
        return gameId;
    }

    public void setId(long gId) {
        this.gameId = gId;
    }
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void addgamePLayer(GamePlayer gameplayer){
        this.gamePlayers.add(gameplayer);
        gameplayer.setGame(this);
    }

}
