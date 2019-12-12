package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository repository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
		return (args) -> {
			// save a couple of customers
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
			Game g1 = new Game("Game 1",new Date());
			gameRepository.save(g1);
			Game g2 = new Game("Game 2",new Date());
			gameRepository.save(g2);
			GamePlayer gp1 = new GamePlayer( p1, g1);
			gamePlayerRepository.save(gp1);
			GamePlayer gp2 = new GamePlayer( p2, g1);
			gamePlayerRepository.save(gp2);
			GamePlayer gp3 = new GamePlayer( p3, g2);
			gamePlayerRepository.save(gp3);
			GamePlayer gp4 = new GamePlayer( p4, g2);
			gamePlayerRepository.save(gp4);
			gameRepository.save(g1);
			gameRepository.save(g2);

			System.out.println(gp1);



		};
	}
}