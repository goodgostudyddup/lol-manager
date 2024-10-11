package com.example.demo.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Client {
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
}
