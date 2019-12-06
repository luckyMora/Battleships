package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository repository) {
		return (args) -> {
			// save a couple of customers
			Player player1 = new Player("jackyB","jacky@kek.com", "Jack", "Bauer");
			Player player2 = new Player("chloeO","chloe@na.com","Chloe", "O'Brian");
			Player player3 = new Player("kimB", "kim@was.com", "Kim", "Bauer");
			Player player4 = new Player("davidP","david@hey.com","David", "Palmer");
			Player player5 = new Player("michelleD", "michelle@fuck.com","Michelle", "Dessler");
			repository.save(player1);
			repository.save(player2);
			repository.save(player3);
			repository.save(player4);
			repository.save(player5);
			repository.save( new Player("keko", "keckse@live.de", "Kendrick", "Jafar"));

		};
	}
}