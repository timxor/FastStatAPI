package src.main.java.com.timsiwula.faststatapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
		System.out.println("\n\napi server running at:  http://localhost:8080/faststat   \n\n");
	}
}
