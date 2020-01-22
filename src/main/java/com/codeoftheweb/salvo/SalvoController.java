package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    private GameRepository repo;

    @Autowired
    private GamePlayerRepository repoGP;

    @Autowired
    private PlayerRepository repoP;

    @RequestMapping("/games")
    public List<Object> getAll() {
        List<Object> gamesInfoList = new ArrayList<>();

        repo.findAll().forEach(game -> {
            Map<String, Object> gameInfo = new HashMap<>();
            gameInfo.put("created",game.getDate().toString());
                gameInfo.put("Game_Id",game.getGameId());
                gameInfo.put("Gameplayers", getgameplayersinfo(game));
                System.out.println(game.getGameId());
                gamesInfoList.add(gameInfo);

        });

        return gamesInfoList;
    }






    public List<Object> getgameplayersinfo(Game game) {
        List<Object> gameplayersInfoList = new ArrayList<>();
        game.gamePlayer.forEach( g -> {
                    Map<String, Object> gameplayersInfos = new HashMap<>();
                    gameplayersInfos.put("Gameplayer Id",g.getGamePlayerId() );
                    gameplayersInfos.put("Player", getPlayersInfo(g.getPlayer()));
                    gameplayersInfos.put("Ships", getShipsInfo(g));
                    gameplayersInfos.put("Salvos", getSalvosInfo(g));
                    gameplayersInfoList.add(gameplayersInfos);

                }
                );
        return gameplayersInfoList;
    }



    public List<Object> getPlayersInfo(Player player) {
        List<Object> playersInfoList = new ArrayList<>();
        Map<String, Object> playersInfo = new HashMap<>();
        playersInfo.put("PlayerID", player.getId());
        playersInfo.put("PlayerFirstName", player.getFirstName() );
        playersInfo.put("PlayerLastName", player.getLastName());
        playersInfo.put("PlayerEmail", player.getEmail());
        playersInfo.put("Score", getScoreInfo(player));
        playersInfoList.add(playersInfo);
        return playersInfoList;
    }



    public List<Object> getScoreInfo(Player player) {
        List<Object> ScoreInfoList = new ArrayList<>();
        player.getScores().stream().forEach( sc -> {
            Map<String, Object> scoreInfo = new HashMap<>();
            scoreInfo.put("ScoreID", sc.getScoreID());
            scoreInfo.put("ActualScore", sc.getActualscore());
            ScoreInfoList.add(scoreInfo);

        });

        return ScoreInfoList;
    }





    public List<Object> getShipsInfo(GamePlayer g) {
        List<Object> ShipsInfoList = new ArrayList<>();
        g.getShip().forEach( shi -> {
           Map<String, Object> ShipsInfo = new HashMap<>();
           ShipsInfo.put("ShipID", shi.getShipID());
            ShipsInfo.put("ShipLocation", shi.getLocations());
            ShipsInfoList.add(ShipsInfo);


        });

        return ShipsInfoList;
    }

    public List<Object> getSalvosInfo(GamePlayer g) {
        List<Object> SalvoInfoList = new ArrayList<>();
        g.getSalvos().forEach( sa ->{
            Map<String, Object> SalvoInfo = new HashMap<>();
            SalvoInfo.put("SalvoID", sa.getSalvoID());
            SalvoInfo.put("SalvoTurnNumber", sa.getTurnNumber());
            SalvoInfo.put("SalvoLocation", sa.getSalvolocations());
            SalvoInfoList.add(SalvoInfo);
        });
        return SalvoInfoList;
    }



    public List<Object> allScoresInfo(Player player){
        List<Object> allscoreList = new ArrayList<>();
        List<Integer> winList = new ArrayList<>();
        List<Integer> tieList = new ArrayList<>();
        List<Integer> loseList = new ArrayList<>();
//        long[] winsList = new long[0];
//        long[] tiesList = new long[0];
//        long[] losesList = new long[0];
        System.out.println(player.getFirstName());
        player.getScores().forEach(score -> {
            Map<String, Object> ScoresInfo = new HashMap<>();

            if (score.getActualscore() == 2) {
                winList.add(score.getActualscore());
            }else if (score.getActualscore() == 1){
                tieList.add(score.getActualscore());
            }else {
                loseList.add(score.getActualscore());
            }
            ScoresInfo.put("Loses", loseList.size());
            ScoresInfo.put("Ties", tieList.size());
            ScoresInfo.put("Wins", winList.size());

            allscoreList.add(ScoresInfo);
        });
        System.out.println(winList);
        System.out.println(loseList);
        return allscoreList;
    }

    public Object totalscoreInfo(Player player) {
        Map<String,Object> totalscoreInfoList = new HashMap<>();
        Set<Score> scores = player.getScores();
        int totalscore = 0;
        for(Score sco: scores ){
            totalscore += sco.getActualscore();
            totalscoreInfoList.put("totalscore", totalscore);

        };
        return totalscoreInfoList;
    }





    @RequestMapping("/game_view/{gamePlayerID}")
    public List<Object> gameview(@PathVariable long gamePlayerID) {
        List<Object> gamesviewList = new ArrayList<>();
        GamePlayer currentGP = repoGP.findByGamePlayerId(gamePlayerID);

//      System.out.println(currentGP.getPlayer().getEmail());
        Map<String, Object> gameplayerInfos = new HashMap<>();
        gameplayerInfos.put("GamplayerID",currentGP.getGamePlayerId());
        gameplayerInfos.put("User", currentGP.getPlayer().getUserName());
        gameplayerInfos.put("Ships", getShipsInfo(currentGP));
        gameplayerInfos.put("Salvos", getSalvosInfo(currentGP));
        gameplayerInfos.put("Enemy", getEnemyInfo(currentGP));
        gamesviewList.add(gameplayerInfos);
        ;
        return gamesviewList;

    }

    public List<Object> getEnemyInfo( GamePlayer gamePlayer){
        List<Object> EnemyInfosList = new ArrayList<>();
        gamePlayer.getGame().getGamePlayer().stream().forEach(gPlayer ->{
            if(gPlayer != gamePlayer ){
                Map<String, Object> EnemyInfo = new HashMap<>();
                EnemyInfo.put("Enemy_User_Name", gPlayer.getPlayer().getUserName());
                EnemyInfo.put("Enemy_Salvos", getSalvosInfo(gPlayer));
                EnemyInfosList.add((EnemyInfo));
            }
        });
        return  EnemyInfosList;
    }
    @RequestMapping("/ranking")
    public List<Object> ranking() {
        List<Object> rankingInfosList = new ArrayList<>();
        repoP.findAll().forEach(player -> {
            Map<String, Object> rankingInfo = new HashMap<>();
            rankingInfo.put("Player", player.getUserName());
            rankingInfo.put("Scores", allScoresInfo(player));
            rankingInfo.put("Totalscore", totalscoreInfo(player));
            rankingInfosList.add(rankingInfo);
        });
        return rankingInfosList;
    }


}

