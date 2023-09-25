package src.main.java.com.timsiwula.faststatapi.models;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "height", "number", "birthdate", "PTS", "Reb", "AST", "TO", "FastStat" })
@JsonRootName(value = "result")
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

    public int getFastStat() {
        return this.fastStat;
    }
    
    public void setFastStat(int fastStat) {
        this.fastStat = fastStat;
    }
    
}
