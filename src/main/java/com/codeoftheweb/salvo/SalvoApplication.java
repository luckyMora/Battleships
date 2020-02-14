package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(PlayerRepository repository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository) {
		return (args) -> {


			// Players
			Player p1 = new Player("jackyB","jacky@kek.com", "Jack", "Bauer", passwordEncoder.encode("11111111"));
			repository.save(p1);
			Player p2 = new Player("chloeO","chloe@na.com","Chloe", "O'Brian",passwordEncoder.encode("22222222"));
			repository.save(p2);
			Player p3 = new Player("kimB", "kim@was.com", "Kim", "Bauer",passwordEncoder.encode("33333333"));
			repository.save(p3);
			Player p4 = new Player("davidP","david@hey.com","David", "Palmer",passwordEncoder.encode("44444444"));
			repository.save(p4);
			Player p5 =  new Player("michelleD", "michelle@fuck.com","Michelle", "Dessler", passwordEncoder.encode("55555555"));
			repository.save(p5);
			Player p6 =  new Player("keko", "keckse@live.de", "Kendrick", "Jafar", passwordEncoder.encode("66666666"));
			repository.save(p6);


			//Games
			Game g1 = new Game(new Date());
			gameRepository.save(g1);
			Game g2 = new Game(new Date());
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


			//Salvos
			Salvo Sa1 = new Salvo(1,"71");
			gp1.addSalvo(Sa1);
			salvoRepository.save(Sa1);
			Salvo Sa2 = new Salvo(2,"91");
			gp1.addSalvo(Sa2);
			salvoRepository.save(Sa2);
			Salvo Sa3 = new Salvo(3,"73");
			gp1.addSalvo(Sa3);
			salvoRepository.save(Sa3);
			Salvo Sa4 = new Salvo(4,"75");
			gp1.addSalvo(Sa4);
			salvoRepository.save(Sa4);
			Salvo Sa5 = new Salvo(1,"01");
			gp2.addSalvo(Sa5);
			salvoRepository.save(Sa5);
			Salvo Sa6 = new Salvo(2,"16");
			gp2.addSalvo(Sa6);
			salvoRepository.save(Sa6);
			Salvo Sa7 = new Salvo(3,"27");
			gp2.addSalvo(Sa7);
			salvoRepository.save(Sa7);
			Salvo Sa8 = new Salvo(4,"78");
			gp2.addSalvo(Sa8);
			salvoRepository.save(Sa8);






			//Ship Laocations List
			List<String> l1 = Arrays.asList("01","02");
			List<String> l2 = Arrays.asList("11","12","13");
			List<String> l3 = Arrays.asList("21","22","23","24");
			List<String> l4 = Arrays.asList("31","32","33","34","35");
			List<String> l5 = Arrays.asList("01","11");
			List<String> l6 = Arrays.asList("02","12","22");
			List<String> l7 = Arrays.asList("03","13","23","33");
			List<String> l8 = Arrays.asList("04","14","24","34","44");


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

			// Scores
			Score Sco1 = new Score(1, g1, p1);
			scoreRepository.save(Sco1);
			Score Sco2 = new Score(1, g1, p2);
			scoreRepository.save(Sco2);
			Score Sco3 = new Score(0, g2, p3);
			scoreRepository.save(Sco3);
			Score Sco4 = new Score(2, g2, p4);
			scoreRepository.save(Sco4);


			//saving the Gameplayers
			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
			gamePlayerRepository.save(gp3);
			gamePlayerRepository.save(gp4);


			//Saving PLayers
			repository.save(p1);
			repository.save(p2);


			//saving the games
			gameRepository.save(g1);
			gameRepository.save(g2);




		};
	}






@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	PlayerRepository repopl;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inputUser-> {
			System.out.println(inputUser);
			Player player = repopl.findByUserName(inputUser);
			if (player != null) {
				return new User(player.getUserName(), player.getPassword(),
						AuthorityUtils.createAuthorityList("USER"));
			} else {
				throw new UsernameNotFoundException("Unknown user: " + inputUser);
			}
		});
	}
}

@CrossOrigin(origins = "http://127.0.0.1:5500")
@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
				.authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/style2.css").permitAll()
				.antMatchers("/BFotos/**").permitAll()
				.antMatchers("/manager").permitAll()
				.antMatchers("/index.js").permitAll()
				.antMatchers("/login.js").permitAll()
				.antMatchers("/register.js").permitAll()
				.antMatchers("/ranking.js").permitAll()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/ranking.html").permitAll()


				//.antMatchers("/web/ranking*").permitAll()
				//.antMatchers("/web/login.html").permitAll()
				.antMatchers("/login.html").permitAll()
				.antMatchers("/api/games*").permitAll()
				.antMatchers("/api/players*").permitAll()
				.antMatchers("/api/login*").permitAll()

				.antMatchers("/api/manager*").permitAll()
				.antMatchers("/web/manager*").permitAll()
				.antMatchers("/api/ranking*").permitAll()
				.antMatchers("/api/game_view*").permitAll()
				.antMatchers("/api/player*").permitAll()

				.antMatchers("/**").permitAll()

				.anyRequest().authenticated().and()

				.formLogin()

				.usernameParameter("userName")
				.passwordParameter("password")
				.loginPage("/api/login")
				.and()
				.logout()
				.logoutUrl("/api/logout");;


		// turn off checking for CSRF tokens
		http.csrf().disable();

		// if user is not authenticated, just send an authentication failure response
		http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if login is successful, just clear the flags asking for authentication
		http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

		// if login fails, just send an authentication failure response
		http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if logout is successful, just send a success response
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
}}