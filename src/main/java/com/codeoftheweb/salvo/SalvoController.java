package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SalvoController {
    @Autowired
    private GameRepository repo;

    @RequestMapping("/api/games")
    public List<Object> getAll() {
        List<Object> gamesIdList = new ArrayList<>();

        repo.findAll().forEach(game -> {
            Map<String, Object> gameIds = new HashMap<>();
            gameIds.put("created",game.getDate().toString());
                gameIds.put("Id",game.getGameId());
                gameIds.put("Gameplayers", getgameplayersinfo(game));
                System.out.println(game.getGameId());
                gamesIdList.add(gameIds);

        }
        );

        return gamesIdList;
    }

    public List<Object> getgameplayersinfo(Game game) {
        List<Object> gameplayersIdList = new ArrayList<>();
        game.gamePlayers.forEach( g -> {
                    Map<String, Object> gameplayersIds = new HashMap<>();
                    gameplayersIds.put("Gameplayer Id",g.getGamePlayerId() );
                    gameplayersIds.put("Player", g.getPlayer());
                    gameplayersIdList.add(gameplayersIds);

                }
                );
        return gameplayersIdList;
    }

}

