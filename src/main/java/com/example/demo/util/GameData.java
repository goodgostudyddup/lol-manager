package com.example.demo.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo.bean.Client;
import com.example.demo.bean.Player;
import com.example.demo.bean.getItemSkill;
import com.example.demo.bean.items;
import com.example.demo.http.RequestLcuUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GameData {
    private static Client client;
    private static Player player;
    private static List<items> items;
    private GameData() {} // 私有构造函数，防止实例化

    public static List<items> getItems() {
    if (items == null) {
            try {
                RequestLcuUtil lcuUtil = new RequestLcuUtil(getClient());
                String str =lcuUtil.doGet("/lol-game-data/assets/v1/items.json");
                List<items> items  = JSON.parseArray(str, items.class);
                return items;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static Client getClient() throws IOException {
        if (client == null) {
            client = WinUtil.getClientProcess();
        }
        return client;
    }

    public static Player getPlayer() {
        if (player == null) {
            try {
                RequestLcuUtil lcuUtil = new RequestLcuUtil(getClient());
                ObjectMapper mapper = new ObjectMapper();
                String str = lcuUtil.doGet("/lol-summoner/v1/current-summoner");
                player = mapper.readValue(str, Player.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return player;
    }
}