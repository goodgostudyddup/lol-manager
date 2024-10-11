package com.example.demo.bean.matchHIstory;

import lombok.Data;

import java.util.List;

@Data
public class Game {
    //gameBeginDate
    private String gameBeginDate;
    //gameCount
    private int gameCount;
    //gameEndDate
    private String gameEndDate;
    //gameIndexBegin
    private int gameIndexBegin;
    //gameIndexEnd
    private int gameIndexEnd;

    //games
    List<games> games;
}
