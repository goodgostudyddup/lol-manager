package com.example.demo.bean.matchHIstory;

import lombok.Data;

//玩家操作英雄详细数据 例如伤害
@Data

public class stats {
    //"assists" 表示在游戏中一位玩家对队友进行的助攻次数
    private int assists;

    //"causedEarlySurrender"是一个布尔值（Boolean），表示是否因为某个玩家的请求而导致游戏提前结束。
    private boolean causedEarlySurrender;

    //"champLevel"表示玩家在游戏中达到的最高等级 英雄等级
    private int champLevel;

    //"combatPlayerScore"表示玩家在游戏中的战斗得分。
    private int combatPlayerScore;

    //damageDealtToObjectives该玩家对游戏目标（如敌方建筑、野怪、小兵等）造成的总伤害量
    private int damageDealtToObjectives;

    //damageDealtToTurrets该玩家对防御塔造成的总伤害量
    private int damageDealtToTurrets;

    //damageSelfMitigated该玩家在游戏中自己吸收的伤害量
    private int damageSelfMitigated;

    //deaths该玩家在游戏中的死亡次数
    private int deaths;

    //doubleKills 该玩家在游戏中获得的双杀次数
    private int doubleKills;

    //earlySurrenderAccomplice" 是一个布尔值，表示玩家是否协助了早期投降。
    private boolean earlySurrenderAccomplice;

    //firstBloodAssist该玩家是否协助获得了一血
    private boolean firstBloodAssist;

    //firstBloodKill该玩家是否获得了一血
    private boolean firstBloodKill;

    //firstInhibitorAssist该玩家是否协助摧毁了第一个防御塔
    private boolean firstInhibitorAssist;

    //firstInhibitorKill该玩家是否摧毁了第一个防御塔
    private boolean firstInhibitorKill;

    //firstTowerAssist该玩家是否协助摧毁了第一个防御塔 （水晶？）
    private boolean firstTowerAssist;

    //firstTowerKill该玩家是否摧毁了第一个防御塔（水晶？）
    private boolean firstTowerKill;

    //"gameEndedInEarlySurrender" 是一个布尔值，表示游戏是否因为早期投降而结束（十五投？）
    private boolean gameEndedInEarlySurrender;

    //gameEndedInSurrender" 是一个布尔值，表示游戏是否因为投降而结束
    private boolean gameEndedInSurrender;

    //goldEarned该玩家在游戏中的总金币收入
    private int goldEarned;

    //goldSpent该玩家在游戏中花费的金币数
    private int goldSpent;

    //inhibitorKills该玩家在游戏中摧毁的防御塔（水晶？）数量
    private int inhibitorKills;

    //item0 装备栏第零个装备id
    private int item0;

    //item1 装备栏第一个装备id
    private int item1;

    //item2 装备栏第二个装备id
    private int item2;

    //item3 装备栏第三个装备id
    private int item3;

    //item4 装备栏第四个装备id
    private int item4;

    //item5 装备栏第五个装备id
    private int item5;

    //item6 装备栏第六个装备id
    private int item6;

    //killingSprees 在没有死亡的情况下连续击杀敌方英雄的次数
    private int killingSprees;

    //kills该玩家在游戏中的击杀数
    private int kills;

    //largestCriticalStrike 该玩家在游戏中造成的最大暴击伤害
    private int largestCriticalStrike;

    //largestKillingSpree该玩家在游戏中连续击杀的最大数量
    private int largestKillingSpree;

    //largestMultiKill该玩家在游戏中获得的最大连杀数
    private int largestMultiKill;

    //longestTimeSpentLiving该玩家在游戏中生存的最长时间
    private int longestTimeSpentLiving;

    //magicDamageDealt 造成的魔法伤害
    private int magicDamageDealt;

    //magicDamageDealtToChampions 对英雄造成的魔法伤害
    private int magicDamageDealtToChampions;

    //magicalDamageTaken 受到的魔法伤害
    private int magicalDamageTaken;

    //neutralMinionsKilled 刷野数量（中立
    private int neutralMinionsKilled;

    //neutralMinionsKilledEnemyJungle 敌方野区刷野数量
    private int neutralMinionsKilledEnemyJungle;

    //neutralMinionsKilledTeamJungle 自己野区刷野数量
    private int neutralMinionsKilledTeamJungle;

    //objectivePlayerScore 该玩家在游戏中的目标得分
    private int objectivePlayerScore;

    //participantId 该玩家的参赛ID
    private int participantId;

    //pentaKills 该玩家在游戏中获得的五杀次数
    private int pentaKills;

    //perk0 该玩家在游戏中的第一个大符文ID
    private int perk0;

    //perk0Var1 该玩家在游戏中的第一个符文变量1
    private int perk0Var1;

    //perk0Var2 该玩家在游戏中的第一个符文变量2
    private int perk0Var2;

    //perk0Var3 该玩家在游戏中的第一个符文变量3
    private int perk0Var3;

    //perk1 该玩家在游戏中的第二个大符文ID
    private int perk1;

    //perk1Var1 该玩家在游戏中的第二个符文变量1
    private int perk1Var1;

    //perk1Var2 该玩家在游戏中的第二个符文变量2
    private int perk1Var2;

    //perk1Var3 该玩家在游戏中的第二个符文变量3
    private int perk1Var3;

    //perk2 该玩家在游戏中的第三个大符文ID
    private int perk2;

    //perk2Var1 该玩家在游戏中的第三个符文变量1
    private int perk2Var1;

    //perk2Var2 该玩家在游戏中的第三个符文变量2
    private int perk2Var2;

    //perk2Var3 该玩家在游戏中的第三个符文变量3
    private int perk2Var3;

    //perk3 该玩家在游戏中的第四个大符文ID
    private int perk3;

    //perk3Var1 该玩家在游戏中的第四个符文变量1
    private int perk3Var1;

    //perk3Var2 该玩家在游戏中的第四个符文变量2
    private int perk3Var2;

    //perk3Var3 该玩家在游戏中的第四个符文变量3
    private int perk3Var3;

    //perk4 该玩家在游戏中的第五个大符文ID
    private int perk4;

    //perk4Var1 该玩家在游戏中的第五个符文变量1
    private int perk4Var1;

    //perk4Var2 该玩家在游戏中的第五个符文变量2
    private int perk4Var2;

    //perk4Var3 该玩家在游戏中的第五个符文变量3
    private int perk4Var3;

    //perk5 该玩家在游戏中的第六个大符文ID
    private int perk5;

    //perk5Var1 该玩家在游戏中的第六个符文变量1
    private int perk5Var1;

    //perk5Var2 该玩家在游戏中的第六个符文变量2
    private int perk5Var2;
    //perk5Var3 该玩家在游戏中的第六个符文变量3
    private int perk5Var3;

    //perkPrimaryStyle 该玩家在游戏中的主符文ID
    private int perkPrimaryStyle;

    //perkSubStyle 该玩家在游戏中的副符文ID
    private int perkSubStyle;

    //physicalDamageDealt 玩家对敌方单位造成的物理伤害总量
    private int physicalDamageDealt;

    //physicalDamageDealtToChampions 该玩家在游戏中对敌方英雄造成的物理伤害
    private int physicalDamageDealtToChampions;

    //physicalDamageTaken 玩家在游戏中受到的物理伤害
    private int physicalDamageTaken;

    //playerAugment1 玩家在游戏中的第一个增强符文ID
    private int playerAugment1;

    //playerAugment2 玩家在游戏中的第二个增强符文ID
    private int playerAugment2;

    //playerAugment3 玩家在游戏中的第三个增强符文ID
    private int playerAugment3;

    //playerAugment4 玩家在游戏中的第四个增强符文ID
    private int playerAugment4;

    //playerAugment5 玩家在游戏中的第五个增强符文ID
    private int playerAugment5;

    //playerAugment6 玩家在游戏中的第六个增强符文ID
    private int playerAugment6;

    //playerScore0 玩家在游戏中的第一个评分
    private int playerScore0;

    //playerScore1 玩家在游戏中的第二个评分
    private int playerScore1;

    //playerScore2 玩家在游戏中的第三个评分
    private int playerScore2;

    //playerScore3 玩家在游戏中的第四个评分
    private int playerScore3;

    //playerScore4 玩家在游戏中的第五个评分
    private int playerScore4;

    //playerScore5 玩家在游戏中的第六个评分
    private int playerScore5;

    //playerScore6 玩家在游戏中的第七个评分
    private int playerScore6;

    //playerScore7 玩家在游戏中的第八个评分
    private int playerScore7;

    //playerScore8 玩家在游戏中的第九个评分
    private int playerScore8;

    //playerScore9 玩家在游戏中的第十个评分
    private int playerScore9;

    //playerSubteamId 玩家在游戏中的子队伍ID
    private int playerSubteamId;

    //quadraKills 该玩家在游戏中获得的四杀次数
    private int quadraKills;

    //sightWardsBoughtInGame 该玩家在游戏中购买的视野守卫数量
    private int sightWardsBoughtInGame;

    //subteamPlacement 玩家在游戏中的子队伍排名
    private int subteamPlacement;

    //teamEarlySurrendered 该玩家所在队伍是否在游戏中过早投降
    private boolean teamEarlySurrendered;

    //timeCCingOthers 该玩家在游戏中控制其他玩家的总时间
    private int timeCCingOthers;

    // totalDamageDealt 该玩家在游戏中对敌方单位造成的总伤害
    private int totalDamageDealt;

    //totalDamageDealtToChampions 该玩家在游戏中对敌方英雄造成的总伤害
    private int totalDamageDealtToChampions;

    //totalDamageTaken 该玩家在游戏中受到的总伤害
    private int totalDamageTaken;

    //totalHeal 通过各种方式（如技能、物品、符文等）总共恢复的生命值是
    private int totalHeal;

    //totalMinionsKilled 该玩家在游戏中击杀的小兵总数
    private int totalMinionsKilled;

    //totalPlayerScore 玩家在游戏中的总评分
    private int totalPlayerScore;

    //totalScoreRank 玩家在游戏中的总评分排名
    private int totalScoreRank;

    //totalTimeCrowdControlDealt 该玩家在游戏中对敌方玩家施加的总控制时间 ms
    private int totalTimeCrowdControlDealt;

    //totalUnitsHealed 该玩家在游戏中治疗的总单位数
    private int totalUnitsHealed;

    //tripleKills 该玩家在游戏中获得的三角杀次数
    private int tripleKills;

    //trueDamageDealt 该玩家在游戏中对敌方单位造成的真实伤害
    private int trueDamageDealt;

    //trueDamageDealtToChampions 该玩家在游戏中对敌方英雄造成的真实伤害
    private int trueDamageDealtToChampions;

    //trueDamageTaken 该玩家在游戏中受到的真实伤害
    private int trueDamageTaken;

    //turretKills 该玩家在游戏中摧毁的防御塔数量
    private int turretKills;

    //unrealKills 该玩家在游戏中获得的超神杀次数
    private int unrealKills;

    //visionScore 该玩家在游戏中的视野得分
    private int visionScore;

    //visionWardsBoughtInGame 该玩家在游戏中购买的视野守卫数量
    private int visionWardsBoughtInGame;

    //wardsKilled 该玩家在游戏中摧毁的守卫数量
    private int wardsKilled;

    //wardsPlaced 该玩家在游戏中放置的守卫数量
    private int wardsPlaced;

    //win 该玩家是否在游戏中获胜
    private boolean win;




}
