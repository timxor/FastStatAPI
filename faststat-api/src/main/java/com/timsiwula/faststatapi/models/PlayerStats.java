package src.main.java.com.timsiwula.faststatapi.models;

import java.time.Period;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class PlayerStats {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("height")
    private String height;

    @JsonProperty("number")
    private String number;

    @JsonProperty("birthdate")
    private String birthdate;

    @JsonProperty("PTS")
    private int pts;

    @JsonProperty("Reb")
    private int reb;

    @JsonProperty("AST")
    private int ast;

    @JsonProperty("TO")
    private int to;

    @JsonProperty("FastStat")
    private int fastStat;
//	PlayerStats() {}
//	PlayerStats(String name, String height, String number, String birthdate, 
//              int pts, int reb, int ast, int to, int fastStat) {
//        this.name = name;
//		this.height = height;
//        this.number = number;
//        this.birthdate = birthdate;
//        this.pts = pts;
//        this.reb = reb;
//        this.ast = ast;
//        this.to = to;
//        this.fastStat = fastStat;
//    }
    
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getHeight() {
        return this.height;
    }

    public String getNumber() {
        return this.number;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public int getPts() {
        return this.pts;
    }

    public int getReb() {
        return this.reb;
    }

    public int getAst() {
        return this.ast;
    }

    public int getTo() {
        return this.to;
    }

    public int getFastStatScore() {
        return this.fastStat;
    }

    public int calculateFastStatScore() {
        
        int score = 0;
        // get the baseline score from (total points + rebounds + assist) - turnovers
        int baseLineScore = getBaselinePoints();
        
        // get 10 points for every inch over 6 feet
        int tallPoints = getTallPoints();
        
        // get 10 points if the players first and last name is an alliterative name
        // an alliterative name is a name in which both the first and last names begin with the same letter
        int alliterativeNamePoints = getAlliterativeNamePoints();
        
        // get the value of their jersey number and their age added as points
        int jerseyAndAgePoints = getJerseyPoints() + getAgePoints();
        
        // aggregate all points
        score = baseLineScore + tallPoints + alliterativeNamePoints + jerseyAndAgePoints;
        
        return score;
    }
    
    public int getBaselinePoints() {
        return (this.getPts() + this.getReb() + this.getAst()) - this.getTo();
    }
    
    public int getTallPoints() {
        String[] parts = this.getHeight().split("'");
        int feet = Integer.parseInt(parts[0]);
        if (feet <= 5) return 0;
        int inches = Integer.parseInt(parts[1].replaceAll("\"", ""));
        return (feet - 6) * 12 + inches * 10;
    }

    public int getJerseyPoints() {
        return Integer.parseInt(this.getNumber());
    }

    public int getAgePoints() {
        LocalDate birthDate = LocalDate.parse(this.getBirthdate());
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
    
    public int getAlliterativeNamePoints() {
        int score = 0;
        String name = getName().trim();
        
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
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public void setReb(int reb) {
        this.reb = reb;
    }

    public void setAst(int ast) {
        this.ast = ast;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public void setFastStat(int fastStat) {
        this.fastStat = fastStat;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof PlayerStats))
            return false;

        PlayerStats playerStats = (PlayerStats) o;

        return Objects.equals(this.id, playerStats.id) &&
                Objects.equals(this.name, playerStats.name) &&
                Objects.equals(this.name, playerStats.height) &&
                Objects.equals(this.name, playerStats.number) &&
                Objects.equals(this.name, playerStats.birthdate) &&
                Objects.equals(this.name, playerStats.pts) &&
                Objects.equals(this.name, playerStats.reb) &&
                Objects.equals(this.name, playerStats.ast) &&
                Objects.equals(this.name, playerStats.to) &&
                Objects.equals(this.name, playerStats.fastStat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.height, this.number,
                this.birthdate, this.pts, this.reb, this.ast,
                this.to, this.fastStat);
    }

    @Override
    public String toString() {

        return "PlayerStats{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", height='" + this.height + '\'' +
                ", number='" + this.number + '\'' +
                ", birthdate='" + this.birthdate + '\'' +
                ", pts='" + this.pts + '\'' +
                ", reb='" + this.reb + '\'' +
                ", ast='" + this.ast + '\'' +
                ", to='" + this.to + '\'' +
                ", fastStat='" + this.fastStat + '\'' +
                '}';
    }

}





/*

    Interview tasks:
    
    1) Given a list of player stats (sample.json) create a spring boot endpoint that does the following:

    ● Respond with a list of objects that include the initial values of the sample.json along with the new "FastStat" field

    ● The response should be sorted by the "FastStat" score in descending order.


    Endpoint:
    {{host}}/faststat
    
    Request Example: input=sample.json
    {
        "id": 1,
        "name": "Kevin Anthony",
        "height": "6'10\"",
        "number": "37",
        "birthdate": "1997-11-20",
        "PTS": 7,
        "Reb": 3,
        "AST": 2,
        "TO": 1
    },
    
    Response Example: output
    [ 
      {
          "name": "Avery Adams",
          "height": "6'3\"",
          "number": "11",
          "birthdate": "1990-11-26",
          "PTS": 423,
          "Reb": 115,
          "AST": 63,
          "TO": 47,
          "FastStat": 610
      },
      {
          "name": "Kevin Anthony",
          "height": "6'10\"",
          "number": "37",
          "birthdate": "1997-11-20",
          "PTS": 7,
          "Reb": 3,
          "AST": 2,
          "TO": 1,
          "FastStat": 83
      }
    ]







*/












