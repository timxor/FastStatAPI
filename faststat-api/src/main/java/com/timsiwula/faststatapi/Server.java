package src.main.java.com.timsiwula.faststatapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.java.com.timsiwula.faststatapi.PlayerStats;
//import java.nio.File;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			
			// /Users/tim/code/FastStatAPI/faststat-api/src/main/resources/sample.json
			String relativePath = "sample.json";

			Path absolutePath = Paths.get(relativePath).toAbsolutePath();
			
			System.out.println("absolutePath: " + absolutePath.toString());
			
			// Replace "sample.json" with the actual path to your JSON file
			File jsonFile = new File(absolutePath.toString());

			// Deserialize JSON into a Java object
			PlayerStats dataObject = objectMapper.readValue(jsonFile, PlayerStats.class);

			// Now you can work with the dataObject as needed

			// Example: Print the dataObject
			System.out.println(dataObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
