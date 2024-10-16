package com.example.demo.bean.matchHIstory;

import lombok.Data;

///玩家操作英雄详情
@Data
public class participants {
    //championId 玩家所选英雄
    Integer championId;

    //highestAchievedSeasonTier 玩家最高段位
    String highestAchievedSeasonTier;

    //participantId 玩家在对局中排位
    Integer participantId;

    //spell1Id 第一个召唤师技能
    Integer spell1Id;

    //spell2Id 第二个召唤师技能
    Integer spell2Id;

    //玩家操作英雄详细数据 例如伤害
    stats stats;

    //teamId 玩家所在队伍
    Integer teamId;

    //timeline 玩家操作英雄时间线
    timeline timeline;
}
