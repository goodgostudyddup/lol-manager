package org.example;

import org.example.bo.ClientBO;
import org.example.utils.ClientIsRun;
import org.example.utils.WinUtil;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (ClientIsRun.isRun("LeagueClientUx.exe")) {
            System.out.println("检测到英雄联盟进程");
            ClientBO clientBO = WinUtil.getClientProcess();
        } else {
            System.out.println("未检测到英雄联盟进程");
        }
    }
}