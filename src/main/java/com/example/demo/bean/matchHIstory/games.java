package com.example.demo.bean.matchHIstory;

import lombok.Data;

import java.util.List;
@Data
//对局详情
public class games {
    //游戏结束时的结果  GameComplete游戏已经正常结束
    String endOfGameResul;

    //"gameCreation"是一个时间戳，表示游戏创建的时间。
    Long gameCreation;

    //"gameCreationDate" 字段表示的是游戏创建的时间
    String gameCreationDate;

    //"gameDuration" 表示的是游戏持续的时间，单位是毫秒
    Long gameDuration;

    //gameId" 指的是一场游戏的唯一标识符
    Long gameId;

    //gameMode" 字段表示的是游戏的模式，例如"CLASSIC"表示经典模式
    String gameMode;

    //gameType" 字段表示的是游戏的类型，例如"RANKED_SOLO_5x5"表示排位赛 MATCHED_GAME 表示匹配游戏
    String gameType;

    //mapId" 字段表示的是游戏的地图编号
    Integer mapId;

    //participantIdentities" 字段表示的是参与游戏的玩家信息，每个玩家都有一个对应的participantId
    private List<participantIdentities> participantIdentities;

    private List<participants> participants;
    //platformId 大区
    private String platformId;

    //queueId" 字游戏的队列ID，通常用于区分不同类型的游戏模式
    private Integer queueId;

    //seasonId" 字段表示的是游戏的赛季ID
    private Integer seasonId;

    //teams" 字段表示的是游戏中的队伍信息
    private List<teams> teams;
}
