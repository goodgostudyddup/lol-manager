package org.example;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.example.api.ScoreBO;
import org.example.bo.ClientBO;
import org.example.bo.PlayerBo;
import org.example.api.CurrentSummoner;
import org.example.http.RequestLcuUtil;
import org.example.utils.ClientIsRun;
import org.example.utils.WinUtil;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static ClientBO client;
    static PlayerBo player;
    private static RequestLcuUtil requestLcuUtil ;


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        if (ClientIsRun.isRun("LeagueClientUx.exe")) {
            client = WinUtil.getClientProcess();
            player=CurrentSummoner.getCurrentSummoner(client);
            requestLcuUtil = new RequestLcuUtil(client);
            List<ScoreBO> lb = getScoreById(player.getPuuid(),1);
        } else {
            System.out.println("未检测到英雄联盟进程");
        }
    }

    public static List<ScoreBO> getScoreById(String id, int endIndex) throws IOException {
        String endpoint = "/lol-match-history/v1/products/lol/" + id + "/matches?begIndex=0&endIndex=" + endIndex;
        String resp = requestLcuUtil.doGet(endpoint);
        JSONObject jsonObject = JSON.parseObject(resp);
        return jsonObject.getJSONObject("games").getJSONArray("games")
                .toJavaList(JSONObject.class)
                .stream().map(i -> {
                    JSONObject participants = i.getJSONArray("participants").getJSONObject(0);
                    JSONObject stats = participants.getJSONObject("stats");
                    return stats.toJavaObject(ScoreBO.class);
                }).collect(Collectors.toList());
    }
}