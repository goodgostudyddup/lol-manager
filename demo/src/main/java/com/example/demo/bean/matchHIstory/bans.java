package com.example.demo.bean.matchHIstory;

import lombok.Data;

//禁用的英雄
@Data
public class bans {
    //championId
    private int championId;
    //pickTurn 几楼ban的
    private int pickTurn;
}
