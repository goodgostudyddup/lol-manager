package com.example.demo;

import com.example.demo.bean.Client;
import com.example.demo.http.RequestLcuUtil;
import com.example.demo.util.GameData;

import javax.imageio.ImageIO;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        Client client = GameData.getClient();
        RequestLcuUtil LcuUtil = new RequestLcuUtil(client);
//       LcuUtil.download("/lol-game-data/assets/ASSETS/Items/Icons2D/1001_Class_T1_BootsofSpeed.png");

    }
}
