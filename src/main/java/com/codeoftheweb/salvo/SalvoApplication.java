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
	public CommandLineRunner initData(PlayerRepository repository, GameRepositoty gameRepositoty, GamePlayerRepository gamePlayerRepository) {
		return (args) -> {
			// save a couple of customers
			Player p1 = new Player("jackyB","jacky@kek.com", "Jack", "Bauer");
			repository.save(p1);
			repository.save( new Player("chloeO","chloe@na.com","Chloe", "O'Brian"));
			repository.save( new Player("kimB", "kim@was.com", "Kim", "Bauer"));
			repository.save( new Player("davidP","david@hey.com","David", "Palmer"));
			repository.save( new Player("michelleD", "michelle@fuck.com","Michelle", "Dessler"));
			repository.save( new Player("keko", "keckse@live.de", "Kendrick", "Jafar"));
			Game g1 = new Game("Game 1",new Date());
			gameRepositoty.save(g1);
			GamePlayer gp1 = new GamePlayer( p1, g1);
			gamePlayerRepository.save(gp1);

			System.out.println(gp1);



		};
	}
}