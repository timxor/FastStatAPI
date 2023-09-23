package src.main.java.FastStatAPI;

class PlayerNotFoundException extends RuntimeException {

	PlayerNotFoundException(Long id) {
		super("Could not find player stats for player id: " + id);
	}
}
