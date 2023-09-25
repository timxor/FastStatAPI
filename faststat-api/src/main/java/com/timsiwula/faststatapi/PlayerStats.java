// PlayerStats.java

package src.main.java.com.timsiwula.faststatapi;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class PlayerStats {
  
  // input only, 1 id, int
  // private int id;
  private @Id @GeneratedValue Long id;
  
  // 1 name, string
  private String name;
  
  // 2 height, string
  private String height;
  
  // 3 number, string
  private String number;
  
  // 4 birthdate, string
  private String birthdate;
  
  // 5 PTS, int
  @JsonProperty("PTS")
  private int pts;
  
  // 6 Reb, int
  @JsonProperty("Reb")
  private int reb;
  
  // 7 AST, int
  @JsonProperty("AST")
  private int ast;
  
  // 8 TO, int
  @JsonProperty("TO")
  private int to;
  
  // 9 FastStat, int
  private int fastStat;

	PlayerStats() {}

	PlayerStats(String name, String height, String number, String birthdate, 
              int pts, int reb, int ast, int to, int fastStat) {
		this.name = name;
		this.height = height;
    this.number = number;
    this.birthdate = birthdate;
    this.pts = pts;
    this.reb = reb;
    this.ast = ast;
    this.to = to;
    this.fastStat = fastStat;
	}
  
  
  // GETTERS
  
  public Long getId() {
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
  
  public int getFastStat() {
    return this.fastStat;
  }
  
  
  // SETTERS
  
  public void setId(Long id) {
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












