package src.main.java.com.timsiwula.faststatapi.controllers;

import src.main.java.com.timsiwula.faststatapi.models.PlayerStats;
import src.main.java.com.timsiwula.faststatapi.service.PlayerStatsService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faststat")
public class PlayerStatsController {
    private final PlayerStatsService service;
    public PlayerStatsController(PlayerStatsService service) {
        this.service = service;
    }
    
    // http://localhost:8080/faststat
    @GetMapping
    public List<PlayerStats> getAllStats() { 
        return service.getAllPlayerStats();
    }
}
