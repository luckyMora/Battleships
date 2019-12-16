package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository repository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository) {
		return (args) -> {


			// PLayers
			Player p1 = new Player("jackyB","jacky@kek.com", "Jack", "Bauer");
			repository.save(p1);
			Player p2 =  new Player("chloeO","chloe@na.com","Chloe", "O'Brian");
			repository.save(p2);
			Player p3 = new Player("kimB", "kim@was.com", "Kim", "Bauer");
			repository.save(p3);
			Player p4 = new Player("davidP","david@hey.com","David", "Palmer");
			repository.save(p4);
			Player p5 =  new Player("michelleD", "michelle@fuck.com","Michelle", "Dessler");
			repository.save(p5);
			Player p6 =  new Player("keko", "keckse@live.de", "Kendrick", "Jafar");
			repository.save(p6);


			//Games
			Game g1 = new Game("Game 1",new Date());
			gameRepository.save(g1);
			Game g2 = new Game("Game 2",new Date());
			gameRepository.save(g2);



			//Gameplayers
			GamePlayer gp1 = new GamePlayer( p1, g1);
			gamePlayerRepository.save(gp1);
			GamePlayer gp2 = new GamePlayer( p2, g1);
			gamePlayerRepository.save(gp2);
			GamePlayer gp3 = new GamePlayer( p3, g2);
			gamePlayerRepository.save(gp3);
			GamePlayer gp4 = new GamePlayer( p4, g2);
			gamePlayerRepository.save(gp4);


			//Ship Laocations List
			List<String> l1 = Arrays.asList("A1","A2");
			List<String> l2 = Arrays.asList("B1","B2","B3");
			List<String> l3 = Arrays.asList("C1","C2","C3","C4");
			List<String> l4 = Arrays.asList("D1","D2","D3","D4","D5");
			List<String> l5 = Arrays.asList("A1","B1");
			List<String> l6 = Arrays.asList("A2","B2","C2");
			List<String> l7 = Arrays.asList("A3","B3","C3","D3");
			List<String> l8 = Arrays.asList("A$","B4","C4","D4","E4");


			//Ships
			Ship sh1 = new Ship("submarine", l1);
			gp1.addShip(sh1);
			shipRepository.save(sh1);
			Ship sh2 = new Ship("destroyer", l2);
			gp1.addShip(sh2);
			shipRepository.save(sh2);
			Ship sh3 = new Ship("cruiser", l3);
			gp1.addShip(sh3);
			shipRepository.save(sh3);
			Ship sh4 = new Ship("carrier", l4);
			gp1.addShip(sh4);
			shipRepository.save(sh4);
			Ship sh5 = new Ship("submarine", l5);
			gp2.addShip(sh5);
			shipRepository.save(sh5);
			Ship sh6 = new Ship("destroyer", l6);
			gp2.addShip(sh6);
			shipRepository.save(sh6);
			Ship sh7 = new Ship("cruiser", l7);
			gp2.addShip(sh7);
			shipRepository.save(sh7);
			Ship sh8 = new Ship("carrier", l8);
			gp2.addShip(sh8);
			shipRepository.save(sh8);


			//saving the Gameplayer
			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
			gamePlayerRepository.save(gp3);
			gamePlayerRepository.save(gp4);

			//saving the games
			gameRepository.save(g1);
			gameRepository.save(g2);




		};
	}
}