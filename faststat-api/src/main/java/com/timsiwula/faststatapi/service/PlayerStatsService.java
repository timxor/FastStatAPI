package src.main.java.com.timsiwula.faststatapi.service;

import src.main.java.com.timsiwula.faststatapi.models.PlayerStats;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PlayerStatsService {
    public static final String SAMPLE_JSON_FILE_PATH = "./src/main/resources/static/sample.json";
    private List<PlayerStats> playerStatsList = new ArrayList<>();
    public List<PlayerStats> getAllPlayerStats(){
        playerStatsList = readPlayerStatsFromFile();

        // Calculate FastStat for each player and add it to the PlayerStats objects
        for (PlayerStats player : playerStatsList) {
            int score = calculateFastStatScore(player);
            player.setFastStat(score);
        }

        // Sort the list by FastStat in descending order
        playerStatsList.sort(Comparator.comparingInt(PlayerStats::getFastStat).reversed());
        return playerStatsList;
    }
    
    public List<PlayerStats> defineCustomFormula(String postRequestPayload) {
        String[] parts = postRequestPayload.split("=", 2);

        String formulaName = parts[0].trim();
        String formulaEquation = parts[1].trim();
        playerStatsList = readPlayerStatsFromFile();


        // Calculate FastStat for each player and add it to the PlayerStats objects
        for (PlayerStats player : playerStatsList) {
            int score = calculateFastStatScore(player);
            player.setFastStat(score);

            // Calculate custom formula for each player
            double customScore = evaluate(formulaEquation, player);
            player.setDynamicProperty(formulaName, customScore);
        }

        // Sort the list by FastStat in descending order
        playerStatsList.sort(Comparator.comparingInt(PlayerStats::getFastStat).reversed());
        return playerStatsList;
    }

    public double evaluate(String formula, PlayerStats player) {
        formula = formula.replace("PTS", String.valueOf(player.getPts()))
                .replace("REB", String.valueOf(player.getReb()))
                .replace("AST", String.valueOf(player.getAst()))
                .replace("TO", String.valueOf(player.getTo()));
        return new org.mariuszgromada.math.mxparser.Expression(formula).calculate();
    }

    private List<PlayerStats> readPlayerStatsFromFile() {
        String relativePath = SAMPLE_JSON_FILE_PATH;
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File jsonFile = new File(absolutePath.toString());
        List<PlayerStats> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode root = objectMapper.readTree(jsonFile);
            JsonNode list = root.path("result");
            if (list.isArray()) {
                for (JsonNode node : list) {
                    PlayerStats playerStats = objectMapper.treeToValue(node, PlayerStats.class);
                    result.add(playerStats);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int calculateFastStatScore(PlayerStats player) {

        int score = 0;
        // get the baseline score from (total points + rebounds + assist) - turnovers
        int baseLineScore = getBaselinePoints(player);

        // get 10 points for every inch over 6 feet
        int tallPoints = getTallPoints(player);

        // get 10 points if the players first and last name is an alliterative name
        // an alliterative name is a name in which both the first and last names begin with the same letter
        int alliterativeNamePoints = getAlliterativeNamePoints(player);

        // get the value of their jersey number and their age added as points
        int jerseyAndAgePoints = getJerseyPoints(player) + getAgePoints(player);

        // aggregate all points
        score = baseLineScore + tallPoints + alliterativeNamePoints + jerseyAndAgePoints;

        return score;
    }

    public int getBaselinePoints(PlayerStats player) {
        return (player.getPts() + player.getReb() + player.getAst()) - player.getTo();
    }

    public int getTallPoints(PlayerStats player) {
        String[] parts = player.getHeight().split("'");
        int feet = Integer.parseInt(parts[0]);
        if (feet <= 5) return 0;
        int inches = Integer.parseInt(parts[1].replaceAll("\"", ""));
        return (feet - 6) * 12 + inches * 10;
    }

    public int getJerseyPoints(PlayerStats player) {
        return Integer.parseInt(player.getNumber());
    }

    public int getAgePoints(PlayerStats player) {
        LocalDate birthDate = LocalDate.parse(player.getBirthdate());
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }

    public int getAlliterativeNamePoints(PlayerStats player) {
        int score = 0;
        String name = player.getName().trim();

        if (name.isEmpty() || !name.contains(" ")) {
            return score;
        }

        String[] names = name.split(" ");

        if (names.length != 2 || names[0].isEmpty() || names[1].isEmpty()) {
            return score;
        }

        // Get the first character of both names and compare them
        char firstLetterFirstName = names[0].charAt(0);
        char firstLetterLastName = names[1].charAt(0);

        if (Character.toLowerCase(firstLetterFirstName) == Character.toLowerCase(firstLetterLastName)) {
            score += 10;
        }

        return score;
    }
    
}
