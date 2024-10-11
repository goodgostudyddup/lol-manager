package org.example.bo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayerBo {
    private long accountId;
    private String displayName;
    private String gameName;
    private String internalName;
    private boolean nameChangeFlag;
    private int percentCompleteForNextLevel;
    private String privacy;
    private int profileIconId;
    private String puuid;
    private RerollPoints rerollPoints;
    private long summonerId;
    private int summonerLevel;
    private String tagLine;
    private boolean unnamed;
    private int xpSinceLastLevel;
    private int xpUntilNextLevel;

    // Getters and setters

    public static class RerollPoints {
        @JsonProperty("currentPoints")
        private int currentPoints;
        @JsonProperty("maxRolls")
        private int maxRolls;
        @JsonProperty("numberOfRolls")
        private int numberOfRolls;
        @JsonProperty("pointsCostToRoll")
        private int pointsCostToRoll;
        @JsonProperty("pointsToReroll")
        private int pointsToReroll;

        // Getters and setters
    }
}
