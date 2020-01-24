package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String password;





    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayer;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<Score> scores;



    public Player(){ }

    public Player (String Name, String em, String first, String last, String password){
        userName = Name;
        email = em;
        firstName = first;
        lastName = last;
        this.password = password;

    }
    public String getFirstName() {
        return firstName;
    }

    public Set<GamePlayer> getGamePlayer() {
        return gamePlayer;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String toString(){
        return firstName + " " + lastName + " with the " + userName + ", " + email;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public void addScore(Score score){
        score.setPlayer(this);
        this.scores.add(score);
    }
}
