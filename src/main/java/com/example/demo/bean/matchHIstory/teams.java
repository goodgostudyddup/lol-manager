package com.example.demo.bean.matchHIstory;

import lombok.Data;

import java.util.List;
@Data
public class teams {
    //禁用的英雄
    List<bans> bans;

    //baronKills 大龙击杀
    int baronKills;

    //dominionVictoryScore 领地胜利分数
    int dominionVictoryScore;

    //dragonKills 小龙击杀
    int dragonKills;

    //firstBaron 第一条大龙击杀
    boolean firstBaron;

    //firstBlood 第一滴血
    boolean firstBlood;

    //firstDargon 第一条小龙击杀
    boolean firstDargon;

    //firstInhibitor 第一个水晶
    boolean firstInhibitor;

    //firstTower 第一座塔
    boolean firstTower;

    //hordeKills
    int hordeKills;

    //inhibitorKills 水晶击杀
    int inhibitorKills;

    //riftHeraldKills 峡谷先锋击杀
    int riftHeraldKills;

    //teamId 队伍id
    int teamId;

    //towerKills 塔击杀
    int towerKills;

    //vilemawKills
    int vilemawKills;

    // win win or fail
    String win;

}
