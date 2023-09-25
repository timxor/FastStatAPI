package src.main.java.com.timsiwula.faststatapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import src.main.java.com.timsiwula.faststatapi.models.PlayerStats;
import src.main.java.com.timsiwula.faststatapi.service.PlayerStatsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/faststat")
public class PlayerStatsController {
    private final PlayerStatsService service;
    public PlayerStatsController(PlayerStatsService service) {
        this.service = service;
    }

    // http://localhost:8080/faststat
    // curl http://localhost:8080/faststat | jq | less
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStats() {
        List<PlayerStats> stats = service.getAllPlayerStats();
        Map<String, Object> response = new HashMap<>();
        response.put("result", stats);
        return ResponseEntity.ok(response);
    }

    // http://localhost:8080/faststat
    // curl -X POST -H "Content-Type: text/plain" -d "MyFormula = (PTS+(REB-AST))/2" http://localhost:8080/faststat | jq
    @PostMapping
    public ResponseEntity<Map<String, Object>> handlePostRequest(@RequestBody String payload) {
        String[] parts = payload.split("=");
        
        if (parts.length != 2) {
            Map<String, Object> response = new HashMap<>();
            response.put("Invalid format. Expected 'Name = Formula'.", payload);
            return ResponseEntity.badRequest().body(response);
        }

        String name = parts[0].trim();
        String formula = parts[1].trim();

        if (name.isEmpty() || formula.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("Invalid name or formula.", payload);
            return ResponseEntity.badRequest().body(response);
        }

        List<PlayerStats> stats = service.defineCustomFormula(payload);
        Map<String, Object> response = new HashMap<>();
        response.put("result", stats);
        return ResponseEntity.ok(response);
    }
}
