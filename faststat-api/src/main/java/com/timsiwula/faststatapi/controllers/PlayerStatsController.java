package src.main.java.com.timsiwula.faststatapi.controllers;

import org.springframework.http.ResponseEntity;
import src.main.java.com.timsiwula.faststatapi.models.PlayerStats;
import src.main.java.com.timsiwula.faststatapi.service.PlayerStatsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> getAllStats() {
        List<PlayerStats> stats = service.getAllPlayerStats();
        Map<String, Object> response = new HashMap<>();
        response.put("result", stats);
        return ResponseEntity.ok(response);
    }
}
