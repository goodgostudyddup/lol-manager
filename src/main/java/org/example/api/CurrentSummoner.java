package org.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.bo.ClientBO;
import org.example.bo.PlayerBo;
import org.example.http.TrustAllCertificates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//获取召唤师player基础信息

public class CurrentSummoner {
    public static PlayerBo getCurrentSummoner(ClientBO client) throws NoSuchAlgorithmException, KeyManagementException {
        try {

            TrustAllCertificates.main();
            URL url = new URL("https://127.0.0.1:"+client.getPort()+"/lol-summoner/v1/current-summoner");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // 用户名和密码
            String user = "riot";
            String password = client.getToken();
            // 拼接用户名和密码
            String auth = user + ":" + password;
            // 编码
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            // 设置请求头
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String res = String.valueOf(response);
            ObjectMapper mapper = new ObjectMapper();
            PlayerBo player = mapper.readValue(res, PlayerBo.class);
            return player;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}