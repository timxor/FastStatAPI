// LoadDatabase.java

package src.main.java.FastStatAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(PlayerStatsRepository repository) {

		return args -> {
      
			log.info("Preloading " + 
      repository.save(new PlayerStats("Kevin Anthony", "6'10\"", "37", "1997-11-20", 
																			7, 3, 2, 1, -1)));
      
			log.info("Preloading " + 
      repository.save(new PlayerStats("Avery Adams", "6'3\"", "11", "1990-11-26", 
																			423, 115, 63, 47, -1)));
		};
	}
}

/*
"id": 2,
"name": "Avery Adams",
"height": "6'3\"",
"number": "11",
"birthdate": "1990-11-26",
"PTS": 423,
"Reb": 115,
"AST": 63,
"TO": 47

*/