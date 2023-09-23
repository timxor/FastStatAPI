// PlayerStatsController.java
//

package src.main.java.FastStatAPI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class PlayerStatsController {

	private final PlayerStatsRepository repository;

	PlayerStatsController(PlayerStatsRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
  
	// tag::get-aggregate-root[]
	@GetMapping("/faststat")
	CollectionModel<EntityModel<PlayerStats>> all() {

		List<EntityModel<PlayerStats>> playerStats = repository.findAll().stream()
				.map(player -> EntityModel.of(player,
						linkTo(methodOn(PlayerStatsController.class).one(player.getId())).withSelfRel(),
						linkTo(methodOn(PlayerStatsController.class).all()).withRel("playerStats")))
				.collect(Collectors.toList());

		return CollectionModel.of(playerStats, linkTo(methodOn(PlayerStatsController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/faststat")
	PlayerStats newEmployee(@RequestBody PlayerStats newPlayerStats) {
		return repository.save(newPlayerStats);
	}

	// Single item

	// tag::get-single-item[]
	@GetMapping("/faststat/{id}")
	EntityModel<PlayerStats> one(@PathVariable Long id) {

		PlayerStats playerStat = repository.findById(id) //
				.orElseThrow(() -> new PlayerNotFoundException(id));

		return EntityModel.of(playerStat, //
				linkTo(methodOn(PlayerStatsController.class).one(id)).withSelfRel(),
				linkTo(methodOn(PlayerStatsController.class).all()).withRel("playerStats"));
	}
	// end::get-single-item[]

	@PutMapping("/faststat/{id}")
	PlayerStats replacePlayerStats(@RequestBody PlayerStats newPlayerStats, @PathVariable Long id) {

		return repository.findById(id) //
				.map(player -> {
					player.setName(newPlayerStats.getName());
          player.setHeight(newPlayerStats.getHeight());
          player.setNumber(newPlayerStats.getNumber());
          player.setBirthdate(newPlayerStats.getBirthdate());
          player.setPts(newPlayerStats.getPts());
          player.setReb(newPlayerStats.getReb());
          player.setAst(newPlayerStats.getAst());
          player.setTo(newPlayerStats.getTo());
          player.setFastStat(newPlayerStats.getFastStat());
					return repository.save(player);
				}) //
				.orElseGet(() -> {
					newPlayerStats.setId(id);
					return repository.save(newPlayerStats);
				});
	}

	@DeleteMapping("/faststat/{id}")
	void deletePlayerStats(@PathVariable Long id) {
		repository.deleteById(id);
	}
}

