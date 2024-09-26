package org.example.bo;

import lombok.Data;

/**
 * 客户端信息
 *
 */
@Data
public class ClientBO {
    /**
     * 端口
     */
    private String port;

    /**
     * 密钥
     */
    private String token;

    /**
     * 区域
     */
    private String region;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "ClientBO{" +
                "port='" + port + '\'' +
                ", token='" + token + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}