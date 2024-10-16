package com.example.demo.bean.matchHIstory;

import lombok.Data;

@Data
public class MatchHistory {
    //玩家id
    private Long accountId;
    //对局详情
    private Game Games;


}
