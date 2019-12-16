package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GamePlayer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long GamePlayerId;
    Date dates;



    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    private Set<Ship> ships = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    public GamePlayer (){ }
    public GamePlayer(Player player, Game game) {
        this.player = player;
        this.game = game;
    }
    public GamePlayer(Player player) {
        this.player = player;
    }

    public long getGamePlayerId() {
        return GamePlayerId;
    }

    public void setGamePlayerId(long gamePlayerId) {
        GamePlayerId = gamePlayerId;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public Set<Ship> getShip() {
        return ships;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    public void addShip(Ship ship){
        ship.setGamePlayer(this);
        ships.add(ship);
    }
}
