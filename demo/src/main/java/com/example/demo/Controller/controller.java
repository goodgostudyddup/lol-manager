package com.example.demo.Controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo.bean.Client;
import com.example.demo.bean.Player;
import com.example.demo.bean.items;
import com.example.demo.bean.spells;
import com.example.demo.http.RequestLcuUtil;
import com.example.demo.util.GameData;
import com.example.demo.util.WinUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Frequency;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
//
// 获取英雄头像照片 召唤师 物品照片的接口
//

@CrossOrigin(origins = "http://127.0.0.1:5500")
@Controller
@ResponseBody
@RequestMapping("Client")
public class controller {

    /**
     * 通过英雄ID查询英雄头像
     *
     */
    @RequestMapping("getChampionIcons")
    public JSONObject getChampionIcons(@RequestParam Integer championId) throws IOException {


        Client client = GameData.getClient();
        Player player = GameData.getPlayer();

        String endpoint = "https://127.0.0.1:" + client.getPort() + "/lol-game-data/assets/v1/champion-icons/" + championId + ".png";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", endpoint);
        return jsonObject;
    }

    //获取召唤师技能图标地址
    @RequestMapping("getSummonerSkill")
    public JSONObject getSummonerSkill(int id) throws IOException {
        Client client = GameData.getClient();
        RequestLcuUtil lcuUtil = new RequestLcuUtil(client);
        String jsonArray =lcuUtil.doGet("/lol-game-data/assets/v1/summoner-spells.json");
        List<spells> summonerSpells = JSON.parseArray(jsonArray, spells.class);
        System.out.println();
        String iconpath=" ";
        for (spells s : summonerSpells) {
            if (s.getId() == id) {
                iconpath = s.getIconPath();
            }
        }
        String endpoint = "https://127.0.0.1:" + client.getPort() + iconpath;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", endpoint);
        return jsonObject;
    }

    //获取物品图标地址
    @RequestMapping("getitem")
    public JSONObject getitems(int id) throws IOException {
        Client client = GameData.getClient();
        Player player = GameData.getPlayer();
        RequestLcuUtil lcuUtil = new RequestLcuUtil(client);
        List<items> items = GameData.getItems();
        String iconpath=" ";
        for (items s : items)
            if (s.getId() == id)
                iconpath = s.getIconPath();
        String endpoint = "https://127.0.0.1:" + client.getPort() + iconpath;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", endpoint);
        return jsonObject;

    }
}
