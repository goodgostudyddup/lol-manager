package com.example.demo.bean;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Player {
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
