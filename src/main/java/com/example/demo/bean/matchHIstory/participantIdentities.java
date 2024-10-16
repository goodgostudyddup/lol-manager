package com.example.demo.bean.matchHIstory;

import com.example.demo.bean.Player;
import lombok.Data;

//participantIdentities" 字段表示的是参与游戏的玩家信息，每个玩家都有一个对应的participantId
@Data
public class participantIdentities {
    //participantId玩家id
    Integer participantId;
    //player玩家信息
    Player player;
}
