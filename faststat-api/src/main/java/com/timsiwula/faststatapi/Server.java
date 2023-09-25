package src.main.java.com.timsiwula.faststatapi;
import src.main.java.com.timsiwula.faststatapi.PlayerStats;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode; // Import JsonNode
import java.util.List; // Import List
import java.util.Arrays; // Import Arrays

@SpringBootApplication @JsonIgnoreProperties(ignoreUnknown = true)
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);

		String relativePath = "sample.json";
		Path absolutePath = Paths.get(relativePath).toAbsolutePath();
		File jsonFile = new File(absolutePath.toString());
		// System.out.println("absolutePath: " + absolutePath.toString());
		
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Deserialize JSON into a Java object
			// PlayerStats[] playerStats = objectMapper.readValue(jsonFile, PlayerStats[].class);
			// System.out.println("playerStats: " + playerStats);
			
			
			// ObjectMapper om = new ObjectMapper();
			JsonNode list = objectMapper.readTree(jsonFile).path("result");
			List<PlayerStats> result = Arrays.asList(objectMapper.treeToValue(list,PlayerStats[].class));
			System.out.println("playerStats: " + result);

			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
