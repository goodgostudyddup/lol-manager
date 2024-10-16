package com.example.demo;

import com.example.demo.bean.Client;
import com.example.demo.util.GameData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        Client client = GameData.getClient();
        if(client.getPort() == null){
            System.out.println("请先启动游戏");
        }else {
            SpringApplication.run(DemoApplication.class, args);
        }
    }

}
