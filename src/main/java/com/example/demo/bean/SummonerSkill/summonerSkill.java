package com.example.demo.bean.SummonerSkill;

import lombok.Data;

import java.util.List;
@Data
// 召唤师技能
// json文件接口 ：/lol-game-data/assets/v1/summoner-spells.json
public class summonerSkill {
    //技能编号
    static Integer id;
    //技能名称
    static String name;
    //技能描述
    static String description;
    //技能冷却时间
    static Integer cooldown;
    //游戏模式
    static List<String> gameModes;
    //技能图标地址
    static String iconPath;

}
