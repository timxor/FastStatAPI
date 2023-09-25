package src.main.java.com.timsiwula.faststatapi.service;

import src.main.java.com.timsiwula.faststatapi.models.PlayerStats;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PlayerStatsService {
    private List<PlayerStats> playerStatsList = new ArrayList<>();
    public List<PlayerStats> getAllPlayerStats(){
        playerStatsList = readPlayerStatsFromFile();

        // Calculate FastStat for each player and add it to the PlayerStats objects
        for (PlayerStats player : playerStatsList) {
            int score = player.calculateFastStatScore();
            player.setFastStat(score);
        }

        // Sort the list by FastStat in descending order
        playerStatsList.sort(Comparator.comparingInt(PlayerStats::getFastStatScore).reversed());
        return playerStatsList;
    }

    private List<PlayerStats> readPlayerStatsFromFile() {
        String relativePath = "./faststat-api/src/main/resources/static/sample.json";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
//        System.out.println("absolutePath = " + absolutePath.toString());
        File jsonFile = new File(absolutePath.toString());
        List<PlayerStats> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode list = objectMapper.readTree(jsonFile).path("result");
            result = Arrays.asList(objectMapper.treeToValue(list,PlayerStats[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
