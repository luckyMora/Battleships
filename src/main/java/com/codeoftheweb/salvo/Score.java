package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Score {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long ScoreID;
    private int actualscore = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    public Score (){}

    public Score(int actualscore, Game game, Player player) {
        this.actualscore = actualscore;
        this.game = game;
        this.player = player;
    }

    //public Score (Integer actualscore){
   //     actualscore = actualscore;
   // }

    public long getScoreID() {
        return ScoreID;
    }

    public void setScoreID(long scoreID) {
        ScoreID = scoreID;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getActualscore() {
        return actualscore;
    }

    public void setActualscore(int actualscore) {
        this.actualscore = actualscore;
    }
}
