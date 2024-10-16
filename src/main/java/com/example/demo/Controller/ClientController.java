package com.example.demo.Controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.example.demo.bean.*;
import com.example.demo.bean.matchHIstory.MatchHistory;
import com.example.demo.http.RequestLcuUtil;
import com.example.demo.util.GameData;
import com.example.demo.util.WinUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"http://127.0.0.1:5501","http://127.0.0.1:5500"})

@Controller
@ResponseBody
@RequestMapping("Client")
public class ClientController {

    @RequestMapping("getToken")
    public JSONObject getToken() throws IOException {
        Client client = GameData.getClient();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", client.getToken());
        return jsonObject;
    }

    //根据游戏场次id获取游戏信息
    @RequestMapping("getMatchHistoryBygameId")
    public JSONObject getMatchHistoryBygameId(@RequestParam("gameId") String gameId) throws IOException {
        Client client = GameData.getClient();
        RequestLcuUtil lcuUtil = new RequestLcuUtil(client);
        String str = lcuUtil.doGet("/lol-match-history/v1/games/" + gameId);
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject;
    }

    @RequestMapping("login")
    public JSONObject login() throws IOException {
        try {
            Client client = GameData.getClient();
            RequestLcuUtil lcuUtil = new RequestLcuUtil(client);
            System.out.println(client.getToken());
            String str = lcuUtil.doGet("/lol-summoner/v1/current-summoner");
            JSONObject jsonObject = JSON.parseObject(str);
            jsonObject.put("token",client.getToken());
            return jsonObject;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 通过puuid查玩家信息

     */
    @RequestMapping("getInfoByPuuId")
    public JSONObject getInfoByPuuId(@RequestParam("puuid")String puuId) throws IOException {
        Client client = GameData.getClient();
        System.out.println(puuId);
        RequestLcuUtil lcuUtil = new RequestLcuUtil(client);
        String resp = lcuUtil.doGet("/lol-summoner/v2/summoners/puuid/" + puuId);
        return JSON.parseObject(resp);
    }

    @RequestMapping("getrank")
    public String getrank() throws IOException {
        Client client = GameData.getClient();
        RequestLcuUtil lcuUtil = new RequestLcuUtil(client);
        ObjectMapper mapper = new ObjectMapper();
        String str = lcuUtil.doGet("/lol-summoner/v1/current-summoner");
        Player player = mapper.readValue(str, Player.class);

        String resp = lcuUtil.doGet("/lol-ranked/v1/ranked-stats/" + player.getPuuid());
        return resp;
    }

    // 获取单条战绩
    @RequestMapping("getProductsMatchHistoryBybegIndex")
    public JSONObject getProductsMatchHistoryByBybegIndex(@RequestParam("puuid")String puuId,int begIndex) throws IOException {
        Client client = GameData.getClient();
        RequestLcuUtil lcuUtil = new RequestLcuUtil(client);
        ObjectMapper mapper = new ObjectMapper();
        String endpoint = "/lol-match-history/v1/products/lol/" + puuId + "/matches?begIndex=" + begIndex + "&endIndex=" + begIndex;
        String resp = lcuUtil.doGet(endpoint);
        JSONObject jsonObject = JSONObject.parseObject(resp);
        return jsonObject;

    }


}
