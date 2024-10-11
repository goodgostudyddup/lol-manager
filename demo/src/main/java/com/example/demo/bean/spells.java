package com.example.demo.bean;

import lombok.Data;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Data
//召唤师技能
public class spells {
    private int id;
    private String name;
    private String description;
    private int summonerLevel;
    private int cooldown;
    private List<String> gameModes;
    private String iconPath;
}
