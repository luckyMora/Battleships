package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    private GameRepository repo;

    @RequestMapping("/games")
    public List<Object> getAll() {
        List<Object> gamesIdList = new ArrayList<>();

        repo.findAll().forEach(game -> {
            Map<String, Object> gameIds = new HashMap<>();
            gameIds.put("created",game.getDate().toString());
                gameIds.put("Id",game.getGameId());
                gameIds.put("Gameplayers", getgameplayersinfo(game));
                System.out.println(game.getGameId());
                gamesIdList.add(gameIds);

        });

        return gamesIdList;
    }

    public List<Object> getgameplayersinfo(Game game) {
        List<Object> gameplayersIdList = new ArrayList<>();
        game.gamePlayer.forEach( g -> {
                    Map<String, Object> gameplayersIds = new HashMap<>();
                    gameplayersIds.put("Gameplayer Id",g.getGamePlayerId() );
                    gameplayersIds.put("Player", getPlayersInfo(g.getPlayer()));
                    gameplayersIds.put("Ships", getShipsInfo(g));
                    gameplayersIdList.add(gameplayersIds);

                }
                );
        return gameplayersIdList;
    }

    public List<Object> getPlayersInfo(Player player) {
        List<Object> playersInfoList = new ArrayList<>();
        Map<String, Object> playersInfo = new HashMap<>();
        playersInfo.put("PlayerID", player.getId());
        playersInfo.put("PlayerFirstName", player.getFirstName() );
        playersInfo.put("PlayerLastName", player.getLastName());
        playersInfo.put("PlayerEmail", player.getEmail());
        playersInfoList.add(playersInfo);
        return playersInfoList;
    }

    public List<Object> getShipsInfo(GamePlayer g) {
        List<Object> ShipsInfoList = new ArrayList<>();
        System.out.println(g.getShip());
        g.getShip().forEach( ga -> {
            System.out.println(ga);
           Map<String, Object> ShipsInfo = new HashMap<>();
           ShipsInfo.put("ShipID", ga.getShipID());
            ShipsInfo.put("ShipLocation", ga.getLocations());
            ShipsInfoList.add(ShipsInfo);


        });

        return ShipsInfoList;
    }
}

