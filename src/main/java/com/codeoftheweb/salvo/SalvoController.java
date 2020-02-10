package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

//import javax.jnlp.ClipboardService;
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

    @Autowired
    private PasswordEncoder passwordEn;

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam String userName, @RequestParam String email, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (repoP.findByUserName(userName) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        repoP.save(new Player(userName, email, firstName, lastName, passwordEn.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @RequestMapping("/games")
    public List<Object> getAll(Authentication authentication) {


        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
           Player loggedPlayer = repoP.findByUserName(authentication.getName());





        List<Object> gamesInfoList = new ArrayList<>();
            Player finalLoggedPlayer = loggedPlayer;
            repo.findAll().forEach(game -> {

            Map<String, Object> gameInfo = new HashMap<>();
            gameInfo.put("created",game.getDate().toString());
                gameInfo.put("Game_Id",game.getGameId());
                gameInfo.put("Gameplayers", getgameplayersinfo(game));
                gameInfo.put("currentLoginUserName", finalLoggedPlayer.getUserName());
                gameInfo.put("Score", getScoreInfo(game));

                //gameInfo.put("Current",finalLoggedPlayer);
                //System.out.println(game.getGameId());
                gamesInfoList.add(gameInfo);

        });

        return gamesInfoList;}
        else return null;

    }

    public Map<String,Object> getgameplayersinfo(Game game) {
        Map<String,Object> gameplayersInfos = new HashMap<>();
        int i = 1;
        for(GamePlayer g : game.gamePlayer) {
            gameplayersInfos.put("PlayerName" + i,g.getPlayer().getUserName());
            gameplayersInfos.put("Player_Id" + i,g.getPlayer().getId());
            gameplayersInfos.put("GamePlayer_Id" + i,g.getGamePlayerId());
            i++;

        }

        return gameplayersInfos;
    }






    @RequestMapping(path = "/games", method = RequestMethod.POST)
    public ResponseEntity<Object> createGame(Authentication authentication) {
        Player loggedinplayer = repoP.findByUserName(authentication.getName());
        List<Game> gameslist = new ArrayList<>();

        repo.findAll().forEach(game -> {

            if(game.getGamePlayer().size() < 2){
                gameslist.add(game);
            }
        });


        if (loggedinplayer == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }else if (gameslist.size() > 0 ){
            Game opengame = gameslist.get(0);

            if(opengame.getGamePlayer().stream().findFirst().get().getPlayer() == loggedinplayer){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {

            GamePlayer gamePlayer = new GamePlayer(loggedinplayer, opengame);
            System.out.println(opengame.getGamePlayer().stream().findFirst().get().getPlayer().getUserName());
            repo.save(opengame);
            repoGP.save(gamePlayer);
            return new ResponseEntity<>(doMap("GPid",gamePlayer.getGamePlayerId()), HttpStatus.CREATED);
            }

        }else {Game game = new Game(new Date());
            GamePlayer gamePlayer = new GamePlayer(loggedinplayer, game);
            repo.save(game);
            repoGP.save(gamePlayer);

            return new ResponseEntity<>(doMap("GPid",gamePlayer.getGamePlayerId()), HttpStatus.CREATED);
        }

    }



    private HashMap<String, Object> doMap(String key, Object value) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }






    public List<Object> getPlayersInfo(Player player) {
        List<Object> playersInfoList = new ArrayList<>();
        Map<String, Object> playersInfo = new HashMap<>();
        playersInfo.put("Username", player.getUserName());
        playersInfo.put("PlayerID", player.getId());
        playersInfo.put("PlayerFirstName", player.getFirstName() );
        playersInfo.put("PlayerLastName", player.getLastName());
        playersInfo.put("PlayerEmail", player.getEmail());
        //playersInfo.put("Score", getScoreInfo(player));
        playersInfoList.add(playersInfo);
        return playersInfoList;
    }




    public Map<String,Object> getScoreInfo(Game game) {
        Map<String,Object> ScoreInfoList = new HashMap<>();
        int i = 1;
        for(Score sc : game.scores){


            ScoreInfoList.put("ScoreID" + i, sc.getScoreID());
            ScoreInfoList.put("ActualScore" + i, sc.getActualscore());
            i++;

        };

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
    @RestController
    public class AppController {



        @RequestMapping(path = "/games/players/{gamePlayerID}/ships", method = RequestMethod.POST)
        public ResponseEntity<Object> getShips(@PathVariable long gamePlayerID, Authentication authentication, @RequestBody List<Ship> ships) {
            Player loggedinplayer = repoP.findByUserName(authentication.getName());
           GamePlayer currentgameP = repoGP.findByGamePlayerId(gamePlayerID);
            System.out.println(ships);
            if (loggedinplayer == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }else if(currentgameP.getPlayer() != loggedinplayer){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }else if(currentgameP.getShips().size() == 5){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }else {
                ships.forEach(ship -> {
                    currentgameP.addShip(ship);
                    repoGP.save(currentgameP);
                });
                return new ResponseEntity<>(HttpStatus.CREATED);
            }

           // return null;
        }
    }



}

